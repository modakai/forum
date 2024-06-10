package com.sakura.forum.web.controller;

import com.sakura.form.core.pojo.SysUser;
import com.sakura.forum.system.service.ISysUserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Resource
    private ISysUserService sysUserService;

    @GetMapping("/demo")
    public SysUser demo() {
        return sysUserService.demo("admin");
    }
}
