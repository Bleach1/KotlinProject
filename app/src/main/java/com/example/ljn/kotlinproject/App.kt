package com.example.ljn.kotlinproject

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.support.multidex.MultiDexApplication
import com.example.ljn.kotlinproject.injection.component.AppComponent
import com.example.ljn.kotlinproject.injection.component.DaggerAppComponent
import com.example.ljn.kotlinproject.injection.module.AppModule
import com.example.ljn.kotlinproject.injection.module.HttpModule
import com.example.ljn.kotlinproject.service.InitializeService
import java.util.*

class App : MultiDexApplication() {

    private var activityList = LinkedList<Activity>()
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        instance = this
        InitializeService.start(this)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
        @SuppressLint("StaticFieldLeak")
        var instance: App? = null

        fun getAppComponent(): AppComponent {
            return DaggerAppComponent.builder()
                    .appModule(AppModule(instance as App))
                    .httpModule(HttpModule())
                    .build()
        }
    }

    fun addActivity(act: Activity) {
        activityList.add(act)
    }

    fun removeActivity(act: Activity) {
        activityList.remove(act)
    }

    fun exitApp() {
        synchronized(activityList) {
            activityList.forEach { act -> act.finish() }
        }
        android.os.Process.killProcess(android.os.Process.myPid())
        System.exit(0)
    }
}
