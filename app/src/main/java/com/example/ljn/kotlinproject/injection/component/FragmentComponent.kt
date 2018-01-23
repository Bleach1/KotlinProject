package com.example.ljn.kotlinproject.injection.component

import android.support.v7.app.AppCompatActivity
import com.example.ljn.kotlinproject.injection.module.FragmentModule
import com.example.ljn.kotlinproject.injection.scope.FragmentScope
import dagger.Component

@FragmentScope
@Component(dependencies = [(AppComponent::class)], modules = [(FragmentModule::class)])
interface FragmentComponent {

    fun getActivity(): AppCompatActivity

    // void inject(DailyFragment dailyFragment);

}