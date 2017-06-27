package com.zyyoona7.myloadingviews.progress.header;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.lcodecore.tkrefreshlayout.OnAnimEndListener;
import com.zyyoona7.loading.view.progress.uc.MaterialProgressDrawable;
import com.zyyoona7.loading.view.progress.uc.RightDrawable;
import com.zyyoona7.loading.view.progress.uc.UCLoadingView;

/**
 * Created by zyyoona7 on 2017/6/9.
 * UC浏览器头条HeaderView
 */

public class UCHeaderView extends FrameLayout implements IHeaderView {

    private UCLoadingView mLoadingView;
    private MaterialProgressDrawable mProgressDrawable;
    private RightDrawable mRightDrawable;

    public UCHeaderView(@NonNull Context context) {
        this(context, null);
    }

    public UCHeaderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        //imageView,drawable的载体
        mLoadingView = new UCLoadingView(context, Color.parseColor("#33B8FF"));
        mProgressDrawable = new MaterialProgressDrawable(context, this);
        //进度条drawable初始化
        mProgressDrawable.setColorSchemeColors(Color.WHITE);
        //是否显示箭头
        mProgressDrawable.showArrow(true);
        //箭头缩放，默认是0
        mProgressDrawable.setArrowScale(1f);
        //起始位置
        mProgressDrawable.setStartEndTrim(0f, 0.7f);
        //设置进度条透明度
        mProgressDrawable.setAlpha(0);
        mLoadingView.setImageDrawable(mProgressDrawable);
        //初始化对勾drawable
        mRightDrawable = new RightDrawable(Color.WHITE, dp2px(2));
        LayoutParams layoutParams = new LayoutParams(dp2px(30), dp2px(30), Gravity.CENTER);
        mLoadingView.setLayoutParams(layoutParams);
        addView(mLoadingView);
    }

    /**
     * 设置颜色
     *
     * @param color
     */
    public void setColor(int color) {
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void onPullingDown(float fraction, float maxHeadHeight, float headHeight) {
        if (fraction < 1) {
            mLoadingView.setCurrentProgress(fraction * mLoadingView.getMaxProgress());
            //小于1时设置进度条不可见
            if (mProgressDrawable.getAlpha() > 0) {
                mProgressDrawable.setAlpha(0);
            }
        } else {
            //>1时设置进度条可见，变更旋转角度
            mProgressDrawable.setAlpha(255);
            mProgressDrawable.setProgressRotation(fraction - 1);
            //设置背景进度为最大
            if (mLoadingView.getCurrentProgress() < mLoadingView.getMaxProgress()) {
                mLoadingView.setCurrentProgress(mLoadingView.getMaxProgress());
            }
        }
    }

    @Override
    public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight) {
        if (fraction < 1) {
            mLoadingView.setCurrentProgress(Math.round(fraction * mLoadingView.getMaxProgress()));
        }
    }

    @Override
    public void startAnim(float maxHeadHeight, float headHeight) {
        //进度条开始动画
        mProgressDrawable.start();
    }

    @Override
    public void onFinish(final OnAnimEndListener animEndListener) {
        //加载完毕，设置ImageDrawable为对勾drawable
        mLoadingView.setImageDrawable(mRightDrawable);
        //监听对勾动画执行完毕
        mRightDrawable.startAnim(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //重置
                reset();
                animEndListener.onAnimEnd();
                mRightDrawable.stopAnim();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    public void reset() {
        //进度条停止动画
        mProgressDrawable.stop();
        mProgressDrawable.setStartEndTrim(0f, 0.7f);
        mProgressDrawable.showArrow(true);
        mProgressDrawable.setArrowScale(1f);
        mProgressDrawable.setAlpha(0);
        mProgressDrawable.invalidateSelf();
        //重置对勾drawable的旋转角度
        mRightDrawable.resetDegree();
        //将imageDrawable设置回进度条drawable
        mLoadingView.setImageDrawable(mProgressDrawable);
    }

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
}
