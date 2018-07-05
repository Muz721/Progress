package com.muz.progress.ui.gank.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.muz.progress.R;
import com.muz.progress.ui.gank.adapter.GankClassifyAdapter;
import com.muz.progress.app.MyApplication;
import com.muz.progress.base.BaseFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * @author Muz
 * @description 干货分类
 * @date 2018/7/2 9:54
 */

public class GankClassifyFragment extends BaseFragment {
    public static String[] tabTitle = new String[]{"Android", "iOS", "前端", "瞎推荐", "拓展资源", "App"};
//    @Inject
    GankClassifySkillFragment androidFragment;
//    @Inject
    GankClassifySkillFragment iOSFragment;
//    @Inject
    GankClassifySkillFragment webFragment;
//    @Inject
    GankClassifySkillFragment recommendFragment;
//    @Inject
    GankClassifySkillFragment extendFragment;
//    @Inject
    GankClassifySkillFragment appFragment;
    List<Fragment> fragments = new ArrayList<>();
    @BindView(R.id.gank_classify_tab)
    TabLayout gankClassifyTab;
    @BindView(R.id.gank_classify_view_pager)
    ViewPager gankClassifyViewPager;
    GankClassifyAdapter adapter;
    @Override
    protected int getLayout() {
        return R.layout.fragment_gank_classify;
    }

    @Override
    protected void initViewAndEvent() {
        MyApplication.getInstance().getFragmentComponent().inject(this);
//        Bundle androidBundle = new Bundle();
//        androidBundle.putString("gank_classify",tabTitle[0]);
//        androidFragment.setArguments(androidBundle);
//        Bundle iosBundle = new Bundle();
//        iosBundle.putString("gank_classify",tabTitle[1]);
//        iOSFragment.setArguments(androidBundle);
//        Bundle webBundle = new Bundle();
//        webBundle.putString("gank_classify",tabTitle[2]);
//        webFragment.setArguments(androidBundle);
//        Bundle recommendBundle = new Bundle();
//        recommendBundle.putString("gank_classify",tabTitle[3]);
//        recommendFragment.setArguments(androidBundle);
//        Bundle extendBundle = new Bundle();
//        extendBundle.putString("gank_classify",tabTitle[4]);
//        extendFragment.setArguments(androidBundle);
        androidFragment=bundleFragment(0, androidFragment);
        iOSFragment=bundleFragment(1, iOSFragment);
        webFragment=bundleFragment(2, webFragment);
        recommendFragment=bundleFragment(3, recommendFragment);
        extendFragment=bundleFragment(4, extendFragment);
        appFragment=bundleFragment(5, appFragment);
        fragments.add(androidFragment);
        fragments.add(iOSFragment);
        fragments.add(webFragment);
        fragments.add(recommendFragment);
        fragments.add(extendFragment);
        fragments.add(appFragment);
        adapter=new GankClassifyAdapter(getFragmentManager(),fragments, Arrays.asList(tabTitle));
        gankClassifyViewPager.setAdapter(adapter);
        gankClassifyTab.setupWithViewPager(gankClassifyViewPager);
//        gankClassifyTab.addTab(gankClassifyTab.newTab().setTag(tabTitle[0]));
    }

    private GankClassifySkillFragment bundleFragment(int position, GankClassifySkillFragment fragment) {
        fragment=new GankClassifySkillFragment();
        Bundle bundle = new Bundle();
        bundle.putString("gank_classify", tabTitle[position]);
        fragment.setArguments(bundle);
        return fragment;
    }
}
