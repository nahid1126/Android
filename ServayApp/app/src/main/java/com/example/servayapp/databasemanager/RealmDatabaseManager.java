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

    public void insertSurveyModel(SurveyModel surveyModel) {
        if (realm == null) {
            realm = Realm.getDefaultInstance();
        }
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    Log.d(TAG, "execute: " + surveyModel.toString());
                    realm.copyToRealmOrUpdate(surveyModel);
                } catch (Exception e) {
                    Log.e(TAG, "execute: ", e);
                }
            }
        });
    }

    public List<SurveyModel> getSurveyModelList() {
        realm = Realm.getDefaultInstance();
        try {
            //return realm.copyFromRealm(realm.where(StudentModel.class).findAll());
            RealmResults<SurveyModel> surveyModels = realm.where(SurveyModel.class).findAll();
            return surveyModels;
        } catch (Exception e) {
            Log.d(TAG, "Insert" + e);
        }
        return null;
    }

    public boolean updateSurveyModel(SurveyModel surveyModel,String name, String homeDist,
                                     String edu, String dob, String area, String kids, String wife){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                SurveyModel updateModel =realm.where(SurveyModel.class)
                        .equalTo("id",surveyModel.getId()).findFirst();
                updateModel.setSurveyName(name);
                updateModel.setSurveyHomeDist(homeDist);
                updateModel.setSurveyEducation(edu);
                updateModel.setSurveyDOB(dob);
                updateModel.setSurveyArea(area);
                updateModel.setSurveyKids(kids);
                updateModel.setSurveyHouse(wife);
            }
        });
        return true;
    }
    public void deleteSurveyModel(SurveyModel surveyModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                SurveyModel deleteModel=realm.where(SurveyModel.class).equalTo("id",surveyModel.getId()).findFirst();
                deleteModel.deleteFromRealm();
            }
        });
    }

}
