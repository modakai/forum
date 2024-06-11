package com.sakura.forum.framework.web.exception;

import com.sakura.forum.core.AjaxResult;
import com.sakura.forum.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;

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
    public AjaxResult<String> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                                  HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
        return AjaxResult.fail(e.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    public AjaxResult<String> handlerServiceException(ServiceException e) {
        log.error("业务异常：{}", e.getMessage(), e);
        Integer code = e.getCode();
        return code != null ? AjaxResult.fail(e.getCode(), e.getMsg(), null) : AjaxResult.fail(e.getMsg());
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public AjaxResult<String> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestURI, e);
        return AjaxResult.fail(e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public AjaxResult<String> handleException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestURI, e);
        return AjaxResult.fail(e.getMessage());
    }

    /**
     * 请求参数类型不匹配
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public AjaxResult<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String errorMessage = String.format(
                "参数类型不匹配: 参数 '%s' 需要是 '%s' 类型，但提供了 '%s'",
                e.getName(),
                Objects.requireNonNull(e.getRequiredType()).getSimpleName(),
                e.getValue() == null ? "null" : e.getValue().getClass().getSimpleName()
        );

        log.error("请求参数类型不匹配'{}',发生系统异常.", requestURI, e);
        return AjaxResult.fail(errorMessage);
    }

}
