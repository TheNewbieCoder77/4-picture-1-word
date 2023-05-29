package com.example.a4pic1word.mvp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ImageView;

import com.example.a4pic1word.R;
import com.example.a4pic1word.data.source.MyPref;
import com.example.a4pic1word.domain.Repository;
import com.example.a4pic1word.mvp.contract.MainContract;
import com.example.a4pic1word.mvp.model.MainModel;
import com.example.a4pic1word.mvp.presenter.MainPresenter;
import com.example.a4pic1word.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View{
    private List<ImageView> images;
    private AppCompatTextView level;
    private AppCompatButton play;
    private AppCompatButton about;
    private MainContract.Presenter presenter;
    private AppCompatButton quit;
    private long mLastClickTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        presenter = new MainPresenter(this);
        clickEvents();


    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadCurrentQuestion();
    }

    private void findViews(){
        images = new ArrayList<>(Util.MAX_IMAGES_COUNT.value);
        images.add(findViewById(R.id.image1));
        images.add(findViewById(R.id.image2));
        images.add(findViewById(R.id.image3));
        images.add(findViewById(R.id.image4));

        level = findViewById(R.id.textQuestionPos);
        play = findViewById(R.id.buttonPlay);
        about = findViewById(R.id.buttonAbout);
        quit = findViewById(R.id.buttonQuit);

    }

    private void clickEvents(){
        play.setOnClickListener( v ->{
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                return;
            }
            mLastClickTime = SystemClock.elapsedRealtime();
            presenter.clickedStartButton();
        });

        about.setOnClickListener(v ->{
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                return;
            }
            mLastClickTime = SystemClock.elapsedRealtime();
            presenter.clickedAboutButton();
        });

        quit.setOnClickListener(v ->{
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                return;
            }
            mLastClickTime = SystemClock.elapsedRealtime();
            presenter.clickedQuitButton();
        });
    }

    @Override
    public void openGameActivity() {
        startActivity(new Intent(this, GameView.class));
    }

    @Override
    public void openAboutActivity() {
        startActivity(new Intent(this,AboutView.class));
    }

    @Override
    public void quitGame() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.bg_quit_dialog);
        dialog.getWindow().setBackgroundDrawable(null);
        dialog.show();

        AppCompatButton btnYes = dialog.findViewById(R.id.btnQuitYes);
        AppCompatButton btnNo = dialog.findViewById(R.id.btnQuitNo);

        btnYes.setOnClickListener(v ->{
            if (SystemClock.elapsedRealtime() - mLastClickTime < 500){
                return;
            }
            mLastClickTime = SystemClock.elapsedRealtime();
            dialog.dismiss();
            finish();
        });

        btnNo.setOnClickListener(v ->{
            if (SystemClock.elapsedRealtime() - mLastClickTime < 500){
                return;
            }
            mLastClickTime = SystemClock.elapsedRealtime();
            dialog.dismiss();
        });
    }

    @Override
    public void showCurrentPositionOfQuestion(int pos) {
        level.setText(String.valueOf(pos+1));
    }

    @Override
    public void showImagesOfCurrentQuestion(List<Integer> images) {
        for (int i = 0; i < Util.MAX_IMAGES_COUNT.value; i++) {
            this.images.get(i).setImageResource(images.get(i));
        }
    }





}