package com.sakura.forum.web.controller.system;

import cn.dev33.satoken.util.SaResult;
import com.sakura.forum.core.BaseController;
import com.sakura.forum.core.domain.dto.ChangePasswordDto;
import com.sakura.forum.core.domain.dto.ChangeProfileDto;
import com.sakura.forum.system.service.ISysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Tag(name = "系统用户接口")
@RestController
@RequestMapping("/system/user")
public class SysUerController extends BaseController {

    private final ISysUserService userService;

    public SysUerController(ISysUserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "修改密码", description = "修改密码")
    @PutMapping("change/password")
    public SaResult changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        userService.changePassword(changePasswordDto);
        return success();
    }

    @Operation(summary = "修改个人信息", description = "修改个人信息")
    @PutMapping("change/profile")
    public SaResult changeProfile(@RequestBody ChangeProfileDto form) {
        // 修改接口
        userService.changeProfile(form);
        return success();
    }

    @Operation(summary = "修改头像", description = "修改头像")
    @PutMapping("change/avatar")
    public SaResult changeAvatar(@RequestBody Map<String, Object> data) {
        String avatarUrl = (String) data.get("avatarUrl");
        // 修改数据库
        userService.changeAvatar(avatarUrl);
        // 修改缓存
        // 返回响应结果
        return success();
    }

}
