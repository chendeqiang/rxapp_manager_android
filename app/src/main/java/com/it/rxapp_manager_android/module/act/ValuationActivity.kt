package com.it.rxapp_manager_android.module.act

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.widget.AbsListView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.modle.ListValuationsEntity
import com.it.rxapp_manager_android.module.adapter.ValuationAdapter
import com.it.rxapp_manager_android.module.base.ComponentHolder
import com.it.rxapp_manager_android.module.base.MyPresenter
import com.it.rxapp_manager_android.utils.Constants
import com.it.rxapp_manager_android.widget.MyProgress
import com.it.rxapp_manager_android.widget.OrderFooterView
import com.it.rxapp_manager_android.widget.ShowToast
import com.squareup.otto.Subscribe
import javax.inject.Inject

/**
 * Created by deqiangchen on 2018/10/19 11:08
 */
class ValuationActivity : BaseActivity(), ValuationAdapter.onItemValuationListener, AbsListView.OnScrollListener, TabLayout.OnTabSelectedListener {


    private lateinit var ivBack: ImageView
    //    private lateinit var ivAdd: ImageView
    private lateinit var lvValuation: ListView
    private lateinit var srlRefresh: SwipeRefreshLayout
    private lateinit var footerView: OrderFooterView
    private lateinit var llEmpty: LinearLayout
    private lateinit var adapter: ValuationAdapter
    private lateinit var tabPriceRule: TabLayout

    @Inject
    lateinit var presenter: MyPresenter
    lateinit var userNo: String
    private var pageIndex: Int = 0
    private var pageCount: Int = 20
    private var productTypeReq:Int=0
    private lateinit var progress: MyProgress

    companion object {
        @JvmStatic
        fun startValuationActivity(context: Context, userNo: String) {
            context.startActivity(Intent(context, ValuationActivity::class.java).putExtra(Constants.USER_NO, userNo))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_valuation)
        ComponentHolder.appComponent!!.inject(this)
        presenter.register(this)
        progress = MyProgress(this)
        userNo = intent.getStringExtra(Constants.USER_NO)
        initView()
    }

    private fun initView() {
        lvValuation = findViewById(R.id.lv_valuation) as ListView
        srlRefresh = findViewById(R.id.srl_refresh) as SwipeRefreshLayout
        llEmpty = findViewById(R.id.ll_empty) as LinearLayout

        lvValuation.emptyView = llEmpty
        tabPriceRule =findViewById(R.id.tab_pricerule) as TabLayout

        ivBack = findViewById(R.id.iv_back_valuation) as ImageView
        ivBack.setOnClickListener {
            finish()
        }
//        ivAdd = findViewById(R.id.iv_add_valuation) as ImageView
//        ivAdd.setOnClickListener {
//            //新增计价
//            CreateValuationActivity.startCreateValuationActivity(this, userNo)
//        }


        adapter = ValuationAdapter(this, arrayListOf())
        lvValuation.adapter = adapter
        footerView = OrderFooterView(this)
        lvValuation.addFooterView(footerView, "", false)
        adapter.setOnItemValuationClickListener(this)
        lvValuation.setOnScrollListener(this)

        lvValuation.setOnItemClickListener { _, view, i, _ ->
            if (view != footerView) {
                var data = lvValuation.getItemAtPosition(i) as ListValuationsEntity.PriceRulesBean
                UpdateValuationActivity.startUpdateValuationActivity(this, userNo, data)
            }
        }

        srlRefresh.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorButtonBg))
        tabPriceRule.addOnTabSelectedListener(this)
        srlRefresh.setOnRefreshListener {
            pageIndex = 0
            adapter.clear()
            progress.show()
            if (productTypeReq==0){
                presenter.listPriceRule(userNo, pageIndex, pageCount,"")
            }else{
                presenter.listPriceRule(userNo, pageIndex, pageCount,productTypeReq.toString())
            }
        }
    }

    override fun onValuationClick(i: Int) {
//        ShowToast.showCenter(this, "比价...")
        var data = lvValuation.getItemAtPosition(i) as ListValuationsEntity.PriceRulesBean
        RelativePriceActivity.startRelativePriceActivity(this,data.authCityId)
    }

    override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
        if (totalItemCount == 0)
            return
        if (firstVisibleItem + visibleItemCount == totalItemCount) {
            val lastItemView = lvValuation.getChildAt(lvValuation.childCount - 1)
            if (lvValuation.bottom == lastItemView.bottom) {
                if (footerView.refresh) {
                    pageIndex += pageCount
//                    presenter.listPriceRule(userNo, pageIndex, pageCount,productTypeReq.toString())
                    if (productTypeReq==0){
                        presenter.listPriceRule(userNo, pageIndex, pageCount,"")
                    }else{
                        presenter.listPriceRule(userNo, pageIndex, pageCount,productTypeReq.toString())
                    }
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
        presenter.listPriceRule(userNo, pageIndex, pageCount,"")
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


    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        productTypeReq= tab!!.position
//        ShowToast.showCenter(this,productTypeReq.toString())
        pageIndex=0
        adapter.clear()
        if (productTypeReq==0){
            presenter.listPriceRule(userNo, pageIndex, pageCount,"")
        }else{
            presenter.listPriceRule(userNo, pageIndex, pageCount,productTypeReq.toString())
        }
    }

}