package com.example.ifruit.controller

import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.annimation.SettingInfo
import com.example.ifruit.R
import com.example.ifruit.database.DataBaseHelper

class SettingActivity : AppCompatActivity() {

    var dbHandler: DataBaseHelper? = null
    var redColor = Color.parseColor("#880E4F")
    var blueColor = Color.parseColor("#1A237E")
    var greenColor = Color.parseColor("#004D40")
    var grayColor = Color.parseColor("#757575")
    var orangeColor = Color.parseColor("#b74d29")

    var PRIMARY_COLOR: String = "#757575"
    var PRIMARY_FONT: String = "IArabics"
    var PRIMARY_NAME: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val generalBackground = findViewById<ConstraintLayout>(R.id.background)
        val redColorChanger = findViewById<ImageButton>(R.id.modifier_to_red_btn)
        val blueColorChanger = findViewById<ImageButton>(R.id.modifier_to_blue_btn)
        val greenColorChanger = findViewById<ImageButton>(R.id.modifier_to_green_btn)
        val grayColorChanger = findViewById<ImageButton>(R.id.modifier_to_gray_btn)
        val orangeColorChanger = findViewById<ImageButton>(R.id.modifier_to_orange_btn)
        val titleContent = findViewById<TextView>(R.id.title_content)
        val changeColorTitle = findViewById<TextView>(R.id.change_color_title)
        val changeFontTitle = findViewById<TextView>(R.id.change_font_title)
        val fontStyleSpinner = findViewById<Spinner>(R.id.change_font_style)
        val changeNameTitle = findViewById<TextView>(R.id.change_name_title)
        val changeNameEditText = findViewById<EditText>(R.id.change_name)
        val saveButton = findViewById<ImageButton>(R.id.contract_save_btn)

        dbHandler = DataBaseHelper(this)

        PRIMARY_NAME = dbHandler!!.readSettingInfo(1)?.name.toString()

        //------------------------------------------------
        /*change color and text font of setting page (55 - 64)*/
        //------------------------------------------------

        val getSetting = dbHandler?.readSettingInfo(1)
        //System.out.println(getSetting?.color)
        val backgroundColor = Color.parseColor(getSetting?.color.toString())
        generalBackground.setBackgroundColor(backgroundColor)
        val fontName = getSetting?.font?.toLowerCase()
        //System.out.println(fontName)
        val font = Typeface.createFromAsset(assets, "font/$fontName.ttf")
        titleContent.typeface = font
        changeColorTitle.typeface = font
        changeFontTitle.typeface = font
        changeNameTitle.typeface = font


        val fontStyleList = arrayOf(
                "IArabics",
                "BBadr",
                "BBaran",
                "BDavat",
                "BElham",
                "BMitra",
                "BNazanin",
                "BTehran",
                "BArabics",
                "IKamran",
                "IranNastaliq",
                "IRoya"
        )
        if (fontStyleSpinner != null) {
            val arrayAdapter: ArrayAdapter<Any?> =
                    ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, fontStyleList)

            fontStyleSpinner.adapter = arrayAdapter

            fontStyleSpinner.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View, position: Int, id: Long
                ) {

                    PRIMARY_FONT = "IArabics"

                    Toast.makeText(this@SettingActivity, fontStyleList[id.toInt()], Toast.LENGTH_SHORT).show()
                    val fontName = fontStyleList[id.toInt()].toLowerCase()
                    val font = Typeface.createFromAsset(assets, "font/$fontName.ttf")
                    changeFontTitle.typeface = font
                    changeColorTitle.typeface = font
                    titleContent.typeface = font
                    PRIMARY_FONT = fontStyleList[id.toInt()]

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        redColorChanger.setOnClickListener {
            PRIMARY_COLOR = "#ffffff"
            generalBackground.setBackgroundColor(redColor)
            PRIMARY_COLOR = "#880E4F"
        }
        blueColorChanger.setOnClickListener {
            PRIMARY_COLOR = "#ffffff"
            generalBackground.setBackgroundColor(blueColor)
            PRIMARY_COLOR = "#1A237E"
        }
        greenColorChanger.setOnClickListener {
            PRIMARY_COLOR = "#ffffff"
            generalBackground.setBackgroundColor(greenColor)
            PRIMARY_COLOR = "#004D40"
        }
        grayColorChanger.setOnClickListener {
            PRIMARY_COLOR = "#ffffff"
            generalBackground.setBackgroundColor(grayColor)
            PRIMARY_COLOR = "#757575"
        }
        orangeColorChanger.setOnClickListener {
            PRIMARY_COLOR = "#ffffff"
            generalBackground.setBackgroundColor(orangeColor)
            PRIMARY_COLOR = "#b74d29"
        }

        saveButton.setOnClickListener {
            var settinginfo = SettingInfo()
            settinginfo.color = PRIMARY_COLOR
            settinginfo.font = PRIMARY_FONT
            if (!TextUtils.isEmpty(changeNameEditText.text)) {
                PRIMARY_NAME = changeNameEditText.text.toString()
            }
            settinginfo.name = PRIMARY_NAME
            dbHandler!!.updateSettingInfo(settinginfo, 1)

            Toast.makeText(this, "با موفقیت ثبت شد", Toast.LENGTH_LONG).show()
        }
    }
}