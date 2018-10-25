package com.it.rxapp_manager_android.module.act

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.modle.ListDriverEntity
import com.it.rxapp_manager_android.modle.ListDriversEntity
import com.it.rxapp_manager_android.module.adapter.EnableDriverAdapter
import com.it.rxapp_manager_android.module.base.ComponentHolder
import com.it.rxapp_manager_android.module.base.MyPresenter
import com.it.rxapp_manager_android.utils.Constants
import com.it.rxapp_manager_android.widget.MyProgress
import com.it.rxapp_manager_android.widget.OrderFooterView
import com.squareup.otto.Subscribe
import javax.inject.Inject

/**
 * Created by deqiangchen on 2018/10/16 09:55
 */
class ListEnableDriverActivity : BaseActivity(), AbsListView.OnScrollListener, TextWatcher {

    @Inject
    lateinit var presenter: MyPresenter
    lateinit var userNo: String
    lateinit var orderNo: String
    private var pageIndex: Int = 0
    private var pageCount: Int = 20
    private lateinit var progress: MyProgress

    private lateinit var lvDrivers: ListView
    private lateinit var srlRefresh: SwipeRefreshLayout
    private lateinit var footerView: OrderFooterView
    private lateinit var llEmpty: LinearLayout
    private lateinit var adapter: EnableDriverAdapter

    private lateinit var etDriver: EditText
    private lateinit var ivCancle: ImageView
    private lateinit var tvCancle: TextView


    companion object {
        @JvmStatic
        fun startListEnableDriverActivity(context: Activity, userNo: String, orderNo: String) {
            context.startActivityForResult(Intent(context, ListEnableDriverActivity::class.java).putExtra(Constants.USER_NO, userNo).putExtra(Constants.ORDER_NO, orderNo), Constants.REQUEST_SELECT_DRIVER_ACTIVITY)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enable_drivers)
        ComponentHolder.appComponent!!.inject(this)
        presenter.register(this)
        progress = MyProgress(this)
        userNo = intent.getStringExtra(Constants.USER_NO)
        orderNo = intent.getStringExtra(Constants.ORDER_NO)
        initView()
    }

    private fun initView() {
        setToolbar(toolbar = findViewById(R.id.toolbar) as Toolbar)
        (findViewById(R.id.tv_toolbar_title) as TextView).text = "司机列表"

        etDriver = findViewById(R.id.et_driver) as EditText
        ivCancle = findViewById(R.id.img_cancel) as ImageView
        tvCancle = findViewById(R.id.tv_cancel) as TextView

        lvDrivers = findViewById(R.id.lv_drivers) as ListView
        srlRefresh = findViewById(R.id.srl_refresh) as SwipeRefreshLayout
        llEmpty = findViewById(R.id.ll_empty) as LinearLayout

        tvCancle.setOnClickListener {
            finish()
        }
        ivCancle.setOnClickListener {
            etDriver.text.clear()
        }

        lvDrivers.emptyView = llEmpty
        adapter = EnableDriverAdapter(this, arrayListOf())
        lvDrivers.adapter = adapter
        footerView = OrderFooterView(this)
        lvDrivers.addFooterView(footerView)
        lvDrivers.setOnScrollListener(this)

        lvDrivers.setOnItemClickListener { _, _, i, _ ->
            setResult(Activity.RESULT_OK, Intent().putExtra(Constants.ACTIVITY_BACK_DATA, adapter.getItem(i) as ListDriversEntity.DriversBean))
            finish()
        }

        srlRefresh.setOnRefreshListener {
            pageIndex = 0
            adapter.clear()
            progress.show()
            presenter.getOrderCar(userNo, orderNo, pageIndex, pageCount, "", "")
        }
        srlRefresh.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorButtonBg))

        etDriver.addTextChangedListener(this)
    }

    override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
        if (totalItemCount == 0)
            return
        if (firstVisibleItem + visibleItemCount == totalItemCount) {
            val lastItemView = lvDrivers.getChildAt(lvDrivers.childCount - 1)
            if (lvDrivers.bottom == lastItemView.bottom) {
                if (footerView.refresh) {
                    pageIndex += pageCount
                    presenter.getOrderCar(userNo, orderNo, pageIndex, pageCount, "", "")
                }
            }
        }
    }

    override fun onScrollStateChanged(p0: AbsListView?, p1: Int) {
    }

    override fun onStart() {
        super.onStart()
        pageIndex = 0
        adapter.clear()
        progress.show()
        presenter.getOrderCar(userNo, orderNo, pageIndex, pageCount, "", "")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unregister(this)
    }

    @Subscribe
    fun loadData(any: Any) {
        if (any::class == ListDriversEntity::class) {
            var data = any as ListDriversEntity
            if (data.rspCode.equals("00")) {
                adapter.addAll(data.drivers)
                footerView.refresh = data.drivers.size >= pageCount
            }
            srlRefresh.isRefreshing = false
        }
        progress.dismiss()
    }

    override fun afterTextChanged(p0: Editable?) {
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        adapter.clear()
        progress.show()
        presenter.getOrderCar(userNo, orderNo, pageIndex, pageCount, "", p0.toString())
    }

}