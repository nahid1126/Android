package com.example.myproject.api;

import com.example.myproject.model.CommentModel;
import com.example.myproject.model.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiClientInterface {
    @GET("posts")
    Call<List<PostModel>> getPost();

    @GET("posts/{id}/comments")
    Call<List<CommentModel>> getComment(@Path("id") int postId);
}
