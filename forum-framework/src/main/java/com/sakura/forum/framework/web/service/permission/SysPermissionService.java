package com.sakura.forum.framework.web.service.permission;

import com.sakura.forum.constant.RedisCacheConstant;
import com.sakura.forum.core.domain.dto.MenuRoleDto;
import com.sakura.forum.core.domain.entity.SysUser;
import com.sakura.forum.system.service.ISysMenuService;
import com.sakura.forum.system.service.ISysRoleService;
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
            rolePerms.add("*");
        } else {
            // 去查询用户的角色
            List<String> roleList = roleService.searchRolePerms(user);
            rolePerms.addAll(roleList);
        }

        // 缓存角色列表 set 集合存储
        redisUtil.setCacheSet(RedisCacheConstant.ROLE_KEY + user.getId(), rolePerms);

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
            menuRoleDto.setMenuPerms("*");
            menuRoleDto.setRoleKey("*");

            menuRoleList.add(menuRoleDto);
        } else {
            // 去查询用户的菜单
            menuRoleList.addAll(menuService.searchMenuPerms(user));
        }

        // 缓存
        // 根据 roleKey进行权限分组
        Map<String, Set<String>> rolePerms = menuRoleList.stream().collect(
                // 根据 roleKey进行权限分组
                Collectors.groupingBy(MenuRoleDto::getRoleKey,
                        // 获取菜单权限
                        Collectors.mapping(MenuRoleDto::getMenuPerms, Collectors.toSet())));
        // 缓存
        redisUtil.setCacheMap("role:perms", rolePerms);

        return menuRoleList.stream().map(MenuRoleDto::getMenuPerms).toList();
    }
}
