package com.sakura.forum.core.domain.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "系统用户登录参数")
public class SysUserLoginDto {

    @Schema(name = "username", description = "用户账号")
    @NotBlank(message = "用户账号不能为空")
    private String username;

    @Schema(name = "password", description = "用户密码")
    private String password;

    @Schema(name = "captcha", description = "验证码")
    @NotBlank(message = "验证码不能为空")
    private String captcha;

    @Schema(name = "loginType", description = "登录类型 normal（账号密码登入）；sms（短信验证码登入）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "登录类型不能为空")
    private String loginType;

    @Schema(name = "key", description = "验证码的key")
    private String key;

}
