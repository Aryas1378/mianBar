package com.example.ifruit.model

import com.example.ifruit.model.recycleviewmodel.*

class DataGeneratorFromDataBase {

//    var costIndexLists = listOf(
//        CostIndex("costReason", 1000, "costDate"),
//        CostIndex("costReason", 2000, "costDate")
//    )


    var costDataBaseModelList :ArrayList<CostDataBaseModel> = ArrayList()
    var contractDataBaseModelList : ArrayList<ContractDataBaseModel> = ArrayList()
    var debtDataBaseModelList : ArrayList<DebtDataBaseModel> = ArrayList()
    var employeeDataBaseModelList : ArrayList<EmployeeManagementDataBaseModel> = ArrayList()
    var fruitDataBaseModelList : ArrayList<FruitDataBaseModel> = ArrayList()
    var salaryDataBaseModelList : ArrayList<SalaryDataBaseModel> = ArrayList()
    var phoneContactDataBaseModelList : ArrayList<PhoneContactDataBaseModel> = ArrayList()

    fun costGenerateData(costReason: String, costAmount: String, costDate: String) {
        costDataBaseModelList.plusAssign(CostDataBaseModel(costReason, costAmount.toLong(), costDate))

    }

    fun contractGenerateData( name: String,
                              nationalCode: Long,
                              transactionVolume: Long,
                              contractTitle: String,
                              productInformation: String,
                              date: Long) {
        contractDataBaseModelList.plusAssign(ContractDataBaseModel(name, nationalCode, transactionVolume,contractTitle,productInformation,date))

    }
    fun debtGenerateData( name:String,phoneNumber:Long,  debtAmount:Long) {
        debtDataBaseModelList.plusAssign(DebtDataBaseModel(name, phoneNumber, debtAmount))

    }
    fun employeeGenerateData( firstName: String,
                              phoneNumber: Long,
                              dateOfEmployee: Long,
                              salary: Long,
                              jobTitle: String) {
        employeeDataBaseModelList.plusAssign(EmployeeManagementDataBaseModel(firstName, phoneNumber, dateOfEmployee, salary, jobTitle))

    }
    fun fruitGenerateData( name: String ,  price: Long, qlt: Int) {
        fruitDataBaseModelList.plusAssign(FruitDataBaseModel(name, price, qlt))

    }
    fun salaryGenerateData( name: String, salary: Int , phoneNumber : Long) {
        salaryDataBaseModelList.plusAssign(SalaryDataBaseModel(name, salary, phoneNumber))

    }
    fun phoneContactGenerateData( firstName: String, title : String, phoneNumber: Long) {
        phoneContactDataBaseModelList.plusAssign(PhoneContactDataBaseModel(firstName, title, phoneNumber))

    }


    fun getProduct(category: String): ArrayList<*> {
        return when (category) {
            "cost" -> costDataBaseModelList
            "contract" -> contractDataBaseModelList
            "debt" -> debtDataBaseModelList
            "employee" -> employeeDataBaseModelList
            "fruit" -> fruitDataBaseModelList
            "salary" ->salaryDataBaseModelList
            else -> phoneContactDataBaseModelList
        }

    }


}