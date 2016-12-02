package com.ijinshan.liemo.views.render;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Li Guoqing on 2016/11/15.
 */
public class CommonMixView extends RelativeLayout {
    private int mAdHashCode = -1;

    public CommonMixView(Context context) {
        super(context);
    }

    public CommonMixView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommonMixView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setData(ICommonViewData data) {
        if (data == null) {
            return;
        }

        if (mAdHashCode == data.hashCode()) {
            return;
        }

        mAdHashCode = data.hashCode();
        View view = createDefaultImageView(data);
        if (view != null) {
            addViewToCMMediaView(view);
        }
    }
    private View createDefaultImageView(ICommonViewData data) {
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        RenderViewHelper.setImageView(imageView, data.getImageUrl());
        return imageView;
    }

    private void addViewToCMMediaView(View view) {
        if (view == null) {
            return;
        }

        LayoutParams layoutParams = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        view.setLayoutParams(layoutParams);
        view.setVisibility(View.VISIBLE);
        removeAllViews();
        addView(view);
    }

}
