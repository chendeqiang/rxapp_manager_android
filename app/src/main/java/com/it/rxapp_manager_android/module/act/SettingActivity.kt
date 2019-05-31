package com.it.rxapp_manager_android.module.act

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.modle.CommEntity
import com.it.rxapp_manager_android.modle.ListDriverEntity
import com.it.rxapp_manager_android.modle.OrganizationInfoEntity
import com.it.rxapp_manager_android.modle.UpdateOrgInfoEntity
import com.it.rxapp_manager_android.module.base.ComponentHolder
import com.it.rxapp_manager_android.module.base.MyPresenter
import com.it.rxapp_manager_android.module.base.OrderModel
import com.it.rxapp_manager_android.utils.Constants
import com.it.rxapp_manager_android.utils.TextUtil
import com.it.rxapp_manager_android.widget.MyProgress
import com.it.rxapp_manager_android.widget.ShowToast
import com.squareup.otto.Subscribe
import javax.inject.Inject

/**
 * Created by deqiangchen on 2018/9/5 11:05
 */
class SettingActivity : BaseActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var spDispatchPattern: Spinner
    private lateinit var etFleetName: EditText
    private lateinit var etFleetMobile: EditText
    private lateinit var etFleetNo: EditText
    private lateinit var et_Phone: EditText
    private lateinit var et_Phone1: EditText
    private lateinit var et_Phone2: EditText
    private lateinit var etCommisionXc: EditText
    private lateinit var etCommisionTc: EditText
    private lateinit var etCommisionFz: EditText
    private lateinit var etCommisionSy: EditText
    private lateinit var btnSave: Button
    @Inject
    lateinit var presenter: MyPresenter
    lateinit var userNo: String
    private lateinit var progress: MyProgress
    private var orderType: Int = 0
    lateinit var type: Array<String>

    companion object {
        @JvmStatic
        fun startSettingActivity(context: Context, userNo: String) {
            context.startActivity(Intent(context, SettingActivity::class.java).
                    putExtra(Constants.USER_NO, userNo))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        ComponentHolder.appComponent!!.inject(this)
        presenter.register(this)
        progress = MyProgress(this)
        userNo = intent.getStringExtra(Constants.USER_NO)
        initView()
    }

    private fun initView() {
        setToolbar(toolbar = findViewById(R.id.toolbar) as Toolbar)
        (findViewById(R.id.tv_toolbar_title) as TextView).text = "用户设置"
        etFleetName = findViewById(R.id.et_fleetName) as EditText
        etFleetMobile = findViewById(R.id.et_fleetMobile) as EditText
        etFleetNo = findViewById(R.id.et_fleetNo) as EditText
        et_Phone = findViewById(R.id.et_phone) as EditText
        et_Phone1 = findViewById(R.id.et_phone1) as EditText
        et_Phone2 = findViewById(R.id.et_phone2) as EditText
        etCommisionXc = findViewById(R.id.et_commisionXc) as EditText
        etCommisionTc = findViewById(R.id.et_commisionTc) as EditText
        etCommisionFz = findViewById(R.id.et_commisionFz) as EditText
        etCommisionSy = findViewById(R.id.et_commisionSy) as EditText
        spDispatchPattern = findViewById(R.id.spinner_dispatchPattern) as Spinner
        btnSave = findViewById(R.id.btn_save_setting) as Button

        spDispatchPattern.onItemSelectedListener = this
        btnSave.setOnClickListener {
            checkView()
        }
    }

    private fun checkView() {
        if (!TextUtil.isEmpty(etFleetName.text.toString()) &&
                !TextUtil.isEmpty(etFleetMobile.text.toString()) &&
                !TextUtil.isEmpty(etFleetNo.text.toString()) &&
                !TextUtil.isEmpty(etCommisionXc.text.toString()) &&
                !TextUtil.isEmpty(etCommisionTc.text.toString()) &&
                !TextUtil.isEmpty(etCommisionFz.text.toString()) &&
                !TextUtil.isEmpty(etCommisionSy.text.toString()) &&
                !TextUtil.isEmpty(orderType.toString()) &&
                !TextUtil.isEmpty(et_Phone.text.toString()) ||
                !TextUtil.isEmpty(et_Phone1.text.toString()) ||
                !TextUtil.isEmpty(et_Phone2.text.toString())) {
            presenter.updateOrgInfo(userNo, etFleetName.text.toString(), orderType.toString(), etFleetMobile.text.toString(),
                    etFleetNo.text.toString(), etCommisionFz.text.toString(),
                    etCommisionSy.text.toString(), etCommisionTc.text.toString(), etCommisionXc.text.toString(), et_Phone.text.toString(), et_Phone1.text.toString(), et_Phone2.text.toString())
        } else {
            ShowToast.showCenter(this, "信息填写不完整")
        }
    }

    @Subscribe
    fun loadData(any: Any) {
        if (any::class == OrganizationInfoEntity::class) {
            var data = any as OrganizationInfoEntity
            if (data.rspCode.equals("00")) {
                setData(data.organizations.get(0))
            } else if (data.rspCode.equals("101")) {
                ShowToast.showCenter(this, "账号异常,请重新登陆")
            } else {
                ShowToast.showCenter(this, data.rspDesc)
                finish()
            }
        } else if (any::class == UpdateOrgInfoEntity::class) {
            var data = any as UpdateOrgInfoEntity
            if (data.rspCode.equals("00")) {
                ShowToast.showCenter(this, data.rspDesc)
                presenter.listOrganizationInfo(userNo)
            } else {
                ShowToast.showCenter(this, data.rspDesc)
            }
        }
        progress.dismiss()

    }


    private fun setData(organizations: OrganizationInfoEntity.OrganizationsBean?) {
        etFleetName.setText(organizations!!.fleetName)
        etFleetNo.setText(organizations.fleetNo)
        etFleetMobile.setText(organizations.fleetMobile)
        et_Phone.setText(organizations.phone1)
        if (TextUtil.isEmpty(organizations.phone2)) {
            et_Phone1.setText(" ")
        } else {
            et_Phone1.setText(organizations.phone2)
        }
        if (TextUtil.isEmpty(organizations.phone3)) {
            et_Phone2.setText(" ")
        } else {
            et_Phone2.setText(organizations.phone3)
        }
        etCommisionXc.setText(organizations.orgcommissionXC)
        etCommisionTc.setText(organizations.orgcommissionTC)
        etCommisionFz.setText(organizations.orgcommissionFZ)
        etCommisionSy.setText(organizations.orgcommissionSY)

        type = resources.getStringArray(R.array.order)
        for (i in type.indices) {
            if (OrderModel.getKey(organizations.dispatchPattern.toInt()).equals(type[i])) {
                spDispatchPattern.setSelection(i)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        progress.show()
        presenter.listOrganizationInfo(userNo)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unregister(this)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent!!.id) {
            R.id.spinner_dispatchPattern -> {
                orderType = position + 1
            }
        }
    }
}