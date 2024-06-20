package com.sakura.forum.framework.web.service.captcha;

import com.sakura.forum.core.domain.dto.CaptchaDto;

/**
 * 验证码服务 抽象策略
 */
public interface CaptchaService {

    /**
     * 生成验证码
     *
     * @param params 参数
     */
    String generateCaptcha(CaptchaDto params);

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
