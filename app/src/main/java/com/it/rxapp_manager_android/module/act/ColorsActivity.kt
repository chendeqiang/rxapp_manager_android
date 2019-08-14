package com.it.rxapp_manager_android.module.act

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.Toolbar
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.modle.ListColorEntity
import com.it.rxapp_manager_android.module.adapter.ColorsAdapter
import com.it.rxapp_manager_android.module.base.ComponentHolder
import com.it.rxapp_manager_android.module.base.MyPresenter
import com.it.rxapp_manager_android.utils.Constants
import com.it.rxapp_manager_android.widget.MyProgress
import com.squareup.otto.Subscribe
import javax.inject.Inject

class ColorsActivity:BaseActivity() {

    private lateinit var lvColors: ListView
    private lateinit var srlRefresh: SwipeRefreshLayout
    private lateinit var llEmpty: LinearLayout
    private lateinit var adapter:ColorsAdapter

            @Inject
    lateinit var presenter: MyPresenter
    private lateinit var progress: MyProgress

    companion object {
        @JvmStatic
        fun startColorsActivity(context: Activity) {
            context.startActivityForResult(Intent(context, ColorsActivity::class.java), Constants.REQUEST_SELECT_COLOR_ACTIVITY)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colors)
        ComponentHolder.appComponent!!.inject(this)
        presenter.register(this)
        progress = MyProgress(this)
        initView()
    }

    private fun initView() {
        setToolbar(toolbar = findViewById(R.id.toolbar) as Toolbar)
        (findViewById(R.id.tv_toolbar_title) as TextView).text = "选择颜色"
        lvColors = findViewById(R.id.lv_color) as ListView
        srlRefresh = findViewById(R.id.srl_refresh) as SwipeRefreshLayout
        llEmpty = findViewById(R.id.ll_empty) as LinearLayout

        lvColors.emptyView=llEmpty
        adapter= ColorsAdapter(this, arrayListOf())
        lvColors.adapter=adapter
        lvColors.setOnItemClickListener { _, _, i, _ ->
            setResult(Activity.RESULT_OK, Intent().putExtra(Constants.ACTIVITY_BACK_DATA, adapter.getItem(i) as ListColorEntity.CarcolorsBean))
            finish()
        }

        srlRefresh.setOnRefreshListener {
            adapter.clear()
            progress.show()
            presenter.listColor()
        }
        srlRefresh.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorButtonBg))
    }

    @Subscribe
    fun loadData(any: Any) {
        if (any::class==ListColorEntity::class){
            var data= any as ListColorEntity
            if (data.rspCode.equals("00")){
                adapter.addAll(data.carcolors)
            }
            srlRefresh.isRefreshing = false
        }
        progress.dismiss()
    }

    override fun onStart() {
        super.onStart()
        adapter.clear()
        progress.show()
        presenter.listColor()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unregister(this)
    }
}