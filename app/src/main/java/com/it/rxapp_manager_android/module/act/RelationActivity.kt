package com.it.rxapp_manager_android.module.act

import android.app.Activity
import android.content.Context
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
import com.it.rxapp_manager_android.modle.CommEntity
import com.it.rxapp_manager_android.modle.ListCarEntity
import com.it.rxapp_manager_android.modle.ListRelationEntity
import com.it.rxapp_manager_android.module.adapter.RelationAdapter
import com.it.rxapp_manager_android.module.base.ComponentHolder
import com.it.rxapp_manager_android.module.base.MyPresenter
import com.it.rxapp_manager_android.utils.Constants
import com.it.rxapp_manager_android.widget.MyProgress
import com.it.rxapp_manager_android.widget.OrderFooterView
import com.it.rxapp_manager_android.widget.ShowToast
import com.squareup.otto.Subscribe
import javax.inject.Inject

/**
 * Created by deqiangchen on 2018/10/8 09:52
 */
class RelationActivity : BaseActivity(), AbsListView.OnScrollListener, RelationAdapter.onItemChangeListener {


    private lateinit var lvRelation: ListView
    private lateinit var srlRefresh: SwipeRefreshLayout
    private lateinit var llEmpty: LinearLayout
    private lateinit var footerView: OrderFooterView
    private lateinit var adapter: RelationAdapter
    private var carInfo: ListCarEntity.CarsBean? = null
    private var relation: ListRelationEntity.RelationsBean? = null

    @Inject
    lateinit var presenter: MyPresenter
    lateinit var userNo: String
    private var pageIndex: Int = 0
    private var pageCount: Int = 20
    private lateinit var progress: MyProgress

    companion object {
        @JvmStatic
        fun startRelationActivity(context: Context, userNo: String) {
            context.startActivity(Intent(context, RelationActivity::class.java).putExtra(Constants.USER_NO, userNo))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relation)
        ComponentHolder.appComponent!!.inject(this)
        presenter.register(this)
        progress = MyProgress(this)
        userNo = intent.getStringExtra(Constants.USER_NO)
        initView()
    }

    private fun initView() {
        setToolbar(toolbar = findViewById(R.id.toolbar) as Toolbar)
        (findViewById(R.id.tv_toolbar_title) as TextView).text = "司机-车辆"

        lvRelation = findViewById(R.id.lv_relation) as ListView
        srlRefresh = findViewById(R.id.srl_refresh) as SwipeRefreshLayout
        llEmpty = findViewById(R.id.ll_empty) as LinearLayout

        lvRelation.emptyView = llEmpty
        adapter = RelationAdapter(this, arrayListOf())
        lvRelation.adapter = adapter
        footerView = OrderFooterView(this)
        lvRelation.addFooterView(footerView)
        adapter.setOnItemChangeClickListener(this)
        lvRelation.setOnScrollListener(this)
        srlRefresh.setOnRefreshListener {
            pageIndex = 0
            adapter.clear()
            progress.show()
            presenter.listRelation(userNo, pageIndex, pageCount)
        }
        srlRefresh.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorButtonBg))
    }

    @Subscribe
    fun loadData(any: Any) {
        if (any::class == ListRelationEntity::class) {
            var data = any as ListRelationEntity
            if (data.rspCode.equals("00")) {
                adapter.addAll(data.relations)
                footerView.refresh = data.relations.size >= pageCount
            } else {
                ShowToast.showCenter(this, data.rspDesc)
            }
            srlRefresh.isRefreshing = false
        } else if (any::class == CommEntity::class) {
            var data = any as CommEntity
            if (data.rspCode.equals("00")) {
                ShowToast.showBottom(this, "编辑成功")
            }
        }
        progress.dismiss()

    }


    override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
        if (totalItemCount == 0)
            return
        if (firstVisibleItem + visibleItemCount == totalItemCount) {
            val lastItemView = lvRelation.getChildAt(lvRelation.childCount - 1)
            if (lvRelation.bottom == lastItemView.bottom) {
                if (footerView.refresh) {
                    pageIndex += pageCount
                    presenter.listDriver(userNo, pageIndex, pageCount)
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
        presenter.listRelation(userNo, pageIndex, pageCount)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unregister(this)
    }

    override fun onItemClick(i: Int) {
        CarsActivity.startCarsActivity(this, userNo, 1)
        relation = lvRelation.getItemAtPosition(i) as ListRelationEntity.RelationsBean

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.REQUEST_SELECT_CAR1_ACTIVITY && resultCode == Activity.RESULT_OK) {
            carInfo = data!!.getSerializableExtra(Constants.ACTIVITY_BACK_DATA) as ListCarEntity.CarsBean
            var carId = carInfo!!.carID
            var driverId = relation!!.driver
            presenter.relation(userNo, carId, driverId)
        }
    }
}