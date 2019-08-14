package com.it.rxapp_manager_android.module.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.modle.ListColorEntity

/**
 * Created by deqiangchen on 2018/10/9 15:59
 */
class ColorsAdapter() : BaseAdapter() {

    private lateinit var context: Context
    private lateinit var inflater: LayoutInflater
    private lateinit var datas: ArrayList<ListColorEntity.CarcolorsBean>

    constructor(context: Context, datas: ArrayList<ListColorEntity.CarcolorsBean>) : this() {
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
            v = inflater.inflate(R.layout.item_car_color, null)
            holder = ViewHolder(v)
            v.tag = holder
        } else {
            v = convertView
            holder = v.tag as ViewHolder
        }
        var car = datas[position]
        holder.tvColor.text = car.name

        return v
    }

    inner class ViewHolder {

        var tvColor: TextView

        constructor(view: View) {
            tvColor = view.findViewById(R.id.tv_color)
        }

    }

    fun addAll(datas: List<ListColorEntity.CarcolorsBean>) {
        this.datas.addAll(datas)
        notifyDataSetChanged()
    }

    fun clear() {
        this.datas.clear()
        notifyDataSetChanged()
    }

}