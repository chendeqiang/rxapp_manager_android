package com.it.rxapp_manager_android.module.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.modle.ListDriverEntity
import com.it.rxapp_manager_android.modle.ListDriversEntity
import com.it.rxapp_manager_android.module.base.DriverStatus
import com.it.rxapp_manager_android.utils.TextUtil

/**
 * Created by deqiangchen on 2018/9/29 15:06
 */
class EnableDriverAdapter() : BaseAdapter() {


    private lateinit var context: Context
    private lateinit var inflater: LayoutInflater
    private lateinit var datas: ArrayList<ListDriversEntity.DriversBean>


    constructor(context: Context, datas: ArrayList<ListDriversEntity.DriversBean>) : this() {
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
            v = inflater.inflate(R.layout.item_enable_driver, null)
            holder = ViewHolder(v)
            v.tag = holder
        } else {
            v = convertView
            holder = v.tag as ViewHolder
        }
        var driver = datas[position]
        holder.tvDriverName.text = "姓名："+driver.driverName
        holder.tvCar.text = "车型："+driver.carBrand
        holder.tvCarNo.text ="车牌："+ driver.carNo+"("+driver.carName+")"
//        holder.tvCarType.text = driver.carName

        if (!TextUtil.isEmpty(driver.cidentity)){
            holder.tvDriverCard.text ="证件："+driver.cidentity.subSequence(0,3)+"---"+driver.cidentity.substring(14)
        }else{
            holder.tvDriverCard.text ="证件：---"
        }


        return v
    }

    inner class ViewHolder {

        var tvDriverName: TextView
        var tvCar: TextView
        var tvCarNo: TextView
//        var tvCarType: TextView
        var tvDriverCard:TextView


        constructor(view: View) {
            tvDriverName = view.findViewById(R.id.tv_driver_name)
            tvCar = view.findViewById(R.id.tv_car)
            tvCarNo = view.findViewById(R.id.tv_car_no)
//            tvCarType = view.findViewById(R.id.tv_car_typee)
            tvDriverCard =view.findViewById(R.id.tv_driver_card)

        }

    }

    fun addAll(datas: List<ListDriversEntity.DriversBean>) {
        this.datas.addAll(datas)
        notifyDataSetChanged()
    }

    fun clear() {
        this.datas.clear()
        notifyDataSetChanged()
    }

}