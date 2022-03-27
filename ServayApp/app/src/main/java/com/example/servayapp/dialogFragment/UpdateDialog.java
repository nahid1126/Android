package com.example.servayapp.dialogFragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.servayapp.R;
import com.example.servayapp.model.SurveyModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateDialog extends DialogFragment {

    @BindView(R.id.txtUserName)
    EditText txtUserName;
    @BindView(R.id.txtHomeDIST)
    EditText txtHomeDIST;
    @BindView(R.id.txtEducation)
    EditText txtEducation;
    @BindView(R.id.txtDOB)
    EditText txtDOB;
    @BindView(R.id.txtArea)
    EditText txtArea;
    @BindView(R.id.txtKids)
    EditText txtKids;
    @BindView(R.id.txtHouseWife)
    EditText txtHouseWife;

    @BindView(R.id.btnUpdate)
    Button btnUpdate;

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    private DialogInterface dialogInterface;
    private SurveyModel surveyModel;

    public void setDialogInterface(DialogInterface dialogInterface) {
        this.dialogInterface = dialogInterface;
    }

    public UpdateDialog(SurveyModel surveyModel) {
        this.surveyModel = surveyModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.update_dialog, container, false);
        ButterKnife.bind(this, view);

       // setupToolbar();

        txtUserName.setText(surveyModel.getSurveyName());
        txtHomeDIST.setText(surveyModel.getSurveyHomeDist());
        txtEducation.setText(surveyModel.getSurveyEducation());
        txtDOB.setText(surveyModel.getSurveyDOB());
        txtArea.setText(surveyModel.getSurveyArea());
        txtKids.setText(surveyModel.getSurveyKids());
        txtHouseWife.setText(surveyModel.getSurveyHouse());
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(txtUserName.getText().toString())) {
                    Toast.makeText(getContext(), "Name Filed must be Filed", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(txtHomeDIST.getText().toString())) {
                    Toast.makeText(getContext(), "Home Dist Filed must be Filed", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(txtEducation.getText().toString())) {
                    Toast.makeText(getContext(), "Education Filed must be Filed", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(txtDOB.getText().toString())) {
                    Toast.makeText(getContext(), "DOB Filed must be Filed", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(txtArea.getText().toString())) {
                    Toast.makeText(getContext(), "Area Filed must be Filed", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(txtKids.getText().toString())) {
                    Toast.makeText(getContext(), "Kids Filed must be Filed", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(txtKids.getText().toString())) {
                    Toast.makeText(getContext(), "Kids Filed must be Filed", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(txtHouseWife.getText().toString())) {
                    Toast.makeText(getContext(), "Wife Filed must be Filed", Toast.LENGTH_SHORT).show();
                } else {
                    dialogInterface.onUpdate(txtUserName.getText().toString(), txtHomeDIST.getText().toString(),
                            txtEducation.getText().toString(), txtDOB.getText().toString(),
                            txtArea.getText().toString(), txtKids.getText().toString(), txtHouseWife.getText().toString());
                }
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        WindowManager.LayoutParams lp = getDialog().getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes(lp);
    }

    //private void setupToolbar() {
//        setSupportActionBar(toolbar);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            toolbar.setTitleTextColor(getResources().getColor(android.R.color.white, null));
//        } else {
//            toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
//        }
//
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayShowTitleEnabled(true);
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setTitle("Survey List");
//        }
//
//        toolbar.setNavigationOnClickListener(v -> onBackPressed());
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            toolbar.getNavigationIcon().setColorFilter(new BlendModeColorFilter(Color.WHITE,
//                    BlendMode.SRC_ATOP));
//        } else {
//            toolbar.getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
//        }
    //    toolbar.setTitle("Update");
    //}
}
