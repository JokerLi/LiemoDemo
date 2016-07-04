package com.ijinshan.liemo.application;

import android.app.Application;

import com.ijinshan.liemo.utils.AppContextUtil;
import com.ijinshan.liemo.utils.logs.ELog;

/**
 * Created by Li Guoqing on 2016/4/28.
 */
public class DemoApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        AppContextUtil.getInstance().setAppContext(this);
        ELog.e("hehehehhe");
    }
}
