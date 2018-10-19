package com.example.ljn.kotlinproject.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.ljn.kotlinproject.R
import com.example.ljn.kotlinproject.base.TestBean


class TestAdapter(layoutResId: Int, data: List<TestBean>) : BaseQuickAdapter<TestBean,
        BaseViewHolder>(layoutResId, data) {
    override fun convert(helper: BaseViewHolder?, item: TestBean?) {
        helper?.setText(R.id.tv_name, item?.name)
        helper?.setText(R.id.tv_age, item?.errorMsg)
        with(item) {
            helper?.setText(R.id.tv_name, this?.name)
            helper?.setText(R.id.tv_age, this?.errorMsg)
        }
    }

}

