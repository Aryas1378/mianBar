package com.example.ifruit.model

class InvoiceInfo {

    var id: Int ?=null
    var name: String?=null
    var amount: Float= 0.0F
    var sum: Int?=null
    var invoiceNumber:Int?=null


    constructor(){}

    constructor(id:Int, name:String, amount: Float, sum:Int,invoiceNumber:Int) {
        this.id = id
        this.name = name
        this.amount = amount
        this.sum = sum
        this.invoiceNumber=invoiceNumber
    }
}