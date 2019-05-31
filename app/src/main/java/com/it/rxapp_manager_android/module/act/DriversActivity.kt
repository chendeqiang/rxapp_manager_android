package com.it.rxapp_manager_android.module.act

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.widget.*
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.dialog.MessageDialog
import com.it.rxapp_manager_android.modle.CommEntity
import com.it.rxapp_manager_android.modle.ListDriverEntity
import com.it.rxapp_manager_android.module.adapter.DriverAdapter
import com.it.rxapp_manager_android.module.base.ComponentHolder
import com.it.rxapp_manager_android.module.base.MyPresenter
import com.it.rxapp_manager_android.utils.Constants
import com.it.rxapp_manager_android.utils.TextUtil
import com.it.rxapp_manager_android.widget.MyProgress
import com.it.rxapp_manager_android.widget.OrderFooterView
import com.it.rxapp_manager_android.widget.ShowToast
import com.squareup.otto.Subscribe
import javax.inject.Inject
import android.view.inputmethod.EditorInfo


/**
 * Created by deqiangchen on 2018/9/6 14:00
 */
class DriversActivity : BaseActivity(), AbsListView.OnScrollListener, DriverAdapter.onItemChangeListener {

    private lateinit var ivBack: ImageView
    private lateinit var ivAdd: ImageView
    private lateinit var lvDrivers: ListView
    private lateinit var srlRefresh: SwipeRefreshLayout
    private lateinit var footerView: OrderFooterView
    private lateinit var llEmpty: LinearLayout
    private lateinit var adapter: DriverAdapter

    private lateinit var etDriver: EditText
    private lateinit var ivCancle: ImageView

    @Inject
    lateinit var presenter: MyPresenter
    lateinit var userNo: String
    private var pageIndex: Int = 0
    private var pageCount: Int = 20
    private lateinit var progress: MyProgress


    companion object {
        @JvmStatic
        fun startDriversActivity(context: Context, userNo: String) {
            context.startActivity(Intent(context, DriversActivity::class.java).putExtra(Constants.USER_NO, userNo))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drivers)
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        ComponentHolder.appComponent!!.inject(this)
        presenter.register(this)
        progress = MyProgress(this)
        userNo = intent.getStringExtra(Constants.USER_NO)
        initView()
    }

    private fun initView() {
        etDriver = findViewById(R.id.et_driver) as EditText
        ivCancle = findViewById(R.id.img_cancel) as ImageView
        lvDrivers = findViewById(R.id.lv_drivers) as ListView
        srlRefresh = findViewById(R.id.srl_refresh) as SwipeRefreshLayout
        llEmpty = findViewById(R.id.ll_empty) as LinearLayout

        lvDrivers.emptyView = llEmpty

        ivBack = findViewById(R.id.iv_back_drivers) as ImageView
        ivBack.setOnClickListener {
            finish()
        }
        ivAdd = findViewById(R.id.iv_add_driver) as ImageView
        ivAdd.setOnClickListener {
            //跳转至添加司机界面
            CreateDriverActivity.startCreateDriverActivity(this, userNo)
        }
        ivCancle.setOnClickListener {
            etDriver.text.clear()
        }

        etDriver.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (etDriver.text.isNotEmpty()) {
                    if (TextUtil.isStartWithNumber(etDriver.text.toString())) {
                        adapter.clear()
                        progress.show()
                        pageIndex=0
                        pageCount=20
                        presenter.listDriver(userNo, pageIndex, pageCount, "", etDriver.text.toString())
                    } else {
                        pageIndex=0
                        pageCount=20
                        adapter.clear()
                        progress.show()
                        presenter.listDriver(userNo, pageIndex, pageCount, etDriver.text.toString(), "")
                    }
                }
                true
            } else {
                false
            }
        }
        adapter = DriverAdapter(this, arrayListOf())
        lvDrivers.adapter = adapter
        footerView = OrderFooterView(this)
        lvDrivers.addFooterView(footerView, "", false)
        adapter.setOnItemChangeClickListener(this)
        lvDrivers.setOnScrollListener(this)

        srlRefresh.setOnRefreshListener {
            pageIndex = 0
            adapter.clear()
            progress.show()
            presenter.listDriver(userNo, pageIndex, pageCount, "", "")
        }
        srlRefresh.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorButtonBg))
    }

    override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
        if (totalItemCount == 0)
            return
        if (firstVisibleItem + visibleItemCount == totalItemCount) {
            val lastItemView = lvDrivers.getChildAt(lvDrivers.childCount - 1)
            if (lvDrivers.bottom == lastItemView.bottom) {
                if (footerView.refresh) {
                    pageIndex += pageCount
                    presenter.listDriver(userNo, pageIndex, pageCount, "", "")
                }
            }
        }
    }

    override fun onScrollStateChanged(p0: AbsListView?, p1: Int) {
    }

    @Subscribe
    fun loadData(any: Any) {
        if (any::class == ListDriverEntity::class) {
            var data = any as ListDriverEntity
            if (data.rspCode.equals("00")) {
                if (data.drivers.isEmpty()) {
                    ShowToast.showCenter(this, "司机不存在！")
                }
                adapter.addAll(data.drivers)
                footerView.refresh = data.drivers.size >= pageCount
            } else if (data.rspCode.equals("101")) {
                ShowToast.showCenter(this, "账号异常,请重新登陆")
            }
            srlRefresh.isRefreshing = false
        } else if (any::class == CommEntity::class) {
            var data = any as CommEntity
            if (data.rspCode.equals("00")) {
                ShowToast.showBottom(this, "成功")
            }
        }
        progress.dismiss()

    }

    override fun onStart() {
        super.onStart()
        pageIndex = 0
        adapter.clear()
        progress.show()
        presenter.listDriver(userNo, pageIndex, pageCount, "", "")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unregister(this)
    }

    override fun onEnableClick(i: Int) {
        var driver = lvDrivers.getItemAtPosition(i) as ListDriverEntity.DriversBean
        val dialog = MessageDialog(this)
        dialog.setMessageText("确定上线该司机吗?")
        dialog.setOnCancelClickListener {
            dialog.dismiss()
        }
        dialog.setOnOkClickListener {
            dialog.dismiss()
            progress.show()
            presenter.enableDriver(driver.no)
        }
        dialog.show()
    }

    override fun onDisableClick(i: Int) {
        var driver = lvDrivers.getItemAtPosition(i) as ListDriverEntity.DriversBean
        val dialog = MessageDialog(this)
        dialog.setMessageText("确定下线该司机吗?")
        dialog.setOnCancelClickListener {
            dialog.dismiss()
        }
        dialog.setOnOkClickListener {
            dialog.dismiss()
            progress.show()
            presenter.disableDriver(driver.no)
        }
        dialog.show()
    }
}