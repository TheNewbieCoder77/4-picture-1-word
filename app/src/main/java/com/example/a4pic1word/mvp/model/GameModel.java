package com.example.a4pic1word.mvp.model;

import android.widget.RelativeLayout;

import com.example.a4pic1word.data.model.Question;
import com.example.a4pic1word.domain.Repository;
import com.example.a4pic1word.mvp.contract.GameContract;

public class GameModel implements GameContract.Model {
    private final Repository repository;

    public GameModel(){
        repository = Repository.getInstance();
    }


    @Override
    public Question getQuestionData() {
        return repository.getCurrentQuestion();
    }

    @Override
    public int getCurrentQuestionPos() {
        return repository.getPos();
    }

    @Override
    public int getQuestionCount() {
        return repository.getQuestionsCount();
    }

    @Override
    public void saveCurrentQuestionPos(int pos) {
        repository.savePos(pos);
    }

    @Override
    public int getCoins() {
        return repository.getCoins();
    }

    @Override
    public void saveCoins(int coins) {
        repository.saveCoins(coins);
    }
}
