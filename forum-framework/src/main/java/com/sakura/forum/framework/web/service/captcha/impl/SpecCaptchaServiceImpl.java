package com.sakura.forum.framework.web.service.captcha.impl;

import com.sakura.forum.constant.RedisCacheConstant;
import com.sakura.forum.core.domain.dto.CaptchaDto;
import com.sakura.forum.exception.ServiceException;
import com.sakura.forum.framework.web.service.captcha.CaptchaService;
import com.sakura.forum.framework.web.service.captcha.config.CaptchaProperties;
import com.sakura.forum.utils.RedisUtil;
import io.springboot.captcha.SpecCaptcha;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 具体策略类
 */
@Service("spec")
public class SpecCaptchaServiceImpl implements CaptchaService {

    private final CaptchaProperties captchaProperties;

    private final RedisUtil redisUtil;

    private final String KEY_PREFIX;

    public SpecCaptchaServiceImpl(CaptchaProperties captchaProperties, RedisUtil redisUtil) {
        this.captchaProperties = captchaProperties;
        this.redisUtil = redisUtil;
        // key的前缀
        KEY_PREFIX = RedisCacheConstant.CAPTCHA_KEY + captchaProperties.getType() + ":";
    }


    @Override
    public String generateCaptcha(CaptchaDto params) {
        // 生成 spec 的 验证码
        SpecCaptcha specCaptcha = new SpecCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight(), captchaProperties.getLen());
        specCaptcha.setCharType(captchaProperties.getFont());

        //  保存到redis
        redisUtil.setCacheObject(
                KEY_PREFIX + params.getKey(),
                specCaptcha.text(),
                captchaProperties.getTime(),
                TimeUnit.SECONDS
        );

        return specCaptcha.toBase64();
    }

    @Override
    public void removeCaptcha(String username) {
        redisUtil.deleteObject(KEY_PREFIX + username);
    }

    @Override
    public void validateCaptcha(String key, String captcha) {
        if (StringUtils.isBlank(key)) {
            throw new ServiceException("验证码的key不能为空");
        }

        // 1 从redis中获取数据
        String redisCaptcha = redisUtil.getCacheObject(KEY_PREFIX + key);
        if (StringUtils.isBlank(redisCaptcha)) {
            throw new ServiceException("验证码已过期，请重新获取");
        }

        // 2 验证码校验
        if (!StringUtils.equalsIgnoreCase(redisCaptcha, captcha)) {
            throw new ServiceException("验证码错误");
        }
    }
}
