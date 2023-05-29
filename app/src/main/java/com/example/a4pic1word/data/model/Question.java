package com.example.a4pic1word.data.model;

import java.util.List;

public class Question {
    private List<Integer> images;
    private String answer;
    private String variant;

    public Question(List<Integer> images, String answer, String variant) {
        this.images = images;
        this.answer = answer;
        this.variant = variant;
    }

    public List<Integer> getImages() {
        return images;
    }

    public String getAnswer() {
        return answer;
    }

    public String getVariant() {
        return variant;
    }
}
