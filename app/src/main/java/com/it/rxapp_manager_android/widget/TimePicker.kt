package com.it.rxapp_manager_android.widget

import android.app.Activity

/**
 * Created by deqiangchen on 2018/11/2 11:17
 */
class TimePicker() {
    private lateinit var timeP: TwoPicker
    private lateinit var context: Activity
    var onSelectDate: OnSelectDate? = null

    interface OnSelectDate {
        fun selectDate(date: String)
    }

    constructor(context: Activity) : this() {
        this.context = context
        initTimePicker()
    }

    private fun initTimePicker() {
        val hour = arrayListOf("00", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23")
        val min = arrayListOf("00", "10", "20", "30", "40", "50")

        timeP = TwoPicker(context, hour, min)
        timeP.setTitleText("时间选择")
        timeP.setOnWheelListener(object : TwoPicker.OnWheelListener {
            override fun onFirstWheeled(index: Int, item: String?) {
                if (index == 0) {
                    timeP.updateDataSecond(min, 0)
                }
            }

            override fun onSecondWheeled(index: Int, item: String?) {
            }
        })
        timeP.setOnPickListener { _, _ ->
            onSelectDate!!.selectDate(timeP.selectedFirstItem + ":" + timeP.selectedSecondItem)
        }
    }

    fun show() {
        timeP.show()
    }

}