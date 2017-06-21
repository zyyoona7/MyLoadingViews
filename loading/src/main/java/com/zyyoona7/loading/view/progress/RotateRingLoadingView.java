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
 * Created by zyyoona7 on 2017/6/13.
 * 圆环的loading，Android版腾讯体育下拉刷新的效果
 */

public class RotateRingLoadingView extends BaseProgressView {

    private RectF mRectF;

    private Paint mPaint;

    private float degrees = 0f;

    public RotateRingLoadingView(Context context) {
        this(context, null);
    }

    public RotateRingLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initPaint() {
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(dp2px(DEFAULT_PAINT_WIDTH));
        mPaint.setColor(DEFAULT_COLOR);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float circleSpacing = 10;
        float x = (getWidth()) / 2;
        float y = (getHeight()) / 2;
        //将旋转中心移动到view的中心
        canvas.translate(x, y);
        if (mRectF == null) {
            //有边距，否则圆弧会不完全
            mRectF = new RectF(-x + circleSpacing, -y + circleSpacing, x - circleSpacing, y - circleSpacing);
        }
        if (mCurrentMode == MODE_PROGRESS) {
            //负角度逆时针画，正角度顺时针画
            canvas.drawArc(mRectF, 300, -mCurrentProgress, false, mPaint);
        } else {
            //-degrees逆时针旋转，degrees顺时针
            canvas.rotate(-degrees);
            canvas.drawArc(mRectF, 300, -330, false, mPaint);
        }
    }

    /**
     * 设置圆环的宽度
     *
     * @param width dp
     */
    public void setStrokeWidth(float width) {
        mPaint.setStrokeWidth(dp2px(width));
        postInvalidate();
    }

    @Override
    public void setColor(int color) {
        mPaint.setColor(color);
        postInvalidate();
    }

    @Override
    public void startAnim() {
        stopAnim();
        setMode(MODE_ROTATE);
        startAnim(0, 360, 500);
    }

    @Override
    public void startAnim(long time) {
        stopAnim();
        setMode(MODE_ROTATE);
        startAnim(0, 360, time);
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
        degrees = (float) animator.getAnimatedValue();
        postInvalidate();
    }

    @Override
    protected int setMaxProgress() {
        return 330;
    }

}
