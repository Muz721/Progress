package com.muz.progress.di.component;

import com.muz.progress.di.module.FragmentModule;
import com.muz.progress.di.module.PageModule;
import com.muz.progress.di.scope.FragmentScope;
import com.muz.progress.ui.gank.fragment.GankClassifyFragment;
import com.muz.progress.ui.gank.fragment.GankClassifySkillFragment;
import com.muz.progress.ui.gank.fragment.GankFragment;
import com.muz.progress.ui.gank.fragment.GankNewestFragment;
import com.muz.progress.ui.gank.fragment.GankWelfareFragment;

import dagger.Component;

/**
 * Created by Administrator on 2018/6/15.
 */

@FragmentScope
@Component(dependencies = ApiComponent.class, modules = { FragmentModule.class , PageModule.class})
public interface FragmentComponent {
    void inject(GankFragment gankFragment);

    void inject(GankNewestFragment gankNewestFragment);
    void inject(GankClassifyFragment gankClassifyFragment);
    void inject(GankClassifySkillFragment gankClassifySkillFragment);
    void inject(GankWelfareFragment gankWelfareFragment);
}
