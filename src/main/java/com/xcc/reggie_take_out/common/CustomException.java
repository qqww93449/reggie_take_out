package com.xcc.reggie_take_out.common;

/**
 * @author Charles
 * @version 1.0
 * @className CustomException
 */

/**
 * 自定义业务异常类
 */
public class CustomException extends RuntimeException {
    public CustomException(String message){
        super(message);
    }
}
