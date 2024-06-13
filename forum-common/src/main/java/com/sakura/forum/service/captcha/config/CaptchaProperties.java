package com.sakura.forum.service.captcha.config;

import io.springboot.captcha.base.Captcha;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 验证码配置
 */
@Component
@ConfigurationProperties(prefix = "customer-captcha")
@Data
public class CaptchaProperties {

    private String type;
    private Integer width;
    private Integer height;
    private Integer len;
    private Integer font;
    private Integer time;

    public void setFont(Integer font) {
        if (font < Captcha.FONT_1 || font > Captcha.FONT_10) {
            font = Captcha.FONT_10;
        }
        this.font = font;
    }
}
