package com.taylor.common.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 接口响应结果类
 *
 * @author loveCamille
 * @date 2025-04-02 21:23:25
 */
@Getter
@Setter
@Accessors(chain = true)
@SuppressWarnings("unused")
public class Result<T> {

    private Integer code;

    private String msg;

    private T data;

    private Result() {
    }

    public static <T> Result<T> reply(HttpStatus httpStatus, T data) {
        return new Result<T>().setCode(httpStatus.getCode()).setData(data).setMsg(httpStatus.getMsg());
    }

    public static <T> Result<T> reply(Integer code, String msg, T data) {
        return new Result<T>().setCode(code).setData(data).setMsg(msg);
    }

    public static <T> Result<T> reply(Integer code, String msg) {
        return reply(code, msg, null);
    }

    public static <T> Result<T> reply(HttpStatus httpStatus) {
        return reply(httpStatus, null);
    }

    public static <T> Result<T> success(T data) {
        return reply(HttpStatus.BaseHttpStatus.SUCCESS, data);
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> fail(String msg) {
        return reply(HttpStatus.BaseHttpStatus.ERROR.getCode(), msg);
    }

}
