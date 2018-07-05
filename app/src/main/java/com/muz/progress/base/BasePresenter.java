package com.muz.progress.base;

/**
 * @description  Presenter基类
 * @author  Muz
 * @date  2018/6/13 18:20
 */

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}
