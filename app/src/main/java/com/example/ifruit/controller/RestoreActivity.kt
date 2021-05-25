package com.example.ifruit.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ifruit.R
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.lang.Exception

class RestoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restore)

        try {

            val fileRead:List<String> = File("data/data/com.example.ifruit/files/user.csv").readLines()
            var counter = 0
            fileRead.forEach{reade->
                if (counter!=0){
                    val corectForm:List<String> = reade.split(",")
                    println(corectForm.get(2))
                }
                counter++;
            }
            counter=0
        }catch (e:Exception){
            e.printStackTrace()
        }
        Log.d("joooooooon", "jooooon")
    }
}