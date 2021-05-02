package com.example.annimation

import java.util.*

class CostInfo() {
    var id: Int? = null
    var reason: String? = null
    var amount: Long? = null
    var date: Long? = null

    constructor(
        id: Int,
        reason: String,
        amount: Long,
        date: Long
    ) : this() {
        this.id = id
        this.reason = reason
        this.amount = amount
        this.date = date
    }
}