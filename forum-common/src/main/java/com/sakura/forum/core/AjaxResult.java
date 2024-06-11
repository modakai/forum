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
    @Schema(name = "详细信息", description = "用于给开发人员观看")
    private String detailMsg;
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
     * 私有化构造器
     */
    private AjaxResult(int code, String msg, String detailMsg, T data) {
        this.code = code;
        this.msg = msg;
        this.detailMsg = detailMsg;
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
        return fail(ResultCodeEnum.FAIL, null, null);
    }

    /**
     * 响应失败
     */
    public static <T> AjaxResult<T> fail(String detailMsg) {
        return fail(ResultCodeEnum.FAIL, detailMsg, null);
    }

    /**
     * 响应失败
     */
    public static <T> AjaxResult<T> fail(String detailMsg, T data) {
        return fail(ResultCodeEnum.FAIL, detailMsg, data);
    }

    /**
     * 响应失败
     */
    public static <T> AjaxResult<T> fail(ResultCodeEnum resultCodeEnum, String detailMsg, T data) {
        return fail(resultCodeEnum.getCode(), resultCodeEnum.getMessage(), detailMsg, data);
    }

    /**
     * 响应失败
     */
    public static <T> AjaxResult<T> fail(int code, String msg, String detailMsg, T data) {
        return new AjaxResult<>(code, msg, detailMsg, data);
    }

}
