package com.nahid.pushnotification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView mToken;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToken = findViewById(R.id.txtToken);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if(!task.isSuccessful()) {
                        Log.w(TAG, "Fetching FCM registration mToken failed", task.getException());
                        return;
                    }

                    // Get new FCM registration mToken
                    token = task.getResult();

                    // Log and toast
                    Log.d(TAG, "Token" + token);
                    Toast.makeText(MainActivity.this, "Device reg mToken is " + token, Toast.LENGTH_SHORT).show();
                    mToken.setText(token);
                });
    }
}