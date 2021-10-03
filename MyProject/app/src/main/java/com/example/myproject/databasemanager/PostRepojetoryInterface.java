package com.example.myproject.databasemanager;

import com.example.myproject.model.CommentModel;
import com.example.myproject.model.PostModel;

import java.util.List;

import io.realm.RealmResults;

public interface PostRepojetoryInterface {

    void onSuccess(List<PostModel> postModels);
    void onFailed();

    public interface CommentInterface {
        void onSuccess(List<CommentModel> comments);
        void onFailed();
    }

}
