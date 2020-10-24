package com.llt.health.controller;


import com.llt.health.entity.Result;
import com.llt.health.exception.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一处理异常
 * 这个类必须进入spring容器中 ,腰子啊扫包的目录下
 */
@RestControllerAdvice
public class MyExceptionHandler {
    /**
     * 记录执行的过程
     * 记录执行过程中重要的key
     * 记录异常信息
     */
    private static final Logger log=LoggerFactory.getLogger(MyExceptionHandler.class);
    /**
     * 捕获异常
     */

    @ExceptionHandler({MyException.class})
    public Result handleMyException(MyException e){
        return new Result(false,e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){

        log.error("发生未知错误",e);
        return  new Result(false,"发生未知错误 ,请联系管理员");
    }

}
