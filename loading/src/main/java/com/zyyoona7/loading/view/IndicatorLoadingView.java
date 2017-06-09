package com.zyyoona7.loading.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.LinearInterpolator;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Created by zyyoona7 on 2017/6/8.
 * 仿iOS的菊花loading
 */

public class IndicatorLoadingView extends BaseView {

    private static final String TAG = "IndicatorLoadingView";
    //转圈模式
    public static final int TURN_AROUND = 0;
    //进度模式
    public static final int PROGRESS = 1;

    //画笔
    private Paint mPaint;
    //矩形
    private RectF mRectF;

    private ValueAnimator mValueAnimator;
    //当前进度
    private int mCurrentProgress = 1;
    //当前模式
    private int mCurrentMode = 0;

    //线宽
    private int mLineWidth = 4;

    //动画更新时的当前值，如果不变则不重绘（动画更新太快而值根本没变）
    private int currentValue = 0;

    private LinkedList<Integer> mColorList = null;

    //是否已经开转了
    private boolean isAround = false;

    public IndicatorLoadingView(Context context) {
        super(context);
        init();
    }

    public IndicatorLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mColorList = new LinkedList<>();
        mColorList.add(0xE7000000);
        mColorList.add(0xD2000000);
        mColorList.add(0xBD000000);
        mColorList.add(0xA8000000);
        mColorList.add(0x93000000);
        mColorList.add(0x7E000000);
        mColorList.add(0x69000000);
        mColorList.add(0x54000000);
        mColorList.add(0x3F000000);
        mColorList.add(0x2A000000);
        mColorList.add(0x15000000);
        mColorList.add(0x00000000);
        mLineWidth = dp2px(2);
        Log.e(TAG, "init: " + getNewColor(mColorList.getLast(), 0xFFFFFF));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        width = width > height ? height : width;
        setMeasuredDimension(width, width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        if (mRectF == null) {
            mRectF = new RectF(getWidth() / 2 - mLineWidth / 2, 0, getWidth() / 2 + mLineWidth / 2, getHeight() / 4);
        }

        if (mCurrentMode == PROGRESS) {
            mPaint.setColor(mColorList.get(0));
            for (int i = 0; i < mCurrentProgress; i++) {
                //使用圆角矩形，使得更圆润
                canvas.drawRoundRect(mRectF, mLineWidth / 2, mLineWidth / 2, mPaint);
                canvas.rotate(30, getWidth() / 2, getHeight() / 2);
            }
        } else {
            for (int i = 0; i < 12; i++) {
                mPaint.setColor(mColorList.get(i));
                //使用圆角矩形，使得更圆润
                canvas.drawRoundRect(mRectF, mLineWidth / 2, mLineWidth / 2, mPaint);
                canvas.rotate(30, getWidth() / 2, getHeight() / 2);
            }
            //如果没开始旋转时候重绘，不改变颜色顺序
            if (isAround) {
                //改变颜色顺序，每次重绘，第一个矩形的颜色为上一次的最后一个颜色
                mColorList.addFirst(mColorList.getLast());
                mColorList.removeLast();
            }
        }

        canvas.restore();
    }

    /**
     * 设置线条宽度
     *
     * @param lineWidth do
     */
    public void setLineWidth(int lineWidth) {
        this.mLineWidth = dp2px(lineWidth);
        invalidate();
    }

    /**
     * 设置view的颜色
     *
     * @param color
     */
    public void setColor(int color) {
        for (int i = 0; i < mColorList.size(); i++) {
            mColorList.set(i, getNewColor(mColorList.get(i), color));
        }
        invalidate();
    }

    /**
     * 设置模式
     *
     * @param mode
     */
    public void setMode(int mode) {
        this.mCurrentMode = mode;
    }

    /**
     * 设置当前进度
     *
     * @param currentProgress 1-12
     */
    public void setCurrentProgress(int currentProgress) {
        mCurrentMode = PROGRESS;
        if (currentProgress > 12) {
            currentProgress = 12;
        }
        this.mCurrentProgress = currentProgress;
        invalidate();
    }

    /**
     * 开始旋转
     */
    public void startAnim() {
        isAround = true;
        mCurrentMode = TURN_AROUND;
        mValueAnimator = ValueAnimator.ofInt(0, 12);
        mValueAnimator.setDuration(1000);
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.setRepeatMode(ValueAnimator.RESTART);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (currentValue != (int) (animation.getAnimatedValue())) {
                    currentValue = (int) (animation.getAnimatedValue());
                    invalidate();
                }
            }
        });
        mValueAnimator.start();
    }

    /**
     * 开始旋转
     *
     * @param duration 设置转1圈的动画时间
     */
    public void startAnim(int duration) {
        isAround = true;
        mCurrentMode = TURN_AROUND;
        mValueAnimator = ValueAnimator.ofInt(0, 12);
        mValueAnimator.setDuration(duration);
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.setRepeatMode(ValueAnimator.RESTART);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (currentValue != (int) (animation.getAnimatedValue())) {
                    currentValue = (int) (animation.getAnimatedValue());
                    invalidate();
                }
            }
        });
        mValueAnimator.start();
    }

    /**
     * 退出旋转
     */
    public void stopAnim() {
        isAround = false;
        currentValue = 0;
        if (mValueAnimator != null) {
            clearAnimation();
            mValueAnimator.setRepeatCount(0);
            mValueAnimator.end();
            mValueAnimator.cancel();
            mValueAnimator = null;
        }
    }

    /**
     * 重置color的排序
     */
    public void resetColorOrder() {
        Collections.sort(mColorList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                //转换成16进制字符串，有000000ff的情况会被转成ff，所以加格式约束，空格用0代替
                String hexString1 = String.format("%8s", Integer.toHexString(o1)).replace(" ", "0");
                hexString1 = hexString1.substring(0, 2);
                String hexString2 = String.format("%8s", Integer.toHexString(o2)).replace(" ", "0");
                hexString2 = hexString2.substring(0, 2);

                //只比较16进制的前两位
                int hex1 = Integer.parseInt(hexString1, 16);
                int hex2 = Integer.parseInt(hexString2, 16);

                if (hex1 < hex2) {
                    return 1;
                } else if (hex1 > hex2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        invalidate();
    }

    /**
     * 获取新的color
     *
     * @param oldColor 用于提取保存的alpha通道
     * @param color
     * @return
     */
    private int getNewColor(int oldColor, int color) {
        int newColor = 0;
        int alpha = Math.round(Color.alpha(oldColor));
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        newColor = Color.argb(alpha, r, g, b);
        return newColor;
    }
}
