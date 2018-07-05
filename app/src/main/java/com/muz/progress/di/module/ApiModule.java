package com.muz.progress.di.module;

import com.muz.progress.di.scope.ApiScope;
import com.muz.progress.model.htttp.api.GankApi;
import com.muz.progress.model.htttp.HttpCommonInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/6/14.
 */
@Module
public class ApiModule {
    public static final String GANKAPI = "GankApi";
    @ApiScope
    @Provides
    GankApi providGankService(@Named(GANKAPI)Retrofit retrofit){
        return retrofit.create(GankApi.class);
    }
    @ApiScope
    @Provides
    @Named(GANKAPI)
    Retrofit provideGankRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, GankApi.BASE_URL);
    }

    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient okHttpClient, String url) {
return builder.baseUrl(url)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    }
    @ApiScope
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }
    @ApiScope
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }
    @ApiScope
    @Provides
    OkHttpClient provideClient(OkHttpClient.Builder builder) {
        // 添加公共参数拦截器
        HttpCommonInterceptor commonInterceptor = new HttpCommonInterceptor.Builder()
                .addHeaderParams("paltform","android")
                .addHeaderParams("userToken","1234343434dfdfd3434")
                .addHeaderParams("userId","123445")
                .build();
        //设置拦截器
        builder.addInterceptor(commonInterceptor);
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }
}
