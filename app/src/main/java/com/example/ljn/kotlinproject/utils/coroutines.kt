package com.example.ljn.kotlinproject.utils

import android.text.Html
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.sync.Mutex
import kotlinx.coroutines.experimental.sync.withLock
import java.nio.channels.Channel

/**
@description:  协程使用--非阻塞异步解决方案
@author:  ljn
@time:  2018/9/13
 */
fun main(args: Array<String>) {
    //不会阻塞
    launch(CommonPool) {
        delay(1000)
        println("Hello World")
    }
    println("Over")
    Thread.sleep(15000)

//会阻塞线程
    runBlocking {
        launch(CommonPool) {
            delay(1000)
            println("Hello World")
        }
        println("Over")
        Thread.sleep(15000)
    }

    val ctx = newSingleThreadContext("CTX")
    launch(ctx) {
        println("")
    }

    runBlocking {
        val d = async(CommonPool) {
            delay(1000)
            3
        }

        val d2 = async(CommonPool) {
            delay(2000)
            5
        }

        println("${d.await()}--${d2.await()}")
    }

    //协程通讯
    runBlocking {
        val channel = kotlinx.coroutines.experimental.channels.Channel<Int>()
        val job1 = launch(CommonPool) {
            for (i in 1..5) {
                delay(1000)
                channel.send(i * i)
            }
        }

        val job2 = launch(CommonPool) {
            repeat(5) {
                println("${channel.receive()}")
            }
        }
        //主线程等待执行完毕
        job1.join()
        job2.join()

    }

    var count = 0
    //协程资源抢占
    //1.单线程模型-- val ctx = newSingleThreadContext("CTX")
    //2.互斥锁
    runBlocking {
        val mutex = Mutex()
        launch(CommonPool) {
            mutex.withLock {
                count++
            }
        }

    }
    //3.Actors
}
