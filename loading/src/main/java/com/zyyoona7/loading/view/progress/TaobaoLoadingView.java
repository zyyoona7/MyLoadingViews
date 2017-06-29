package com.zyyoona7.loading.view.progress;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by zyyoona7 on 2017/6/21.
 */

public class TaobaoLoadingView extends BaseProgressView {

    private Paint mPaint;
    private Path mPath;
    private RectF mRectF;

    private float degree;

    public TaobaoLoadingView(Context context) {
        super(context);
    }

    public TaobaoLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected int setMaxProgress() {
        return 330;
    }

    @Override
    protected void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(DEFAULT_COLOR);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(dp2px(DEFAULT_PAINT_WIDTH-1));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float circleSpacing = 10;
        float x = getWidth() / 2;
        float y = getHeight() / 2;

        canvas.translate(x, y);

        if (mPath == null) {
            mRectF = new RectF(-x + circleSpacing, -y + circleSpacing, x - circleSpacing, y - circleSpacing);
            mPath = new Path();
            mPath.moveTo(0, -y / 3);
            mPath.lineTo(0, y / 3);
            // TODO: 2017/6/21 获取箭头的宽度 再减，防止出头
            mPath.moveTo(0,y/3-dp2px(DEFAULT_PAINT_WIDTH)/2);
            mPath.lineTo(x / 3, 0);
            mPath.moveTo(0, y / 3-dp2px(DEFAULT_PAINT_WIDTH)/2);
            mPath.lineTo(-x / 3, 0);
        }
        if (mCurrentMode == MODE_PROGRESS) {
            mPaint.setStrokeWidth(dp2px(DEFAULT_PAINT_WIDTH));
            canvas.drawPath(mPath, mPaint);
            mPaint.setStrokeWidth(dp2px(DEFAULT_PAINT_WIDTH-1));
            canvas.drawArc(mRectF, -75, mCurrentProgress, false, mPaint);
        } else {
            mPaint.setStrokeWidth(dp2px(DEFAULT_PAINT_WIDTH-1));
            canvas.rotate(degree);
            canvas.drawArc(mRectF, -75, 330, false, mPaint);
        }
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
        degree = (float) animator.getAnimatedValue();
        postInvalidate();
    }
}
