package com.sakura.forum.web.controller;

import com.sakura.form.core.pojo.SysUser;
import com.sakura.forum.system.service.ISysUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system")
@Tag(name = "测试")
public class DemoController {

    @Resource
    private ISysUserService sysUserService;

    //    @Operation(summary = "测试")
    @GetMapping("/demo")
    public SysUser demo() {
        return sysUserService.demo("admin");
    }
}
