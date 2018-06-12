package com.muz.progress.presenter;


import com.muz.progress.activity.IMainView;

/**
 * @description  主界面主导器
 * @author  Muz
 * @date  2018/6/12 16:53
 */

public class MainPresenter implements IMainPresenter {
    private IMainView view;
    public MainPresenter(IMainView view){
        this.view=view;
    }
}
