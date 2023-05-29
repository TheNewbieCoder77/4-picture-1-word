package com.example.a4pic1word.mvp.contract;

import com.example.a4pic1word.data.model.Question;

import java.util.List;

public interface GameContract {
    interface Model{
        Question getQuestionData();
        int getCurrentQuestionPos();
        int getQuestionCount();
        void saveCurrentQuestionPos(int pos);

        int getCoins();
        void saveCoins(int coins);
    }

    interface Presenter{
        void loadCurrentQuestion();
        void answerBtnClick(int clickedPosition);
        void variantBtnClick(int clickedPosition);
        void finishActivity();
        void loadNextQuestion();
        void clickedHint();
        void clickedHintYes();
    }

    interface View{
        void showQuestionImagesToView(List<Integer> questionImages);
        void showCoins(int coins);
        void showVariantsToView(String variant);
        void showLevel(int _level);

        void setVisibilityToAnswer(int answerLength);

        void clearOldData();
        void closeActivity();
        void showDialog();

        void setTextToAnswer(int pos,String letter);

        void setEnabledVariantByPos(int pos, boolean state);
        void wrongAnswerAnimation();
        void setWrongColorToAnswers();
        void setDefaultColorToAnswers();
        void showRestartDialog();
        void showHintDialog();



    }
}
