package com.example.retrofit_2.api;

import com.example.retrofit_2.Model.Comment;
import com.example.retrofit_2.Model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    ///https://jsonplaceholder.typicode.com/--->it's a base url
    ///here posts is relative/end url


    @GET("posts")
    Call<List<Post>> getPosts(
            @Query("userId") int userId,
            @Query("_sort") String sort,
            @Query("_order") String order
    );

    @GET("posts/1/comments")
    Call<List<Comment>> getComments();

    //post request
    @POST("posts")
    Call<Post> createPosts(@Body Post post);

    //put
    @PUT("posts/{id}")
    Call<Post> putPost(@Path("id") int id, @Body Post post);


    //patch
    @PUT("posts/{id}")
    Call<Post> patchPost(@Path("id") int id, @Body Post post);
}
