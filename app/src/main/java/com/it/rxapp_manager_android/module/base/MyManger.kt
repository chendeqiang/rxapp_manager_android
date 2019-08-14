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

    fun listDriver(no: String, pageIndex: Int, pageCount: Int, driverName: String,mobile:String, callback: retrofit2.Callback<ListDriverEntity>) {
        val map = TreeMap<String, Any>()
        map.put("orgId", no)
        map.put("pageIndex", pageIndex)
        map.put("pageCount", pageCount)
        map.put("driverName", driverName)
        map.put("mobile", mobile)
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

    fun listCar(orgId: String, pageIndex: Int, pageCount: Int, carNo: String, carBrand: String, callback: retrofit2.Callback<ListCarEntity>) {
        val map = TreeMap<String, Any>()
        map.put("orgId", orgId)
        map.put("pageIndex", pageIndex)
        map.put("pageCount", pageCount)
        map.put("carNo", carNo)
        map.put("carBrand", carBrand)
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

    fun updateOrgInfo(orgId: String, fleetName: String, dispatchPattern: String, fleetMobile: String, fleetNo: String, orgcommissionFZ: String, orgcommissionSY: String, orgcommissionTC: String, orgcommissionXC: String, phone1: String, phone2: String, phone3: String, callback: retrofit2.Callback<UpdateOrgInfoEntity>) {
        val map = TreeMap<String, Any>()
        map.put("orgId", orgId)
        map.put("fleetName", fleetName)
        map.put("dispatchPattern", dispatchPattern)
        map.put("fleetMobile", fleetMobile)
        map.put("fleetNo", fleetNo)
        map.put("orgcommissionFZ", orgcommissionFZ)
        map.put("orgcommissionSY", orgcommissionSY)
        map.put("orgcommissionTC", orgcommissionTC)
        map.put("orgcommissionXC", orgcommissionXC)
        map.put("phone1", phone1)
        map.put("phone2", phone2)
        map.put("phone3", phone3)
        val headers = HeaderUtil.getHeaders(map)
        LogUtils.d("updateOrgInfo 参数", map.toString())
        LogUtils.d("headers", headers.toString())
        apiService.updateOrgInfo(map, headers).enqueue(callback)
    }

    fun listRelation(orgId: String, pageIndex: Int, pageCount: Int, driverName: String,mobile: String, callback: retrofit2.Callback<ListRelationEntity>) {
        val map = TreeMap<String, Any>()
        map.put("orgId", orgId)
        map.put("pageIndex", pageIndex)
        map.put("pageCount", pageCount)
        map.put("driverName", driverName)
        map.put("mobile", mobile)
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

    fun addCar(orgId: String, carType: String, carNo: String,ccolor:String, callback: retrofit2.Callback<AddCarEntity>) {
        val map = TreeMap<String, Any>()
        map.put("orgId", orgId)
        map.put("carType", carType)
        map.put("carNo", carNo)
        map.put("ccolor", ccolor)
        val headers = HeaderUtil.getHeaders(map)
        LogUtils.d("addCar 参数", map.toString())
        LogUtils.d("headers", headers.toString())
        apiService.addCar(map, headers).enqueue(callback)
    }

    fun editCar(carID: String, carType: String,ccolor: String, callback: Callback<CommEntity>) {
        val map = TreeMap<String, Any>()
        map.put("carID", carID)
        map.put("carType", carType)
        map.put("ccolor", ccolor)
        val headers = HeaderUtil.getHeaders(map)
        LogUtils.d("editCar 参数", map.toString())
        LogUtils.d("headers", headers.toString())
        apiService.editCar(map, headers).enqueue(callback)
    }

    fun listOrder(orgId: String, orderStatus: String, orderType: String, pageIndex: Int, pageCount: Int, sortType: String, callback: retrofit2.Callback<ListOrderEntity>) {
        val map = TreeMap<String, Any>()
        map.put("orgId", orgId)
        map.put("orderStatus", orderStatus)
        if (orderType.toInt() >= 0 && orderType.toInt() < 10) {
            map.put("orderType", orderType)
        } else {
            map.put("orderType", "")
        }
        map.put("pageIndex", pageIndex)
        map.put("pageCount", pageCount)
        map.put("sortType", sortType)
        val headers = HeaderUtil.getHeaders(map)
        LogUtils.d("listOrder 参数", map.toString())
        LogUtils.d("headers", headers.toString())
        apiService.listOrder(map, headers).enqueue(callback)

    }

    fun pubOrder(orgId: String, orderNo: String, driverNo: String, price: String, callback: Callback<CommEntity>) {
        val map = TreeMap<String, Any>()
        map.put("orgId", orgId)
        map.put("orderNo", orderNo)
        map.put("driverNo", driverNo)
        map.put("price", price)
        val headers = HeaderUtil.getHeaders(map)
        LogUtils.d("pubOrder 参数", map.toString())
        LogUtils.d("headers", headers.toString())
        apiService.pubOrder(map, headers).enqueue(callback)
    }

    fun getOrderCar(orgId: String, orderNo: String, pageIndex: Int, pageCount: Int, mobile: String, driverName: String, callback: retrofit2.Callback<ListDriversEntity>) {
        val map = TreeMap<String, Any>()
        map.put("orgId", orgId)
        map.put("orderNo", orderNo)
        map.put("pageIndex", pageIndex)
        map.put("pageCount", pageCount)
        map.put("mobile", mobile)
        map.put("driverName", driverName)
        val headers = HeaderUtil.getHeaders(map)
        LogUtils.d("getOrderCar 参数", map.toString())
        LogUtils.d("headers", headers.toString())
        apiService.getOrderCar(map, headers).enqueue(callback)
    }

    fun remarkPush(orderNo: String, driverNo: String, remark: String, callback: Callback<CommEntity>) {
        val map = TreeMap<String, Any>()
        map.put("orderNo", orderNo)
        map.put("driverNo", driverNo)
        map.put("remark", remark)
        val headers = HeaderUtil.getHeaders(map)
        LogUtils.d("remarkPush 参数", map.toString())
        LogUtils.d("headers", headers.toString())
        apiService.remarkPush(map, headers).enqueue(callback)
    }

    fun returnToOrderPool(orgId: String, orderNo: String, callback: Callback<CommEntity>) {
        val map = TreeMap<String, Any>()
        map.put("orgId", orgId)
        map.put("orderNo", orderNo)
        val headers = HeaderUtil.getHeaders(map)
        LogUtils.d("returnToOrderPool 参数", map.toString())
        LogUtils.d("headers", headers.toString())
        apiService.returnToOrderPool(map, headers).enqueue(callback)
    }

    fun checkVersion(key: String, callback: retrofit2.Callback<CheckVersionEntity>) {
        val map = TreeMap<String, Any>()
        map.put("key", key)
        val headers = HeaderUtil.getHeaders(map)
        LogUtils.d("checkVersion 参数", map.toString())
        LogUtils.d("headers", headers.toString())
        apiService.checkVersion(map, headers).enqueue(callback)

    }

    fun listColor(callback: retrofit2.Callback<ListColorEntity>){
        apiService.listColor().enqueue(callback)
    }


    fun listPriceRule(orgId: String, pageIndex: Int, pageCount: Int,productTypeReq:String, callback: retrofit2.Callback<ListValuationsEntity>) {
        val map = TreeMap<String, Any>()
        map.put("orgId", orgId)
        map.put("pageIndex", pageIndex)
        map.put("pageCount", pageCount)
        map.put("productTypeReq", productTypeReq)
        val headers = HeaderUtil.getHeaders(map)
        LogUtils.d("listPriceRule 参数", map.toString())
        LogUtils.d("headers", headers.toString())
        apiService.listPriceRule(map, headers).enqueue(callback)
    }

    //比价
    fun listpricerulecompetebycmainid(cmainid: String, pageIndex: Int, pageCount: Int, callback: retrofit2.Callback<ListValuationsEntity>){
        val map = TreeMap<String, Any>()
        map.put("cmainid", cmainid)
        map.put("pageIndex", pageIndex)
        map.put("pageCount", pageCount)
        val headers = HeaderUtil.getHeaders(map)
        LogUtils.d("listpricerulecompetebycmainid 参数", map.toString())
        LogUtils.d("headers", headers.toString())
        apiService.listpricerulecompetebycmainid(map, headers).enqueue(callback)
    }


    fun listBasicAuthCity(orgId: String, startCity: String, endCity: String, lineType: String, carType: String, pageIndex: Int, pageCount: Int, callback: retrofit2.Callback<ListBasicAuthCityEntity>) {
        val map = TreeMap<String, Any>()
        map.put("orgId", orgId)
        map.put("startCity", startCity)
        map.put("endCity", endCity)
        map.put("lineType", lineType)
        map.put("carType", carType)
        map.put("pageIndex", pageIndex)
        map.put("pageCount", pageCount)
        val headers = HeaderUtil.getHeaders(map)
        LogUtils.d("listBasicAuthCity 参数", map.toString())
        LogUtils.d("headers", headers.toString())
        apiService.listBasicAuthCity(map, headers).enqueue(callback)
    }

    fun addPriceRule(orgId: String, operater:String,startPrice: String, startKm: String, orgName: String, outStartKmPrice: String, productType: String,usetype:String,
                     startCity: String, startCityName: String, endCity: String, endCityName: String, authCityId: String, authCityName: String,
                     incityCartype: String, carType: String, carTypeName: String, online: String, lineType: String, lineTypeName: String,
                     isPush: String, isInquire: String, longDistanceKm: String, longDistanceKmPrice: String, superLongDistanceKm: String,
                     superLongDistanceKmPrice: String,maxdistancekm:String,maxdistancekmprice:String, otherPrice: String, nightFee: String, nightBegin: String, nightEnd: String, mondayRate: String,
                     tuesdayRate: String, wednesdayRate: String, thursdayRate: String, fridayRate: String, saturdayRate: String, sundayRate: String,
                     publicholidaysRate: String, callback: Callback<CreateValuationEntity>) {
        val map = TreeMap<String, Any>()
        map.put("orgId", orgId)
        map.put("operater",operater)
        map.put("startPrice", startPrice)
        map.put("startKm", startKm)
        map.put("orgName", orgName)
        map.put("outStartKmPrice", outStartKmPrice)
        map.put("productType", productType)
        map.put("Usetype", usetype)
        map.put("startCity", startCity)
        map.put("startCityName", startCityName)
        map.put("endCity", endCity)
        map.put("endCityName", endCityName)
        map.put("authCityId", authCityId)
        map.put("authCityName", authCityName)
        map.put("incityCartype", incityCartype)
        map.put("carType", carType)
        map.put("carTypeName", carTypeName)
        map.put("online", online)
        map.put("lineType", lineType)
        map.put("lineTypeName", lineTypeName)
        map.put("isPush", isPush)
        map.put("isInquire", isInquire)
        map.put("longDistanceKm", longDistanceKm)
        map.put("longDistanceKmPrice", longDistanceKmPrice)
        map.put("superLongDistanceKm", superLongDistanceKm)
        map.put("superLongDistanceKmPrice", superLongDistanceKmPrice)
        map.put("maxdistancekm", maxdistancekm)
        map.put("maxdistancekmprice", maxdistancekmprice)
        map.put("otherPrice", otherPrice)
        map.put("nightFee", nightFee)
        map.put("nightBegin", nightBegin)
        map.put("nightEnd", nightEnd)
        map.put("mondayRate", mondayRate)
        map.put("tuesdayRate", tuesdayRate)
        map.put("wednesdayRate", wednesdayRate)
        map.put("thursdayRate", thursdayRate)
        map.put("fridayRate", fridayRate)
        map.put("saturdayRate", saturdayRate)
        map.put("sundayRate", sundayRate)
        map.put("publicholidaysRate", publicholidaysRate)
        val headers = HeaderUtil.getHeaders(map)
        LogUtils.d("addPriceRule 参数", map.toString())
        LogUtils.d("headers", headers.toString())
        apiService.addPriceRule(map, headers).enqueue(callback)
    }

    fun updatePriceRule(orgId: String,operater: String, productNo: String, startPrice: String, startKm: String, outStartKmPrice: String, longDistanceKm: String, longDistanceKmPrice: String, superLongDistanceKm: String,
                        superLongDistanceKmPrice: String,maxdistancekm:String,maxdistancekmprice:String,otherPrice: String, nightFee: String, nightBegin: String, nightEnd: String, mondayRate: String,
                        tuesdayRate: String, wednesdayRate: String, thursdayRate: String, fridayRate: String, saturdayRate: String, sundayRate: String,
                        publicholidaysRate: String, callback: Callback<UpdateValuationEntity>) {
        val map = TreeMap<String, Any>()
        map.put("orgId", orgId)
        map.put("operater",operater)
        map.put("productNo", productNo)
        map.put("startPrice", startPrice)
        map.put("startKm", startKm)
        map.put("outStartKmPrice", outStartKmPrice)
        map.put("longDistanceKm", longDistanceKm)
        map.put("longDistanceKmPrice", longDistanceKmPrice)
        map.put("superLongDistanceKm", superLongDistanceKm)
        map.put("superLongDistanceKmPrice", superLongDistanceKmPrice)
        map.put("maxdistancekm", maxdistancekm)
        map.put("maxdistancekmprice", maxdistancekmprice)
        map.put("otherPrice", otherPrice)
        map.put("nightFee", nightFee)
        map.put("nightBegin", nightBegin)
        map.put("nightEnd", nightEnd)
        map.put("mondayRate", mondayRate)
        map.put("tuesdayRate", tuesdayRate)
        map.put("wednesdayRate", wednesdayRate)
        map.put("thursdayRate", thursdayRate)
        map.put("fridayRate", fridayRate)
        map.put("saturdayRate", saturdayRate)
        map.put("sundayRate", sundayRate)
        map.put("publicholidaysRate", publicholidaysRate)
        val headers = HeaderUtil.getHeaders(map)
        LogUtils.d("updatePriceRule 参数", map.toString())
        LogUtils.d("headers", headers.toString())
        apiService.updatePriceRule(map, headers).enqueue(callback)
    }
}