package com.example.servayapp.databasemanager;

import android.util.Log;

import com.example.servayapp.model.SurveyModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmDatabaseManager {

    private static final String TAG = "RealmDatabaseManager";
    private static Realm realm;

    public RealmDatabaseManager() {
        realm = Realm.getDefaultInstance();
    }

    public static void insertSurveyModel(SurveyModel surveyModel) {
        if (realm == null) {
            realm = Realm.getDefaultInstance();
        }
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    Log.d(TAG, "execute: "+surveyModel.toString());
                    realm.copyToRealmOrUpdate(surveyModel);
                } catch (Exception e) {
                    Log.e(TAG, "execute: ", e);
                }
            }
        });
    }

    public static List<SurveyModel> getSurveyModelList(){
        realm=Realm.getDefaultInstance();
        try {
            //return realm.copyFromRealm(realm.where(StudentModel.class).findAll());
            RealmResults<SurveyModel> surveyModels =realm.where(SurveyModel.class).findAll();
            return surveyModels;
        }catch (Exception e){
            Log.d(TAG,"Insert"+e);
        }
        return null;
    }

}
