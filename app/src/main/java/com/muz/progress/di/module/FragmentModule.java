package com.muz.progress.di.module;



import android.app.Activity;
import android.support.v4.app.Fragment;

import com.muz.progress.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/6/15.
 */
@Module
public class FragmentModule {
private Fragment fragment;
public FragmentModule(Fragment fragment){
    this.fragment=fragment;
}
@ActivityScope
@Provides
public Activity provideActivity(){
    return fragment.getActivity();
}
}
