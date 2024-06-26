package com.sakura.forum.web.controller.common;

import cn.dev33.satoken.util.SaResult;
import com.sakura.forum.core.domain.dto.CaptchaDto;
import com.sakura.forum.framework.factory.captcha.CaptchaFactory;
import com.sakura.forum.framework.web.service.captcha.CaptchaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证码接口文档
 */
@Tag(name = "验证码接口")
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    private static final Logger log = LoggerFactory.getLogger(CaptchaController.class);

    private final CaptchaFactory captchaFactory;

    public CaptchaController(CaptchaFactory captchaFactory) {
        this.captchaFactory = captchaFactory;
    }

    @Operation(summary = "获取验证码")
    @GetMapping
    public SaResult generateCaptcha(@ParameterObject CaptchaDto params) {
        // 1，使用工程创建对应的验证码类型结合配置文件
        CaptchaService captchaService = captchaFactory.createCaptchaGenerator(params.getType());
        // 2，调用服务
        String base64captcha = captchaService.generateCaptcha(params);
        return SaResult.ok().setData(base64captcha);
    }
}
