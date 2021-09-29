package com.example.flowersapi.databasemanager;

import com.example.flowersapi.model.FlowerModel;

import java.util.List;

public interface FlowerRepositoryInterface {
    void onSuccess(List<FlowerModel> flowerModels);

    void onFailed();
}
