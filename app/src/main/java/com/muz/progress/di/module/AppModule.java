package com.muz.progress.di.module;

import android.content.Context;

import com.muz.progress.model.GankLoader;
import com.muz.progress.model.htttp.api.GankApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/6/14.
 */
@Module
public class AppModule {

    private Context context;

    public AppModule(Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return context;
    }

    @Provides
    @Singleton
    GankLoader provideGankLoader(GankApi gankApi){
        return new GankLoader(gankApi);
    }
}
