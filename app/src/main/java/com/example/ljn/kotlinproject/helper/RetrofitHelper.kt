package com.example.ljn.kotlinproject.helper

import com.example.ljn.kotlinproject.base.BaseBean
import com.example.ljn.kotlinproject.data.ApiService
import com.example.ljn.kotlinproject.data.CacheProviders
import com.example.ljn.kotlinproject.data.model.TestBean
import io.reactivex.Flowable
import io.rx_cache2.DynamicKey
import io.rx_cache2.EvictDynamicKey
import io.rx_cache2.Reply

class RetrofitHelper {

    var apiService: ApiService? = null
    var cacheProviders: CacheProviders? = null

    constructor(apiService: ApiService) {
        this.apiService = apiService
    }

    constructor(apiService: ApiService, cacheProviders: CacheProviders) {
        this.apiService = apiService
        this.cacheProviders = cacheProviders
    }


    fun fetchDailyListInfo2(): Flowable<BaseBean<TestBean>> {
        return apiService!!.getDailyBeforeList2("")
    }

    fun cache_fetchVersionInfo(cache_info: String, update: Boolean): Flowable<Reply<BaseBean<TestBean>>>? {
        return cacheProviders?.getfetchVersionInfo(fetchDailyListInfo2(), DynamicKey(cache_info), EvictDynamicKey(update))
    }
}