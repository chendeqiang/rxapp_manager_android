package com.it.rxapp_manager_android.module.base

import com.it.rxapp_manager_android.modle.CommEntity
import com.it.rxapp_manager_android.modle.LoginEntity
import com.it.rxapp_manager_android.modle.LogoutEntity
import com.it.rxapp_manager_android.modle.UserinfoEntity
import com.it.rxapp_manager_android.utils.LogUtils
import java.util.*

/**
 * Created by deqiangchen on 2018/7/31 14:31
 */
class MyManger(val apiService: ApiService) {

    fun getVcode(mobile: String, callback: retrofit2.Callback<CommEntity>) {
        val map = TreeMap<String, Any>()
        map.put("mobile", mobile)
        val headers = HeaderUtil.getHeaders(map)
        LogUtils.d("getVcode 参数", map.toString())
        LogUtils.d("headers", headers.toString())
        apiService.getVcode(map, headers).enqueue(callback)

    }

    fun login(mobile: String, vcode: String, orgName: String, devType: Int, devToken: String, devInfo: String, callback: retrofit2.Callback<LoginEntity>) {
        val map = TreeMap<String, Any>()
        map.put("mobile", mobile)
        map.put("vcode", vcode)
        map.put("orgName", orgName)
        map.put("devType", devType)
        map.put("devToken", devToken)
        map.put("devInfo", devInfo)
        val headers = HeaderUtil.getHeaders(map)
        LogUtils.d("login 参数", map.toString())
        LogUtils.d("headers", headers.toString())
        apiService.login(map, headers).enqueue(callback)
    }

    fun logout(no: String, callback: retrofit2.Callback<LogoutEntity>) {
        val map = TreeMap<String, Any>()
        map.put("driverNo", no)
        val headers = HeaderUtil.getHeaders(map)
        LogUtils.d("logot 参数", map.toString())
        LogUtils.d("headers", headers.toString())
        apiService.logout(map, headers).enqueue(callback)

    }

    fun getInfo(no: String, callback: retrofit2.Callback<UserinfoEntity>) {
        val map = TreeMap<String, Any>()
        map.put("driverNo", no)
        val headers = HeaderUtil.getHeaders(map)
        LogUtils.d("getInfo 参数", map.toString())
        LogUtils.d("headers", headers.toString())
        apiService.getInfo(map, headers).enqueue(callback)

    }
}