package com.muz.progress.util;

import android.content.Context;
import android.net.ConnectivityManager;

import com.muz.progress.app.MyApplication;

/**
 * Created by Administrator on 2018/6/26.
 */

public class AppUtils {
    /**
     * 检查是否有可用网络
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) MyApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
