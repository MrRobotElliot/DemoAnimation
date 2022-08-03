package com.study.demoanimation.circleAnimation.animation;

import android.view.animation.Animation;

import com.study.demoanimation.circleAnimation.view.LoadingCircle;

/**
 * @Author: elliotalderson
 * @Date: 2022/8/2 16:25
 * @Email: yy982107287@163.com
 * @Phone: 18268396056
 * @Version: 1.0
 * @Desciption: TODO
 **/
public class AudioCacheLoadingAnimation {
    public static AudioCacheLoadingAnimation INSTANCE = null;
    private static final int LIMIT_TIME = 600;
    private LoadingCircle circle;
    MidWayCircleAngleAnimation midWayAn;

    public AudioCacheLoadingAnimation(LoadingCircle loadingCircle) {
        super();
        circle = loadingCircle;
        initAnimation();
    }


    private void initAnimation() {
        midWayAn = new MidWayCircleAngleAnimation(circle);
        midWayAn.setDuration(LIMIT_TIME);
        FinalCircleAngleToRightAnimation finalRigthAn = new FinalCircleAngleToRightAnimation(circle);
        finalRigthAn.setDuration(LIMIT_TIME / 2);
        FinalCircleAngleToLeftAnimation finalLeftAn = new FinalCircleAngleToLeftAnimation(circle);
        finalLeftAn.setDuration(LIMIT_TIME / 2);
        midWayAn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                circle.startAnimation(finalRigthAn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        finalRigthAn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                circle.startAnimation(finalLeftAn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        finalLeftAn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                circle.startAnimation(midWayAn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    public AudioCacheLoadingAnimation startAnimation() {
        circle.clearAnimation();
        circle.startAnimation(midWayAn);
        return this;
    }

    public AudioCacheLoadingAnimation clearAnimation() {
        circle.setAnimation(null);
        return this;
    }

    public static AudioCacheLoadingAnimation getInstance(LoadingCircle loadingCircle) {
        if (INSTANCE == null) {
            synchronized (AudioCacheLoadingAnimation.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AudioCacheLoadingAnimation(loadingCircle);
                }
            }
        }
        return INSTANCE;
    }


    public static AudioCacheLoadingAnimation createAnima(LoadingCircle loadingCircle) {
        return new AudioCacheLoadingAnimation(loadingCircle);
    }

}
