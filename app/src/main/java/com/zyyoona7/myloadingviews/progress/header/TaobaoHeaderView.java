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
import com.zyyoona7.loading.view.progress.AliPayLoadingView;
import com.zyyoona7.loading.view.progress.TaobaoLoadingView;
import com.zyyoona7.myloadingviews.R;

/**
 * Created by zyyoona7 on 2017/6/21.
 */

public class TaobaoHeaderView extends FrameLayout implements IHeaderView {


    private TaobaoLoadingView mLoadingView;

    public TaobaoHeaderView(@NonNull Context context) {
        this(context, null);
    }

    public TaobaoHeaderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.header_taobao, this);
        mLoadingView = (TaobaoLoadingView) findViewById(R.id.tlv_header);
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
            mLoadingView.setCurrentProgress(Math.round(fraction * mLoadingView.getMaxProgress()));
        } else {
            mLoadingView.setCurrentProgress(mLoadingView.getMaxProgress());
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
