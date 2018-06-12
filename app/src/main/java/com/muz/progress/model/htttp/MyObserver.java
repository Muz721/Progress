package com.muz.progress.model.htttp;

import android.content.Context;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @description  统一处理返回数据
 * @author  Muz
 * @date  2018/6/12 18:03
 */

public class MyObserver<T> implements Observer<T> {
    private ObserverOnNextListener listener;
    private Context context;
    public MyObserver(Context context, ObserverOnNextListener listener) {
        this.listener = listener;
        this.context = context;
        //TODO 加载
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        this.listener.onNext(t);
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }
}
