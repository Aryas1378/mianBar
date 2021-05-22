package com.example.ifruit.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.ifruit.R

class BackUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_back_up)
        val radioGp = findViewById<RadioGroup>(R.id.radio_group)
        radioGp.setOnCheckedChangeListener { group, checkedId ->

            val radio: RadioButton = findViewById(checkedId)
            Log.d("joooooooooooon", "${radio.text}")
            Toast.makeText(this, "${radio.text}", Toast.LENGTH_LONG).show()

        }
    }
}