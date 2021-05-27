package com.example.ifruit.controller

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.ifruit.R
import com.example.ifruit.database.DataBaseHelper

class SMSPanelActivity : AppCompatActivity() {

    private val PERMISSION_SEND_SMS = 0
    var number : String?=null
    var dbHandler:DataBaseHelper?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_s_m_s_panel)
        dbHandler = DataBaseHelper(this)
        val contactNumber=  intent.getStringExtra("NUMBER").toString()

        val sendButton = findViewById<ImageButton>(R.id.send_massage)
        val phoneNumber = findViewById<EditText>(R.id.phone_number)
        val textMassage = findViewById<EditText>(R.id.massage)
        val addNumImageButton = findViewById<ImageButton>(R.id.add_number)
        val addContact = findViewById<ImageButton>(R.id.add_contact)
        val background = findViewById<RelativeLayout>(R.id.background)
        val activityHeader = findViewById<TextView>(R.id.activity_title)

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

        if (phoneNumber.text!=null && contactNumber!=null){
            phoneNumber.setText(contactNumber)

        }



        sendButton.setOnClickListener {
            val text = textMassage.text
            val number = phoneNumber.text
            val phoneNumbers = number.split(",")
            requestSmsPermission(phoneNumbers, text.toString())

        }


        addNumImageButton.setOnClickListener {
           // startActivity(Intent(this,Contact::class.java))
            val intent = Intent(this,SearchViewActivity::class.java)
            intent.putExtra("NUMBER",phoneNumber.text)
            intent.putExtra("TABLE","contact")
            startActivity(intent)
            //this.finish()
        }

        addContact.setOnClickListener {
            val intent = Intent(this,AddContactActivity::class.java)
            startActivity(intent)
            this.finish()
        }
        //if (contactNumber.length > 5){

            System.out.println(number)
            phoneNumber.setText(number)
        //}

    }

    private fun requestSmsPermission(phoneNo: List<String>?, msg: String?) {

        // check permission is given
        val checkPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
        if (checkPermission == PackageManager.PERMISSION_GRANTED) {
            sendSMS(phoneNo,msg)
        } else {

            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.SEND_SMS),
                PERMISSION_SEND_SMS
            )
        }

    }

    fun sendSMS(phoneNo: List<String>?, msg: String?) {
        try {
            for (i in phoneNo!!.indices) {
                val smsManager: SmsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(phoneNo[0], null, msg, null, null)
            }
            Toast.makeText(
                applicationContext, "Message Sent",
                Toast.LENGTH_LONG
            ).show()
        } catch (ex: Exception) {
            Toast.makeText(
                applicationContext, ex.message.toString(),
                Toast.LENGTH_LONG
            ).show()
            ex.printStackTrace()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            0 -> {
                if (grantResults.size > 0 && grantResults[0] === PackageManager.PERMISSION_GRANTED) {
                    // permission was granted
                    // sendSMS("09189730663", "message")
                } else {
                    // permission denied
                }
                return
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


}