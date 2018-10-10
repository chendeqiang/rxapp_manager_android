package com.it.rxapp_manager_android.module.act

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.dialog.MessageDialog
import com.it.rxapp_manager_android.dialog.RemarkDialog
import com.it.rxapp_manager_android.modle.OrderEntity
import com.it.rxapp_manager_android.modle.OrderInfoEntity
import com.it.rxapp_manager_android.module.base.*
import com.it.rxapp_manager_android.utils.Constants
import com.it.rxapp_manager_android.utils.StartUtil
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
    private lateinit var tvFlight: TextView
    private lateinit var tvPhone: TextView
    private lateinit var tvRemark: TextView
    private lateinit var tvDriverName: TextView
    private lateinit var tvDriverCar: TextView
    private lateinit var tvFinishTime: TextView
    private lateinit var tvFlightHint: TextView
    private lateinit var tvTime: TextView
    private lateinit var tvStAds: TextView
    private lateinit var tvEnAds: TextView
    private lateinit var imgFlight: ImageView


    @Inject
    lateinit var presenter: MyPresenter
    private lateinit var progress: MyProgress
    lateinit var userNo: String
    private lateinit var orderNo: String
    private lateinit var flowNo: String

    companion object {
        @JvmStatic
        fun startOrderInfoActivity(context: Context, orderNo: String, flowNo: String, userNo: String) {
            context.startActivity(Intent(context, OrderInfoActivity::class.java).
                    putExtra(Constants.ORDER_NO, orderNo).
                    putExtra(Constants.FLOW_NO, flowNo).
                    putExtra(Constants.USER_NO, userNo))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_info)
        ComponentHolder.appComponent!!.inject(this)
        presenter.register(this)
        progress = MyProgress(this)
        initView()
        orderNo = intent.getStringExtra(Constants.ORDER_NO)
        flowNo = intent.getStringExtra(Constants.FLOW_NO)
        userNo = intent.getStringExtra(Constants.USER_NO)
        Handler().postDelayed({
            progress.show()
//            presenter.qryOrder(orderNo)
        }, 300)
    }

    private fun initView() {
        setToolbar(toolbar = findViewById(R.id.toolbar) as Toolbar)
        (findViewById(R.id.tv_toolbar_title) as TextView).text = "订单详情"

        tvOrderNo = findViewById(R.id.tv_order_no) as TextView
        tvOrderType = findViewById(R.id.tv_order_type) as TextView
        tvOrderFee = findViewById(R.id.tv_order_fee) as TextView
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
        tvFinishTime = findViewById(R.id.tv_finish_time) as TextView
        imgFlight = findViewById(R.id.img_flight) as ImageView
        tvFlightHint = findViewById(R.id.tv_flight_hint) as TextView
        tvTime = findViewById(R.id.tv_time) as TextView
        tvStAds = findViewById(R.id.tv_start_address) as TextView
        tvEnAds = findViewById(R.id.tv_end_address) as TextView
        llOrderOperate = findViewById(R.id.ll_order_operate) as LinearLayout

    }

    private fun initListener() {
        findViewById(R.id.btn_copy).setOnClickListener {
            val clipboardManager = (this.getSystemService(android.content.Context.CLIPBOARD_SERVICE) as ClipboardManager)
            clipboardManager.primaryClip = ClipData.newPlainText(null, tvOrderNo.text)
            ShowToast.showCenter(this, "复制成功")
        }
        findViewById(R.id.btn_order_arrange).setOnClickListener {
            ShowToast.showCenter(this, "派工")
        }
        findViewById(R.id.btn_order_revise).setOnClickListener {
            ShowToast.showCenter(this, "改派")
        }
        findViewById(R.id.btn_return2pool).setOnClickListener {
            ShowToast.showCenter(this, "重回订单池")
        }
        findViewById(R.id.btn_route).setOnClickListener {
            SearchRouteActivity.startSearchRouteActivity(this, orderNo)
        }
        findViewById(R.id.btn_add_remark).setOnClickListener {
            //            ShowToast.showCenter(this, "添加备注")
            writeRemark()
        }
    }

    private fun writeRemark() {
        val dialog = RemarkDialog(this)
        dialog.setOnCancelClickListener { dialog.dismiss() }
        dialog.setOnOkClickListener {
            dialog.dismiss()
            //请求网络，提交数据
            ShowToast.showCenter(this, dialog.message())
        }
        dialog.show()
    }

    @Subscribe
    fun loadData(any: Any) {
        if (any::class == OrderInfoEntity::class) {
            val data: OrderInfoEntity = any as OrderInfoEntity
            if (data.rspCode.equals("00")) {
                setData(data.order)
            } else {
                ShowToast.showCenter(this, data.rspDesc)
                finish()
            }
            progress.dismiss()
        }
    }

    private fun setData(orderInfo: OrderEntity) {
        if (orderInfo.orderType == OrderType.SEND_PLANE_TYPE || orderInfo.orderType == OrderType.TAKE_PLANE_TYPE) {
            tvFlightHint.text = "航班:"
            imgFlight.setImageResource(R.drawable.ic_plane)
            llEnAds.visibility = View.VISIBLE
            llStAds.visibility = View.VISIBLE
            llFlight.visibility = View.VISIBLE
            llAddress.visibility = View.GONE
            tvTime.text = TextUtil.getFormatWeek2(orderInfo.bookTime.toLong())
        } else if (orderInfo.orderType == OrderType.SEND_TRAIN_TYPE || orderInfo.orderType == OrderType.TAKE_TRAIN_TYPE) {
            tvFlightHint.text = "车次:"
            imgFlight.setImageResource(R.drawable.ic_train)
            llEnAds.visibility = View.VISIBLE
            llStAds.visibility = View.VISIBLE
            llFlight.visibility = View.VISIBLE
            llAddress.visibility = View.GONE
            tvTime.text = TextUtil.getFormatWeek2(orderInfo.bookTime.toLong())
        } else {
            llEnAds.visibility = View.GONE
            llStAds.visibility = View.GONE
            llFlight.visibility = View.GONE
            llAddress.visibility = View.VISIBLE

            tvTime.text = TextUtil.getFormatString((orderInfo.bookTime)!!.toLong(), orderInfo.bookDays, "yyyy-MM-dd HH:mm")
        }
        tvOrderType.text = OrderType.getKey(orderInfo.orderType) + "(" + CarLevel.getKey(orderInfo.carLevel) + ")"
        tvOrderStatus.text = OrderStatus.getKey(orderInfo.orderStatus)
        tvOrderNo.text = orderInfo.orderNo
        tvFlight.text = orderInfo.tripNo
        tvAddress.text = orderInfo.startAddr
        tvStAds.text = orderInfo.startAddr
        tvEnAds.text = orderInfo.endAddr
        tvOrderFee.text = "¥" + orderInfo.orderAmount / 100 + "元"
        tvPhone.text = orderInfo.passengerMobile

        if (!TextUtil.isEmpty(orderInfo.remark) && orderInfo.remark.length > 50) {
            tvRemark.text = orderInfo.remark.substring(0, 50) + "..."
        } else if (!TextUtil.isEmpty(orderInfo.remark)) {
            tvRemark.text = orderInfo.remark
        } else {
            tvRemark.text = ""
        }
        initListener()
        if (orderInfo.orderStatus == OrderStatus.UNANSWERED_TYPE) {//未接单
        } else if (orderInfo.orderStatus == OrderStatus.ANSWERED_TYPE) {//已接单
        } else if (orderInfo.orderStatus == OrderStatus.WAITFORSERVICE_TYPE) {//待服务
            llDriverInfo.visibility = View.GONE
            llFinish.visibility = View.GONE
            tvFinishTime.visibility = View.GONE
            llOrderOperate.visibility = View.VISIBLE
        } else if (orderInfo.orderStatus == OrderStatus.ORDERCANCEL_TYPE) {//已取消
        } else if (orderInfo.orderStatus == OrderStatus.ALREADY_TYPE) {//已就位
        } else if (orderInfo.orderStatus == OrderStatus.ORDERING_TYPE) {//用车中
        } else if (orderInfo.orderStatus == OrderStatus.ORDERFINISH_TYPE) {//已完成
            llDriverInfo.visibility = View.VISIBLE
            llFinish.visibility = View.VISIBLE
            tvFinishTime.visibility = View.VISIBLE
            tvDriverName.text = orderInfo.driverNo
            tvDriverCar.text = orderInfo.carNo + "(" + CarLevel.getKey(orderInfo.carLevel) + ")"
            tvFinishTime.text = "完成时间:${TextUtil.getFormatString(orderInfo.orderStopTime)}"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unregister(this)
    }
}