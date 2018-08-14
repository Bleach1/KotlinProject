package com.example.ljn.kotlinproject.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.ljn.kotlinproject.R
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
class TestActivity : AppCompatActivity() {

    //知道具体值 用的时候在加载 val
    private val name by lazy { "" }
    //不知道具体值 后面再复制 var
    private lateinit var name2: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    /**
     * launch 方法启动了一个协程，CommonPool 是一个有线程池的上下文，
     * 它可以负责把协程的执行分配到合适的线程上。所以从线程的角度来看，
     * 打印的这两句是在不同的线程上的。
     * 20170206-063015.015 [main] Hello,
     * 20170206-063016.016 [ForkJoinPool.commonPool-worker-1] World!
     */
    fun main(args: Array<String>) {
        launch(CommonPool) {
            // create new coroutine in common thread pool
            delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
            println("World!") // print after delay
        }
        println("Hello,") // main function continues while coroutine is delayed
        Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
    }

    //创建的协程会直接运行在当前线程上
    fun main1(args: Array<String>) = runBlocking<Unit> {
        launch(CommonPool) {
            delay(1000L)
            println("World!")
        }
        println("Hello,")
        delay(2000L)
    }

    // defer 来替换你的 launch 携带返回值
    fun main2(args: Array<String>) = runBlocking<Unit> {
        val job = launch(CommonPool) {
            delay(1000L)
            println("World!")
        }
        println("Hello,")
        //job.join 其实就是要求当前协程等待 job 执行完成之后再继续执行。
        job.join()
        //如果 job 已经执行完毕，那么 job.cancel 的执行时没有意义的。
        // 我们也可以根据 cancel 的返回值来判断是否取消成功。
        val cancel = job.cancel()
        //job.cancel(IllegalAccessException("World!"))
        if (isActive) println("协程存活")
    }


    //终止For循环
    fun main3(args: Array<String>) {
        val list = listOf(1, 3, 4, 5, 6, 7, 9)
        list.forEach {
            if (it > 3) return@forEach
            println(it)
        }
        println("Hello")
    }

//别名
    /**
     * it=1
     *  1
     * it=3
     * 3
     *  it=4
     * it=5
     *  it=6
     *  it=7
     * it=9
     * Hello
     */
    fun main4(args: Array<String>) {
        val list = listOf(1, 3, 4, 5, 6, 7, 9)
        list.forEach block@{
            println("it=$it")
            if (it > 3) return@block
            println(it)
        }
        println("Hello")
    }

    /**
     * 一个函数返回了一个内部函数，该内部函数引用了外部函数的相关参数和变量
     * 该返回的内部函数成为闭包
     * kotlin--lambda表达式
     */
    private fun box(): () -> Unit {
        var a = 10
        return {
            print(a)
            a++
        }
    }


    /**
     * 高阶函数--函数传递函数参数
     * a,b传递的参数
     * block传递的工具
     */

    private fun tools(a: Int, b: Int, block: (Int, Int) -> Int): Int {
        return block(a, b)
    }


    fun add(a: Int, b: Int): Int {
        return a + b
    }

    //::函数的引用
    var sum = tools(10, 10, ::add)
    var sum2 = tools(10, 10) { m, n -> m + n }
}