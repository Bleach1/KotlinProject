package com.example.ljn.kotlinproject.data

import com.example.ljn.kotlinproject.base.BaseBean
import com.example.ljn.kotlinproject.data.model.TestBean
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("news/before/{date}")
    fun getDailyBeforeList2(@Path("date") data: String): Flowable<BaseBean<TestBean>>
}