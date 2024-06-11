package com.sakura.forum.core;

import com.sakura.forum.enums.ResultCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * web 通用的controller
 *
 * @author modakai
 */
public class BaseController {
    /**
     * 日志
     */
    protected static final Logger log = LoggerFactory.getLogger(BaseController.class);

    protected <T> AjaxResult<T> success() {
        return AjaxResult.success();
    }

    protected <T> AjaxResult<T> success(String msg) {
        return AjaxResult.success(msg);
    }

    protected <T> AjaxResult<T> success(T data) {
        return AjaxResult.success(data);
    }

    protected <T> AjaxResult<T> fail() {
        return AjaxResult.fail();
    }

    protected <T> AjaxResult<T> fail(String msg) {
        return AjaxResult.fail(msg);
    }

    protected <T> AjaxResult<T> fail(ResultCodeEnum resultCodeEnum) {
        return AjaxResult.fail(resultCodeEnum);
    }


}
