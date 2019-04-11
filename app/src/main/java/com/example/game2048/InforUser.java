package com.example.game2048;

import android.content.Context;
import android.content.SharedPreferences;

public class InforUser {
    public int point = 0;
    private String name = "appGame", keyPoint = "point";

    public void saveData(Context c) {
        SharedPreferences sharedPreferences = c.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(keyPoint, point);
        edit.apply();
    }


    public void getData(Context c) {
        SharedPreferences sharedPreferences = c.getSharedPreferences(name, Context.MODE_PRIVATE);
        if (sharedPreferences != null) {
            point = sharedPreferences.getInt(keyPoint, 0);
        }
    }
}
