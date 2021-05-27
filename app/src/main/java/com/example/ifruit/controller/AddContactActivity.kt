package com.example.ifruit.controller

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import com.example.annimation.ContactInfo
import com.example.annimation.CostInfo
import com.example.ifruit.R
import com.example.ifruit.database.DataBaseHelper

class AddContactActivity : AppCompatActivity() {

    var dbHandler: DataBaseHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        dbHandler=DataBaseHelper(this)

        val addContactHeader = findViewById<TextView>(R.id.contact_header)
        val addContactEditText = findViewById<EditText>(R.id.contact_name_ac)
        val addContactTitle = findViewById<EditText>(R.id.contact_title_ac)
        val addContactPhoneNumber = findViewById<EditText>(R.id.contact_phone_number_ac)
        val saveContactData = findViewById<ImageButton>(R.id.contact_save_btn)
        val background = findViewById<RelativeLayout>(R.id.background)

        val getSetting = dbHandler?.readSettingInfo(1)
        // SET BACKGROUND COLOR OF COST ACTIVITY
        var color = Color.parseColor(getSetting?.color.toString())
        background.setBackgroundColor(color)

        // SET TEXTS FONT INSIDE COST ACTIVITY
        var fontName = getSetting?.font?.toLowerCase()
        var font = Typeface.createFromAsset(assets, "font/$fontName.ttf")
        addContactHeader.typeface = font


        saveContactData.setOnClickListener {
            if (!TextUtils.isEmpty(addContactEditText.text.toString())
                && !TextUtils.isEmpty(addContactTitle.text.toString())
                && !TextUtils.isEmpty(addContactPhoneNumber.text.toString())

            ) {
              //  System.out.println("............."+updateRequire)
                insertDataToDataBase(
                    addContactEditText.text.toString(), addContactTitle.text.toString(),
                    addContactPhoneNumber.text.toString()
                )
                Toast.makeText(this, "با موفقیت ثبت شد", Toast.LENGTH_LONG).show()

            }
        }
    }

    fun insertDataToDataBase(addContactText: String, addContactTitle: String, addContactPhoneNumber: String) {
        dbHandler = DataBaseHelper(this)

        val contactInfo = ContactInfo()
        contactInfo.firstName = addContactText
        contactInfo.title = addContactTitle
        contactInfo.phoneNumber = addContactPhoneNumber.toLong()
        dbHandler?.createContactInfo(contactInfo)

    }
}