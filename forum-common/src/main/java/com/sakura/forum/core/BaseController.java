package com.sakura.forum.core;

import cn.dev33.satoken.util.SaResult;
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

    public SaResult success() {
        return SaResult.ok();
    }

    protected SaResult success(String msg) {
        return SaResult.ok(msg);
    }

    protected SaResult success(Object data) {
        return SaResult.data(data);
    }

    protected SaResult success(int code, String msg, Object data) {
        return SaResult.ok(msg).setData(data).setCode(code);
    }

    protected SaResult fail() {
        return SaResult.error();
    }

    protected SaResult fail(String msg) {
        return SaResult.error(msg);
    }

    protected SaResult fail(int code, String msg) {
        return fail(code, msg, null);
    }
    
    protected SaResult fail(int code, String msg, Object data) {
        return SaResult.error().setCode(code).setData(data).setMsg(msg);
    }
}
