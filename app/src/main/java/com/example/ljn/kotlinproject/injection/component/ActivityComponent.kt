package com.example.ljn.kotlinproject.injection.component

import android.support.v7.app.AppCompatActivity
import com.example.ljn.kotlinproject.injection.module.ActivityModule
import com.example.ljn.kotlinproject.injection.scope.ActivityScope
import com.example.ljn.kotlinproject.ui.main.MainActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [(ActivityModule::class)])
interface ActivityComponent {

    val activity: AppCompatActivity

    fun inject(mainActivity: MainActivity)

}