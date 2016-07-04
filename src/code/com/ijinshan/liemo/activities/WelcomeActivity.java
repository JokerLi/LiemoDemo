package com.ijinshan.liemo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.ijinshan.liemo.utils.ThreadUtils;
import com.ijinshan.liemo.utils.logs.ELog;
import com.ijinshan.liemoapp.R;

/**
 * Created by Li Guoqing on 2016/5/5.
 */
public class WelcomeActivity extends Activity {
    private static final int DELAY_JUMP_INTERNAL = 5 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Handler handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(Message msg) {
                ELog.e("handleMessage" + " " + msg.what);
            }
        };
        handler.sendEmptyMessage(1);
    }

/*    static {
        System.loadLibrary("HelloWorldJNI");
    }
    private native String printJNI(String inputStr);*/

    @Override
    protected void onResume() {
        super.onResume();
        delayJump();
    }

    private void delayJump() {
        ThreadUtils.postOnUiThread(new Runnable() {
            @Override
            public void run() {
                jumpToMainActivity();
            }
        });
//        ThreadUtils.postOnUiThreadDelayed(new Runnable() {
//            @Override
//            public void run() {
//                jumpToMainActivity();
//            }
//        }, DELAY_JUMP_INTERNAL);
    }

    private void jumpToMainActivity() {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

}
