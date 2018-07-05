package com.muz.progress.model.htttp.response;

/**
 * @description  数据请求基类
 * @author  Muz
 * @date  2018/6/12 18:43
 */

 public class GankBaseResponse<T> {
    public boolean error;
    public T results;
    public boolean isSuccess(){
        return error;
    }

}
