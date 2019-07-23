package com.it.rxapp_manager_android.module.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.modle.ListValuationsEntity

/**
 * Created by deqiangchen on 2018/9/29 15:06
 */
class RelativePriceAdapter() : BaseAdapter() {


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
            v = inflater.inflate(R.layout.item_relative_price, null)
            holder = ViewHolder(v)
            v.tag = holder
        } else {
            v = convertView
            holder = v.tag as ViewHolder
        }
        var data = datas[position]
        holder.tvValuationType.text = "[" + data.orgName + "]" + data.startcityname + "-" + data.endcityname + "-" + data.productType + "-" + data.cartypename
        holder.tvStartPrice.text = data.startPrice.substring(0, data.startPrice.length - 3) + "元"
        holder.tvStartKm.text = data.startKm.substring(0, data.startKm.length - 3) + "km"
        holder.tvTwentyKmPrice.text = data.twentykmprice.toString() + "元"
        holder.tvThrityKmPrice.text = data.thritykmprice.toString() + "元"
        holder.tvFortyKmPrice.text = data.fortykmprice.toString() + "元"
        holder.tvFiftyKmPrice.text = data.fiftykmprice.toString() + "元"
        return v
    }

    inner class ViewHolder {

        var tvValuationType: TextView
        var tvStartPrice: TextView
        var tvStartKm: TextView
        var tvTwentyKmPrice: TextView
        var tvThrityKmPrice: TextView
        var tvFortyKmPrice: TextView
        var tvFiftyKmPrice: TextView


        constructor(view: View) {
            tvValuationType = view.findViewById(R.id.tv_valuation_type)
            tvStartPrice = view.findViewById(R.id.tv_start_price)
            tvStartKm = view.findViewById(R.id.tv_start_km)

            tvTwentyKmPrice = view.findViewById(R.id.tv_twentykmprice)
            tvThrityKmPrice = view.findViewById(R.id.tv_thritykmprice)
            tvFortyKmPrice = view.findViewById(R.id.tv_fortykmprice)
            tvFiftyKmPrice = view.findViewById(R.id.tv_fiftykmprice)


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
}