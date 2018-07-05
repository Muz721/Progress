package com.muz.progress.base.contract.gank;

import com.muz.progress.base.BasePresenter;
import com.muz.progress.base.BaseView;
import com.muz.progress.model.entity.GankItemBean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/4.
 */

public interface GankWelfareContract {
    interface View extends BaseView {
void welfareData(List<GankItemBean> data);
    }
    interface Presenter extends BasePresenter<View> {
        /**
         * 获取福利数据
         * @param pager  页数
         */
        void welfareData(int pager);
    }
}
