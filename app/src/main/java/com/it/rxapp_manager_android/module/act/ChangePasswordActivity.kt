package com.it.rxapp_manager_android.module.act

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.modle.CommEntity
import com.it.rxapp_manager_android.module.base.ComponentHolder
import com.it.rxapp_manager_android.module.base.MyPresenter
import com.it.rxapp_manager_android.module.base.data.UserInfoPreferences
import com.it.rxapp_manager_android.utils.DevInfo
import com.it.rxapp_manager_android.widget.MyProgress
import com.it.rxapp_manager_android.widget.ShowToast
import com.squareup.otto.Subscribe
import javax.inject.Inject

/**
 * Created by deqiangchen on 2018/10/9 10:13
 */
class ChangePasswordActivity : BaseActivity() {

    private lateinit var etUserName: EditText
    private lateinit var etOldPwd: EditText
    private lateinit var etNewPwd: EditText
    private lateinit var btnSv: Button

    private lateinit var progress: MyProgress
    @Inject
    lateinit var presenter: MyPresenter

    companion object {
        @JvmStatic
        fun startChangePasswordActivity(context: Context) {
            context.startActivity(Intent(context, ChangePasswordActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pwd)
        ComponentHolder.appComponent!!.inject(this)
        presenter.register(this)
        progress = MyProgress(this)
        initView()
    }

    private fun initView() {
        setToolbar(toolbar = findViewById(R.id.toolbar) as Toolbar)
        (findViewById(R.id.tv_toolbar_title) as TextView).text = "密码修改"

        etUserName = findViewById(R.id.et_user_name) as EditText
        etOldPwd = findViewById(R.id.et_old_pwd) as EditText
        etNewPwd = findViewById(R.id.et_new_pwd) as EditText
        btnSv = findViewById(R.id.btn_save) as Button

        btnSv.setOnClickListener {
            progress.show()
            val devInfo: String = DevInfo.getInfo()
            presenter.changePassword(etUserName.text.toString(), etOldPwd.text.toString(), etNewPwd.text.toString(), 1, "devToken", devInfo)
        }

        etUserName.append(UserInfoPreferences.getInstance().mobile)
    }

    @Subscribe
    fun loadData(any: Any) {
        if (any::class == CommEntity::class) {
            var data = any as CommEntity
            if (data.rspCode.equals("00")) {
                ShowToast.showBottom(this, "修改成功")
                MainActivity.startMainActivity(this)
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