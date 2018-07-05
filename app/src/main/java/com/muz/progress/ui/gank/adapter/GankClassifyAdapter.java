package com.muz.progress.ui.gank.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2018/7/3.
 */

public class GankClassifyAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragments;
    private List<String> titles;
    public GankClassifyAdapter(FragmentManager fm,List<Fragment> fragments,List<String> titles) {
        super(fm);
        this.fragments=fragments;
        this.titles=titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return !isNullOrEmpty(titles) ? titles.get(position) : "";
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }//item的位置发生变化时进行刷新  PagerAdapter.POSITION_NONE刷新
    /**
     * 判断集合是否为null或者0个元素
     *
     * @param c
     * @return
     */
    public static boolean isNullOrEmpty(Collection c) {
        if (null == c || c.isEmpty()) {
            return true;
        }
        return false;
    }
}
