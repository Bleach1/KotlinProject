package com.example.ljn.kotlinproject.ui.main

import com.example.ljn.kotlinproject.R
import com.example.ljn.kotlinproject.base.BaseActivity
import com.safframework.log.L
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainContract.View, MainPresenter>(), MainContract.View {
    override fun showError(msg: String) {
        L.i("sssssss4")
    }

    override fun showMsg() {
        L.i("msg")
    }

    override fun printMsg() {
        L.i("msg")
    }

    override fun initInject() {
        getActivityComponent().inject(this)
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun initEventAndData() {
        mPresenter?.attachView(this)
        mPresenter?.getData("")
        text_ljn.text = "Success"
    }

}
