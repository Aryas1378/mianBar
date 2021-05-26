package com.example.annimation

class EmployeeManagementInfo() {
    var id: Int? = null
    var firstName: String? = null
    var phoneNumber: Long? = null
    var dateOfEmployee: String? = null
    var salary: Long? = null
    var jobTitle: String? = null

    constructor(
            id: Int,
            firstName: String,
            phoneNumber: Long,
            dateOfEmployee: String,
            salary: Long,
            jobTitle: String
    ) : this() {
        this.id = id
        this.firstName = firstName
        this.phoneNumber = phoneNumber
        this.dateOfEmployee = dateOfEmployee
        this.salary = salary
        this.jobTitle = jobTitle
    }
}