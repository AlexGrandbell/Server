package com.example.server.enums;

//返回的code所代表的类型
public enum ResultEnum {
    SUCCESS(0,"操作成功"),
    ERROR_UNKONW(-1,"未知错误"),
    ERROR_NOT_FOUND(1,"资源未找到"),
    ERROR_OPERATION(2,"操作不成功");

    private int code;
    private String msg;
    ResultEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode(){
        return this.code;
    }

    public String getMsg(){
        return this.msg;
    }
}
