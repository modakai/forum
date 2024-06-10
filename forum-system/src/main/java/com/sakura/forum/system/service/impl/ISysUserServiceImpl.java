package com.sakura.forum.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sakura.form.core.pojo.SysUser;
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
    public SysUser demo(String username) {
        return lambdaQuery().eq(SysUser::getUsername, username).one();
    }

}
