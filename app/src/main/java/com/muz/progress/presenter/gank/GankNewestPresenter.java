package com.muz.progress.presenter.gank;

import com.muz.progress.base.contract.gank.GankNewestContract;
import com.muz.progress.model.GankLoader;
import com.muz.progress.model.CommonSubscriber;
import com.muz.progress.model.entity.GankItemBean;
import com.muz.progress.model.entity.GankRandomBean;
import com.muz.progress.base.Rxpresenter;
import com.muz.progress.util.TimesUtils;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2018/6/19.
 */

public class GankNewestPresenter extends Rxpresenter<GankNewestContract.View> implements GankNewestContract.Presenter{
    private GankLoader gankLoader;
    @Inject
    public GankNewestPresenter(GankLoader gankLoader){
        this.gankLoader=gankLoader;
    }

    @Override
    public void attachView(final GankNewestContract.View view) {
        super.attachView(view);
        getDate();
    }

    /**
     * 获取最新数据
     * @param date   日期
     */
    @Override
    public void getGankNewestData(String date) {
        addSubscribe(gankLoader.gankDayData(date).subscribeWith(
                new CommonSubscriber<LinkedHashMap<String, List<GankItemBean>>>(view) {
            @Override
            public void onNext(LinkedHashMap<String, List<GankItemBean>> hashMap) {
view.getGankNewestData(hashMap);
            }
        }));
    }

    /**
     * 获取随机数据
     * @param classify    分类
     * @param number      数量
     */
    @Override
    public void getGankRandom(String classify, int number) {
        addSubscribe(gankLoader.randomData("Android",10).subscribe(new Consumer<List<GankRandomBean>>() {
            @Override
            public void accept(List<GankRandomBean> movies) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                view.showErrorMsg("sss");
            }
        }));
    }

    /**
     * 获取所有干货日期
     */
    @Override
    public void getDate() {
        addSubscribe(gankLoader.gankDate()
                .subscribeWith(new CommonSubscriber<Date[]>(view) {
                    @Override
                    public void onNext(Date[] dates) {
                        getGankNewestData(TimesUtils.timeFormatAlter(dates[0],TimesUtils.TIME_SLASH_YMD_FORMAT));
                    }
                }));
    }
}
