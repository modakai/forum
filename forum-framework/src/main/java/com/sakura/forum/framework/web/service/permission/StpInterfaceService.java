package com.sakura.forum.framework.web.service.permission;

import cn.dev33.satoken.stp.StpInterface;
import com.sakura.forum.constant.CommonConstant;
import com.sakura.forum.constant.RedisCacheConstant;
import com.sakura.forum.core.domain.entity.SysUser;
import com.sakura.forum.utils.RedisUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 读取登入用户的权限
 */
@Component
public class StpInterfaceService implements StpInterface {

    private final RedisUtil redisUtil;

    public StpInterfaceService(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<String> x = isAdmin(Long.parseLong((String) loginId));
        if (x != null) return x;

        // 1. 声明权限码集合
        List<String> permissionList = new ArrayList<>();

        // 2. 获取权限集合
        // 2.1 通过用户拥有的角色列表获取对应的权限码集合
        List<String> roleList = getRoleList(loginId, loginType);

        for (int i = 0; i < roleList.size(); i++) {
            String roleKey = roleList.get(i);
            Set<String> permissionRoleList = redisUtil.getCacheMapValue(RedisCacheConstant.PERMISSION_KEY, roleKey);
            if (CollectionUtils.isEmpty(permissionRoleList)) continue;
            permissionList.addAll(permissionRoleList);
        }

        return permissionList;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 1. 判断是否为超级管理员
        List<String> x = isAdmin(Long.parseLong((String) loginId));
        if (x != null) return x;

        // 从 redis中获取角色集合
        List<String> roleList = redisUtil.getCacheList(RedisCacheConstant.ROLE_KEY + loginId);
        // 如果为空则返回空的集合
        return CollectionUtils.isEmpty(roleList) ? Collections.emptyList() : roleList;
    }

    private static List<String> isAdmin(Long loginId) {
        if (SysUser.isAdmin(loginId)) {
            return List.of(CommonConstant.ADMIN_PERMISSION);
        }
        return null;
    }
}
