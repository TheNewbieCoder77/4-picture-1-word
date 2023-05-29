package com.example.a4pic1word.mvp.contract;

import java.util.List;

public interface MainContract {
    interface Model{
        int getPos();
        List<Integer> getCurrentQuestionImages();

    }
    interface View{
        void openGameActivity();
        void openAboutActivity();
        void quitGame();
        void showCurrentPositionOfQuestion(int pos);
        void showImagesOfCurrentQuestion(List<Integer> images);

    }
    interface Presenter{
        void clickedStartButton();
        void clickedAboutButton();
        void clickedQuitButton();
        void loadCurrentQuestion();
    }
}
