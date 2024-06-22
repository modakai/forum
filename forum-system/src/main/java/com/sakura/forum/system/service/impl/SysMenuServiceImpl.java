package com.sakura.forum.system.service.impl;

import com.sakura.forum.core.domain.dto.MenuRoleDto;
import com.sakura.forum.core.domain.entity.SysUser;
import com.sakura.forum.system.mapper.SysMenuMapper;
import com.sakura.forum.system.service.ISysMenuService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuServiceImpl implements ISysMenuService {

    @Resource
    private SysMenuMapper menuMapper;

    @Override
    public List<MenuRoleDto> searchMenuPerms(SysUser user) {
        return menuMapper.selectMenuPerms(user.getId());
    }
}
