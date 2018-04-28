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

        functionTest()

    }

    private fun functionTest() {
        //1.run 仅仅用于执行一个代码块
        val x = kotlin.run {
            L.i("Hello world")
            return@run 1
        }
        L.i(x.toString())

//可以用做类型转换
        val y = "A:B:C:D:E:F".run {
            substring(2)
            return@run split(substring(2))
        }

        L.i(y.toString())

        //2. with 传递的是对象直接操作属性 函数等
        val a = A()
        with(a) {
            sayHello()
        }
        //3.apply
        a.apply {
            println("This is a block")
            sayHello()
        }.other()
        //4.also 通with
        //5.let 相当于 RxJava  map 可以转换类型
        "A:B:C:D:E:F".let {
            it.substring(5)
            return@let A()
        }

        a?.let {
            //表示object不为null的条件下，才会去执行let函数体
            it.sayHello()
        }
        //6.takeIf 筛选集合中某个数据是否符合要求
        val arr = listOf(1, 2, 3)
        arr.forEach {
            println("$it % 2 == 0 => ${it.takeIf { it % 2 == 0 }}")
            println("$it % 2 == 0 => ${it.takeUnless { it % 2 == 0 }}")
        }
        //7.takeUnless 如果符合条件返回null，不符合条件返回对象本身
        //8.repeat 将一个动作重复指定的次数
        repeat(3) {
            println("Just repeat, index: $it")
        }
    }


    class A {
        fun sayHello() {}
        fun other() {
            println("Other function...")
        }
    }


}
