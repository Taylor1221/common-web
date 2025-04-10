package com.taylor.common.web.exception;

/**
 * 业务异常
 *
 * @author loveCamille
 * @date 2025-04-10 11:15:26
 */
public class BizException extends RuntimeException {

    public BizException(String message) {
        super(message);
    }
}
