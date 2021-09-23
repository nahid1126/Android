package com.example.loginpage.sharedPreference;

import android.annotation.SuppressLint;
import android.content.Context;

public class StoreData {

    private static final String TAG = "Store Data";

    private final android.content.SharedPreferences sharedPreferences;
    private final android.content.SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public StoreData(Context context) {
        sharedPreferences = context.getSharedPreferences(TAG, 0);
        editor = sharedPreferences.edit();
    }


    public String getUserName() {
        return sharedPreferences.getString("UserName", "");
    }

    public void setUserName(String name) {
        editor.putString("UserName", name);
        editor.apply();
    }

    public String getUserPass() {
        return sharedPreferences.getString("Password", "");
    }

    public void setUserPass(String pass) {
        editor.putString("Password", pass);
        editor.apply();
    }

    public void clearData(){
        editor.clear().apply();
    }
}
