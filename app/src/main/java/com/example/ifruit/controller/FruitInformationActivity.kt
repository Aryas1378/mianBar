package com.example.ifruit.controller

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import com.example.annimation.CostInfo
import com.example.annimation.FruitInfo
import com.example.ifruit.R
import com.example.ifruit.database.DataBaseHelper

class FruitInformationActivity : AppCompatActivity() {

    var dbHandler: DataBaseHelper? = null
    private var updateRequire: String? = ""
    private var fruitNameUpdateData: String? = null
    private var fruitPriceUpdateData: Int? = null
    private var fruitQltUpdateData: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit_information)

        updateRequire = intent.getStringExtra("UPDATE").toString()
        fruitNameUpdateData = intent.getStringExtra("FRUITNAME").toString()
        fruitPriceUpdateData = intent.getIntExtra("FRUITPRICE", 0)
        fruitQltUpdateData = intent.getIntExtra("FRUITQLT",0)


        val background = findViewById<RelativeLayout>(R.id.background)
        val activityHeader = findViewById<TextView>(R.id.fruit_header)
        val searchButton = findViewById<ImageButton>(R.id.search_button)
        val fruitNameEditText = findViewById<EditText>(R.id.fruit_name_ac)
        val fruitPriceEditText = findViewById<EditText>(R.id.fruit_price_ac)
        val fruitQltEditText = findViewById<EditText>(R.id.fruit_qlt_ac)
        val fruitActivitySaveData = findViewById<ImageButton>(R.id.fruit_save_btn)
        dbHandler = DataBaseHelper(this)

        ////////////////////////////////////////////////////////////////
        val getSetting = dbHandler?.readSettingInfo(1)
        // SET BACKGROUND COLOR OF COST ACTIVITY
        var Color = Color.parseColor(getSetting?.color.toString())
        background.setBackgroundColor(Color)

        // SET TEXTS FONT INSIDE COST ACTIVITY
        var fontName = getSetting?.font?.toLowerCase()
        var font = Typeface.createFromAsset(assets, "font/$fontName.ttf")
        activityHeader.typeface = font
        ///////////////////////////////////////////////////////////////

        val backButton = findViewById<ImageButton>(R.id.fruitinfo_back)
        backButton.setOnClickListener {
            val intent = Intent(this, AccountingActivity::class.java)
            startActivity(intent)
            finish()
            finish()
        }



        if (!updateRequire.equals("update")) {
            searchButton.setOnClickListener {
                val intent = Intent(this, SearchViewActivity::class.java)
                intent.putExtra("TABLE", "fruit")
                startActivity(intent)
            }
        }

        fruitActivitySaveData.setOnClickListener {
            System.out.println("............."+updateRequire)

            if (!TextUtils.isEmpty(fruitNameEditText.text.toString())
                && !TextUtils.isEmpty(fruitPriceEditText.text.toString())
                && !TextUtils.isEmpty(fruitQltEditText.text.toString())
                && !updateRequire.equals("update")
            ) {
                System.out.println("............."+updateRequire)

                insertDataToDataBase(
                    fruitNameEditText.text.toString(), fruitPriceEditText.text.toString(),
                    fruitQltEditText.text.toString()
                )
                Toast.makeText(this, "با موفقیت ثبت شد", Toast.LENGTH_LONG).show()

            }
            else if (!TextUtils.isEmpty(fruitNameEditText.text.toString())
                && !TextUtils.isEmpty(fruitPriceEditText.text.toString())
                && !TextUtils.isEmpty(fruitQltEditText.text.toString())
                && updateRequire.equals("update")
            ) {

                updateCostDataDatabase(
                    fruitNameUpdateData.toString(),
                    fruitPriceUpdateData!!.toInt(),
                    fruitQltUpdateData!!.toInt(),
                    fruitNameEditText.text.toString(),
                    fruitPriceEditText.text.toString(),
                    fruitQltEditText.text.toString()
                )
                Toast.makeText(this, "با موفقیت ویرایش شد", Toast.LENGTH_LONG).show()
            }


        }


    }

    fun insertDataToDataBase(fruitName: String, fruitPrice: String, fruitQlt: String) {
        dbHandler = DataBaseHelper(this)

        val fruitInfo = FruitInfo()
        fruitInfo.name = fruitName
        fruitInfo.price = fruitPrice.toInt()
        fruitInfo.qlt = fruitQlt.toInt()
        dbHandler?.addFruit(fruitInfo)

    }


    fun updateCostDataDatabase(
        oldFruitName: String,
        oldFruitPrice: Int,
        oldFruitQlt: Int,
        newFruitName: String,
        newFruitPrice: String,
        newFruitQlt: String
    ) {
        dbHandler = DataBaseHelper(this)

        val newFruitInfo = FruitInfo()
        val oldFruitInfo = FruitInfo()

        newFruitInfo.name = newFruitName
        newFruitInfo.price = newFruitPrice.toInt()
        newFruitInfo.qlt = newFruitQlt.toInt()

        oldFruitInfo.name = oldFruitName
        oldFruitInfo.price = oldFruitPrice
        oldFruitInfo.qlt = oldFruitQlt

        dbHandler?.updateFruitRowData(oldFruitInfo,newFruitInfo)
    }
}