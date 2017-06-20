package com.zyyoona7.loading.view.progress;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by zyyoona7 on 2017/6/20.
 * 支付宝loadingView，iOS支付宝我的页面下拉刷新效果
 */

public class AliPayLoadingView extends BaseProgressView {

    public AliPayLoadingView(Context context) {
        super(context);
    }

    public AliPayLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int setMaxProgress() {
        return 0;
    }

    @Override
    protected void initPaint() {

    }

    @Override
    public void setColor(int color) {

    }

    @Override
    public void startAnim() {

    }

    @Override
    public void startAnim(long time) {

    }

    @Override
    protected int getRepeatCount() {
        return 0;
    }

    @Override
    protected int getRepeatMode() {
        return 0;
    }

    @Override
    protected void onAnimatorUpdate(ValueAnimator animator) {

    }
}
