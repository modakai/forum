package com.sakura.forum.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sakura.forum.core.LoginUser;
import com.sakura.forum.core.domain.dto.ChangePasswordDto;
import com.sakura.forum.core.domain.dto.ChangeProfileDto;
import com.sakura.forum.core.domain.entity.SysUser;
import com.sakura.forum.enums.ResultCodeEnum;
import com.sakura.forum.exception.ServiceException;
import com.sakura.forum.struct.BeanCopyMapper;
import com.sakura.forum.system.mapper.SysUserMapper;
import com.sakura.forum.system.service.ISysUserService;
import com.sakura.forum.utils.LoginUserUtil;
import com.sakura.forum.utils.PasswordUtil;
import com.sakura.forum.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ISysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {


    private final RedisUtil redisUtil;

    @Override
    public SysUser searchUserInfo(long id) {
        return this.lambdaQuery()
                .eq(SysUser::getId, id)
                .oneOpt()
                .orElseThrow(() -> new ServiceException(ResultCodeEnum.USER_IS_NOT_EXIST));
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void changePassword(ChangePasswordDto changePasswordDto) {
        Long userId = LoginUserUtil.getLoginSysUserId();
        // 1 查询用户的密码
        SysUser loginUser = lambdaQuery()
                .eq(SysUser::getId, userId)
                .oneOpt()
                .orElseThrow(() -> new ServiceException(ResultCodeEnum.USER_IS_NOT_EXIST));
        // 2 校验旧密码
        PasswordUtil.validate(changePasswordDto.getOldPassword(), loginUser.getPassword());
        // 3 将新密码更新
        lambdaUpdate()
                .eq(SysUser::getId, userId)
                .set(SysUser::getPassword, PasswordUtil.encryption(changePasswordDto.getNewPassword()))
                .update();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void changeProfile(ChangeProfileDto form) {
        // 1 取Id
        LoginUser loginSysUser = LoginUserUtil.getLoginSysUser();
        Long userId = loginSysUser.getId();
        // 2 转换对象
        SysUser sysUser = BeanCopyMapper.INSTANCE.changeProfileDtoToSysUser(form);
        // 3 修改数据
        lambdaUpdate()
                .eq(SysUser::getId, userId)
                .update(sysUser);

        // 修改缓存？
        loginSysUser
                .setGender(form.getGender())
                .setNickName(form.getNickName())
                .setPhone(form.getPhone());

        // 更新缓存
        LoginUserUtil.cacheLoginUser(loginSysUser);
    }

    @Override
    public void changeAvatar(String avatarUrl) {
        // 取用户id
        LoginUser loginSysUser = LoginUserUtil.getLoginSysUser();
        // 修改数据
        lambdaUpdate()
                .eq(SysUser::getId, loginSysUser.getId())
                .set(SysUser::getAvatar, avatarUrl)
                .update();

        // 修改缓存数据
        loginSysUser.setAvatar(avatarUrl);
        LoginUserUtil.cacheLoginUser(loginSysUser);
    }

}
