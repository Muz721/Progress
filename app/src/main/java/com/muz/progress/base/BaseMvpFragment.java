package com.muz.progress.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.muz.progress.di.module.FragmentModule;

import javax.inject.Inject;

/**
 * @description  mvp fragment 基类
 * @author  Muz
 * @date  2018/6/19 10:53
 */

public abstract class BaseMvpFragment<T extends BasePresenter>extends BaseFragment implements BaseView {
    @Inject
    protected T presenter;
    protected FragmentModule getFragmentModule(){
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        presenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (presenter!=null){
            presenter.detachView();
        }
        super.onDestroyView();
    }

    /**
     * 请求失败
     * @param msg    失败信息
     */
    @Override
    public void showErrorMsg(String msg) {

    }

    @Override
    public void stateError() {

    }

    protected abstract void initInject();
}
