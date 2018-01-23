package com.example.ljn.kotlinproject.injection.module

import android.support.v7.app.AppCompatActivity
import com.example.ljn.kotlinproject.injection.scope.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val mActivity: AppCompatActivity) {

    @Provides
    @ActivityScope
    internal fun provideActivity(): AppCompatActivity {
        return mActivity
    }
}