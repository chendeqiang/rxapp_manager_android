package com.it.rxapp_manager_android.module.base

/**
 * Created by deqiangchen on 2018/9/14 14:49
 */
enum class ProductType {
    TAKE_PLANE_TRAIN(0, "接"), SEND_PLANE_TRAIN(1, "送"), DAY_RENTER(4, "日租");

    private var value: Int
    private var key: String

    private constructor(value: Int, key: String) {
        this.value = value
        this.key = key
    }

    companion object {
        @JvmStatic
        fun getKey(index: Int): String {
            ProductType.values().forEach {
                if (it.value == index) {
                    return it.key
                }
            }
            return ""
        }

        const val TAKE_PLANE_TRAIN_TYPE = 0
        const val SEND_PLANE_TRAIN_TYPE = 1
        const val DAY_RENTER_TYPE = 4
    }
}