package com.ijinshan.liemo.views.render;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Map;

/**
 * Created by Li Guoqing on 2016/11/15.
 */
public class CommonViewRender {
    private static final String TAG = "CommonViewRender";

    private Context mContext;
    private ViewHolder mViewHolder;

    public CommonViewRender(Context context, CommonViewTemplate cmViewBinder) {
        mContext = context;
        if(cmViewBinder == null){
            throw new RuntimeException("CommonViewTemplate is null");
        }
        mViewHolder = new ViewHolder(mContext, cmViewBinder);
    }

    public View getBindedView(ICommonViewData data) {
        if (data == null) {
            log("ad is null, return null view!");
            return null;
        }

        renderView(data);
        View view = mViewHolder.getView();
        return view;
    }

    private void renderView(ICommonViewData ad) {
        mViewHolder.resetView();
        if (ad == null) {
            return;
        }

        removeFromOldViewGroup();
        RenderViewHelper.setTextView(mViewHolder.mTitleView, ad.getTitle(), "");
        RenderViewHelper.setTextView(mViewHolder.mBodyView, ad.getBody(), "");
        RenderViewHelper.setTextView(mViewHolder.mSocialContextView, ad.getSmallBody(), "");
        RenderViewHelper.setTextView(mViewHolder.mCallToActionView, ad.getButton(), "Detail");
        RenderViewHelper.setBigCard(mViewHolder.mMainImageView, ad);
        RenderViewHelper.setImageView(mViewHolder.mIconImageView, ad.getIcon());
    }

    private void removeFromOldViewGroup() {
        if(mViewHolder.mLayoutView == null){
            return;
        }

        ViewParent parent = mViewHolder.mLayoutView.getParent();
        if(parent == null || !(parent instanceof ViewGroup)){
            return;
        }

        ((ViewGroup)parent).removeAllViews();
    }

    public static class ViewHolder {
        private Context mContext;
        private LayoutInflater mInflater;
        private CommonViewTemplate mViewBinder;

        private View mView;
        public View mLayoutView;
        public TextView mTitleView;
        public TextView mBodyView;
        public TextView mSocialContextView;
        public TextView mCallToActionView;
        public TextView mSponsoredView;
        public CommonMixView mMainImageView;
        public ImageView mIconImageView;
        public Map<String, Integer> extras;

        public ViewHolder(Context context, CommonViewTemplate viewBinder) {
            mContext = context;
            mViewBinder = viewBinder;
            mInflater = LayoutInflater.from(mContext);
            inflateView();
        }

        public void resetView() {
            mView = mLayoutView;
        }

        public void setView(View view) {
            mView = view;
        }

        public View getView() {
            return mView;
        }

        private void inflateView() {
            try {
                mView = mLayoutView = mInflater.inflate(mViewBinder.mLayoutId, null);
                mTitleView = noExceptionFindView(mLayoutView, mViewBinder.mTitleId, TextView.class, "mTitleView");
                mBodyView = noExceptionFindView(mLayoutView, mViewBinder.mTextId, TextView.class, "mBodyView");
                mSocialContextView = noExceptionFindView(mLayoutView, mViewBinder.mSocialContextId, TextView.class, "mSocialContextView");
                mCallToActionView = noExceptionFindView(mLayoutView, mViewBinder.mCallToActionId, TextView.class, "mCallToActionView");
                mSponsoredView = noExceptionFindView(mLayoutView, mViewBinder.mSponsoredId, TextView.class, "mSponsoredView");
                mMainImageView = noExceptionFindView(mLayoutView, mViewBinder.mMainImageId, CommonMixView.class, "mMainImageView");
                mIconImageView = noExceptionFindView(mLayoutView, mViewBinder.mIconImageId, ImageView.class, "mIconImageView");
                extras = mViewBinder.extras;
            } catch (Exception e) {
            }
        }

        private <T extends View> T noExceptionFindView(View layoutView, int id, Class<T> tClass, String type) {
            if (id == 0) {
                return null;
            }

            try {
                View view = layoutView.findViewById(id);
                if(tClass.isInstance(view)){
                    return (T) layoutView.findViewById(id);
                }else{
                    return null;
                }
            } catch (Exception e) {
                return null;
            }
        }

    }

    private static void log(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
    }
}
