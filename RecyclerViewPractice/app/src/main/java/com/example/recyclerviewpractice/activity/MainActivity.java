package com.example.recyclerviewpractice.activity;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclerviewpractice.R;
import com.example.recyclerviewpractice.StudentRepository;
import com.example.recyclerviewpractice.adapter.StudentAdapter;
import com.example.recyclerviewpractice.adapter.StudentDetailsInterface;
import com.example.recyclerviewpractice.databasemanager.RealmDatabase;
import com.example.recyclerviewpractice.databasemanager.RealmDatabaseManager;
import com.example.recyclerviewpractice.model.StudentModel;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.realm.Realm;
import io.realm.RealmResults;

import static com.example.recyclerviewpractice.databasemanager.RealmDatabase.realm;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txtStudentName)
    EditText txtStudentName;
    @BindView(R.id.txtStudentID)
    EditText txtStudentID;
    @BindView(R.id.txtStudentMail)
    EditText txtStudentMail;
    @BindView(R.id.txtStudentPhone)
    EditText txtStudentPhone;
    @BindView(R.id.txtStudentDept)
    EditText txtStudentDept;
    @BindView(R.id.txtStudentDateOfBirth)
    TextView txtStudentDateOfBirth;
    //spinner
    @BindView(R.id.divSpinner)
    Spinner divSpinner;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    private static final String TAG = "MainActivity";
    String divisionSelect = "";
    List<String> divisionList;

    @OnClick(R.id.btnSubmit)
    public void onClick() {
        String studentName = txtStudentName.getText().toString();
        String studentID = txtStudentID.getText().toString();
        String studentEmail = txtStudentMail.getText().toString();
        String studentPhone = txtStudentPhone.getText().toString();
        String studentDept = txtStudentDept.getText().toString();
        String studentDateofBirth = txtStudentDateOfBirth.getText().toString();

        if (TextUtils.isEmpty(studentName)) {
            Toast.makeText(this, "Enter Name First", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(studentID)) {
            Toast.makeText(this, "Enter ID First", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(studentEmail)) {
            Toast.makeText(this, "Enter Email First", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(studentPhone)) {
            Toast.makeText(this, "Enter Phone Number First", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(studentDept)) {
            Toast.makeText(this, "Enter Department First", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(studentDateofBirth)) {
            Toast.makeText(this, "Enter Date OF Birth First", Toast.LENGTH_SHORT).show();
        } else if (divisionSelect.isEmpty()) {
            Toast.makeText(this, "Enter Division First", Toast.LENGTH_SHORT).show();
        } else {
            StudentModel studentModel = new StudentModel(Integer.parseInt(studentID), studentName,
                    studentEmail, studentPhone, studentDept, studentDateofBirth, divisionSelect);
            RealmDatabaseManager.insertStudentModel(studentModel);

            Toast.makeText(MainActivity.this, "Successfully Added", Toast.LENGTH_SHORT)
                    .show();
            clear();

        }
    }

    @OnClick(R.id.btnList)
    public void onShowList() {
        if (RealmDatabaseManager.getStudentModelList().size() > 0) {
            Intent intent = new Intent(MainActivity.this, StudentListActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "No Data Available !!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);

        setTitle("Student Registration");
        //realm = Realm.getDefaultInstance();


        MaterialDatePicker datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date of Birth").setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();
        txtStudentDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.show(getSupportFragmentManager(), "Date picker");
                datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        txtStudentDateOfBirth.setText(datePicker.getHeaderText());
                    }
                });
            }
        });

        divisionList = new ArrayList<>();
        divisionList.add("Select division");
        divisionList.add("Barisal");
        divisionList.add("Chittagong");
        divisionList.add("Dhaka");
        divisionList.add("Khulna");
        divisionList.add("Mymensingh");
        divisionList.add("Rajshahi");
        divisionList.add("Rangpur");
        divisionList.add("Sylhet");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, divisionList);
        divSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    divisionSelect = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        divSpinner.setAdapter(arrayAdapter);


    }

    private void clear() {
        txtStudentName.setText("");
        txtStudentID.setText("");
        txtStudentMail.setText("");
        txtStudentPhone.setText("");
        txtStudentDept.setText("");
        txtStudentDateOfBirth.setText("");
    }
}