package com.example.ifruit.controller

import android.content.Context
import android.database.Cursor
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_back_up)

        dbHandler = DataBaseHelper(this@BackUpActivity)
        val backUpSubBtn = findViewById<ImageButton>(R.id.backUpSubBtn)

        var fileOutPutStream: FileOutputStream? = null

        fileOutPutStream = openFileOutput("user.csv", Context.MODE_PRIVATE)

        backUpSubBtn.setOnClickListener {
            val curser:Cursor = dbHandler!!.userDbCopy()
            try {
                Toast.makeText(this@BackUpActivity, "file is created", Toast.LENGTH_LONG).show()
                fileOutPutStream?.write(userCSVHeader.toByteArray())
                while (!curser.isNull(0)) {
                    fileOutPutStream?.write("\n".toByteArray())
                    fileOutPutStream?.write(curser.getString(0).toByteArray())
                    fileOutPutStream?.write(",".toByteArray())
                    fileOutPutStream?.write(curser.getString(1).toByteArray())
                    fileOutPutStream?.write(",".toByteArray())
                    fileOutPutStream?.write(curser.getString(2).toByteArray())
                    fileOutPutStream?.write(",".toByteArray())
                    fileOutPutStream?.write(curser.getString(3).toByteArray())
                    curser.moveToNext()
                }
                fileOutPutStream.close()

            }catch (e: FileNotFoundException){
                e.printStackTrace()
            }
            catch (e: Exception){
                e.printStackTrace()
            }


        }

    }
}

