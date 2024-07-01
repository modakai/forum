package com.sakura.forum.system.service;

import com.sakura.forum.core.domain.dto.MenuRoleDto;
import com.sakura.forum.core.domain.entity.SysMenu;
import com.sakura.forum.core.domain.entity.SysUser;
import com.sakura.forum.core.domain.vo.Router;

import java.util.List;

public interface ISysMenuService {


    List<MenuRoleDto> searchMenuPerms(SysUser user);

    List<SysMenu> searchMenuTreeList(Long userId);

    List<Router> buildRouters(List<SysMenu> menuList);
}
