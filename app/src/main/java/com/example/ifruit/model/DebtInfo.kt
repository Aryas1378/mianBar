package com.example.annimation

class DebtInfo() {
    var id: Int? = null
    var Name:String?=null
    var LastName:String?=null
    var PhoneNumber:Long?=null
    var DebtAmount:Long?=null


    constructor(Name:String, LastName:String?=null ,PhoneNumber:Long, DebtAmount:Long):this(){
        this.Name = Name
        this.LastName = LastName
        this.PhoneNumber = PhoneNumber
        this.DebtAmount = DebtAmount


    }
}