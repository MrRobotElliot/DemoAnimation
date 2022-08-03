package com.study.demoanimation.circleAnimation.animation;

import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.study.demoanimation.circleAnimation.help.AnimationHelp;
import com.study.demoanimation.circleAnimation.view.LoadingCircle;

/**
 * @Author: elliotalderson
 * @Date: 2022/8/1 17:27
 * @Email: yy982107287@163.com
 * @Phone: 18268396056
 * @Version: 1.0
 * @Desciption: TODO
 **/
public class FinalCircleAngleToRightAnimation extends Animation {
    private static final String TAG = FinalCircleAngleToRightAnimation.class.getSimpleName();
    private LoadingCircle loadingCircle;
    private float oldStartAnglePoint;
    private float newStartAnglePoint;
    private float oldAngle;
    private float newAngle;

    private final float BACK_FORTH_LEN = 45;

    public FinalCircleAngleToRightAnimation(LoadingCircle loadingCircle) {
        this.oldAngle = loadingCircle.getAngle();
        this.newAngle = 360;
        this.loadingCircle = loadingCircle;
        this.oldStartAnglePoint = AnimationHelp.FIST_END;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
        setFinalAngleMove(interpolatedTime);
    }

    private void setFinalAngleMove(float interpolatedTime) {
        float midAngle = LoadingCircle.MIDWAY_LEN;
        float angle = getChangedAngle(interpolatedTime, midAngle);
        float start = getChangedStartPoint(interpolatedTime, AnimationHelp.FIST_TO_RIGHT);
        loadingCircle.setStartAnglePoint(start);
        loadingCircle.setAngle(midAngle);
        loadingCircle.requestLayout();
    }

    private float getChangedAngle(float interpolatedTime, float newAngle) {
        return oldAngle + ((newAngle - oldAngle) * interpolatedTime);
    }


    private float getChangedStartPoint(float interpolatedTime, float newStart) {
        return oldStartAnglePoint - ((oldStartAnglePoint - newStart) * interpolatedTime);
    }

}