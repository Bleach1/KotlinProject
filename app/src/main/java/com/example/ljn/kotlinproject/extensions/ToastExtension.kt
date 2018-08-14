package com.example.ljn.kotlinproject.extensions

import android.content.Context
import android.widget.Toast
/**
 @description:  扩展函数--子类可以调用父类的扩展函数
 @author:  ljn
 @time:  2018/8/13 
 */
fun Context.longToastShow(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

val Context.pgName: String
    get() = "com.ljn.geb"