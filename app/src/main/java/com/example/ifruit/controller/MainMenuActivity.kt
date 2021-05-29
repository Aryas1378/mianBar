package com.example.ifruit.controller

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.ifruit.R
import com.example.ifruit.database.DataBaseHelper
import com.google.android.material.navigation.NavigationView

class MainMenuActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

//    private lateinit var log_out: Button

    private lateinit var acc_btn: Button
    private lateinit var mng_btn: Button
    private lateinit var setting_btn: ImageView
    private lateinit var setting_text_btn: TextView
    private lateinit var backup_btn: ImageView
    private lateinit var backup_text_btn: TextView
    private lateinit var contactus_btn: ImageView
    private lateinit var contactus_text_btn: TextView
    private lateinit var logoutDrawer_btn: ImageView
    private lateinit var logout_text_btn: TextView
    private lateinit var item0: MenuItem
    private lateinit var toggle: ImageButton
    private lateinit var toolbar: Toolbar
    private lateinit var drawer: DrawerLayout
    private lateinit var username_text_view: TextView
    private var dbHandler: DataBaseHelper? = null

    //    private lateinit var toggle:ActionBarDrawerToggle
    private lateinit var navigationView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
//        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        dbHandler = DataBaseHelper(this)
        val getSetting = dbHandler?.readSettingInfo(1)
        var factory = layoutInflater
        var textView: View = factory.inflate(R.layout.nav_header, null)
        var userNameInDrawerLayout = textView.findViewById<TextView>(R.id.username_textview)

        userNameInDrawerLayout.setText(getSetting?.name)
      //  userNameInDrawerLayout.text = getSetting?.name
        System.out.println("امه اکاته یوزر نیم کمان کاکه //...." + userNameInDrawerLayout.text)




        drawer = findViewById(R.id.drawer_layout)

        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
//        dbHandler = DataBaseHelper(this)
//        val getSetting = dbHandler?.readSettingInfo(1)


//        toggle= ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        toggle = findViewById(R.id.nav_toggle)
        toggle.setOnClickListener {
            drawer.openDrawer(GravityCompat.END)
        }
//        drawer.addDrawerListener(toggle)
//        toggle.syncState()

        initViews()
//        username_text_view.text=getSetting?.name

        setting_btn.setOnClickListener {
//            Toast.makeText(this, "Setting Clicked", Toast.LENGTH_SHORT).show()
            val accountsIntent = Intent(this, SettingActivity::class.java)
            startActivity(accountsIntent)
            drawer.closeDrawer(GravityCompat.END)
        }
        setting_text_btn.setOnClickListener {
//            Toast.makeText(this, "Setting Clicked", Toast.LENGTH_SHORT).show()
            val accountsIntent = Intent(this, SettingActivity::class.java)
            startActivity(accountsIntent)
            drawer.closeDrawer(GravityCompat.END)
        }

        backup_btn.setOnClickListener {
//            Toast.makeText(this, "Backup Clicked", Toast.LENGTH_SHORT).show()
            val accountsIntent = Intent(this, BackUpActivity::class.java)
            startActivity(accountsIntent)
            drawer.closeDrawer(GravityCompat.END)
        }
        backup_text_btn.setOnClickListener {
//            Toast.makeText(this, "Backup Clicked", Toast.LENGTH_SHORT).show()
            val accountsIntent = Intent(this, BackUpActivity::class.java)
            startActivity(accountsIntent)
            drawer.closeDrawer(GravityCompat.END)
        }

        contactus_btn.setOnClickListener {
//            Toast.makeText(this, "ContactUs Clicked", Toast.LENGTH_SHORT).show()
            val accountsIntent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(accountsIntent)
            drawer.closeDrawer(GravityCompat.END)
        }
        contactus_text_btn.setOnClickListener {
//            Toast.makeText(this, "ContactUs Clicked", Toast.LENGTH_SHORT).show()
            val accountsIntent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(accountsIntent)
            drawer.closeDrawer(GravityCompat.END)
        }

        logoutDrawer_btn.setOnClickListener {
//            Toast.makeText(this, "Logout Clicked", Toast.LENGTH_SHORT).show()
            val accountsIntent = Intent(this, LoginActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
////            finish()
//            startActivity(accountsIntent)
            finish()

//            drawer.closeDrawer(GravityCompat.END)
        }
        logout_text_btn.setOnClickListener {
//            Toast.makeText(this, "Logout Clicked", Toast.LENGTH_SHORT).show()
            val accountsIntent = Intent(this, LoginActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
//            startActivity(accountsIntent)
//            finish()
              finish()
//            drawer.closeDrawer(GravityCompat.END)
        }
//
//
//        log_out.setOnClickListener {
//            val accountsIntent = Intent(this, LoginActivity::class.java)
//            startActivity(accountsIntent)
//            finish()
//        }
        acc_btn.setOnClickListener {
            val accountsIntent = Intent(this, AccountingActivity::class.java)
            // finish()
            startActivity(accountsIntent)
//            finish()
        }
        mng_btn.setOnClickListener {
            val accountsIntent = Intent(this, ManagementActivity::class.java)
            //finish()
            startActivity(accountsIntent)
//            finish()
        }

    }

    override fun onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END)
        } else {

            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.nav_setting -> Toast.makeText(this, "Setting Clicked", Toast.LENGTH_LONG).show()

            R.id.nav_backup -> Toast.makeText(this, "Backup Clicked", Toast.LENGTH_LONG).show()

            R.id.nav_contactUs -> Toast.makeText(this, "ContactUs Clicked", Toast.LENGTH_LONG)
                .show()

            R.id.nav_logout -> Toast.makeText(this, "Logout Clicked", Toast.LENGTH_LONG).show()
        }
        drawer.closeDrawer(GravityCompat.END)
        return true
    }

    private fun initViews() {
//        log_out = findViewById(R.id.log_out) as Button
        item0 = navigationView.menu.getItem(0)

        setting_btn = item0.actionView.findViewById(R.id.nav_setting) as ImageView
        setting_text_btn = item0.actionView.findViewById(R.id.nav_setting_text) as TextView

        backup_btn = item0.actionView.findViewById(R.id.nav_backup) as ImageView
        backup_text_btn = item0.actionView.findViewById(R.id.nav_backup_text) as TextView

        contactus_btn = item0.actionView.findViewById(R.id.nav_contactUs) as ImageView
        contactus_text_btn = item0.actionView.findViewById(R.id.nav_contactUs_text) as TextView

        logoutDrawer_btn = item0.actionView.findViewById(R.id.nav_logout) as ImageView
        logout_text_btn = item0.actionView.findViewById(R.id.nav_logout_text) as TextView

//        username_text_view=findViewById(R.id.username_textview) as TextView

        acc_btn = findViewById(R.id.acc_btn) as Button
        mng_btn = findViewById(R.id.mng_btn) as Button
    }
}