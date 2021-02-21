package com.darkstyler.whatyouknow.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private SharedPreferences preferences;
    public Prefs(Activity activity){
        this.preferences = activity.getPreferences(Context.MODE_PRIVATE);
    }
    public void saveHighestScore(int score){
        int lastScore = preferences.getInt("high_score",0);
        if(score > lastScore){
            preferences.edit().putInt("high_score", score).apply();
        }
    }
    public int getHighestScore(){
        return  preferences.getInt("high_score",0);
    }
}
