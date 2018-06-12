package com.muz.progress.model;

/**
 * @description  主界面业务处理工厂
 * @author  Muz
 * @date  2018/6/12 17:10
 */

public class MainModelFactory {
    public static IMainModel newInstance(){
        return new MainModel();
    }
}
