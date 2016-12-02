package com.ijinshan.liemo.views.render;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Li Guoqing on 2016/11/15.
 */
public class CommonViewTemplate {
    public static final String SPONSORED_TEXT = "sponsoredtext";
    public static final String SPONSORED_IMAGE = "sponsoredimage";
    public static final String AD_CORNER = "adcorner";
    public static final String BRAND_LOGO = "brandlogo";

    /**
     * check id , check view , save id
     */
    public final int mLayoutId;
    public final int mTitleId;
    public final int mTextId;
    public final int mSocialContextId;
    public final int mCallToActionId;
    public final int mMainImageId;
    public final int mIconImageId;
    public final int mStarRatingId;
    public final int mSponsoredId;
    @NonNull
    final Map<String, Integer> extras;
    private CommonViewTemplate(Builder builder){
        if(checkBuilderIllegal(builder)){
            throwException("input resource id is illegal");
        }
        mLayoutId = builder.mLayoutId;
        mTitleId = builder.mTitleId;
        mTextId = builder.mTextId;
        mSocialContextId = builder.mSocialContextId;
        mCallToActionId = builder.mCallToActionId;
        mMainImageId = builder.mMainImageId;
        mIconImageId = builder.mIconImageId;
        mStarRatingId = builder.mStarRatingId;
        mSponsoredId = builder.mSponsoredId;
        extras = builder.extras;
    }

    private void throwException(String s){
        if(TextUtils.isEmpty(s)){
            return;
        }
        throw new RuntimeException(s);
    }

    private boolean checkBuilderIllegal(Builder builder) {
        if(builder == null){
            return true;
        }
        return false;
    }

    public static class Builder{
        private final int mLayoutId;
        private int mTitleId;
        private int mTextId;
        private int mSocialContextId;
        private int mCallToActionId;
        private int mMainImageId;
        private int mIconImageId;
        private int mSponsoredId;
        private int mStarRatingId;

        @NonNull
        private Map<String, Integer> extras = Collections.emptyMap();

        public Builder(int layoutId) {
            this.mLayoutId = layoutId;
            this.extras = new HashMap();
        }

        @NonNull
        public final Builder titleId(int titleId) {
            this.mTitleId = titleId;
            return this;
        }

        @NonNull
        public final Builder textId(int textId) {
            this.mTextId = textId;
            return this;
        }

        @NonNull
        public final Builder callToActionId(int callToActionId) {
            this.mCallToActionId = callToActionId;
            return this;
        }

        @NonNull
        public final Builder mainImageId(int mainImageId) {
            this.mMainImageId = mainImageId;
            return this;
        }

        @NonNull
        public final Builder iconImageId(int iconImageId) {
            this.mIconImageId = iconImageId;
            return this;
        }

        @NonNull
        public final Builder socialContextId(int socialContextId) {
            this.mSocialContextId = socialContextId;
            return this;
        }

        @NonNull
        public final Builder starRatingId(int starRatingId) {
            this.mStarRatingId = starRatingId;
            return this;
        }

        @NonNull
        public final Builder sponsoredId(int sponsoredId) {
            this.mSponsoredId = sponsoredId;
            return this;
        }

        @NonNull
        public final Builder addExtras(Map<String, Integer> resourceIds) {
            this.extras = new HashMap(resourceIds);
            return this;
        }

        @NonNull
        public final Builder addExtra(String key, int resourceId) {
            this.extras.put(key, Integer.valueOf(resourceId));
            return this;
        }

        public final CommonViewTemplate build() {
            return new CommonViewTemplate(this);
        }

    }
}
