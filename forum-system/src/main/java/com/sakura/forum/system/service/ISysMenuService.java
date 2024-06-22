package com.sakura.forum.system.service;

import com.sakura.forum.core.domain.dto.MenuRoleDto;
import com.sakura.forum.core.domain.entity.SysUser;

import java.util.List;

public interface ISysMenuService {


    List<MenuRoleDto> searchMenuPerms(SysUser user);
}
