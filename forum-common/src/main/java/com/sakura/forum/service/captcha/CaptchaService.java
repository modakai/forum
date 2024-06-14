package com.sakura.forum.service.captcha;

import com.sakura.forum.core.domain.dto.CaptchaDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 验证码服务 抽象策略
 */
public interface CaptchaService {

    /**
     * 生成验证码
     *
     * @param params   参数
     * @param request  请求
     * @param response 响应
     */
    void generateCaptcha(CaptchaDto params, HttpServletRequest request, HttpServletResponse response);

    /**
     * 验证验证码
     *
     * @param username redis-key的后缀
     * @param captcha  验证码
     */
    void validateCaptcha(String username, String captcha);

    /**
     * 删除验证码
     *
     * @param username redis-key的后缀
     */
    void removeCaptcha(String username);
}
