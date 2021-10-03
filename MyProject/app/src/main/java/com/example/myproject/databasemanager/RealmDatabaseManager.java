package com.example.myproject.databasemanager;

import android.util.Log;

import com.example.myproject.model.PostModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmDatabaseManager extends RealmDatabase {
    private static final String TAG = "RealmDatabaseManager";
    private static Realm realm;

    private static volatile RealmDatabaseManager realmDatabaseManager;

    public static synchronized RealmDatabaseManager getInstance() {
        if (realmDatabaseManager == null) {
            realmDatabaseManager = new RealmDatabaseManager();
        }

        return realmDatabaseManager;
    }

    public static void insertPostModel(List<PostModel> postModel) {

        if (realm == null) {
            realm = Realm.getDefaultInstance();
        }
        RealmResults<PostModel> deleteModel = realm.where(PostModel.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                deleteModel.deleteAllFromRealm();

            }
        });
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    realm.copyToRealmOrUpdate(postModel);
                } catch (Exception e) {
                    Log.e(TAG, "execute: ", e);
                }
            }
        });
    }

    public static void insertNewPostModel(PostModel postModel) {
        if (realm == null) {
            realm = Realm.getDefaultInstance();
        }
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    realm.copyToRealmOrUpdate(postModel);
                } catch (Exception e) {
                    Log.e(TAG, "execute: ", e);
                }
            }
        });
    }

    public List<PostModel> getPostModelList() {
        if (realm == null) {
            realm = Realm.getDefaultInstance();
        }
        try {
            RealmResults<PostModel> results = realm.where(PostModel.class).findAll();
            return results;
        } catch (Exception e) {
            Log.e(TAG, "execute: ", e);
        }
        return null;
    }

    public PostModel getPostModel(int id) {
        if (realm == null) {
            realm = Realm.getDefaultInstance();
        }
        try {
            PostModel model = realm.where(PostModel.class).equalTo("id", id).findFirst();
            return model;
        } catch (Exception e) {
            Log.e(TAG, "execute: ", e);
        }
        return null;
    }

}
