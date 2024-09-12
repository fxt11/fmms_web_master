package com.example.fmms.exception;

import com.example.fmms.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//放置所以异常错误处理
@Slf4j
@RestControllerAdvice

public class ExceptionHandle  {

    @ExceptionHandler(value = ServicesException.class)
    public Result serviceExceptionError(ServicesException e) {
        log.error("业务错误",e);
        return Result.error().message(e.getMessage());

    }

//    @ExceptionHandler(value = Exception.class)
//    public Result exceptionError(Exception e) {
//        log.error("数据库插入不进去",e);
//       return Result.error().message("系统错误");
//
//    }

}
