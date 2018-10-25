package com.it.rxapp_manager_android.module.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.modle.ListBasicAuthCityEntity
import com.it.rxapp_manager_android.module.base.ProductType

/**
 * Created by deqiangchen on 2018/10/22 11:18
 */
class BasicAuthCityAdapter() : BaseAdapter() {

    private lateinit var context: Context
    private lateinit var inflater: LayoutInflater
    private lateinit var datas: ArrayList<ListBasicAuthCityEntity.AuthCitysBean>

    constructor(context: Context, datas: ArrayList<ListBasicAuthCityEntity.AuthCitysBean>) : this() {
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
            v = inflater.inflate(R.layout.item_basic_authcity, null)
            holder = ViewHolder(v)
            v.tag = holder
        } else {
            v = convertView
            holder = v.tag as ViewHolder
        }
        var product = datas[position]
        holder.tvAuthCity.text = "[" + product.linetypename + "] " + product.startcityname + "--" + product.endcityname + "--" + product.cartypename + "--" + ProductType.getKey(product.productType.toInt())

        return v
    }

    inner class ViewHolder {

        var tvAuthCity: TextView

        constructor(view: View) {
            tvAuthCity = view.findViewById(R.id.tv_basic_authcity)

        }

    }

    fun addAll(datas: List<ListBasicAuthCityEntity.AuthCitysBean>) {
        this.datas.addAll(datas)
        notifyDataSetChanged()
    }

    fun clear() {
        this.datas.clear()
        notifyDataSetChanged()
    }

}