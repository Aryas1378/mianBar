package com.example.ifruit.controller

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.solver.state.State
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ifruit.R
import com.example.ifruit.database.DataBaseHelper
import com.example.ifruit.model.DataGeneratorFromDataBase
import com.example.ifruit.model.recycleviewadapter.*
import com.example.ifruit.model.recycleviewmodel.*

class SearchViewActivity : AppCompatActivity() {

    var dbHandler: DataBaseHelper? = null
    lateinit var costAdapter: CostRecycleViewAdapter
    lateinit var contractAdapter: ContractRecycleViewAdapter
    lateinit var debtAdapter: DebtRecycleViewAdapter
    lateinit var fruitAdapter: FruitRecycleViewAdapter
    lateinit var employeeAdapter: EmployeeRecycleViewAdapter
    lateinit var salaryAdapter: SalaryRecycleViewAdapter
    lateinit var phoneContactAdapter: PhoneContactRecycleViewAdapter
    lateinit var costList: ArrayList<CostDataBaseModel>
    lateinit var contractList: ArrayList<ContractDataBaseModel>
    lateinit var debtList: ArrayList<DebtDataBaseModel>
    lateinit var fruitList: ArrayList<FruitDataBaseModel>
    lateinit var employeeList: ArrayList<EmployeeManagementDataBaseModel>
    lateinit var salaryList: ArrayList<SalaryDataBaseModel>
    lateinit var phoneContactList: ArrayList<PhoneContactDataBaseModel>

    private var number : String ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_view)

        val tableRequire: String = intent.getStringExtra("TABLE").toString()
        number = intent.getStringExtra("ALLNUMBER")


        dbHandler = DataBaseHelper(this)
        costList = ArrayList()
        contractList = ArrayList()
        debtList = ArrayList()
        fruitList = ArrayList()
        employeeList = ArrayList()
        salaryList = ArrayList()
        phoneContactList = ArrayList()

        val dataGeneratorFromDataBase = DataGeneratorFromDataBase()
        val listProduct = findViewById<RecyclerView>(R.id.information_list)
        val searchViewEditText = findViewById<EditText>(R.id.search_edittext)
        val searchViewButton = findViewById<ImageButton>(R.id.search_btn)
        val background = findViewById<ConstraintLayout>(R.id.search_background)
        val activityHeader = findViewById<TextView>(R.id.search_title)

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

        when (tableRequire) {
            "cost" -> getCostDataAndAdaptIt()
            "contract" -> getContractDataAndAdaptIt()
            "debt" -> getDebtDataAndAdaptIt()
            "employee" -> getEmployeeDataAndAdaptIt()
            "salary" -> getSalaryDataAndAdaptIt()
            "fruit" -> getFruitDataAndAdaptIt()
            "contact" -> getContactDataAndAdaptIt()
        }

        val layoutManager = GridLayoutManager(this, 1)
        listProduct.layoutManager = layoutManager

        when (tableRequire) {
            "cost" -> listProduct.adapter = costAdapter
            "contract" -> listProduct.adapter = contractAdapter
            "debt" -> listProduct.adapter = debtAdapter
            "employee" -> listProduct.adapter = employeeAdapter
            "salary" -> listProduct.adapter = salaryAdapter
            "fruit" -> listProduct.adapter = fruitAdapter
            "contact" -> listProduct.adapter = phoneContactAdapter

        }

        searchViewEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

                when (tableRequire) {
                    "cost" -> costFilterData(s.toString())
                    "contract" -> contractFilterData(s.toString())
                    "debt" -> debtFilterData(s.toString())
                    "employee" -> employeeFilterData(s.toString())
                    "salary" -> salaryFilterData(s.toString())
                    "fruit" -> fruitFilterData(s.toString())
                    "contact" -> phoneContactFilterData(s.toString())

                }


            }

        })
    }


    fun costFilterData(name: String) {
        var tempList = ArrayList<CostDataBaseModel>()

        for (model: CostDataBaseModel in costList) {
            if (model.costReason.contains(name)) {
                tempList.add(model)
            }
        }
        costAdapter.updateCostList(tempList)

    }

    fun contractFilterData(name: String) {
        var tempList = ArrayList<ContractDataBaseModel>()

        for (model: ContractDataBaseModel in contractList) {
            if (model.name.contains(name)) {
                tempList.add(model)
            }
        }
        contractAdapter.updateContractList(tempList)

    }

    fun debtFilterData(name: String) {
        var tempList = ArrayList<DebtDataBaseModel>()

        for (model: DebtDataBaseModel in debtList) {
            if (model.name.contains(name)) {
                tempList.add(model)
            }
        }
        debtAdapter.updateDebtList(tempList)

    }

    fun employeeFilterData(name: String) {
        var tempList = ArrayList<EmployeeManagementDataBaseModel>()

        for (model: EmployeeManagementDataBaseModel in employeeList) {
            if (model.firstName.contains(name)) {
                tempList.add(model)
            }
        }
        employeeAdapter.updateEmployeeList(tempList)

    }

    fun fruitFilterData(name: String) {
        var tempList = ArrayList<FruitDataBaseModel>()

        for (model: FruitDataBaseModel in fruitList) {
            if (model.name.contains(name)) {
                tempList.add(model)
            }
        }
        fruitAdapter.updateFruitList(tempList)

    }

    fun salaryFilterData(name: String) {
        var tempList = ArrayList<SalaryDataBaseModel>()

        for (model: SalaryDataBaseModel in salaryList) {
            if (model.name.contains(name)) {
                tempList.add(model)
            }
        }
        salaryAdapter.updateSalaryList(tempList)

    }

    fun phoneContactFilterData(name: String) {
        var tempList = ArrayList<PhoneContactDataBaseModel>()

        for (model: PhoneContactDataBaseModel in phoneContactList) {
            if (model.firstName.contains(name)) {
                tempList.add(model)
            }
        }
        phoneContactAdapter.updatePhoneContactList(tempList)

    }


    fun getCostDataAndAdaptIt() {
        dbHandler = DataBaseHelper(this)
        val dataGeneratorFromDataBase = DataGeneratorFromDataBase()
        for (i in 1..dbHandler!!.getCostTableRowCount()) {
            val index = dbHandler!!.readCostInfo(i)
            dataGeneratorFromDataBase.costGenerateData(
                index?.reason.toString(),
                index?.amount.toString(),
                index?.date.toString()
            )

        }
        costList = dataGeneratorFromDataBase.getProduct("cost") as ArrayList<CostDataBaseModel>
        costAdapter = CostRecycleViewAdapter(this, costList) { costDataBaseModel ->
            costDialogMassage(
                costDataBaseModel.costReason,
                costDataBaseModel.costAmount,
                costDataBaseModel.costDate
            )
            println()
        }

    }

    fun costDialogMassage(costReason: String, costAmount: Long, costDate: String) {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("")
        dialog.setIcon(R.drawable.ic_warning)
        dialog.setMessage(
            "دلیل هزینه انجام شده : " + costReason + "\n" +
                    "مقدار هزینه : " + costAmount + "\n" +
                    "تاریخ انجام شده : " + costDate
        )
        dialog.setPositiveButton("ویرایش") { _, which ->
            var intent = Intent(this,CostActivity::class.java)
            intent.putExtra("UPDATE","update")
            intent.putExtra("COSTREASON",costReason)
            intent.putExtra("COSTAMOUNT",costAmount)
            intent.putExtra("COSTDATE",costDate)
            startActivity(intent)
        }


        dialog.show()

    }

    fun getContractDataAndAdaptIt() {
        dbHandler = DataBaseHelper(this)
        val dataGeneratorFromDataBase = DataGeneratorFromDataBase()
        for (i in 1..dbHandler!!.getContactTableRowCount()) {
            val index = dbHandler!!.readContractInfo(i)
            dataGeneratorFromDataBase.contractGenerateData(
                index?.name.toString(),
                index?.nationalCode!!.toLong(),
                index.transactionVolume!!.toLong(),
                index.contractTitle.toString(),
                index.productInformation.toString(),
                index.date.toString()
            )

        }
        contractList =
            dataGeneratorFromDataBase.getProduct("contract") as ArrayList<ContractDataBaseModel>
        contractAdapter = ContractRecycleViewAdapter(this, contractList) { contractDataBaseModel ->
            contractDialogMassage(
                contractDataBaseModel.name,
                contractDataBaseModel.nationalCode,
                contractDataBaseModel.transactionVolume,
                contractDataBaseModel.contractTitle,
                contractDataBaseModel.productInformation,
                contractDataBaseModel.date
            )

        }
    }

    fun contractDialogMassage(
        contractName: String,
        nationalCode: Long,
        contractTransactionVolume: Long,
        contractTitle: String,
        contractProductInformation: String,
        contractDate: String
    ) {

        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("")
        dialog.setIcon(R.drawable.ic_warning)
        dialog.setMessage(
            "نام طرف قرارداد : " + contractName + "\n" +
                    "کد ملی : " + nationalCode + "\n" +
                    "ارزش مالی قرارداد : " + contractTransactionVolume + "\n" +
                    "عنوان قرارداد : " + contractTitle + "\n" +
                    "مشخصات محصول : " + contractProductInformation + "\n" +
                    "تاریخ عقد قراردا : " + contractDate
        )
        dialog.setPositiveButton("ویرایش") { _, which ->
            val intent = Intent(this,ContractActivity::class.java)
            intent.putExtra("UPDATE","update")
            intent.putExtra("CONTRACTNAME",contractName)
            intent.putExtra("NATIONALCODE",nationalCode)
            intent.putExtra("TRANSACTIONVOLUME",contractTransactionVolume)
            intent.putExtra("CONTRACTTITLE",contractTitle)
            intent.putExtra("PRODUCTINFO",contractProductInformation)
            intent.putExtra("CONTRACTDATE",contractDate)
            startActivity(intent)
        }


        dialog.show()

    }

    fun getDebtDataAndAdaptIt() {
        dbHandler = DataBaseHelper(this)
        val dataGeneratorFromDataBase = DataGeneratorFromDataBase()
        for (i in 1..dbHandler!!.getDebtTableRowCount()) {
            val index = dbHandler!!.readDebtDataBase(i)
            dataGeneratorFromDataBase.debtGenerateData(
                index?.Name.toString(),
                index?.PhoneNumber!!.toLong(),
                index.DebtAmount!!.toLong()
            )

        }
        debtList = dataGeneratorFromDataBase.getProduct("debt") as ArrayList<DebtDataBaseModel>
        debtAdapter = DebtRecycleViewAdapter(this, debtList) { debtDataBaseModel ->
            debtDialogMassage(
                debtDataBaseModel.name,
                debtDataBaseModel.phoneNumber,
                debtDataBaseModel.debtAmount
            )

        }
    }

    fun debtDialogMassage(debtorName: String, debtorPhoneNum: Long, debtAmount: Long) {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("")
        dialog.setIcon(R.drawable.ic_warning)
        dialog.setMessage(
            "نام بدهکار : " + debtorName + "\n" +
                    "شماره تماس بدهکار : " + debtorPhoneNum + "\n" +
                    "مقدار بدهی : " + debtAmount
        )
        dialog.setPositiveButton("ویرایش") { _, which ->
            var intent = Intent(this,DebtActivity::class.java)
            intent.putExtra("DEBTORNAME",debtorName)
            intent.putExtra("DEBTORPHONENUM",debtorPhoneNum.toString())
            intent.putExtra("DEBTAMOUNT",debtAmount.toString())
            intent.putExtra("UPDATE","update")
            startActivity(intent)
        }


        dialog.show()

    }

    fun getEmployeeDataAndAdaptIt() {
        dbHandler = DataBaseHelper(this)
        val dataGeneratorFromDataBase = DataGeneratorFromDataBase()
        for (i in 1..dbHandler!!.getEmployeeManagementTableRowCount()) {
            val index = dbHandler!!.readEmployeeManagementInfo(i)
            dataGeneratorFromDataBase.employeeGenerateData(
                index?.firstName.toString(),
                index?.phoneNumber!!.toLong(),
                index?.dateOfEmployee.toString(),
                index?.salary!!.toLong(),
                index?.jobTitle.toString()
            )

        }
        employeeList =
            dataGeneratorFromDataBase.getProduct("employee") as ArrayList<EmployeeManagementDataBaseModel>
        employeeAdapter =
            EmployeeRecycleViewAdapter(this, employeeList) { employeeManagementDataBaseModel ->
                employeeDialogMassage(
                    employeeManagementDataBaseModel.firstName,
                    employeeManagementDataBaseModel.phoneNumber,
                    employeeManagementDataBaseModel.dateOfEmployee,
                    employeeManagementDataBaseModel.salary,
                    employeeManagementDataBaseModel.jobTitle
                )

            }
    }

    fun employeeDialogMassage(
        employeeName: String,
        employeePhone: Long,
        employeeDateOfEmployee: String,
        employeeSalary: Long,
        employeeJobTitle: String
    ) {

        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("")
        dialog.setIcon(R.drawable.ic_warning)
        dialog.setMessage(
            "نام بدهکار : " + employeeName + "\n" +
                    "شماره تماس کارمند : " + employeePhone + "\n" +
                    "تاریخ استخدام : " + employeeDateOfEmployee + "\n" +
                    "حقوق پایه کارمند : " + employeeSalary + "\n" +
                    "سمت کاری کارمند : " + employeeJobTitle + "\n"
        )
        dialog.setPositiveButton("ویرایش") { _, which ->
            var intent = Intent(this,StaffManagingActivity::class.java)
            intent.putExtra("EMPLOYEENAME",employeeName)
            intent.putExtra("EMPLOYEEPHONENUM",employeePhone)
            intent.putExtra("EMPLOYEEDATEOFEMPLOYEE",employeeDateOfEmployee)
            intent.putExtra("EMPLOYEESALARY",employeeSalary)
            intent.putExtra("EMPLOYEEJOBTITLE",employeeJobTitle)
            intent.putExtra("UPDATE","update")
            startActivity(intent)
        }


        dialog.show()
    }

    fun getFruitDataAndAdaptIt() {
        dbHandler = DataBaseHelper(this)
        val dataGeneratorFromDataBase = DataGeneratorFromDataBase()
        for (i in 1..dbHandler!!.getFruitTableRowCount()) {
            val index = dbHandler!!.readFruitDataBase(i)
            dataGeneratorFromDataBase.fruitGenerateData(
                index?.name.toString(),
                index?.price!!.toLong(),
                index.qlt!!.toInt()
            )

        }
        fruitList = dataGeneratorFromDataBase.getProduct("fruit") as ArrayList<FruitDataBaseModel>
        fruitAdapter = FruitRecycleViewAdapter(this, fruitList) { fruitDataBaseModel ->

            fruitDialogMassage(fruitDataBaseModel.name,fruitDataBaseModel.price.toInt(),fruitDataBaseModel.qlt)
        }
    }

    fun fruitDialogMassage(fruitName: String, fruitPrice: Int, fruitQlt: Int){
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("")
        dialog.setIcon(R.drawable.ic_warning)
        dialog.setMessage(
            "نام میوه : " + fruitName + "\n" +
                    "قیمت پایه میوه : " + fruitPrice + "\n" +
                    "درجه کیفیت : " + fruitQlt
        )
        dialog.setPositiveButton("ویرایش") { _, which ->
            var intent = Intent(this,FruitInformationActivity::class.java)
            intent.putExtra("FRUITNAME",fruitName)
            intent.putExtra("FRUITPRICE",fruitPrice)
            intent.putExtra("FRUITQLT",fruitQlt)
            intent.putExtra("UPDATE","update")
            startActivity(intent)
        }


        dialog.show()
    }

    fun getContactDataAndAdaptIt() {
        dbHandler = DataBaseHelper(this)
        val dataGeneratorFromDataBase = DataGeneratorFromDataBase()
        for (i in 1..dbHandler!!.getContactTableRowCount()) {
            val index = dbHandler!!.readContactInfo(i)
            dataGeneratorFromDataBase.phoneContactGenerateData(
                index?.firstName.toString(),
                index?.title.toString(),
                index?.phoneNumber!!.toLong()
            )

        }
        phoneContactList =
            dataGeneratorFromDataBase.getProduct("contact") as ArrayList<PhoneContactDataBaseModel>
        phoneContactAdapter =
            PhoneContactRecycleViewAdapter(this, phoneContactList) { phoneContactDataBaseModel ->
                var intent = Intent(this,SMSPanelActivity::class.java)
                //var num = intent.getStringExtra("PASTENUMBER")
                System.out.println("joooooooon be maola velet nmikonam"+ number)
                intent.putExtra("NUMBER",  phoneContactDataBaseModel.phoneNumber.toString() +"," )
                startActivity(intent)
                //this.finish()
            }
    }




    fun getSalaryDataAndAdaptIt() {
        dbHandler = DataBaseHelper(this)
        val dataGeneratorFromDataBase = DataGeneratorFromDataBase()
        for (i in 1..dbHandler!!.getSalaryTableRowCount()) {
            val index = dbHandler!!.readSalaryDataBase(i)
            dataGeneratorFromDataBase.salaryGenerateData(
                index?.name.toString(),
                index?.salary!!.toInt(),
                index.phoneNumber!!.toLong()
            )

        }
        salaryList =
            dataGeneratorFromDataBase.getProduct("salary") as ArrayList<SalaryDataBaseModel>
        salaryAdapter = SalaryRecycleViewAdapter(this, salaryList) { salaryDataBaseModel ->
            salaryDialogMassage(salaryDataBaseModel.name,salaryDataBaseModel.salary,salaryDataBaseModel.phoneNumber)

        }
    }
    fun salaryDialogMassage(salaryName: String, salary: Int, salaryPhone: Long){
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("")
        dialog.setIcon(R.drawable.ic_warning)
        dialog.setMessage(
            "نام کارمند : " + salaryName + "\n" +
                    "حقوق پرداختی : " + salary + "\n" +
                    "شماره تلفن کارمند : " + salaryPhone
        )
        dialog.setPositiveButton("ویرایش") { _, which ->
            var intent = Intent(this,SalaryActivity::class.java)
            intent.putExtra("SALARYNAME",salaryName)
            intent.putExtra("SALARYSALRY",salary)
            intent.putExtra("SLARYPHONE",salaryPhone)
            intent.putExtra("UPDATE","update")
            startActivity(intent)
        }


        dialog.show()
    }
}