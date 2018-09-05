package com.it.rxapp_manager_android.module.base

import com.it.rxapp_manager_android.modle.CommEntity
import com.it.rxapp_manager_android.modle.LoginEntity
import com.it.rxapp_manager_android.modle.LogoutEntity
import com.it.rxapp_manager_android.modle.UserinfoEntity
import com.it.rxapp_manager_android.utils.LogUtils
import com.it.rxapp_manager_android.utils.TextUtil
import com.squareup.otto.Bus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by deqiangchen on 2018/7/31 14:32
 */
class MyPresenter(private val mBs: Bus, private val manger: MyManger) {
    fun register(any: Any) {
        mBs.register(any)
    }

    fun unregister(any: Any) {
        mBs.unregister(any)
    }

    fun getVcode(mobile: String) {
        if (!TextUtil.isMobileNO(mobile)) {
            val data = CommEntity()
            data.rspCode = "1000"
            data.rspDesc = "请输入正确号码"
            mBs.post(data)
            return
        }
        manger.getVcode(mobile, object : Callback<CommEntity> {
            override fun onResponse(call: Call<CommEntity>, response: Response<CommEntity>) {
                if (response.body() != null) {
                    LogUtils.d("getVcode", response.body().toString())
                    mBs.post(response.body())
                }
            }

            override fun onFailure(call: Call<CommEntity>, t: Throwable) {
                val data = CommEntity()
                data.rspCode = "1000"
                data.rspDesc = "网络连接失败"
                mBs.post(data)
                t.printStackTrace()
            }
        })
    }

    fun login(mobile: String, vcode: String, orgName: String, devType: Int, devToken: String, devInfo: String) {
        if (!TextUtil.isMobileNO(mobile)) {
            val data = LoginEntity()
            data.rspCode = "1000"
            data.rspDesc = "请输入正确手机号"
            mBs.post(data)
        }

        if (TextUtil.isEmpty(orgName)) {
            val data = LoginEntity()
            data.rspCode = "1000"
            data.rspDesc = "车队名不能为空"
            mBs.post(data)
        }
        if (TextUtil.isEmpty(vcode)) {
            val data = LoginEntity()
            data.rspCode = "1000"
            data.rspDesc = "验证码不能为空"
            mBs.post(data)
        }

        manger.login(mobile, vcode, orgName, devType, devToken, devInfo, object : Callback<LoginEntity> {
            override fun onResponse(call: Call<LoginEntity>, response: Response<LoginEntity>) {
                if (response.body() != null) {
                    LogUtils.d("login", response.body().toString())
                    mBs.post(response.body())
                }
            }

            override fun onFailure(call: Call<LoginEntity>, t: Throwable) {
                val data = LoginEntity()
                data.rspCode = "1000"
                data.rspDesc = "网络连接失败"
                mBs.post(data)
                t.printStackTrace()
            }
        })
    }

    fun logout(no: String) {
        manger.logout(no, object : Callback<LogoutEntity> {
            override fun onResponse(call: Call<LogoutEntity>, response: Response<LogoutEntity>) {
                if (response.body() != null) {
                    LogUtils.d("logout", response.body().toString())
                    mBs.post(response.body())
                }
            }

            override fun onFailure(call: Call<LogoutEntity>, t: Throwable) {
                val data = LogoutEntity()
                data.rspCode = "1000"
                data.rspDesc = "网络连接失败"
                mBs.post(data)
                t.printStackTrace()
            }
        })
    }

    fun getInfo(no: String) {
        if (TextUtil.isEmpty(no)) {
            mBs.post("工号不能为空,请重新登录")
            return
        }
        manger.getInfo(no, object : Callback<UserinfoEntity> {
            override fun onResponse(call: Call<UserinfoEntity>, response: Response<UserinfoEntity>) {
                if (response.body() != null) {
                    LogUtils.d("getInfo", response.body().toString())
                    mBs.post(response.body())
                }
            }

            override fun onFailure(call: Call<UserinfoEntity>, t: Throwable) {
                val data = UserinfoEntity()
                data.rspCode = "1000"
                data.rspDesc = "网络连接失败"
                mBs.post(data)
                t.printStackTrace()
            }
        })
    }
}