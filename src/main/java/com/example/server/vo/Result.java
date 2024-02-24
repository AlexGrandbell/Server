package com.example.server.vo;

//对应标准的三段式返回结构
//使用范型T作为data的数据类型，该数据类型由另外类文件即可
public class Result<T> {
    public int code;
    public String msg;
    public T data;

    //构造
    private Result(int code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    //无需data的成功返回数据生成
    public static <T> Result success(){
        return new Result(0,"成功",null);
    }

    //有data的成功返回数据生成
    public static <T> Result success(T data){
        return new Result<T>(0,"成功",data);
    }

    //失败返回数据生成
    public static <T> Result failed(int code,String msg){
        return new Result(code,msg,null);
    }
}
