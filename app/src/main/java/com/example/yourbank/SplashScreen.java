package com.example.yourbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_SCREEN=5000;
    Animation top,bot;
    ImageView im;
    TextView mtext,mtext1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);
        top= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bot=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        im=findViewById(R.id.rupee);
        mtext=findViewById(R.id.splashtext);
        mtext1=findViewById(R.id.tag);
        im.setAnimation(top);
        mtext.setAnimation(bot);
        mtext1.setAnimation(bot);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this,SecondActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);


    }
}