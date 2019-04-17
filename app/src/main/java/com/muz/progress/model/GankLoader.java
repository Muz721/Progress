package com.muz.progress.model;

import com.muz.progress.model.htttp.api.GankApi;
import com.muz.progress.model.entity.GankItemBean;
import com.muz.progress.model.entity.GankRandomBean;
import com.muz.progress.model.ObjectLoader;
import com.muz.progress.model.htttp.function.PayLoad;
import com.muz.progress.model.IGankLoader;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2018/6/19.
 */

public class GankLoader extends ObjectLoader implements IGankLoader {
    protected GankApi gankApi;
    @Inject
    public GankLoader(GankApi gankApi){
        this.gankApi=gankApi;
    }
    /**
     * 获取随机数据
     * @param classify    分类
     * @param number      数量
     * @return            数据
     */
    @Override
    public Observable<List<GankRandomBean>> randomData(String classify, int number) {
        return observe(gankApi.randomData(classify,number).map(new PayLoad<List<GankRandomBean>>()));
    }

    /**
     * 获取所有干货日期
     * @return      所有日期
     */
    public Observable<Date[]> gankDate(){
        return observe(gankApi.gankDate().map(new PayLoad<Date[]>()));
    }
    /**
     * 获取每日数据
     * @param date      日期
     * @return         返回数据
     */
    public Observable<LinkedHashMap<String, List<GankItemBean>>> gankDayData(String date){
        return observe(gankApi.gankDayData(date).map(new PayLoad<LinkedHashMap<String, List<GankItemBean>>>()));
    }
    /**
     * 获取最新数据
     * @return         返回数据
     */
    public Observable<LinkedHashMap<String, List<GankItemBean>>> gankTodayData(){
        return observe(gankApi.gankTodayData().map(new PayLoad<LinkedHashMap<String, List<GankItemBean>>>()));
    }
    public Observable<List<GankItemBean>> gankClassifyData(String classify, int number, int page){
        return observe(gankApi.gankClassifyData(classify, number, page).map(new PayLoad<List<GankItemBean>>()));
    }
}
