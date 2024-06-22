package com.sakura.forum.web.controller.system;


import cn.dev33.satoken.util.SaResult;
import com.sakura.forum.core.domain.dto.SysUserLoginDto;
import com.sakura.forum.core.domain.entity.SysUser;
import com.sakura.forum.framework.web.service.permission.SysPermissionService;
import com.sakura.forum.framework.web.service.syslogin.SysLoginService;
import com.sakura.forum.utils.LoginUserUtil;
import com.sakura.forum.web.vo.SysUserInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Tag(name = "系统用户登录接口", description = "系统登录接口")
@RestController
@RequestMapping("/system")
public class SysLoginController {

    private final SysLoginService sysLoginService;
    private final SysPermissionService permissionService;

    public SysLoginController(SysLoginService sysLoginService, SysPermissionService permissionService) {
        this.sysLoginService = sysLoginService;
        this.permissionService = permissionService;
    }


    // todo 后续使用工厂加策略模式优化
    @Operation(summary = "登录接口", description = "登录接口")
    @PostMapping("login")
    public SaResult login(@RequestBody @Validated SysUserLoginDto formData) {
        String token = sysLoginService.login(formData);
        return SaResult.data(token);
    }

    @Operation(summary = "获取用户信息", description = "获取用户信息")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = SysUserInfoVo.class)))
    @GetMapping("getInfo")
    public SaResult getInfo() {
        SysUserInfoVo result = new SysUserInfoVo();
        // 获取用户信息
        SysUser sysUser = LoginUserUtil.getLoginSysUser();
        result.setUser(sysUser);
        // 获取角色
        Set<String> roleList = permissionService.getRolePermission(sysUser);
        List<String> permission = permissionService.getMenuPermission(sysUser);

        result.setRoles(roleList);
        result.setPermissions(permission);

        return SaResult.ok().setData(result);
    }


}
