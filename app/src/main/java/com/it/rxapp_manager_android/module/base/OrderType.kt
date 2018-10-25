package com.it.rxapp_manager_android.module.base

/**
 * Created by deqiangchen on 2018/9/14 14:49
 */
enum class OrderType {
    TAKE_PLANE(0, "接机"), SEND_PLANE(1, "送机"), TUNIU(3, "途牛旅游"), POINT2POINT(4, "点对点"), TAKE_TRAIN(7, "接站"), SEND_TRAIN(8, "送站"), DAY_RENTER(2, "日租");

    private var value: Int
    private var key: String

    private constructor(value: Int, key: String) {
        this.value = value
        this.key = key
    }

    companion object {
        @JvmStatic
        fun getKey(index: Int): String {
            OrderType.values().forEach {
                if (it.value == index) {
                    return it.key
                }
            }
            return ""
        }

        const val TAKE_PLANE_TYPE = 0
        const val SEND_PLANE_TYPE = 1
        const val TUNIU_TYPE = 3
        const val POINT2POINT_TYPE = 4
        const val TAKE_TRAIN_TYPE = 7
        const val SEND_TRAIN_TYPE = 8
        const val DAY_RENTER_TYPE = 2
    }
}