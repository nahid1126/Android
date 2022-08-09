package com.nahid.databinding.java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.nahid.databinding.databinding.ActivityJavaBinding;

public class JavaActivity extends AppCompatActivity {
    private ActivityJavaBinding binding;
    private ViewModelString viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJavaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ViewModelString.class);


        //without Using proper dataBinding
        //      viewModel.message.observe(this, s -> binding.textView.setText(s));

        //using proper DataBinding
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        binding.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModel.setMessage(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
}