package com.example.recyclerviewpractice.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.recyclerviewpractice.R;
import com.example.recyclerviewpractice.StudentRepository;
import com.example.recyclerviewpractice.adapter.StudentAdapter;
import com.example.recyclerviewpractice.adapter.StudentDetailsInterface;
import com.example.recyclerviewpractice.databasemanager.RealmDatabaseManager;
import com.example.recyclerviewpractice.dialogFragment.UpdateDialog;
import com.example.recyclerviewpractice.dialogFragment.UpdateDialogInterface;
import com.example.recyclerviewpractice.model.StudentModel;
import com.shreyaspatil.MaterialDialog.BottomSheetMaterialDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class StudentListActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private Realm realm;
    private UpdateDialog updateDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        ButterKnife.bind(this);

        realm = Realm.getDefaultInstance();
        setTitle("Student List");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        RealmResults<StudentModel> studentModels = realm.where(StudentModel.class).findAll();

        studentAdapter = new StudentAdapter(StudentListActivity.this, studentModels,
                new StudentDetailsInterface() {
                    @Override
                    public void onClickItem(int position, StudentModel studentModel) {
                        Intent intent = new Intent(StudentListActivity.this,
                                StudentDetailsActivity.class);
                        intent.putExtra("details", studentModel);
                        startActivity(intent);
                        Toast.makeText(StudentListActivity.this, "" + position, Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onMenuButtonClick(int position, StudentModel studentModel, View v) {
                        PopupMenu popupMenu = new PopupMenu(StudentListActivity.this, v);
                        popupMenu.inflate(R.menu.popup);
                        popupMenu.show();

                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @SuppressLint("RestrictedApi")
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.updateID: {
                                        updateDialog = new UpdateDialog(studentModel);
                                        updateDialog.setCancelable(false);
                                        updateDialog.setUpdateDialogInterface(new UpdateDialogInterface() {
                                            @Override
                                            public void onUpdate(String n, String m, String p, String d) {
                                                updateDialog.dismiss();
                                                RealmDatabaseManager.updateStudentModel(studentModel, n, m, p, d);
                                                studentAdapter.notifyDataSetChanged();
                                                Toast.makeText(StudentListActivity.this,
                                                        "Update successful "
                                                                + studentModel.getStudentId(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        updateDialog.show(((AppCompatActivity) StudentListActivity.this)
                                                .getSupportFragmentManager(), "Student list Updte");
                                        return true;
                                    }
                                    case R.id.deleteId: {
                                        BottomSheetMaterialDialog bottomSheetMaterialDialog =
                                                new BottomSheetMaterialDialog.Builder(StudentListActivity.this)
                                                .setTitle("Delete ??")
                                                .setMessage("Are you sure! do you want to delete Id: " + studentModel.getStudentId() + " ??")
                                                .setCancelable(false)
                                                .setPositiveButton("Yes", R.drawable.ic_tik, new BottomSheetMaterialDialog.OnClickListener() {
                                                    @Override
                                                    public void onClick(com.shreyaspatil.MaterialDialog.interfaces.DialogInterface dialogInterface, int which) {
                                                        dialogInterface.dismiss();
                                                        RealmDatabaseManager.deleteStudentModel(studentModel);
                                                        studentAdapter.notifyDataSetChanged();
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
        recyclerView.setAdapter(studentAdapter);
        studentAdapter.notifyDataSetChanged();
    }
}