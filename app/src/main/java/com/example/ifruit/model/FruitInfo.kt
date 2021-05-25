package com.example.annimation

class FruitInfo {

    var id: Int ?= null
    var name: String?=null
    var price: Int?=null
    var qlt: Int?=null


    constructor(){}

    constructor(id:Int,name:String,price: Int,qlt:Int){
        this.id=id
        this.name=name
        this.price=price
        this.qlt=qlt

    }

}