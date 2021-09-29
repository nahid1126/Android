package com.example.flowersapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flowersapi.adapter.FlowerAdapter;
import com.example.flowersapi.api.ApiClient;
import com.example.flowersapi.api.ApiInterface;
import com.example.flowersapi.databasemanager.FlowerRepository;
import com.example.flowersapi.databasemanager.FlowerRepositoryInterface;
import com.example.flowersapi.model.FlowerModel;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private FlowerRepository flowerRepository;
    private RecyclerView flowerRecyclerView;
    private FlowerAdapter flowerAdapter;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(MainActivity.this);

        progressBar = new ProgressDialog(MainActivity.this);
        progressBar.setMessage("Please Wait..........");
        progressBar.show();

        flowerRecyclerView = findViewById(R.id.flowerRecyclerVIew);

        flowerRepository = new FlowerRepository(MainActivity.this);

        flowerRepository.getFlowerList(new FlowerRepositoryInterface() {
            @Override
            public void onSuccess(List<FlowerModel> flowerModels) {
                LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
                flowerRecyclerView.setLayoutManager(manager);

                flowerAdapter = new FlowerAdapter(MainActivity.this, flowerModels);
                flowerRecyclerView.setAdapter(flowerAdapter);
                flowerAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "View successful", Toast.LENGTH_SHORT).show();
                progressBar.dismiss();
            }

            @Override
            public void onFailed() {
                Toast.makeText(MainActivity.this, "Failed communicate with server", Toast.LENGTH_SHORT).show();
                progressBar.dismiss();
            }
        });


    }
}