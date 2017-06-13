package com.zyyoona7.loading.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by zyyoona7 on 2017/6/13.
 */

public class RotateRingLoadingView extends BaseView {

    public static final int PROGRESS = 1;
    public static final int ROTATE = 2;

    private RectF mRectF;

    private Paint mPaint;

    private ValueAnimator mValueAnimator;

    private float degrees=0f;

    public RotateRingLoadingView(Context context) {
        this(context, null);
    }

    public RotateRingLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(dp2px(2));
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        width = width > height ? height : width;
        setMeasuredDimension(width, width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float circleSpacing = 10;
        float x = (getWidth()) / 2;
        float y = (getHeight()) / 2;
        //将旋转中心移动到view的中心
        canvas.translate(x, y);
        if (mRectF == null) {
            //有边距，否则圆弧会不完全
            mRectF = new RectF(-x + circleSpacing, -y + circleSpacing, x - circleSpacing, y - circleSpacing);
        }
        //-degrees逆时针旋转，degrees顺时针
        canvas.rotate(-degrees);
        canvas.drawArc(mRectF, 300, -330, false, mPaint);
    }

    public void startAnim() {
        mValueAnimator = ValueAnimator.ofFloat(0, 360);
        mValueAnimator.setDuration(500);
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.setRepeatMode(ValueAnimator.RESTART);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                degrees = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        mValueAnimator.start();
    }

    public void stopAnim(){
        if (mValueAnimator != null) {
            clearAnimation();
            mValueAnimator.setRepeatCount(0);
            mValueAnimator.end();
            mValueAnimator.cancel();
            mValueAnimator = null;
        }
    }
}
