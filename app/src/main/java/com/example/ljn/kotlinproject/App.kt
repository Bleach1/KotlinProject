package com.example.ljn.kotlinproject

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.example.ljn.kotlinproject.base.MyObjectBox
import com.example.ljn.kotlinproject.injection.component.AppComponent
import com.example.ljn.kotlinproject.injection.component.DaggerAppComponent
import com.example.ljn.kotlinproject.injection.module.AppModule
import com.example.ljn.kotlinproject.injection.module.HttpModule
import com.example.ljn.kotlinproject.service.InitializeService
import io.objectbox.BoxStore
import java.util.*
import kotlin.properties.Delegates

class App : Application() {

    private val activityList = LinkedList<Activity>()
    private lateinit var build: BoxStore
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        instance = this
        InitializeService.start(this)
        build = MyObjectBox.builder().androidContext(this).build()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    fun getBoxStore(): BoxStore {
        return build
    }

    companion object {
        var context: Context by Delegates.notNull()
            private set
        var instance: App by Delegates.notNull()
            private set

        fun getAppComponent(): AppComponent {
            return DaggerAppComponent.builder()
                    .appModule(AppModule(instance))
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
            activityList.filter { activity -> !activity.isFinishing }
                    .forEach { act -> act.finish() }
        }
        android.os.Process.killProcess(android.os.Process.myPid())
        System.exit(0)
    }
}
