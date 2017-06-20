package com.zyyoona7.loading.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by zyyoona7 on 2017/6/8.
 */

public abstract class BaseView extends View {

    protected ValueAnimator mValueAnimator;

    public BaseView(Context context) {
        this(context, null);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

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
