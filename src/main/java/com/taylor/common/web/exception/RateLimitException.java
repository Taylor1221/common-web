package com.taylor.common.web.exception;

/**
 * 限流业务异常
 *
 * @author loveCamille
 * @date 2025-04-19 00:14:50
 */
public class RateLimitException extends RuntimeException {

    public RateLimitException(String message) {
        super(message);
    }

}
