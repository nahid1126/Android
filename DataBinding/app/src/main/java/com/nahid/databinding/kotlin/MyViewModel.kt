package com.nahid.databinding.kotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    val msg = MutableLiveData<String>()

    fun setMsg(txt: String) {
        msg.value = txt
    }

}