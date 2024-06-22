package com.sakura.forum.system.service;

import com.sakura.forum.core.domain.entity.SysUser;

import java.util.List;

/**
 * 系统角色服务类
 */
public interface ISysRoleService {

    /**
     * 获取用户的权限列表
     *
     * @param user 用户
     * @return 权限列表
     */
    List<String> searchRolePerms(SysUser user);
}
