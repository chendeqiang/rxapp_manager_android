package com.it.rxapp_manager_android.module.act

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.Toolbar
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.module.base.ComponentHolder
import com.it.rxapp_manager_android.module.base.MyPresenter
import com.it.rxapp_manager_android.utils.Constants
import com.it.rxapp_manager_android.widget.MyProgress
import com.it.rxapp_manager_android.widget.MyTagButton
import com.it.rxapp_manager_android.widget.OrderFooterView
import javax.inject.Inject

/**
 * Created by deqiangchen on 2018/9/6 10:51
 */
class OrdersActivity : BaseActivity() {

    private lateinit var lvOrders: ListView
    //    private lateinit var adapter: OrderAdapter
    private lateinit var tagButtons: Array<MyTagButton>
    private lateinit var orderFooterView: OrderFooterView
    private lateinit var srlRefresh: SwipeRefreshLayout
    private lateinit var llEmpty: LinearLayout
    private lateinit var hsvOrderType: HorizontalScrollView
    private lateinit var tabOrder: TabLayout

    @Inject
    lateinit var presenter: MyPresenter
    lateinit var userNo: String
    private var orderType: Int = 0
    private var pageIndex: Int = 0
    private var pageCount: Int = 20
    private lateinit var progress: MyProgress

    companion object {
        @JvmStatic
        fun startOrdersActivity(context: Context, userNo: String) {
            context.startActivity(Intent(context, OrdersActivity::class.java).putExtra(Constants.USER_NO, userNo))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)
        ComponentHolder.appComponent!!.inject(this)
        presenter.register(this)
        progress = MyProgress(this)
        userNo = intent.getStringExtra(Constants.USER_NO)
        initView()
    }

    private fun initView() {
        lvOrders = findViewById(R.id.lv_orders) as ListView
        srlRefresh = findViewById(R.id.srl_refresh) as SwipeRefreshLayout

        setToolbar(toolbar = findViewById(R.id.toolbar) as Toolbar)
        (findViewById(R.id.tv_toolbar_title) as TextView).text = "车队订单"

        llEmpty = findViewById(R.id.ll_empty) as LinearLayout
        hsvOrderType = findViewById(R.id.hsv_order_type) as HorizontalScrollView

        lvOrders.emptyView = llEmpty

        tabOrder = findViewById(R.id.tab_order) as TabLayout

        tagButtons = arrayOf(findViewById(R.id.btn_all) as MyTagButton,
                findViewById(R.id.btn_take_plane) as MyTagButton,
                findViewById(R.id.btn_send_plane) as MyTagButton,
                findViewById(R.id.btn_take_train) as MyTagButton,
                findViewById(R.id.btn_send_train) as MyTagButton,
                findViewById(R.id.btn_day_renter) as MyTagButton)
    }
}