package com.sakura.forum.exception;

import com.sakura.forum.enums.ResultCodeEnum;
import lombok.Getter;

/**
 * 业务异常
 */
@Getter
public final class ServiceException extends BaseException {

    public ServiceException(String msg) {
        super(500, msg);
    }

    public ServiceException(Integer code, String msg) {
        super(code, msg);
    }

    public ServiceException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum);
    }
}
