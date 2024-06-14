package com.sakura.forum.enums;

import lombok.Getter;

/**
 * 登入用户的类型 分为 ADMIN 、APP_USER
 */
@Getter
public enum UserLoginTypeEnum {

    ADMIN("ADMIN"), APP_USER("APP_USER");

    private final String value;

    UserLoginTypeEnum(String value) {
        this.value = value;
    }

}
