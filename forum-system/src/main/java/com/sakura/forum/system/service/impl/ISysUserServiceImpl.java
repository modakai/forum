package com.sakura.forum.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sakura.forum.core.domain.dto.ChangePasswordDto;
import com.sakura.forum.core.domain.entity.SysUser;
import com.sakura.forum.enums.ResultCodeEnum;
import com.sakura.forum.exception.ServiceException;
import com.sakura.forum.system.mapper.SysUserMapper;
import com.sakura.forum.system.service.ISysUserService;
import com.sakura.forum.utils.LoginUserUtil;
import com.sakura.forum.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ISysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {


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

}
