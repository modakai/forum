package com.sakura.forum.enums;

import lombok.Getter;

/**
 * 验证码的类型
 */
@Getter
public enum CaptchaTypeEnum {

    SMS("sms"), NORMAL("normal");

    private final String type;

    CaptchaTypeEnum(String type) {
        this.type = type;
    }

    private static final CaptchaTypeEnum[] values = values();

    // 验证传入的type是否存在对应的类型
    public static boolean isExist(String type) {
        for (CaptchaTypeEnum captchaTypeEnum : values) {
            if (captchaTypeEnum.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return type;
    }
}
