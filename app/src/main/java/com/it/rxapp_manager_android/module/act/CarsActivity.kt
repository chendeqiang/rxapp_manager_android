package com.it.rxapp_manager_android.module.act

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.module.base.ComponentHolder
import com.it.rxapp_manager_android.module.base.MyPresenter
import com.it.rxapp_manager_android.utils.Constants
import com.it.rxapp_manager_android.widget.MyProgress
import javax.inject.Inject

/**
 * Created by deqiangchen on 2018/9/6 14:20
 */
class CarsActivity : BaseActivity() {

    private lateinit var ivBack: ImageView
    private lateinit var ivAdd: ImageView
    private lateinit var lvCars: ListView
    private lateinit var srlRefresh: SwipeRefreshLayout
    private lateinit var llEmpty: LinearLayout

    @Inject
    lateinit var presenter: MyPresenter
    lateinit var userNo: String
    private var pageIndex: Int = 0
    private var pageCount: Int = 20
    private lateinit var progress: MyProgress

    companion object {
        @JvmStatic
        fun startCarsActivity(context: Context, userNo: String) {
            context.startActivity(Intent(context, CarsActivity::class.java).putExtra(Constants.USER_NO, userNo))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cars)
        ComponentHolder.appComponent!!.inject(this)
        presenter.register(this)
        progress = MyProgress(this)
        userNo = intent.getStringExtra(Constants.USER_NO)
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
        }
    }
}