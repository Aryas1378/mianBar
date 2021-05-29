package com.example.ifruit.controller

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import com.example.annimation.CostInfo
import com.example.annimation.SalaryInfo
import com.example.ifruit.R
import com.example.ifruit.database.DataBaseHelper

class SalaryActivity : AppCompatActivity() {

    var dbHandler: DataBaseHelper? = null
    private var updateRequire: String? = ""
    private var salaryNameUpdateData: String? = null
    private var salarySalaryUpdateData: Int? = null
    private var salaryPhoneNumberUpdateData: Long? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salary)

        updateRequire = intent.getStringExtra("UPDATE").toString()
        salaryNameUpdateData = intent.getStringExtra("SALARYNAME").toString()
        salarySalaryUpdateData = intent.getIntExtra("SALARYSALRY",0)
        salaryPhoneNumberUpdateData = intent.getLongExtra("SLARYPHONE",0)


        val background = findViewById<RelativeLayout>(R.id.background)
        val activityHeader = findViewById<TextView>(R.id.salary_header)
        val searchButton = findViewById<ImageButton>(R.id.search_button)
        val salaryNameEditText = findViewById<EditText>(R.id.salary_name_ac)
        val salarySalaryEditText = findViewById<EditText>(R.id.salary_salary_ac)
        val salaryPhoneNumEditText = findViewById<EditText>(R.id.salary_phone_ac)
        val salaryActivitySaveData = findViewById<ImageButton>(R.id.salary_save_btn)

        dbHandler = DataBaseHelper(this)

        ///////////////////////////////////////////////////////////////////
        val getSetting = dbHandler?.readSettingInfo(1)
        // SET BACKGROUND COLOR OF COST ACTIVITY
        var Color = Color.parseColor(getSetting?.color.toString())
        background.setBackgroundColor(Color)

        // SET TEXTS FONT INSIDE COST ACTIVITY
        var fontName = getSetting?.font?.toLowerCase()
        var font = Typeface.createFromAsset(assets, "font/$fontName.ttf")
        activityHeader.typeface = font
        //////////////////////////////////////////////////////////////////

        val backButton = findViewById<ImageButton>(R.id.salary_back)
        backButton.setOnClickListener {
            val intent = Intent(this, AccountingActivity::class.java)
            startActivity(intent)
            finish()
            finish()
        }



        if (!updateRequire.equals("update")) {
            searchButton.setOnClickListener {
                val intent = Intent(this, SearchViewActivity::class.java)
                intent.putExtra("TABLE", "salary")
                startActivity(intent)
            }
        }

        salaryActivitySaveData.setOnClickListener {
            System.out.println("............."+updateRequire)
            if (!TextUtils.isEmpty(salaryNameEditText.text.toString())
                && !TextUtils.isEmpty(salarySalaryEditText.text.toString())
                && !TextUtils.isEmpty(salaryPhoneNumEditText.text.toString())
                && !updateRequire.equals("update")
            ) {
                System.out.println("............."+updateRequire)
                insertDataToDataBase(
                    salaryNameEditText.text.toString(), salarySalaryEditText.text.toString(),
                    salaryPhoneNumEditText.text.toString()
                )
                Toast.makeText(this, "با موفقیت ثبت شد", Toast.LENGTH_LONG).show()

            }
            else if (!TextUtils.isEmpty(salaryNameEditText.text.toString())
                && !TextUtils.isEmpty(salarySalaryEditText.text.toString())
                && !TextUtils.isEmpty(salaryPhoneNumEditText.text.toString())
                && updateRequire.equals("update")
            ) {

                updateSalaryDataDatabase(
                    salaryNameUpdateData.toString(),
                    salarySalaryUpdateData!!.toInt(),
                    salaryPhoneNumberUpdateData!!.toLong(),
                    salaryNameEditText.text.toString(),
                    salarySalaryEditText.text.toString(),
                    salaryPhoneNumEditText.text.toString()
                )
                Toast.makeText(this, "با موفقیت ویرایش شد", Toast.LENGTH_LONG).show()
            }


        }

    }
    fun insertDataToDataBase(salaryName: String, salary: String, salaryPhone: String) {
        dbHandler = DataBaseHelper(this)

        val salaryInfo = SalaryInfo()
        salaryInfo.name = salaryName
        salaryInfo.salary = salary.toInt()
        salaryInfo.phoneNumber = salaryPhone.toLong()
        dbHandler?.addSalary(salaryInfo)

    }

    fun updateSalaryDataDatabase(
        oldSalaryName: String,
        oldSalarySalary: Int,
        oldSalaryPhone: Long,
        newSalaryName: String,
        newSalarySalary: String,
        newSalaryPhone: String
    ) {
        dbHandler = DataBaseHelper(this)

        val newSalaryInfo = SalaryInfo()
        val oldSalaryInfo = SalaryInfo()

        newSalaryInfo.name = newSalaryName
        newSalaryInfo.salary = newSalarySalary.toInt()
        newSalaryInfo.phoneNumber = newSalaryPhone.toLong()

        oldSalaryInfo.name = oldSalaryName
        oldSalaryInfo.salary = oldSalarySalary
        oldSalaryInfo.phoneNumber = oldSalaryPhone

        dbHandler?.updateSalaryRowData(oldSalaryInfo,newSalaryInfo)
    }
}