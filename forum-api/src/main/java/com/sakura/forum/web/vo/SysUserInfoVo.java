package com.sakura.forum.web.vo;

import com.sakura.forum.core.domain.entity.SysUser;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * 系统用户信息
 */
@Schema(description = "后台用户实体")
public class SysUserInfoVo {

    @Schema(name = "user", description = "用户信息")
    private SysUser user;

    private List<String> permissions;
    private List<String> roles;

    public SysUserInfoVo(SysUser user, List<String> permissions, List<String> roles) {
        this.user = user;
        this.permissions = permissions;
        this.roles = roles;
    }

    public SysUserInfoVo() {
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "SysUserInfoVo{" +
                "user=" + user +
                ", permissions=" + permissions +
                ", roles=" + roles +
                '}';
    }
}
