package com.it.rxapp_manager_android.module.act

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.graphics.ColorUtils
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.utils.Constants
import android.view.MotionEvent
import android.view.View
import com.it.rxapp_manager_android.modle.AddCarEntity
import com.it.rxapp_manager_android.modle.ListColorEntity
import com.it.rxapp_manager_android.modle.SearchCarEntity
import com.it.rxapp_manager_android.module.base.ComponentHolder
import com.it.rxapp_manager_android.module.base.MyPresenter
import com.it.rxapp_manager_android.utils.KeyboardUtil
import com.it.rxapp_manager_android.utils.TextUtil
import com.it.rxapp_manager_android.widget.MyProgress
import com.it.rxapp_manager_android.widget.ShowToast
import com.squareup.otto.Subscribe
import javax.inject.Inject


/**
 * Created by deqiangchen on 2018/9/29 13:40
 */
class AddCarActivity : BaseActivity(), TextWatcher, View.OnTouchListener {

    private lateinit var etNo: EditText
    private lateinit var tvType: TextView
    private lateinit var tvColor: TextView
    private lateinit var btnSv: Button
    private var keyboardUtil: KeyboardUtil? = null
    @Inject
    lateinit var presenter: MyPresenter
    private lateinit var progress: MyProgress
    lateinit var userNo: String
    private var car: SearchCarEntity? = null
    private var color :ListColorEntity.CarcolorsBean?=null

    companion object {
        @JvmStatic
        fun startAddCarActivity(context: Context, userNo: String) {
            context.startActivity(Intent(context, AddCarActivity::class.java).putExtra(Constants.USER_NO, userNo))
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_car)
        ComponentHolder.appComponent!!.inject(this)
        presenter.register(this)
        progress = MyProgress(this)
        userNo = intent.getStringExtra(Constants.USER_NO)
        initView()
    }

    private fun initView() {

        setToolbar(toolbar = findViewById(R.id.toolbar) as Toolbar)
        (findViewById(R.id.tv_toolbar_title) as TextView).text = "添加车辆"
        etNo = findViewById(R.id.et_car_no) as EditText
        tvType = findViewById(R.id.tv_car_type) as TextView
        tvColor = findViewById(R.id.tv_car_color) as TextView
        btnSv = findViewById(R.id.btn_save) as Button
        tvType.setOnClickListener {
            CarTypeActivity.startCarTypeActivity(this)
        }

        tvColor.setOnClickListener {
            ColorsActivity.startColorsActivity(this)
        }
        etNo.setOnTouchListener(this)

        etNo.addTextChangedListener(this)

        btnSv.setOnClickListener {
            if (TextUtil.isEmpty(etNo.text.toString()) || tvType.text.toString().equals("请选择车型")||tvColor.text.toString().equals("请选择颜色")) {
                ShowToast.showCenter(this, "信息不完整")
            } else {
                progress.show()
                presenter.addCar(userNo, car!!.value, etNo.text.toString(),tvColor.text.toString())
            }
        }
    }

    override fun onTouch(p0: View, p1: MotionEvent): Boolean {
        if (keyboardUtil == null) {
            keyboardUtil = KeyboardUtil(this@AddCarActivity, etNo)
            keyboardUtil!!.hideSoftInputMethod()
            keyboardUtil!!.showKeyboard()
        } else {
            keyboardUtil!!.showKeyboard()
        }
        return false
    }

    override fun afterTextChanged(p0: Editable?) {
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (keyboardUtil!!.isShow()) {
                keyboardUtil!!.hideKeyboard()
            } else {
                finish()
            }
        }
        return false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == Constants.REQUEST_SELECT_CAR_ACTIVITY) {
            car = data!!.getSerializableExtra(Constants.ACTIVITY_BACK_DATA) as SearchCarEntity
            tvType.text = car!!.label
        }else if (resultCode == Activity.RESULT_OK && requestCode == Constants.REQUEST_SELECT_COLOR_ACTIVITY){
            color = data!!.getSerializableExtra(Constants.ACTIVITY_BACK_DATA) as ListColorEntity.CarcolorsBean
            tvColor.text= color!!.name
        }
    }

    @Subscribe
    fun loadData(any: Any) {
        if (any::class == AddCarEntity::class) {
            var data = any as AddCarEntity
            if (data.rspCode.equals("00")) {
                ShowToast.showCenter(this, data.rspDesc)
                finish()
            } else {
                ShowToast.showCenter(this, data.rspDesc)
            }
        }
        progress.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unregister(this)
    }
}