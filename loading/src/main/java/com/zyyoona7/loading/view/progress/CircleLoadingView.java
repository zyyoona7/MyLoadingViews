package com.zyyoona7.loading.view.progress;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by zyyoona7 on 2017/6/21.
 * 小密圈LoadingView iOS版小密圈下拉刷新
 */

public class CircleLoadingView extends BaseProgressView {

    private Paint mPaint;

    private RectF mRectF;

    private float degree;

    private float scale;

    public CircleLoadingView(Context context) {
        super(context);
    }

    public CircleLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int setMaxProgress() {
        return 0;
    }

    @Override
    protected void initPaint() {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(DEFAULT_COLOR);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(dp2px(DEFAULT_PAINT_WIDTH));
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
        mPaint.setStyle(Paint.Style.STROKE);
        if (mCurrentMode==MODE_PROGRESS){
            canvas.scale(scale,scale);
            canvas.drawCircle(0,0,x-circleSpacing,mPaint);
        }else {
            canvas.rotate(degree);
            canvas.drawArc(mRectF, 270, -300, false, mPaint);
            mPaint.setStyle(Paint.Style.FILL);
            //计算圆心的坐标
            double angle = -60.0;
            angle = Math.toRadians(angle);
            float cx = (float) ((x - circleSpacing) * Math.cos(angle));
            float cy = (float) ((x - circleSpacing) * Math.sin(angle));
            canvas.drawCircle(cx, cy, dp2px(DEFAULT_PAINT_WIDTH + 1), mPaint);
        }

    }

    /**
     * 设置缩放大小
     * @param scale
     */
    public void setScale(float scale){
        setMode(MODE_PROGRESS);
        if (scale>1) {
            scale=1;
        }
        this.scale=scale;
        postInvalidate();
    }

    @Override
    public void setColor(int color) {
        mPaint.setColor(color);
        postInvalidate();
    }

    @Override
    public void startAnim() {
        stopAnim();
        setMode(MODE_ROTATE);
        startAnim(0,360,500);
    }

    @Override
    public void startAnim(long time) {
        stopAnim();
        setMode(MODE_ROTATE);
        startAnim(0,360,500);
    }

    @Override
    protected int getRepeatCount() {
        return ValueAnimator.INFINITE;
    }

    @Override
    protected int getRepeatMode() {
        return ValueAnimator.RESTART;
    }

    @Override
    protected void onAnimatorUpdate(ValueAnimator animator) {
        degree= (float) animator.getAnimatedValue();
        postInvalidate();
    }
}
