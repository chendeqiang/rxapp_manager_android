package com.it.rxapp_manager_android.module.act

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.modle.CommEntity
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
    private lateinit var etCarTeam: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnGetVCode: Button

    private lateinit var progress: MyProgress
    @Inject
    lateinit var presenter: MyPresenter
    private var countDown: CountDownTimer = MyCountDownTimer()

    companion object {
        @JvmStatic
        fun startLoginActivity(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    inner class MyCountDownTimer : CountDownTimer(60000, 1000) {
        override fun onFinish() {
            btnGetVCode.isClickable = true
            btnGetVCode.setBackgroundResource(R.drawable.btn_red_box)
            btnGetVCode.setTextColor(ContextCompat.getColor(this@LoginActivity, R.color.text_color_red))
            btnGetVCode.text = "获取验证码"
        }

        override fun onTick(time: Long) {
            btnGetVCode.text = (time / 1000).toString() + "s"
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        LogUtils.d("classname", this::class.java.name)
        ComponentHolder.appComponent!!.inject(this)
        presenter.register(this)
        progress = MyProgress(this)
        initView()
    }

    private fun initView() {
        etCarTeam = findViewById(R.id.et_car_team) as EditText
        etPhone = findViewById(R.id.et_phone) as EditText
        etVCode = findViewById(R.id.et_vcode) as EditText
        btnLogin = findViewById(R.id.btn_sign) as Button
        btnGetVCode = findViewById(R.id.btn_get_vcode) as Button

        if (!TextUtils.isEmpty(UserInfoPreferences.getInstance().driverNo)) {
            MainActivity.startMainActivity(this)
            finish()
        }

        btnLogin.setOnClickListener {
            progress.show()
            var devType: String = UserInfoPreferences.getInstance().devToken
            val devInfo: String = DevInfo.getInfo()
            presenter.login(etPhone.text.toString(), etVCode.text.toString(), etCarTeam.text.toString(), 1, devType, devInfo)
        }
        btnGetVCode.setOnClickListener {
            countDown.start()
            btnGetVCode.isClickable = false
            btnGetVCode.setBackgroundResource(R.drawable.btn_gray_box)
            btnGetVCode.setTextColor(ContextCompat.getColor(this, R.color.text_color_gray))
            presenter.getVcode(etPhone.text.toString())
        }
        etCarTeam.append(UserInfoPreferences.getInstance().carTeam)
        etPhone.append(UserInfoPreferences.getInstance().mobile)
    }

    @Subscribe
    fun loadData(any: Any) {
        if (any::class == LoginEntity::class) {
            login(any)
        } else if (any::class == CommEntity::class) {
            getVcode(any)
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
                UserInfoPreferences.getInstance().carTeam = etCarTeam.text.toString()
                MainActivity.startMainActivity(this)
                finish()
            } else {
                ShowToast.showCenter(this, "数据有误，请联系服务人员")
            }
        } else {
            ShowToast.showCenter(this, loginEntity.rspDesc)
        }
    }

    private fun getVcode(any: Any) {
        val commEntity = any as CommEntity
        if (!commEntity.rspCode.equals("00")) {
            ShowToast.showCenter(this, commEntity.rspDesc)
        } else {
            ShowToast.showCenter(this, "发送成功")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unregister(this)
    }
}