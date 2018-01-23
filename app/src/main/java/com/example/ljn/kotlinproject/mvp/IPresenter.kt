package com.example.ljn.kotlinproject.mvp

interface IPresenter<in V : IView> {

    fun attachView(mRootView: V)

    fun detachView()

}