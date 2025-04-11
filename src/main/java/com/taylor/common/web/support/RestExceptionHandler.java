package com.taylor.common.web.support;

import com.taylor.common.web.exception.BizException;
import com.taylor.common.web.util.MDCUtil;
import com.taylor.common.web.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * 接口全局异常处理
 *
 * @author loveCamille
 * @date 2025-04-02 21:54:34
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BindException.class)
    public Result<Void> validException(BindException e) {
        return Result.fail(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<?> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        return Result.fail("不支持的请求方式 " + ex.getMethod());
    }

    @ExceptionHandler(BizException.class)
    public Result<Void> bizException(BizException e) {
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<Void> exception(Exception e) {
        String message = String.format("发生未知异常, 请求流水号: %s", MDCUtil.get());
        log.error("{}", message, e);
        return Result.fail(message);
    }

}
