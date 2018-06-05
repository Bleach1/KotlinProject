package com.example.ljn.kotlinproject.ui.main

import com.example.ljn.kotlinproject.base.BasePresenter
import com.example.ljn.kotlinproject.base.TestBean

import com.example.ljn.kotlinproject.helper.RetrofitHelper
import com.example.ljn.kotlinproject.utils.RxUtil
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class MainPresenter @Inject constructor(private var mRetrofitHelper: RetrofitHelper) : BasePresenter<MainContract.View>(), MainContract.Presenter {
    override fun getData(str: String) {

        val disposableSubscriber = mRetrofitHelper.fetchDailyListInfo2()
                ?.compose(RxUtil.handleResult())
                ?.subscribeWith(object : DisposableSubscriber<TestBean>() {
                    override fun onNext(baseBean: TestBean) {
                        mView?.printMsg()
                    }

                    override fun onError(t: Throwable) {
                        mView?.showError(t.message.toString())
                    }

                    override fun onComplete() {

                    }
                })
        disposableSubscriber?.let { addSubscribe(it) }
    }
}