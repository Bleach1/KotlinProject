package com.example.ljn.kotlinproject.ui.main

import com.example.ljn.kotlinproject.R
import com.example.ljn.kotlinproject.base.BaseActivity
import com.example.ljn.kotlinproject.ui.AnkoActivity
import com.safframework.log.L
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.singleTop

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
class MainActivity : BaseActivity<MainContract.View, MainPresenter>(), MainContract.View {

    private var msg: String = "SUCCESS"
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
        mPresenter?.getData("")
        text_ljn.text = msg
        //点击事件
        text_ljn.onClick {
            //传参数+flags
            startActivity(intentFor<AnkoActivity>("id" to 5).singleTop())
        }
    }

}
