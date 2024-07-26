package com.sakura.forum.framework.web.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.util.SaResult;
import com.sakura.forum.exception.BaseException;
import com.sakura.forum.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;

import static com.sakura.forum.enums.ResultCodeEnum.LOGIN_FAIL;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public SaResult handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                        HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
        return SaResult.error(e.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    public SaResult handlerServiceException(ServiceException e) {
        log.error("业务异常：{}", e.getMessage(), e);
        return SaResult.error(e.getMsg()).setCode(e.getCode());
    }

    @ExceptionHandler(NotLoginException.class)
    public SaResult handlerNotLoginException(NotLoginException e) {
        log.error("未登录异常：{}", e.getMessage(), e);
        return SaResult.error()
                .setCode(LOGIN_FAIL.getCode())
                .setMsg(LOGIN_FAIL.getMessage());
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(BaseException.class)
    public SaResult handleBaseException(BaseException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}', 发送异常.", requestURI, e);
        return SaResult.error(e.getMessage()).setCode(e.getCode());
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public SaResult handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestURI, e);
        return SaResult.error(e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public SaResult handleException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestURI, e);
        return SaResult.error(e.getMessage());
    }

    /**
     * 请求参数类型不匹配
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public SaResult handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String errorMessage = String.format(
                "参数类型不匹配: 参数 '%s' 需要是 '%s' 类型，但提供了 '%s'",
                e.getName(),
                Objects.requireNonNull(e.getRequiredType()).getSimpleName(),
                e.getValue() == null ? "null" : e.getValue().getClass().getSimpleName()
        );

        log.error("请求参数类型不匹配'{}',发生系统异常.", requestURI, e);
        return SaResult.error(errorMessage);
    }

}
