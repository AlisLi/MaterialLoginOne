package com.example.materialloginone;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Lizhiguo on 2017/10/20.
 */

public class RegisterActivity extends AppCompatActivity {
    private EditText etRegisterUserName;
    private EditText etRegisterPassword;
    private Button btnRegister;
    private CardView cvRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        etRegisterPassword = (EditText) findViewById(R.id.et_register_username);
        etRegisterPassword = (EditText) findViewById(R.id.et_login_password);
        btnRegister = (Button) findViewById(R.id.btn_register);
        cvRegister = (CardView) findViewById(R.id.register_card );

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ShowEnterAnimation();
        }


    }

    @Override
    public void onBackPressed() {
        animateRevealClose();
    }

    //显示进入动画
    private void ShowEnterAnimation() {
        //获取过渡对象,并且设置过渡动画
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.register_transition);
        getWindow().setSharedElementEnterTransition(transition);

        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                //将当前活动布局去掉，以动画形式加载
                cvRegister.setVisibility(View.GONE);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                //取消监听器
                transition.removeListener(this);
                //动画加载布局
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }

    //进入动画
    private void animateRevealShow(){
        //设置动画
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvRegister,cvRegister.getWidth()/2,0,cvRegister.getWidth()/8,cvRegister.getHeight());
        //设置动画时间
        mAnimator.setDuration(500);
        //设置动画变化速率(越来越快)
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                cvRegister.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

            }
        });
        mAnimator.start();
    }

    //退出动画
    private void animateRevealClose(){
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvRegister,cvRegister.getWidth()/2,0,cvRegister.getHeight(),0);
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                cvRegister.setVisibility(View.INVISIBLE);
                RegisterActivity.super.onBackPressed();
            }
        });
        mAnimator.start();
    }
}
