package com.example.a4pic1word.mvp.presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.a4pic1word.App.App;
import com.example.a4pic1word.data.model.Question;
import com.example.a4pic1word.mvp.contract.GameContract;
import com.example.a4pic1word.mvp.model.GameModel;
import com.example.a4pic1word.utils.Util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.sax.SAXResult;

public class GamePresenter implements GameContract.Presenter {
    private final GameContract.Model model;
    private final GameContract.View view;

    private List<String> typedAnswers;
    private List<Boolean> typedVariants;

    public GamePresenter(GameContract.View view){
        this.view = view;
        model = new GameModel();
    }


    @Override
    public void loadCurrentQuestion() {
        view.clearOldData();
        view.showQuestionImagesToView(model.getQuestionData().getImages());
        view.showVariantsToView(model.getQuestionData().getVariant());
        view.setVisibilityToAnswer(model.getQuestionData().getAnswer().length());
        view.showCoins(model.getCoins());
        view.showLevel(model.getCurrentQuestionPos()+1);

        initTypedArrays();
    }


    @Override
    public void answerBtnClick(int clickedPosition) {
        Question question = model.getQuestionData();
        String variant = question.getVariant();
        String typedLetter = typedAnswers.get(clickedPosition);//D

        for (int i = 0; i < Util.MAX_VARIANT_COUNT.value; i++) {
            if (String.valueOf(variant.charAt(i)).equals(typedLetter) && typedVariants.get(i)) {
                view.setEnabledVariantByPos(i, true);
                typedVariants.set(i, false);
                typedAnswers.set(clickedPosition, null);
                view.setTextToAnswer(clickedPosition, "");
                view.setDefaultColorToAnswers();
                return;
            }
        }
    }

    @Override
    public void variantBtnClick(int clickedPosition) {
        int positionAnswer = typedAnswers.indexOf(null);
        if (positionAnswer == -1) return;

        Question question = model.getQuestionData();

        String variant = question.getVariant();
        String letter = "" + variant.charAt(clickedPosition);
        view.setTextToAnswer(positionAnswer, letter);
        typedAnswers.set(positionAnswer, letter);
        view.setEnabledVariantByPos(clickedPosition, false);
        typedVariants.set(clickedPosition, true);

        isWin();
    }


    @Override
    public void finishActivity() {
        view.closeActivity();
    }

    @Override
    public void loadNextQuestion() {
        loadCurrentQuestion();
    }

    @Override
    public void clickedHint() {
        view.showHintDialog();
    }

    @Override
    public void clickedHintYes() {
        int coins = model.getCoins();
        if(coins < 5){
            Toast.makeText(App.context,"You have not enough coins!",Toast.LENGTH_SHORT).show();
            return;
        }

        String answer = model.getQuestionData().getAnswer();
        String variant = model.getQuestionData().getVariant();

        for (int i = 0; i < typedVariants.size(); i++) {
            if((!typedVariants.get(i)) && (!answer.contains(variant.charAt(i)+""))){
                view.setEnabledVariantByPos(i,false);
                typedVariants.set(i,true);
                model.saveCoins(model.getCoins() - 5);
                view.showCoins(model.getCoins());
                break;
            }
        }
    }



    private void initTypedArrays() {
        int answerSize = model.getQuestionData().getAnswer().length();
        typedAnswers = new ArrayList<>(answerSize);
        for (int i = 0; i < answerSize; i++) {
            typedAnswers.add(null);
        }

        typedVariants = new ArrayList<>(Util.MAX_VARIANT_COUNT.value);
        for (int i = 0; i < Util.MAX_VARIANT_COUNT.value; i++) {
            typedVariants.add(false);
        }
    }

    private void isWin() {
        Question question = model.getQuestionData();
        String answer = question.getAnswer();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < typedAnswers.size(); i++) {
            sb.append(typedAnswers.get(i));
        }

        String userAnswer = sb.toString();
        if(userAnswer.length() != answer.length()) return;

        if(userAnswer.equals(answer)){
            if(model.getQuestionCount() == model.getCurrentQuestionPos() + 1){
                model.saveCoins(model.getCoins()+7);
                saveQuestionPosition(0);
                view.showRestartDialog();
            }
            else{
                model.saveCoins(model.getCoins()+7);
                saveQuestionPosition(model.getCurrentQuestionPos() + 1);
                view.showDialog();
            }
        }
        else{
            view.setWrongColorToAnswers();
            view.wrongAnswerAnimation();
        }


    }

    private void saveQuestionPosition(int position){
        model.saveCurrentQuestionPos(position);
    }


}
