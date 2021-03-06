package com.network.utils;

import android.content.Context;

/**
 * Created by Li Guoqing on 2016/4/29.
 */
public class AppContextUtil {
    private static AppContextUtil sInstance;
    private Context mAppContext;

    public synchronized static AppContextUtil getInstance(){
        if(sInstance == null){
            sInstance = new AppContextUtil();
        }
        return sInstance;
    }

    private AppContextUtil(){
    }

    public void setAppContext(Context context){
        if (context != null){
            mAppContext = context.getApplicationContext();
        }
    }

    public Context getAppContext(){
        return mAppContext;
    }

}
