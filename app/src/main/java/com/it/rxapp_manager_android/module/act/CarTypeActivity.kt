package com.it.rxapp_manager_android.module.act

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.modle.SearchCarEntity
import com.it.rxapp_manager_android.module.adapter.CarTypeAdapter
import com.it.rxapp_manager_android.utils.Constants
import com.it.rxapp_manager_android.utils.LogUtils
import com.it.rxapp_manager_android.utils.TextUtil


/**
 * Created by deqiangchen on 2018/9/29 11:16
 */
class CarTypeActivity : BaseActivity(), TextWatcher {

    private lateinit var etCar: EditText
    private lateinit var ivCancle: ImageView
    private lateinit var tvCancle: TextView
    private lateinit var lvCar: ListView
    private lateinit var adapter: CarTypeAdapter
    private lateinit var datas: ArrayList<SearchCarEntity>

    companion object {
        @JvmStatic
        fun startCarTypeActivity(context: Activity) {
            context.startActivityForResult(Intent(context, CarTypeActivity::class.java), Constants.REQUEST_SELECT_CAR_ACTIVITY)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_type)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        initView()
    }

    private fun initView() {
        etCar = findViewById(R.id.et_car) as EditText
        ivCancle = findViewById(R.id.img_cancel) as ImageView
        tvCancle = findViewById(R.id.tv_cancel) as TextView
        lvCar = findViewById(R.id.lv_car) as ListView

        tvCancle.setOnClickListener {
            finish()
        }
        ivCancle.setOnClickListener {
            etCar.text.clear()
        }

        etCar.addTextChangedListener(this)
        adapter = CarTypeAdapter(this, arrayListOf())
        lvCar.adapter = adapter

        adapter.addAll(TextUtil.getCar(this))
        etCar.addTextChangedListener(this)
        lvCar.setOnItemClickListener { _, _, i, _ ->
            setResult(Activity.RESULT_OK, Intent().putExtra(Constants.ACTIVITY_BACK_DATA, adapter.getItem(i) as SearchCarEntity))
            finish()
        }
    }

    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        datas = arrayListOf()
        if (!TextUtil.isEmpty(s.toString())) {
            TextUtil.getCar(this).forEach { it ->
                if (it.label.contains(s.toString())) {
                    datas.add(it)
                }
            }
            adapter.clear()
            adapter.addAll(datas)
        }
    }
}