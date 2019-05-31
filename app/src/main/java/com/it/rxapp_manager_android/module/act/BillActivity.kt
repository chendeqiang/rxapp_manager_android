package com.it.rxapp_manager_android.module.act

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.Toolbar
import android.widget.LinearLayout
import android.widget.TextView
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.utils.Constants
import se.emilsjolander.stickylistheaders.StickyListHeadersListView

/**
 * Created by deqiangchen on 2018/9/7 14:18
 */
class BillActivity : BaseActivity() {
    private lateinit var llEmpty: LinearLayout
    private lateinit var srlRefresh: SwipeRefreshLayout
    private lateinit var lvBill: StickyListHeadersListView

    companion object {
        @JvmStatic
        fun startBillActivity(context: Context, userNo: String) {
            context.startActivity(Intent(context, BillActivity::class.java).putExtra(Constants.USER_NO, userNo))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bill)
        initView()
    }

    private fun initView() {
        setToolbar(toolbar = findViewById(R.id.toolbar) as Toolbar)
        (findViewById(R.id.tv_toolbar_title) as TextView).text = "统计账单"

        srlRefresh = findViewById(R.id.srl_refresh) as SwipeRefreshLayout
        lvBill = findViewById(R.id.lv_bill) as StickyListHeadersListView


        llEmpty = findViewById(R.id.ll_empty) as LinearLayout
        lvBill.emptyView = llEmpty
    }
}