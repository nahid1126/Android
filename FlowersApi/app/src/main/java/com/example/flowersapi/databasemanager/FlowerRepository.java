package com.example.flowersapi.databasemanager;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import com.example.flowersapi.MainActivity;
import com.example.flowersapi.api.ApiClient;
import com.example.flowersapi.api.ApiInterface;
import com.example.flowersapi.model.FlowerModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlowerRepository {

    Context context;
    public static final int RESPONSE_OK = 200;
    private ApiInterface apiInterface;

    public FlowerRepository(Context context) {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        this.context = context;
    }

    public void getFlowerList(FlowerRepositoryInterface flowerRepositoryInterface){

        Call<List<FlowerModel>> call = apiInterface.getFeeds();
        try {

            call.enqueue(new Callback<List<FlowerModel>>() {
                @Override
                public void onResponse(Call<List<FlowerModel>> call,
                                       Response<List<FlowerModel>> response) {

                    if (response.isSuccessful() && response.code() == RESPONSE_OK) {
                        List<FlowerModel> flowerModels = response.body();

                        flowerRepositoryInterface.onSuccess(flowerModels);

                    }else {
                        flowerRepositoryInterface.onFailed();
                        Toast.makeText(context, "Failed ", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<List<FlowerModel>> call, Throwable t) {
                    flowerRepositoryInterface.onFailed();
                    call.cancel();
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT)
                            .show();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
