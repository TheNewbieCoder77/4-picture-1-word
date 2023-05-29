package com.example.a4pic1word.mvp.presenter;

import com.example.a4pic1word.mvp.contract.MainContract;
import com.example.a4pic1word.mvp.model.MainModel;

public class MainPresenter implements MainContract.Presenter{
    private MainContract.Model model;
    private MainContract.View view;

    public MainPresenter(MainContract.View view){
        this.view = view;
        model = new MainModel();
    }


    @Override
    public void clickedStartButton() {
        view.openGameActivity();
    }

    @Override
    public void clickedAboutButton() {
        view.openAboutActivity();
    }

    @Override
    public void clickedQuitButton() {
        view.quitGame();
    }

    @Override
    public void loadCurrentQuestion() {
        view.showCurrentPositionOfQuestion(model.getPos());
        view.showImagesOfCurrentQuestion(model.getCurrentQuestionImages());
    }


}
