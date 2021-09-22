package com.example.recyclerviewpractice.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.recyclerviewpractice.R;
import com.example.recyclerviewpractice.model.StudentModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentDetailsActivity extends AppCompatActivity {

    @BindView(R.id.txtStudentName)
    TextView txtStudentName;
    @BindView(R.id.txtStudentID)
    TextView txtStudentID;
    @BindView(R.id.txtStudentMail)
    TextView txtStudentMail;
    @BindView(R.id.txtStudentPhone)
    TextView txtStudentPhone;
    @BindView(R.id.txtStudentDept)
    TextView txtStudentDept;
    @BindView(R.id.txtStudentDateOfBirth)
    TextView txtStudentDateOfBirth;
    @BindView(R.id.txtStudentDivision)
    TextView txtStudentDivision;

    StudentModel studentModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details_activity);
        ButterKnife.bind(this);

        setTitle("Student Details");

        studentModel=getIntent().getParcelableExtra("details");
        if (studentModel!=null){
            txtStudentName.setText("Name: "+studentModel.getStudentName());
            txtStudentID.setText("ID: "+studentModel.getStudentId());
            txtStudentMail.setText("Mail: "+studentModel.getStudentMail());
            txtStudentPhone.setText("Phone: "+studentModel.getStudentPhone());
            txtStudentDept.setText("Department: "+studentModel.getStudentDept());
            txtStudentDateOfBirth.setText("Date of Birth: "+studentModel.getStudentDateOfBirth());
            txtStudentDivision.setText("Divisipon: "+studentModel.getDivisonadd());
        }
    }
}