package com.ijinshan.liemo.utils;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;

/**
 * Created by Li Guoqing on 2016/5/30.
 */
public class SystemCommons {
    /**
     * 获取安卓ID
     * @param context
     * @return
     */
    private static String sAndroidID = "";
    private static Object sAndroidIDLock = new Object();
    public static String getAndroidId() {
        Context context = AppContextUtil.getInstance().getAppContext();
        if (TextUtils.isEmpty(sAndroidID)) {
            synchronized (sAndroidIDLock) {
                if (TextUtils.isEmpty(sAndroidID)) {
                    String androidId = "";
                    try {
                        androidId = Settings.System.getString(
                                context.getContentResolver(),
                                Settings.System.ANDROID_ID);
                        if(!TextUtils.isEmpty(androidId)){
                            sAndroidID = androidId;
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        return sAndroidID;
    }
}
