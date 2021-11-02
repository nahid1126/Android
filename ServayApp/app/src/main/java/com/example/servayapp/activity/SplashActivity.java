package com.example.servayapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.servayapp.R;

public class SplashActivity extends AppCompatActivity {

    ImageView splashImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashImg = findViewById(R.id.splashImg);
        YoYo.with(Techniques.BounceInDown)
                .duration(3000)
                .onEnd(animator -> {
                    try {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        SplashActivity.this.finish();
                    }
                }).playOn(splashImg);
    }
}