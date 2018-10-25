package com.it.rxapp_manager_android.module.act

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.Toolbar
import android.widget.AbsListView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.modle.ListBasicAuthCityEntity
import com.it.rxapp_manager_android.module.adapter.BasicAuthCityAdapter
import com.it.rxapp_manager_android.module.base.ComponentHolder
import com.it.rxapp_manager_android.module.base.MyPresenter
import com.it.rxapp_manager_android.utils.Constants
import com.it.rxapp_manager_android.widget.MyProgress
import com.it.rxapp_manager_android.widget.OrderFooterView
import com.squareup.otto.Subscribe
import javax.inject.Inject

/**
 * Created by deqiangchen on 2018/10/22 11:04
 */
class ListBasicAuthCityActivity : BaseActivity(), AbsListView.OnScrollListener {

    @Inject
    lateinit var presenter: MyPresenter
    lateinit var userNo: String
    private var pageIndex: Int = 0
    private var pageCount: Int = 20
    private lateinit var progress: MyProgress

    private lateinit var lvProducts: ListView
    private lateinit var srlRefresh: SwipeRefreshLayout
    private lateinit var footerView: OrderFooterView
    private lateinit var llEmpty: LinearLayout
    private lateinit var adapter: BasicAuthCityAdapter

    companion object {
        @JvmStatic
        fun startListBasicAuthCityActivity(context: Activity, userNo: String) {
            context.startActivityForResult(Intent(context, ListBasicAuthCityActivity::class.java).putExtra(Constants.USER_NO, userNo), Constants.REQUEST_SELECT_PRODUCT_ACTIVITY)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listauthcity)
        ComponentHolder.appComponent!!.inject(this)
        presenter.register(this)
        progress = MyProgress(this)
        userNo = intent.getStringExtra(Constants.USER_NO)
        initView()
    }

    private fun initView() {
        setToolbar(toolbar = findViewById(R.id.toolbar) as Toolbar)
        (findViewById(R.id.tv_toolbar_title) as TextView).text = "产品列表"

        lvProducts = findViewById(R.id.lv_products) as ListView
        srlRefresh = findViewById(R.id.srl_refresh) as SwipeRefreshLayout
        llEmpty = findViewById(R.id.ll_empty) as LinearLayout

        lvProducts.emptyView = llEmpty
        adapter = BasicAuthCityAdapter(this, arrayListOf())
        lvProducts.adapter = adapter
        footerView = OrderFooterView(this)
        lvProducts.addFooterView(footerView)
        lvProducts.setOnScrollListener(this)

        lvProducts.setOnItemClickListener { _, _, i, _ ->
            setResult(Activity.RESULT_OK, Intent().putExtra(Constants.ACTIVITY_BACK_DATA, adapter.getItem(i) as ListBasicAuthCityEntity.AuthCitysBean))
            finish()
        }

        srlRefresh.setOnRefreshListener {
            pageIndex = 0
            adapter.clear()
            progress.show()
            presenter.listBasicAuthCity(userNo, pageIndex, pageCount)
        }
        srlRefresh.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorButtonBg))
    }

    override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
        if (totalItemCount == 0)
            return
        if (firstVisibleItem + visibleItemCount == totalItemCount) {
            val lastItemView = lvProducts.getChildAt(lvProducts.childCount - 1)
            if (lvProducts.bottom == lastItemView.bottom) {
                if (footerView.refresh) {
                    pageIndex += pageCount
                    presenter.listBasicAuthCity(userNo, pageIndex, pageCount)
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
        presenter.listBasicAuthCity(userNo, pageIndex, pageCount)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unregister(this)
    }

    @Subscribe
    fun loadData(any: Any) {
        if (any::class == ListBasicAuthCityEntity::class) {
            var data = any as ListBasicAuthCityEntity
            if (data.rspCode.equals("00")) {
                adapter.addAll(data.authCitys)
                footerView.refresh = data.authCitys.size >= pageCount
            }
            srlRefresh.isRefreshing = false
        }
        progress.dismiss()
    }
}