package com.example.annimation

class SalaryInfo {

    var id: Int = -1
    var name: String?=null
    var salary: Int=-1
    var phoneNumber: Int=-1


    constructor(){}

    constructor(id:Int, name:String, salary: Int, phoneNumber:Int) {
        this.id = id
        this.name = name
        this.salary = salary
        this.phoneNumber = phoneNumber
    }

}