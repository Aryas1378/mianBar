package com.example.ifruit.controller

import android.database.Cursor
import android.database.DatabaseUtils
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ifruit.R
import com.example.ifruit.database.DataBaseHelper
import com.example.ifruit.model.InvoiceInfo
import com.example.ifruit.model.recycleviewadapter.InvoiceAdapter


class InvoiceActivity : AppCompatActivity() {

    private lateinit var decrease_btn: Button
    private lateinit var increase_btn: Button
    private lateinit var add_btn: Button
    private lateinit var submit_btn: ImageButton
    private lateinit var fruitEditTextName: AutoCompleteTextView
    private lateinit var amountTextview: EditText
    private var db: DataBaseHelper?=null
    private  var fAmount: Float=0.0F
    private lateinit var mAdapter: InvoiceAdapter
    var listFruit:List<String> = listOf<String>("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invoice)


        db=DataBaseHelper(this)
        var recyclerView: RecyclerView = findViewById(R.id.invoice_recyclerview)
        recyclerView.layoutManager=LinearLayoutManager(this)


        val background = findViewById<ConstraintLayout>(R.id.main_invoice_layout)
        val activityHeader = findViewById<TextView>(R.id.invoice_header)
        val recycleBackground = findViewById<RelativeLayout>(R.id.invoice_RL)
        ////////////////////////////////////////////////////////////////////////
        val getSetting = db?.readSettingInfo(1)
        // SET BACKGROUND COLOR OF COST ACTIVITY
        var color = Color.parseColor(getSetting?.color.toString())
        background.setBackgroundColor(color)
        recycleBackground.setBackgroundColor(color)

        // SET TEXTS FONT INSIDE COST ACTIVITY
        var fontName = getSetting?.font?.toLowerCase()
        var font = Typeface.createFromAsset(assets, "font/$fontName.ttf")
        activityHeader.typeface = font
        ////////////////////////////////////////////////////////////////////////

        var result= db!!.readFruitData()
        for (i in 0..(result?.size-1)){
            listFruit+= result.get(i).name.toString()
        }

        initViews()
        fruitEditTextName.setAdapter(
            ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                listFruit
//            resources.getStringArray(R.array.test_array)
            )
        )
//        if (amountTextview.equals(null)){
//            fAmount=0.0F
//        }else{
//            fAmount=(amountTextview.text.toString()).toFloat()
//            "%.1f".format(fAmount)
//        }


        var invoiceNumber:Int=0
        val cursor1: Cursor =db!!.getInvoiceNumber()
        if (cursor1.moveToFirst()){
            do {
                if (cursor1.getInt(0)>invoiceNumber){
                    System.out.println("EQUAl")
                    invoiceNumber=cursor1.getInt(0)
                }

            }while (cursor1.moveToNext())
        }
        System.out.println(DatabaseUtils.dumpCursorToString(db!!.getInvoice(invoiceNumber)))
        mAdapter= InvoiceAdapter(this, db!!.getInvoice(invoiceNumber))
        recyclerView.adapter=mAdapter

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                removeItem(viewHolder.itemView.tag as Int,invoiceNumber)
            }
        }).attachToRecyclerView(recyclerView)

        System.out.println("Invoice is" + invoiceNumber)

        System.out.println(DatabaseUtils.dumpCursorToString(cursor1))

        increase_btn.setOnClickListener {
            increase()
        }

        add_btn.setOnClickListener {
            if (invoiceNumber==0){
                addItem(1)
            }else
                addItem(invoiceNumber + 1)

        }

        decrease_btn.setOnClickListener {
            decrease()
        }

        submit_btn.setOnClickListener {
            invoiceNumber+=1
            Toast.makeText(this,"فاکتور ثبت شد" , Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeItem(id: Int,invoiceNumber: Int) {
        db!!.deleteItemInvoice(id)
        mAdapter.swapCursor(db!!.getInvoice(invoiceNumber))
    }

    private fun decrease() {
        if (TextUtils.isEmpty(amountTextview.text.toString())){
            fAmount=0.0F
        }else{
            fAmount=(amountTextview.text.toString()).toFloat()
            "%.1f".format(fAmount)
        }
        if (fAmount>0.0){
            fAmount-=0.5F
            "%.1f".format(fAmount)
            amountTextview.setText(fAmount.toString())
        }
    }


    private fun addItem(invoiceNumber: Int) {
        if (fruitEditTextName.text.toString().trim().length==0 )
            return



        var price:Int=0
        val cursor: Cursor =db!!.getFruitByName()
        if (cursor.moveToFirst()){
            do {
                if (cursor.getString(1).equals(fruitEditTextName.text.toString())){
                    System.out.println("EQUAl")
                    price=cursor.getInt(2)
                }

            }while (cursor.moveToNext())
        }
        fAmount=(amountTextview.text.toString()).toFloat()
        "%.1f".format(fAmount)
        val invoice= InvoiceInfo()

        invoice.name=fruitEditTextName.text.toString()
        invoice.amount=fAmount
        invoice.sum= price.times(fAmount).toInt()
        invoice.invoiceNumber=invoiceNumber

        db!!.addInvoice(invoice)
        mAdapter.swapCursor(db!!.getInvoice(invoiceNumber))

        fruitEditTextName.text.clear()
        cursor.close()

    }

    private fun increase() {
        if (TextUtils.isEmpty(amountTextview.text.toString())){
            fAmount=0.0F
        }else{
            fAmount=(amountTextview.text.toString()).toFloat()
            "%.1f".format(fAmount)
        }
        fAmount+=0.5F
        "%.1f".format(fAmount)
        amountTextview.setText(fAmount.toString())
    }

    private fun initViews() {
        decrease_btn=findViewById(R.id.decrease_btn) as Button
        increase_btn=findViewById(R.id.increase_btn) as Button
        add_btn=findViewById(R.id.addtobasket_btn) as Button
        submit_btn=findViewById(R.id.submit_invoice_btn) as ImageButton
        fruitEditTextName=findViewById(R.id.product_name_text) as AutoCompleteTextView
        amountTextview=findViewById(R.id.textview_amount) as EditText

    }
}