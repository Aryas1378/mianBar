package com.example.annimation

class DebtInfo() {
    var id: Int? = null
    var Name:String?=null
    var PhoneNumber:Long?=null
    var DebtAmount:Long?=null


    constructor(Name:String ,PhoneNumber:Long, DebtAmount:Long):this(){
        this.Name = Name
        this.PhoneNumber = PhoneNumber
        this.DebtAmount = DebtAmount


    }
}