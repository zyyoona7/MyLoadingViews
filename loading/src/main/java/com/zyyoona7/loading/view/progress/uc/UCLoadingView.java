package com.zyyoona7.loading.view.progress.uc;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by zyyoona7 on 2017/6/27.
 * Uc浏览器头条下拉刷新imageView，需要结合ProgressDrawable,MaterialProgressDrawable,RightDrawable
 * <p>
 * 详细信息看UCHeaderView实现
 */

public class UCLoadingView extends AppCompatImageView {
    private ProgressDrawable mBgDrawable;
    private float mCurrentProgress;
    private int mBgColor = Color.BLACK;
    private float mMaxProgress;


    public UCLoadingView(Context context, int bgColor) {
        super(context);
        this.mBgColor = bgColor;
        mBgDrawable = new ProgressDrawable(mBgColor);
        setBackgroundDrawable(mBgDrawable);
        mMaxProgress=setMaxProgress();
    }

    public UCLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mBgDrawable = new ProgressDrawable(mBgColor);
        setBackgroundDrawable(mBgDrawable);
    }

    protected float setMaxProgress() {
        return 360f;
    }

    public float getMaxProgress(){
        return mMaxProgress;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     * @param progress
     */
    public void setCurrentProgress(float progress) {
        this.mCurrentProgress = progress;
        mBgDrawable.setProgress(mCurrentProgress);
    }

    public float getCurrentProgress(){
        return mCurrentProgress;
    }


    public void setBgColor(int color) {
        this.mBgColor = color;
        postInvalidate();
    }
}
