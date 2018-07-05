package com.muz.progress.di.component;

import com.muz.progress.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2018/6/14.
 */
@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {

}
