package com.sakura.forum.enums;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {
    SUCCESS(200, "操作成功"),
    FAIL(500, "操作失败"),
    // 用户不存在
    USER_IS_NOT_EXIST(404, "用户不存在"),
    // 登入失败
    LOGIN_FAIL(401, "登入失败"),
    WARN(501, "警告"),
    // 参数异常
    DATA_ERROR(403, "参数异常");

    /**
     * 自定义状态码
     **/
    private final int code;
    /**
     * 自定义描述
     **/
    private final String message;

    ResultCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
