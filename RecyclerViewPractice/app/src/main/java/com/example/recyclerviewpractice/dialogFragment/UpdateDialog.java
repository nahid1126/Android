package com.example.recyclerviewpractice.dialogFragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.recyclerviewpractice.R;
import com.example.recyclerviewpractice.model.StudentModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateDialog extends DialogFragment {

    @BindView(R.id.txtUpdateName)
    EditText txtUpdateName;
    @BindView(R.id.txtUpdateMail)
    EditText txtUpdateMail;
    @BindView(R.id.txtUpdatePhone)
    EditText txtUpdatePhone;
    @BindView(R.id.txtUpdateDept)
    EditText txtUpdateDept;
    @BindView(R.id.btnUpdate)
    Button btnUpdate;


    private UpdateDialogInterface updateDialogInterface;
    private StudentModel studentModel;

    public void setUpdateDialogInterface(UpdateDialogInterface updateDialogInterface) {
        this.updateDialogInterface = updateDialogInterface;
    }

    public UpdateDialog(StudentModel studentModel) {
        this.studentModel = studentModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.update_dialog, container, false);
        ButterKnife.bind(this, view);
        //TODO
        txtUpdateName.setText(studentModel.getStudentName());
        txtUpdateMail.setText(studentModel.getStudentMail());
        txtUpdatePhone.setText(studentModel.getStudentPhone()+"");
        txtUpdateDept.setText(studentModel.getStudentDept());
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(txtUpdateName.getText().toString())||
                        TextUtils.isEmpty(txtUpdateMail.getText().toString())||
                        TextUtils.isEmpty(txtUpdatePhone.getText().toString())||
                        TextUtils.isEmpty(txtUpdateDept.getText().toString())){
                    Toast.makeText(getActivity(), "Filed must be filed", Toast.LENGTH_SHORT)
                            .show();
                }else {
                    updateDialogInterface.onUpdate(txtUpdateName.getText().toString(),
                            txtUpdateMail.getText().toString(),txtUpdatePhone.getText().toString(),
                            txtUpdateDept.getText().toString());

                }
            }
        });
        return view;
    }
}
