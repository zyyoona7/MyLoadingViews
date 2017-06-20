package com.zyyoona7.loading.view.progress;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.zyyoona7.loading.view.BaseView;

/**
 * Created by zyyoona7 on 2017/6/14.
 * 可以设置进度的LoadingView的基类
 */

public abstract class BaseProgressView extends BaseView {

    //模式，设置进度模式、旋转模式
    protected static final int MODE_PROGRESS = 1;
    protected static final int MODE_ROTATE = 2;

    //当前的进度
    protected int mCurrentProgress = 0;

    //最大进度
    protected int mMaxProgress = 0;

    //当前模式
    protected int mCurrentMode = MODE_PROGRESS;

    public BaseProgressView(Context context) {
        this(context, null);
    }

    public BaseProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mMaxProgress = setMaxProgress();
    }

    /**
     * 设置当前模式
     *
     * @param mode
     */
    public void setMode(int mode) {
        this.mCurrentMode = mode;
    }

    /**
     * 设置当前进度
     *
     * @param currentProgress
     */
    public void setCurrentProgress(int currentProgress) {
        setMode(MODE_PROGRESS);
        if (currentProgress > mMaxProgress) {
            currentProgress = mMaxProgress;
        }
        this.mCurrentProgress = currentProgress;
        postInvalidate();
    }

    /**
     * 获取最大进度
     *
     * @return
     */
    public int getMaxProgress() {
        return mMaxProgress;
    }

    /**
     * 设置最大进度
     *
     * @return
     */
    protected abstract int setMaxProgress();

}
