package com.example.ljn.kotlinproject.base

import com.example.ljn.kotlinproject.mvp.IPresenter
import com.example.ljn.kotlinproject.mvp.IView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<T : IView> : IPresenter<T> {

    private var mCompositeDisposable: CompositeDisposable? = null
    var mView: T? = null
        private set

    override fun attachView(mRootView: T) {
        this.mView = mRootView
    }

    override fun detachView() {
        this.mView = null
        unSubscribe()
    }

    private fun unSubscribe() {
        if (!mCompositeDisposable?.isDisposed!!) {
            mCompositeDisposable!!.clear()
        }

    }

    protected fun addSubscribe(disposable: Disposable) {
        mCompositeDisposable ?: let { mCompositeDisposable = CompositeDisposable() }
        mCompositeDisposable?.add(disposable)
    }
}