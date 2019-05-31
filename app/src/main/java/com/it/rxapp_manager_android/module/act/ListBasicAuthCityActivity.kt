package com.it.rxapp_manager_android.module.act

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.*
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.modle.ListBasicAuthCityEntity
import com.it.rxapp_manager_android.module.adapter.BasicAuthCityAdapter
import com.it.rxapp_manager_android.module.base.ComponentHolder
import com.it.rxapp_manager_android.module.base.MyPresenter
import com.it.rxapp_manager_android.utils.Constants
import com.it.rxapp_manager_android.utils.LogUtils
import com.it.rxapp_manager_android.widget.MyProgress
import com.it.rxapp_manager_android.widget.OrderFooterView
import com.squareup.otto.Subscribe
import javax.inject.Inject

/**
 * Created by deqiangchen on 2018/10/22 11:04
 */
class ListBasicAuthCityActivity : BaseActivity(), AbsListView.OnScrollListener, AdapterView.OnItemSelectedListener {

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
    private lateinit var sp_carType: Spinner
    private lateinit var sp_productType: Spinner
    private lateinit var etStartCity: EditText
    private lateinit var etEndCity: EditText
    private lateinit var tvSearch: TextView

    lateinit var cartpye: String
    lateinit var producttpye: String

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

        sp_carType = findViewById(R.id.spinner_carType) as Spinner
        sp_productType = findViewById(R.id.spinner_productType) as Spinner
        etStartCity = findViewById(R.id.et_startCity) as EditText
        etEndCity = findViewById(R.id.et_endCity) as EditText
        tvSearch = findViewById(R.id.tv_search) as TextView

        lvProducts = findViewById(R.id.lv_products) as ListView
        srlRefresh = findViewById(R.id.srl_refresh) as SwipeRefreshLayout
        llEmpty = findViewById(R.id.ll_empty) as LinearLayout

        lvProducts.emptyView = llEmpty
        adapter = BasicAuthCityAdapter(this, arrayListOf())
        lvProducts.adapter = adapter
        footerView = OrderFooterView(this)
        lvProducts.addFooterView(footerView, "", false)
        lvProducts.setOnScrollListener(this)

        sp_carType.onItemSelectedListener = this
        sp_productType.onItemSelectedListener = this

        tvSearch.setOnClickListener {
            adapter.clear()
            progress.show()
            presenter.listBasicAuthCity(userNo, etStartCity.text.toString(), etEndCity.text.toString(), producttpye, cartpye, pageIndex, pageCount)
        }

        lvProducts.setOnItemClickListener { _, _, i, _ ->

            setResult(Activity.RESULT_OK, Intent().putExtra(Constants.ACTIVITY_BACK_DATA, adapter.getItem(i) as ListBasicAuthCityEntity.AuthCitysBean))
            finish()
        }

        srlRefresh.setOnRefreshListener {
            pageIndex = 0
            adapter.clear()
            progress.show()
            presenter.listBasicAuthCity(userNo, "", "", "", "", pageIndex, pageCount)
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
                    presenter.listBasicAuthCity(userNo, "", "", "", "", pageIndex, pageCount)
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
        presenter.listBasicAuthCity(userNo, "", "", "", "", pageIndex, pageCount)

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

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when (p0!!.id) {
            R.id.spinner_carType -> {
                val car = resources.getStringArray(R.array.carType)
                if (p2 == 0) {
                    cartpye = ""
                } else {
                    cartpye = car[p2]
                }
            }
            R.id.spinner_productType -> {
                if (p2 == 0) {
                    producttpye = ""
                } else {
                    producttpye = p2.toString()
                }
            }
        }
    }

}