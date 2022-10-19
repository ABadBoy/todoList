package com.example.demo.dto;

public enum ResultEnum
{
    /**
     * 成功. ErrorCode : 0
     */
    SUCCESS("0","成功"),
    /**
     * 未知异常. ErrorCode : 1
     */
    FAIL("1","失败");


    private String code;

    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
