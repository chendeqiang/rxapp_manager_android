package com.it.rxapp_manager_android.module

import com.squareup.otto.Bus

/**
 * Created by deqiangchen on 2018/7/31 14:32
 */
class MyPresenter(private val mBs: Bus, private val manger: MyManger) {
    fun register(any: Any) {
        mBs.register(any)
    }

    fun unregister(any: Any) {
        mBs.unregister(any)
    }
}