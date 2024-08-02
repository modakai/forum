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
    DATA_ERROR(403, "参数异常"),
    // 旧密码错误
    OLD_PASSWORD_ERROR(101, "旧密码错误"),
    // 文件类型错误
    IMAGE_TYPE_ERROR(201, "图片文件类型错误；仅支持jpg、png、jpeg格式图片");

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
