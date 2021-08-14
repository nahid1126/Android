 package com.nahid.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

 public class MainActivity extends AppCompatActivity {
     private static final String MESSAGE_ID = "Messages";
     private Button saveButton;
    private EditText enterMsg;
    private TextView showMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveButton=findViewById(R.id.button);
        enterMsg=findViewById(R.id.enterMsgTxt);
        showMsg=findViewById(R.id.textView);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = enterMsg.getText().toString().trim();

                SharedPreferences sharedPreferences=getSharedPreferences(MESSAGE_ID,MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("Message",msg);
                editor.apply(); //saving to disk!

            }
        });

        //get back data from sp
        SharedPreferences getSharedData=getSharedPreferences(MESSAGE_ID,MODE_PRIVATE);
        String value=getSharedData.getString("Message","Nothing Yet");
        
        showMsg.setText(value);
    }
}