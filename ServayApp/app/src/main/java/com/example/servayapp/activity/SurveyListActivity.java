package com.example.servayapp.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servayapp.R;
import com.example.servayapp.adapter.SurveyAdapter;
import com.example.servayapp.adapter.SurveyInterface;
import com.example.servayapp.databasemanager.RealmDatabaseManager;
import com.example.servayapp.model.SurveyModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SurveyListActivity extends AppCompatActivity {

    @BindView(R.id.recyclerViewSurvey)
    RecyclerView recyclerViewSurvey;

    private SurveyAdapter surveyAdapter;
    private RealmDatabaseManager realmDatabaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_list);

        ButterKnife.bind(this);

        setTitle("Survey List");
        realmDatabaseManager = new RealmDatabaseManager();
        recyclerViewSurvey.setLayoutManager(new LinearLayoutManager(this));

        surveyAdapter = new SurveyAdapter(SurveyListActivity.this,
                realmDatabaseManager.getSurveyModelList(), new SurveyInterface() {
            @Override
            public void onClickItem(int position, SurveyModel surveyModel) {

            }

            @Override
            public void onMenuButtonClick(int position, SurveyModel surveyModel, View v) {

            }
        });

        recyclerViewSurvey.setAdapter(surveyAdapter);
        surveyAdapter.notifyDataSetChanged();

    }
}