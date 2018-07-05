package com.muz.progress.model;

import com.muz.progress.model.entity.GankRandomBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2018/6/19.
 */

public interface IGankLoader {
    Observable<List<GankRandomBean>> randomData(String classify, int number);
}
