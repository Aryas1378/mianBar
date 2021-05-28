package com.example.ifruit.controller

import android.content.Context
import android.database.Cursor
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.ifruit.R
import com.example.ifruit.database.DataBaseHelper
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.FileWriter
import java.lang.Exception

class BackUpActivity : AppCompatActivity() {
    var dbHandler:DataBaseHelper?=null
    private val userCSVHeader = "id,name,username,password"
    private val debtCSVHeader = "id,name,phone,debtAmount"
    private val costCSVHeader = "id,reason,amount,date"
    private val contractCSVHeader = "id,name,nationalCode,transVolume,title,productInfo,date"
    private val fruitCSVHeader = "id,name,price,quality"
    private val salaryCSVHeader = "id,name,salary,phone"
    private val employeeCSVHeader = "id,name,phone,dateOfEmployee,salary,jobTitle"
    private val contactCSVHeader = "id,name,title,phone"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_back_up)
        dbHandler = DataBaseHelper(this)

        val backUpSubBtn = findViewById<ImageButton>(R.id.backUpSubBtn)
        val background = findViewById<RelativeLayout>(R.id.backup_background)
        val activityHeader = findViewById<TextView>(R.id.backup_header)

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

        var userFileOutPutStream: FileOutputStream? = null
        var debtFileOutPutStream: FileOutputStream? = null
        var costFileOutPutStream: FileOutputStream? = null
        var contractFileOutPutStream: FileOutputStream? = null
        var fruitFileOutPutStream: FileOutputStream? = null
        var salaryFileOutPutStream: FileOutputStream? = null
        var employeeFileOutPutStream: FileOutputStream? = null
        var contactFileOutPutStream: FileOutputStream? = null


        userFileOutPutStream = openFileOutput("user.csv", Context.MODE_APPEND)
        debtFileOutPutStream = openFileOutput("debt.csv", Context.MODE_APPEND)
        costFileOutPutStream = openFileOutput("cost.csv", Context.MODE_APPEND)
        contractFileOutPutStream = openFileOutput("contract.csv", Context.MODE_APPEND)
        fruitFileOutPutStream = openFileOutput("fruit.csv", Context.MODE_APPEND)
        salaryFileOutPutStream = openFileOutput("salary.csv", Context.MODE_APPEND)
        employeeFileOutPutStream = openFileOutput("employee.csv", Context.MODE_APPEND)
        contactFileOutPutStream = openFileOutput("contact.csv", Context.MODE_APPEND)


        backUpSubBtn.setOnClickListener {
            val userCurser:Cursor = dbHandler!!.userDbCopy()
            val debtCurser:Cursor = dbHandler!!.debtDbCopy()
            val costCurser:Cursor = dbHandler!!.costDbCopy()
            val contractCurser:Cursor = dbHandler!!.contractDbCopy()
            val fruitCurser:Cursor = dbHandler!!.fruitDbCopy()
            val salaryCurser:Cursor = dbHandler!!.salaryDbCopy()
            val employeeCurser:Cursor = dbHandler!!.employeeDbCopy()
            val contactCurser:Cursor = dbHandler!!.contactDbCopy()

            try {
                Toast.makeText(this@BackUpActivity, "file is created", Toast.LENGTH_LONG).show()
                while (!userCurser.isNull(0)) {
                    userFileOutPutStream?.write("\n".toByteArray())
                    userFileOutPutStream?.write(userCurser.getString(0).toByteArray())
                    userFileOutPutStream?.write(",".toByteArray())
                    userFileOutPutStream?.write(userCurser.getString(1).toByteArray())
                    userFileOutPutStream?.write(",".toByteArray())
                    userFileOutPutStream?.write(userCurser.getString(2).toByteArray())
                    userFileOutPutStream?.write(",".toByteArray())
                    userFileOutPutStream?.write(userCurser.getString(3).toByteArray())
                    userCurser.moveToNext()
                }
                userFileOutPutStream.close()


                while (!debtCurser.isNull(0)){
                    debtFileOutPutStream?.write("\n".toByteArray())
                    debtFileOutPutStream?.write(debtCurser.getString(0).toByteArray())
                    debtFileOutPutStream?.write(",".toByteArray())
                    debtFileOutPutStream?.write(debtCurser.getString(1).toByteArray())
                    debtFileOutPutStream?.write(",".toByteArray())
                    debtFileOutPutStream?.write(debtCurser.getString(2).toByteArray())
                    debtFileOutPutStream?.write(",".toByteArray())
                    debtFileOutPutStream?.write(debtCurser.getString(3).toByteArray())
                    debtCurser.moveToNext()
                }
                debtFileOutPutStream.close()

                while (!costCurser.isNull(0)){
                    costFileOutPutStream?.write("\n".toByteArray())
                    costFileOutPutStream?.write(costCurser.getString(0).toByteArray())
                    costFileOutPutStream?.write(",".toByteArray())
                    costFileOutPutStream?.write(costCurser.getString(1).toByteArray())
                    costFileOutPutStream?.write(",".toByteArray())
                    costFileOutPutStream?.write(costCurser.getString(2).toByteArray())
                    costFileOutPutStream?.write(",".toByteArray())
                    costFileOutPutStream?.write(costCurser.getString(3).toByteArray())
                    costCurser.moveToNext()
                }
                costFileOutPutStream.close()

                while (!contractCurser.isNull(0)){
                    contractFileOutPutStream?.write("\n".toByteArray())
                    contractFileOutPutStream?.write(contractCurser.getString(0).toByteArray())
                    contractFileOutPutStream?.write(",".toByteArray())
                    contractFileOutPutStream?.write(contractCurser.getString(1).toByteArray())
                    contractFileOutPutStream?.write(",".toByteArray())
                    contractFileOutPutStream?.write(contractCurser.getString(2).toByteArray())
                    contractFileOutPutStream?.write(",".toByteArray())
                    contractFileOutPutStream?.write(contractCurser.getString(3).toByteArray())
                    contractFileOutPutStream?.write(",".toByteArray())
                    contractFileOutPutStream?.write(contractCurser.getString(4).toByteArray())
                    contractFileOutPutStream?.write(",".toByteArray())
                    contractFileOutPutStream?.write(contractCurser.getString(5).toByteArray())
                    contractFileOutPutStream?.write(",".toByteArray())
                    contractFileOutPutStream?.write(contractCurser.getString(6).toByteArray())
                    contractCurser.moveToNext()
                }
                contractFileOutPutStream.close()

                while (!fruitCurser.isNull(0)){
                    fruitFileOutPutStream?.write("\n".toByteArray())
                    fruitFileOutPutStream?.write(fruitCurser.getString(0).toByteArray())
                    fruitFileOutPutStream?.write(",".toByteArray())
                    fruitFileOutPutStream?.write(fruitCurser.getString(1).toByteArray())
                    fruitFileOutPutStream?.write(",".toByteArray())
                    fruitFileOutPutStream?.write(fruitCurser.getString(2).toByteArray())
                    fruitFileOutPutStream?.write(",".toByteArray())
                    fruitFileOutPutStream?.write(fruitCurser.getString(3).toByteArray())
                    fruitCurser.moveToNext()
                }
                fruitFileOutPutStream.close()


                while (!salaryCurser.isNull(0)){
                    salaryFileOutPutStream?.write("\n".toByteArray())
                    salaryFileOutPutStream?.write(salaryCurser.getString(0).toByteArray())
                    salaryFileOutPutStream?.write(",".toByteArray())
                    salaryFileOutPutStream?.write(salaryCurser.getString(1).toByteArray())
                    salaryFileOutPutStream?.write(",".toByteArray())
                    salaryFileOutPutStream?.write(salaryCurser.getString(2).toByteArray())
                    salaryFileOutPutStream?.write(",".toByteArray())
                    salaryFileOutPutStream?.write(salaryCurser.getString(3).toByteArray())
                    salaryCurser.moveToNext()
                }
                salaryFileOutPutStream.close()

                while (!employeeCurser.isNull(0)){
                    employeeFileOutPutStream?.write("\n".toByteArray())
                    employeeFileOutPutStream?.write(employeeCurser.getString(0).toByteArray())
                    employeeFileOutPutStream?.write(",".toByteArray())
                    employeeFileOutPutStream?.write(employeeCurser.getString(1).toByteArray())
                    employeeFileOutPutStream?.write(",".toByteArray())
                    employeeFileOutPutStream?.write(employeeCurser.getString(2).toByteArray())
                    employeeFileOutPutStream?.write(",".toByteArray())
                    employeeFileOutPutStream?.write(employeeCurser.getString(3).toByteArray())
                    employeeFileOutPutStream?.write(",".toByteArray())
                    employeeFileOutPutStream?.write(employeeCurser.getString(4).toByteArray())
                    employeeFileOutPutStream?.write(",".toByteArray())
                    employeeFileOutPutStream?.write(employeeCurser.getString(5).toByteArray())
                    employeeCurser.moveToNext()
                }
                employeeFileOutPutStream.close()

                while (!contactCurser.isNull(0)){
                    contactFileOutPutStream?.write("\n".toByteArray())
                    contactFileOutPutStream?.write(contactCurser.getString(0).toByteArray())
                    contactFileOutPutStream?.write(",".toByteArray())
                    contactFileOutPutStream?.write(contactCurser.getString(1).toByteArray())
                    contactFileOutPutStream?.write(",".toByteArray())
                    contactFileOutPutStream?.write(contactCurser.getString(2).toByteArray())
                    contactFileOutPutStream?.write(",".toByteArray())
                    contactFileOutPutStream?.write(contactCurser.getString(3).toByteArray())
                    contactCurser.moveToNext()
                }
                contactFileOutPutStream.close()



            }catch (e: FileNotFoundException){
                e.printStackTrace()
            }
            catch (e: Exception){
                e.printStackTrace()
            }


        }

    }
}

