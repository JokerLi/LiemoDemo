package com.ijinshan.liemo.views.loadings.loading2;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

/**
 * Created by Li Guoqing on 2016/12/2.
 */
public class LoadingView extends RelativeLayout {
    private static final int ANIMATION_DURATION = 500;
    private final static float DISTANCE = 200;
    private final static float FACTOR = 1.2f;

    private AnimatorSet mDownAnimatorSet;
    private AnimatorSet mUpAnimatorSet;
    LoadingShapeView mShapeView;

    public LoadingView(Context context) {
        super(context);
        initPaint();
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint(){
        mShapeView = new LoadingShapeView(getContext());
        mShapeView.setLayoutParams(new RelativeLayout.LayoutParams(100, 100));
        addView(mShapeView);
        freeFall();
    }

    public void freeFall() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mShapeView, "translationY", 0, DISTANCE);
        objectAnimator.setDuration(ANIMATION_DURATION);
        objectAnimator.setInterpolator(new AccelerateInterpolator(FACTOR));
        mDownAnimatorSet = new AnimatorSet();
        mDownAnimatorSet.setDuration(ANIMATION_DURATION);
        mDownAnimatorSet.play(objectAnimator);
        mDownAnimatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {}

            @Override
            public void onAnimationEnd(Animator animation) {
                mShapeView.changeShape();
                upThrow();
            }

            @Override
            public void onAnimationCancel(Animator animation) {}

            @Override
            public void onAnimationRepeat(Animator animation) {}
        });
        mDownAnimatorSet.start();
    }

    public void upThrow() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mShapeView, "translationY", DISTANCE, 0);
        ObjectAnimator objectAnimator1 = null;
        switch (mShapeView.getShape()) {
            case LoadingShapeView.SHAPE_CIRCLE:
                objectAnimator1 = ObjectAnimator.ofFloat(mShapeView, "rotation", 0, -120);
                break;
            case LoadingShapeView.SHAPE_RECTANGLE:
                objectAnimator1 = ObjectAnimator.ofFloat(mShapeView, "rotation", 0, 180);
                break;
            case LoadingShapeView.SHAPE_TRIANGLE:
                objectAnimator1 = ObjectAnimator.ofFloat(mShapeView, "rotation", 0, 180);
                break;
        }

        objectAnimator.setDuration(ANIMATION_DURATION);
        objectAnimator1.setDuration(ANIMATION_DURATION);
        objectAnimator.setInterpolator(new DecelerateInterpolator(FACTOR));
        objectAnimator1.setInterpolator(new DecelerateInterpolator(FACTOR));
        mUpAnimatorSet = new AnimatorSet();
        mUpAnimatorSet.setDuration(ANIMATION_DURATION);
        mUpAnimatorSet.playTogether(objectAnimator, objectAnimator1);

        mUpAnimatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {}

            @Override
            public void onAnimationEnd(Animator animation) {
                freeFall();
            }

            @Override
            public void onAnimationCancel(Animator animation) {}

            @Override
            public void onAnimationRepeat(Animator animation) {}
        });
        mUpAnimatorSet.start();
    }

}
