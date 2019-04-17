package com.muz.progress.ui.gank.activity;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.muz.progress.R;
import com.muz.progress.base.BaseActivity;
import com.muz.progress.util.AppUtils;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;

/**
 * @author Muz
 * @description 展示web
 * @date 2018/6/26 10:19
 */
@Route(path = "/gank/activity/webActivity")
public class WebActivity extends BaseActivity {
    @BindView(R.id.web_detail_bar_image)
    ImageView webDetailBarImage;
    @BindView(R.id.web_detail_bar_copyright)
    TextView webDetailBarCopyright;
    @BindView(R.id.web_view_toolbar)
    Toolbar webViewToolbar;
    @BindView(R.id.web_bar)
    AppBarLayout webBar;
    @BindView(R.id.web_view_main)
    WebView webViewMain;
    @BindView(R.id.web_nsv_scroller)
    NestedScrollView webNsvScroller;
//    String url, title;

    @Autowired
    String url;
    @Autowired
    String title;

    @Override
    protected int getLayout() {
        return R.layout.activity_web;
    }

    @Override
    protected void initViewAndEvent() {
        ARouter.getInstance().inject(this);
        Glide.with(this).load(R.drawable.img_1).into(webDetailBarImage);
//        Intent intent = getIntent();
//        url = intent.getStringExtra("url");
//        title = intent.getStringExtra("title");
        Log.e("url=",url);
        Log.e("title=",title);
        setToolBar(webViewToolbar, title);
        WebSettings settings = webViewMain.getSettings();
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        if (AppUtils.isNetworkConnected()) {
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            settings.setCacheMode(WebSettings.LOAD_CACHE_ONLY);
        }
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        webViewMain.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                webView.loadUrl(s);
                return true;
            }
        });
        webViewMain.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
//TODO webView 加载进度
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                setTitle(title);
            }
        });
        webViewMain.loadUrl(url);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webViewMain.canGoBack()) {
            webViewMain.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                finishAfterTransition();
            } else {
                finish();
            }
        }
    }
}
