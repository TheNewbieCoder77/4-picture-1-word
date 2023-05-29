package com.example.a4pic1word.data.source;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.a4pic1word.App.App;

public class MyPref {
    private static MyPref myPref;
    private SharedPreferences preferences;

    private MyPref(){
        preferences = App.context.getSharedPreferences("PICTURE_GAME", Context.MODE_PRIVATE);
    }

    public static MyPref getInstance(){
        if(myPref == null){
            myPref = new MyPref();
        }
        return myPref;
    }

    public void savePos(int pos){
        preferences.edit().putInt("POSITION",pos).apply();
    }

    public int getPos(){
        return preferences.getInt("POSITION",0);
    }

    public void saveCoins(int coins){
        preferences.edit().putInt("COINS",coins).apply();
    }

    public int getCoins(){
        return preferences.getInt("COINS",100);
    }

}


