package com.example.annimation

class FruitInfo {

    var id: Int = -1
    var name: String?=null
    var price: Int=-1
    var qlt: Int=-1


    constructor(){}

    constructor(id:Int,name:String,price: Int,qlt:Int){
        this.id=id
        this.name=name
        this.price=price
        this.qlt=qlt

    }

}