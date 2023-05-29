package com.example.a4pic1word.domain;

import com.example.a4pic1word.R;
import com.example.a4pic1word.data.model.Question;
import com.example.a4pic1word.data.source.MyPref;
import com.example.a4pic1word.utils.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Repository {
    private static Repository repository;
    private MyPref myPref;
    private List<Question> questions;

    private Repository(){
        myPref = MyPref.getInstance();
        loadQuestions();
    }

    public static Repository getInstance(){
        if(repository == null){
            repository = new Repository();
        }
        return repository;
    }

    private void loadQuestions(){
        questions = new ArrayList<>();
        questions.add(new Question(Arrays.asList(R.drawable.q1_pic1,R.drawable.q1_pic2,R.drawable.q1_pic3,R.drawable.q1_pic4),"ICE",generateVariant("ICE")));
        questions.add(new Question(Arrays.asList(R.drawable.q2_pic1,R.drawable.q2_pic2,R.drawable.q2_pic3,R.drawable.q2_pic4),"FIT",generateVariant("FIT")));
        questions.add(new Question(Arrays.asList(R.drawable.q3_pic1,R.drawable.q3_pic2,R.drawable.q3_pic3,R.drawable.q3_pic4),"COTTON",generateVariant("COTTON")));
        questions.add(new Question(Arrays.asList(R.drawable.q4_pic1,R.drawable.q4_pic2,R.drawable.q4_pic3,R.drawable.q4_pic4),"ANIMAL",generateVariant("ANIMAL")));
        questions.add(new Question(Arrays.asList(R.drawable.q5_pic1,R.drawable.q5_pic2,R.drawable.q5_pic3,R.drawable.q5_pic4),"FAMILY",generateVariant("FAMILY")));
        questions.add(new Question(Arrays.asList(R.drawable.q6_pic1,R.drawable.q6_pic2,R.drawable.q6_pic3,R.drawable.q6_pic4),"YELLOW",generateVariant("YELLOW")));
        questions.add(new Question(Arrays.asList(R.drawable.q7_pic1,R.drawable.q7_pic2,R.drawable.q7_pic3,R.drawable.q7_pic4),"COWBOY",generateVariant("COWBOY")));
        questions.add(new Question(Arrays.asList(R.drawable.q8_pic1,R.drawable.q8_pic2,R.drawable.q8_pic3,R.drawable.q8_pic4),"CIRCUS",generateVariant("CIRCUS")));
        questions.add(new Question(Arrays.asList(R.drawable.q9_pic1,R.drawable.q9_pic2,R.drawable.q9_pic3,R.drawable.q9_pic4),"PRINCESS",generateVariant("PRINCESS")));
        questions.add(new Question(Arrays.asList(R.drawable.q10_pic1,R.drawable.q10_pic2,R.drawable.q10_pic3,R.drawable.q10_pic4),"DENTURES",generateVariant("DENTURES")));
    }

    private String generateVariant(String answer){
        List<Character> variant = new ArrayList<>(14);
        List<Character> letters = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N','O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');

        for (int i = 0; i < answer.length(); i++) {
            variant.add(answer.charAt(i));
        }

        Random rand = new Random();
        for (int i = answer.length(); i < Util.MAX_VARIANT_COUNT.value; i++) {
            Character temp = letters.get(rand.nextInt(26));
            if(!(answer.contains(temp + ""))){
                variant.add(temp);
            }
            else{
                --i;
            }
        }

        Collections.shuffle(variant);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < variant.size(); i++) {
            sb.append(variant.get(i));
        }

        return sb.toString();

    }

    public List<Integer> getCurrentQuestionImages(){
        return questions.get(myPref.getPos()).getImages();
    }

    public Question getCurrentQuestion(){
        return questions.get(myPref.getPos());
    }

    public void savePos(int pos){
        myPref.savePos(pos);
    }

    public int getPos(){
        return myPref.getPos();
    }

    public int getQuestionsCount(){
        return questions.size();
    }

    public void saveCoins(int coins){
         myPref.saveCoins(coins);
    }

    public int getCoins(){
        return myPref.getCoins();
    }


}
