package com.example.loginpage.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginpage.R;
import com.example.loginpage.sharedPreference.StoreData;

public class DashBoardFragment extends Fragment implements View.OnClickListener {

    TextView txtName;
    TextView txtPass;
    Button btnLogout;

    StoreData storeData;

    public DashBoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dash_board, container, false);


        txtName = view.findViewById(R.id.txtName);
        txtPass = view.findViewById(R.id.txtPass);
        btnLogout=view.findViewById(R.id.btnLogout);

        storeData = new StoreData(getContext());
        txtName.setText(storeData.getUserName());
        txtPass.setText(storeData.getUserPass());

        btnLogout.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        storeData.setUserName("");

        storeData.setUserPass("");

       // storeData.clearData();

        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,
                new LogInFragment()).commit();
        Toast.makeText(getContext(), "LogOut Successful", Toast.LENGTH_SHORT).show();
    }
}