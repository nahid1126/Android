package com.example.recyclerviewpractice.databasemanager;

import android.util.Log;
import android.widget.Toast;

import com.example.recyclerviewpractice.adapter.StudentAdapter;
import com.example.recyclerviewpractice.model.StudentModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmDatabaseManager{

    private static final String TAG = "RealmDatabaseManager";
    private static Realm realm;

    public RealmDatabaseManager() {
        realm = Realm.getDefaultInstance();
    }
    public static void insertStudentModel(StudentModel studentModel){
        if(realm == null){
            realm = Realm.getDefaultInstance();
        }
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    realm.copyToRealmOrUpdate(studentModel);
                }catch (Exception e){
                    Log.e(TAG, "execute: ", e);
                }
            }
        });
    }

    public static List<StudentModel> getStudentModelList(){
        realm=Realm.getDefaultInstance();
        try {
            //return realm.copyFromRealm(realm.where(StudentModel.class).findAll());
            RealmResults<StudentModel> studentModels=realm.where(StudentModel.class).findAll();
            return studentModels;
        }catch (Exception e){
            Log.d(TAG,"Insert"+e);
        }
        return null;
    }

    public static boolean updateStudentModel(StudentModel studentModel,String n, String m, String p, String d){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                StudentModel updateStudent = realm.where(StudentModel.class)
                        .equalTo("studentId", studentModel
                                .getStudentId()).findFirst();
                updateStudent.setStudentDept(d);
                updateStudent.setStudentPhone(p);
                updateStudent.setStudentName(n);
                updateStudent.setStudentMail(m);

            }

        });

        return true;
    }

    public static void deleteStudentModel(StudentModel studentModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                StudentModel deleteStudent = realm.where(StudentModel.class).equalTo("studentId", studentModel.getStudentId()).findFirst();
                deleteStudent.deleteFromRealm();
            }
        });
    }

}
