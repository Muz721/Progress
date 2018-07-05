package com.muz.progress.model.htttp.function;

/**
 * @description  统一处理错误
 * @author  Muz
 * @date  2018/6/12 18:39
 */

public class Fault extends RuntimeException {
    private boolean errorCode;

     Fault(boolean errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

    boolean getErrorCode() {
        return errorCode;
    }
}
