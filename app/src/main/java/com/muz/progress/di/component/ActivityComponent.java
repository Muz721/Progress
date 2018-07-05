package com.muz.progress.di.component;

import com.muz.progress.ui.MainActivity;
import com.muz.progress.di.module.ActivityModule;
import com.muz.progress.di.module.PageModule;
import com.muz.progress.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by Administrator on 2018/6/14.
 */

@ActivityScope
@Component(dependencies = ApiComponent.class,modules = {ActivityModule.class , PageModule.class})
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
