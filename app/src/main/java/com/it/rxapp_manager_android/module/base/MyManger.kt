package com.it.rxapp_manager_android.module.base

import com.it.rxapp_manager_android.modle.*
import com.it.rxapp_manager_android.utils.LogUtils
import retrofit2.Callback
import java.util.*

/**
 * Created by deqiangchen on 2018/7/31 14:31
 */
class MyManger(val apiService: ApiService) {

    fun login(loginName: String, loginPassword: String, devType: Int, devToken: String, devInfo: String, callback: retrofit2.Callback<LoginEntity>) {
        val map = TreeMap<String, Any>()
        map.put("loginName", loginName)
        map.put("loginPassword", loginPassword)
        map.put("devType", devType)
        map.put("devToken", devToken)
        map.put("devInfo", devInfo)
        val headers = HeaderUtil.getHeaders(map)
        LogUtils.d("login 参数", map.toString())
        LogUtils.d("headers", headers.toString())
        apiService.login(map, headers).enqueue(callback)
    }

    fun changePassword(loginName: String, loginPassword: String, newpassword: String, devType: Int, devToken: String, devInfo: String, callback: Callback<CommEntity>) {
        val map = TreeMap<String, Any>()
        map.put("loginName", loginName)
        map.put("loginPassword", loginPassword)
        map.put("newpassword", newpassword)
        map.put("devType", devType)
        map.put("devToken", devToken)
        map.put("devInfo", devInfo)
        val headers = HeaderUtil.getHeaders(map)
        LogUtils.d("changePassword 参数", map.toString())
        LogUtils.d("headers", headers.toString())
        apiService.changePassword(map, headers).enqueue(callback)
    }

    fun listDriver(no: String, pageIndex: Int, pageCount: Int, callback: retrofit2.Callback<ListDriverEntity>) {
        val map = TreeMap<String, Any>()
        map.put("orgId", no)
        map.put("pageIndex", pageIndex)
        map.put("pageCount", pageCount)
        val headers = HeaderUtil.getHeaders(map)
        LogUtils.d("listDriver 参数", map.toString())
        LogUtils.d("headers", headers.toString())
        apiService.listDriver(map, headers).enqueue(callback)

    }

    fun addDriver(no: String, driverName: String, mobile: String, callback: retrofit2.Callback<AddDriverEntity>) {
        val map = TreeMap<String, Any>()
        map.put("orgId", no)
        map.put("driverName", driverName)
        map.put("mobile", mobile)
        val headers = HeaderUtil.getHeaders(map)
        LogUtils.d("addDriver 参数", map.toString())
        LogUtils.d("headers", headers.toString())
        apiService.addDriver(map, headers).enqueue(callback)

    }

    fun enableDriver(driverNo: String, callback: retrofit2.Callback<CommEntity>) {
        val map = TreeMap<String, Any>()
        map.put("driverNo", driverNo)
        val headers = HeaderUtil.getHeaders(map)
        LogUtils.d("enableDriver 参数", map.toString())
        LogUtils.d("headers", headers.toString())
        apiService.enableDriver(map, headers).enqueue(callback)

    }

    fun disableDriver(driverNo: String, callback: retrofit2.Callback<CommEntity>) {
        val map = TreeMap<String, Any>()
        map.put("driverNo", driverNo)
        val headers = HeaderUtil.getHeaders(map)
        LogUtils.d("disableDriver 参数", map.toString())
        LogUtils.d("headers", headers.toString())
        apiService.disableDriver(map, headers).enqueue(callback)

    }

    fun listCar(orgId: String, pageIndex: Int, pageCount: Int, callback: retrofit2.Callback<ListCarEntity>) {
        val map = TreeMap<String, Any>()
        map.put("orgId", orgId)
        map.put("pageIndex", pageIndex)
        map.put("pageCount", pageCount)
        val headers = HeaderUtil.getHeaders(map)
        LogUtils.d("listCar 参数", map.toString())
        LogUtils.d("headers", headers.toString())
        apiService.listCar(map, headers).enqueue(callback)

    }

    fun listOrganizationInfo(orgId: String, callback: retrofit2.Callback<OrganizationInfoEntity>) {
        val map = TreeMap<String, Any>()
        map.put("orgId", orgId)
        val headers = HeaderUtil.getHeaders(map)
        LogUtils.d("listOrganizationInfo 参数", map.toString())
        LogUtils.d("headers", headers.toString())
        apiService.listOrganizationInfo(map, headers).enqueue(callback)

    }

    fun listRelation(orgId: String, pageIndex: Int, pageCount: Int, callback: retrofit2.Callback<ListRelationEntity>) {
        val map = TreeMap<String, Any>()
        map.put("orgId", orgId)
        map.put("pageIndex", pageIndex)
        map.put("pageCount", pageCount)
        val headers = HeaderUtil.getHeaders(map)
        LogUtils.d("listRelation 参数", map.toString())
        LogUtils.d("headers", headers.toString())
        apiService.listRelation(map, headers).enqueue(callback)

    }

    fun relation(orgId: String, carID: String, driver: String, callback: retrofit2.Callback<CommEntity>) {
        val map = TreeMap<String, Any>()
        map.put("orgId", orgId)
        map.put("carID", carID)
        map.put("driver", driver)
        val headers = HeaderUtil.getHeaders(map)
        LogUtils.d("relation 参数", map.toString())
        LogUtils.d("headers", headers.toString())
        apiService.relation(map, headers).enqueue(callback)

    }

    fun addCar(orgId: String, carType: String, carNo: String, callback: retrofit2.Callback<AddCarEntity>) {
        val map = TreeMap<String, Any>()
        map.put("orgId", orgId)
        map.put("carType", carType)
        map.put("carNo", carNo)
        val headers = HeaderUtil.getHeaders(map)
        LogUtils.d("addCar 参数", map.toString())
        LogUtils.d("headers", headers.toString())
        apiService.addCar(map, headers).enqueue(callback)
    }

//    fun listOrder(driverNo: String, flowStatus: Int, orderType: Int, pageIndex: Int, pageCount: Int, callback: retrofit2.Callback<ListOrderEntity>) {
//        val map = TreeMap<String, Any>()
//        map.put("driverNo", driverNo)
//        map.put("flowStatus", flowStatus)
//        if (orderType >= 1) {
//            map.put("orderType", orderType)
//        }
//        map.put("pageIndex", pageIndex)
//        map.put("pageCount", pageCount)
//        val headers = HeaderUtil.getHeaders(map)
//        LogUtils.d("listDriverOrder 参数", map.toString())
//        LogUtils.d("headers", headers.toString())
//        apiService.listOrder(map, headers).enqueue(callback)
//
//    }
//
//    fun qryOrder(orderNo: String, callback: retrofit2.Callback<OrderInfoEntity>) {
//        val map = TreeMap<String, Any>()
//        map.put("orderNo", orderNo)
//        val headers = HeaderUtil.getHeaders(map)
//        LogUtils.d("qryOrder 参数", map.toString())
//        LogUtils.d("headers", headers.toString())
//        apiService.qryOrder(map, headers).enqueue(callback)
//
//    }
}