package com.lin.a3dmgame.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by my on 2016/7/5.
 */
public class NetUtils {
    //判断是否联网
    public static boolean netConnect(Activity activity) {
        boolean flag = false;
        Log.i("aaa", "看下一行");
        ConnectivityManager manager = (ConnectivityManager) activity.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            return flag;
        }
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null || info.isAvailable()) {
            flag = true;
        }
        return flag;
    }
}
