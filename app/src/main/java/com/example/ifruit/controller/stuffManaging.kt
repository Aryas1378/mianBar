package com.example.ifruit.controller

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ifruit.R
import java.util.*

class stuffManaging :AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    var day = 0
    var month = 0
    var year = 0

    var SavedDay = 0
    var SavedMonth = 0
    var SavedYear = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stuff_managing)

        pickDate()

    }

    private fun getDateTimeCalender(){
        val cal: Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)

    }

    private fun pickDate(){
        val dateBtn = findViewById(R.id.button_date) as Button
        dateBtn.setOnClickListener{

            getDateTimeCalender()
            DatePickerDialog(this, this, year, month, day).show()
        }

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        SavedDay = dayOfMonth
        SavedMonth = month
        SavedYear = year

        val btn_date = findViewById(R.id.button_date) as Button
        btn_date.text = "$SavedMonth-$SavedDay-$SavedYear"
        getDateTimeCalender()

    }

}
