package com.example.ifruit.model

val DATABASE_VERSION: Int = 10
val DATABASE_NAME: String = "ifruit.db"
val TABLE_NAME1: String = "User"
val TABLE_NAME2: String = "Fruit"
val TABLE_NAME3: String = "Salary"
val TABLE_NAME4: String = "contract"
val TABLE_NAME5: String = "cost"
val TABLE_NAME6: String = "Debt"
val TABLE_NAME7: String = "EmployeeManagement"
val TABLE_NAME8: String = "Setting"
val TABLE_NAME9:String="contact"

//table user columns

val KEY_USER_ID: String = "id"
val KEY_USER_NAME: String = "name"
val KEY_USER_EMAIL: String = "username"
val KEY_USER_PASSWORD: String = "password"


//table fruit columns

val KEY_FRUIT_ID: String = "id"
val KEY_FRUIT_NAME: String = "name"
val KEY_FRUIT_PRICE: String = "price"
val KEY_FRUIT_QUALITY: String = "quality"

//table employee columns

val KEY_SALARY_ID: String = "id"
val KEY_SALARY_NAME: String = "name"
val KEY_SALARY_SALARY: String = "salary"
val KEY_SALARY_PHONENUMBER: String = "phonenumber"

//table cost columns

val KEY_COST_ID: String = "id"
val KEY_COST_REASON: String = "costreason"
val KEY_COST_AMOUNT: String = "costamount"
val KEY_COST_DATE: String = "date"

//table contract columns

val KEY_CONTRACT_ID: String = "id"
val KEY_CONTRACT_NAME: String = "name"
val KEY_CONTRACT_NATIONAL_CODE: String = "national_code"
val KEY_CONTRACT_TRANSACION_VOlLME: String = "Transaction_volume"
val KEY_CONTRACT_CONTRACT_TITLE: String = "Contract_title"
val KEY_CONTRACT_PRODUCT_INFORMATON: String = "Product_Information"

//table debt columns

val KEY_DEBT_ID: String = "id"
val KEY_DEBT_NAME: String = "people_names"
val KEY_DEBT_LAST_NAME: String = "people_last_name"
val KEY_DEBT_PHONE_NUMBER: String = "phone_number"
val KEY_DEBT_AMOUNT: String = "debt_amount"

//table employee management columns

val KEY_EMPLOYEE_MANAGEMENT_ID :String="id"
val KEY_EMPLOYEE_MANAGEMENT_FIRST_NAME :String= "first_name"
val KEY_EMPLOYEE_MANAGEMENT_LAST_NAME:String="last_name"
val KEY_EMPLOYEE_MANAGEMENT_PHONE_NUMBER:String="phone_number"
val KEY_EMPLOYEE_MANAGEMENT_DATE_OF_EMPLOYEE:String="date_of_employee"
val KEY_EMPLOYEE_MANAGEMENT_SALARY:String="salary"
val KEY_EMPLOYEE_MANAGEMENT_JOB_TITLE:String="job_title"

//table setting columns

val KEY_SETTING_ID:String="id"
val KET_SETTING_NAME:String="name"
val KEY_SETTING_COLOR:String="color"
val KEY_SETTING_FONT:String="font"

//table contact columns

val KEY_CONTACT_ID:String="id"
val KEY_CONTACT_FIRST_NAME:String="first_name"
val KEY_CONTACT_LAST_NAME:String="last_name"
val KEY_CONTACT_PHONE_NUMBER:String="phone_number"