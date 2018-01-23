package com.example.ljn.kotlinproject.service

import android.annotation.SuppressLint
import android.app.IntentService
import android.content.Context
import android.content.Intent
import com.example.ljn.kotlinproject.utils.Config

import com.safframework.log.L


@SuppressLint("Registered")
class InitializeService : IntentService("InitializeService") {

    override fun onHandleIntent(intent: Intent?) {
        if (Config.ACTION_INIT == intent?.action) {
            initApp()
        }
    }

    private fun initApp() {
        L.i("初始化一些第三方库...")

    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, InitializeService::class.java)
            intent.action = Config.ACTION_INIT
            context.startService(intent)
        }
    }
}