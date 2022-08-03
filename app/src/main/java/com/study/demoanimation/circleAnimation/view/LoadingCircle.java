package com.study.demoanimation.circleAnimation.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.study.demoanimation.R;


/**
 * @Author: elliotalderson
 * @Date: 2022/8/1 17:25
 * @Email: yy982107287@163.com
 * @Phone: 18268396056
 * @Version: 1.0
 * @Desciption: TODO
 **/
public class LoadingCircle extends View {

    public static final int MIDWAY_LEN = 90;
    private final Paint paint;
    private final Paint bgPaint;
    private final Paint bgCriPaint;
    private float angle;
    private int width;
    private int strokeWidth = 10;


    private int cirLeft = strokeWidth;
    private int cirTop = strokeWidth;
    private int cirRight = strokeWidth;
    private int cirBottom = strokeWidth;

    private float startAnglePoint = 90;


    @RequiresApi(api = Build.VERSION_CODES.M)
    public LoadingCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        bgPaint = initBgPaint(context);
        bgCriPaint = initBgCriPaint(context);
        paint = initCirclePaint(context);
    }


    @NonNull
    private Paint initBgPaint(Context context) {
        final Paint bgPaint;
        bgPaint = new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setStrokeWidth(strokeWidth);
        bgPaint.setColor(context.getColor(R.color.blue));
        return bgPaint;
    }

    @NonNull
    private Paint initBgCriPaint(Context context) {
        final Paint bgCriPaint;
        bgCriPaint = new Paint();
        bgCriPaint.setAntiAlias(true);
        bgCriPaint.setStyle(Paint.Style.STROKE);
        bgCriPaint.setStrokeWidth(strokeWidth);
        bgCriPaint.setColor(context.getColor(R.color.blue_bg_circle));
        return bgCriPaint;
    }

    @NonNull
    private Paint initCirclePaint(Context context) {
        final Paint paint;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(context.getColor(R.color.white));
        paint.setStrokeCap(Paint.Cap.ROUND); // 设置画笔两头为圆角
        angle = 0;//Initial Angle (optional, it can be zero)
        return paint;
    }

    @Override

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        strokeWidth = width / 5;
        cirLeft = strokeWidth;
        cirTop = strokeWidth ;
        cirRight = width - strokeWidth;
        cirBottom = width - strokeWidth;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawYuan(canvas, true, width / 2, width / 2, width / 2, bgPaint);
        drawBgCircle(canvas);
        drawCircle(canvas);
    }

    private void drawCircle(Canvas canvas) {
        RectF rect = new RectF(cirLeft, cirTop, cirRight, cirBottom);
        canvas.drawArc(rect, startAnglePoint, angle, false, paint);
    }


    private void drawBgCircle(Canvas canvas) {
        RectF rect = new RectF(cirLeft, cirTop, cirRight, cirBottom);
        canvas.drawArc(rect, 0, 360, false, bgCriPaint);
    }

    private void drawYuan(Canvas canvas, boolean choose, float startX, float startY, float radius, Paint paint) {
        if (choose) {
            //设置实心圆
            paint.setStyle(Paint.Style.FILL);
        } else {
            //设置空心圆
            paint.setStyle(Paint.Style.STROKE);
        }
        //X、Y坐标，radius半径
        canvas.drawCircle(startX, startY, radius, paint);
    }


    public float getStartAnglePoint() {
        return startAnglePoint;
    }

    public void setStartAnglePoint(float startAnglePoint) {
        this.startAnglePoint = startAnglePoint;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }


}