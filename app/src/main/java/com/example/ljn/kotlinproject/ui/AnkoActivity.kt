package com.example.ljn.kotlinproject.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.ljn.kotlinproject.R
import com.example.ljn.kotlinproject.adapter.TestAdapter
import com.example.ljn.kotlinproject.base.Data
import com.example.ljn.kotlinproject.base.TestBean


import com.safframework.log.L
import kotlinx.android.synthetic.main.anko_activity.*
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.Ref
import org.jetbrains.anko.coroutines.experimental.asReference
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.longToast
import org.jetbrains.anko.progressDialog
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
class AnkoActivity : AppCompatActivity() {
    companion object {
        const val KEY = "key"
        const val VALUE = "value"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.anko_activity)
        val intExtra = intent.getIntExtra("id", 0)
        L.i(intExtra.toString())
        recycler.layoutManager = LinearLayoutManager(this)
        val data: MutableList<TestBean> = ArrayList(20)
        val bean = TestBean("NullPointerException", "鎏金安娜", 26)
        for (i in 0 until 20) {
            data.add(bean)
        }
        L.i(data.size.toString())
        recycler.adapter = TestAdapter(R.layout.list_item, data)
        btn_jump.onClick { startActivity<ConstraintLayoutDSLActivity>() }
    }

    private fun loadAndShowData() {
        val ref: Ref<AnkoActivity> = this.asReference()
        async(UI) {
            val data = getData()
            // Use ref() instead of this@MyActivity
            ref().showData()
        }
    }

    private fun showData() {}

    private fun getData() {}


    private fun ankoBg() {
        async(UI) {
            val data: Deferred<Data> = bg {
                // Runs in background
                getBgData()
            }
            // This code is executed on the UI thread
            showBgData(data.await())
        }


        /* Toast*/
        fun showToast() {
            toast("")
            longToast("")
        }

        fun showSnackBars(view: View) {
            snackbar(view, "Hi there!")
            snackbar(view, R.string.message)
            longSnackbar(view, "Wow, such duration")
            snackbar(view, "Action, reaction", "Click me!") { /*doStuff()*/ }
/*菊花*/
            val dialog = progressDialog(message = "Please wait a bit…", title = "Fetching data")


        }
    }

    private fun showBgData(await: Data) {}

    private fun getBgData(): Data {
        return Data(5)
    }
}


