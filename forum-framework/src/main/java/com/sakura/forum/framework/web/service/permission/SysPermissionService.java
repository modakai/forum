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

            List<String> roleList = redisUtil.getCacheList(RedisCacheConstant.ROLE_KEY + user.getId());
            // 判断是否为空
            if (roleList == null || roleList.isEmpty()) {
                // 去查询用户的角色
                roleList = roleService.searchRolePerms(user);

                // 缓存角色列表 set 集合存储
                redisUtil.setCacheList(RedisCacheConstant.ROLE_KEY + user.getId(), roleList);
            }
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
        Map<String, Set<String>> rolePerms;

        // 判断是否为 超级管理员
        if (user.isAdmin()) {
            // 上帝权限
            rolePerms = Collections.singletonMap(ADMIN_PERMISSION, Collections.singleton(ADMIN_PERMISSION));
        } else {
            // 尝试从缓存中获取权限分组
            rolePerms = redisUtil.getCacheMap(RedisCacheConstant.PERMISSION_KEY);

            if (rolePerms == null) {
                // 如果缓存中没有数据，去查询用户的菜单并更新缓存
                List<MenuRoleDto> menuRoleList = menuService.searchMenuPerms(user);
                // 如果没有对应的数据，直接返回空列表
                if (menuRoleList == null || menuRoleList.isEmpty()) {
                    return Collections.emptyList();
                }

                rolePerms = menuRoleList.stream().collect(
                        Collectors.groupingBy(
                                MenuRoleDto::getRoleKey,
                                Collectors.mapping(
                                        MenuRoleDto::getMenuPerms,
                                        Collectors.toSet()
                                )
                        )
                );

                redisUtil.setCacheMap(RedisCacheConstant.PERMISSION_KEY, rolePerms);
            }
        }

        // 返回扁平化的权限列表
        return rolePerms.values().stream()
                .flatMap(Set::stream)
                .collect(Collectors.toList());
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
