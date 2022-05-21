package com.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView flashImg;
    private TextView txt_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        flashImg = findViewById(R.id.flash_img);
        txt_type = findViewById(R.id.txt_type);

        txt_type.setOnClickListener(view -> {
            if(txt_type.getText().equals("ON")) {
                txt_type.setText("OFF");
                flashImg.setImageResource(R.drawable.flashlight_on);

                changeLight(true);
            } else {
                txt_type.setText("ON");
                flashImg.setImageResource(R.drawable.flashlight_off);

                changeLight(false);
            }
        });
    }

    private void changeLight(boolean b) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            CameraManager manager = (CameraManager) getSystemService(CAMERA_SERVICE);
            String camId;
            try {
                camId = manager.getCameraIdList()[0];
                manager.setTorchMode(camId, b);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }
}