package com.sakura.forum.framework.web.service.captcha.config;

import io.springboot.captcha.base.Captcha;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 验证码配置
 */
@Component
@ConfigurationProperties(prefix = "customer-captcha")
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getLen() {
        return len;
    }

    public void setLen(Integer len) {
        this.len = len;
    }

    public Integer getFont() {
        return font;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
