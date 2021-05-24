package com.example.ifruit.controller

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.ifruit.R

class SMSPanelActivity : AppCompatActivity() {
    private val PERMISSION_SEND_SMS = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_s_m_s_panel)

        val sendBtn = findViewById<ImageButton>(R.id.send_massage)
        val phoneNumber = findViewById<EditText>(R.id.phone_number)
        val textMassage = findViewById<EditText>(R.id.massage)
        val addNumImageButton = findViewById<ImageButton>(R.id.add_number)

        sendBtn.setOnClickListener {
            var text = textMassage.text
            var number = phoneNumber.text
            requestSmsPermission(number.toString(), text.toString())

        }

        addNumImageButton.setOnClickListener {
           // startActivity(Intent(this,Contact::class.java))
            Toast.makeText(this,"jooooooooooooooooooooooooon", Toast.LENGTH_SHORT).show()
        }

    }
    private fun requestSmsPermission(phoneNo: String?, msg: String?) {

        // check permission is given
        var checkPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
        if (checkPermission == PackageManager.PERMISSION_GRANTED) {
            sendSMS(phoneNo,msg)
        } else {

            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.SEND_SMS),
                PERMISSION_SEND_SMS
            )
        }
    }
    fun sendSMS(phoneNo: String?, msg: String?) {
        try {
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNo, null, msg, null, null)
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