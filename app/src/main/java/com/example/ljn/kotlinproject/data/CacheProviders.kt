package com.example.ljn.kotlinproject.data

import com.example.ljn.kotlinproject.base.BaseBean
import com.example.ljn.kotlinproject.base.TestBean

import io.reactivex.Flowable
import io.rx_cache2.DynamicKey
import io.rx_cache2.EvictDynamicKey
import io.rx_cache2.LifeCache
import io.rx_cache2.Reply
import java.util.concurrent.TimeUnit

interface CacheProviders {
    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    fun getVersionInfo(fetchVersionInfo: Flowable<BaseBean<TestBean>>, userName: DynamicKey, evictDynamicKey: EvictDynamicKey): Flowable<Reply<BaseBean<TestBean>>>
}