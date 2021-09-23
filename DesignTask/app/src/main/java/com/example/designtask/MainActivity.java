package com.example.designtask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.designtask.model.ModelClass;
import com.example.designtask.sharedPreferences.SharedPreferencesDemo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String msg = "Message";
    ModelClass modelClass;
    SharedPreferencesDemo sharedpreferencesDemo;

    @BindView(R.id.txtName)
    EditText txtName;
    @BindView(R.id.txtAge)
    EditText txtAge;
    @BindView(R.id.showName)
    TextView showName;
    @BindView(R.id.showAge)
    TextView showAge;


    @OnClick(R.id.btnSubmit)
    public void onClick() {
        String name = txtName.getText().toString();
        String age = txtAge.getText().toString();

        if (TextUtils.isEmpty(txtName.getText().toString())) {
            Toast.makeText(MainActivity.this, "Enter Name First", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(txtAge.getText().toString())) {
            Toast.makeText(MainActivity.this, "Enter Age First", Toast.LENGTH_SHORT).show();
        }else {
            modelClass=new ModelClass(name,Integer.parseInt(age));
            sharedpreferencesDemo.setName(name);
            sharedpreferencesDemo.setAge(Integer.parseInt(age));
            clear();
            Toast.makeText(MainActivity.this, "Submit", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        sharedpreferencesDemo=new SharedPreferencesDemo(MainActivity.this);

        showName.setText(sharedpreferencesDemo.getName());
        showAge.setText(sharedpreferencesDemo.getAge()+"");
    }

    private void clear() {
        txtAge.setText("");
        txtName.setText("");
    }
}