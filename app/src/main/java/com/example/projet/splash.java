package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class splash extends AppCompatActivity{
    ImageView logo,backg,lottie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow(). setFlags (WindowManager.LayoutParams. FLAG_FULLSCREEN, WindowManager. LayoutParams. FLAG_FULLSCREEN);

        logo = findViewById(R.id.appname);
        backg = findViewById(R.id.back);
        lottie= findViewById(R.id.lottie);
        backg.animate().translationY(-2500).setDuration(500).setStartDelay(5500);
        logo.animate().translationY(2000).setDuration(500).setStartDelay(5500);
        lottie.animate().translationY(2000).setDuration(500).setStartDelay(5500);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                startActivity(new Intent(splash.this,MainActivity.class));
            }
        },6000);
    }

}
