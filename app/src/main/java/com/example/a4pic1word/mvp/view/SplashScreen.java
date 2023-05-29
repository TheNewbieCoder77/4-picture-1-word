package com.example.a4pic1word.mvp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.a4pic1word.R;

import java.util.Timer;
import java.util.TimerTask;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {
    private Timer time;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2500);
    }
}