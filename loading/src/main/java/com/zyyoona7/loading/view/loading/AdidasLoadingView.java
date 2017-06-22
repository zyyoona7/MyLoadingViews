package com.zyyoona7.loading.view.loading;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.zyyoona7.loading.view.BaseView;

import java.util.ArrayList;

/**
 * Created by zyyoona7 on 2017/6/22.
 * 类似腾讯视频的LoadingView
 */

public class AdidasLoadingView extends BaseView {
    public static final float SCALE = 1.0f;

    float[] scaleFloats = new float[]{SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,};
    private Paint mPaint;
    private Matrix mMatrix;

    private ArrayList<ValueAnimator> mAnimators;

    public AdidasLoadingView(Context context) {
        super(context);
    }

    public AdidasLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(DEFAULT_COLOR);
        mPaint.setStyle(Paint.Style.FILL);
        //利用Matrix 设置倾斜 x向左倾斜
        mMatrix = new Matrix();
        mMatrix.preSkew(-0.25f, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float translateX = getWidth() / 7;
        float translateY = getHeight() / 2;
        for (int i = 0; i < 3; i++) {
            canvas.save();
            canvas.translate((2 + i * 2) * translateX - translateX / 2, translateY);
            canvas.scale(scaleFloats[i], scaleFloats[i]);
            canvas.concat(mMatrix);
            RectF rectF = new RectF(-translateX / 2, -getHeight() / 2.5f, translateX / 2, getHeight() / 2.5f);
            canvas.drawRoundRect(rectF, 5, 5, mPaint);
            canvas.restore();
        }
    }

    /**
     * 创建动画
     *
     * @param time
     */
    private void createAnimators(long time) {
        mAnimators = new ArrayList<>();
        long[] delays = new long[]{100, 200, 300};
        for (int i = 0; i < 3; i++) {
            final int index = i;
            ValueAnimator scaleAnim = ValueAnimator.ofFloat(1, 0.4f, 1);
            scaleAnim.setDuration(time);
            scaleAnim.setRepeatCount(-1);
            scaleAnim.setStartDelay(delays[i]);
            scaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    scaleFloats[index] = (float) animation.getAnimatedValue();
                    postInvalidate();
                }
            });
            scaleAnim.start();
            mAnimators.add(scaleAnim);
        }
    }

    private void stopAnimators() {
        if (mAnimators != null) {
            for (ValueAnimator animator : mAnimators) {
                if (animator != null && animator.isStarted()) {
                    animator.removeAllUpdateListeners();
                    animator.end();
                }
            }
        }
    }

    @Override
    public void setColor(int color) {

    }

    @Override
    public void stopAnim() {
//        super.stopAnim();
        stopAnimators();
    }

    @Override
    public void startAnim() {
        stopAnimators();
        createAnimators(500);
    }

    @Override
    public void startAnim(long time) {
        stopAnimators();
        createAnimators(time);
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
