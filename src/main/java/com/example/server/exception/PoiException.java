package com.example.server.exception;

import com.example.server.enums.ResultEnum;
import com.example.server.mapper.PoiMapper;
import com.example.server.pojo.Poi;

public class PoiException extends RuntimeException{
    private PoiException(String msg){
        super(msg);
    }

    public static PoiException notFound(){
        return new PoiException(ResultEnum.ERROR_NOT_FOUND.getMsg());
    }

    public static PoiException operateFail(){
        return new PoiException(ResultEnum.ERROR_OPERATION.getMsg());
    }

    public static PoiException Unkown(){
        return new PoiException(ResultEnum.ERROR_UNKONW.getMsg());
    }
}
