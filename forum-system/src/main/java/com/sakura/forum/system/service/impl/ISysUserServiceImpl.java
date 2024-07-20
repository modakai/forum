package com.sakura.forum.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sakura.forum.core.domain.entity.SysUser;
import com.sakura.forum.enums.ResultCodeEnum;
import com.sakura.forum.exception.ServiceException;
import com.sakura.forum.system.mapper.SysUserMapper;
import com.sakura.forum.system.service.ISysUserService;
import org.springframework.stereotype.Service;

@Service
public class ISysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public SysUser searchUserInfo(long id) {
        return this.lambdaQuery()
                .eq(SysUser::getId, id)
                .oneOpt()
                .orElseThrow(() -> new ServiceException(ResultCodeEnum.USER_IS_NOT_EXIST));
    }
}
