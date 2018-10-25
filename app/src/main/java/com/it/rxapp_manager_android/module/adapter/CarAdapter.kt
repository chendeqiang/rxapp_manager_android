package com.it.rxapp_manager_android.module.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.modle.ListCarEntity
import com.it.rxapp_manager_android.utils.TextUtil

/**
 * Created by deqiangchen on 2018/9/30 14:32
 */
class CarAdapter() : BaseAdapter() {
    private lateinit var context: Context
    private lateinit var inflater: LayoutInflater
    private lateinit var datas: ArrayList<ListCarEntity.CarsBean>


    constructor(context: Context, datas: ArrayList<ListCarEntity.CarsBean>) : this() {
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
            v = inflater.inflate(R.layout.item_car, null)
            holder = ViewHolder(v)
            v.tag = holder
        } else {
            v = convertView
            holder = v.tag as ViewHolder
        }
        var car = datas[position]

        if (!TextUtil.isEmpty(car.carBrand) && car.carBrand.length > 8) {
            holder.tvCarType.text = car.carBrand.substring(0, 8) + "..."
        } else if (!TextUtil.isEmpty(car.carBrand)) {
            holder.tvCarType.text = car.carBrand
        } else {
            holder.tvCarType.text = ""
        }

//        holder.tvCarType.text = car.carBrand
        holder.tvCarStyle.text = car.carLevelName
        holder.tvCarNo.text = car.carNo

        holder.imgBj.setOnClickListener {
            mOnItemChangeListener!!.onItemClick(position)
        }

        return v
    }

    inner class ViewHolder {

        var tvCarType: TextView
        var tvCarStyle: TextView
        var tvCarNo: TextView
        var imgBj: ImageView


        constructor(view: View) {
            tvCarType = view.findViewById(R.id.tv_car_type)
            tvCarStyle = view.findViewById(R.id.tv_car_style)
            tvCarNo = view.findViewById(R.id.tv_car_no)
            imgBj = view.findViewById(R.id.img_bj)

        }

    }

    fun addAll(datas: List<ListCarEntity.CarsBean>) {
        this.datas.addAll(datas)
        notifyDataSetChanged()
    }

    fun clear() {
        this.datas.clear()
        notifyDataSetChanged()
    }

    interface onItemChangeListener {
        fun onItemClick(i: Int)
    }

    private var mOnItemChangeListener: onItemChangeListener? = null

    fun setOnItemChangeClickListener(mOnItemChangeListener: onItemChangeListener) {
        this.mOnItemChangeListener = mOnItemChangeListener
    }
}