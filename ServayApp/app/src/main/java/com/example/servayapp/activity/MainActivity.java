package com.example.servayapp.activity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.servayapp.R;
import com.example.servayapp.databasemanager.RealmDatabaseManager;
import com.example.servayapp.model.SurveyModel;

import java.util.Locale;

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

    private int hour, minute;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final String TAG = "MainActivity";

    @OnClick(R.id.btnSubmit)
    void onClick() {
        String surveyName = txtUserName.getText().toString();
        String surveyHome = txtHomeDIST.getText().toString();
        String surveyEdu = txtEducation.getText().toString();
        String surveyDOB = txtDOB.getText().toString();
        String surveyArea = txtArea.getText().toString();
        String surveyKids = txtKids.getText().toString();
        String surveyHouse = txtHouseWife.getText().toString();

        if (TextUtils.isEmpty(surveyName)) {
            Toast.makeText(this, "Enter Name First", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(surveyHome)) {
            Toast.makeText(this, "Enter Home Dist First", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(surveyEdu)) {
            Toast.makeText(this, "Enter Education First", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(surveyDOB)) {
            Toast.makeText(this, "Enter DOB First", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(surveyArea)) {
            Toast.makeText(this, "Enter Area First", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(surveyKids)) {
            Toast.makeText(this, "Enter Kids First", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(surveyHouse)) {
            Toast.makeText(this, "Enter House Wife First", Toast.LENGTH_SHORT).show();
        } else {
            SurveyModel surveyModel = new SurveyModel(surveyHome, surveyName,
                    surveyEdu, surveyDOB, surveyArea, surveyKids, surveyHouse);
            RealmDatabaseManager.insertSurveyModel(surveyModel);

            Toast.makeText(MainActivity.this, "Successfully Added", Toast.LENGTH_SHORT)
                    .show();

            clear();

        }
    }

    @OnClick(R.id.btnList)
    void onShowListClick() {
        if (RealmDatabaseManager.getSurveyModelList().size() > 0) {
            Intent intent = new Intent(MainActivity.this, SurveyListActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "No Data Available !!", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.txtTime)
    void onTimeClick() {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = (view, selectH, selectM) -> {
            hour = selectH;
            minute = selectM;
            txtTime.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
        };

        TimePickerDialog time = new TimePickerDialog(MainActivity.this, onTimeSetListener, hour, minute, true);
        time.setTitle("Select Time");
        time.show();
    }

    @OnClick(R.id.imageItem)
    void onCameraClick(){
        Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(openCamera, REQUEST_IMAGE_CAPTURE);
            Log.e(TAG, "onClick: try work");
        } catch (Exception e) {
            Log.e(TAG, "onClick: " + e.getMessage());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            Bitmap pic = (Bitmap) bundle.get("data");
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
    }

}