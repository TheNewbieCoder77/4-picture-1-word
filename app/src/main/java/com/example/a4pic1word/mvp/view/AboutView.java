package com.example.a4pic1word.mvp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;

import android.os.Bundle;

import com.example.a4pic1word.R;
import com.example.a4pic1word.mvp.contract.AboutContract;
import com.example.a4pic1word.mvp.contract.MainContract;
import com.example.a4pic1word.mvp.presenter.AboutPresenter;

public class AboutView extends AppCompatActivity implements AboutContract.View {
    private AppCompatImageButton btnBack;
    private AboutContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_view);
        findView();
        presenter = new AboutPresenter(this);
        clickEvent();

    }

    private void findView(){
        btnBack = findViewById(R.id.btnBackId);
    }

    private void clickEvent(){
        btnBack.setOnClickListener(v ->{
            presenter.finishActivity();
        });
    }

    @Override
    public void closeActivity() {
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        presenter.finishActivity();
    }
}