package com.example.ifruit.controller

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.ifruit.R
import com.example.ifruit.database.DataBaseHelper

class ManagementActivity : AppCompatActivity() {
    var dbHandler: DataBaseHelper? = null
    private lateinit var staff_btn: ImageButton
    private lateinit var sms_btn: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_management)

        dbHandler = DataBaseHelper(this)
        val background = findViewById<LinearLayout>(R.id.management_background)
        val activityHeader = findViewById<TextView>(R.id.management_header)

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

        initViews()


        staff_btn.setOnClickListener {
            val accountsIntent = Intent(this, StaffManagingActivity::class.java)
            startActivity(accountsIntent)
//            finish()
        }
        sms_btn.setOnClickListener {
            val accountsIntent = Intent(this, SMSPanelActivity::class.java)
            startActivity(accountsIntent)
//            finish()
        }


    }



    private fun initViews() {

        staff_btn=findViewById(R.id.staff_mng_btn) as ImageButton
        sms_btn=findViewById(R.id.sms_panel_btn) as ImageButton
    }

}