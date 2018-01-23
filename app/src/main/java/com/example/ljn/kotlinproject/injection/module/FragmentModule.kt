package com.example.ljn.kotlinproject.injection.module

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.ljn.kotlinproject.injection.scope.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: Fragment) {

    @Provides
    @FragmentScope
    internal fun provideActivity(): AppCompatActivity {
        return fragment.activity as AppCompatActivity
    }
}