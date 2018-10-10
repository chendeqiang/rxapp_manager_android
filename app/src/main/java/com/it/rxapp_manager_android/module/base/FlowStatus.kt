package com.it.rxapp_manager_android.module.base

/**
 * Created by deqiangchen on 2018/9/17 10:36
 */
enum class FlowStatus {
    CANCEL(1, "已取消"), WAITFORSERVICE(2, "待服务"), ALREADY(3, "已就位"), ORDERING(4, "进行中"),
    ORDERFINISH(5, "已结束"), REPUB(6, "改派"), PAY_SUCC(100, "支付成功"), PAY_FAIL(101, "支付失败");

    private var value: Int
    private var key: String

    private constructor(value: Int, key: String) {
        this.value = value
        this.key = key
    }

    companion object {
        @JvmStatic
        fun getKey(index: Int): String {
            FlowStatus.values().forEach {
                if (it.value == index) {
                    return it.key
                }
            }
            return ""
        }

        const val CANCEL_TYPE = 1
        const val WAITFORSERVICE_TYPE = 2
        const val ALREADY_TYPE = 3

        const val ORDERING_TYPE = 4
        const val ORDERFINISH_TYPE = 5
        const val REPUB_TYPE = 6
        const val PAY_SUCC_TYPE = 100
        const val PAY_FAIL_TYPE = 101
    }
}