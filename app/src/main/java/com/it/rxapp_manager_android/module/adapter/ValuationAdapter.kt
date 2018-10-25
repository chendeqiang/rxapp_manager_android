package com.it.rxapp_manager_android.module.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.modle.ListValuationsEntity

/**
 * Created by deqiangchen on 2018/9/29 15:06
 */
class ValuationAdapter() : BaseAdapter() {


    private lateinit var context: Context
    private lateinit var inflater: LayoutInflater
    private lateinit var datas: ArrayList<ListValuationsEntity.PriceRulesBean>


    constructor(context: Context, datas: ArrayList<ListValuationsEntity.PriceRulesBean>) : this() {
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
            v = inflater.inflate(R.layout.item_valuation, null)
            holder = ViewHolder(v)
            v.tag = holder
        } else {
            v = convertView
            holder = v.tag as ViewHolder
        }
        var data = datas[position]
        holder.tvValuationType.text = "[" + data.linetypename + "]" + data.startcityname + "--" + data.endcityname + "--" + data.productType + "--" + data.cartypename
        holder.tvStartPrice.text = data.startPrice.substring(0, data.startPrice.length - 3)
        holder.tvStartKm.text = data.startKm.substring(0, data.startKm.length - 3)
        holder.tvValuation.setOnClickListener {
            mOnItemValuationListener!!.onValuationClick(position)
        }
        return v
    }

    inner class ViewHolder {

        var tvValuationType: TextView
        var tvStartPrice: TextView
        var tvStartKm: TextView
        var tvValuation: TextView


        constructor(view: View) {
            tvValuationType = view.findViewById(R.id.tv_valuation_type)
            tvStartPrice = view.findViewById(R.id.tv_start_price)
            tvStartKm = view.findViewById(R.id.tv_start_km)
            tvValuation = view.findViewById(R.id.tv_valuation)

        }

    }

    fun addAll(datas: List<ListValuationsEntity.PriceRulesBean>) {
        this.datas.addAll(datas)
        notifyDataSetChanged()
    }

    fun clear() {
        this.datas.clear()
        notifyDataSetChanged()
    }

    interface onItemValuationListener {
        fun onValuationClick(i: Int)
    }

    private var mOnItemValuationListener: onItemValuationListener? = null

    fun setOnItemValuationClickListener(mOnItemValuationListener: onItemValuationListener) {
        this.mOnItemValuationListener = mOnItemValuationListener
    }

}