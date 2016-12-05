package com.ijinshan.liemo.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.lang.reflect.Field;

/**
 * Created by yzx on 2015/12/10.
 */
public class DimenSdkUtils {
    public final static float BASE_SCREEN_WIDH = 720f;
    public final static float BASE_SCREEN_HEIGHT = 1280f;
    public final static float BASE_SCREEN_DENSITY = 2f;
    public static Float sScaleW, sScaleH;
    /**
     * 如果要计算的值已经经过dip计算，则使用此结果，如果没有请使用getScaleFactorWithoutDip
     */
    public static float getScaleFactorW() {
        if (sScaleW == null) {
            sScaleW = (getScreenWidth() * BASE_SCREEN_DENSITY) / (getDensity() * BASE_SCREEN_WIDH);
        }
        return sScaleW;
    }

    public static float getScaleFactorH() {
        if (sScaleH == null) {
            sScaleH = (getScreenHeight() * BASE_SCREEN_DENSITY)
                    / (getDensity() * BASE_SCREEN_HEIGHT);
        }
        return sScaleH;
    }

    public static int getScreenWidth() {
        DisplayMetrics dm = AppContextUtil.getInstance().getAppContext().getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        DisplayMetrics dm = AppContextUtil.getInstance().getAppContext().getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    public static float getDensity() {
        return AppContextUtil.getInstance().getAppContext().getResources().getDisplayMetrics().density;
    }

    public static int dp2px(float value) {
        return (int) applyDimension(DP_TO_PX, value, AppContextUtil.getInstance().getAppContext().getResources().getDisplayMetrics());
    }



    private static final int DP_TO_PX = TypedValue.COMPLEX_UNIT_DIP;
    private static final int SP_TO_PX = TypedValue.COMPLEX_UNIT_SP;
    private static final int PX_TO_DP = TypedValue.COMPLEX_UNIT_MM + 1;
    private static final int PX_TO_SP = TypedValue.COMPLEX_UNIT_MM + 2;
    private static final int DP_TO_PX_SCALE_H = TypedValue.COMPLEX_UNIT_MM + 3;
    private static final int DP_SCALE_H = TypedValue.COMPLEX_UNIT_MM + 4;
    private static final int DP_TO_PX_SCALE_W = TypedValue.COMPLEX_UNIT_MM + 5;
    // -- dimens convert

    private static float applyDimension(int unit, float value, DisplayMetrics metrics) {
        switch (unit) {
            case DP_TO_PX:
            case SP_TO_PX:
                return TypedValue.applyDimension(unit, value, metrics);
            case PX_TO_DP:
                return value / metrics.density;
            case PX_TO_SP:
                return value / metrics.scaledDensity;
            case DP_TO_PX_SCALE_H:
                return TypedValue.applyDimension(DP_TO_PX, value * getScaleFactorH(), metrics);
            case DP_SCALE_H:
                return value * getScaleFactorH();
            case DP_TO_PX_SCALE_W:
                return TypedValue.applyDimension(DP_TO_PX, value * getScaleFactorW(), metrics);
        }
        return 0;
    }

    public static void updateLayout(View view, int w, int h) {
        if (view == null)
            return;
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null)
            return;
        if (w != -3)
            params.width = w;
        if (h != -3)
            params.height = h;
        view.setLayoutParams(params);
    }

    public static void updateLayoutMargin(View view, int l, int t, int r, int b) {
        if (view == null)
            return;
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null)
            return;
        if (params instanceof RelativeLayout.LayoutParams) {
            updateMargin(view, (RelativeLayout.LayoutParams) params, l, t, r, b);
        } else if (params instanceof LinearLayout.LayoutParams) {
            updateMargin(view, (LinearLayout.LayoutParams) params, l, t, r, b);
        } else if (params instanceof FrameLayout.LayoutParams) {
            updateMargin(view, (FrameLayout.LayoutParams) params, l, t, r, b);
        }
    }

    private static void updateMargin(View view, ViewGroup.MarginLayoutParams params, int l, int t,int r, int b) {
        if (view == null)
            return;
        boolean isChange = false;
        if (l != -3){
            if(params.leftMargin != l){
                params.leftMargin = l;
                isChange = true;
            }
        }

        if (t != -3){
            if(params.topMargin != t){
                params.topMargin = t;
                isChange = true;
            }
        }

        if (r != -3){
            if(params.rightMargin != r){
                params.rightMargin = r;
                isChange = true;
            }
        }

        if (b != -3){
            if(params.bottomMargin != b){
                params.bottomMargin = b;
                isChange = true;
            }
        }
        if(isChange){
            view.setLayoutParams(params);
        }
    }

    /**
     * 获取状态栏高度
     * @return
     */
    public static int getStatusBarHeight(){
        Context context = AppContextUtil.getInstance().getAppContext();
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

}
