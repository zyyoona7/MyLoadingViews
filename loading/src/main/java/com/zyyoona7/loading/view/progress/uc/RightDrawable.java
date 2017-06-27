package com.zyyoona7.loading.view.progress.uc;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by zyyoona7 on 2017/6/27.
 * 对勾Drawable
 */

public class RightDrawable extends Drawable {

    private Paint mPaint;
    private float degree;
    private ValueAnimator mValueAnimator;

    public RightDrawable(int color,float strokeWidth) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(color);
        mPaint.setStrokeWidth(strokeWidth);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Rect rect = getBounds();
        float x = rect.width() / 2;
        float y = rect.height() / 2;
        canvas.translate(x, y);

        canvas.rotate(-30 + degree);
        Path path = new Path();
        path.moveTo(-x / 3, -y/7);
        path.lineTo(-x / 10, y / 4);
        path.lineTo(x / 2, -y / 4);
        canvas.drawPath(path, mPaint);
    }

    public void startAnim(Animator.AnimatorListener listener) {
        mValueAnimator = ValueAnimator.ofFloat(0f, 30f);
        mValueAnimator.setDuration(100);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                degree = (float) animation.getAnimatedValue();
                invalidateSelf();
            }
        });
        mValueAnimator.addListener(listener);
        mValueAnimator.start();
    }

    public void stopAnim(){
        if (mValueAnimator != null) {
            mValueAnimator.cancel();
            mValueAnimator=null;
        }
    }

    /**
     * 重置角度
     */
    public void resetDegree(){
        degree=0;
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
