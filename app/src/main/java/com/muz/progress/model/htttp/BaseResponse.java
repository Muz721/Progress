package com.muz.progress.model.htttp;

/**
 * @description  数据请求基类
 * @author  Muz
 * @date  2018/6/12 18:43
 */

 class BaseResponse<T> {
    private int start;
    String message="";
    T subjects;
    boolean isSuccess(){
        return start == 0;
    }
}
