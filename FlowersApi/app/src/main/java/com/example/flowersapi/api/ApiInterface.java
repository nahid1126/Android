package com.example.flowersapi.api;

import com.example.flowersapi.model.FlowerModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("feeds/flowers.json")
    Call<List<FlowerModel>>getFeeds();
}
