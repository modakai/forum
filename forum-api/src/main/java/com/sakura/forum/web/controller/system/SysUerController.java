package com.sakura.forum.web.controller.system;

import cn.dev33.satoken.util.SaResult;
import com.sakura.forum.core.BaseController;
import com.sakura.forum.core.domain.dto.ChangePasswordDto;
import com.sakura.forum.core.domain.dto.ChangeProfileDto;
import com.sakura.forum.core.domain.dto.PageDto;
import com.sakura.forum.core.domain.dto.SysUserPageDto;
import com.sakura.forum.core.domain.entity.SysUser;
import com.sakura.forum.system.service.ISysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "系统用户接口")
@RestController
@RequestMapping("/system/user")
public class SysUerController extends BaseController {

    private final ISysUserService userService;

    public SysUerController(ISysUserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "查询用户列表")
    @GetMapping({"list"})
    public SaResult searchUserTable(@ParameterObject SysUserPageDto param, @ParameterObject PageDto pageDto) {
        return success(userService.searchUserPage(param, pageDto));
    }

    @Operation(summary = "查询用户详情", responses = {
            @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SysUser.class)))
    })
    @GetMapping("{id}")
    public SaResult searchUser(@PathVariable Long id) {
        SysUser sysUser = userService.getById(id);
        return success(sysUser);
    }

    @Operation(summary = "更新用户信息")
    @PutMapping
    public SaResult changeUser(SysUser user) {
        userService.updateById(user);
        return success();
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("{id}")
    public SaResult delete(@PathVariable Long id) {
        userService.removeById(id);
        return success();
    }

    @Operation(summary = "批量删除用户")
    @DeleteMapping("{ids}")
    public SaResult deleteBatch(@PathVariable List<Long> ids) {
        userService.removeByIds(ids);
        return success();
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
        // 返回响应结果
        return success();
    }

}
