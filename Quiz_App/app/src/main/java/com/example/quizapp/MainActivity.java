package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.quizapp.databinding.ActivityMainBinding;
import com.example.quizapp.model.Questions;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Questions[]questions=new Questions[]{
            new Questions(R.string.first,true),
            new Questions(R.string.second,true),
            new Questions(R.string.third,true),
            new Questions(R.string.fourth,true),
            new Questions(R.string.fifth,true),
            new Questions(R.string.sixth,true),
            new Questions(R.string.seventh,true)
    };
    private int currentIndex=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        binding.questions.setText(questions[currentIndex].getQusID());


        binding.trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        binding.falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        binding.prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentIndex>0){
                    currentIndex=(currentIndex-1)%questions.length;
                    updateQuestions();
                }
            }
        });
        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex=(currentIndex+1)%questions.length;
                updateQuestions();
            }
        });
    }

    private void checkAnswer(boolean userChose) {
        boolean ansISTrue=questions[currentIndex].isAnsTrue();
        int ansID;
        if (ansISTrue==userChose){
            ansID=R.string.right;
        }else{
            ansID=R.string.wrong;
        }
        Snackbar.make(binding.imageView,ansID,Snackbar.LENGTH_SHORT).show();
    }

    private void updateQuestions() {
        binding.questions.setText(questions[currentIndex].getQusID());
    }
}