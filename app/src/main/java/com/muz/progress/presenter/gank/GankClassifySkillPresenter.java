package com.muz.progress.presenter.gank;

import com.muz.progress.base.contract.gank.GankClassifySkillContract;
import com.muz.progress.model.GankLoader;
import com.muz.progress.model.CommonSubscriber;
import com.muz.progress.model.entity.GankItemBean;
import com.muz.progress.base.Rxpresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/7/2.
 */

public class GankClassifySkillPresenter extends Rxpresenter<GankClassifySkillContract.View> implements GankClassifySkillContract.Presenter{
    private GankLoader gankLoader;
    @Inject
    public GankClassifySkillPresenter(GankLoader gankLoader){
        this.gankLoader=gankLoader;
    }
    @Override
    public void skillData(String classify,int number,int pager) {
addSubscribe(gankLoader.gankClassifyData(classify,number,pager).subscribeWith(
        new CommonSubscriber<List<GankItemBean>>(view){

            @Override
            public void onNext(List<GankItemBean> data) {
                view.skillData(data);
            }
        }));
    }
}
