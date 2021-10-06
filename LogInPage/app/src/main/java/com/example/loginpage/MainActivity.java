package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.loginpage.fragment.DashBoardFragment;
import com.example.loginpage.fragment.LogInFragment;
import com.example.loginpage.sharedPreference.StoreData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StoreData storeData = new StoreData(this);
        if (storeData.getUserName().equals("") || storeData.getUserPass().equals("")) {
            getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,
                    new LogInFragment()).commit();
        } else {
            getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,
                    new DashBoardFragment()).commit();

        }

    }
}