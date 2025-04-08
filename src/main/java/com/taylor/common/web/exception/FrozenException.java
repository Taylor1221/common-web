package com.taylor.common.web.exception;


/**
 * 冻结异常
 *
 * @author loveCamille
 * @date 2025-04-08 16:00:12
 */
public class FrozenException extends RuntimeException {

    public FrozenException(String message) {
        super(message);
    }

}
