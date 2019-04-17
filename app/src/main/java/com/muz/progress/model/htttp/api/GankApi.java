package com.muz.progress.model.htttp.api;

import com.muz.progress.model.entity.GankItemBean;
import com.muz.progress.model.entity.GankRandomBean;
import com.muz.progress.model.htttp.response.GankBaseResponse;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 干货集中营api接口
 */

public interface GankApi {
     String BASE_URL ="http://gank.io/api/";

    /**
     * 获取随机数据
     * @param classify    分类
     * @param number      数量
     * @return            数据
     */
    @GET("random/data/{classify}/{number}")
    Observable<GankBaseResponse<List<GankRandomBean>>> randomData(@Path("classify") String classify, @Path("number")int number);

    /**
     * 获取所有干货日期
     * @return      所有日期
     */
    @GET("day/history")
    Observable<GankBaseResponse<Date[]>> gankDate();

    /**
     * 获取每日数据
     * @param date      日期
     * @return         返回数据
     */
    @GET("day/{date}")
    Observable<GankBaseResponse<LinkedHashMap<String, List<GankItemBean>>>> gankDayData(@Path("date")String date);

    /**
     * 获取最新数据
     * @return         返回数据
     */
    @GET("today")
    Observable<GankBaseResponse<LinkedHashMap<String, List<GankItemBean>>>> gankTodayData();

    /**
     * 获取分类数据
     * @param classify    分类
     * @param number      数量
     * @param pager       页数
     * @return            数据
     */
    @GET("data/{classify}/{number}/{pager}")
    Observable<GankBaseResponse<List<GankItemBean>>> gankClassifyData(@Path("classify")String classify, @Path("number")int number, @Path("pager")int pager);
}
