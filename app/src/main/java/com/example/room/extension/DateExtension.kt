package com.example.room.extension

import java.text.SimpleDateFormat
import java.util.*

fun Long.convertLongTime():String {
    val date = Date(this)
    val format = SimpleDateFormat("yyy.MM.DD HH:mm")
    return  format.format(date)
}