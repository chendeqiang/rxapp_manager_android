package com.it.rxapp_manager_android.module.base

/**
 * Created by deqiangchen on 2018/9/29 15:26
 */
enum class DriverStatus {

    DISABLE(0, "下线"), ENABLE(1, "上线");

    private var value: Int
    private var key: String

    private constructor(value: Int, key: String) {
        this.value = value
        this.key = key
    }

    companion object {
        @JvmStatic
        fun getKey(index: Int): String {
            DriverStatus.values().forEach {
                if (it.value == index) {
                    return it.key
                }
            }
            return ""
        }

        const val DISABLE_TYPE = 0
        const val ENABLE_TYPE = 1
    }
}