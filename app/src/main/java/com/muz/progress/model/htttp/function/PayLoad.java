package com.muz.progress.model.htttp.function;

import com.muz.progress.model.htttp.response.GankBaseResponse;

import io.reactivex.functions.Function;

/**
 * Created by Administrator on 2018/6/8.
 */

public class PayLoad<T> implements Function<GankBaseResponse<T>, T> {
    @Override
    public T apply(GankBaseResponse<T> listBaseResponse) throws Exception {
        if(listBaseResponse.isSuccess()){
            throw new Fault(listBaseResponse.error,"网络错误");
//            throw new Fault(listBaseResponse.start,listBaseResponse.message);
        }
        return listBaseResponse.results;
    }
}
