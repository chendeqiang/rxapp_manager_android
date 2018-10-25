package com.it.rxapp_manager_android.module.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.modle.ListRelationEntity
import com.it.rxapp_manager_android.utils.TextUtil

/**
 * Created by deqiangchen on 2018/10/8 11:01
 */
class RelationAdapter() : BaseAdapter() {

    private lateinit var context: Context
    private lateinit var inflater: LayoutInflater
    private lateinit var datas: ArrayList<ListRelationEntity.RelationsBean>

    constructor(context: Context, datas: ArrayList<ListRelationEntity.RelationsBean>) : this() {
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
            v = inflater.inflate(R.layout.item_relation, null)
            holder = ViewHolder(v)
            v.tag = holder
        } else {
            v = convertView
            holder = v.tag as ViewHolder
        }
        var relation = datas[position]
        holder.tvDriverName.text = relation.cname

        if (!TextUtil.isEmpty(relation.model_name) && relation.model_name.length > 8) {
            holder.tvCar1.text  = relation.model_name.substring(0, 8) + "..."
        } else if (!TextUtil.isEmpty(relation.model_name)) {
            holder.tvCar1.text = relation.model_name
        } else {
            holder.tvCar1.text = ""
        }

//        if (relation.model_name.isNullOrEmpty()) {
//            holder.tvCar1.text = " "
//        } else {
//            holder.tvCar1.text = relation.model_name + "--"
//        }
        if (relation.carname.isNullOrEmpty()) {
            holder.tvCar2.text = " "
        } else {
            holder.tvCar2.text = relation.carname
        }
        if (relation.cplatenumber.isNullOrEmpty()) {
            holder.tvCarNo.text = " "
        } else {
            holder.tvCarNo.text = relation.cplatenumber
        }

        holder.btnChange.setOnClickListener {
            mOnItemChangeListener!!.onItemClick(position)
        }
        return v
    }

    inner class ViewHolder {

        var tvDriverName: TextView
        var tvCar1: TextView
        var tvCar2: TextView
        var tvCarNo: TextView
        var btnChange: Button


        constructor(view: View) {
            tvDriverName = view.findViewById(R.id.tv_driverName)
            tvCar1 = view.findViewById(R.id.tv_car1)
            tvCar2 = view.findViewById(R.id.tv_car2)
            tvCarNo = view.findViewById(R.id.tv_carNo)
            btnChange = view.findViewById(R.id.btn_change_relation)

        }

    }

    fun addAll(datas: List<ListRelationEntity.RelationsBean>) {
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