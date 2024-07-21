package com.sakura.forum.framework.web.service.permission;

import com.sakura.forum.constant.CommonConstant;
import com.sakura.forum.constant.RedisCacheConstant;
import com.sakura.forum.core.domain.dto.MenuRoleDto;
import com.sakura.forum.core.domain.entity.SysMenu;
import com.sakura.forum.core.domain.entity.SysUser;
import com.sakura.forum.core.domain.vo.Router;
import com.sakura.forum.system.service.ISysMenuService;
import com.sakura.forum.system.service.ISysRoleService;
import com.sakura.forum.utils.LoginUserUtil;
import com.sakura.forum.utils.RedisUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 权限服务
 */
@Component
public class SysPermissionService {

    private final String ADMIN_PERMISSION = CommonConstant.ADMIN_PERMISSION;

    @Resource
    private ISysRoleService roleService;
    @Resource
    private ISysMenuService menuService;
    @Resource
    private RedisUtil redisUtil;

    /**
     * 获取用户的角色列表
     *
     * @param user 用户
     * @return 角色列表
     */
    public Set<String> getRolePermission(SysUser user) {
        Set<String> rolePerms = new HashSet<>(8);
        // 判断是否为 超级管理员
        if (user.isAdmin()) {
            // 上帝角色
            rolePerms.add(ADMIN_PERMISSION);
        } else {
            // 去查询用户的角色
            // TODO 先尝试从缓存获取
            List<String> roleList = roleService.searchRolePerms(user);

            // 缓存角色列表 set 集合存储
            redisUtil.setCacheList(RedisCacheConstant.ROLE_KEY + user.getId(), roleList);

            rolePerms.addAll(roleList);
        }

        return rolePerms;
    }

    /**
     * 获取用户的菜单权限
     *
     * @param user 用户
     * @return 菜单权限
     */
    public List<String> getMenuPermission(SysUser user) {
        List<MenuRoleDto> menuRoleList = new ArrayList<>(8);
        // 判断是否为 超级管理员
        if (user.isAdmin()) {
            // 上帝权限
            MenuRoleDto menuRoleDto = new MenuRoleDto();
            menuRoleDto.setMenuPerms(ADMIN_PERMISSION);
            menuRoleDto.setRoleKey(ADMIN_PERMISSION);

            menuRoleList.add(menuRoleDto);
        } else {
            // 去查询用户的菜单
            menuRoleList.addAll(menuService.searchMenuPerms(user));
            // 根据 roleKey进行权限分组
            Map<String, Set<String>> rolePerms = menuRoleList.stream().collect(
                    // 根据 roleKey进行权限分组
                    Collectors.groupingBy(MenuRoleDto::getRoleKey,
                            // 获取菜单权限
                            Collectors.mapping(MenuRoleDto::getMenuPerms, Collectors.toSet())));
            // 缓存
            redisUtil.setCacheMap(RedisCacheConstant.PERMISSION_KEY, rolePerms);
        }


        return menuRoleList.stream().map(MenuRoleDto::getMenuPerms).toList();
    }

    /**
     * 获取前端路由
     *
     * @return 返回前端对应的路由组件信息 {@link Router}
     */
    public List<Router> getRouters() {
        // 1 获取当前登入的用户
        Long userId = LoginUserUtil.getLoginSysUserId();
        List<SysMenu> menuList = menuService.searchMenuTreeList(userId);

        // 转化成前端需要的路由组件
        return menuService.buildRouters(menuList);
    }

    /**
     * 删除 rolePerms 缓存
     *
     * @param roleKeyList roleKey的列表
     */
    public void removeCacheRolePerms(List<String> roleKeyList) {
        // 使用redis 工具类缓存
        redisUtil.deleteCacheMapValue(RedisCacheConstant.PERMISSION_KEY, roleKeyList);
    }
}
