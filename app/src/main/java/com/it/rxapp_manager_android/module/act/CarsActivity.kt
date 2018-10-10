package com.it.rxapp_manager_android.module.act

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.widget.AbsListView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.modle.CommEntity
import com.it.rxapp_manager_android.modle.ListCarEntity
import com.it.rxapp_manager_android.module.adapter.CarAdapter
import com.it.rxapp_manager_android.module.base.ComponentHolder
import com.it.rxapp_manager_android.module.base.MyPresenter
import com.it.rxapp_manager_android.utils.Constants
import com.it.rxapp_manager_android.widget.MyProgress
import com.it.rxapp_manager_android.widget.OrderFooterView
import com.it.rxapp_manager_android.widget.ShowToast
import com.squareup.otto.Subscribe
import javax.inject.Inject

/**
 * Created by deqiangchen on 2018/9/6 14:20
 */
class CarsActivity : BaseActivity(), AbsListView.OnScrollListener {

    private lateinit var ivBack: ImageView
    private lateinit var ivAdd: ImageView
    private lateinit var lvCars: ListView
    private lateinit var srlRefresh: SwipeRefreshLayout
    private lateinit var llEmpty: LinearLayout
    private lateinit var footerView: OrderFooterView
    private lateinit var adapter: CarAdapter

    @Inject
    lateinit var presenter: MyPresenter
    lateinit var userNo: String
    private var tag: Int = 0
    private var pageIndex: Int = 0
    private var pageCount: Int = 20
    private lateinit var progress: MyProgress

    companion object {
        @JvmStatic
        fun startCarsActivity(context: Activity, userNo: String, tag: Int) {
            context.startActivityForResult(Intent(context, CarsActivity::class.java).putExtra(Constants.USER_NO, userNo).putExtra(Constants.TAG, tag), Constants.REQUEST_SELECT_CAR1_ACTIVITY)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cars)
        ComponentHolder.appComponent!!.inject(this)
        presenter.register(this)
        progress = MyProgress(this)
        userNo = intent.getStringExtra(Constants.USER_NO)
        tag = intent.getIntExtra(Constants.TAG, 0)
        initView()
    }

    private fun initView() {
        lvCars = findViewById(R.id.lv_cars) as ListView
        srlRefresh = findViewById(R.id.srl_refresh) as SwipeRefreshLayout
        llEmpty = findViewById(R.id.ll_empty) as LinearLayout

        lvCars.emptyView = llEmpty
        ivBack = findViewById(R.id.iv_back_cars) as ImageView
        ivBack.setOnClickListener {
            finish()
        }
        ivAdd = findViewById(R.id.iv_add_car) as ImageView
        ivAdd.setOnClickListener {
            //跳转至添加车辆界面
            AddCarActivity.startAddCarActivity(this, userNo)
        }
        adapter = CarAdapter(this, arrayListOf())
        lvCars.adapter = adapter
        footerView = OrderFooterView(this)
        lvCars.addFooterView(footerView)
        lvCars.setOnScrollListener(this)
        if (tag == 1) {
            lvCars.setOnItemClickListener { _, _, i, _ ->

                setResult(Activity.RESULT_OK, Intent().putExtra(Constants.ACTIVITY_BACK_DATA, adapter.getItem(i) as ListCarEntity.CarsBean))
                finish()

            }
        }


        srlRefresh.setOnRefreshListener {
            pageIndex = 0
            adapter.clear()
            progress.show()
            presenter.listCar(userNo, pageIndex, pageCount)
        }
        srlRefresh.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorButtonBg))
    }

    override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
        if (totalItemCount == 0)
            return
        if (firstVisibleItem + visibleItemCount == totalItemCount) {
            val lastItemView = lvCars.getChildAt(lvCars.childCount - 1)
            if (lvCars.bottom == lastItemView.bottom) {
                if (footerView.refresh) {
                    pageIndex += pageCount
                    presenter.listDriver(userNo, pageIndex, pageCount)
                }
            }
        }
    }

    override fun onScrollStateChanged(p0: AbsListView?, p1: Int) {
    }

    @Subscribe
    fun loadData(any: Any) {
        if (any::class == ListCarEntity::class) {
            var data = any as ListCarEntity
            if (data.rspCode.equals("00")) {
                adapter.addAll(data.cars)
                footerView.refresh = data.cars.size >= pageCount
            }
            srlRefresh.isRefreshing = false
        }
        progress.dismiss()

    }

    override fun onStart() {
        super.onStart()
        pageIndex = 0
        adapter.clear()
        progress.show()
        presenter.listCar(userNo, pageIndex, pageCount)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unregister(this)
    }
}