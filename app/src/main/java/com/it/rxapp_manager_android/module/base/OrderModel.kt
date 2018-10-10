package com.it.rxapp_manager_android.module.base

/**
 * Created by deqiangchen on 2018/9/27 13:49
 */
enum class OrderModel {

    POINT(1, "指派"), ROB(2, "抢单"), QUOTE(3, "竞价");

    private var value: Int
    private var key: String

    private constructor(value: Int, key: String) {
        this.value = value
        this.key = key
    }

    companion object {
        @JvmStatic
        fun getKey(index: Int): String {
            OrderModel.values().forEach {
                if (it.value == index) {
                    return it.key
                }
            }
            return ""
        }

        const val ROB_TYPE = 2
        const val QUOTE_TYPE = 3
        const val POINT_TYPE = 1
    }
}