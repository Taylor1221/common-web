package com.taylor.common.web.exception;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 冻结异常
 *
 * @author loveCamille
 * @date 2025-04-08 16:00:12
 */
public class FrozenException extends RuntimeException {

    public FrozenException(String message, Date expiredAfter) {
        super(message + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(expiredAfter));
    }

}
