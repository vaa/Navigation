package com.zhou.exception;

/**
 * @author tycoding
 * @date 2019-03-09
 */
public class GlobalException extends RuntimeException {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public GlobalException(String message) {
        this.msg = message;
    }
}
