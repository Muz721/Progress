package com.muz.progress.ui;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.muz.progress.R;
import com.muz.progress.base.BaseActivity;
import com.muz.progress.app.MyApplication;
import com.muz.progress.ui.gank.fragment.GankFragment;
import com.muz.progress.util.UIUtils;
import com.umeng.analytics.MobclickAgent;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.main_navigation)
    NavigationView mainNavigation;
    @BindView(R.id.main_drawer)
    DrawerLayout mainDrawer;
    //干货集中营
    @Inject
    GankFragment gankFragment;
    ActionBarDrawerToggle drawerToggle;
    MenuItem lastMenuItem;
    /**
     * 绑定布局
     * @return   布局id
     */
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }


    /**
     * 初始化布局和事件
     */
    @Override
    protected void initViewAndEvent() {
        MyApplication.getInstance().getActivityComponent().inject(this);
setToolBar(toolBar,"干货集中营");
        drawerToggle=new ActionBarDrawerToggle(this,mainDrawer,toolBar,R.string.drawer_open,R.string.drawer_close);
   drawerToggle.syncState();
   mainDrawer.addDrawerListener(drawerToggle);
        lastMenuItem=mainNavigation.getMenu().findItem(R.id.drawer_gank);
        loadMultipleRootFragment(R.id.main_frameLayout,0,gankFragment);
        mainNavigation.setNavigationItemSelectedListener(this);
        View view=View.inflate(this,R.layout.view_drawer_header,null);
        final ImageView img =view.findViewById(R.id.drawer_cover);
//        Glide.with(this).load(R.drawable.img_1).into(img);
        Glide.with(this).load(R.drawable.img_11).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new SimpleTarget<Bitmap>(UIUtils.getScreenWidth(this) / 2, UIUtils.getScreenWidth(this)/ 2) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

//                                int width = resource.getWidth();
//                                int height = resource.getHeight();
//                                int realHeight = (UIUtils.getScreenWidth(getBaseContext()) / 2) * height / width;
                        img.setImageBitmap(resource);

                    }
                });
        mainNavigation.addHeaderView(view);
        MobclickAgent.setSessionContinueMillis(1000*40);
//        mainNavigation.add
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

        }
        if (lastMenuItem!=null){
            lastMenuItem.setChecked(false);
        }
        lastMenuItem=item;
        item.setChecked(true);
        toolBar.setTitle(item.getTitle());
        mainDrawer.closeDrawers();
//        showHideFragment(getTargetFragment(showFragment), getTargetFragment(hideFragment));
        return true;
    }
}
