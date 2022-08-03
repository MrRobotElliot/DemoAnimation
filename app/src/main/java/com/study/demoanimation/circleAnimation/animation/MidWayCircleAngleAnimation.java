package com.study.demoanimation.circleAnimation.animation;

import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.study.demoanimation.circleAnimation.view.LoadingCircle;

/**
 * @Author: elliotalderson
 * @Date: 2022/8/1 17:27
 * @Email: yy982107287@163.com
 * @Phone: 18268396056
 * @Version: 1.0
 * @Desciption: TODO
 **/
public class MidWayCircleAngleAnimation extends Animation {
    private static final String TAG = MidWayCircleAngleAnimation.class.getSimpleName();
    private LoadingCircle loadingCircle;
    private float oldStartAnglePoint;
    private float newStartAnglePoint;
    private float oldAngle;
    private float newAngle;


    public MidWayCircleAngleAnimation(LoadingCircle loadingCircle) {
        this.oldAngle = loadingCircle.getAngle();
        this.newAngle = 360;
        this.loadingCircle = loadingCircle;
        this.oldStartAnglePoint = loadingCircle.getStartAnglePoint();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
        setMidWayAnglgeMove(interpolatedTime);
    }


    private void setMidWayAnglgeMove(float interpolatedTime) {
        float midAngle = LoadingCircle.MIDWAY_LEN;
        float angle = getChangedAngle(interpolatedTime, midAngle);
        float start = getChangedStartPoint(interpolatedTime, 360 + 90);
        loadingCircle.setStartAnglePoint(start);
        loadingCircle.setAngle(angle);
        loadingCircle.requestLayout();
    }


    private float getChangedAngle(float interpolatedTime, float newAngle) {
        return oldAngle + ((newAngle - oldAngle) * interpolatedTime);
    }


    private float getChangedStartPoint(float interpolatedTime, float newStart) {
        return oldStartAnglePoint + ((newStart - oldStartAnglePoint) * interpolatedTime);
    }



}