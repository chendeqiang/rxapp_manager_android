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
import com.it.rxapp_manager_android.module.base.DriverStatus


/**
 * Created by deqiangchen on 2018/9/29 15:06
 */
class DriverAdapter() : BaseAdapter() {


    private lateinit var context: Context
    private lateinit var inflater: LayoutInflater
    private lateinit var datas: ArrayList<ListDriverEntity.DriversBean>


    constructor(context: Context, datas: ArrayList<ListDriverEntity.DriversBean>) : this() {
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
            v = inflater.inflate(R.layout.item_driver, null)
            holder = ViewHolder(v)
            v.tag = holder
        } else {
            v = convertView
            holder = v.tag as ViewHolder
        }
        var driver = datas[position]
        holder.tvDriverName.text = "姓名: " + driver.cname
        holder.tvDriverPhone.text = "电话: " + driver.cphone
        holder.tvDriverStu.text = DriverStatus.getKey(driver.cstate)
        holder.tvWrite.setOnClickListener {
            mOnItemChangeListener!!.onWriteClick(position)
        }

        if (driver.cidentity == null || driver.cidentity.equals("")) {
            holder.tvDriverIdCard.text = "证件: " + "---"
        } else {
            holder.tvDriverIdCard.text = "证件: " + driver.cidentity
        }
        if (driver.cstate == 0) {
            holder.tvDriverStu.setTextColor(ContextCompat.getColor(context, R.color.text_color_red))
            holder.tvDriverStu.setOnClickListener {
                mOnItemChangeListener!!.onEnableClick(position)
            }
        } else {
            holder.tvDriverStu.setTextColor(ContextCompat.getColor(context, R.color.text_color_blue))
            holder.tvDriverStu.setOnClickListener {
                mOnItemChangeListener!!.onDisableClick(position)
            }
        }

        return v
    }

    inner class ViewHolder {

        var tvDriverName: TextView
        var tvDriverPhone: TextView
        var tvDriverStu: TextView
        var tvDriverIdCard: TextView
        var tvWrite: TextView


        constructor(view: View) {
            tvDriverName = view.findViewById(R.id.tv_driver_name)
            tvDriverPhone = view.findViewById(R.id.tv_driver_phone)
            tvDriverStu = view.findViewById(R.id.tv_driver_stu)
            tvDriverIdCard = view.findViewById(R.id.tv_driver_id_card)
            tvWrite = view.findViewById(R.id.tv_write)
        }

    }

    fun addAll(datas: List<ListDriverEntity.DriversBean>) {
        this.datas.addAll(datas)
        notifyDataSetChanged()
    }

    fun clear() {
        this.datas.clear()
        notifyDataSetChanged()
    }

    interface onItemChangeListener {
        fun onEnableClick(i: Int)
        fun onDisableClick(i: Int)
        fun onWriteClick(i: Int)
    }

    private var mOnItemChangeListener: onItemChangeListener? = null

    fun setOnItemChangeClickListener(mOnItemChangeListener: onItemChangeListener) {
        this.mOnItemChangeListener = mOnItemChangeListener
    }


}