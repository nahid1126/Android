package com.example.servayapp.databasemanager;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import io.realm.Realm;

public class RealmDatabase extends AppCompatActivity {
    private static final String TAG = "RealmDatabase";

    public static volatile Realm realm;
    public static Context mContext;

    public static void init(Context context) {
        if(realm == null)
            realm = Realm.getDefaultInstance();
        mContext = context;
    }
}
