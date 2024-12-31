package com.refanzzzz.znews.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun String.convertDateFormat(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val date = LocalDateTime.parse(this, formatter)

    return "${date.dayOfMonth}.${date.month.value}.${date.year}"
}

fun String.toStandardString(): String = this.replaceAfter("[", "").replace("[", "")
