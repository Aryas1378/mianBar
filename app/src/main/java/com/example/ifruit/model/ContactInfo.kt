package com.example.annimation

class ContactInfo() {
    var id: Int? = null
    var firstName: String? = null
    var lastName: String? = null
    var phoneNumber: Long? = null

    constructor(id: Int, firstName: String, lastName: String, phoneNumber: Long) : this() {
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
        this.phoneNumber = phoneNumber
    }
}