package com.ijinshan.liemo.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Li Guoqing on 2016/12/2.
 */
public class LoadingView extends View {
    private final static int MESS = 7776;

    private int mWidth;
    private int mHeight;
    Paint mPaint;
    int mIndex = 0;
    int mIndexLong = 0;
    Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what != 7776){
                return;
            }
            mIndex++;
            if(mIndex > 40){
                mIndex = 0;
            }
            mIndexLong++;
            if(mIndexLong > 60){
                mIndexLong = 0;
            }
            invalidate();
        }
    };

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
        mPaint = new Paint();             // 创建画笔
        mPaint.setColor(Color.BLACK);           // 画笔颜色 - 黑色
        mPaint.setStyle(Paint.Style.STROKE);    // 填充模式 - 描边
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10);              // 边框宽度 - 10
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);  // 移动坐标系到屏幕中心
        canvas.scale(1,-1);                         // <-- 注意 翻转y坐标轴

        Path path = new Path();
        RectF oval = new RectF(-30,-30,30,30);
        int startAngle = -9 * mIndex;
        int sweepAngle = 4 * (Math.abs(mIndexLong - 30) + 15) - 10;

        path.addArc(oval, startAngle, sweepAngle);
        canvas.drawPath(path,mPaint);

        mHandler.sendEmptyMessageDelayed(MESS, 20);
    }

}
