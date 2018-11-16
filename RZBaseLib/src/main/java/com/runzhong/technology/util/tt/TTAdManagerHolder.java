package com.runzhong.technology.util.tt;

import android.content.Context;


import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdManager;
import com.bytedance.sdk.openadsdk.TTAdManagerFactory;
import com.runzhong.technology.R;
import com.runzhong.technology.RZManager;

public class TTAdManagerHolder {
    private static String appId;
    private static boolean sInit;
    public static TTAdManager getInstance(Context context,String appId) {
        if(appId!=null) {
            TTAdManagerHolder.appId = appId;
        }
        TTAdManager ttAdManager = TTAdManagerFactory.getInstance(context);
        if (!sInit) {
            synchronized (TTAdManagerHolder.class) {
                if (!sInit) {
                    doInit(ttAdManager,context);
                    sInit = true;
                }
            }
        }
        return ttAdManager;
    }
    public static TTAdManager getInstance(Context context) {
        return getInstance(context,null);
    }

    private static void doInit(TTAdManager ttAdManager,Context context) {
        ttAdManager.setAppId(appId)
                .setName(context.getString(R.string.app_name)).setTitleBarTheme(TTAdConstant.TITLE_BAR_THEME_DARK)
                .setAllowShowNotifiFromSDK(true)
                .setAllowLandingPageShowWhenScreenLock(true)
                .setGlobalAppDownloadListener(new AppDownloadStatusListener(context))
                .setDirectDownloadNetworkType(TTAdConstant.NETWORK_STATE_WIFI);
//        if(RZManager.getInstance().isDebugMode()){
//            ttAdManager.openDebugMode();
//        }
    }
}
