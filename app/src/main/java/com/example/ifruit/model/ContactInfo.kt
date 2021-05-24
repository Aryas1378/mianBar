package com.example.annimation

class ContactInfo() {
    var id: Int? = null
    var firstName: String? = null
    var title: String? = null
    var phoneNumber: Long? = null

    constructor(id: Int, firstName: String, title: String, phoneNumber: Long) : this() {
        this.id = id
        this.firstName = firstName
        this.title = title
        this.phoneNumber = phoneNumber
    }
}