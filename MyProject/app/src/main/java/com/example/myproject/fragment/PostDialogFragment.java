package com.example.myproject.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostDialogFragment extends DialogFragment {

    @BindView(R.id.txtTitle)
    EditText txtTitle;
    @BindView(R.id.txtBody)
    EditText txtBody;
    @BindView(R.id.btnAdd)
    Button btnAdd;

    private PostDialogInterface postDialogInterface;



    public PostDialogFragment() {

    }
    public void setPostDialogInterface(PostDialogInterface postDialogInterface) {
        this.postDialogInterface = postDialogInterface;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);
        ButterKnife.bind(this, view);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tile = txtTitle.getText().toString();
                String body = txtBody.getText().toString();
                if (TextUtils.isEmpty(tile) || TextUtils.isEmpty(body)) {
                    Toast.makeText(getContext(), "Enter Tile and Body first",
                            Toast.LENGTH_SHORT).show();
                }else {
                    postDialogInterface.addButtonClick(tile,body);
                }
            }
        });

        return view;
    }

}