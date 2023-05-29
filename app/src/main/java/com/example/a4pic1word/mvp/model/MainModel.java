package com.example.a4pic1word.mvp.model;

import com.example.a4pic1word.domain.Repository;
import com.example.a4pic1word.mvp.contract.MainContract;

import java.util.List;

public class MainModel implements MainContract.Model {
    private Repository repository;

    public MainModel() {
        this.repository = Repository.getInstance();
    }

    @Override
    public int getPos() {
        return repository.getPos();
    }

    @Override
    public List<Integer> getCurrentQuestionImages() {
        return repository.getCurrentQuestionImages();
    }


}
