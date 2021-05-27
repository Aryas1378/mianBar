package com.example.ifruit.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.ifruit.R

class ManagementActivity : AppCompatActivity() {

    private lateinit var staff_btn: ImageButton
    private lateinit var sms_btn: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_management)

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