package com.muz.progress.model.htttp;

/**
 * @description  统一处理错误
 * @author  Muz
 * @date  2018/6/12 18:39
 */

class Fault extends RuntimeException {
    private int errorCode;

     Fault(int errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

    int getErrorCode() {
        return errorCode;
    }
}
