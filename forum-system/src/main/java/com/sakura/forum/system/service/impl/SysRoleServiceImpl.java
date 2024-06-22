package com.sakura.forum.system.service.impl;

import com.sakura.forum.core.domain.entity.SysUser;
import com.sakura.forum.system.mapper.SysRoleMapper;
import com.sakura.forum.system.service.ISysRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Resource
    private SysRoleMapper roleMapper;

    @Override
    public List<String> searchRolePerms(SysUser user) {
        return roleMapper.selectRolePerms(user.getId());
    }
}
