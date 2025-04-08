package com.taylor.common.web.support;

import com.taylor.common.web.exception.FrozenException;
import com.taylor.common.web.util.MDCUtil;
import com.taylor.common.web.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 接口全局异常处理
 *
 * @author loveCamille
 * @date 2025-04-02 21:54:34
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(FrozenException.class)
    public Result<Void> exception(FrozenException e) {
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<Void> exception(Exception e) {
        String message = String.format("发生未知异常, 请求流水号: %s", MDCUtil.get());
        log.error("{}", message, e);
        return Result.fail(message);
    }

}
