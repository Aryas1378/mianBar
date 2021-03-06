package com.example.ifruit.controller

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
    private var updateRequire: String? = ""
    private var costReasonUpdateData: String? = null
    private var costAmountUpdateData: Long? = null
    private var costDateUpdateData: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cost)

        updateRequire = intent.getStringExtra("UPDATE").toString()
        costReasonUpdateData = intent.getStringExtra("COSTREASON").toString()
        costAmountUpdateData = intent.getLongExtra("COSTAMOUNT", 0)
        costDateUpdateData = intent.getStringExtra("COSTDATE")


        val background = findViewById<RelativeLayout>(R.id.background)
        val activityHeader = findViewById<TextView>(R.id.cost_header)
        val searchButton = findViewById<ImageButton>(R.id.search_button)
        val costReasonEditText = findViewById<EditText>(R.id.feereason_ac)
        val costAmountEditText = findViewById<EditText>(R.id.feeamont_ac)
        val costDateEditText = findViewById<EditText>(R.id.date_ac)
        val costActivitySaveData = findViewById<ImageButton>(R.id.cost_save_btn)
        dbHandler = DataBaseHelper(this)


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

        val backButton = findViewById<ImageButton>(R.id.cost_back)
        backButton.setOnClickListener {
            val intent = Intent(this, AccountingActivity::class.java)
            startActivity(intent)
            finish()
            finish()
        }


        if (!updateRequire.equals("update")) {
            searchButton.setOnClickListener {
                val intent = Intent(this, SearchViewActivity::class.java)
                intent.putExtra("TABLE", "cost")
                startActivity(intent)
            }
        }

        costActivitySaveData.setOnClickListener {
            System.out.println("............."+updateRequire)
            if (!TextUtils.isEmpty(costReasonEditText.text.toString())
                && !TextUtils.isEmpty(costAmountEditText.text.toString())
                && !TextUtils.isEmpty(costDateEditText.text.toString())
                && !updateRequire.equals("update")
            ) {
                System.out.println("............."+updateRequire)
                insertDataToDataBase(
                    costReasonEditText.text.toString(), costAmountEditText.text.toString(),
                    costDateEditText.text.toString()
                )
                Toast.makeText(this, "???? ???????????? ?????? ????", Toast.LENGTH_LONG).show()

            }
            else if (!TextUtils.isEmpty(costReasonEditText.text.toString())
                && !TextUtils.isEmpty(costAmountEditText.text.toString())
                && !TextUtils.isEmpty(costDateEditText.text.toString())
                && updateRequire.equals("update")
            ) {

                updateCostDataDatabase(
                    costReasonEditText.text.toString(),
                    costAmountEditText.text.toString(),
                    costDateEditText.text.toString(),
                    costReasonUpdateData.toString(),
                    costAmountUpdateData,
                    costDateUpdateData.toString()
                )
                Toast.makeText(this, "???? ???????????? ???????????? ????", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this, "???????? ???????? ???? ???? ???????? ????????", Toast.LENGTH_LONG).show()
            }


        }

    }

    fun insertDataToDataBase(costReason: String, costAmount: String, costDate: String) {
        dbHandler = DataBaseHelper(this)

        val costInfo = CostInfo()
        costInfo.reason = costReason
        costInfo.amount = costAmount.toLong()
        costInfo.date = costDate
        dbHandler?.createCostInfo(costInfo)

    }

    fun updateCostDataDatabase(
        newCostReason: String,
        newCostAmount: String,
        newCostDate: String,
        oldCostReason: String,
        oldCostAmount: Long?,
        oldCostDate: String
    ) {
        dbHandler = DataBaseHelper(this)

        val newCostInfo = CostInfo()
        val oldCostInfo = CostInfo()

        newCostInfo.reason = newCostReason
        newCostInfo.amount = newCostAmount.toLong()
        newCostInfo.date = newCostDate

        oldCostInfo.reason = oldCostReason
        oldCostInfo.amount = oldCostAmount
        oldCostInfo.date = oldCostDate

        dbHandler?.updateCostRow(oldCostInfo,newCostInfo)
    }

}
