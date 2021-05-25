package com.example.ifruit.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageButton
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_view)

        val tableRequire: String = intent.getStringExtra("TABLE").toString()

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

        when (tableRequire) {
            "cost" -> getCostDataAndAdaptIt()
            "contract" -> getContractDataAndAdaptIt()
            "debt" -> getDebtDataAndAdaptIt()
            "employee" -> getEmployeeDataAndAdaptIt()
            "salary" -> getSalaryDataAndAdaptIt()
            "fruit" -> getFruitDataAndAdaptIt()
            else -> getContactDataAndAdaptIt()
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
            else -> listProduct.adapter = phoneContactAdapter

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
                    else -> phoneContactFilterData(s.toString())

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
        costAdapter = CostRecycleViewAdapter(this, costList)
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
                index.date!!.toLong()
            )

        }
        contractList =
            dataGeneratorFromDataBase.getProduct("contract") as ArrayList<ContractDataBaseModel>
        contractAdapter = ContractRecycleViewAdapter(this, contractList)
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
        debtAdapter = DebtRecycleViewAdapter(this, debtList)
    }

    fun getEmployeeDataAndAdaptIt() {
        dbHandler = DataBaseHelper(this)
        val dataGeneratorFromDataBase = DataGeneratorFromDataBase()
        for (i in 1..dbHandler!!.getEmployeeManagementTableRowCount()) {
            val index = dbHandler!!.readEmployeeManagementInfo(i)
            dataGeneratorFromDataBase.employeeGenerateData(
                index?.firstName.toString(),
                index?.phoneNumber!!.toLong(),
                index?.dateOfEmployee!!.toLong(),
                index?.salary!!.toLong(),
                index?.jobTitle.toString()
            )

        }
        employeeList =
            dataGeneratorFromDataBase.getProduct("employee") as ArrayList<EmployeeManagementDataBaseModel>
        employeeAdapter = EmployeeRecycleViewAdapter(this, employeeList)
    }

    fun getFruitDataAndAdaptIt() {
        dbHandler = DataBaseHelper(this)
        val dataGeneratorFromDataBase = DataGeneratorFromDataBase()
        for (i in 1..dbHandler!!.getFruitTableRowCount()) {
            val index = dbHandler!!.readFruitDataBase(i)
            dataGeneratorFromDataBase.fruitGenerateData(
                index?.name.toString(),
                index?.price!!.toLong(),
                index.qlt.toInt()
            )

        }
        fruitList = dataGeneratorFromDataBase.getProduct("fruit") as ArrayList<FruitDataBaseModel>
        fruitAdapter = FruitRecycleViewAdapter(this, fruitList)
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
        phoneContactAdapter = PhoneContactRecycleViewAdapter(this, phoneContactList)
    }

    fun getSalaryDataAndAdaptIt() {
        dbHandler = DataBaseHelper(this)
        val dataGeneratorFromDataBase = DataGeneratorFromDataBase()
        for (i in 1..dbHandler!!.getSalaryTableRowCount()) {
            val index = dbHandler!!.readSalaryDataBase(i)
            dataGeneratorFromDataBase.salaryGenerateData(
                index?.name.toString(),
                index?.salary!!.toInt(),
                index.phoneNumber.toLong()
            )

        }
        salaryList =
            dataGeneratorFromDataBase.getProduct("salary") as ArrayList<SalaryDataBaseModel>
        salaryAdapter = SalaryRecycleViewAdapter(this, salaryList)
    }
}