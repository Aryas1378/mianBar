package com.example.annimation

class SalaryInfo {

    var id: Int ?= null
    var name: String?=null
    var salary: Int?=null
    var phoneNumber: Long?=null


    constructor(){}

    constructor(id:Int, name:String, salary: Int, phoneNumber:Long) {
        this.id = id
        this.name = name
        this.salary = salary
        this.phoneNumber = phoneNumber
    }

}