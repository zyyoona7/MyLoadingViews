package com.zyyoona7.loading.view.progress;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyyoona7 on 2017/6/28.
 * 朋友圈LoadingView（新的实现方式）
 */

public class FriendCircleLoadingView extends BaseProgressView {

    //填充区域数
    private static final int AREAS_NUMBER = 8;
    //画布旋转角度
    private static final int ROTATE_DEGREE = 45;

    private Paint mPaint;
    private List<Integer> mColorList;
    private float degree;
    private int mStrokeColor = Color.WHITE;
    private int mStrokeWidth = DEFAULT_PAINT_WIDTH;

    public FriendCircleLoadingView(Context context) {
        super(context);
    }

    public FriendCircleLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int setMaxProgress() {
        return 1080;
    }

    @Override
    protected void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(DEFAULT_COLOR);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(dp2px(mStrokeWidth));
        mColorList = new ArrayList<>(8);
        mColorList.add(Color.parseColor("#EE5C42"));
        mColorList.add(Color.parseColor("#9B30FF"));
        mColorList.add(Color.parseColor("#436EEE"));
        mColorList.add(Color.parseColor("#63B8FF"));
        mColorList.add(Color.parseColor("#00EE00"));
        mColorList.add(Color.parseColor("#7CFC00"));
        mColorList.add(Color.parseColor("#EEB422"));
        mColorList.add(Color.parseColor("#EE7942"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float space = 10;
        float x = getWidth() / 2;
        float y = getHeight() / 2;
        //将原点移动到中心
        canvas.translate(x, y);
        if (mCurrentMode == MODE_PROGRESS) {
            //旋转动画
            canvas.rotate(-mCurrentProgress);
        } else {
            canvas.rotate(degree);
        }
        //小圆半径，little radius
        float lr = x / 3;
        //大圆半径，large Radius
        float lR = x - space;
        //内圆与x轴垂直切点，到外圆的距离
        //x*x+y*y=lR*lR -->x=lr
        float dy = (float) Math.sqrt(lR * lR - lr * lr);
        //小圆45°切点到大圆的交点 x轴坐标
        //因为是45°，从交点垂直y轴做直线，圆心连接交点，平分两个直角三角形
        //根据lR计算x轴坐标
        float dx = (float) (Math.cos(Math.toRadians(22.5)) * lR);
        //cx为45°切点在x轴坐标
        //cy为45°切点在y轴坐标
        float cx = (float) (Math.cos(Math.toRadians(45)) * lr);
        float cy = (float) (Math.sin(Math.toRadians(45)) * lr);
        //创建path对象
        Path path = new Path();
        //设置画笔style为填充，以填充颜色
        mPaint.setStyle(Paint.Style.FILL);
        RectF rectF = new RectF(-x + space, -y + space, x - space, y - space);
        //保存画布当前状态
        canvas.save();
        //画出8个填充颜色区域
        for (int i = 0; i < AREAS_NUMBER; i++) {
            //取颜色
            if (i < mColorList.size()) {
                mPaint.setColor(mColorList.get(i));
            } else {
                //如果i>mColorList.size取最后一个颜色填充
                mPaint.setColor(mColorList.get(mColorList.size() - 1));
            }
            //重置path
            path.reset();
            //将path移动到小圆左侧，垂直于x轴的切点
            path.moveTo(-lr, 0);
            //连接到小圆垂直于x轴切点到大圆的交点
            path.lineTo(-lr, dy);
            //添加45°的弧度，以填充
            path.arcTo(rectF, 90, ROTATE_DEGREE);
            //连接小圆左侧45°切点到圆的交点
            path.lineTo(-dx, lr);
            //连接小圆左侧45°切点
            path.lineTo(-cx, -cy);
            //画出填充路径
            canvas.drawPath(path, mPaint);
            //旋转45°
            canvas.rotate(ROTATE_DEGREE);
        }
        //恢复之前状态
        canvas.restore();
        //颜色设置为白色
        mPaint.setColor(mStrokeColor);
        mPaint.setStrokeWidth(dp2px(mStrokeWidth));
        //设置style为描边
        mPaint.setStyle(Paint.Style.STROKE);
        //保存画布当前状态
        canvas.save();
        //画出8条描边的线
        for (int i = 0; i < AREAS_NUMBER; i++) {
            //重置path
            path.reset();
            //同上
            path.moveTo(-lr, 0);
            path.lineTo(-lr, dy);
            canvas.drawPath(path, mPaint);
            canvas.rotate(ROTATE_DEGREE);
        }
        //恢复画布状态
        canvas.restore();
        //画小圆
        canvas.drawCircle(0, 0, lr, mPaint);
        //画大圆
        canvas.drawCircle(0, 0, lR, mPaint);
    }

    @Override
    public void setColor(int color) {

    }

    /**
     * 设置描边宽度
     *
     * @param width
     */
    public void setStrokeWidth(int width) {
        this.mStrokeWidth = width;
        postInvalidate();
    }

    /**
     * 设置描边颜色
     *
     * @param color
     */
    public void setStrokeColor(int color) {
        this.mStrokeColor = color;
        postInvalidate();
    }

    /**
     * 设置填充颜色
     *
     * @param colors
     */
    public void setFillColor(int... colors) {
        mColorList.clear();
        for (int i = 0; i < colors.length; i++) {
            mColorList.add(colors[i]);
        }
        postInvalidate();
    }

    @Override
    public void startAnim() {
        stopAnim();
        setMode(MODE_ROTATE);
        startAnim(0, 360, 1000);
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
