package com.mukul.finddoctor.Activity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.mukul.finddoctor.R;
import com.mukul.finddoctor.Utils.SessionManager;

import static com.mukul.finddoctor.Data.Data.TYPE_DOCTOR;
import static com.mukul.finddoctor.Data.Data.TYPE_PATIENT;


public class SplashActivity extends Activity {
    SessionManager sessionManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sessionManager = new SessionManager(SplashActivity.this);


        View mSplashImage = findViewById(R.id.splash);
        TextView mSplashText = findViewById(R.id.splashText);
        Animation splashAnimImage = AnimationUtils.loadAnimation(this, R.anim.splash_anim_img);
        Animation splashAnimText = AnimationUtils.loadAnimation(this, R.anim.splash_anim);
        mSplashText.startAnimation(splashAnimText);
        mSplashImage.startAnimation(splashAnimImage);

        splashAnimImage.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (sessionManager.getLoggedIn()) {

                    {
                        if (sessionManager.getUserType().equals(TYPE_DOCTOR)){
                            startActivity(new Intent(SplashActivity.this, DrNewHomeActivity.class));
                            finish();
                        }else   if (sessionManager.getUserType().equals(TYPE_PATIENT)){
                            startActivity(new Intent(SplashActivity.this, PatientNewHome.class));
                            finish();
                        }else {
                            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                            finish();
                        }



                    }

                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();

                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }




    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}