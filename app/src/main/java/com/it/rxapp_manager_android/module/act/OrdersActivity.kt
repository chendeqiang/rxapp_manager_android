package com.it.rxapp_manager_android.module.act

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.widget.*
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.modle.ListOrderEntity
import com.it.rxapp_manager_android.module.adapter.OrderAdapter
import com.it.rxapp_manager_android.module.base.ComponentHolder
import com.it.rxapp_manager_android.module.base.MyPresenter
import com.it.rxapp_manager_android.utils.Constants
import com.it.rxapp_manager_android.utils.LogUtils
import com.it.rxapp_manager_android.widget.MyProgress
import com.it.rxapp_manager_android.widget.MyTagButton
import com.it.rxapp_manager_android.widget.OrderFooterView
import com.it.rxapp_manager_android.widget.ShowToast
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
    private lateinit var ivBack: ImageView
    private lateinit var ivSort: ImageView
    private lateinit var hsvOrderType: HorizontalScrollView
    private lateinit var tabOrder: TabLayout
    private var sortType: String = "0"


    @Inject
    lateinit var presenter: MyPresenter
    lateinit var userNo: String
    private var orderStatus: Int = 0
    private var orderType: Int = 10
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

        ivBack = findViewById(R.id.iv_back_orders) as ImageView
        ivSort = findViewById(R.id.iv_sort_orders) as ImageView
        lvOrders = findViewById(R.id.lv_orders) as ListView
        srlRefresh = findViewById(R.id.srl_refresh) as SwipeRefreshLayout

        llEmpty = findViewById(R.id.ll_empty) as LinearLayout
        hsvOrderType = findViewById(R.id.hsv_order_type) as HorizontalScrollView
        ivBack.setOnClickListener {
            finish()
        }
        lvOrders.emptyView = llEmpty

        tabOrder = findViewById(R.id.tab_order) as TabLayout

        tagButtons = arrayOf(
                findViewById(R.id.btn_all) as MyTagButton,
                findViewById(R.id.btn_take_plane) as MyTagButton,
                findViewById(R.id.btn_send_plane) as MyTagButton,
                findViewById(R.id.btn_day_renter) as MyTagButton,
                findViewById(R.id.btn_take_train) as MyTagButton,
                findViewById(R.id.btn_send_train) as MyTagButton)

        adapter = OrderAdapter(this, arrayListOf())
        lvOrders.adapter = adapter

        lvOrders.setOnItemClickListener { _, view, i, _ ->
            if (view != orderFooterView) {
                var data = adapter.getItem(i) as ListOrderEntity.OrdersBean
                OrderInfoActivity.startOrderInfoActivity(this, data, userNo)
            }
        }
        tagButtons.forEach {
            it.setOnClickListener(this)
        }
        orderFooterView = OrderFooterView(this)
        lvOrders.addFooterView(orderFooterView, "", false)
        lvOrders.setOnScrollListener(this)
        srlRefresh.setOnRefreshListener {
            pageIndex = 0
            adapter.clear()
            progress.show()
            presenter.listOrder(userNo, orderStatus.toString(), orderType.toString(), pageIndex, pageCount, sortType)
        }
        srlRefresh.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorButtonBg))

        tabOrder.addOnTabSelectedListener(this)
        tagButtons[0].isSelected = true


        ivSort.setOnClickListener {
            if (sortType == "0") {
                sortType = "1"
                pageIndex = 0
                adapter.clear()
                progress.show()
                presenter.listOrder(userNo, orderStatus.toString(), orderType.toString(), pageIndex, pageCount, sortType)
            } else {
                sortType = "0"
                pageIndex = 0
                adapter.clear()
                progress.show()
                presenter.listOrder(userNo, orderStatus.toString(), orderType.toString(), pageIndex, pageCount, sortType)
            }
//            LogUtils.d("--------", sortType)
        }
    }

    override fun onClick(view: View?) {
        tagButtons.forEach {
            it.isSelected = false
            if (it == view) {
                if (tagButtons.indexOf(view) == 0) {
                    orderType = 10
                } else if (tagButtons.indexOf(view) > 0 && tagButtons.indexOf(view) < 4) {
                    orderType = tagButtons.indexOf(view) - 1
                } else if (tagButtons.indexOf(view) == 4) {
                    orderType = 7
                } else if (tagButtons.indexOf(view) == 5) {
                    orderType = 8
                }
            }
        }
        if (view != null) {
            (view as MyTagButton).isSelected = true
        }

        pageIndex = 0
        adapter.clear()
        progress.show()
        presenter.listOrder(userNo, orderStatus.toString(), orderType.toString(), pageIndex, pageCount, sortType)
    }

    override fun onStart() {
        super.onStart()
        pageIndex = 0
        adapter.clear()
        progress.show()
        presenter.listOrder(userNo, orderStatus.toString(), orderType.toString(), pageIndex, pageCount, sortType)
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        orderStatus = tab!!.position
        pageIndex = 0
        adapter.clear()
        onClick(tagButtons[0])
    }

    override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
        if (totalItemCount == 0)
            return
        if (firstVisibleItem + visibleItemCount == totalItemCount) {//滚动到底部
            val lastItemView = lvOrders.getChildAt(lvOrders.childCount - 1)
            if (lvOrders.bottom == lastItemView.bottom) {
                if (orderFooterView.refresh) {
                    pageIndex += pageCount
                    presenter.listOrder(userNo, orderStatus.toString(), orderType.toString(), pageIndex, pageCount, sortType)
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
                adapter.addAll(data.orders)
                orderFooterView.refresh = data.orders.size >= pageCount
            } else if (data.rspCode.equals("101")) {
                ShowToast.showCenter(this, "账号异常,请重新登陆")
            } else {
                ShowToast.showCenter(this, data.rspDesc)
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