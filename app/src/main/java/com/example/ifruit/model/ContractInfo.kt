package com.example.annimation

class ContractInfo() {
    var id: Int? = null
    var name: String? = null
    var nationalCode: Long? = null
    var transactionVolume: Long? = null
    var contractTitle: String? = null
    var productInformation: String? = null
    var date: Long? = null

    constructor(
        id: Int,
        name: String,
        nationalCode: Long,
        transactionVolume: Long,
        contractTitle: String,
        productInformation: String,
        date:Long
    ) : this() {
        this.id = id
        this.name = name
        this.nationalCode = nationalCode
        this.transactionVolume = transactionVolume
        this.contractTitle = contractTitle
        this.productInformation = productInformation
        this.date=date
    }
}