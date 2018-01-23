package com.example.ljn.kotlinproject.ui.main

import com.example.ljn.kotlinproject.base.BasePresenter
import com.example.ljn.kotlinproject.data.model.TestBean
import com.example.ljn.kotlinproject.helper.RetrofitHelper
import com.example.ljn.kotlinproject.utils.RxUtil
import com.safframework.log.L
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

/*
class MainPresenter @Inject constructor(private var mRetrofitHelper: RetrofitHelper, activity: AppCompatActivity) : BasePresenter<MainContract.View>(activity), MainContract.Presenter {
    override fun getData(str: String) {
        L.i("sssssss")
        val disposableSubscriber = mRetrofitHelper.fetchDailyListInfo2()
                .compose(RxUtil.handleResult())
                .subscribeWith(object : DisposableSubscriber<TestBean>() {
                    override fun onNext(baseBean: TestBean) {
                        mView?.printMsg()
                        L.i("sssssss2")
                    }

                    override fun onError(t: Throwable) {
                        mView?.showError(t.message.toString())
                        L.i("sssssss3")
                    }

                    override fun onComplete() {

                    }
                })
        addSubscribe(disposableSubscriber)
    }
}*/

class MainPresenter @Inject constructor(private var mRetrofitHelper: RetrofitHelper) : BasePresenter<MainContract.View>(), MainContract.Presenter {
    override fun getData(str: String) {

        L.i("sssssss")
        val disposableSubscriber = mRetrofitHelper.fetchDailyListInfo2()
                .compose(RxUtil.handleResult())
                .subscribeWith(object : DisposableSubscriber<TestBean>() {
                    override fun onNext(baseBean: TestBean) {
                        mView?.printMsg()
                        L.i("sssssss2")
                    }

                    override fun onError(t: Throwable) {
                        if (mView == null) {
                            L.i("sssss23")
                        } else {
                            L.i("sdsdsdsdsd")
                        }
                    }

                    override fun onComplete() {

                    }
                })
        addSubscribe(disposableSubscriber)
    }
}