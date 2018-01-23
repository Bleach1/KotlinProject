package com.example.ljn.kotlinproject.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ljn.kotlinproject.App
import com.example.ljn.kotlinproject.injection.component.DaggerFragmentComponent
import com.example.ljn.kotlinproject.injection.component.FragmentComponent
import com.example.ljn.kotlinproject.injection.module.FragmentModule
import com.example.ljn.kotlinproject.mvp.IPresenter
import com.example.ljn.kotlinproject.mvp.IView
import javax.inject.Inject

@Suppress("UNREACHABLE_CODE", "CAST_NEVER_SUCCEEDS", "UNCHECKED_CAST")
abstract class BaseFragment<in V : IView, T : IPresenter<V>> : Fragment(), IView {
    @JvmField
    @Inject
    protected var mPresenter: T? = null
    private lateinit var mView: View
    private lateinit var mActivity: Activity
    private lateinit var mContext: Context

    @SuppressLint("MissingSuperCall")
    override fun onAttach(context: Context?) {
        mActivity = context as Activity
        mContext = context
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(getLayoutId(), null)
        initInject()
        return mView
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter?.attachView(this as V)
        initEventAndData()
    }

    protected abstract fun initInject()

    protected abstract fun getLayoutId(): Int

    protected abstract fun initEventAndData()

    protected fun getFragmentComponent(): FragmentComponent {
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(FragmentModule(this))
                .build()
    }
}