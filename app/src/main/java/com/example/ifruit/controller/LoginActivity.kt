package com.example.ifruit.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import at.favre.lib.crypto.bcrypt.BCrypt
import com.example.ifruit.R
import com.example.ifruit.database.DataBaseHelper

class LoginActivity : AppCompatActivity() {

    private var user: String = ""
    private var pass: String = ""
    var dbHandler: DataBaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        dbHandler = DataBaseHelper(this)

        val userName = findViewById<EditText>(R.id.editusername)
        val password = findViewById<EditText>(R.id.editpassword)
        val forgotPassword = findViewById<TextView>(R.id.forgot_password)
        val loginButton = findViewById<Button>(R.id.sign_in)

        loginButton.setOnClickListener {
            var id = 0
            var counter = 0

            //Here we check that the inputs of EditText are not empty

            if (!TextUtils.isEmpty(userName.text.toString())
                && !TextUtils.isEmpty(password.text.toString())
            ) {

                user = userName.text.toString().trimEnd()
                pass = password.text.toString().trimEnd()
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//              This loop compares the input loop with all the rows in the database,
//              and if it finds a similarity, it equates the ID number to the index of the data in the database,
//              and if not, ID equals to -1 .
// //////////////////////////////////////////////////////////////////////////////////////////////////////////////

                for (i in 1..dbHandler!!.getUserTableRowCount()) {

                    val getUser = dbHandler?.readUser(i)

                    if (TextUtils.equals(
                            getUser!!.email,
                            user
                        ) && checkPassword(getUser.password.toString(), pass)
                    ) {
                        val intent = Intent(this, MainMenuActivity::class.java)
                        startActivity(intent)
                        id = i
                        counter++
                    } else if (!TextUtils.equals(
                            getUser!!.email,
                            user
                        ) || !checkPassword(getUser.password.toString(), pass) && counter < 1
                    ) {
                        id = -1
                    }

                }
                ////////////////////////////////////////////////////////
                //This section examines the resultThis section examines the result
                ////////////////////////////////////////////////////////
                if (id != -1) {
                    var getUser2 = dbHandler!!.readUser(id)
                    if (TextUtils.equals(
                            getUser2?.email,
                            user
                        ) && checkPassword(getUser2?.password.toString(), pass)
                    ) {
                        val intent = Intent(this, MainMenuActivity::class.java)
                        startActivity(intent)
                    }
                } else {
                    logInDialogMassage(1)
                }

            } else if (TextUtils.isEmpty(userName.text.toString()) || TextUtils.isEmpty(password.text.toString())) {
                Toast.makeText(
                    this,
                    "نام کاربری یا رمز عبور وارد نشده است.",
                    Toast.LENGTH_LONG
                ).show()
                logInDialogMassage(2)

            }
        }

        forgotPassword.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }
    fun checkPassword(databasePass :String,editTextPass :String):Boolean{
        val result: BCrypt.Result =
            BCrypt.verifyer().verify(editTextPass.toCharArray(), databasePass)
        return result.verified
    }

    fun logInDialogMassage(ID : Int) {
        val dialog = AlertDialog.Builder(this)

        if(ID == 1){
            dialog.setTitle("خطا")
            dialog.setIcon(R.drawable.ic_error)
            dialog.setMessage("رمز عبور یا نام کاربری اشتباه است !")
            dialog.setPositiveButton("تایید",null)

            }

        else if (ID==2){
            dialog.setTitle("خطا")
            dialog.setIcon(R.drawable.ic_warning)
            dialog.setMessage("رمز عبور یا نام کاربری وارد نشده است !")
            dialog.setPositiveButton("تایید",null)
        }


        dialog.show()

    }
}