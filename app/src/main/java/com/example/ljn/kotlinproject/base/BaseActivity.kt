package com.example.ljn.kotlinproject.base

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.ljn.kotlinproject.App
import com.example.ljn.kotlinproject.injection.component.ActivityComponent
import com.example.ljn.kotlinproject.injection.component.DaggerActivityComponent
import com.example.ljn.kotlinproject.injection.module.ActivityModule
import com.example.ljn.kotlinproject.mvp.IPresenter
import com.example.ljn.kotlinproject.mvp.IView
import javax.inject.Inject

/**
@ description:  再看 再看 再看就把你喝掉  有点小坑 不大
@ author:  ljn
@ time:  2018/1/12
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseActivity<in V : IView, T : IPresenter<V>> : AppCompatActivity(), IView {

    @JvmField
    @Inject
    protected var mPresenter: T? = null
    private lateinit var mContext: Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        mContext = this
        initInject()
        mPresenter?.attachView(this as V)
        App.instance?.addActivity(this)
        initEventAndData()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
        App.instance?.removeActivity(this)
    }

    protected abstract fun initInject()

    protected abstract fun getLayout(): Int

    protected abstract fun initEventAndData()

    protected fun getActivityComponent(): ActivityComponent {
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(ActivityModule(this))
                .build()
    }
}