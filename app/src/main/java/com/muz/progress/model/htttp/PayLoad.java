package com.muz.progress.model.htttp;

import io.reactivex.functions.Function;

/**
 * Created by Administrator on 2018/6/8.
 */

public class PayLoad<T> implements Function<BaseResponse<T>, T> {
    @Override
    public T apply(BaseResponse<T> listBaseResponse) throws Exception {
        if(!listBaseResponse.isSuccess()){

            throw new Fault(listBaseResponse.start,listBaseResponse.message);
        }
        return listBaseResponse.subjects;
    }
}
