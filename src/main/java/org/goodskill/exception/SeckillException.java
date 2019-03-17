package org.goodskill.exception;

/**
 * @author ：yangzengrui
 * @date ：Created in 17/3/2019 11:43 AM
 * @description：秒杀相关业务异常
 */

public class SeckillException extends RuntimeException{

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
