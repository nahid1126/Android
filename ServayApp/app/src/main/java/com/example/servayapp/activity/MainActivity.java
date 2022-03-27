package com.example.servayapp.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.example.servayapp.R;
import com.example.servayapp.databasemanager.RealmDatabaseManager;
import com.example.servayapp.model.SurveyModel;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txtUserName)
    EditText txtUserName;

    @BindView(R.id.txtHomeDIST)
    EditText txtHomeDIST;

    @BindView(R.id.txtEducation)
    EditText txtEducation;

    @BindView(R.id.txtDOB)
    EditText txtDOB;

    @BindView(R.id.txtArea)
    EditText txtArea;

    @BindView(R.id.txtKids)
    EditText txtKids;

    @BindView(R.id.txtHouseWife)
    EditText txtHouseWife;

    @BindView(R.id.imageItem)
    ImageView imageItem;

    @BindView(R.id.txtTime)
    TextView txtTime;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private int hour, minute;
    public static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final String TAG = "MainActivity";
    private RealmDatabaseManager realmDatabaseManager;
    private String currentPath = null;
    private String currentPhotoPath;
    private String imageName = "photo";

    @OnClick(R.id.btnSubmit)
    void onClick() {
        String surveyName = txtUserName.getText().toString();
        String surveyHome = txtHomeDIST.getText().toString();
        String surveyEdu = txtEducation.getText().toString();
        String surveyDOB = txtDOB.getText().toString();
        String surveyArea = txtArea.getText().toString();
        String surveyKids = txtKids.getText().toString();
        String surveyHouse = txtHouseWife.getText().toString();
        String currentTime = txtTime.getText().toString();
        String imageStore = currentPhotoPath;
        if (TextUtils.isEmpty(surveyName)) {
            Toast.makeText(this, "Enter Name First", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(surveyHome)) {
            Toast.makeText(this, "Enter Home Dist", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(surveyEdu)) {
            Toast.makeText(this, "Enter Education", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(surveyDOB)) {
            Toast.makeText(this, "Enter DOB", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(surveyArea)) {
            Toast.makeText(this, "Enter Area", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(surveyKids)) {
            Toast.makeText(this, "Enter Kids", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(surveyHouse)) {
            Toast.makeText(this, "Enter House Wife", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(currentTime)) {
            Toast.makeText(this, "Enter Time", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(imageStore)) {
            Toast.makeText(this, "Capture image ", Toast.LENGTH_SHORT).show();
        } else {
            SurveyModel surveyModel = new SurveyModel(realmDatabaseManager.getSurveyModelList().size() + 1, surveyHome, surveyName,
                    surveyEdu, surveyDOB, surveyArea, surveyKids, surveyHouse, currentTime, imageStore);
            realmDatabaseManager.insertSurveyModel(surveyModel);

            Toast.makeText(MainActivity.this, "Successfully Added", Toast.LENGTH_SHORT)
                    .show();

            clear();

        }
    }

    @OnClick(R.id.btnList)
    void onShowListClick() {
        if (realmDatabaseManager.getSurveyModelList().size() > 0) {
            Intent intent = new Intent(MainActivity.this, SurveyListActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "No Data Available !!", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.txtTime)
    void onTimeClick() {
//        TimePickerDialog.OnTimeSetListener onTimeSetListener = (view, selectH, selectM) -> {
//            hour = selectH;
//            minute = selectM;
//            txtTime.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
//        };
//
//        TimePickerDialog time = new TimePickerDialog(MainActivity.this, onTimeSetListener, hour, minute, true);
//        time.setTitle("Select Time");
//        time.show();
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("hh:mm a dd-MM-yyyy");
        String dateToStr = format.format(today);
        txtTime.setText(dateToStr);
    }

    @OnClick(R.id.imageItem)
    void onCameraClick() {
        File imageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        try {
            File imageFIle = File.createTempFile(imageName, "jpg", imageDirectory);
            currentPhotoPath = imageFIle.getAbsolutePath();

            Uri imageUri = FileProvider.getUriForFile(MainActivity.this, "com.example.servayapp.fileprovider", imageFIle);

            Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            openCamera.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(openCamera, REQUEST_IMAGE_CAPTURE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.txtTime)
    void onTxtTimeClick() {
        txtTime.setText(getTime());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);
        realmDatabaseManager = new RealmDatabaseManager();

        setupToolbar();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap pic = BitmapFactory.decodeFile(currentPhotoPath);
            imageItem.setImageBitmap(pic);
        }
    }

    private void clear() {
        txtUserName.setText("");
        txtHomeDIST.setText("");
        txtEducation.setText("");
        txtDOB.setText("");
        txtArea.setText("");
        txtKids.setText("");
        txtHouseWife.setText("");
        txtTime.setText("Set Current Time");
        imageItem.setImageResource(R.drawable.ic_camera);
    }


    private String getTime() {
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("hh:mm a dd-MM-yyyy");
        String dateToStr = format.format(today);
        return dateToStr;
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            toolbar.setTitleTextColor(getResources().getColor(android.R.color.white, null));
        } else {
            toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle("Information");
        }
    }
}