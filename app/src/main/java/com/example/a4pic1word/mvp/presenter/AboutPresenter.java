package com.example.a4pic1word.mvp.presenter;

import com.example.a4pic1word.mvp.contract.AboutContract;

public class AboutPresenter implements AboutContract.Presenter {
    private AboutContract.View view;
    public AboutPresenter(AboutContract.View view){
        this.view = view;
    }
    @Override
    public void finishActivity() {
        view.closeActivity();
    }
}
