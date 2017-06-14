package com.zyyoona7.loading.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by zyyoona7 on 2017/6/14.
 * 可以设置进度的LoadingView的基类
 */

public class BaseProgressView extends BaseView{

    public BaseProgressView(Context context) {
        this(context,null);
    }

    public BaseProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    // TODO: 2017/6/14 公共的方法 setProgress()等...
}
