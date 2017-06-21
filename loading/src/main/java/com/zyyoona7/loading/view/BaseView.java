package com.zyyoona7.loading.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by zyyoona7 on 2017/6/8.
 */

public abstract class BaseView extends View {

    private static final int DEFAULT_WIDTH=50;
    private static final int DEFAULT_HEIGHT=50;
    //默认颜色
    protected static final int DEFAULT_COLOR= Color.BLACK;
    //默认画笔宽度
    protected static final int DEFAULT_PAINT_WIDTH=2;

    protected ValueAnimator mValueAnimator;

    public BaseView(Context context) {
        this(context, null);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidthSize(widthMeasureSpec),measureHeightSize(heightMeasureSpec));
    }

    /**
     * measure width
     * @param measureSpec spec
     * @return width
     */
    private int measureWidthSize(int measureSpec) {
        int defSize = dp2px(DEFAULT_WIDTH);
        int specSize = MeasureSpec.getSize(measureSpec);
        int specMode = MeasureSpec.getMode(measureSpec);

        int result = 0;
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                result = Math.min(defSize, specSize);
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }

    /**
     * measure height
     * @param measureSpec spec
     * @return height
     */
    private int measureHeightSize(int measureSpec) {
        int defSize = dp2px(DEFAULT_HEIGHT);
        int specSize = MeasureSpec.getSize(measureSpec);
        int specMode = MeasureSpec.getMode(measureSpec);

        int result = 0;
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                result = Math.min(defSize, specSize);
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }

//    @Override
//    protected void onVisibilityChanged(@NonNull View changedView,int visibility) {
//        super.onVisibilityChanged(changedView, visibility);
//        if (visibility == GONE || visibility == INVISIBLE) {
//            stopAnim();
//        } else {
//            startAnim();
//        }
//    }

    /**
     * 开始动画
     *
     * @param startF
     * @param endF
     * @param time
     */
    protected void startAnim(float startF, final float endF, long time) {
        mValueAnimator = ValueAnimator.ofFloat(startF, endF);
        mValueAnimator.setDuration(time);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        if (ValueAnimator.RESTART == getRepeatMode()) {
            mValueAnimator.setRepeatMode(ValueAnimator.RESTART);
        } else if (ValueAnimator.REVERSE == getRepeatMode()) {
            mValueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        }
        mValueAnimator.setRepeatCount(getRepeatCount());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                onAnimatorUpdate(animation);
            }
        });

        if (!mValueAnimator.isRunning()) {
            mValueAnimator.start();
        }
    }

    /**
     * 停止动画
     */
    public void stopAnim() {
        if (mValueAnimator != null) {
            clearAnimation();

            mValueAnimator.setRepeatCount(0);
            mValueAnimator.cancel();
            mValueAnimator.end();

        }
    }

    /**
     * 初始化画笔
     */
    protected abstract void initPaint();

    /**
     * 设置颜色
     *
     * @param color
     */
    public abstract void setColor(int color);

    /**
     * 开始动画
     */
    public abstract void startAnim();

    /**
     * 开始动画 可以设置动画时长
     *
     * @param time
     */
    public abstract void startAnim(long time);

    /**
     * 获取动画重复次数
     *
     * @return
     */
    protected abstract int getRepeatCount();

    /**
     * 获取动画重复模式
     *
     * @return
     */
    protected abstract int getRepeatMode();

    /**
     * 动画更新监听
     *
     * @param animator
     */
    protected abstract void onAnimatorUpdate(ValueAnimator animator);

    /**
     * dp转px
     *
     * @param dpValue dp值
     * @return px值
     */
    public int dp2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转dp
     *
     * @param pxValue px值
     * @return dp值
     */
    public int px2dp(float pxValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp转px
     *
     * @param spValue sp值
     * @return px值
     */
    public int sp2px(float spValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * px转sp
     *
     * @param pxValue px值
     * @return sp值
     */
    public int px2sp(float pxValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
}
