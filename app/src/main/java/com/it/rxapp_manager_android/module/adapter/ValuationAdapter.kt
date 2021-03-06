package com.it.rxapp_manager_android.module.adapter

import android.content.Context
import android.graphics.Color
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
        holder.tvValuationType.text = "[" + data.linetypename + "]" + data.startcityname + "-" + data.endcityname + "-" + data.productType + "-" + data.cartypename
        holder.tvStartPrice.text = data.startPrice.substring(0, data.startPrice.length - 3) + "元"
        holder.tvStartKm.text = data.startKm.substring(0, data.startKm.length - 3) + "km"
        holder.tvTwentyKmPrice.text = data.twentykmprice.toString() + "元"
        holder.tvThrityKmPrice.text = data.thritykmprice.toString() + "元"
        holder.tvFortyKmPrice.text = data.fortykmprice.toString() + "元"
        holder.tvFiftyKmPrice.text = data.fiftykmprice.toString() + "元"
        if (data.state == 1) {
            holder.tvState.setTextColor(Color.GREEN)
            holder.tvState.text = "竞价被选中"
        } else {
            holder.tvState.setTextColor(Color.GRAY)
            holder.tvState.text = "常规"
        }


        if (data.online==0){//下线
            holder.tvStateOnline.setTextColor(Color.RED)
            holder.tvStateOnline.text="下线"
        }else{//上线
            holder.tvStateOnline.setTextColor(Color.GREEN)
            holder.tvStateOnline.text="上线"
        }



        if (data.modify == -1) {
            holder.tvModify.text="初始化"
        } else if (data.modify == 0) {
            holder.tvModify.text="未修改"
        } else {
            holder.tvModify.text="已修改"
        }

        if (data.frozen==0){
            holder.tvFrozen.setTextColor(Color.GRAY)
            holder.tvFrozen.text="未冻结"
        }else{
            holder.tvFrozen.setTextColor(Color.RED)
            holder.tvFrozen.text="冻结(无法修改)"
        }

        holder.btnCompare.setOnClickListener {
            mOnItemValuationListener!!.onValuationClick(position)
        }
        return v
    }

    inner class ViewHolder {

        var tvValuationType: TextView
        var tvStartPrice: TextView
        var tvStartKm: TextView
        var btnCompare: Button
        var tvTwentyKmPrice: TextView
        var tvThrityKmPrice: TextView
        var tvFortyKmPrice: TextView
        var tvFiftyKmPrice: TextView

        var tvState: TextView
        var tvModify: TextView
        var tvFrozen: TextView
        var tvStateOnline:TextView



        constructor(view: View) {
            tvValuationType = view.findViewById(R.id.tv_valuation_type)
            tvStartPrice = view.findViewById(R.id.tv_start_price)
            tvStartKm = view.findViewById(R.id.tv_start_km)

            btnCompare = view.findViewById(R.id.btn_compare)
            tvTwentyKmPrice = view.findViewById(R.id.tv_twentykmprice)
            tvThrityKmPrice = view.findViewById(R.id.tv_thritykmprice)
            tvFortyKmPrice = view.findViewById(R.id.tv_fortykmprice)
            tvFiftyKmPrice = view.findViewById(R.id.tv_fiftykmprice)

            tvState = view.findViewById(R.id.tv_state)
            tvModify = view.findViewById(R.id.tv_modify)
            tvFrozen = view.findViewById(R.id.tv_frozen)
            tvStateOnline=view.findViewById(R.id.tv_state_online)
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