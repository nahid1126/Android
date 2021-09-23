package com.example.designtask.sharedPreferences;

import android.content.Context;

public class SharedPreferencesDemo {
    private static final String TAG = "LocalStorage";

    private android.content.SharedPreferences sharedPreferences;
    private android.content.SharedPreferences.Editor editor;

    public SharedPreferencesDemo(Context context) {
        sharedPreferences = context.getSharedPreferences(TAG, 0);
        editor = sharedPreferences.edit();
    }


    public String getName() {
        return sharedPreferences.getString("Name", null);
    }

    public void setName(String name) {
        editor.putString("Name", name);
        editor.apply();
    }

    public int getAge() {
        return sharedPreferences.getInt("Age", 0);
    }

    public void setAge(int age) {
        editor.putInt("Age", age);
        editor.apply();
    }
   /* public static String read(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void write(String key, String value) {
        Shared_Preferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    public static boolean read(String key, boolean defValue) {
        return mSharedPref.getBoolean(key, defValue);
    }

    public static void write(String key, boolean value) {
        Shared_Preferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.commit();
    }

    public static Integer read(String key, int defValue) {
        return mSharedPref.getInt(key, defValue);
    }

    public static void write(String key, Integer value) {
        Shared_Preferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putInt(key, value).commit();
    }*/
}