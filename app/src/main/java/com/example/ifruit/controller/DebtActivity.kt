package com.example.ifruit.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.annimation.DebtInfo
import com.example.ifruit.R
import com.example.ifruit.database.DataBaseHelper

class DebtActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debt)
        var dbHandler = DataBaseHelper(this)
        val newDebt = DebtInfo()
        val DebtNameField = findViewById<EditText>(R.id.debtor_name_ac)
        val DebtPhoneFiled = findViewById<EditText>(R.id.debtor_phone_num_ac)
        val DebtAmountField = findViewById<EditText>(R.id.debtor_debt_ac)
        val saveBtn = findViewById<ImageButton>(R.id.savebtn_ac)

        saveBtn.setOnClickListener {
            if (DebtNameField.text.isEmpty() or DebtAmountField.text.isEmpty() or DebtPhoneFiled.text.isEmpty()){
                Toast.makeText(this@DebtActivity, "اطلاعات را وارد کنید", Toast.LENGTH_LONG).show()
            }
            else{
                newDebt.Name = DebtNameField.text.toString()
                newDebt.PhoneNumber = DebtPhoneFiled.text.toString().toLong()
                newDebt.DebtAmount = DebtAmountField.text.toString().toLong()
                Toast.makeText(this@DebtActivity, "ذخیره شد", Toast.LENGTH_LONG).show()
                dbHandler?.createDebtDataBase(newDebt)
            }
        }




    }
}