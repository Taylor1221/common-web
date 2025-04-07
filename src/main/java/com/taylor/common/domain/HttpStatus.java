package com.taylor.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

public interface HttpStatus {

    int getCode();

    String getMsg();


    @Getter
    @AllArgsConstructor
    enum BaseHttpStatus implements HttpStatus {

        SUCCESS(200, "请求成功"),

        ERROR(5000, "请求错误"),

        UNAUTHORIZED(401, "请先登录"),

        FORBIDDEN(403, "暂无权限访问"),

        ;

        private final int code;

        private final String msg;
    }

}
