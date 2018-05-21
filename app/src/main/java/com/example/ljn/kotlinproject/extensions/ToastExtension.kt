package com.example.ljn.kotlinproject.extensions

import android.content.Context
import android.widget.Toast

fun Context.longToastShow(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}