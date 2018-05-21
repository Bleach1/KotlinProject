package com.example.ljn.kotlinproject.utils

import com.example.ljn.kotlinproject.App
import java.io.File

object Config {
    const val ACTION_INIT = "action_init"
    const val DB_NAME = "myRealm.realm"
    const val HOST = "http://www.baidu.com"

    private val PATH_DATA = App.instance.cacheDir?.absolutePath + File.separator + "data"
    val PATH_CACHE = "$PATH_DATA/NetCache"

    const val TAG = "ljn"
}