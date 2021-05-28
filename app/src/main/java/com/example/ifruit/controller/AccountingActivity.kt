package com.example.ifruit.controller

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.ifruit.R
import com.example.ifruit.database.DataBaseHelper

class AccountingActivity : AppCompatActivity() {

    var dbHandler: DataBaseHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accounting)

        dbHandler = DataBaseHelper(this)

        val activityHeader = findViewById<TextView>(R.id.accounting_title)
        val background = findViewById<ScrollView>(R.id.background)
        val costBtn = findViewById<ImageButton>(R.id.fee)
        val incomeBtn = findViewById<ImageButton>(R.id.income)
        val salaryBtn = findViewById<ImageButton>(R.id.salary)
        val debtBtn = findViewById<ImageButton>(R.id.debt)
        val contractBtn = findViewById<ImageButton>(R.id.contract)
        val fruitInfoBtn = findViewById<ImageButton>(R.id.fruitinfo)
        val invoiceActivity = findViewById<ImageButton>(R.id.invoice)

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

        costBtn.setOnClickListener {
            val intent = Intent(this, CostActivity::class.java)
            startActivity(intent)
        }

        incomeBtn.setOnClickListener {
            Toast.makeText(this, "income", Toast.LENGTH_SHORT).show()
              var intent = Intent(this,IncomeActivity::class.java)
               startActivity(intent)
        }

        salaryBtn.setOnClickListener {
            Toast.makeText(this, "salary", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SalaryActivity::class.java)
            startActivity(intent)

        }

        debtBtn.setOnClickListener {
            Toast.makeText(this, "debt/credit", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, DebtActivity::class.java)
            startActivity(intent)
        }

        contractBtn.setOnClickListener {
            val intent = Intent(this, ContractActivity::class.java)
            startActivity(intent)
        }

        fruitInfoBtn.setOnClickListener {
            Toast.makeText(this, "fruit information", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, FruitInformationActivity::class.java)
            startActivity(intent)
        }
        invoiceActivity.setOnClickListener {
            Toast.makeText(this, "fruit information", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, InvoiceActivity::class.java)
            startActivity(intent)
        }

    }
}