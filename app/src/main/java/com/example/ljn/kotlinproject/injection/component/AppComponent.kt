package com.example.ljn.kotlinproject.injection.component

import com.example.ljn.kotlinproject.App
import com.example.ljn.kotlinproject.helper.RealmHelper
import com.example.ljn.kotlinproject.helper.RetrofitHelper
import com.example.ljn.kotlinproject.injection.module.AppModule
import com.example.ljn.kotlinproject.injection.module.HttpModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (HttpModule::class)])
interface AppComponent {

    fun getContext(): App  // 提供App的Context

    fun retrofitHelper(): RetrofitHelper   //提供http的帮助类

    fun realmHelper(): RealmHelper     //提供数据库帮助类
}
