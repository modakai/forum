package com.sakura.forum.framework.factory.captcha;

import com.sakura.forum.enums.CaptchaTypeEnum;
import com.sakura.forum.exception.ServiceException;
import com.sakura.forum.framework.web.service.captcha.CaptchaService;
import com.sakura.forum.framework.web.service.captcha.config.CaptchaProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 抽象工厂
 */
@Component
public class CaptchaFactory {

    private final ApplicationContext context;

    private final CaptchaProperties captchaProperties;

    public CaptchaFactory(ApplicationContext context, CaptchaProperties captchaProperties) {
        this.context = context;
        this.captchaProperties = captchaProperties;
    }

    /**
     * 生成验证码服务
     *
     * @return 返回对应 captchaType的验证码服务
     */
    public CaptchaService createCaptchaGenerator(String captchaType) {
        if (!CaptchaTypeEnum.isExist(captchaType)) {
            throw new ServiceException("验证码类型错误");
        }

        if (StringUtils.isEmpty(captchaType) || CaptchaTypeEnum.NORMAL.getType().equals(captchaType))
            captchaType = captchaProperties.getType();

        return (CaptchaService) context.getBean(captchaType);
    }

}
