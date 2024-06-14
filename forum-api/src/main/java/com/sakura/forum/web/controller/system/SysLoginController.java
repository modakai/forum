package com.sakura.forum.web.controller.system;


import com.sakura.forum.core.AjaxResult;
import com.sakura.forum.core.domain.dto.SysUserLoginDto;
import com.sakura.forum.enums.ResultCodeEnum;
import com.sakura.forum.framework.web.service.syslogin.SysLoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "系统用户登录接口", description = "系统登录接口")
@RestController
@RequestMapping("/system")
public class SysLoginController {

    private final SysLoginService sysLoginService;

    public SysLoginController(SysLoginService sysLoginService) {
        this.sysLoginService = sysLoginService;
    }


    // todo 后续使用工厂加策略模式优化
    @Operation(summary = "登录接口", description = "登录接口")
    @PostMapping("login")
    public AjaxResult<Object> login(@RequestBody SysUserLoginDto formData) {
        String token = sysLoginService.login(formData);
        return AjaxResult.success(ResultCodeEnum.SUCCESS, token);
    }


}
