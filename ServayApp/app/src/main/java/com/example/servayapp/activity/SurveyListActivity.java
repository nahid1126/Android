package com.example.servayapp.activity;

import android.annotation.SuppressLint;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servayapp.R;
import com.example.servayapp.adapter.SurveyAdapter;
import com.example.servayapp.adapter.SurveyInterface;
import com.example.servayapp.databasemanager.RealmDatabaseManager;
import com.example.servayapp.dialogFragment.DialogInterface;
import com.example.servayapp.dialogFragment.UpdateDialog;
import com.example.servayapp.model.SurveyModel;
import com.shreyaspatil.MaterialDialog.BottomSheetMaterialDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SurveyListActivity extends AppCompatActivity {

    @BindView(R.id.recyclerViewSurvey)
    RecyclerView recyclerViewSurvey;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private SurveyAdapter surveyAdapter;
    private RealmDatabaseManager realmDatabaseManager;
    private UpdateDialog updateDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_list);

        ButterKnife.bind(this);

        realmDatabaseManager = new RealmDatabaseManager();

        recyclerViewSurvey.setLayoutManager(new LinearLayoutManager(this));

        surveyAdapter = new SurveyAdapter(SurveyListActivity.this,
                realmDatabaseManager.getSurveyModelList(), new SurveyInterface() {
            @Override
            public void onClickItem(int position, SurveyModel surveyModel) {
                Toast.makeText(SurveyListActivity.this, "ID: " + surveyModel.getId(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMenuButtonClick(int position, SurveyModel surveyModel, View v) {
                PopupMenu popupMenu = new PopupMenu(SurveyListActivity.this, v);
                popupMenu.inflate(R.menu.popup);
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.updateID: {
                                updateDialog = new UpdateDialog(surveyModel);
                                updateDialog.setCancelable(false);
                                updateDialog.setDialogInterface(new DialogInterface() {
                                    @Override
                                    public void onUpdate(String name, String homeDist, String edu, String dob, String area, String kids, String wife) {
                                        updateDialog.dismiss();
                                        realmDatabaseManager.updateSurveyModel(surveyModel, name, homeDist, edu, dob, area, kids, wife);
                                        surveyAdapter.notifyDataSetChanged();
                                        Toast.makeText(SurveyListActivity.this,
                                                "Update successful "
                                                        + surveyModel.getId(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                                updateDialog.show(((AppCompatActivity) SurveyListActivity.this)
                                        .getSupportFragmentManager(), "Survey list Update");
                                return true;
                            }
                            case R.id.deleteId: {
                                BottomSheetMaterialDialog bottomSheetMaterialDialog =
                                        new BottomSheetMaterialDialog.Builder(SurveyListActivity.this)
                                                .setTitle("Delete ??")
                                                .setMessage("Are you sure! do you want to delete Id: " + surveyModel.getId() + " ??")
                                                .setCancelable(false)
                                                .setPositiveButton("Yes", R.drawable.ic_tik, new BottomSheetMaterialDialog.OnClickListener() {
                                                    @Override
                                                    public void onClick(com.shreyaspatil.MaterialDialog.interfaces.DialogInterface dialogInterface, int which) {
                                                        dialogInterface.dismiss();
                                                        realmDatabaseManager.deleteSurveyModel(surveyModel);
                                                        surveyAdapter.notifyDataSetChanged();
                                                        Toast.makeText(getApplicationContext(), "Delete Successfully", Toast.LENGTH_SHORT).show();
                                                    }
                                                })
                                                .setNegativeButton("No", R.drawable.ic_delete,
                                                        new BottomSheetMaterialDialog.OnClickListener() {
                                                            @Override
                                                            public void onClick(com.shreyaspatil.MaterialDialog.interfaces.DialogInterface dialogInterface, int which) {
                                                                dialogInterface.dismiss();
                                                            }
                                                        }).build();
                                bottomSheetMaterialDialog.show();
                                return true;
                            }
                        }
                        return true;
                    }
                });
            }
        });

        recyclerViewSurvey.setAdapter(surveyAdapter);
        surveyAdapter.notifyDataSetChanged();
        setupToolbar();

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
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Survey List");
        }

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            toolbar.getNavigationIcon().setColorFilter(new BlendModeColorFilter(Color.WHITE,
                    BlendMode.SRC_ATOP));
        } else {
            toolbar.getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        }
    }
}