package com.nahid.databinding.java;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelString extends ViewModel {

    public MutableLiveData<String> message = new MutableLiveData<>();

    public void setMessage(String message) {
        this.message.setValue(message);
    }
}
