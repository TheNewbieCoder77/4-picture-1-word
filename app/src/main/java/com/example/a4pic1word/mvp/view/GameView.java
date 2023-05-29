package com.example.a4pic1word.mvp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.a4pic1word.R;
import com.example.a4pic1word.data.model.Question;
import com.example.a4pic1word.mvp.contract.GameContract;
import com.example.a4pic1word.mvp.presenter.GamePresenter;
import com.example.a4pic1word.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class GameView extends AppCompatActivity implements GameContract.View {
    private List<ImageView> images;
    private List<TextView> answers;
    private List<TextView> variants;
    private AppCompatImageButton hint;
    private TextView coin;
    private TextView level;


    private GameContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_view);
        findViews();
        presenter = new GamePresenter(this);
        presenter.loadCurrentQuestion();

    }

    public void findViews(){
        hint = findViewById(R.id.hintId);
        coin = findViewById(R.id.coinTextId);
        level = findViewById(R.id.levelId);

        images = new ArrayList<>(Util.MAX_IMAGES_COUNT.value);
        images.add(findViewById(R.id.image1));
        images.add(findViewById(R.id.image2));
        images.add(findViewById(R.id.image3));
        images.add(findViewById(R.id.image4));

        LinearLayout parentAnswers = findViewById(R.id.answers);
        answers = new ArrayList<>(Util.MAX_ANSWER_COUNT.value);
        for (int i = 0; i < Util.MAX_ANSWER_COUNT.value; i++) {
            TextView temp = (TextView) parentAnswers.getChildAt(i);
            temp.setTag(i);
            answers.add(temp);
        }

        RelativeLayout parentVariants = findViewById(R.id.variants);
        variants = new ArrayList<>(Util.MAX_VARIANT_COUNT.value);
        for (int i = 0; i < Util.MAX_VARIANT_COUNT.value; i++) {
            TextView temp = (TextView) parentVariants.getChildAt(i);
            temp.setTag(i);
            variants.add(temp);
        }
        clickEvents();
    }


    @Override
    public void showQuestionImagesToView(List<Integer> questionImages) {
        for (int i = 0; i < Util.MAX_IMAGES_COUNT.value; i++) {
            images.get(i).setImageResource(questionImages.get(i));
        }
    }

    @Override
    public void showCoins(int coins) {
        coin.setText(String.valueOf(coins));
    }

    @Override
    public void showVariantsToView(String variant) {
        for (int i = 0; i < Util.MAX_VARIANT_COUNT.value; i++) {
            variants.get(i).setText(String.valueOf(variant.charAt(i)));
        }
    }

    @Override
    public void showLevel(int _level) {
        level.setText("Level " + _level);
    }

    @Override
    public void setVisibilityToAnswer(int answerLength) {
        for (int i = 0; i < answerLength; i++) {
            answers.get(i).setVisibility(View.VISIBLE);
        }

        for (int i = answerLength; i < Util.MAX_ANSWER_COUNT.value; i++) {
            answers.get(i).setVisibility(View.GONE);
        }
    }

    @Override
    public void clearOldData() {
        for (int i = 0; i < Util.MAX_ANSWER_COUNT.value; i++) {
            answers.get(i).setText("");
        }

        for (int i = 0; i < Util.MAX_VARIANT_COUNT.value; i++) {
            variants.get(i).setEnabled(true);
        }
    }


    @Override
    public void closeActivity() {
        finish();
    }

    @Override
    public void showDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.bg_dialog);
        dialog.getWindow().setBackgroundDrawable(null);
        dialog.setCancelable(false);
        dialog.show();

        AppCompatButton btnContinue = dialog.findViewById(R.id.btnContinue);
        AppCompatButton btnBack = dialog.findViewById(R.id.btnBack);

        btnContinue.setOnClickListener(v ->{
            dialog.cancel();
            presenter.loadNextQuestion();
        });

        btnBack.setOnClickListener(v ->{
            dialog.cancel();
            presenter.finishActivity();
        });
    }

    @Override
    public void setTextToAnswer(int pos, String letter) {
        answers.get(pos).setText(letter);
    }

    @Override
    public void setEnabledVariantByPos(int pos, boolean state) {
        variants.get(pos).setEnabled(state);
    }

    @Override
    public void wrongAnswerAnimation() {
        YoYo.with(Techniques.Shake).duration(600).playOn(findViewById(R.id.answers));
    }

    @Override
    public void setWrongColorToAnswers() {
        for (int i = 0; i < Util.MAX_ANSWER_COUNT.value; i++) {
            answers.get(i).setTextColor(getResources().getColor(R.color.red));
        }
    }

    @Override
    public void setDefaultColorToAnswers() {
        for (int i = 0; i < Util.MAX_ANSWER_COUNT.value; i++) {
            answers.get(i).setTextColor(getResources().getColor(R.color.black));
        }
    }

    @Override
    public void showRestartDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.bg_restart_dialog);
        dialog.getWindow().setBackgroundDrawable(null);
        dialog.setCancelable(false);
        dialog.show();

        AppCompatButton btnYes = dialog.findViewById(R.id.btnYes);
        AppCompatButton btnNo = dialog.findViewById(R.id.btnNo);

        btnYes.setOnClickListener(v ->{
            dialog.cancel();
            presenter.loadCurrentQuestion();
        });

        btnNo.setOnClickListener(v ->{
            dialog.cancel();
            presenter.finishActivity();
        });
    }

    @Override
    public void showHintDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.bg_hint_dialog);
        dialog.getWindow().setBackgroundDrawable(null);
        dialog.setCancelable(true);
        dialog.show();

        AppCompatButton btnYes = dialog.findViewById(R.id.btnYesHint);
        AppCompatButton btnNo = dialog.findViewById(R.id.btnNoHint);

        btnYes.setOnClickListener(v ->{
            dialog.cancel();
            presenter.clickedHintYes();
        });

        btnNo.setOnClickListener(v ->{
            dialog.cancel();
        });
    }



    @Override
    public void onBackPressed() {
        presenter.finishActivity();
    }

    private void clickEvents(){
        for (int i = 0; i < Util.MAX_ANSWER_COUNT.value; i++) {
            answers.get(i).setOnClickListener(v -> presenter.answerBtnClick((int) v.getTag()));
        }

        for (int i = 0; i < Util.MAX_VARIANT_COUNT.value; i++) {
            variants.get(i).setOnClickListener(v -> presenter.variantBtnClick((int) v.getTag()));
        }

        hint.setOnClickListener( v -> presenter.clickedHint());
    }
}