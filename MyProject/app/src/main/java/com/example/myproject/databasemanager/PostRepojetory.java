package com.example.myproject.databasemanager;

import android.content.Context;
import android.widget.Toast;

import com.example.myproject.api.ApiClient;
import com.example.myproject.api.ApiClientInterface;
import com.example.myproject.model.CommentModel;
import com.example.myproject.model.PostModel;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepojetory {
    private Context context;
    private ApiClientInterface apiClientInterface;
    private final int RESPONSE_OK = 200;
    private static Realm realm;

    public PostRepojetory(Context context) {
        this.context = context;
        apiClientInterface = ApiClient.getApiClient().create(ApiClientInterface.class);
    }

    public void getPostList(PostRepojetoryInterface postRepojetoryInterface) {
        Call<List<PostModel>> call = apiClientInterface.getPost();
        try {
            call.enqueue(new Callback<List<PostModel>>() {
                @Override
                public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                    if (response.isSuccessful() && response.code() == RESPONSE_OK) {
                        List<PostModel> postModels = response.body();
                        postRepojetoryInterface.onSuccess(postModels);

                    } else {
                        postRepojetoryInterface.onFailed();
                        Toast.makeText(context, "Failed ", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<PostModel>> call, Throwable t) {
                    postRepojetoryInterface.onFailed();
                    call.cancel();
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
