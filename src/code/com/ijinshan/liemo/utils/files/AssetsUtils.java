package com.ijinshan.liemo.utils.files;

import android.graphics.Bitmap;
import android.text.TextUtils;

import com.ijinshan.liemo.utils.AppContextUtil;

/**
 * Created by Li Guoqing on 2016/5/26.
 */
public class AssetsUtils {
    public static String getStrByAssets(String fileName) {
        if(TextUtils.isEmpty(fileName)){
            return null;
        }
        String adJson = FileUtils.readStringFromAsset(AppContextUtil.getInstance().getAppContext(), fileName);
        return adJson;
    }

    public static Bitmap getBitmapByAssets(String fileName) {
        if(TextUtils.isEmpty(fileName)){
            return null;
        }
        Bitmap bitmap = FileUtils.getBitmapFromAsset(AppContextUtil.getInstance().getAppContext(), fileName);
        return bitmap;
    }
}
