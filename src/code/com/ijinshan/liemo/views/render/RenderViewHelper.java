package com.ijinshan.liemo.views.render;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ijinshan.liemo.utils.VolleyUtil;

/**
 * Created by Li Guoqing on 2016/11/19.
 */
public class RenderViewHelper {

    public static void setImageView(final ImageView imageView, String url) {
        if (imageView == null || TextUtils.isEmpty(url)) {
            return;
        }

        imageView.setVisibility(View.VISIBLE);
        VolleyUtil.loadImage(imageView, url);
    }

    public static void setStarRating(RatingBar ratingBar, float num) {
        if (ratingBar == null || num < 0f) {
            return;
        }

        ratingBar.setVisibility(View.VISIBLE);
        ratingBar.setRating(num);
    }

    public static void setTextView(TextView textView, CharSequence inputText, CharSequence defaultText) {
        if (textView == null) {
            return;
        }
        CharSequence txt = inputText;
        if (TextUtils.isEmpty(txt)) {
            if (TextUtils.isEmpty(defaultText)) {
                textView.setVisibility(View.GONE);
                return;
            } else {
                txt = defaultText;
            }
        }
        textView.setVisibility(View.VISIBLE);
        textView.setText(txt);
    }

    public static void setBigCard(CommonMixView mainImageView, ICommonViewData ad) {
        if (mainImageView == null || ad == null) {
            return;
        }
        mainImageView.setData(ad);
    }

}
