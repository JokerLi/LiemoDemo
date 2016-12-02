package com.ijinshan.liemo.views.render;

import android.view.View;

/**
 * Created by Li Guoqing on 2016/12/2.
 */
public interface ICommonViewData {
    void registerView(View view);
    CharSequence getTitle();
    CharSequence getBody();
    CharSequence getSmallBody();
    CharSequence getButton();
    String getIcon();
    String getImageUrl();
}
