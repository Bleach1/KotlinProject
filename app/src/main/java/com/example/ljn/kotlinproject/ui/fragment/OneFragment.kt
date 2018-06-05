package com.example.ljn.kotlinproject.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ljn.kotlinproject.R
/**
 @description:  https://www.jianshu.com/p/ad040aab0e66  升到3.2再说吧
 @author:  ljn
 @time:  2018/5/29 
 */
class OneFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(container?.context).inflate(R.layout.fragment_test_navigation, container, false)
    }

    companion object {
        fun newInstance(bundle: Bundle): Fragment {
            val fragment = OneFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

}