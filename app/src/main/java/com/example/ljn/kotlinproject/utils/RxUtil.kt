package com.example.ljn.kotlinproject.utils

import com.example.ljn.kotlinproject.base.BaseBean
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Publisher

object RxUtil {

    fun <T> handleResult(): FlowableTransformer<BaseBean<T>, T> {
        return FlowableTransformer { upstream ->
            upstream.flatMap { baseBean ->
                Publisher<T> { subscriber ->
                    if (baseBean.code == 200)
                        subscriber.onNext(baseBean.data)
                    else
                        subscriber.onError(ExceptionUtil(baseBean.msg))

                }
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }
    }
}