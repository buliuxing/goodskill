package org.goodskill.exception;

/**
 * @author ：yangzengrui
 * @date ：Created in 17/3/2019 11:39 AM
 * @description：重复秒杀异常
 */

public class RepeatKillException extends RuntimeException{

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
