package com.muz.progress.base.contract.gank;

import com.muz.progress.base.BasePresenter;
import com.muz.progress.base.BaseView;
import com.muz.progress.model.entity.GankItemBean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/2.
 */

public interface GankClassifySkillContract {
    interface View extends BaseView {
        /**
         * 获取分类数据
         * @param skillData         分类数据
         */
        void skillData(List<GankItemBean> skillData);
    }
    interface Presenter extends BasePresenter<View> {
        /**
         * 获取分类数据
         * @param classify        分类
         * @param number          数量
         * @param pager           页数
         */
        void skillData(String classify,int number,int pager);
    }
}
