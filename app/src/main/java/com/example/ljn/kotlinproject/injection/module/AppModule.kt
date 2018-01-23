package com.example.ljn.kotlinproject.injection.module

import com.example.ljn.kotlinproject.App
import com.example.ljn.kotlinproject.data.ApiService
import com.example.ljn.kotlinproject.helper.RealmHelper
import com.example.ljn.kotlinproject.helper.RetrofitHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: App) {

    @Provides
    @Singleton
    internal fun provideApplicationContext(): App {
        return application
    }

    @Provides
    @Singleton
    internal fun provideRetrofitHelper(apiService: ApiService): RetrofitHelper {
        return RetrofitHelper(apiService)
    }

    @Provides
    @Singleton
    internal fun provideRealmHelper(): RealmHelper {
        return RealmHelper(application)
    }
}