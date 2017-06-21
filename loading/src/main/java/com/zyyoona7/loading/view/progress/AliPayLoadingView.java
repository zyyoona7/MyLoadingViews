package com.zyyoona7.loading.view.progress;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by zyyoona7 on 2017/6/20.
 * 支付宝loadingView，iOS支付宝我的页面下拉刷新效果
 */

public class AliPayLoadingView extends BaseProgressView {

    //圆画笔，需要设置两个不同的颜色，所以用了两个画笔
    private Paint mCirclePaint;
    //圆弧画笔
    private Paint mArcPaint;

    private RectF mRectF;

    private float degree;

    public AliPayLoadingView(Context context) {
        super(context);
    }

    public AliPayLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int setMaxProgress() {
        return 360;
    }

    @Override
    protected void initPaint() {
        mCirclePaint=new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setColor(DEFAULT_COLOR);
        mCirclePaint.setStrokeWidth(dp2px(DEFAULT_PAINT_WIDTH));

        mArcPaint=new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setColor(Color.RED);
        mArcPaint.setStrokeWidth(dp2px(DEFAULT_PAINT_WIDTH));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float space=10;
        float x=getWidth()/2;
        float y=getHeight()/2;

        canvas.translate(x,y);
        if (mRectF == null) {
            //有边距，否则圆弧会不完全
            mRectF = new RectF(-x + space, -y + space, x - space, y - space);
        }
        canvas.drawCircle(0,0,x-space,mCirclePaint);
        if (mCurrentMode==MODE_PROGRESS) {
            canvas.drawArc(mRectF, -90, mCurrentProgress, false, mArcPaint);
        }else {
            canvas.rotate(degree);
            canvas.drawArc(mRectF, -90, 30, false, mArcPaint);
        }
    }

    /**
     * 设置颜色
     * @param circleColor
     * @param arcColor
     */
    public void setColor(int circleColor,int arcColor){
        mCirclePaint.setColor(circleColor);
        mArcPaint.setColor(arcColor);
        postInvalidate();
    }

    @Override
    public void setColor(int color) {
        mArcPaint.setColor(color);
        postInvalidate();
    }

    @Override
    public void startAnim() {
        stopAnim();
        setMode(MODE_ROTATE);
        startAnim(0,360,1000);
    }

    @Override
    public void startAnim(long time) {
        stopAnim();
        setMode(MODE_ROTATE);
        startAnim(0,360,time);
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
