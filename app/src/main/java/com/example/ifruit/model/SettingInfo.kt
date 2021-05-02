package com.example.annimation

class SettingInfo() {
    var id: Int? = null
    var name: String? = null
    var color: String? = null
    var font: String? = null

    constructor(
            id: Int,
            name: String,
            color: String,
            font: String

    ) : this() {
        this.id = id
        this.name = name
        this.color = color
        this.font = font


    }
}