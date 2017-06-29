package com.zyyoona7.loading.view.progress;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.zyyoona7.loading.view.BaseView;

/**
 * Created by zyyoona7 on 2017/6/26.
 * 朋友圈LoadingView(最初实现思路，有的点计算不准确)
 */

public class FriendCircleLoadingViewOld extends BaseProgressView {

    private Paint mPaint;

    private float degree;

    private PointF fp1, fp2, tp1, tp2, sp1, sp2, firp1, firp2, lp1, lp2, lp3, lp4, lp5, lp6, lp7, lp8;

    public FriendCircleLoadingViewOld(Context context) {
        super(context);
    }

    public FriendCircleLoadingViewOld(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        startAnim();
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
        mPaint.setStrokeWidth(dp2px(DEFAULT_PAINT_WIDTH));
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        float space = 10;
        float x = getWidth() / 2;
        float y = getHeight() / 2;
        //将圆分成8份，45°
        //各个点坐标
        //小圆半径
        float lr = (x - space) / 3;
        //直角三角形边长公式a*a+b*b=c*c
        //a为切线到外圆的长度
        float a = (float) Math.sqrt((x - space) * (x - space) - lr * lr);
        //切点之一的坐标
        //dx为45°切点在x轴坐标
        //dy为45°切点在y轴坐标
        float dx = (float) (Math.cos(Math.toRadians(45)) * lr);
        float dy = (float) (Math.sin(Math.toRadians(45)) * lr);

        //切点到外圆后，外圆的交点坐标
        //每个象限两个点
        //左下
        fp1 = new PointF(-lr, a);
        fp2 = new PointF(-a, lr);
        //左上
        tp1 = new PointF(-a, -lr);
        tp2 = new PointF(-lr, -a);
        //右上
        sp1 = new PointF(lr, -a);
        sp2 = new PointF(a, -lr);
        //右下
        firp1 = new PointF(a, lr);
        firp2 = new PointF(lr, a);

        //切点坐标
        lp1 = new PointF(0, lr);
        lp2 = new PointF(-dx, dy);
        lp3 = new PointF(-lr, 0);
        lp4 = new PointF(-dx, -dy);
        lp5 = new PointF(0, -lr);
        lp6 = new PointF(dx, -dy);
        lp7 = new PointF(lr, 0);
        lp8 = new PointF(dx, dy);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float space = 10;
        float x = getWidth() / 2;
        float y = getHeight() / 2;
        canvas.translate(x, y);
        if (mCurrentMode == MODE_PROGRESS) {
            //旋转动画
            canvas.rotate(-mCurrentProgress);
        } else {
            canvas.rotate(degree);
        }

        Path path = new Path();
        RectF rectF = new RectF(-(x - space), -(y - space), (x - space), y - space);
        //设置填充
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        //填充颜色
        drawFillColorByPath(canvas, path, rectF);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        //外层圆环
        canvas.drawCircle(0, 0, x - space, mPaint);
        //画描边的线
        drawLines(canvas, path);
        //内层圆
        canvas.drawCircle(0, 0, (x - space) / 3, mPaint);
    }

    /**
     * 用path画出描边的线
     *
     * @param canvas
     * @param path
     */
    private void drawLines(Canvas canvas, Path path) {
        //画白色的线
        path.reset();
        //移动到内圆的切点
        path.moveTo(lp3.x, lp3.y);
        //连接到外圆的交点
        path.lineTo(fp1.x, fp1.y);
        canvas.drawPath(path, mPaint);

        path.reset();
        path.moveTo(lp4.x, lp4.y);
        path.lineTo(fp2.x, fp2.y);
        canvas.drawPath(path, mPaint);

        path.reset();
        path.moveTo(lp5.x, lp5.y);
        path.lineTo(tp1.x, tp1.y);
        canvas.drawPath(path, mPaint);

        path.reset();
        path.moveTo(lp6.x, lp6.y);
        path.lineTo(tp2.x, tp2.y);
        canvas.drawPath(path, mPaint);

        path.reset();
        path.moveTo(lp7.x, lp7.y);
        path.lineTo(sp1.x, sp1.y);
        canvas.drawPath(path, mPaint);

        path.reset();
        path.moveTo(lp8.x, lp8.y);
        path.lineTo(sp2.x, sp2.y);
        canvas.drawPath(path, mPaint);

        path.reset();
        path.moveTo(lp1.x, lp1.y);
        path.lineTo(firp1.x, firp1.y);
        canvas.drawPath(path, mPaint);

        path.reset();
        path.moveTo(lp2.x, lp2.y);
        path.lineTo(firp2.x, firp2.y);
        canvas.drawPath(path, mPaint);
    }

    /**
     * 通过path画出8中填充颜色
     *
     * @param canvas
     * @param path
     * @param rectF
     */
    private void drawFillColorByPath(Canvas canvas, Path path, RectF rectF) {
        //连线,填充区域
        mPaint.setColor(Color.parseColor("#EE5C42"));
        //移动到内圆切点
        path.moveTo(lp3.x, lp3.y);
        //连接到外圆交点
        path.lineTo(fp1.x, fp1.y);
        //添加圆弧以填充
        path.arcTo(rectF, 112.5f, 45);
        //连接到另一个外圆交点以闭合path
        path.lineTo(fp2.x, fp2.y);
        //连接到两一个内圆切点以闭合path
        path.lineTo(lp4.x, lp4.y);
        canvas.drawPath(path, mPaint);

        mPaint.setColor(Color.parseColor("#9B30FF"));
        path.reset();
        path.moveTo(lp4.x, lp4.y);
        path.lineTo(fp2.x, fp2.y);
        path.arcTo(rectF, 157.5f, 45);
        path.lineTo(tp1.x, tp1.y);
        path.lineTo(lp5.x, lp5.y);
        canvas.drawPath(path, mPaint);

        mPaint.setColor(Color.parseColor("#436EEE"));
        path.reset();
        path.moveTo(lp5.x, lp5.y);
        path.lineTo(tp1.x, tp1.y);
        path.arcTo(rectF, 202.5f, 45);
        path.lineTo(tp2.x, tp2.y);
        path.lineTo(lp6.x, lp6.y);
        canvas.drawPath(path, mPaint);

        mPaint.setColor(Color.parseColor("#63B8FF"));
        path.reset();
        path.moveTo(lp6.x, lp6.y);
        path.lineTo(tp2.x, tp2.y);
        path.arcTo(rectF, 247.5f, 45);
        path.lineTo(sp1.x, sp1.y);
        path.lineTo(lp7.x, lp7.y);
        canvas.drawPath(path, mPaint);

        mPaint.setColor(Color.parseColor("#00EE00"));
        path.reset();
        path.moveTo(lp7.x, lp7.y);
        path.lineTo(sp1.x, sp1.y);
        path.arcTo(rectF, 292.5f, 45);
        path.lineTo(sp2.x, sp2.y);
        path.lineTo(lp8.x, lp8.y);
        canvas.drawPath(path, mPaint);

        mPaint.setColor(Color.parseColor("#7CFC00"));
        path.reset();
        path.moveTo(lp8.x, lp8.y);
        path.lineTo(sp2.x, sp2.y);
        path.arcTo(rectF, 337.5f, 45);
        path.lineTo(firp1.x, firp1.y);
        path.lineTo(lp1.x, lp1.y);
        canvas.drawPath(path, mPaint);

        mPaint.setColor(Color.parseColor("#EEB422"));
        path.reset();
        path.moveTo(lp1.x, lp1.y);
        path.lineTo(firp1.x, firp1.y);
        path.arcTo(rectF, 22.5f, 45);
        path.lineTo(firp2.x, firp2.y);
        path.lineTo(lp2.x, lp2.y);
        canvas.drawPath(path, mPaint);

        mPaint.setColor(Color.parseColor("#EE7942"));
        path.reset();
        path.moveTo(lp2.x, lp2.y);
        path.lineTo(firp2.x, firp2.y);
        path.arcTo(rectF, 67.5f, 45);
        path.lineTo(fp1.x, fp1.y);
        path.lineTo(lp3.x, lp3.y);
        canvas.drawPath(path, mPaint);
    }


    @Override
    public void setColor(int color) {

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
