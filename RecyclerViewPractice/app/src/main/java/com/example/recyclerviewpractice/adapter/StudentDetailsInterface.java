package com.example.recyclerviewpractice.adapter;

import android.view.View;

import com.example.recyclerviewpractice.model.StudentModel;

public interface StudentDetailsInterface {
    void onMenuButtonClick(int position, StudentModel studentModel, View v);
}
