package com.example.recyclerviewpractice;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.recyclerviewpractice.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    static List<StudentModel> studentModelList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int getIdSum(){
        return studentModelList
                .stream()
                .mapToInt(StudentModel::getStudentId)
                .sum();
    }

}
