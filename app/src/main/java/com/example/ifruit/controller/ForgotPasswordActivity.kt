package com.example.ifruit.controller

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.ifruit.R
import com.example.ifruit.database.DataBaseHelper

class ForgotPasswordActivity : AppCompatActivity() {

    var dbHandler: DataBaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        dbHandler = DataBaseHelper(this)

        val background = findViewById<RelativeLayout>(R.id.forgot_password_background)
        val activityHeader = findViewById<TextView>(R.id.fruit_header)
        val forgotPassTitle = findViewById<TextView>(R.id.forgot_pass_title)
        val phoneNum = findViewById<TextView>(R.id.phoneNum)
        val telegramID = findViewById<TextView>(R.id.telegram)
        val emailLink = findViewById<TextView>(R.id.email)


        ////////////////////////////////////////////////////////////////////////
        val getSetting = dbHandler?.readSettingInfo(1)
        // SET BACKGROUND COLOR OF COST ACTIVITY
        var Color = Color.parseColor(getSetting?.color.toString())
        background.setBackgroundColor(Color)

        // SET TEXTS FONT INSIDE COST ACTIVITY
        var fontName = getSetting?.font?.toLowerCase()
        var font = Typeface.createFromAsset(assets, "font/$fontName.ttf")
        activityHeader.typeface = font
        forgotPassTitle.typeface = font
        phoneNum.typeface = font
        telegramID.typeface = font
        emailLink.typeface = font
        ////////////////////////////////////////////////////////////////////////

//        val backButton = findViewById<ImageButton>(R.id.addcontact_back)
//        backButton.setOnClickListener {
//            val intent = Intent(this, MainMenuActivity::class.java)
//            startActivity(intent)
//            finish()
//            finish()
//        }
//
    }
}