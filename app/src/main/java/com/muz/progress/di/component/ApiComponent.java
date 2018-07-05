package com.muz.progress.di.component;

import com.muz.progress.di.module.ApiModule;
import com.muz.progress.di.scope.ApiScope;
import com.muz.progress.model.GankLoader;
import com.muz.progress.model.htttp.api.GankApi;

import dagger.Component;

/**
 * Created by Administrator on 2018/6/14.
 */
@Component(dependencies = AppComponent.class,modules = ApiModule.class)
@ApiScope
public interface ApiComponent {
GankApi gankApi();
    GankLoader gankLoader();
}
