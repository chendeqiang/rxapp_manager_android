package com.it.rxapp_manager_android.module.act

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.widget.AbsListView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.modle.ListValuationsEntity
import com.it.rxapp_manager_android.module.adapter.RelativePriceAdapter
import com.it.rxapp_manager_android.module.base.ComponentHolder
import com.it.rxapp_manager_android.module.base.MyPresenter
import com.it.rxapp_manager_android.utils.Constants
import com.it.rxapp_manager_android.widget.MyProgress
import com.it.rxapp_manager_android.widget.OrderFooterView
import com.it.rxapp_manager_android.widget.ShowToast
import com.squareup.otto.Subscribe
import javax.inject.Inject

class RelativePriceActivity : BaseActivity(), AbsListView.OnScrollListener {

    private lateinit var lvValuation: ListView
    private lateinit var srlRefresh: SwipeRefreshLayout
    private lateinit var footerView: OrderFooterView
    private lateinit var llEmpty: LinearLayout
    private lateinit var ivBack: ImageView
    private lateinit var adapter: RelativePriceAdapter

    @Inject
    lateinit var presenter: MyPresenter
    private var pageIndex: Int = 0
    private var pageCount: Int = 20
    private lateinit var progress: MyProgress
    lateinit var cmainid: String


    companion object {
        @JvmStatic
        fun startRelativePriceActivity(context: Context, cmainid: String) {
            context.startActivity(Intent(context, RelativePriceActivity::class.java).putExtra(Constants.CMAINID, cmainid))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relative_price)
        ComponentHolder.appComponent!!.inject(this)
        presenter.register(this)
        progress = MyProgress(this)

        cmainid = intent.getStringExtra(Constants.CMAINID)
        initView()
    }

    private fun initView() {
        lvValuation = findViewById(R.id.lv_valuation) as ListView
        srlRefresh = findViewById(R.id.srl_refresh) as SwipeRefreshLayout
        llEmpty = findViewById(R.id.ll_empty) as LinearLayout

        lvValuation.emptyView = llEmpty

        ivBack = findViewById(R.id.iv_back_valuation) as ImageView
        ivBack.setOnClickListener {
            finish()
        }

        adapter = RelativePriceAdapter(this, arrayListOf())
        lvValuation.adapter = adapter
        footerView = OrderFooterView(this)
        lvValuation.addFooterView(footerView, "", false)
        lvValuation.setOnScrollListener(this)
        srlRefresh.setOnRefreshListener {
            pageIndex = 0
            adapter.clear()
            progress.show()
            presenter.listpricerulecompetebycmainid(cmainid, pageIndex, pageCount)
        }
        srlRefresh.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorButtonBg))

    }

    override fun onStart() {
        super.onStart()
        pageIndex = 0
        adapter.clear()
        progress.show()
        presenter.listpricerulecompetebycmainid(cmainid, pageIndex, pageCount)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unregister(this)
    }

    @Subscribe
    fun loadData(any: Any) {
        if (any::class == ListValuationsEntity::class) {
            var data = any as ListValuationsEntity
            if (data.rspCode.equals("00")) {
                adapter.addAll(data.priceRules)
                footerView.refresh = data.priceRules.size >= pageCount
            } else if (data.rspCode.equals("101")) {
                ShowToast.showCenter(this, "账号异常,请重新登陆")
            } else {
                ShowToast.showCenter(this, data.rspDesc)
            }
            srlRefresh.isRefreshing = false
        }
        progress.dismiss()
    }


    override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
        if (totalItemCount == 0)
            return
        if (firstVisibleItem + visibleItemCount == totalItemCount) {
            val lastItemView = lvValuation.getChildAt(lvValuation.childCount - 1)
            if (lvValuation.bottom == lastItemView.bottom) {
                if (footerView.refresh) {
                    pageIndex += pageCount
                    presenter.listpricerulecompetebycmainid(cmainid, pageIndex, pageCount)
                }
            }
        }
    }

    override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
    }

}