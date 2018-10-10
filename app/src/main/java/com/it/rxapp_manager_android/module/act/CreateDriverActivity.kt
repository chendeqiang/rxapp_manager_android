package com.it.rxapp_manager_android.module.act

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.*
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.modle.AddDriverEntity
import com.it.rxapp_manager_android.module.base.ComponentHolder
import com.it.rxapp_manager_android.module.base.MyPresenter
import com.it.rxapp_manager_android.utils.Constants
import com.it.rxapp_manager_android.utils.TextUtil
import com.it.rxapp_manager_android.widget.MyProgress
import com.it.rxapp_manager_android.widget.ShowToast
import com.squareup.otto.Subscribe
import javax.inject.Inject

/**
 * Created by deqiangchen on 2018/9/25 10:45
 */
class CreateDriverActivity : BaseActivity() {

    //    private lateinit var sp: Spinner
    private lateinit var btnSave: Button
    private lateinit var dvName: EditText
    private lateinit var dvPhone: EditText

    @Inject
    lateinit var presenter: MyPresenter
    private lateinit var progress: MyProgress
    lateinit var userNo: String
//    lateinit var sptype: String

    companion object {
        @JvmStatic
        fun startCreateDriverActivity(context: Context, userNo: String) {
            context.startActivity(Intent(context, CreateDriverActivity::class.java).
                    putExtra(Constants.USER_NO, userNo))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_driver)
        ComponentHolder.appComponent!!.inject(this)
        presenter.register(this)
        progress = MyProgress(this)
        initView()
        userNo = intent.getStringExtra(Constants.USER_NO)

    }

    private fun initView() {
        setToolbar(toolbar = findViewById(R.id.toolbar) as Toolbar)
        (findViewById(R.id.tv_toolbar_title) as TextView).text = "添加司机"
//        sp = findViewById(R.id.spinner_type) as Spinner
//        sp.onItemSelectedListener = this
        dvName = findViewById(R.id.et_driver_name) as EditText
        dvPhone = findViewById(R.id.et_driver_phone) as EditText
        btnSave = findViewById(R.id.btn_save_driver) as Button
        btnSave.setOnClickListener {
            checkView()
        }
    }

    private fun checkView() {
        if (!TextUtil.isEmpty(dvName.text.toString()) && !TextUtil.isEmpty(dvPhone.text.toString())) {
            createDriver(dvName.text.toString(), dvPhone.text.toString())
        } else {
            ShowToast.showCenter(this, "信息填写不完整")
        }
    }

    private fun createDriver(name: String, phone: String) {
        progress.show()
        presenter.addDriver(userNo,name, phone)
    }

    @Subscribe
    fun loadData(any: Any) {
        if (any::class == AddDriverEntity::class) {
            var data = any as AddDriverEntity
            if (data.rspCode.equals("00") && !TextUtil.isEmpty(data.driver.toString())) {
                ShowToast.showCenter(this, "添加成功")
            }
        }
        progress.dismiss()

    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.unregister(this)
    }

//    override fun onNothingSelected(p0: AdapterView<*>?) {}
//
//    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//        when (parent!!.id) {
//            R.id.spinner_type -> {
//                val letter = resources.getStringArray(R.array.letter)
//                sptype = letter[position].toString()
//                Log.i("spinner1点击------", sptype)
//            }
//        }
//    }
}