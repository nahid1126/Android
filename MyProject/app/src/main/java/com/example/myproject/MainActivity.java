package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.myproject.atapter.PostAdapter;
import com.example.myproject.atapter.PostDetailsInterface;
import com.example.myproject.databasemanager.PostRepojetory;
import com.example.myproject.databasemanager.PostRepojetoryInterface;
import com.example.myproject.fragment.HomeFragment;
import com.example.myproject.fragment.PostDetailsFragment;
import com.example.myproject.model.PostModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.mainPanel)
    FrameLayout recyclerViewFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);

        loadFragment();

    }

    private void loadFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.mainPanel, new HomeFragment())
                .addToBackStack(null)
                .commit();
    }
}