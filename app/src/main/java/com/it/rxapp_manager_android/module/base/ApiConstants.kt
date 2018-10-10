package com.it.rxapp_manager_android.module.base

/**
 * Created by deqiangchen on 2018/7/31 14:26
 */
object ApiConstants {

    const val sign = "Rx-Sign"
    const val token = "Rx-Token"
    const val version = "Rx-Vern"
    const val ip = "http://101.37.202.182:8018/"//

    const val login = "usr/fleet/login"//登录
    const val changepassword = "usr/fleet/changepassword"//密码修改
    const val listdriver = "usr/fleet/listdriver"//司机列表
    const val adddriver = "usr/fleet/adddriver"//新增司机
    const val enabledriver = "usr/fleet/enabledriver"//启用司机
    const val disabledriver = "usr/fleet/disabledriver"//禁用司机
    const val listcar = "usr/fleet/listcar"//车辆列表
    const val addcar = "usr/fleet/addcar"//新增车辆
    const val listorganizationinfo = "usr/fleet/listorganizationinfo"//企业信息
    const val listrelation = "usr/fleet/listrelation"//关系列表信息
    const val relation = "usr/fleet/relation"//维护关系列表
    const val searchCar = "http://www.mxingo.com/jsondata/appcartype.json"//搜索车辆

}