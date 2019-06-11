package com.zhou.enums;

/**
 * @author zhou
 * @date 2019-04-09
 */
public enum StatusEnums {

    SUCCESS(200, "操作成功"),
    ACCOUNT_UNKNOWN(500, "账户不存在"),
    ACCOUNT_EXIST(500, "用户名已占用"),
    ACCOUNT_LOGIN(500, "用户未登录"),
    ACCOUNT_ERROR(500,"用户名或密码错误"),
    SYSTEM_ERROR(500, "系统错误"),
    PARAM_ERROR(400, "参数错误"),
    PARAM_REPEAT(400, "参数已存在"),
    OTHER(-100, "其他错误");



    private int code;
    private String info;

    StatusEnums(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }}
