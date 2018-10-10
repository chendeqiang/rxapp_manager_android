package com.it.rxapp_manager_android.module.act

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.modle.LoginEntity
import com.it.rxapp_manager_android.module.base.ComponentHolder
import com.it.rxapp_manager_android.module.base.MyPresenter
import com.it.rxapp_manager_android.module.base.data.UserInfoPreferences
import com.it.rxapp_manager_android.utils.DevInfo
import com.it.rxapp_manager_android.utils.LogUtils
import com.it.rxapp_manager_android.utils.TextUtil
import com.it.rxapp_manager_android.widget.MyProgress
import com.it.rxapp_manager_android.widget.ShowToast
import com.squareup.otto.Subscribe
import javax.inject.Inject

/**
 * Created by deqiangchen on 2018/9/3 14:23
 */
class LoginActivity : BaseActivity() {

    private lateinit var etVCode: EditText
    private lateinit var etPhone: EditText
    private lateinit var btnLogin: Button

    private lateinit var progress: MyProgress
    @Inject
    lateinit var presenter: MyPresenter

    companion object {
        @JvmStatic
        fun startLoginActivity(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_x)
        LogUtils.d("classname", this::class.java.name)
        ComponentHolder.appComponent!!.inject(this)
        presenter.register(this)
        progress = MyProgress(this)
        initView()
    }

    private fun initView() {
        etPhone = findViewById(R.id.et_phone) as EditText
        etVCode = findViewById(R.id.et_vcode) as EditText
        btnLogin = findViewById(R.id.btn_sign) as Button

        if (!TextUtils.isEmpty(UserInfoPreferences.getInstance().driverNo)) {
            MainActivity.startMainActivity(this)
            finish()
        }

        btnLogin.setOnClickListener {
            progress.show()
//            var devToken: String = UserInfoPreferences.getInstance().devToken
            val devInfo: String = DevInfo.getInfo()
            presenter.login(etPhone.text.toString(), etVCode.text.toString(), 1, "devToken", devInfo)
        }
        etPhone.append(UserInfoPreferences.getInstance().mobile)
    }

    @Subscribe
    fun loadData(any: Any) {
        if (any::class == LoginEntity::class) {
            login(any)
        }
    }

    private fun login(any: Any) {
        progress.dismiss()
        val loginEntity: LoginEntity = any as LoginEntity
        if (loginEntity.rspCode.equals("00")) {
            if (!TextUtil.isEmpty(loginEntity.no)) {
                UserInfoPreferences.getInstance().driverNo = loginEntity.no
                UserInfoPreferences.getInstance().token = loginEntity.rxToken
                UserInfoPreferences.getInstance().mobile = etPhone.text.toString()
                MainActivity.startMainActivity(this)
                finish()
            } else {
                ShowToast.showCenter(this, "数据有误，请联系服务人员")
            }
        } else {
            ShowToast.showCenter(this, loginEntity.rspDesc)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unregister(this)
    }
}