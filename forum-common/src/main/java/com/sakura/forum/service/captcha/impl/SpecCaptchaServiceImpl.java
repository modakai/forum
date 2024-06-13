package com.sakura.forum.service.captcha.impl;

import com.sakura.forum.constant.RedisCacheConstant;
import com.sakura.forum.core.domain.dto.CaptchaDto;
import com.sakura.forum.exception.ServiceException;
import com.sakura.forum.service.captcha.CaptchaService;
import com.sakura.forum.service.captcha.config.CaptchaProperties;
import com.sakura.forum.utils.RedisUtil;
import io.springboot.captcha.SpecCaptcha;
import io.springboot.captcha.utils.CaptchaJakartaUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 具体策略类
 */
@Service("spec")
@AllArgsConstructor(onConstructor_ = @__({@Autowired}))
public class SpecCaptchaServiceImpl implements CaptchaService {

    private final CaptchaProperties captchaProperties;

    private final RedisUtil redisUtil;


    @Override
    public void generateCaptcha(CaptchaDto params, HttpServletRequest request, HttpServletResponse response) {
        // 生成 spec 的 验证码
        SpecCaptcha specCaptcha = new SpecCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight(), captchaProperties.getLen());
        specCaptcha.setCharType(captchaProperties.getFont());

        //  保存到redis
        redisUtil.setCacheObject(
                RedisCacheConstant.CAPTCHA_KEY + params.getKey(),
                specCaptcha.text(),
                captchaProperties.getTime(),
                TimeUnit.SECONDS
        );

        try {
            // 导出验证码
            CaptchaJakartaUtil.out(specCaptcha, request, response);
        } catch (IOException e) {
            throw new ServiceException("验证码生成失败");
        }
    }
}
