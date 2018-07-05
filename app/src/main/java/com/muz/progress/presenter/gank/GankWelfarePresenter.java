package com.muz.progress.presenter.gank;

import com.muz.progress.base.contract.gank.GankWelfareContract;
import com.muz.progress.model.GankLoader;
import com.muz.progress.model.CommonSubscriber;
import com.muz.progress.model.entity.GankItemBean;
import com.muz.progress.base.Rxpresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/7/4.
 */

public class GankWelfarePresenter extends Rxpresenter<GankWelfareContract.View> implements GankWelfareContract.Presenter {
    private GankLoader gankLoader;
    String classify = "福利";
    int number = 20;
    @Inject
    public GankWelfarePresenter(GankLoader gankLoader){
        this.gankLoader=gankLoader;
    }
    @Override
    public void attachView(GankWelfareContract.View view) {
        super.attachView(view);
        welfareData(1);
    }

    @Override
    public void welfareData(int pager) {
        addSubscribe(gankLoader.gankClassifyData(classify,number,pager).subscribeWith(new CommonSubscriber<List<GankItemBean>>(view) {
            @Override
            public void onNext(List<GankItemBean> data) {
                view.welfareData(data);
            }
        }));
    }
}
