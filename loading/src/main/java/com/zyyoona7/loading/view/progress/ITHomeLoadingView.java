package com.zyyoona7.loading.view.progress;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by zyyoona7 on 2017/6/22.
 */

public class ITHomeLoadingView extends BaseProgressView {

    //画笔
    private Paint mPaint;
    //矩阵
    private Matrix mMatrix;
    //Camera，配合矩阵左各种旋转
    private Camera mCamera;

    //字体颜色
    private int mFontColor = Color.WHITE;
    //关键字
    private String mKeyword = "IT";
    //字体大小
    private float mFontSize = 15f;

    //主颜色
    private int mHomeColor = DEFAULT_COLOR;

    //测量字体边界的rect
    private Rect mRect = null;

    //画圆弧时需要
    private RectF mRectF = null;

    private float degree;

    //圆环与圆的间距
    private float mRingSpacing = DEFAULT_PAINT_WIDTH;

    //圆环宽度
    private float mStrokeWidth = DEFAULT_PAINT_WIDTH;

    public ITHomeLoadingView(Context context) {
        super(context);
    }

    public ITHomeLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int setMaxProgress() {
        return 360;
    }

    @Override
    protected void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        //粗体
        mPaint.setFakeBoldText(true);
        mPaint.setColor(DEFAULT_COLOR);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(dp2px(mStrokeWidth));
        //测量字体边界
        mRect = new Rect();
        mMatrix = new Matrix();
        mCamera = new Camera();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float spacing = 10;
        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;
        //画实心圆
        mPaint.setStrokeWidth(dp2px(mStrokeWidth));
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX, centerX, centerX - dp2px(mStrokeWidth) - spacing - dp2px(mRingSpacing), mPaint);

        mPaint.setTextSize(sp2px(mFontSize));
        mPaint.setColor(mFontColor);
        if (mRect == null) {
            mRect = new Rect();
        }
        mPaint.getTextBounds(mKeyword, 0, mKeyword.length(), mRect);
        //画文字，居中
        canvas.drawText(mKeyword, (getWidth() - mRect.width()) / 2, (getHeight() - mRect.height()) / 2 + mRect.height(), mPaint);

        if (mCurrentMode == MODE_PROGRESS) {
            //下拉过程
            mPaint.setColor(mHomeColor);
            mPaint.setStyle(Paint.Style.STROKE);
            if (mRectF == null) {
                //有边距，否则圆弧会不完全
                mRectF = new RectF(spacing, spacing, getWidth() - spacing, getHeight() - spacing);
            }
            canvas.drawArc(mRectF, 0, mCurrentProgress, false, mPaint);
        } else {
            //loading过程
            //矩阵+Camera 控制旋转
            mMatrix.reset();
            mCamera.save();
            mCamera.rotateY(degree);
            mCamera.getMatrix(mMatrix);
            mCamera.restore();

            mMatrix.preTranslate(-centerX, -centerY);
            mMatrix.postTranslate(centerX, centerY);
            canvas.concat(mMatrix);
            mPaint.setColor(mHomeColor);
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(centerX, centerX, centerX - spacing, mPaint);
        }
    }

    @Override
    public void setColor(int color) {
        this.mHomeColor = color;
        postInvalidate();
    }

    /**
     * 设置字体颜色
     *
     * @param color
     */
    public void setFontColor(int color) {
        this.mFontColor = color;
        postInvalidate();
    }

    /**
     * 设置字体大小
     *
     * @param fontSize
     */
    public void setFontSize(float fontSize) {
        this.mFontSize = fontSize;
        mPaint.setTextSize(mFontSize);
        postInvalidate();
    }

    /**
     * 设置绘画文字
     *
     * @param keyword
     */
    public void setKeyword(String keyword) {
        mKeyword = keyword;
        mPaint.getTextBounds(mKeyword, 0, mKeyword.length(), mRect);
        postInvalidate();
    }

    /**
     * 设置圆与圆环之间的间距
     *
     * @param spacing
     */
    public void setRingSpacing(float spacing) {
        this.mRingSpacing = spacing;
        postInvalidate();
    }

    /**
     * 设置圆环的宽度
     *
     * @param strokeWidth
     */
    public void setStrokeWidth(float strokeWidth) {
        this.mStrokeWidth = strokeWidth;
        postInvalidate();
    }

    @Override
    public void startAnim() {
        stopAnim();
        setMode(MODE_ROTATE);
        startAnim(0, 180, 500);
    }

    @Override
    public void startAnim(long time) {
        stopAnim();
        setMode(MODE_ROTATE);
        startAnim(0, 180, time);
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
