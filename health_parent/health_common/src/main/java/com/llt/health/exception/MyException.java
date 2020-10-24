package com.llt.health.exception;

/**
 * 自定义异常
 * 区分系统与业务异常
 * 给用户友好的提示信息
 * 终止一直不符合业务逻辑代码的执行
 */

public class MyException extends RuntimeException {
    public MyException (String message){
        super(message);
    }
}
