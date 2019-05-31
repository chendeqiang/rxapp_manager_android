package com.it.rxapp_manager_android.module.act

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.dialog.RemarkDialog
import com.it.rxapp_manager_android.dialog.SetPriceDialog
import com.it.rxapp_manager_android.modle.CommEntity
import com.it.rxapp_manager_android.modle.ListDriversEntity
import com.it.rxapp_manager_android.modle.ListOrderEntity
import com.it.rxapp_manager_android.module.base.*
import com.it.rxapp_manager_android.utils.Constants
import com.it.rxapp_manager_android.utils.TextUtil
import com.it.rxapp_manager_android.widget.MyProgress
import com.it.rxapp_manager_android.widget.ShowToast
import com.squareup.otto.Subscribe
import javax.inject.Inject

/**
 * Created by deqiangchen on 2018/9/13 16:28
 */
class OrderInfoActivity : BaseActivity() {

    private lateinit var tvOrderNo: TextView
    private lateinit var tvOrderType: TextView
    private lateinit var tvOrderFee: TextView
    private lateinit var tvOrderPrice: TextView
    private lateinit var tvOrderStatus: TextView
    private lateinit var tvAddress: TextView
    private lateinit var llAddress: LinearLayout
    private lateinit var llFlight: LinearLayout
    private lateinit var llStAds: LinearLayout
    private lateinit var llEnAds: LinearLayout
    private lateinit var llTime: LinearLayout
    private lateinit var llDriverInfo: LinearLayout
    private lateinit var llFinish: LinearLayout
    private lateinit var llOrderOperate: LinearLayout
    private lateinit var llOrderOperate2: LinearLayout
    private lateinit var tvFlight: TextView
    private lateinit var tvPhone: TextView
    private lateinit var tvRemark: TextView
    private lateinit var tvDriverName: TextView
    private lateinit var tvDriverCar: TextView
    private lateinit var tvFlightHint: TextView
    private lateinit var tvTime: TextView
    private lateinit var tvStAds: TextView
    private lateinit var tvEnAds: TextView
    private lateinit var imgFlight: ImageView


    @Inject
    lateinit var presenter: MyPresenter
    private lateinit var progress: MyProgress
    lateinit var userNo: String
    private lateinit var data: ListOrderEntity.OrdersBean
//    private var driver: ListDriversEntity.DriversBean? = null

    companion object {
        @JvmStatic
        fun startOrderInfoActivity(context: Context, data: ListOrderEntity.OrdersBean, userNo: String) {
            context.startActivity(Intent(context, OrderInfoActivity::class.java).
                    putExtra(Constants.ORDER_INFO, data).
                    putExtra(Constants.USER_NO, userNo))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_info)
        ComponentHolder.appComponent!!.inject(this)
        presenter.register(this)
        progress = MyProgress(this)
        userNo = intent.getStringExtra(Constants.USER_NO)
        data = intent.getSerializableExtra(Constants.ORDER_INFO) as ListOrderEntity.OrdersBean

        initView()
        initData(data)
    }

    private fun initView() {
        setToolbar(toolbar = findViewById(R.id.toolbar) as Toolbar)
        (findViewById(R.id.tv_toolbar_title) as TextView).text = "订单详情"

        tvOrderNo = findViewById(R.id.tv_order_no) as TextView
        tvOrderType = findViewById(R.id.tv_order_type) as TextView
        tvOrderFee = findViewById(R.id.tv_order_fee) as TextView
        tvOrderPrice = findViewById(R.id.tv_order_price) as TextView
        tvOrderStatus = findViewById(R.id.tv_order_status) as TextView
        llAddress = findViewById(R.id.ll_address) as LinearLayout
        tvAddress = findViewById(R.id.tv_address) as TextView
        llFlight = findViewById(R.id.ll_flight) as LinearLayout
        llStAds = findViewById(R.id.ll_start_address) as LinearLayout
        llDriverInfo = findViewById(R.id.ll_driver_info) as LinearLayout
        llEnAds = findViewById(R.id.ll_end_address) as LinearLayout
        llTime = findViewById(R.id.ll_time) as LinearLayout
        llFinish = findViewById(R.id.ll_finish) as LinearLayout
        tvFlight = findViewById(R.id.tv_flight) as TextView
        tvPhone = findViewById(R.id.tv_passenger_phone) as TextView
        tvRemark = findViewById(R.id.tv_remark) as TextView
        tvDriverName = findViewById(R.id.tv_driver_name) as TextView
        tvDriverCar = findViewById(R.id.tv_driver_car) as TextView
        imgFlight = findViewById(R.id.img_flight) as ImageView
        tvFlightHint = findViewById(R.id.tv_flight_hint) as TextView
        tvTime = findViewById(R.id.tv_time) as TextView
        tvStAds = findViewById(R.id.tv_start_address) as TextView
        tvEnAds = findViewById(R.id.tv_end_address) as TextView
        llOrderOperate = findViewById(R.id.ll_order_operate) as LinearLayout
        llOrderOperate2 = findViewById(R.id.ll_order_operate2) as LinearLayout


        findViewById(R.id.btn_copy).setOnClickListener {
            val clipboardManager = (this.getSystemService(android.content.Context.CLIPBOARD_SERVICE) as ClipboardManager)
            clipboardManager.primaryClip = ClipData.newPlainText(null, tvOrderNo.text)
            ShowToast.showCenter(this, "复制成功")
        }

    }

    private fun initListener() {

        findViewById(R.id.btn_order_arrange).setOnClickListener {
            ListEnableDriverActivity.startListEnableDriverActivity(this, userNo, data.orderNo)
        }
        findViewById(R.id.btn_return2pool).setOnClickListener {
            progress.show()
            presenter.returnToOrderPool(userNo, data.orderNo)
        }
        findViewById(R.id.btn_route).setOnClickListener {
            SearchRouteActivity.startSearchRouteActivity(this, data)
        }
        findViewById(R.id.btn_add_remark).setOnClickListener {

            writeRemark()
        }
    }

    private fun writeRemark() {
        val dialog = RemarkDialog(this)
        dialog.setOnCancelClickListener { dialog.dismiss() }
        dialog.setOnOkClickListener {
            dialog.dismiss()
            //请求网络，提交数据
            if (dialog.message().isNullOrEmpty() || data.driver == null) {
                ShowToast.showBottom(this, "信息不完整")
            } else {
                progress.show()
                presenter.remarkPush(data.orderNo, data.driverId, dialog.message())
            }
        }
        dialog.show()
    }

    private fun initData(orderInfo: ListOrderEntity.OrdersBean) {
        if (orderInfo.orderType.toInt() == OrderType.SEND_PLANE_TYPE || orderInfo.orderType.toInt() == OrderType.TAKE_PLANE_TYPE) {
            tvFlightHint.text = "航班:"
            imgFlight.setImageResource(R.drawable.ic_plane)
            llEnAds.visibility = View.VISIBLE
            llStAds.visibility = View.VISIBLE
            llFlight.visibility = View.VISIBLE
            llAddress.visibility = View.GONE
            tvTime.text = orderInfo.useTime
        } else if (orderInfo.orderType.toInt() == OrderType.SEND_TRAIN_TYPE || orderInfo.orderType.toInt() == OrderType.TAKE_TRAIN_TYPE) {
            tvFlightHint.text = "车次:"
            imgFlight.setImageResource(R.drawable.ic_train)
            llEnAds.visibility = View.VISIBLE
            llStAds.visibility = View.VISIBLE
            llFlight.visibility = View.VISIBLE
            llAddress.visibility = View.GONE
            tvTime.text = orderInfo.useTime
        } else {
            llEnAds.visibility = View.GONE
            llStAds.visibility = View.GONE
            llFlight.visibility = View.GONE
            llAddress.visibility = View.VISIBLE
            tvTime.text = orderInfo.useTime
        }
        tvOrderType.text = OrderType.getKey(orderInfo.orderType.toInt()) + "(" + orderInfo.carTypeName + ")"
        tvOrderStatus.text = OrderStatus.getKey(orderInfo.orderStatus.toInt())
        tvOrderNo.text = orderInfo.orderNo
        if (orderInfo.tripNo.isNullOrEmpty()) {
            tvFlight.text = "暂无航班/车次信息... "
        } else {
            tvFlight.text = orderInfo.tripNo.toUpperCase()
        }

        if (orderInfo.payAmount.isNullOrEmpty()) {
//            tvOrderPrice.text = "结算价: " + orderInfo.cpayprice.substring(0, orderInfo.cpayprice.length - 3) + " 元"
            tvOrderPrice.text = "结算价: " + orderInfo.cpayprice + " 元"
        } else {
//            tvOrderPrice.text = "结算价: " + orderInfo.payAmount.toInt() / 100 + " 元"
            tvOrderPrice.text = "结算价: " + String.format("%.2f", orderInfo.payAmount.toDouble() / 100) + " 元"
        }
        tvAddress.text = orderInfo.cstartAddress
        tvStAds.text = orderInfo.cstartAddress
        tvEnAds.text = orderInfo.cendAddress
//        tvOrderFee.text = "¥ " + orderInfo.cpayprice.substring(0, orderInfo.cpayprice.length - 3) + " 元"
        tvOrderFee.text = "¥ " + orderInfo.cpayprice + " 元"
        tvPhone.text = orderInfo.ccustomPhone

        if (!TextUtil.isEmpty(orderInfo.cremark) && orderInfo.cremark.length > 50) {
            tvRemark.text = orderInfo.cremark.substring(0, 50) + "..."
        } else if (!TextUtil.isEmpty(orderInfo.cremark)) {
            tvRemark.text = orderInfo.cremark
        } else {
            tvRemark.text = ""
        }



        if (orderInfo.orderStatus.toInt() == OrderStatus.UNANSWERED_TYPE) {
            //未接单
            llOrderOperate.visibility = View.VISIBLE
            llOrderOperate2.visibility = View.GONE
            initListener()
        } else if (orderInfo.orderStatus.toInt() == OrderStatus.ANSWERED_TYPE) {
            //已接单
            llOrderOperate.visibility = View.VISIBLE
            llDriverInfo.visibility = View.VISIBLE
            llOrderOperate2.visibility = View.VISIBLE
            tvDriverName.text = orderInfo.driver
            tvDriverCar.text = orderInfo.carNo
            initListener()
        } else if (orderInfo.orderStatus.toInt() == OrderStatus.ORDERCANCEL_TYPE) {
            //已取消
        } else if (orderInfo.orderStatus.toInt() == OrderStatus.ORDERFINISH_TYPE) {//已完成
            llDriverInfo.visibility = View.VISIBLE
            llFinish.visibility = View.VISIBLE
            if (orderInfo.driver.isNullOrEmpty()) {
                tvDriverName.text = " "
            } else {
                tvDriverName.text = orderInfo.driver
            }
            if (orderInfo.carNo.isNullOrEmpty()) {
                tvDriverCar.text = " "
            } else {
                tvDriverCar.text = orderInfo.carNo
            }
        }
    }

    @Subscribe
    fun loadData(any: Any) {
        if (any::class == CommEntity::class) {
            var data = any as CommEntity
            if (data.rspCode.equals("00")) {
                ShowToast.showBottom(this, data.rspDesc)
            } else {
                ShowToast.showBottom(this, data.rspDesc)
            }
        }
        progress.dismiss()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == Constants.REQUEST_SELECT_DRIVER_ACTIVITY) {
            val enableDriver = data!!.getSerializableExtra(Constants.ACTIVITY_BACK_DATA) as ListDriversEntity.DriversBean
            setPrice(enableDriver)
//            progress.show()
//            presenter.pubOrder(userNo, tvOrderNo.text.toString(), driver!!.no.toString())
        }
    }

    private fun setPrice(enableDriver: ListDriversEntity.DriversBean) {
        val dialog = SetPriceDialog(this)
        dialog.setOnYesClickListener {
            dialog.dismiss()
            progress.show()
            presenter.pubOrder(userNo, tvOrderNo.text.toString(), enableDriver.no.toString(), dialog.message())
        }
        dialog.show()
    }
}