package com.it.rxapp_manager_android.module.base

/**
 * Created by deqiangchen on 2018/9/14 14:53
 */
enum class OrderStatus {

    UNANSWERED(0, "未接单"), ANSWERED(1, "已接单"), WAITFORSERVICE(3, "待服务"), ORDERCANCEL(7, "已取消"), ALREADY(4, "已就位"),
    ORDERING(5, "进行中"), ORDERFINISH(6, "已完成");

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
        const val WAITFORSERVICE_TYPE = 3
        const val ORDERCANCEL_TYPE = 7
        const val ALREADY_TYPE = 4
        const val ORDERING_TYPE = 5
        const val ORDERFINISH_TYPE = 6
    }
}