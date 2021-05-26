package com.example.ifruit.controller

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.example.annimation.CostInfo
import com.example.ifruit.R
import com.example.ifruit.database.DataBaseHelper

class CostActivity : AppCompatActivity() {

    var dbHandler: DataBaseHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cost)

        val updateRequire: String = intent.getStringExtra("UPDATE").toString()
        val background = findViewById<RelativeLayout>(R.id.background)
        val activityHeader = findViewById<TextView>(R.id.cost_header)
        val searchButton = findViewById<ImageButton>(R.id.search_button)
        val costReasonEditText = findViewById<EditText>(R.id.feereason_ac)
        val costAmountEditText = findViewById<EditText>(R.id.feeamont_ac)
        val costDateEditText = findViewById<EditText>(R.id.date_ac)
        val costActivitySaveData = findViewById<ImageButton>(R.id.savebtn_ac)
        dbHandler = DataBaseHelper(this)

        searchButton.setOnClickListener {
            val intent = Intent(this,SearchViewActivity::class.java)
            intent.putExtra("TABLE","cost")
            startActivity(intent)
        }

        costActivitySaveData.setOnClickListener {
            if (!TextUtils.isEmpty(costReasonEditText.text.toString())
                && !TextUtils.isEmpty(costAmountEditText.text.toString())
                && !TextUtils.isEmpty(costDateEditText.text.toString())
            ) {
                insertDataToDataBase(
                    costReasonEditText.text.toString(), costAmountEditText.text.toString(),
                    costDateEditText.text.toString()
                )
                Toast.makeText(this, "با موفقیت ثبت شد", Toast.LENGTH_LONG).show()
            }


        }

    }
    fun insertDataToDataBase(costReason: String, costAmount: String, date: String) {

        var costinfo = CostInfo()
        costinfo.reason = costReason
        costinfo.amount = costAmount.toLong()
        costinfo.date = date
        dbHandler?.createCostInfo(costinfo)

    }

}
