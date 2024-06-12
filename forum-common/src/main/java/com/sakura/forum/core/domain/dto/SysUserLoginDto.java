package com.sakura.forum.core.domain.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "系统用户登录参数")
public class SysUserLoginDto {

    @Schema(name = "username", description = "用户账号")
    private String username;
    @Schema(name = "password", description = "用户密码")
    private String password;
    @Schema(name = "captcha", description = "验证码")
    private String captcha;
    @Schema(name = "loginType", description = "登录类型 normal（账号密码登入）；phone（短信验证码登入）")
    private String loginType;

}
