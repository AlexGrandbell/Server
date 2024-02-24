package com.example.server.exception;

import com.example.server.enums.ResultEnum;
import com.example.server.vo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice//关联
public class PoiExceptionHandler {
    @ExceptionHandler(PoiException.class)//声明PoiException异常能被该函数自动捕获
    public Result poiExceptionHandler(Exception e){
        if (e.getMessage().endsWith(ResultEnum.ERROR_NOT_FOUND.getMsg())){
            return Result.failed(ResultEnum.ERROR_NOT_FOUND);
        }else if (e.getMessage().endsWith(ResultEnum.ERROR_OPERATION.getMsg())){
            return Result.failed(ResultEnum.ERROR_OPERATION);
        }
        return Result.failed();
    }

    //兜底异常
    @ExceptionHandler(Exception.class)
    public Result serverErrorHandler(Exception e){
        return Result.failed();
    }
}
