package com.example.ifruit.controller

import android.app.DatePickerDialog
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.annimation.EmployeeManagementInfo
import com.example.ifruit.R
import com.example.ifruit.database.DataBaseHelper
import java.util.*

class StaffManagingActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    var day = 0
    var month = 0
    var year = 0

    var SavedDay = 0
    var SavedMonth = 0
    var SavedYear = 0

    var dateValue: String? = null
    var dbHandler: DataBaseHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.staff_managing)

        var spinnerRes: String? = null
        dbHandler = DataBaseHelper(this)
        var newStaff = EmployeeManagementInfo()
        val saveBtn = findViewById<ImageButton>(R.id.stuff_sub_btn)
        val staffName = findViewById<EditText>(R.id.stuff_name)
        val staffPhone = findViewById<EditText>(R.id.stuff_phone_number)
        val staffAmount = findViewById<EditText>(R.id.stuff_salary)
        val searchBtn = findViewById<ImageButton>(R.id.search_button)

        pickDate()

        val activityHeader = findViewById<TextView>(R.id.employee_header)
        val dataText = findViewById<TextView>(R.id.date_text_view)
        val background = findViewById<RelativeLayout>(R.id.background)

        ///////////////////////////////////////////////////////////////////////
        val getSetting = dbHandler?.readSettingInfo(1)
        // SET BACKGROUND COLOR OF COST ACTIVITY
        var Color = Color.parseColor(getSetting?.color.toString())
        background.setBackgroundColor(Color)


        // SET TEXTS FONT INSIDE COST ACTIVITY
        var fontName = getSetting?.font?.toLowerCase()
        var font = Typeface.createFromAsset(assets, "font/$fontName.ttf")
        activityHeader.typeface = font
        dataText.typeface = font
        ///////////////////////////////////////////////////////////////////////////////
        val backButton = findViewById<ImageButton>(R.id.employee_back)
        backButton.setOnClickListener {
            val intent = Intent(this, ManagementActivity::class.java)
            startActivity(intent)
            finish()
            finish()
        }

        //job title spinner definitions
        val job_title_spiner: Spinner = findViewById(R.id.job_title_spiner)
        val spinTitleJobList = listOf("????????????????", "???????? ??????", "????????????")
        val spinnerAdaper = ArrayAdapter<Any?>(
            this@StaffManagingActivity, R.layout.support_simple_spinner_dropdown_item,
            spinTitleJobList
        )

        job_title_spiner.adapter = spinnerAdaper

        job_title_spiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ){spinnerRes = parent?.getItemAtPosition(position).toString()}

        }

        saveBtn.setOnClickListener {
            var staffFound:Boolean = false
            val getStuffInfo: Cursor? = dbHandler?.getEmployeeManagementTableRow()
            getStuffInfo!!.moveToFirst()
            while (getStuffInfo.moveToNext()){
                
                if(getStuffInfo.getString(1).equals(staffName.text.toString()) and getStuffInfo.getString(2).equals(staffPhone.text.toString())) {

                    Toast.makeText(
                        this@StaffManagingActivity,
                        "?????????? ???????? ?????? ??????!",
                        Toast.LENGTH_LONG
                    ).show()
                    break
                    staffFound = true
                }
            }
            if (!staffFound) {
                if (staffName.text.isEmpty() or staffAmount.text.isEmpty() or staffPhone.text.isEmpty()) {
                    Toast.makeText(
                        this@StaffManagingActivity,
                        "???????? ?????????????? ???? ???????? ????????",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    newStaff.firstName = staffName.text.toString()
                    newStaff.phoneNumber = staffPhone!!.text.toString().toLong()
                    newStaff.dateOfEmployee = dateValue
                    newStaff.salary = staffAmount.text.toString().toLong()
                    newStaff.jobTitle = spinnerRes
                    dbHandler?.createEmployeeManagementInfo(newStaff)
                    Toast.makeText(this@StaffManagingActivity, "?????????? ????", Toast.LENGTH_LONG).show()
                }
            }

        }
        searchBtn.setOnClickListener {
            val intent = Intent(this, SearchViewActivity::class.java)
            intent.putExtra("TABLE", "employee")
            startActivity(intent)
        }

    }
    //    Date picking time
    private fun getDateTimeCalender() {
        val cal: Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)

    }

    private fun pickDate() {
        val dateBtn = findViewById(R.id.button_date) as Button
        dateBtn.setOnClickListener {

            getDateTimeCalender()
            DatePickerDialog(this, this, year, month, day).show()
        }

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        SavedDay = dayOfMonth
        SavedMonth = month
        SavedYear = year

        val btn_date = findViewById(R.id.button_date) as Button
//        "$year-"+(month+1).toString()+"-$dayOfMonth"
//        btn_date.text = "$SavedMonth-$SavedDay-$SavedYear"
        btn_date.text = "$SavedYear-" + (month + 1).toString() + "-$SavedDay"
        dateValue = "$SavedYear-" + (month + 1).toString() + "-$SavedDay"
        getDateTimeCalender()

    }
}