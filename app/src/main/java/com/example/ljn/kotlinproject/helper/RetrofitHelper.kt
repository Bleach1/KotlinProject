package com.example.ljn.kotlinproject.helper

import com.example.ljn.kotlinproject.base.BaseBean
import com.example.ljn.kotlinproject.base.TestBean
import com.example.ljn.kotlinproject.data.ApiService
import com.example.ljn.kotlinproject.data.CacheProviders

import io.reactivex.Flowable
import io.rx_cache2.DynamicKey
import io.rx_cache2.EvictDynamicKey
import io.rx_cache2.Reply

class RetrofitHelper {

    private var apiService: ApiService? = null
    private var cacheProviders: CacheProviders? = null

    constructor(apiService: ApiService) {
        this.apiService = apiService
    }

    constructor(apiService: ApiService, cacheProviders: CacheProviders) {
        this.apiService = apiService
        this.cacheProviders = cacheProviders
    }


    fun fetchDailyListInfo2(): Flowable<BaseBean<TestBean>>? {
        return apiService?.getDailyBeforeList2("")
    }

    fun cacheVersionInfo(cache_info: String, update: Boolean): Flowable<Reply<BaseBean<TestBean>>>? {
        return fetchDailyListInfo2()?.let { cacheProviders?.getVersionInfo(it, DynamicKey(cache_info), EvictDynamicKey(update)) }
    }
}