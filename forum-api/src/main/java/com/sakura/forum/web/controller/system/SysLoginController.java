package com.sakura.forum.web.controller.system;


import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.sakura.forum.core.AjaxResult;
import com.sakura.forum.core.domain.dto.SysUserLoginDto;
import com.sakura.forum.enums.ResultCodeEnum;
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


    @Operation(summary = "登录接口", description = "登录接口")
    @PostMapping("login")
    public AjaxResult<Object> login(@RequestBody SysUserLoginDto formData) {
        // sa-token 实现
        StpUtil.login(11111111111L);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return AjaxResult.success(ResultCodeEnum.SUCCESS, tokenInfo);
    }
}
