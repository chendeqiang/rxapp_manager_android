package com.it.rxapp_manager_android.module.base

/**
 * Created by deqiangchen on 2018/9/14 14:53
 */
enum class OrderStatus {

    UNANSWERED(0, "未接单"), ANSWERED(1, "已接单"), ORDERFINISH(2, "已完成"), ORDERCANCEL(3, "已取消"), REASSIGNMENT(4, "已改派");

    private var value: Int
    private var key: String

    private constructor(value: Int, key: String) {
        this.value = value
        this.key = key
    }

    companion object {
        @JvmStatic
        fun getKey(index: Int): String {
            OrderStatus.values().forEach {
                if (it.value == index) {
                    return it.key
                }
            }
            return ""
        }

        const val UNANSWERED_TYPE = 0
        const val ANSWERED_TYPE = 1
        const val ORDERFINISH_TYPE = 2
        const val ORDERCANCEL_TYPE = 3
        const val REASSIGNMENT_TYPE = 4
    }
}