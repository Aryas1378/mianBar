package com.example.ifruit.controller

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import at.favre.lib.crypto.bcrypt.BCrypt
import com.example.annimation.SettingInfo
import com.example.annimation.UserInfo
import com.example.ifruit.R
import com.example.ifruit.database.DataBaseHelper
import java.util.*

class MainActivity : AppCompatActivity() {
    var dbHandler: DataBaseHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHandler = DataBaseHelper(this)


        checkUser()
        setSetting()

        var a = dbHandler!!.readUser(1)
        System.out.println(a?.password)
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                val intent = Intent(this@MainActivity as Context, LoginActivity::class.java)
                this@MainActivity.startActivity(intent)
                finish()
            }
        } as TimerTask, 3100L)
    }
    fun checkUser() {
        val count = dbHandler?.getUserTableRowCount()
        if (count == 0 || count == null) {
            val userinfo = UserInfo()
            userinfo.name = "ابراهیم"
            userinfo.email = "vendor"
            userinfo.password = doHashingPassword("vendor")
            dbHandler?.createUser(userinfo)

        }
    }

    fun setSetting() {
        val count = dbHandler?.getSettingTableRowCount()
        if (count == 0 || count == null) {
            val userName = dbHandler?.readUser(1)
            val settingInfo = SettingInfo()
            settingInfo.name = userName?.name.toString()
            settingInfo.color = "#757575"
            settingInfo.font = "IArabics"
            dbHandler?.createSettingInfo(settingInfo)
        }
    }

    fun doHashingPassword(password: String): String {
        val bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray())
        return bcryptHashString
    }
}