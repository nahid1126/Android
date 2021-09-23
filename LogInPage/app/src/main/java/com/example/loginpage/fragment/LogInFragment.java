package com.example.loginpage.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginpage.R;
import com.example.loginpage.model.DataModel;
import com.example.loginpage.sharedPreference.StoreData;

public class LogInFragment extends Fragment implements View.OnClickListener {

    private EditText txtUserName;
    private EditText txtUserPass;
    private Button btnLogin;

    private TextView txtName;
    private TextView txtPass;

    DataModel dataModel;
    StoreData storeData;

    public LogInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_log_in, container, false);

        storeData = new StoreData(getContext());
        txtUserName = view.findViewById(R.id.txtUserName);
        txtUserPass = view.findViewById(R.id.txtUserPass);
        btnLogin = view.findViewById(R.id.btnLogin);

        txtName = view.findViewById(R.id.txtName);
        txtPass = view.findViewById(R.id.txtPass);

        btnLogin.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        String userName = txtUserName.getText().toString();
        String userPass = txtUserPass.getText().toString();

        if (TextUtils.isEmpty(txtUserName.getText().toString()) ||
                TextUtils.isEmpty(txtUserPass.getText().toString())) {
            Toast.makeText(getContext(), "user name or password needed", Toast.LENGTH_SHORT)
                    .show();
        } else {
            dataModel = new DataModel(userName, userPass);
            storeData.setUserName(userName);
            storeData.setUserPass(userPass);

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, new DashBoardFragment())
                    .commit();

            Toast.makeText(getContext(), "LogIn Successful", Toast.LENGTH_SHORT).show();

        }
    }
}