package com.sakura.forum.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sakura.forum.core.domain.entity.SysUser;
import com.sakura.forum.enums.ResultCodeEnum;
import com.sakura.forum.exception.ServiceException;
import com.sakura.forum.system.mapper.SysUserMapper;
import com.sakura.forum.system.service.ISysUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor_ = @__({@Autowired}))
public class ISysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    private final SysUserMapper sysUserMapper;

    @Override
    public SysUser searchUserInfo(long id) {
        return this.lambdaQuery()
                .eq(SysUser::getId, id)
                .oneOpt()
                .orElseThrow(() -> new ServiceException(ResultCodeEnum.USER_IS_NOT_EXIST));
    }
}
