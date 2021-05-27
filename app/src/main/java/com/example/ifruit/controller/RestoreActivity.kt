package com.example.ifruit.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.annimation.*
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
            val costFileRead:MutableList<String> = File("data/data/com.example.ifruit/files/cost.csv").readLines() as MutableList<String>
            val contractFileRead:MutableList<String> = File("data/data/com.example.ifruit/files/contract.csv").readLines() as MutableList<String>
            val contactFileRead:MutableList<String> = File("data/data/com.example.ifruit/files/contact.csv").readLines() as MutableList<String>
            val employeeFileRead:MutableList<String> = File("data/data/com.example.ifruit/files/employee.csv").readLines() as MutableList<String>
            val salaryFileRead:MutableList<String> = File("data/data/com.example.ifruit/files/salary.csv").readLines() as MutableList<String>
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

            //cost repairing database is successful
//            costFileRead.forEach { read->
//                if(counter>0){
//                    val costCorrectForm:List<String> = read.split(",")
//                    val newCost = CostInfo()
//                    newCost.reason = costCorrectForm.get(1)
//                    newCost.amount = costCorrectForm.get(2).toLong()
//                    newCost.date = costCorrectForm.get(3)
//                    dbHandler?.createCostInfo(newCost)
//                }
//                counter++
//            }
//            counter = 0

            //contract repairing database is successful
//            contractFileRead.forEach { read ->
//                if(counter>0){
//                    val contractCorrectForm:List<String> = read.split(",")
//                    val newContract = ContractInfo()
//                    newContract.name = contractCorrectForm.get(1)
//                    newContract.nationalCode = contractCorrectForm.get(2).toLong()
//                    newContract.transactionVolume = contractCorrectForm.get(3).toLong()
//                    newContract.contractTitle = contractCorrectForm.get(4)
//                    newContract.productInformation = contractCorrectForm.get(5)
//                    newContract.date = contractCorrectForm.get(6)
//                    dbHandler?.createContractInfo(newContract)
//                }
//                counter++
//            }
//            counter = 0

            //contact repairing database is successful
//            contactFileRead.forEach { read->
//                if(counter>0){
//                    val contactCorrectForm:List<String> = read.split(",")
//                    val newContact = ContactInfo()
//                    newContact.firstName = contactCorrectForm.get(1)
//                    newContact.title = contactCorrectForm.get(2)
//                    newContact.phoneNumber = contactCorrectForm.get(3).toLong()
//                    dbHandler?.createContactInfo(newContact)
//                }
//                counter++
//            }
//            counter = 0

            //employee repairing database successful
//            employeeFileRead.forEach { read ->
//                if(counter>0){
//                    val employeeCorrectForm:List<String> = read.split(",")
//                    val newEmployee = EmployeeManagementInfo()
//                    newEmployee.firstName = employeeCorrectForm.get(1)
//                    newEmployee.phoneNumber = employeeCorrectForm.get(2).toLong()
//                    newEmployee.dateOfEmployee = employeeCorrectForm.get(3)
//                    newEmployee.salary = employeeCorrectForm.get(4).toLong()
//                    newEmployee.jobTitle = employeeCorrectForm.get(5)
//                    dbHandler?.createEmployeeManagementInfo(newEmployee)
//                }
//                counter++
//            }
//            counter = 0


            salaryFileRead.forEach { read ->
                if(counter>0){
                    val salaryCorrectForm:List<String> = read.split(",")
                    val newSalary = SalaryInfo()
                    newSalary.name = salaryCorrectForm.get(1)
                    newSalary.salary = salaryCorrectForm.get(2).toInt()
                    newSalary.phoneNumber = salaryCorrectForm.get(3).toLong()
                    dbHandler?.addSalary(newSalary)
                }
                counter++
            }
            counter = 0





        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}