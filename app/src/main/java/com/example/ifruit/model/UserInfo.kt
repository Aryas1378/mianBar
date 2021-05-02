package com.example.annimation

import android.provider.ContactsContract

class UserInfo() {
    var id: Int ?= null
    var name: String?=null
    var email: String?=null
    var password: String?=null


    constructor(id:Int,name:String,email: String,password:String) :this() {
            this.id = id
            this.name = name
            this.email = email
            this.password = password
        }
    }

