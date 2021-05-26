package com.example.ifruit.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import com.example.ifruit.R

class AccountingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accounting)

        val costBtn = findViewById<ImageButton>(R.id.fee)
        val incomeBtn = findViewById<ImageButton>(R.id.income)
        val salaryBtn = findViewById<ImageButton>(R.id.salary)
        val debtBtn = findViewById<ImageButton>(R.id.debt)
        val contractBtn = findViewById<ImageButton>(R.id.contract)
        val fruitInfoBtn = findViewById<ImageButton>(R.id.fruitinfo)

        costBtn.setOnClickListener {
            val intent = Intent(this,CostActivity::class.java)
            startActivity(intent)
        }

        incomeBtn.setOnClickListener {
            Toast.makeText(this,"income", Toast.LENGTH_SHORT).show()
          //  var intent = Intent(this,Setting::class.java)
         //   startActivity(intent)
        }

        salaryBtn.setOnClickListener {
            Toast.makeText(this,"salary", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,SalaryActivity::class.java)
            startActivity(intent)

        }

        debtBtn.setOnClickListener {
            Toast.makeText(this,"debt/credit", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,DebtActivity::class.java)
            startActivity(intent)
        }

        contractBtn.setOnClickListener {
            val intent = Intent(this,ContractActivity::class.java)
            startActivity(intent)
        }

        fruitInfoBtn.setOnClickListener {
            Toast.makeText(this,"fruit information", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,FruitInformationActivity::class.java)
            startActivity(intent)
        }

    }
}