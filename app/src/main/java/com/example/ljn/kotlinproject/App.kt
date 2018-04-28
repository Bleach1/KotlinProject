package com.example.ljn.kotlinproject

import android.app.Activity
import android.content.Context
import android.support.multidex.MultiDexApplication
import com.example.ljn.kotlinproject.data.model.MyObjectBox
import com.example.ljn.kotlinproject.injection.component.AppComponent
import com.example.ljn.kotlinproject.injection.component.DaggerAppComponent
import com.example.ljn.kotlinproject.injection.module.AppModule
import com.example.ljn.kotlinproject.injection.module.HttpModule
import com.example.ljn.kotlinproject.service.InitializeService
import io.objectbox.BoxStore
import java.util.*
import kotlin.properties.Delegates

class App : MultiDexApplication() {

    private val activityList = LinkedList<Activity>()
    private lateinit var build: BoxStore
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        instance = this
        InitializeService.start(this)
        build = MyObjectBox.builder().androidContext(this).build()
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
            activityList.forEach { act -> act.finish() }
        }
        android.os.Process.killProcess(android.os.Process.myPid())
        System.exit(0)
    }
}
