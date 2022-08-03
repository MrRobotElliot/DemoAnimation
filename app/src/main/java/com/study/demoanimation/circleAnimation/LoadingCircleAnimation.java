package com.study.demoanimation.circleAnimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;

import androidx.annotation.NonNull;

import com.study.demoanimation.R;
import com.study.demoanimation.circleAnimation.help.AnimationHelp;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: elliotalderson
 * @Date: 2022/8/3 14:08
 * @Email: yy982107287@163.com
 * @Phone: 18268396056
 * @Version: 1.0
 * @Desciption: TODO
 **/
public class LoadingCircleAnimation extends View {
    public static final int MIDWAY_LEN_PERCENTAGE = 25;
    private Context context;
    /**
     * 画笔对象的引用
     */
    private final Paint bgPaint;
    private final Paint bgCriPaint;
    private final Paint paint;

    /**
     * 圆环的宽度
     */
    private float roundWidth = 10;

    /**
     * 移动
     */
    Scroller scroller;

    private static final int LIMIT_TIME = 600;

    private final float multiInterval = 0.75f;

    public LoadingCircleAnimation(Context context) {
        this(context, null);
        this.context = context;
    }

    public LoadingCircleAnimation(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.context = context;
    }

    public LoadingCircleAnimation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
        this.context = context;
        bgPaint = initBgPaint(context);
        bgCriPaint = initBgCriPaint(context);
        paint = initCirclePaint(context);

//  AccelerateInterpolator localAccelerateInterpolator = new AccelerateInterpolator();
//  this.scroller = new Scroller(context, localAccelerateInterpolator);
    }

    @NonNull
    private Paint initBgPaint(Context context) {
        final Paint bgPaint;
        bgPaint = new Paint();
        bgPaint.setAntiAlias(true); // 消除锯齿
        bgPaint.setStyle(Paint.Style.STROKE);// 设置空心
        bgPaint.setStrokeWidth(roundWidth);// 设置圆环的宽度
        bgPaint.setColor(context.getColor(R.color.blue)); // 设置圆环的颜色
        return bgPaint;
    }

    @NonNull
    private Paint initBgCriPaint(Context context) {
        final Paint bgCriPaint;
        bgCriPaint = new Paint();
        bgCriPaint.setAntiAlias(true);
        bgCriPaint.setStyle(Paint.Style.STROKE);
        bgCriPaint.setStrokeWidth(roundWidth);
        bgCriPaint.setColor(context.getColor(R.color.blue_bg_circle));
        return bgCriPaint;
    }

    @NonNull
    private Paint initCirclePaint(Context context) {
        final Paint paint;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(roundWidth);
        paint.setColor(context.getColor(R.color.white));
        paint.setStrokeCap(Paint.Cap.ROUND); // 设置画笔两头为圆角
        return paint;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        // super.onDraw(canvas);
        float centreX = getWidth() / 2; // 获取圆心的x坐标
        float radius = centreX; // 圆环的半径：原来 = centreX - roundWidth / 2

        drawRound(canvas, true, centreX, radius);
        drawBgCircle(canvas, centreX, radius);
        drawArc(canvas, centreX, radius);
    }

    private void drawRound(Canvas canvas, boolean choose, float centreX, float radius) {
        if (choose) {
            bgPaint.setStyle(Paint.Style.FILL); //设置实心圆
        } else {
            bgPaint.setStyle(Paint.Style.STROKE);  //设置空心圆
        }
        canvas.drawCircle(centreX, centreX, radius, bgPaint);
    }

    private void drawBgCircle(Canvas canvas, float centreX, float radius) {
        canvas.drawCircle(centreX, centreX, radius - roundWidth * multiInterval, bgCriPaint); // 画出圆环
    }

    private void drawArc(Canvas canvas, float centreX, float radius) {
        // 用于定义的圆弧的形状和大小的界限.指定圆弧的外轮廓矩形区域
        // 椭圆的上下左右四个点(View 左上角为0)
        RectF oval = new RectF();
        oval.set(
                centreX - radius + roundWidth * multiInterval,
                centreX - radius + roundWidth * multiInterval,
                centreX + radius - roundWidth * multiInterval,
                centreX + radius - roundWidth * multiInterval);
        canvas.drawArc(oval, startPoint, progress, false, paint);//画圆弧
    }


    public static final String PROGRESS = "progress";
    public static final String START_POINT = "startPoint";

    protected float progress;
    protected float startPoint;


    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress * 360 / 100;
        invalidate();// UI thread
    }

    public float getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(float startPoint) {
        this.startPoint = startPoint * 360 / 100;
        invalidate();// UI thread
        this.startPoint = startPoint;
    }

    AnimatorSet set;

    public void startAnimation() {
        initTimer();


        //也可使用3.0的AnimationSet实现
//  AnimationSet set = new AnimationSet(true);
//  set.setRepeatCount(AnimationSet.INFINITE);
//  set.setInterpolator(new AccelerateDecelerateInterpolator());
//  set.start();
//  this.setAnimation(set);

        //4.0以上，在AnimationSet基础上封装的，遗憾的是没有Repeat

        ObjectAnimator firtProgressAnim = ObjectAnimator.ofFloat(this, PROGRESS, 0f, MIDWAY_LEN_PERCENTAGE);
        firtProgressAnim.setDuration(LIMIT_TIME);// 动画执行时间
        ObjectAnimator firtStartAnim = ObjectAnimator.ofFloat(this, START_POINT, 90F, AnimationHelp.FIST_END);
        firtStartAnim.setDuration(LIMIT_TIME);

        ObjectAnimator midProgressAnim = ObjectAnimator.ofFloat(this, PROGRESS, MIDWAY_LEN_PERCENTAGE, MIDWAY_LEN_PERCENTAGE);
        midProgressAnim.setDuration(LIMIT_TIME / 2);
        ObjectAnimator midStartAnim = ObjectAnimator.ofFloat(this, START_POINT, AnimationHelp.FIST_END, AnimationHelp.FIST_TO_RIGHT);
        midStartAnim.setDuration(LIMIT_TIME / 2);


        ObjectAnimator finalProgressAnim = ObjectAnimator.ofFloat(this, PROGRESS, MIDWAY_LEN_PERCENTAGE, 0f);
        finalProgressAnim.setDuration(LIMIT_TIME / 2);
        ObjectAnimator finalStartAnim = ObjectAnimator.ofFloat(this, START_POINT, AnimationHelp.FIST_TO_RIGHT, AnimationHelp.FIST_TO_LEFT);
        finalStartAnim.setDuration(LIMIT_TIME / 2);

        /*
         * AccelerateInterpolator           加速，开始时慢中间加速
         * DecelerateInterpolator           减速，开始时快然后减速
         * AccelerateDecelerateInterolator       先加速后减速，开始结束时慢，中间加速
         * AnticipateInterpolator            反向 ，先向相反方向改变一段再加速播放
         * AnticipateOvershootInterpolator      反向加超越，先向相反方向改变，再加速播放，会超出目的值然后缓慢移动至目的值
         * BounceInterpolator             跳跃，快到目的值时值会跳跃，如目的值100，后面的值可能依次为85，77，70，80，90，100
         * CycleIinterpolator              循环，动画循环一定次数，值的改变为一正弦函数：Math.sin(2 *
         * mCycles * Math.PI * input) LinearInterpolator    线性，线性均匀改变
         * OvershottInterpolator            超越，最后超出目的值然后缓慢改变到目的值
         * TimeInterpolator                一个接口，允许你自定义interpolator，以上几个都是实现了这个接口
         */
        //动画同时执行,可以做多个动画

        set.playSequentially(firtProgressAnim, midProgressAnim, finalProgressAnim);
        set.playTogether(firtProgressAnim, firtStartAnim);
        set.playTogether(midProgressAnim, midStartAnim);
        set.playTogether(finalProgressAnim, finalStartAnim);
        set.setInterpolator(new LinearInterpolator());
        animTimer.schedule(animTask, 0, new Double(LIMIT_TIME * 0.55).longValue());
    }

    private void initTimer() {
        if (set == null) {
            set = new AnimatorSet();
        }
        if (animTimer == null) {
            animTimer = new Timer();
        }
        if (animHandler == null) {
            animHandler = new AnimHandler();
        }
        if(animTask == null){
            animTask = new TimerTask() {
                @Override
                public void run() {
                    Message msg = new Message();
                    animHandler.sendMessage(msg);
                }
            };
        }
    }

    Timer animTimer;
    AnimHandler animHandler;
    TimerTask animTask;


    class AnimHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            set.start();
        }
    }

    public void stopAnimation() {
        if (null != set) {
            set.cancel();
            set = null;
        }
        if (null != animTask) {
            animTask.cancel();
            animTask = null;
        }
        if (null != animTimer) {
            animTimer.cancel();
            animTimer = null;
        }
    }

}