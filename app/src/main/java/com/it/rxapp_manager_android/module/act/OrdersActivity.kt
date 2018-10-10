package com.it.rxapp_manager_android.module.act

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.*
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.modle.ListOrderEntity
import com.it.rxapp_manager_android.module.adapter.OrderAdapter
import com.it.rxapp_manager_android.module.base.ComponentHolder
import com.it.rxapp_manager_android.module.base.MyPresenter
import com.it.rxapp_manager_android.utils.Constants
import com.it.rxapp_manager_android.widget.MyProgress
import com.it.rxapp_manager_android.widget.MyTagButton
import com.it.rxapp_manager_android.widget.OrderFooterView
import com.squareup.otto.Subscribe
import javax.inject.Inject

/**
 * Created by deqiangchen on 2018/9/6 10:51
 */
class OrdersActivity : BaseActivity(), View.OnClickListener, AbsListView.OnScrollListener, TabLayout.OnTabSelectedListener {

    private lateinit var lvOrders: ListView
    private lateinit var adapter: OrderAdapter
    private lateinit var tagButtons: Array<MyTagButton>
    private lateinit var orderFooterView: OrderFooterView
    private lateinit var srlRefresh: SwipeRefreshLayout
    private lateinit var llEmpty: LinearLayout
    private lateinit var hsvOrderType: HorizontalScrollView
    private lateinit var tabOrder: TabLayout

    @Inject
    lateinit var presenter: MyPresenter
    lateinit var userNo: String
    private var flowStatus: Int = 1
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

        adapter = OrderAdapter(this, arrayListOf())
        lvOrders.adapter = adapter
        lvOrders.setOnItemClickListener { _, view, i, _ ->
            if (view != orderFooterView) {
                var data = adapter.getItem(i) as ListOrderEntity.OrderEntity
                OrderInfoActivity.startOrderInfoActivity(this, data.orderNo, data.flowNo, userNo)
            }
        }
        tagButtons.forEach {
            it.setOnClickListener(this)
        }
        orderFooterView = OrderFooterView(this)
        lvOrders.addFooterView(orderFooterView)
        lvOrders.setOnScrollListener(this)
        srlRefresh.setOnRefreshListener {
            pageIndex = 0
            adapter.clear()
            progress.show()
//            presenter.listOrder(userNo, flowStatus, orderType, pageIndex, pageCount)
        }
        srlRefresh.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorButtonBg))

        tabOrder.addOnTabSelectedListener(this)
        tagButtons[0].isSelected = true
    }

    override fun onClick(view: View?) {
        tagButtons.forEach {
            it.isSelected = false
            if (it == view) {
                orderType = tagButtons.indexOf(view)
            }
        }
        if (view != null) {
            (view as MyTagButton).isSelected = true
        }

        pageIndex = 0
        adapter.clear()
        progress.show()
//        presenter.listOrder(userNo, flowStatus, orderType, pageIndex, pageCount)
    }

    override fun onStart() {
        super.onStart()
        pageIndex = 0
        adapter.clear()
        progress.show()
//        presenter.listOrder(userNo, flowStatus, orderType, pageIndex, pageCount)
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        flowStatus = tab!!.position + 1
        pageIndex = 0
        adapter.clear()
        onClick(tagButtons[0])
    }

    override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
        if (totalItemCount == 0)
            return
        if (firstVisibleItem + visibleItemCount == totalItemCount) {
            val lastItemView = lvOrders.getChildAt(lvOrders.childCount - 1)
            if (lvOrders.bottom == lastItemView.bottom) {
                if (orderFooterView.refresh) {
                    pageIndex += pageCount
//                    presenter.listOrder(userNo, flowStatus, orderType, pageIndex, pageCount)
                }
            }
        }
    }

    override fun onScrollStateChanged(p0: AbsListView?, p1: Int) {
    }

    @Subscribe
    fun loadData(any: Any) {
        if (any::class == ListOrderEntity::class) {
            var data = any as ListOrderEntity
            if (data.rspCode.equals("00")) {
                adapter.addAll(data.order)
                orderFooterView.refresh = data.order.size >= pageCount
            }
            srlRefresh.isRefreshing = false
        }
        progress.dismiss()

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unregister(this)
    }
}