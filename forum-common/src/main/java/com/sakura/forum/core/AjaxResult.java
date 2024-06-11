package com.sakura.forum.core;

import com.sakura.forum.enums.ResultCodeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * 统一响应返回类
 */
@Getter
@ToString
@Schema(description = "统一响应类")
public class AjaxResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "状态码", description = "状态码")
    private final int code;
    @Schema(name = "响应信息", description = "响应信息")
    private final String msg;
    @Schema(name = "响应数据", description = "响应数据")
    private final T data;

    /**
     * 私有化构造器
     */
    private AjaxResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    /**
     * 响应成功
     */
    public static <T> AjaxResult<T> success() {
        return success(ResultCodeEnum.SUCCESS, null);
    }

    /**
     * 响应成功
     */
    public static <T> AjaxResult<T> success(T data) {
        return success(ResultCodeEnum.SUCCESS, data);
    }

    /**
     * 响应成功
     */
    public static <T> AjaxResult<T> success(String msg) {
        return success(ResultCodeEnum.SUCCESS.getCode(), msg, null);
    }

    /**
     * 响应成功
     */
    public static <T> AjaxResult<T> success(ResultCodeEnum resultCodeEnum) {
        return success(resultCodeEnum.getCode(), resultCodeEnum.getMessage(), null);
    }

    /**
     * 响应成功
     */
    public static <T> AjaxResult<T> success(ResultCodeEnum resultCodeEnum, T data) {
        return success(resultCodeEnum.getCode(), resultCodeEnum.getMessage(), data);
    }

    /**
     * 响应成功
     */
    public static <T> AjaxResult<T> success(int code, String msg, T data) {
        return new AjaxResult<>(code, msg, data);
    }

    /**
     * 响应失败
     */
    public static <T> AjaxResult<T> fail() {
        return fail(ResultCodeEnum.FAIL.getCode(), null, null);
    }

    /**
     * 响应失败
     */
    public static <T> AjaxResult<T> fail(String msg) {
        return fail(ResultCodeEnum.FAIL.getCode(), msg, null);
    }

    /**
     * 响应失败
     */
    public static <T> AjaxResult<T> fail(T data) {
        return fail(ResultCodeEnum.FAIL, data);
    }

    /**
     * 响应失败
     */
    public static <T> AjaxResult<T> fail(ResultCodeEnum resultCodeEnum) {
        return fail(resultCodeEnum.getCode(), resultCodeEnum.getMessage(), null);
    }

    /**
     * 响应失败
     */
    public static <T> AjaxResult<T> fail(ResultCodeEnum resultCodeEnum, T data) {
        return fail(resultCodeEnum.getCode(), resultCodeEnum.getMessage(), data);
    }

    /**
     * 响应失败
     */
    public static <T> AjaxResult<T> fail(int code, String msg, T data) {
        return new AjaxResult<>(code, msg, data);
    }

}
