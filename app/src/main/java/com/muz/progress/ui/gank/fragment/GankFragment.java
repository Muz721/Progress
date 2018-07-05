package com.muz.progress.ui.gank.fragment;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.muz.progress.R;
import com.muz.progress.app.MyApplication;
import com.muz.progress.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2018/6/15.
 */

public class GankFragment extends BaseFragment implements BottomNavigationView.OnNavigationItemSelectedListener {
    @Inject
    GankNewestFragment gankNewestFragment;
    @Inject
    GankClassifyFragment gankClassifyFragment;
    @Inject
    GankWelfareFragment gankWelfareFragment;
    @BindView(R.id.gank_main_Navigation)
    BottomNavigationView gankMainNavigation;

    int hideFragment;
    @Override
    protected int getLayout() {
        return R.layout.fragment_gank_main;
    }

    @Override
    protected void initViewAndEvent() {
        MyApplication.getInstance().getFragmentComponent().inject(this);
        loadMultipleRootFragment(R.id.gank_main_fragment, 0, gankNewestFragment,gankClassifyFragment,gankWelfareFragment);
        gankMainNavigation.setOnNavigationItemSelectedListener(this);
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.gank_menu_newest:
                showHideFragment(gankNewestFragment,getHideFragment(hideFragment));
                hideFragment=0;
                break;
            case R.id.gank_menu_classify:
                showHideFragment(gankClassifyFragment,getHideFragment(hideFragment));
                hideFragment=1;
                break;
            case R.id.gank_menu_welfare:
                showHideFragment(gankWelfareFragment,getHideFragment(hideFragment));
                hideFragment=2;
                break;
        }
        return true;
    }

    private SupportFragment getHideFragment(int hideFragment){
        switch (hideFragment){
            case 0:
                return gankNewestFragment;
            case 1:
                return gankClassifyFragment;
            case 2:
                return gankWelfareFragment;
        }
        return null;
    }
}
