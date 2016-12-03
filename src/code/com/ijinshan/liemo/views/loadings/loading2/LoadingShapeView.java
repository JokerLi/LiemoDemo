package com.ijinshan.liemo.views.loadings.loading2;

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
import android.util.Log;
import android.view.View;

/**
 * Created by Li Guoqing on 2016/12/2.
 */
public class LoadingShapeView extends View {
    public static final int SHAPE_TRIANGLE = 0;
    public static final int SHAPE_RECTANGLE = 1;
    public static final int SHAPE_CIRCLE = 2;

    public static final int COLOR_TRIANGLE = Color.parseColor("#66cccc");
    public static final int COLOR_RECTANGLE = Color.parseColor("#6699cc");
    public static final int COLOR_CIRCLE = Color.parseColor("#ff6666");

    private int mWidth;
    private int mHeight;
    private int mShapeType = SHAPE_TRIANGLE;
    Paint mPaint;

    public LoadingShapeView(Context context) {
        super(context);
        initPaint();
    }

    public LoadingShapeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public LoadingShapeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint(){
        mPaint = new Paint();
        mPaint.setColor(COLOR_TRIANGLE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    public int getShape(){
        return mShapeType;
    }

    public void changeShape(){
        mShapeType++;
        if(mShapeType == 3){
            mShapeType = 0;
        }
        invalidate();
        changeColor();
    }

    private void changeColor() {
        switch (mShapeType){
            case SHAPE_TRIANGLE:
                mPaint.setColor(COLOR_TRIANGLE);
                break;
            case SHAPE_RECTANGLE:
                mPaint.setColor(COLOR_RECTANGLE);
                break;
            case SHAPE_CIRCLE:
                mPaint.setColor(COLOR_CIRCLE);
                break;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);  // 移动坐标系到屏幕中心
        canvas.scale(1,-1);                         // <-- 注意 翻转y坐标轴
        Path path = new Path();
        float radius = getRadius();
        switch (mShapeType){
            case SHAPE_CIRCLE: {
                path.addCircle(0, 0, radius, Path.Direction.CCW);
                break;
            }
            case SHAPE_RECTANGLE: {
                RectF rectF = new RectF(-radius, -radius, radius, radius);
                path.addRect(rectF, Path.Direction.CCW);
                canvas.drawPath(path, mPaint);
                break;
            }
            case SHAPE_TRIANGLE: {
                path.moveTo(0, radius);
                path.lineTo( radius * 0.866f, -radius / 2);
                path.lineTo(-radius * 0.866f, -radius / 2);
                break;
            }
        }
        canvas.drawPath(path,mPaint);
    }

    private float getRadius(){
        float radius = Math.min(mWidth, mHeight) / 3;
        return radius;
    }

}
