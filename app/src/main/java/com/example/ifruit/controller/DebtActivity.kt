package com.example.ifruit.controller

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import com.example.annimation.CostInfo
import com.example.annimation.DebtInfo
import com.example.ifruit.R
import com.example.ifruit.database.DataBaseHelper

class DebtActivity : AppCompatActivity() {

    var dbHandler: DataBaseHelper? = null
    private var updateRequire: String? = ""
    private var debtNameUpdateData: String? = null
    private var debtPhoneUpdateData: Long? = null
    private var debtAmountUpdateData: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debt)

        updateRequire = intent.getStringExtra("UPDATE").toString()
        debtNameUpdateData = intent.getStringExtra("DEBTORNAME").toString()
        debtPhoneUpdateData = intent.getLongExtra("DEBTORPHONENUM", 0)
        debtAmountUpdateData = intent.getLongExtra("DEBTAMOUNT",0)


        val background = findViewById<RelativeLayout>(R.id.background)
        val activityHeader = findViewById<TextView>(R.id.debt_header)
        val searchButton = findViewById<ImageButton>(R.id.search_button)
        val debtorNameEditText = findViewById<EditText>(R.id.debtor_name_ac)
        val debtorAmountEditText = findViewById<EditText>(R.id.debtor_debt_ac)
        val debtorPhoneEditText = findViewById<EditText>(R.id.debtor_phone_num_ac)
        val costActivitySaveData = findViewById<ImageButton>(R.id.debt_save_btn)
        dbHandler = DataBaseHelper(this)


        ////////////////////////////////////////////////////////////////////////
        val getSetting = dbHandler?.readSettingInfo(1)
// SET BACKGROUND COLOR OF COST ACTIVITY
        var Color = Color.parseColor(getSetting?.color.toString())
        background.setBackgroundColor(Color)

// SET TEXTS FONT INSIDE COST ACTIVITY
        var fontName = getSetting?.font?.toLowerCase()
        var font = Typeface.createFromAsset(assets, "font/$fontName.ttf")
        activityHeader.typeface = font
        ////////////////////////////////////////////////////////////////////////


        if (!updateRequire.equals("update")) {
            searchButton.setOnClickListener {
                val intent = Intent(this, SearchViewActivity::class.java)
                intent.putExtra("TABLE", "debt")
                startActivity(intent)
            }
        }

        costActivitySaveData.setOnClickListener {
            System.out.println("............."+updateRequire)
            if (!TextUtils.isEmpty(debtorNameEditText.text.toString())
                && !TextUtils.isEmpty(debtorAmountEditText.text.toString())
                && !TextUtils.isEmpty(debtorPhoneEditText.text.toString())
                && !updateRequire.equals("update")
            ) {
                System.out.println("............."+updateRequire)
                insertDataToDataBase(
                    debtorNameEditText.text.toString(), debtorAmountEditText.text.toString(),
                    debtorPhoneEditText.text.toString()
                )
                Toast.makeText(this, "با موفقیت ثبت شد", Toast.LENGTH_LONG).show()

            }
            else if (!TextUtils.isEmpty(debtorNameEditText.text.toString())
                && !TextUtils.isEmpty(debtorAmountEditText.text.toString())
                && !TextUtils.isEmpty(debtorPhoneEditText.text.toString())
                && updateRequire.equals("update")
            ) {

                updateCostDataDatabase(
                    debtNameUpdateData.toString(),
                    debtPhoneUpdateData!!,
                    debtAmountUpdateData!!,
                    debtorNameEditText.text.toString(),
                    debtorPhoneEditText.text.toString(),
                    debtorAmountEditText.text.toString()
                )
            }


        }


    }
    fun insertDataToDataBase(debtName: String, debtAmount: String, debtPhone: String) {
        dbHandler = DataBaseHelper(this)

        val debtInfo = DebtInfo()
        debtInfo.Name = debtName
        debtInfo.PhoneNumber = debtPhone.toLong()
        debtInfo.DebtAmount = debtAmount.toLong()
        dbHandler?.createDebtDataBase(debtInfo)

    }

    fun updateCostDataDatabase(
        oldDebtName: String,
        oldDebtAmount: Long,
        oldDebtPhone: Long,
        newDebtName: String,
        newDebtAmount: String?,
        newDebtPhone: String
    ) {
        dbHandler = DataBaseHelper(this)

        val newDebtInfo = DebtInfo()
        val oldDebtInfo = DebtInfo()

        newDebtInfo.Name = newDebtName
        newDebtInfo.DebtAmount = newDebtAmount?.toLong()
        newDebtInfo.PhoneNumber = newDebtPhone.toLong()

        oldDebtInfo.Name = oldDebtName
        oldDebtInfo.DebtAmount = oldDebtAmount.toLong()
        oldDebtInfo.PhoneNumber = oldDebtPhone

        dbHandler?.updateDebtRow(oldDebtInfo,newDebtInfo)
    }
}