package com.example.ljn.kotlinproject.utils

import com.example.ljn.kotlinproject.App
import java.io.File

object Config {
    val ACTION_INIT = "action_init"
    val DB_NAME = "myRealm.realm"
    val HOST = "http://www.baidu.com"

    private val PATH_DATA = App.instance.cacheDir?.absolutePath + File.separator + "data"
    val PATH_CACHE = PATH_DATA + "/NetCache"

    val TAG = "ljn"
}