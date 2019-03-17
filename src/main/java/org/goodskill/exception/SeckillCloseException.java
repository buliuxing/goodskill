package org.goodskill.exception;

/**
 * @author ：yangzengrui
 * @date ：Created in 17/3/2019 11:42 AM
 * @description：秒杀关闭异常
 */

public class SeckillCloseException extends RuntimeException{

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
