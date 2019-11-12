package com.it.rxapp_manager_android.module.act

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.modle.EditDriverEntity
import com.it.rxapp_manager_android.module.base.ComponentHolder
import com.it.rxapp_manager_android.module.base.MyPresenter
import com.it.rxapp_manager_android.utils.Constants
import com.it.rxapp_manager_android.utils.TextUtil
import com.it.rxapp_manager_android.widget.MyProgress
import com.it.rxapp_manager_android.widget.ShowToast
import com.squareup.otto.Subscribe
import javax.inject.Inject

class EditDriverActivity : BaseActivity() {

    private lateinit var btnSave: Button
    private lateinit var dvName: EditText
    private lateinit var dvPhone: EditText
    private lateinit var dvIdCard: EditText


    @Inject
    lateinit var presenter: MyPresenter
    private lateinit var progress: MyProgress
    lateinit var userNo: String
    lateinit var driverName: String
    lateinit var driverPhone: String
    lateinit var driverIdCard: String
    lateinit var driverNo: String

    companion object {
        @JvmStatic
        fun startEditDriverActivity(context: Context, driverName: String, driverPhone: String, driverIdCard: String, driverNo: String) {
            context.startActivity(Intent(context, EditDriverActivity::class.java)
                    .putExtra(Constants.DRIVER_NAME, driverName)
                    .putExtra(Constants.DRIVER_PHONE, driverPhone)
                    .putExtra(Constants.DRIVER_ID_CARD, driverIdCard)
                    .putExtra(Constants.DRIVER_NO, driverNo))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_driver)
        ComponentHolder.appComponent!!.inject(this)
        presenter.register(this)
        progress = MyProgress(this)
        driverNo = intent.getStringExtra(Constants.DRIVER_NO)
        driverName = intent.getStringExtra(Constants.DRIVER_NAME)
        driverPhone = intent.getStringExtra(Constants.DRIVER_PHONE)
        driverIdCard = intent.getStringExtra(Constants.DRIVER_ID_CARD)
        initView()
    }

    private fun initView() {
        setToolbar(toolbar = findViewById(R.id.toolbar) as Toolbar)
        (findViewById(R.id.tv_toolbar_title) as TextView).text = "编辑司机"
        dvName = findViewById(R.id.et_driver_name) as EditText
        dvPhone = findViewById(R.id.et_driver_phone) as EditText
        dvIdCard = findViewById(R.id.et_driver_id_card) as EditText

        dvPhone.isFocusable=false
        dvPhone.isFocusableInTouchMode=false

        if (!driverName.equals("")) {
            dvName.setText(driverName)
        }

        if (!driverPhone.equals("")) {
            dvPhone.setText(driverPhone)
        }

        if (!driverIdCard.equals("")) {
            dvIdCard.setText(driverIdCard)
        }

        btnSave = findViewById(R.id.btn_save_driver) as Button
        btnSave.setOnClickListener {
            checkView()
        }
    }

    private fun checkView() {
        if (!TextUtil.isEmpty(dvName.text.toString()) && !TextUtil.isEmpty(dvPhone.text.toString()) && !TextUtil.isEmpty(dvIdCard.text.toString())) {
            progress.show()
            presenter.editdriver(driverNo, dvName.text.toString(), dvIdCard.text.toString())
        } else {
            ShowToast.showCenter(this, "信息填写不完整")
        }
    }

    @Subscribe
    fun loadData(any: Any) {
        if (any::class == EditDriverEntity::class) {
            var data = any as EditDriverEntity
            if (data.rspCode.equals("00") && data.driverIdentity != null) {
                ShowToast.showCenter(this, data.rspDesc)
            } else {
                ShowToast.showCenter(this, data.rspDesc)
            }
        }
        progress.dismiss()

    }

    override fun onDestroy() {
        super.onDestroy()
        if (progress != null) {
            progress.dismiss()
        }
        presenter.unregister(this)
    }
}