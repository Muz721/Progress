package com.muz.progress.app;

import android.app.Activity;
import android.app.Application;

import com.muz.progress.di.component.ActivityComponent;
import com.muz.progress.di.component.ApiComponent;
import com.muz.progress.di.component.AppComponent;
//import com.muz.progress.di.component.DaggerApiComponent;
//import com.muz.progress.di.component.DaggerAppComponent;
import com.muz.progress.di.component.DaggerActivityComponent;
import com.muz.progress.di.component.DaggerApiComponent;
import com.muz.progress.di.component.DaggerAppComponent;
import com.muz.progress.di.component.DaggerFragmentComponent;
import com.muz.progress.di.component.FragmentComponent;
import com.muz.progress.di.module.ApiModule;
import com.tencent.smtt.sdk.QbSdk;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Administrator on 2018/6/13.
 */

public class MyApplication extends Application{
    /**
     * Activity集合，用于彻底退出应用
     */
    private List<Activity> activities;
    /**
     * Application实例
     */
    private static MyApplication application;
    /**
     * Application实例
     */
    public static MyApplication getInstance() {
        return application;
    }

    private static AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化实例
        application = this;
        // 创建Activity集合
        activities = new ArrayList<>();
        registerActivityLifecycleCallbacks(new ActivityLifecycleManager());

        if (appComponent == null){
            appComponent = DaggerAppComponent.create();
        }

        //初始化 x5内核
        new Thread(new Runnable() {
            @Override
            public void run() {
                QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
                    @Override
                    public void onCoreInitFinished() {

                    }
                    @Override
                    public void onViewInitFinished(boolean b) {

                    }
                };
                QbSdk.initX5Environment(getApplicationContext(),cb);

            }
        }).start();
    }
public AppComponent getAppComponent(){
        return appComponent;
}
private ApiComponent getApiComponent(){
    return DaggerApiComponent.builder()
            .appComponent(getAppComponent())
            .apiModule(new ApiModule())
            .build();
}
public ActivityComponent getActivityComponent(){
    return DaggerActivityComponent.builder()
            .apiComponent(getApiComponent())
            .build();
}
public FragmentComponent getFragmentComponent(){
    return DaggerFragmentComponent.builder()
            .apiComponent(getApiComponent())
            .build();
}
    /**
     * 向Activity集合中添加元素
     *
     * @param activity activity
     */
    public void addActivity(Activity activity) {
        activities.add(activity);
    }
    /**
     * 从Activity集合中移除元素
     *
     * @param activity activity
     */
    public void removeActivity(Activity activity) {
        if (activities != null) {
            activities.remove(activity);
        }
    }
}
