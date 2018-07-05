package com.muz.progress.di.module;

import com.muz.progress.di.scope.ActivityScope;
import com.muz.progress.di.scope.FragmentScope;
import com.muz.progress.ui.gank.fragment.GankClassifyFragment;
import com.muz.progress.ui.gank.fragment.GankClassifySkillFragment;
import com.muz.progress.ui.gank.fragment.GankFragment;
import com.muz.progress.ui.gank.fragment.GankNewestFragment;
import com.muz.progress.ui.gank.fragment.GankWelfareFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/6/15.
 */
@Module
public class PageModule {
    @Provides
    @ActivityScope
    GankFragment provideGankFragment(){
        return new GankFragment();
    }

    @Provides
    @FragmentScope
    GankNewestFragment provideGankNewestFragment(){
        return new GankNewestFragment();
    }
    @Provides
    @FragmentScope
    GankClassifyFragment provideGankClassifyFragment(){
        return new GankClassifyFragment();
    }
    @Provides
    @FragmentScope
    GankClassifySkillFragment provideGankClassifySkillFragment(){return new GankClassifySkillFragment();}
    @Provides
    @FragmentScope
    GankWelfareFragment provideGankWelfareFragment(){
        return new GankWelfareFragment();
    }
}
