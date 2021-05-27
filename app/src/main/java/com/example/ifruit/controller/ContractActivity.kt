package com.example.ifruit.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import com.example.annimation.ContractInfo
import com.example.ifruit.R
import com.example.ifruit.database.DataBaseHelper

class ContractActivity : AppCompatActivity() {

    var dbHandler: DataBaseHelper? = null
    private var updateRequire: String? = null
    private var contractNameUpdateData: String? = null
    private var contractTitleUpdateData: String? = null
    private var contractProductInfoUpdateData: String? = null
    private var contractDateUpdateData: String? = null
    private var contractTransactionUpdateData: Long? = null
    private var contractNationalCodeUpdateData: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contract)

        updateRequire = intent.getStringExtra("UPDATE").toString()
        contractNameUpdateData = intent.getStringExtra("CONTRACTNAME")
        contractTitleUpdateData= intent.getStringExtra("CONTRACTTITLE")
        contractProductInfoUpdateData= intent.getStringExtra("PRODUCTINFO")
        contractDateUpdateData= intent.getStringExtra("CONTRACTDATE")
        contractTransactionUpdateData= intent.getLongExtra("TRANSACTIONVOLUME",0)
        contractNationalCodeUpdateData= intent.getLongExtra("NATIONALCODE",0)


        dbHandler = DataBaseHelper(this)
        val background = findViewById<RelativeLayout>(R.id.background)
        val activityHeader = findViewById<TextView>(R.id.contract_header)
        val searchButton = findViewById<ImageButton>(R.id.search_button)
        val contractTitleEditText = findViewById<EditText>(R.id.contract_title_ac)
        val contractDateEditText = findViewById<EditText>(R.id.contract_date_ac)
        val contractNationalCodeEditText = findViewById<EditText>(R.id.customer_national_code_ac)
        val contractTransactionEditText = findViewById<EditText>(R.id.contract_transaction_v_ac)
        val contractNameEditText = findViewById<EditText>(R.id.contract_name_ac)
        val contractProductInfoEditText = findViewById<EditText>(R.id.contract_product_info_ac)
        val contractActivitySaveData = findViewById<ImageButton>(R.id.contract_save_btn)

        if (!updateRequire.equals("update")) {
            searchButton.setOnClickListener {
                val intent = Intent(this, SearchViewActivity::class.java)
                intent.putExtra("TABLE", "contract")
                startActivity(intent)
            }
        }

        contractActivitySaveData.setOnClickListener {
            if (!TextUtils.isEmpty(contractNameEditText.text.toString())
                && !TextUtils.isEmpty(contractNationalCodeEditText.text.toString())
                && !TextUtils.isEmpty(contractTransactionEditText.text.toString())
                && !TextUtils.isEmpty(contractTitleEditText.text.toString())
                && !TextUtils.isEmpty(contractProductInfoEditText.text.toString())
                && !TextUtils.isEmpty(contractDateEditText.text.toString())
                && !updateRequire.equals("update")
            ) {
                insertDataToDataBase(
                    contractNameEditText.text.toString(),
                    contractNationalCodeEditText.text.toString(),
                    contractTransactionEditText.text.toString(),
                    contractTitleEditText.text.toString(),
                    contractProductInfoEditText.text.toString(),
                    contractDateEditText.text.toString()
                )
                Toast.makeText(this, "با موفقیت ثبت شد", Toast.LENGTH_LONG).show()
            }
            else if(!TextUtils.isEmpty(contractNameEditText.text.toString())
                && !TextUtils.isEmpty(contractNationalCodeEditText.text.toString())
                && !TextUtils.isEmpty(contractTransactionEditText.text.toString())
                && !TextUtils.isEmpty(contractTitleEditText.text.toString())
                && !TextUtils.isEmpty(contractProductInfoEditText.text.toString())
                && !TextUtils.isEmpty(contractDateEditText.text.toString())
                && updateRequire.equals("update")){

                updateContractDataDatabase(
                    contractNameEditText.text.toString(),
                    contractNationalCodeEditText.text.toString(),
                    contractTransactionEditText.text.toString(),
                    contractTitleEditText.text.toString(),
                    contractProductInfoEditText.text.toString(),
                    contractDateEditText.text.toString(),
                    contractNameUpdateData.toString(),
                    contractNationalCodeUpdateData!!,
                    contractTransactionUpdateData!!,
                    contractTitleUpdateData.toString(),
                    contractProductInfoUpdateData.toString(),
                    contractDateUpdateData.toString()
                )
            }


        }


    }

    fun insertDataToDataBase(
        name: String,
        nationalCode: String,
        transactionVolume: String,
        contractTitle: String,
        productInformation: String,
        date: String
    ) {
        val contractInfo = ContractInfo()
        contractInfo.name = name
        contractInfo.nationalCode = nationalCode.toLong()
        contractInfo.transactionVolume = transactionVolume.toLong()
        contractInfo.contractTitle = contractTitle
        contractInfo.productInformation = productInformation
        contractInfo.date = date
        dbHandler?.createContractInfo(contractInfo)
    }

    fun updateContractDataDatabase(
        newName: String,
        newNationalCode: String,
        newTransactionVolume: String,
        newContractTitle: String,
        newProductInformation: String,
        newDate: String,
        oldName: String,
        oldNationalCode: Long,
        oldTransactionVolume: Long,
        oldContractTitle: String,
        oldProductInformation: String,
        oldDate: String
    ) {
        dbHandler = DataBaseHelper(this)

        val oldContractInfo = ContractInfo()
        val newContractInfo = ContractInfo()

        oldContractInfo.name = oldName
        oldContractInfo.nationalCode = oldNationalCode.toLong()
        oldContractInfo.transactionVolume = oldTransactionVolume.toLong()
        oldContractInfo.contractTitle = oldContractTitle
        oldContractInfo.productInformation = oldProductInformation
        oldContractInfo.date = oldDate

        newContractInfo.name = newName
        newContractInfo.nationalCode = newNationalCode.toLong()
        newContractInfo.transactionVolume = newTransactionVolume.toLong()
        newContractInfo.contractTitle = newContractTitle
        newContractInfo.productInformation = newProductInformation
        newContractInfo.date = newDate

        dbHandler?.updateContractRowDate(oldContractInfo,newContractInfo)

    }



}