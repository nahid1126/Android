package com.example.servayapp.adapter;

import android.view.View;

import com.example.servayapp.model.SurveyModel;

public interface SurveyInterface {
    void onClickItem(int position, SurveyModel surveyModel);

    void onMenuButtonClick(int position, SurveyModel surveyModel, View v);
}
