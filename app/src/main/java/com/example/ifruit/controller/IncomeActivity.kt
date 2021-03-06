package com.example.ifruit.controller


import android.app.DatePickerDialog
import android.content.Intent
import android.database.Cursor
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.ifruit.R
import com.example.ifruit.database.DataBaseHelper
import java.time.format.DateTimeFormatter
import java.util.*

class IncomeActivity : AppCompatActivity() , DatePickerDialog.OnDateSetListener {

    var day = 0
    var month = 0
    var year = 0


    var SavedDay = 0
    var SavedMonth = 0
    var SavedYear = 0

    private lateinit var search_btn:ImageButton
    private lateinit var startDate_textview:TextView
    private lateinit var endDate_textiew:TextView
    //    private lateinit var profit_textiew:TextView
//    private lateinit var loss_textiew:TextView
    private lateinit var income_textiew:TextView

    private var db:DataBaseHelper?=null


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income)
        db=DataBaseHelper(this)

        initViews()
        pickDate()


        search_btn.setOnClickListener {
            if(startDate_textview.text.toString().isEmpty() or endDate_textiew.text.toString().isEmpty())
                Toast.makeText(this, "لطفا تاریخ ها را وارد کنید", Toast.LENGTH_LONG).show()
            else {
                var taxOverProfit: Float = taxOverProfit()
                var insurance: Float = insurance()
                var income: Float = income().toFloat()
                income -= (taxOverProfit.plus(insurance))
                income_textiew.text = income.toString()
            }


        }

        val backButton = findViewById<ImageButton>(R.id.income_back)
        backButton.setOnClickListener {
            val intent = Intent(this, AccountingActivity::class.java)
            startActivity(intent)
            finish()
            finish()
        }


    }

    private fun income(): Int {
        var cursor1: Cursor =db!!.getAllInvoice()
        var resultStart=startDate_textview.text.toString()+"-".split("-")
        var resultEnd=endDate_textiew.text.toString()+"-".split("-")
        var resultStartsum:Int= (resultStart[1].toInt()-1).times(30)+resultStart[2].toInt()
        var resultEndSum:Int=(resultEnd[1].toInt()-1).times(30)+resultEnd[2].toInt()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        var totalMoney:Long=0

        if (cursor1.moveToFirst()){
            do {
                var resultDB=cursor1.getString(5).format(formatter)+"-".split("-")
                var resultDBSum:Int=(resultDB[1].toInt()-1).times(30)+resultDB[2].toInt()

                if (resultDBSum >= resultStartsum || resultDBSum <= resultEndSum){
                    System.out.println("EQUAl")
                    totalMoney+=cursor1.getInt(3)

                }

            }while (cursor1.moveToNext())
        }

        var cursor2: Cursor =db!!.getAllFee()

        var totalFee:Long=0
        if (cursor2.moveToFirst()){
            do {
                var resultDB=cursor2.getString(3)+"-".split("-")
                var resultDBSum:Int=(resultDB[1].toInt()-1).times(30)+resultDB[2].toInt()

                if (resultDBSum >= resultStartsum || resultDBSum <= resultEndSum){
                    System.out.println("EQUAl")
                    totalFee+=cursor2.getInt(2)

                }

            }while (cursor2.moveToNext())
        }
        var total = totalMoney.minus(totalFee)
        return total.toInt()

    }


    private fun insurance(): Float {
        var cursor1: Cursor =db!!.getAllEmployee()
        var resultStart=startDate_textview.text.toString()+"-".split("-")
        var resultEnd=endDate_textiew.text.toString()+"-".split("-")
        var resultStartsum:Int= (resultStart[1].toInt()-1).times(30)+resultStart[2].toInt()
        var resultEndSum:Int=(resultEnd[1].toInt()-1).times(30)+resultEnd[2].toInt()

        var totalMoney:Long=0

        if (cursor1.moveToFirst()){
            do {
                var resultDB=cursor1.getString(3)+"-".split("-")
                var resultDBSum:Int=(resultDB[1].toInt()-1).times(30)+resultDB[2].toInt()

                if (resultDBSum >= resultStartsum || resultDBSum <= resultEndSum){
                    System.out.println("EQUAl")
                    totalMoney+=cursor1.getInt(4)

                }

            }while (cursor1.moveToNext())
        }
        var total = totalMoney.times(0.2)
        return "%.3f".format(total).toFloat()


    }


    private fun taxOverProfit(): Float {
        var cursor1: Cursor =db!!.getAllInvoice()
        var resultStart=startDate_textview.text.toString()+"-".split("-")
        var resultEnd=endDate_textiew.text.toString()+"-".split("-")
        var resultStartsum:Int= (resultStart[1].toInt()-1).times(30)+resultStart[2].toInt()
        var resultEndSum:Int=(resultEnd[1].toInt()-1).times(30)+resultEnd[2].toInt()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        var totalMoney:Long=0

        if (cursor1.moveToFirst()){
            do {
                var resultDB=cursor1.getString(5).format(formatter)+"-".split("-")
                var resultDBSum:Int=(resultDB[1].toInt()-1).times(30)+resultDB[2].toInt()

                if (resultDBSum >= resultStartsum || resultDBSum <= resultEndSum){
                    System.out.println("EQUAl")
                    totalMoney+=cursor1.getInt(3)

                }

            }while (cursor1.moveToNext())
        }
        var total = totalMoney.times(0.09)
        return "%.3f".format(total).toFloat()


    }

    private fun initViews() {
        search_btn=findViewById(R.id.search_btn) as ImageButton
        startDate_textview=findViewById(R.id.startDateNum_textview) as Button
        endDate_textiew=findViewById(R.id.endtDateNum_textview) as Button
//        profit_textiew=findViewById(R.id.profitAmount_textview) as TextView
//        loss_textiew=findViewById(R.id.lossAmount_textview) as TextView
        income_textiew=findViewById(R.id.incomeAmount_textview) as TextView

    }

    private fun getDateTimeCalender(){
        val cal: Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)

    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun pickDate(){
        val startDateBtn = findViewById(R.id.startDateNum_textview) as Button

        startDateBtn.setOnClickListener{

            getDateTimeCalender()
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth -> startDateBtn.text="$year-"+(month+1).toString()+"-$dayOfMonth"  }
                , year, month, day).show()
        }

        val endDateBtn = findViewById(R.id.endtDateNum_textview) as Button
        endDateBtn.setOnClickListener{

            getDateTimeCalender()
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth -> endDateBtn.text="$year-"+(month+1).toString()+"-$dayOfMonth"  },
                year, month, day).show()
        }

    }
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

        SavedDay = dayOfMonth
        SavedMonth = month
        SavedYear = year

        val start_btn_date = findViewById(R.id.startDateNum_textview) as Button
        start_btn_date.text = "$SavedMonth-$SavedDay-$SavedYear"
        getDateTimeCalender()

        val end_btn_date = findViewById(R.id.endtDateNum_textview) as Button
        end_btn_date.text = "$SavedMonth-$SavedDay-$SavedYear"

    }
}

