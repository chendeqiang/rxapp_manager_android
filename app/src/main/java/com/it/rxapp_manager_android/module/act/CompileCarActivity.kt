package com.it.rxapp_manager_android.module.act

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.Button
import android.widget.TextView
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.modle.CommEntity
import com.it.rxapp_manager_android.modle.ListColorEntity
import com.it.rxapp_manager_android.modle.SearchCarEntity
import com.it.rxapp_manager_android.module.base.ComponentHolder
import com.it.rxapp_manager_android.module.base.MyPresenter
import com.it.rxapp_manager_android.utils.Constants
import com.it.rxapp_manager_android.widget.MyProgress
import com.it.rxapp_manager_android.widget.ShowToast
import com.squareup.otto.Subscribe
import javax.inject.Inject

class CompileCarActivity:BaseActivity() {

    private lateinit var tvCarNo: TextView
    private lateinit var tvType: TextView
    private lateinit var tvColor: TextView
    private lateinit var btnSv: Button
    lateinit var carNo: String
    lateinit var carId: String
    private var car: SearchCarEntity? = null
    private var color :ListColorEntity.CarcolorsBean?=null
    @Inject
    lateinit var presenter: MyPresenter
    private lateinit var progress: MyProgress
    companion object {
        @JvmStatic
        fun startCompileCarActivity(context: Activity,carNo:String,carId:String) {
            context.startActivity(Intent(context, CompileCarActivity::class.java).putExtra(Constants.CAR_NO,carNo).putExtra(Constants.CAR_ID,carId))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compile_car)
        ComponentHolder.appComponent!!.inject(this)
        presenter.register(this)
        progress = MyProgress(this)
        carNo =intent.getStringExtra(Constants.CAR_NO)
        carId =intent.getStringExtra(Constants.CAR_ID)
        initView()
    }

    private fun initView() {

        setToolbar(toolbar = findViewById(R.id.toolbar) as Toolbar)
        (findViewById(R.id.tv_toolbar_title) as TextView).text = "编辑车辆"

        tvCarNo= findViewById(R.id.tv_car_number) as TextView
        tvType =findViewById(R.id.tv_car_type) as TextView
        tvColor=findViewById(R.id.tv_car_color) as TextView
        btnSv = findViewById(R.id.btn_save) as Button

        tvCarNo.text=carNo
        tvType.setOnClickListener {
            CarTypeActivity.startCarTypeActivity(this)
        }

        tvColor.setOnClickListener {
            ColorsActivity.startColorsActivity(this)
        }

        btnSv.setOnClickListener{
            if (tvType.text.toString().equals("请选择车型")||tvColor.text.toString().equals("请选择颜色")) {
                ShowToast.showBottom(this, "信息不完整")
            } else {
                progress.show()
                presenter.editCar(carId, car!!.value, color!!.name)
            }
        }
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
        if (any::class == CommEntity::class) {
            var data = any as CommEntity
            if (data.rspCode.equals("00")) {
                ShowToast.showBottom(this, data.rspDesc)
                finish()
            } else {
                ShowToast.showBottom(this, data.rspDesc)
            }
        }
        progress.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unregister(this)
    }
}