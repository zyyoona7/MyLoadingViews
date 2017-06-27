package com.zyyoona7.loading.view.progress.uc;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by zyyoona7 on 2017/6/27.
 * UC背景Drawable
 */

public class ProgressDrawable extends Drawable {

    private static final String TAG = "ProgressDrawable";
    private Paint mPaint;
    private float progress;

    public ProgressDrawable(int color) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(color);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        clearColorFilter();
        RectF rectF = new RectF();
        rectF.set(getBounds());
        canvas.drawArc(rectF, -90f, progress, true, mPaint);
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidateSelf();
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
