package com.muz.progress.base.contract.gank;

import com.muz.progress.base.BasePresenter;
import com.muz.progress.base.BaseView;
import com.muz.progress.model.entity.GankItemBean;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/6/19.
 */

public interface GankNewestContract {
    interface View extends BaseView {
        /**
         * 获取最新干货
         * @param hashMap    干货
         */
        void getGankNewestData(LinkedHashMap<String, List<GankItemBean>> hashMap);
    }
    interface Presenter extends BasePresenter<View> {
        /**
         * 获取最新数据
         * @param date   日期
         */
        void getGankNewestData(String date);
        /**
         * 获取随机数据
         * @param classify    分类
         * @param number      数量
         */
        void getGankRandom(String classify, int number);
        /**
         * 获取所有干活日期
         */
        void getDate();
    }
}
