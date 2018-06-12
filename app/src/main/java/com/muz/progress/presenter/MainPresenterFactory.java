package com.muz.progress.presenter;


import com.muz.progress.activity.IMainView;

/**
 * @description  主界面主导器工厂
 * @author  Muz
 * @date  2018/6/12 16:53
 */

public class MainPresenterFactory {

    public static IMainPresenter newInstance(IMainView view){
return new MainPresenter(view);
    }
}
