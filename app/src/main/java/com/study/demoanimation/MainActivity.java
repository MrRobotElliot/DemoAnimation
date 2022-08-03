package com.study.demoanimation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.study.demoanimation.circleAnimation.LoadingCircleAnimation;
import com.study.demoanimation.circleAnimation.animation.AudioCacheLoadingAnimation;
import com.study.demoanimation.circleAnimation.view.LoadingCircle;

public class MainActivity extends AppCompatActivity {

    private LoadingCircle anLoadingCircle;
    private Button start;
    private LoadingCircle animationCircle;
    private Button hide;
    private Button playBtn;
    private LoadingCircleAnimation loadCirAni;

    private static final int LIMIT_TIME = 600;
    private AudioCacheLoadingAnimation loadingAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @SuppressLint("ObjectAnimatorBinding")
    private void initView() {
        start = (Button) findViewById(R.id.start);
        hide = (Button) findViewById(R.id.hide);
        anLoadingCircle = (LoadingCircle) findViewById(R.id.animationCircle);
        loadCirAni = (LoadingCircleAnimation) findViewById(R.id.newCirlcle);
        playBtn = (Button) findViewById(R.id.play_btn);


        start.setOnClickListener(v -> {
            loadingAnimation = AudioCacheLoadingAnimation.createAnima(animationCircle).startAnimation();
            anLoadingCircle.setVisibility(View.VISIBLE);
            playBtn.setVisibility(View.GONE);

            loadCirAni.setVisibility(View.VISIBLE);
            loadCirAni.startAnimation();
        });
        animationCircle = (LoadingCircle) findViewById(R.id.animationCircle);
        hide.setOnClickListener(v -> {
            loadingAnimation.clearAnimation();
            anLoadingCircle.setVisibility(View.GONE);
            playBtn.setVisibility(View.VISIBLE);

            loadCirAni.stopAnimation();
            loadCirAni.setVisibility(View.GONE);
        });


    }


}