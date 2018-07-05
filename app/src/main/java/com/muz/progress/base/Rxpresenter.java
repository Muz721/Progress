package com.muz.progress.base;

import com.muz.progress.base.BasePresenter;
import com.muz.progress.base.BaseView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @description  基于Rx的Presenter封装,控制订阅的生命周期
 * @author  Muz
 * @date  2018/6/19 11:04
 */

public class Rxpresenter<T extends BaseView> implements BasePresenter<T> {
    protected T view;
    protected CompositeDisposable compositeDisposable;
    protected void unSubscribe(){
        if (compositeDisposable!=null){
            compositeDisposable.clear();
        }
    }
    protected void addSubscribe(Disposable subscription) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(subscription);
    }

    @Override
    public void attachView(T view) {
        this.view=view;
    }

    @Override
    public void detachView() {
        this.view=null;
        unSubscribe();
    }
}
