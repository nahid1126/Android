package com.nahid.activity_life_cycle;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalStorage {
    private static final String TAG = "LocalStorage";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public LocalStorage(Context context) {
        sharedPreferences = context.getSharedPreferences("S-Lock", 0);
        editor = sharedPreferences.edit();
    }

    public void putApiCallFlag(int flag) {
        editor.putInt("done", flag);
        editor.commit();
    }

    public int getApiCallFlag() {
        return sharedPreferences.getInt("done", 0);
    }
}
