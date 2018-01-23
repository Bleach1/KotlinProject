package com.example.ljn.kotlinproject.ui.main

import com.example.ljn.kotlinproject.mvp.IPresenter
import com.example.ljn.kotlinproject.mvp.IView

class MainContract {
    interface View : IView {
        fun printMsg()
        fun showError(msg: String)
    }

    interface Presenter : IPresenter<View> {
        fun getData(str: String)
    }
}