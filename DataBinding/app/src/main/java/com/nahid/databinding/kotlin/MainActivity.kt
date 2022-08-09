package com.nahid.databinding.kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.ViewModelProvider
import com.nahid.databinding.R
import com.nahid.databinding.databinding.ActivityMainBinding
import com.nahid.databinding.java.JavaActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        //without Using proper dataBinding
        /* myViewModel.msg.observe(this) {
             binding.textView.text = it
         }*/

        //using proper DataBinding
        binding.viewModel = myViewModel
        binding.lifecycleOwner = this

        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {
                myViewModel.setMsg(char.toString())
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.btnNext.setOnClickListener { startActivity(Intent(this, JavaActivity::class.java)) }

        val user = User("Nahid", "Hasan", 25, true)
        binding.user = user
    }
}