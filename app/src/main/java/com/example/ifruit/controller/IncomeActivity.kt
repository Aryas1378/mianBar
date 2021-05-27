package com.example.ifruit.controller

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ifruit.R
import com.example.ifruit.database.DataBaseHelper

class IncomeActivity : AppCompatActivity() {
    var dbHandler: DataBaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income)

        dbHandler = DataBaseHelper(this)

        val activityHeader = findViewById<TextView>(R.id.income_header)
        val background = findViewById<ConstraintLayout>(R.id.background)
        val startDateTextView = findViewById<TextView>(R.id.startDate_textview)
        val endDateTextView = findViewById<TextView>(R.id.endtDate_textview)
        val IncomeTextView = findViewById<TextView>(R.id.income_textview)
        val profiTextView = findViewById<TextView>(R.id.profit_textview)
        val lossTextView = findViewById<TextView>(R.id.loss_textview)

        ////////////////////////////////////////////////////////////////////////
        val getSetting = dbHandler?.readSettingInfo(1)
        // SET BACKGROUND COLOR OF COST ACTIVITY
        var Color = Color.parseColor(getSetting?.color.toString())
        background.setBackgroundColor(Color)

        // SET TEXTS FONT INSIDE COST ACTIVITY
        var fontName = getSetting?.font?.toLowerCase()
        var font = Typeface.createFromAsset(assets, "font/$fontName.ttf")
        activityHeader.typeface = font
        startDateTextView.typeface = font
        endDateTextView.typeface = font
        IncomeTextView.typeface = font
        profiTextView.typeface = font
        lossTextView.typeface = font
        ////////////////////////////////////////////////////////////////////////


    }
}