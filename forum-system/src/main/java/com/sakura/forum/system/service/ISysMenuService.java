package com.sakura.forum.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sakura.forum.core.domain.dto.MenuRoleDto;
import com.sakura.forum.core.domain.dto.MenuSaveDto;
import com.sakura.forum.core.domain.entity.SysMenu;
import com.sakura.forum.core.domain.entity.SysUser;
import com.sakura.forum.core.domain.vo.Router;

import java.util.List;

public interface ISysMenuService extends IService<SysMenu> {


    List<MenuRoleDto> searchMenuPerms(SysUser user);

    List<SysMenu> searchMenuTreeList(Long userId);

    List<Router> buildRouters(List<SysMenu> menuList);

    void saveMenu(MenuSaveDto menuSaveDto);
}
