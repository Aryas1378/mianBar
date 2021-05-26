package com.example.ifruit.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ifruit.R
import com.example.ifruit.database.DataBaseHelper
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.lang.Exception

class RestoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restore)
        var dbHandler = DataBaseHelper(this)

        try {
            val userFileRead:MutableList<String> = File("data/data/com.example.ifruit/files/user.csv").readLines() as MutableList<String>
            val debtFileRead:MutableList<String> = File("data/data/com.example.ifruit/files/debt.csv").readLines() as MutableList<String>
            var counter = 0

            //user repairing database is successful
//            userFileRead.forEach{reade->
//                if (counter>0){
//                    val userCorrectForm:List<String> = reade.split(",")
//                    val newUser = UserInfo()
//                    newUser.name = userCorrectForm.get(1)
//                    newUser.email = userCorrectForm.get(2)
//                    newUser.password = userCorrectForm.get(3)
//                    dbHandler?.createUser(newUser)
//                }
//                counter++
//            }
//            counter=0

            //debt repairing database is successful
//            debtFileRead.forEach { read ->
//                if(counter>0){
//                    val debtCorrectForm:List<String> = read.split(",")
//                    val newDebt = DebtInfo()
//                    newDebt.Name = debtCorrectForm.get(1)
//                    Log.d("jooooooooon","jooooooooon")
//                    newDebt.PhoneNumber = debtCorrectForm.get(2).toLong()
//                    newDebt.DebtAmount = debtCorrectForm.get(3).toLong()
//                    dbHandler?.createDebtDataBase(newDebt)
//                }
//                counter++
//            }
//            counter =0

        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}