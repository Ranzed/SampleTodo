package com.ranzed.sampletodo.presentation


import java.text.DateFormat
import java.util.Date

fun Date.format(): String {
    // todo cache SimpleDateFormat
    val dfLong = DateFormat.getDateInstance(DateFormat.LONG)
    return if (this.time > 0) dfLong.format(this) else ""
}

