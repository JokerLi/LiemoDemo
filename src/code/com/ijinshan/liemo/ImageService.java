package com.ijinshan.liemo;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.ijinshan.liemo.utils.VolleyUtil;

import aidl.ImageCallback;
import aidl.ImageLoadService;

public class ImageService extends Service {
    private static final String TAG = "AIDLService";
    private void Log(String str) {
        Log.d(TAG, "------ " + str + "------" + getCurProcessName(this));
    }

    String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return "";
    }

    @Override
    public void onCreate() {
        Log("service create");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log("service start id=" + startId);
    }

    @Override
    public IBinder onBind(Intent t) {
        Log("service on bind");
        return mBinder;
    }

    @Override
    public void onDestroy() {
        Log("service on destroy");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log("service on unbind");
        return super.onUnbind(intent);
    }

    public void onRebind(Intent intent) {
        Log("service on rebind");
        super.onRebind(intent);
    }

    private final ImageLoadService.Stub mBinder = new ImageLoadService.Stub() {
        @Override
        public void registerCallBack(final ImageCallback callback) throws RemoteException {
            if(callback != null){
                VolleyUtil.loadImage(callback.getImageUrl(), new ImageLoader.ImageListener() {
                    @Override
                    public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                        try {
                            callback.onImageLoaded(imageContainer.getRequestUrl(), "url");
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
            }
        }
    };
}
