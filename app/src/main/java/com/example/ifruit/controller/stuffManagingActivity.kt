package com.example.ifruit.controller

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.get
import com.example.ifruit.R
import java.util.*

class stuffManagingActivity : AppCompatActivity() , DatePickerDialog.OnDateSetListener {
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

        //job title spinner definitions
        val job_title_spiner: Spinner = findViewById(R.id.job_title_spiner)
        val spinTitleJobList = listOf("حسابداری", "غرفه دار", "راننده")
        val spinnerAdaper = ArrayAdapter<Any?>(this@stuffManagingActivity, R.layout.support_simple_spinner_dropdown_item,
            spinTitleJobList)

        job_title_spiner.adapter = spinnerAdaper

        job_title_spiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                System.out.println(parent?.getItemAtPosition(position).toString())
    //                should be completed


            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

    }
//    Date picking time
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
