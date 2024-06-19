package com.sakura.forum.core.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 验证码请求参数
 */
@Schema(name = "CaptchaDto 验证码请求参数", description = "验证码请求参数")
@Getter
@Setter
public class CaptchaDto {

    @Schema(name = "type", description = "验证码类型")
    private String type;

    @Schema(name = "key", description = "验证码的key；type=normal时传前端生成的唯一key值，type=sms传递手机号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String key;
}
