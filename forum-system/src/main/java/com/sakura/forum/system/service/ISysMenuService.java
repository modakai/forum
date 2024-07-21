package com.sakura.forum.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sakura.forum.core.domain.dto.MenuQueryDto;
import com.sakura.forum.core.domain.dto.MenuRoleDto;
import com.sakura.forum.core.domain.dto.MenuSaveDto;
import com.sakura.forum.core.domain.entity.SysMenu;
import com.sakura.forum.core.domain.entity.SysUser;
import com.sakura.forum.core.domain.vo.Router;

import java.util.List;

public interface ISysMenuService extends IService<SysMenu> {


    List<MenuRoleDto> searchMenuPerms(SysUser user);

    /**
     * 获取路由菜单树形列表
     *
     * @param userId 用户id
     * @return 路由菜单树形列表
     */
    List<SysMenu> searchMenuTreeList(Long userId);

    List<Router> buildRouters(List<SysMenu> menuList);

    void saveMenu(MenuSaveDto menuSaveDto);

    /**
     * 获取树形菜单列表
     *
     * @param query  查询参数
     * @param userId 用户id
     * @return 树形菜单列表
     */
    List<SysMenu> treeList(MenuQueryDto query, Long userId);

    /**
     * 删除菜单 以及关联的数据
     *
     * @param id 菜单id
     */
    List<String> removeMenu(Long id);

    /**
     * 获取菜单关联的角色key列表
     *
     * @param menuList 菜单列表
     * @return 菜单关联的角色key列表
     */
    List<String> findMenuRelationRoleKeyList(List<SysMenu> menuList);
}
