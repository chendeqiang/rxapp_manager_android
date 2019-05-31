package com.it.rxapp_manager_android.module.adapter

import android.content.Context
import android.icu.text.CollationKey
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.modle.ListOrderEntity
import com.it.rxapp_manager_android.module.base.*
import com.it.rxapp_manager_android.utils.LogUtils
import java.text.Collator
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by deqiangchen on 2018/9/17 10:20
 */
class OrderAdapter() : BaseAdapter() {

    private lateinit var context: Context
    private lateinit var inflater: LayoutInflater
    private lateinit var datas: ArrayList<ListOrderEntity.OrdersBean>
    private var sdfMoth: SimpleDateFormat = SimpleDateFormat("MM月dd日")
    private var sdfDay: SimpleDateFormat = SimpleDateFormat("HH:mm")

    constructor(context: Context, datas: ArrayList<ListOrderEntity.OrdersBean>) : this() {
        this.context = context
        inflater = LayoutInflater.from(context)
        this.datas = datas
    }


    override fun getItem(position: Int): Any {
        return datas[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return datas.size
    }

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
        var holder: ViewHolder?
        var v: View
        if (convertView == null) {
            v = inflater.inflate(R.layout.item_order, null)
            holder = ViewHolder(v)
            v.tag = holder
        } else {
            v = convertView
            holder = v.tag as ViewHolder
        }

        var order = datas[position]
        holder.tvOrderNo.text = order.orderNo
        holder.tvOrderType.text = OrderType.getKey(order.orderType.toInt()) + "(" + order.carTypeName + ")      " + OrderSource.getKey(order.source.toInt())
        holder.tvBookTime.text = order.useTime
        if (order.orderType.toInt() == OrderType.DAY_RENTER_TYPE) {
            holder.llBusiness.visibility = View.VISIBLE
            holder.llNoBusiness.visibility = View.GONE
            holder.tvAddress.text = order.cstartAddress
        } else {
            holder.llBusiness.visibility = View.GONE
            holder.llNoBusiness.visibility = View.VISIBLE
            holder.tvStartAddress.text = order.cstartAddress
            holder.tvEndAddress.text = order.cendAddress
        }

        if (order.payAmount.isNullOrEmpty()) {
//            holder.tvPrice.text = "结算价:" + order.cpayprice.substring(0, order.cpayprice.length - 3)
            holder.tvPrice.text = "结算价:" + order.cpayprice
        } else {
//            holder.tvPrice.text = "结算价:" + order.payAmount.toInt() / 100
            holder.tvPrice.text = "结算价:" + String.format("%.2f", order.payAmount.toDouble() / 100)
        }
//        holder.tvFee.text = "¥" + order.cpayprice.substring(0, order.cpayprice.length - 3)
        holder.tvFee.text = "¥" + order.cpayprice
        holder.tvType.text = OrderStatus.getKey(order.orderStatus.toInt())
        holder.tvType.setTextColor(ContextCompat.getColor(context, R.color.text_color_red))
        holder.tvType.setBackgroundColor(ContextCompat.getColor(context, R.color.white))

        return v
    }

    inner class ViewHolder {

        var tvOrderType: TextView
        var tvBookTime: TextView
        var tvAddress: TextView
        var tvStartAddress: TextView
        var tvEndAddress: TextView
        var tvFee: TextView
        var tvPrice: TextView
        var tvType: TextView
        var llNoBusiness: LinearLayout
        var llBusiness: LinearLayout
        var tvOrderNo: TextView
        var tvOrderFrom: TextView


        constructor(view: View) {
            tvOrderType = view.findViewById(R.id.tv_order_type)
            tvBookTime = view.findViewById(R.id.tv_book_time)
            tvStartAddress = view.findViewById(R.id.tv_start_address)
            tvEndAddress = view.findViewById(R.id.tv_end_address)
            tvFee = view.findViewById(R.id.tv_fee)
            tvPrice = view.findViewById(R.id.tv_price)
            tvType = view.findViewById(R.id.tv_type)
            llNoBusiness = view.findViewById(R.id.ll_no_business)
            llBusiness = view.findViewById(R.id.ll_business)
            tvAddress = view.findViewById(R.id.tv_address)
            tvOrderNo = view.findViewById(R.id.tv_order_no)
            tvOrderFrom = view.findViewById(R.id.tv_order_from_list)

        }

    }

    fun addAll(datas: List<ListOrderEntity.OrdersBean>) {
        this.datas.addAll(datas)
        notifyDataSetChanged()
    }

    fun clear() {
        this.datas.clear()
        notifyDataSetChanged()
    }
}