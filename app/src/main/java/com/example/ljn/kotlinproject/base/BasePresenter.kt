package com.example.ljn.kotlinproject.base

import com.example.ljn.kotlinproject.App
import com.example.ljn.kotlinproject.mvp.IPresenter
import com.example.ljn.kotlinproject.mvp.IView
import io.objectbox.BoxStore
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<T : IView> : IPresenter<T> {

    private var mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    protected var box: BoxStore = App.instance.getBoxStore()
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
        if (!mCompositeDisposable.isDisposed) {
            mCompositeDisposable.clear()
        }

    }

    protected fun addSubscribe(disposable: Disposable) {
        mCompositeDisposable.add(disposable)
    }
}