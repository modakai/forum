package com.sakura.forum.web.controller.common;

import com.sakura.forum.core.domain.dto.CaptchaDto;
import com.sakura.forum.factory.captcha.CaptchaFactory;
import com.sakura.forum.service.captcha.CaptchaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    public void generateCaptcha(@ParameterObject CaptchaDto params,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        // 1，使用工程创建对应的验证码类型结合配置文件
        CaptchaService captchaService = captchaFactory.createCaptchaGenerator(params.getType());
        // 2，调用服务
        captchaService.generateCaptcha(params, request, response);
    }
}
