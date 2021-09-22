package com.example.recyclerviewpractice;

import com.example.recyclerviewpractice.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    static List<StudentModel> studentModelList = new ArrayList<>();
    public static void setStudentModelList (StudentModel studentModel){
        studentModelList.add(studentModel);
    }

    public static List<StudentModel> getStudentModelList(){
        if(studentModelList.size() >0){
            return studentModelList;
        }
        return studentModelList;
    }
}
