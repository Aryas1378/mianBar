package com.example.ifruit.controller

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.annimation.EmployeeManagementInfo
import com.example.ifruit.R
import com.example.ifruit.database.DataBaseHelper
import java.util.*

class StaffManagingActivity : AppCompatActivity() , DatePickerDialog.OnDateSetListener {
    var day = 0
    var month = 0
    var year = 0

    var SavedDay = 0
    var SavedMonth = 0
    var SavedYear = 0
    var dateValue:String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stuff_managing)
        var spinnerRes:String ?= null
        var dbHandler = DataBaseHelper(this)
        var newStaff = EmployeeManagementInfo()
        val saveBtn = findViewById<ImageButton>(R.id.stuff_sub_btn)
        val staffName = findViewById<EditText>(R.id.stuff_name)
        val staffPhone = findViewById<EditText>(R.id.stuff_phone_number)
        val staffAmount = findViewById<EditText>(R.id.stuff_salary)

        pickDate()

        //job title spinner definitions
        val job_title_spiner: Spinner = findViewById(R.id.job_title_spiner)
        val spinTitleJobList = listOf("حسابداری", "غرفه دار", "راننده")
        val spinnerAdaper = ArrayAdapter<Any?>(this@StaffManagingActivity, R.layout.support_simple_spinner_dropdown_item,
            spinTitleJobList)

        job_title_spiner.adapter = spinnerAdaper

        job_title_spiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                spinnerRes = parent?.getItemAtPosition(position).toString()

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
         saveBtn.setOnClickListener {

             if(staffName.text.isEmpty() or staffAmount.text.isEmpty() or staffPhone.text.isEmpty()){
                 Toast.makeText(this@StaffManagingActivity, "اطلاعات را کامل کنید", Toast.LENGTH_LONG).show()
             }
             else{
                 newStaff.firstName = staffName.text.toString()
                 newStaff.phoneNumber = staffPhone!!.text.toString().toLong()
                 newStaff.dateOfEmployee = dateValue
                 newStaff.salary = staffAmount.text.toString().toLong()
                 newStaff.jobTitle = spinnerRes
                 dbHandler?.createEmployeeManagementInfo(newStaff)
                 Toast.makeText(this@StaffManagingActivity, "ذخیره شد", Toast.LENGTH_LONG).show()
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
        dateValue = "$SavedMonth-$SavedDay-$SavedYear"
        getDateTimeCalender()

    }

}
