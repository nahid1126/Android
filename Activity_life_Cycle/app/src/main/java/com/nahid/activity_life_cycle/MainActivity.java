package com.nahid.activity_life_cycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    private LocalStorage preferences;

    private String androidId;
    private static final int PERMISSION_READ_STATE = 999;
    private TelephonyManager mTelephony;
    private static String[] PERMISSIONS_REQ = {
            Manifest.permission.READ_PHONE_STATE
    };
    public static final int STORAGE_REQUEST_CODE = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = new LocalStorage(this);
      /*  if(verifyPermissions(this)) {
            getIME();
        }*/
      txt = findViewById(R.id.txtWorld);

        /*txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Activity_Two.class));
            }
        });

        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();*/

        if(preferences.getApiCallFlag() == 0) {
            callMethod();
            Toast.makeText(this, "call done", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "already call", Toast.LENGTH_SHORT).show();
        }

    }

    private void callMethod() {
        txt.setText(Settings.Secure.getString(this.getContentResolver(),Settings.Secure.ANDROID_ID));
        if(androidId != null) {
            Toast.makeText(this, "Android Id " + androidId, Toast.LENGTH_SHORT).show();
            preferences.putApiCallFlag(1);
        } else {
            Toast.makeText(this, "Android Id not found" + androidId, Toast.LENGTH_SHORT).show();
        }
    }

/*    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();

    }*/


/*    private void getIME() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        } else {
            mTelephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    verifyReadPhoneState();
                }
            }
            assert mTelephony != null;
            if(mTelephony.getDeviceId() != null) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    androidId = mTelephony.getImei();
                } else {
                    androidId = mTelephony.getDeviceId();
                }
            } else {
                androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            }
        }
    }

    public void verifyReadPhoneState() {
        ActivityCompat.requestPermissions(this, PERMISSIONS_REQ, STORAGE_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case STORAGE_REQUEST_CODE:
                getIME();
                break;
        }
    }

    private boolean verifyPermissions(Activity activity) {
        try {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if(Environment.isExternalStorageManager()) {
                } else {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                }
            }

            int writePermission = ActivityCompat.checkSelfPermission(activity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int readPermission = ActivityCompat.checkSelfPermission(activity,
                    Manifest.permission.READ_EXTERNAL_STORAGE);

            if(writePermission != PackageManager.PERMISSION_GRANTED ||
                    readPermission != PackageManager.PERMISSION_GRANTED) {
                try {
                    ActivityCompat.requestPermissions(
                            activity,
                            PERMISSIONS_REQ,
                            STORAGE_REQUEST_CODE
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }*/
}