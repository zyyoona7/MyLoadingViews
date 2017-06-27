package com.zyyoona7.myloadingviews.progress.header;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.lcodecore.tkrefreshlayout.OnAnimEndListener;
import com.zyyoona7.loading.view.progress.CircleLoadingView;
import com.zyyoona7.myloadingviews.R;

/**
 * Created by zyyoona7 on 2017/6/21.
 * 小密圈HeaderView
 */

public class CircleHeaderView extends FrameLayout implements IHeaderView {


    private CircleLoadingView mLoadingView;

    public CircleHeaderView(@NonNull Context context) {
        this(context, null);
    }

    public CircleHeaderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.header_circle, this);
        mLoadingView = (CircleLoadingView) findViewById(R.id.clv_header);
    }

    /**
     * 设置颜色
     *
     * @param color
     */
    public void setColor(int color) {
        mLoadingView.setColor(color);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void onPullingDown(float fraction, float maxHeadHeight, float headHeight) {
        if (fraction < 1) {
            mLoadingView.setScale(fraction);
        } else {
            mLoadingView.setScale(1);
        }
    }

    @Override
    public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight) {
        if (fraction < 1) {
            mLoadingView.setScale(fraction);
        }
    }

    @Override
    public void startAnim(float maxHeadHeight, float headHeight) {
        mLoadingView.startAnim();
    }

    @Override
    public void onFinish(OnAnimEndListener animEndListener) {
        animEndListener.onAnimEnd();
    }

    @Override
    public void reset() {
        mLoadingView.stopAnim();
    }
}
