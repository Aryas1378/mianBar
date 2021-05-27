package com.example.ifruit.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.annimation.*
import com.example.ifruit.model.*

class DataBaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    //sql command to creating all table

    var CREATE_DEBT_TABLE = "CREATE TABLE " + TABLE_NAME6 +
            "(" + KEY_DEBT_ID + " INTEGER PRIMARY KEY," +
            KEY_DEBT_NAME + " TEXT," +
            KEY_DEBT_PHONE_NUMBER + " LONG," +
            KEY_DEBT_AMOUNT + " LONG)"

    var CREATE_COSTS_TABLE =
        "CREATE TABLE " + TABLE_NAME5 + "(" + KEY_COST_ID + " INTEGER PRIMARY KEY," +
                KEY_COST_REASON + " TEXT," +
                KEY_COST_AMOUNT + " BIGINT," +
                KEY_COST_DATE + " TEXT)"
    var CREATE_CONTRACT_TABLE =
        "CREATE TABLE " + TABLE_NAME4 + "(" + KEY_CONTRACT_ID + " INTEGER PRIMARY KEY," +
                KEY_CONTRACT_NAME + " VARCHAR(30)," +
                KEY_CONTRACT_NATIONAL_CODE + " BIGINT," +
                KEY_CONTRACT_TRANSACION_VOlLME + " BIGINT," +
                KEY_CONTRACT_CONTRACT_TITLE + " TEXT," +
                KEY_CONTRACT_PRODUCT_INFORMATON + " TEXT," +
                KEY_CONTRACT_DATE + " TEXT)"
    val CREATE_USER_TABLE =
        "CREATE TABLE " + TABLE_NAME1 + "(" + KEY_USER_ID + " INTEGER PRIMARY KEY," +
                KEY_USER_NAME + " TEXT," +
                KEY_USER_EMAIL + " TEXT," +
                KEY_USER_PASSWORD + " TEXT)"
    val CREATE_FRUIT_TABLE =
        "CREATE TABLE " + TABLE_NAME2 + "(" + KEY_FRUIT_ID + " INTEGER PRIMARY KEY ," +
                KEY_FRUIT_NAME + " TEXT," +
                KEY_FRUIT_PRICE + " INTEGER," +
                KEY_FRUIT_QUALITY + " INTEGER)"

    val CREATE_SALARY_TABLE =
        "CREATE TABLE " + TABLE_NAME3 + "(" + KEY_SALARY_ID + " INTEGER PRIMARY KEY ," +
                KEY_SALARY_NAME + " TEXT," +
                KEY_SALARY_SALARY + " INTEGER," +
                KEY_SALARY_PHONENUMBER + " LONG)"

    val CREATE_EMPLOYEE_MANAGEMENT_TABLE =
        "CREATE TABLE " + TABLE_NAME7 + "(" + KEY_EMPLOYEE_MANAGEMENT_ID + " INTEGER PRIMARY KEY," +
                KEY_EMPLOYEE_MANAGEMENT_FIRST_NAME + " VARCHAR(30)," +
                KEY_EMPLOYEE_MANAGEMENT_PHONE_NUMBER + " LONG," +
                KEY_EMPLOYEE_MANAGEMENT_DATE_OF_EMPLOYEE + " TEXT," +
                KEY_EMPLOYEE_MANAGEMENT_SALARY + " LONG," +
                KEY_EMPLOYEE_MANAGEMENT_JOB_TITLE + " TEXT)"

    val CREATE_SETTING_TABLE =
        "CREATE TABLE " + TABLE_NAME8 + "(" + KEY_SETTING_ID + " INTEGER PRIMARY KEY," +
                KET_SETTING_NAME + " VARCHAR(30)," +
                KEY_SETTING_COLOR + " TEXT," +
                KEY_SETTING_FONT + " TEXT)"

    val CREATE_CONTACT_TABLE =
        "CREATE TABLE " + TABLE_NAME9 + "(" + KEY_CONTACT_ID + " INTEGER PRIMARY KEY," +
                KEY_CONTACT_FIRST_NAME + " VARCHAR(30)," +
                KEY_CONTACT_TITLE + " VARCHAR(30)," +
                KEY_CONTACT_PHONE_NUMBER + " LONG)"

    val CREATE_INVOICE_TABLE =
        "CREATE TABLE " + TABLE_NAME10 + "(" + KEY_INVOICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                KEY_INVOICE_FRUIT_NAME + " TEXT," +
                KEY_INVOICE_FRUIT_AMOUNT + " FLOAT," +
                KEY_INVOICE_SUM + " INTEGER," + KEY_INVOICE_NUM + " INTEGER," +
                KEY_INVOICE_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_USER_TABLE)
        db?.execSQL(CREATE_FRUIT_TABLE)
        db?.execSQL(CREATE_CONTRACT_TABLE)
        db?.execSQL(CREATE_DEBT_TABLE)
        db?.execSQL(CREATE_COSTS_TABLE)
        db?.execSQL(CREATE_SALARY_TABLE)
        db?.execSQL(CREATE_EMPLOYEE_MANAGEMENT_TABLE)
        db?.execSQL(CREATE_SETTING_TABLE)
        db?.execSQL(CREATE_CONTACT_TABLE)
        db?.execSQL(CREATE_INVOICE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME1")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME2")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME3")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME4")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME5")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME6")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME7")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME8")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME9")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME10")
        onCreate(db)
    }

    ////////////////////////////////
    //CRUD functions for user table
    ////////////////////////////////

    fun createUser(userInfo: UserInfo) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(KEY_USER_ID, userInfo.id)
        values.put(KEY_USER_NAME, userInfo.name)
        values.put(KEY_USER_EMAIL, userInfo.email)
        values.put(KEY_USER_PASSWORD, userInfo.password)

        db.insert(TABLE_NAME1, null, values)
        db.close()
    }

    val allUserInfo: List<UserInfo>
        get() {
            val listUsers = ArrayList<UserInfo>()
            val selectQuery = "SELECT * FROM $TABLE_NAME1"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()) {
                do {
                    val user = UserInfo()
                    user.id = cursor.getInt(cursor.getColumnIndex(KEY_USER_ID))
                    user.name = cursor.getString(cursor.getColumnIndex(KEY_USER_NAME))
                    user.email = cursor.getString(cursor.getColumnIndex(KEY_USER_EMAIL))
                    user.password = cursor.getString(cursor.getColumnIndex(KEY_USER_PASSWORD))

                    listUsers.add(user)

                } while (cursor.moveToNext())

            }
            db.close()
            return listUsers
        }

    fun deleteUser(userInfo: UserInfo) {
        val db = this.writableDatabase

        db.delete(TABLE_NAME1, "$KEY_USER_ID=?", arrayOf(userInfo.id.toString()))
        db.close()
    }

    fun getUserTableRowCount(): Int {
        val db: SQLiteDatabase = readableDatabase
        val counter = "SELECT * FROM $TABLE_NAME1"
        var cursor: Cursor = db.rawQuery(counter, null)
        return cursor.count

    }

    fun readUser(id: Int): UserInfo? {
        var db: SQLiteDatabase = writableDatabase
        var cursor = db.query(
            TABLE_NAME1, arrayOf(
                KEY_USER_ID,
                KEY_USER_NAME,
                KEY_USER_EMAIL,
                KEY_USER_PASSWORD
            ), KEY_USER_ID + "=?", arrayOf(id.toString()), null, null, null
        )

        if (cursor != null) {
            cursor.moveToFirst()


            val userInfo = UserInfo()
            userInfo.name = cursor.getString(cursor.getColumnIndex(KEY_USER_NAME))
            userInfo.email = cursor.getString(cursor.getColumnIndex(KEY_USER_EMAIL))
            userInfo.password = cursor.getString(cursor.getColumnIndex(KEY_USER_PASSWORD))



            return userInfo

        } else
            return null
    }
    ////////////////////////////////
    //CRUD functions for cost table
    ////////////////////////////////

    fun createCostInfo(costInfo: CostInfo) {

        var db: SQLiteDatabase = writableDatabase

        var values: ContentValues = ContentValues();
        values.put(KEY_COST_REASON, costInfo.reason)
        values.put(KEY_COST_AMOUNT, costInfo.amount)
        values.put(KEY_COST_DATE, costInfo.date)


        db.insert(TABLE_NAME5, null, values)
        db.close()

        Log.d("DATA INSERTED", "SUCCESS")
    }

    fun readCostInfo(id: Int): CostInfo? {
        var db: SQLiteDatabase = writableDatabase
        var cursor = db.query(
            TABLE_NAME5, arrayOf(
                KEY_COST_ID,
                KEY_COST_REASON,
                KEY_COST_AMOUNT,
                KEY_COST_DATE

            ), KEY_COST_ID + "=?", arrayOf(id.toString()), null, null, null
        )

        if (cursor != null) {
            cursor.moveToFirst()


            val costInfo = CostInfo()
            costInfo.reason = cursor.getString(cursor.getColumnIndex(KEY_COST_REASON))
            costInfo.amount = cursor.getLong(cursor.getColumnIndex(KEY_COST_AMOUNT))
            costInfo.date = cursor.getString(cursor.getColumnIndex(KEY_COST_DATE))



            return costInfo

        } else
            return null


    }

    fun updateCostRow(costInfo1: CostInfo, costInfo2: CostInfo) {
        val db: SQLiteDatabase = writableDatabase

        val updateCommand =
            "UPDATE " + TABLE_NAME5 + " SET " +
                    KEY_COST_REASON + " = '" + costInfo1.reason + "', " +
                    KEY_COST_AMOUNT + " = '" + costInfo1.amount + "', " +
                    KEY_COST_DATE + " = '" + costInfo1.date + "' " +
                    "WHERE $KEY_COST_REASON = '" + costInfo2.reason + "' AND " +
                    "$KEY_COST_AMOUNT = '" + costInfo2.amount + "' AND " +
                    "$KEY_COST_DATE = '" + costInfo2.date + "'"

        db.execSQL(updateCommand)

    }

    fun getCostTableRowCount(): Int {
        val db: SQLiteDatabase = readableDatabase
        val counter = "SELECT * FROM $TABLE_NAME5"
        var cursor: Cursor = db.rawQuery(counter, null)
        return cursor.count

    }

    fun getAllFee(): Cursor {
        val employeeDb =this.readableDatabase
        val id:Int=100
//        val selectQuery="name = " + qname
        val cursor: Cursor = employeeDb.query(TABLE_NAME5, arrayOf( //table5
             KEY_COST_ID,
             KEY_COST_REASON,
             KEY_COST_AMOUNT,
             KEY_COST_DATE

        ), null, null,null,null, KEY_INVOICE_TIMESTAMP + " DESC")
        cursor.moveToFirst()
        employeeDb.close()
        return cursor
    }

    ////////////////////////////////
    //CRUD functions for debt table
    ////////////////////////////////

    fun createDebtDataBase(debtInfo: DebtInfo) {

        var db: SQLiteDatabase = writableDatabase
        var values: ContentValues = ContentValues()

        values.put(KEY_DEBT_NAME, debtInfo.Name)
        values.put(KEY_DEBT_PHONE_NUMBER, debtInfo.PhoneNumber)
        values.put(KEY_DEBT_AMOUNT, debtInfo.DebtAmount)

        db.insert(TABLE_NAME6, null, values)
        db.close()

        Log.d("Data Insertion", "Success")
    }

    fun readDebtDataBase(id: Int): DebtInfo? {
        var db: SQLiteDatabase = writableDatabase
        var cursor: Cursor = db.query(
            TABLE_NAME6, arrayOf(
                KEY_DEBT_ID,
                KEY_DEBT_NAME,
                KEY_DEBT_PHONE_NUMBER,
                KEY_DEBT_AMOUNT
            ), KEY_DEBT_ID + "=?", arrayOf(id.toString()),
            null,
            null,
            null
        )

        if (cursor != null) {
            cursor.moveToFirst()
            var Mydb = DebtInfo()
            Mydb.Name = cursor.getString(cursor.getColumnIndex(KEY_DEBT_NAME))
            Mydb.PhoneNumber = cursor.getLong(cursor.getColumnIndex(KEY_DEBT_PHONE_NUMBER))
            Mydb.DebtAmount = cursor.getLong(cursor.getColumnIndex(KEY_DEBT_AMOUNT))


            return Mydb
        }
        return null
    }

    fun updateDebtRow(oldDebtInfo: DebtInfo, newDebtInfo: DebtInfo) {
        val db: SQLiteDatabase = writableDatabase

        val updateCommand =
            "UPDATE " + TABLE_NAME6 + " SET " +
                    KEY_DEBT_NAME + " = '" + oldDebtInfo.Name + "', " +
                    KEY_DEBT_PHONE_NUMBER + " = '" + oldDebtInfo.PhoneNumber + "', " +
                    KEY_DEBT_AMOUNT + " = '" + oldDebtInfo.DebtAmount + "' " +
                    "WHERE $KEY_DEBT_NAME = '" + newDebtInfo.Name + "' AND " +
                    "$KEY_DEBT_PHONE_NUMBER = '" + newDebtInfo.PhoneNumber + "' AND " +
                    "$KEY_DEBT_AMOUNT = '" + newDebtInfo.DebtAmount + "'"

        db.execSQL(updateCommand)

    }

    fun getDebtTableRowCount(): Int {
        val db: SQLiteDatabase = readableDatabase
        val counter = "SELECT * FROM $TABLE_NAME6"
        var cursor: Cursor = db.rawQuery(counter, null)
        return cursor.count

    }

    ////////////////////////////////
    //CRUD functions for contract table
    ////////////////////////////////
    fun createContractInfo(contractinfo: ContractInfo) {

        var db: SQLiteDatabase = writableDatabase

        var values: ContentValues = ContentValues();
        values.put(KEY_CONTRACT_NAME, contractinfo.name)
        values.put(KEY_CONTRACT_NATIONAL_CODE, contractinfo.nationalCode)
        values.put(KEY_CONTRACT_TRANSACION_VOlLME, contractinfo.transactionVolume)
        values.put(KEY_CONTRACT_CONTRACT_TITLE, contractinfo.contractTitle)
        values.put(KEY_CONTRACT_PRODUCT_INFORMATON, contractinfo.productInformation)
        values.put(KEY_CONTRACT_DATE, contractinfo.date)


        db.insert(TABLE_NAME4, null, values)
        db.close()

        Log.d("DATA INSERTED", "SUCCESS")
    }


    fun readContractInfo(id: Int): ContractInfo? {
        var db: SQLiteDatabase = writableDatabase
        var cursor = db.query(
            TABLE_NAME4, arrayOf(
                KEY_CONTRACT_ID,
                KEY_CONTRACT_NAME,
                KEY_CONTRACT_NATIONAL_CODE,
                KEY_CONTRACT_TRANSACION_VOlLME,
                KEY_CONTRACT_CONTRACT_TITLE,
                KEY_CONTRACT_PRODUCT_INFORMATON,
                KEY_CONTRACT_DATE
            ), KEY_CONTRACT_ID + "=?", arrayOf(id.toString()), null, null, null
        )

        if (cursor != null) {
            cursor.moveToFirst()

            var contractinfo = ContractInfo()
            contractinfo.name = cursor.getString(cursor.getColumnIndex(KEY_CONTRACT_NAME))
            contractinfo.nationalCode = cursor.getLong(
                cursor.getColumnIndex(
                    KEY_CONTRACT_NATIONAL_CODE
                )
            )
            contractinfo.transactionVolume = cursor.getLong(
                cursor.getColumnIndex(
                    KEY_CONTRACT_TRANSACION_VOlLME
                )
            )
            contractinfo.contractTitle = cursor.getString(
                cursor.getColumnIndex(
                    KEY_CONTRACT_CONTRACT_TITLE
                )
            )
            contractinfo.productInformation = cursor.getString(
                cursor.getColumnIndex(
                    KEY_CONTRACT_PRODUCT_INFORMATON
                )
            )
            contractinfo.date = cursor.getString(cursor.getColumnIndex(KEY_CONTRACT_DATE))


            return contractinfo

        } else
            return null


    }

    fun updateContractRowDate(oldContractInfo: ContractInfo, newContractInfo: ContractInfo) {
        val db: SQLiteDatabase = writableDatabase

        val updateCommand =
            "UPDATE " + TABLE_NAME4 + " SET " +
                    KEY_CONTRACT_NAME + " = '" + oldContractInfo.name + "', " +
                    KEY_CONTRACT_NATIONAL_CODE + " = '" + oldContractInfo.nationalCode + "', " +
                    KEY_CONTRACT_TRANSACION_VOlLME + " = '" + oldContractInfo.transactionVolume + "' " +
                    KEY_CONTRACT_CONTRACT_TITLE + " = '" + oldContractInfo.contractTitle + "' " +
                    KEY_CONTRACT_PRODUCT_INFORMATON + " = '" + oldContractInfo.productInformation + "' " +
                    KEY_CONTRACT_DATE + " = '" + oldContractInfo.date + "' " +
                    "WHERE $KEY_CONTRACT_NAME = '" + newContractInfo.name + "' AND " +
                    "$KEY_CONTRACT_NATIONAL_CODE = '" + newContractInfo.nationalCode + "' AND " +
                    "$KEY_CONTRACT_TRANSACION_VOlLME = '" + newContractInfo.transactionVolume + "' AND " +
                    "$KEY_CONTRACT_CONTRACT_TITLE = '" + newContractInfo.contractTitle + "' AND " +
                    "$KEY_CONTRACT_PRODUCT_INFORMATON = '" + newContractInfo.productInformation + "' AND " +
                    "$KEY_CONTRACT_DATE = '" + newContractInfo.date + "'"

        db.execSQL(updateCommand)
    }

    fun getContractTableRowCount(): Int {
        val db: SQLiteDatabase = readableDatabase
        val counter = "SELECT * FROM $TABLE_NAME4"
        var cursor: Cursor = db.rawQuery(counter, null)
        return cursor.count

    }

    ////////////////////////////////
    //CRUD functions for fruit data table
    ////////////////////////////////


    fun addFruit(fruit: FruitInfo) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_FRUIT_ID, fruit.id)
        values.put(KEY_FRUIT_NAME, fruit.name)
        values.put(KEY_FRUIT_PRICE, fruit.price)
        values.put(KEY_FRUIT_QUALITY, fruit.qlt)
        db.insert(TABLE_NAME2, null, values)
//        db.close()
    }

    fun readFruitData(): MutableList<FruitInfo> {
        System.out.println("Hello from read fruit")
        var list: MutableList<FruitInfo> = ArrayList()
        val db = this.writableDatabase
        val query = "Select * from " + TABLE_NAME2
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {

                var fruit = FruitInfo()
                fruit.id = cursor.getString(cursor.getColumnIndex(KEY_FRUIT_ID)).toInt()
                fruit.name = cursor.getString(cursor.getColumnIndex(KEY_FRUIT_NAME))
                fruit.price = cursor.getString(cursor.getColumnIndex(KEY_FRUIT_PRICE)).toInt()
                fruit.qlt = cursor.getString(cursor.getColumnIndex(KEY_FRUIT_QUALITY)).toInt()
                list.add(fruit)

            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return list
    }

    fun getAllFruitByName(): List<FruitInfo> {
        val listFruit = ArrayList<FruitInfo>()
        val selectQuery = "SELECT * FROM $TABLE_NAME2"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val fruit = FruitInfo()
                fruit.id = cursor.getInt(cursor.getColumnIndex(KEY_FRUIT_ID))
                fruit.name = cursor.getString(cursor.getColumnIndex(KEY_FRUIT_NAME))
                fruit.price = cursor.getInt(cursor.getColumnIndex(KEY_FRUIT_PRICE))
                fruit.qlt = cursor.getInt(cursor.getColumnIndex(KEY_FRUIT_QUALITY))

                listFruit.add(fruit)

            } while (cursor.moveToNext())

        }
//            db.close()
        return listFruit
    }

    fun getFruitByName(name: String?): MutableList<FruitInfo> {
        var listFruitInfo: MutableList<FruitInfo> = ArrayList()
        val selectQuery = "SELECT name FROM " + TABLE_NAME2 + " where name like " + name
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val fruit = FruitInfo()
                fruit.id = cursor.getString(cursor.getColumnIndex(KEY_FRUIT_ID)).toInt()
                fruit.name = cursor.getString(cursor.getColumnIndex(KEY_FRUIT_NAME))
                fruit.price = cursor.getString(cursor.getColumnIndex(KEY_FRUIT_PRICE)).toInt()
                fruit.qlt = cursor.getString(cursor.getColumnIndex(KEY_FRUIT_QUALITY)).toInt()

                listFruitInfo.add(fruit)

            } while (cursor.moveToNext())

        }

        cursor.close()
        db.close()
        return listFruitInfo

    }

    fun getFruitByName(): Cursor {
        val fruitDb = this.readableDatabase
        val id: Int = 100
//        val selectQuery="name = " + qname
        val cursor: Cursor = fruitDb.query(
            TABLE_NAME2, arrayOf(
                KEY_FRUIT_ID,
                KEY_FRUIT_NAME,
                KEY_FRUIT_PRICE,
                KEY_FRUIT_QUALITY
            ), null, null, null, null, null
        )
        cursor.moveToFirst()
        fruitDb.close()
        return cursor
    }

    fun readFruitDataBase(id: Int): FruitInfo? {
        var db: SQLiteDatabase = writableDatabase
        var cursor: Cursor = db.query(
            TABLE_NAME2, arrayOf(
                KEY_FRUIT_ID,
                KEY_FRUIT_NAME,
                KEY_FRUIT_PRICE,
                KEY_FRUIT_QUALITY
            ), KEY_DEBT_ID + "=?", arrayOf(id.toString()),
            null,
            null,
            null
        )

        if (cursor != null) {
            cursor.moveToFirst()
            var Mydb = FruitInfo()
            Mydb.name = cursor.getString(cursor.getColumnIndex(KEY_FRUIT_NAME))
            Mydb.price = cursor.getInt(cursor.getColumnIndex(KEY_FRUIT_PRICE))
            Mydb.qlt = cursor.getInt(cursor.getColumnIndex(KEY_FRUIT_QUALITY))


            return Mydb
        }
        return null
    }

    fun updateFruitRowData(oldFruitInfo: FruitInfo, newFruitInfo: FruitInfo) {
        val db: SQLiteDatabase = writableDatabase

        val updateCommand =
            "UPDATE " + TABLE_NAME2 + " SET " +
                    KEY_FRUIT_NAME + " = '" + oldFruitInfo.name + "', " +
                    KEY_FRUIT_PRICE + " = '" + oldFruitInfo.price + "', " +
                    KEY_FRUIT_QUALITY + " = '" + oldFruitInfo.qlt + "' " +
                    "WHERE $KEY_FRUIT_NAME = '" + newFruitInfo.name + "' AND " +
                    "$KEY_FRUIT_PRICE = '" + newFruitInfo.price + "' AND " +
                    "$KEY_FRUIT_QUALITY = '" + newFruitInfo.qlt + "'"

        db.execSQL(updateCommand)
    }


    fun getFruitTableRowCount(): Int {
        val db: SQLiteDatabase = readableDatabase
        val counter = "SELECT * FROM $TABLE_NAME2"
        var cursor: Cursor = db.rawQuery(counter, null)
        return cursor.count

    }

    ////////////////////////////////
    //CRUD functions for employee table
    ////////////////////////////////

    fun readSalaryData(): MutableList<SalaryInfo> {
        System.out.println("Hello from read fruit")
        var list: MutableList<SalaryInfo> = ArrayList()
        val db = this.writableDatabase
        val query = "Select * from " + TABLE_NAME3
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {

                var employee = SalaryInfo()
                employee.id = cursor.getString(cursor.getColumnIndex(KEY_SALARY_ID)).toInt()
                employee.name = cursor.getString(cursor.getColumnIndex(KEY_SALARY_NAME))
                employee.salary = cursor.getString(cursor.getColumnIndex(KEY_SALARY_SALARY)).toInt()
                employee.phoneNumber = cursor.getLong(cursor.getColumnIndex(KEY_SALARY_PHONENUMBER))
                list.add(employee)

            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return list
    }


    fun addSalary(salaryInfo: SalaryInfo) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_SALARY_ID, salaryInfo.id)
        values.put(KEY_SALARY_NAME, salaryInfo.name)
        values.put(KEY_SALARY_SALARY, salaryInfo.salary)
        values.put(KEY_SALARY_PHONENUMBER, salaryInfo.phoneNumber)
        db.insert(TABLE_NAME3, null, values)
//        db.close()
    }

    fun readSalaryDataBase(id: Int): SalaryInfo? {
        var db: SQLiteDatabase = writableDatabase
        var cursor: Cursor = db.query(
            TABLE_NAME3, arrayOf(
                KEY_SALARY_ID,
                KEY_SALARY_NAME,
                KEY_SALARY_SALARY,
                KEY_SALARY_PHONENUMBER
            ), KEY_DEBT_ID + "=?", arrayOf(id.toString()),
            null,
            null,
            null
        )

        if (cursor != null) {
            cursor.moveToFirst()
            var Mydb = SalaryInfo()
            Mydb.name = cursor.getString(cursor.getColumnIndex(KEY_SALARY_NAME))
            Mydb.salary = cursor.getInt(cursor.getColumnIndex(KEY_SALARY_SALARY))
            Mydb.phoneNumber = cursor.getLong(cursor.getColumnIndex(KEY_SALARY_PHONENUMBER))


            return Mydb
        }
        return null
    }


    fun updateSalary(salaryInfo: SalaryInfo): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_SALARY_ID, salaryInfo.id)
        values.put(KEY_SALARY_NAME, salaryInfo.name)
        values.put(KEY_SALARY_SALARY, salaryInfo.salary)
        values.put(KEY_SALARY_PHONENUMBER, salaryInfo.phoneNumber)

        return db.update(TABLE_NAME3, values, "$KEY_SALARY_ID=?", arrayOf(salaryInfo.id.toString()))
    }

    fun deleteSalary(salaryInfo: SalaryInfo) {
        val db = this.writableDatabase

        db.delete(TABLE_NAME3, "$KEY_SALARY_ID=?", arrayOf(salaryInfo.id.toString()))
        db.close()
    }

    fun updateSalaryRowData(oldSalaryInfo: SalaryInfo, newSalaryInfo: SalaryInfo) {
        val db: SQLiteDatabase = writableDatabase

        val updateCommand =
            "UPDATE " + TABLE_NAME3 + " SET " +
                    KEY_SALARY_NAME + " = '" + oldSalaryInfo.name + "', " +
                    KEY_SALARY_SALARY + " = '" + oldSalaryInfo.salary + "', " +
                    KEY_SALARY_PHONENUMBER + " = '" + oldSalaryInfo.phoneNumber + "' " +
                    "WHERE $KEY_SALARY_NAME = '" + newSalaryInfo.name + "' AND " +
                    "$KEY_SALARY_SALARY = '" + newSalaryInfo.salary + "' AND " +
                    "$KEY_SALARY_PHONENUMBER = '" + newSalaryInfo.phoneNumber + "'"

        db.execSQL(updateCommand)


    }

    fun getSalaryTableRowCount(): Int {
        val db: SQLiteDatabase = readableDatabase
        val counter = "SELECT * FROM $TABLE_NAME3"
        var cursor: Cursor = db.rawQuery(counter, null)
        return cursor.count

    }


    ////////////////////////////////
    //CRUD functions for employee management table
    ////////////////////////////////

    fun createEmployeeManagementInfo(employeeManagementInfo: EmployeeManagementInfo) {

        var db: SQLiteDatabase = writableDatabase

        var values: ContentValues = ContentValues();
        values.put(KEY_EMPLOYEE_MANAGEMENT_FIRST_NAME, employeeManagementInfo.firstName)
        values.put(KEY_EMPLOYEE_MANAGEMENT_PHONE_NUMBER, employeeManagementInfo.phoneNumber)
        values.put(KEY_EMPLOYEE_MANAGEMENT_DATE_OF_EMPLOYEE, employeeManagementInfo.dateOfEmployee)
        values.put(KEY_EMPLOYEE_MANAGEMENT_SALARY, employeeManagementInfo.salary)
        values.put(KEY_EMPLOYEE_MANAGEMENT_JOB_TITLE, employeeManagementInfo.jobTitle)


        db.insert(TABLE_NAME7, null, values)
        db.close()

        Log.d("DATA INSERTED", "SUCCESS")
    }


    fun readEmployeeManagementInfo(id: Int): EmployeeManagementInfo? {
        var db: SQLiteDatabase = writableDatabase
        var cursor = db.query(
            TABLE_NAME7, arrayOf(
                KEY_EMPLOYEE_MANAGEMENT_ID,
                KEY_EMPLOYEE_MANAGEMENT_FIRST_NAME,
                KEY_EMPLOYEE_MANAGEMENT_PHONE_NUMBER,
                KEY_EMPLOYEE_MANAGEMENT_DATE_OF_EMPLOYEE,
                KEY_EMPLOYEE_MANAGEMENT_SALARY,
                KEY_EMPLOYEE_MANAGEMENT_JOB_TITLE
            ), KEY_EMPLOYEE_MANAGEMENT_ID + "=?", arrayOf(id.toString()), null, null, null
        )

        if (cursor != null) {
            cursor.moveToFirst()

            var employeeManagementInfo = EmployeeManagementInfo()
            employeeManagementInfo.firstName =
                cursor.getString(cursor.getColumnIndex(KEY_EMPLOYEE_MANAGEMENT_FIRST_NAME))
            employeeManagementInfo.phoneNumber =
                cursor.getLong(cursor.getColumnIndex(KEY_EMPLOYEE_MANAGEMENT_PHONE_NUMBER))
            employeeManagementInfo.dateOfEmployee =
                cursor.getString(cursor.getColumnIndex(KEY_EMPLOYEE_MANAGEMENT_DATE_OF_EMPLOYEE))
            employeeManagementInfo.salary =
                cursor.getLong(cursor.getColumnIndex(KEY_EMPLOYEE_MANAGEMENT_SALARY))
            employeeManagementInfo.jobTitle =
                cursor.getString(cursor.getColumnIndex(KEY_EMPLOYEE_MANAGEMENT_JOB_TITLE))


            return employeeManagementInfo

        } else
            return null


    }

    fun updateEmployeeRowData(
        oldEmployeeManagementInfo: EmployeeManagementInfo,
        newEmployeeManagementInfo: EmployeeManagementInfo
    ) {
        val db: SQLiteDatabase = writableDatabase

        val updateCommand =
            "UPDATE " + TABLE_NAME7 + " SET " +
                    KEY_EMPLOYEE_MANAGEMENT_FIRST_NAME + " = '" + oldEmployeeManagementInfo.firstName + "', " +
                    KEY_EMPLOYEE_MANAGEMENT_PHONE_NUMBER + " = '" + oldEmployeeManagementInfo.phoneNumber + "', " +
                    KEY_EMPLOYEE_MANAGEMENT_DATE_OF_EMPLOYEE + " = '" + oldEmployeeManagementInfo.dateOfEmployee + "' " +
                    KEY_EMPLOYEE_MANAGEMENT_SALARY + " = '" + oldEmployeeManagementInfo.salary + "' " +
                    KEY_EMPLOYEE_MANAGEMENT_JOB_TITLE + " = '" + oldEmployeeManagementInfo.jobTitle + "' " +
                    "WHERE $KEY_EMPLOYEE_MANAGEMENT_FIRST_NAME = '" + newEmployeeManagementInfo.firstName + "' AND " +
                    "$KEY_EMPLOYEE_MANAGEMENT_PHONE_NUMBER = '" + newEmployeeManagementInfo.phoneNumber + "' AND " +
                    "$KEY_EMPLOYEE_MANAGEMENT_DATE_OF_EMPLOYEE = '" + newEmployeeManagementInfo.dateOfEmployee + "' AND " +
                    "$KEY_EMPLOYEE_MANAGEMENT_SALARY = '" + newEmployeeManagementInfo.salary + "' AND " +
                    "$KEY_EMPLOYEE_MANAGEMENT_JOB_TITLE = '" + newEmployeeManagementInfo.jobTitle + "'"

        db.execSQL(updateCommand)
    }

    fun getEmployeeManagementTableRowCount(): Int {
        val db: SQLiteDatabase = readableDatabase
        val counter = "SELECT * FROM $TABLE_NAME7"
        var cursor: Cursor = db.rawQuery(counter, null)
        return cursor.count

    }

    fun getAllEmployee(): Cursor {
        val employeeDb =this.readableDatabase
        val id:Int=100
//        val selectQuery="name = " + qname
        val cursor: Cursor = employeeDb.query(TABLE_NAME7, arrayOf( //table7
            KEY_EMPLOYEE_MANAGEMENT_DATE_OF_EMPLOYEE,
            KEY_EMPLOYEE_MANAGEMENT_SALARY

        ), null, null,null,null, KEY_INVOICE_TIMESTAMP + " DESC")
        cursor.moveToFirst()
        employeeDb.close()
        return cursor
    }

    ////////////////////////////////
    //CRUD functions for setting table
    ////////////////////////////////
    fun createSettingInfo(settingInfo: SettingInfo) {

        val db: SQLiteDatabase = writableDatabase

        val values: ContentValues = ContentValues()
        values.put(KET_SETTING_NAME, settingInfo.name)
        values.put(KEY_SETTING_COLOR, settingInfo.color)
        values.put(KEY_SETTING_FONT, settingInfo.font)



        db.insert(TABLE_NAME8, null, values)
        db.close()

        Log.d("DATA INSERTED", "SUCCESS")
    }

    fun readSettingInfo(id: Int): SettingInfo? {
        var db: SQLiteDatabase = writableDatabase
        var cursor = db.query(
            TABLE_NAME8, arrayOf(
                KET_SETTING_NAME,
                KEY_SETTING_COLOR,
                KEY_SETTING_FONT

            ), KEY_SETTING_ID + "=?", arrayOf(id.toString()), null, null, null
        )

        if (cursor != null) {
            cursor.moveToFirst()

            var settingInfo = SettingInfo()
            settingInfo.name = cursor.getString(cursor.getColumnIndex(KET_SETTING_NAME))
            settingInfo.color = cursor.getString(cursor.getColumnIndex(KEY_SETTING_COLOR))
            settingInfo.font = cursor.getString(cursor.getColumnIndex(KEY_SETTING_FONT))


            return settingInfo

        } else
            return null


    }

    fun updateSettingInfo(settingInfo: SettingInfo, id: Int) {
        val db: SQLiteDatabase = writableDatabase

        // val values: ContentValues = ContentValues();
        //    values.put(KEY_COLOR, settingInfo.color)
        //   values.put(KEY_FONT, settingInfo.font)


        db.execSQL(
            "UPDATE " + TABLE_NAME8 + " SET " +
                    "name = " + "'" + settingInfo.name + "'," +
                    "color= " + "'" + settingInfo.color + "'," +
                    "font= '" + settingInfo.font + "'" +
                    " WHERE id = " + "'" + id + "'"
        );
        //  return db.update(TABLE_NAME2, values, KEY_ID + "=?", new String[] { id })
    }

    fun getSettingTableRowCount(): Int {
        val db: SQLiteDatabase = readableDatabase
        val counter = "SELECT * FROM $TABLE_NAME8"
        var cursor: Cursor = db.rawQuery(counter, null)
        return cursor.count

    }

    ////////////////////////////////
    //CRUD functions for setting table
    ////////////////////////////////

    fun createContactInfo(contactInfo: ContactInfo) {
        val db: SQLiteDatabase = writableDatabase
        val values: ContentValues = ContentValues()
        values.put(KEY_CONTACT_FIRST_NAME, contactInfo.firstName)
        values.put(KEY_CONTACT_TITLE, contactInfo.title)
        values.put(KEY_CONTACT_PHONE_NUMBER, contactInfo.phoneNumber)

        db.insert(TABLE_NAME9, null, values)
        db.close()

        Log.d("DATA INSERTED", "SUCCESS")
    }

    fun readContactInfo(id: Int): ContactInfo? {
        val db: SQLiteDatabase = writableDatabase

        var cursor = db.query(
            TABLE_NAME9, arrayOf(
                KEY_CONTACT_ID,
                KEY_CONTACT_FIRST_NAME,
                KEY_CONTACT_TITLE,
                KEY_CONTACT_PHONE_NUMBER
            ), KEY_CONTACT_ID + "=?", arrayOf(id.toString()), null, null, null
        )

        if (cursor != null) {
            cursor.moveToFirst()

            val contactInfo = ContactInfo()
            contactInfo.firstName = cursor.getString(cursor.getColumnIndex(KEY_CONTACT_FIRST_NAME))
            contactInfo.title = cursor.getString(cursor.getColumnIndex(KEY_CONTACT_TITLE))
            contactInfo.phoneNumber =
                cursor.getLong(cursor.getColumnIndex(KEY_CONTACT_PHONE_NUMBER))


            return contactInfo

        } else
            return null

    }

    fun updateContactInfo(contactInfo: ContactInfo, id: Int) {
        val db: SQLiteDatabase = writableDatabase

        // val values: ContentValues = ContentValues();
        //    values.put(KEY_COLOR, settingInfo.color)
        //   values.put(KEY_FONT, settingInfo.font)


        db.execSQL(
            "UPDATE " + TABLE_NAME9 + " SET " +
                    "first name = " + "'" + contactInfo.firstName + "'," +
                    "last name= " + "'" + contactInfo.title + "'," +
                    "phone number= '" + contactInfo.phoneNumber + "'" +
                    " WHERE id = " + "'" + id + "'"
        );
        //  return db.update(TABLE_NAME2, values, KEY_ID + "=?", new String[] { id })
    }

    fun deleteContactInfo(id: Int) {
        val db: SQLiteDatabase = writableDatabase
        val sqlCommand = "DELETE FROM $TABLE_NAME9 WHERE id= '$id'"
        db.execSQL(sqlCommand)
    }

    fun getContactTableRowCount(): Int {
        val db: SQLiteDatabase = readableDatabase
        val counter = "SELECT * FROM $TABLE_NAME9"
        var cursor: Cursor = db.rawQuery(counter, null)
        return cursor.count

    }

    fun userDbCopy(): Cursor {
        val fruitDb: SQLiteDatabase = readableDatabase
        val cursor: Cursor = fruitDb.query(
            TABLE_NAME1, arrayOf(
                KEY_USER_ID,
                KEY_USER_NAME,
                KEY_USER_EMAIL,
                KEY_USER_PASSWORD
            ), null, null, null, null, null
        )
        cursor.moveToFirst()
        fruitDb.close()
        return cursor
    }

    fun debtDbCopy(): Cursor {
        val debtDb: SQLiteDatabase = readableDatabase
        val cursor: Cursor = debtDb.query(
            TABLE_NAME6, arrayOf(
                KEY_DEBT_ID,
                KEY_DEBT_NAME,
                KEY_DEBT_PHONE_NUMBER,
                KEY_DEBT_PHONE_NUMBER
            ), null, null, null, null, null
        )
        cursor.moveToFirst()
        debtDb.close()
        return cursor
    }

    fun costDbCopy(): Cursor {
        val costDb: SQLiteDatabase = readableDatabase
        val cursor: Cursor = costDb.query(
            TABLE_NAME5, arrayOf(
                KEY_COST_ID,
                KEY_COST_REASON,
                KEY_COST_AMOUNT,
                KEY_COST_DATE
            ), null, null, null, null, null
        )
        cursor.moveToFirst()
        costDb.close()
        return cursor
    }

    fun contractDbCopy(): Cursor {
        val contractDb: SQLiteDatabase = readableDatabase
        val cursor: Cursor = contractDb.query(
            TABLE_NAME4, arrayOf(
                KEY_CONTRACT_ID,
                KEY_CONTRACT_NAME,
                KEY_CONTRACT_NATIONAL_CODE,
                KEY_CONTRACT_TRANSACION_VOlLME,
                KEY_CONTRACT_CONTRACT_TITLE,
                KEY_CONTRACT_PRODUCT_INFORMATON,
                KEY_CONTRACT_DATE
            ), null, null, null, null, null
        )
        cursor.moveToFirst()
        contractDb.close()
        return cursor
    }

    fun fruitDbCopy(): Cursor {
        val fruitDb: SQLiteDatabase = readableDatabase
        val cursor: Cursor = fruitDb.query(
            TABLE_NAME2, arrayOf(
                KEY_FRUIT_ID,
                KEY_FRUIT_NAME,
                KEY_FRUIT_PRICE,
                KEY_FRUIT_QUALITY
            ), null, null, null, null, null
        )
        cursor.moveToFirst()
        fruitDb.close()
        return cursor
    }

    fun salaryDbCopy(): Cursor {
        val salaryDb: SQLiteDatabase = readableDatabase
        val cursor: Cursor = salaryDb.query(
            TABLE_NAME3, arrayOf(
                KEY_SALARY_ID,
                KEY_SALARY_NAME,
                KEY_SALARY_SALARY,
                KEY_SALARY_PHONENUMBER
            ), null, null, null, null, null
        )
        cursor.moveToFirst()
        salaryDb.close()
        return cursor
    }

    fun employeeDbCopy(): Cursor {
        val employeeDb: SQLiteDatabase = readableDatabase
        val cursor: Cursor = employeeDb.query(
            TABLE_NAME7, arrayOf(
                KEY_EMPLOYEE_MANAGEMENT_ID,
                KEY_EMPLOYEE_MANAGEMENT_FIRST_NAME,
                KEY_EMPLOYEE_MANAGEMENT_PHONE_NUMBER,
                KEY_EMPLOYEE_MANAGEMENT_DATE_OF_EMPLOYEE,
                KEY_EMPLOYEE_MANAGEMENT_SALARY,
                KEY_EMPLOYEE_MANAGEMENT_JOB_TITLE
            ), null, null, null, null, null
        )
        cursor.moveToFirst()
        employeeDb.close()
        return cursor
    }

    fun contactDbCopy(): Cursor {
        val contactDb: SQLiteDatabase = readableDatabase
        val cursor: Cursor = contactDb.query(
            TABLE_NAME9, arrayOf(
                KEY_CONTACT_ID,
                KEY_CONTACT_FIRST_NAME,
                KEY_CONTACT_TITLE,
                KEY_CONTACT_PHONE_NUMBER
            ), null, null, null, null, null
        )
        cursor.moveToFirst()
        contactDb.close()
        return cursor
    }


    fun addInvoice(invoice: InvoiceInfo) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_INVOICE_ID, invoice.id)
        values.put(KEY_INVOICE_FRUIT_NAME, invoice.name)
        values.put(KEY_INVOICE_FRUIT_AMOUNT, invoice.amount)
        values.put(KEY_INVOICE_SUM, invoice.sum)
        values.put(KEY_INVOICE_NUM, invoice.invoiceNumber)
        db.insert(TABLE_NAME10, null, values)
        db.close()
    }

    fun getInvoiceNumber(): Cursor {
        val fruitDb = this.readableDatabase
        val id: Int = 100
//        val selectQuery="name = " + qname
        val cursor: Cursor = fruitDb.query(
            TABLE_NAME10, arrayOf(
                KEY_INVOICE_NUM
            ), null, null, null, null, null
        )
        cursor.moveToFirst()
        fruitDb.close()
        return cursor
    }

    fun getAllInvoice(): Cursor {
        val fruitDb =this.readableDatabase
        val id:Int=100
//        val selectQuery="name = " + qname
        val cursor: Cursor = fruitDb.query(TABLE_NAME10, arrayOf(
            KEY_INVOICE_ID,
            KEY_INVOICE_FRUIT_NAME,
            KEY_INVOICE_FRUIT_AMOUNT,
            KEY_INVOICE_SUM
        ), null, null,null,null, KEY_INVOICE_TIMESTAMP + " DESC")
        cursor.moveToFirst()
        fruitDb.close()
        return cursor
    }

    fun getInvoice(invoiceNumber: Int): Cursor {
        val fruitDb = this.readableDatabase
        val id: Int = 100
//        val selectQuery="name = " + qname
        val cursor: Cursor = fruitDb.query(
            TABLE_NAME10,
            arrayOf(
                KEY_INVOICE_ID,
                KEY_INVOICE_FRUIT_NAME,
                KEY_INVOICE_FRUIT_AMOUNT,
                KEY_INVOICE_SUM
            ),
            KEY_INVOICE_NUM + "=" + invoiceNumber,
            null,
            null,
            null,
            KEY_INVOICE_TIMESTAMP + " DESC"
        )
        cursor.moveToFirst()
        fruitDb.close()
        return cursor
    }

    fun deleteItemInvoice(id: Int) {
        val db = this.writableDatabase

        db.delete(TABLE_NAME10, KEY_INVOICE_ID + "=" + id, null)
        db.close()
    }


}