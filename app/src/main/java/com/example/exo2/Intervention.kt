package com.example.exo2

import java.util.*

class Intervention(_id:Int, _date: Date, _nom:String, _type:String) {
    val id = _id
    var date = _date
    var nom = _nom
    var type = _type
    override fun toString(): String {
        return super.toString()
    }
}