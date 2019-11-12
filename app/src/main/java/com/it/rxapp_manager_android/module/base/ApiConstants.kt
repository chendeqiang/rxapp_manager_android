package com.it.rxapp_manager_android.module.base

/**
 * Created by deqiangchen on 2018/7/31 14:26
 */
object ApiConstants {

    const val sign = "Rx-Sign"
    const val token = "Rx-Token"
    const val version = "Rx-Vern"
//    const val ip = "http://101.37.202.182:8018/"//测试库
    const val ip = "https://wycapi.mxingo.com:443/"//生产库

    const val login = "usr/fleet/login"//登录
    const val changepassword = "usr/fleet/changepassword"//密码修改
    const val listdriver = "usr/fleet/listdriver"//司机列表
    const val adddriver = "usr/fleet/adddriver"//新增司机
    const val enabledriver = "usr/fleet/enabledriver"//启用司机
    const val disabledriver = "usr/fleet/disabledriver"//禁用司机
    const val editdriver = "usr/fleet/editdriver"//编辑司机

    const val listcar = "usr/fleet/listcar"//车辆列表
    const val addcar = "usr/fleet/addcar"//新增车辆
    const val editcar = "usr/fleet/editcar"//新增车辆
    const val listorganizationinfo = "usr/fleet/listorganizationinfo"//企业信息
    const val updateorginfo = "usr/fleet/updateorginfo"//维护企业信息
    const val listrelation = "usr/fleet/listrelation"//关系列表信息
    const val relation = "usr/fleet/relation"//维护关系列表
    const val searchCar = "http://www.mxingo.com/jsondata/appcartype.json"//搜索车辆

    const val listorder = "usr/fleet/listorderinfo"//订单列表
    const val getordercar = "usr/fleet/getordercar"//获取可用司机
    const val puborder = "usr/fleet/puborder"//派单
    const val remarkpush = "usr/fleet/remarkpush"//添加备注
    const val returntoorderpool = "usr/fleet/returntoorderpool"//重回订单池
    const val listpricerule = "usr/fleet/listpricerule"//计价规则列表

    const val listpricerulecompete = "usr/fleet/listpricerulecompete"//计价规则列表

    const val updatepricerule = "usr/fleet/updatepricerulecompete"//更新计价规则

    const val listpricerulecompetebycmainid="usr/fleet/listpricerulecompetebycmainid"//比价
    const val addpricerule = "usr/fleet/addpricerule"//新增计价规则
    const val listbasicauthcity = "usr/fleet/listbasicauthcity"//产品授权列表

    const val checkVersion = "comm/checkversion"//检查更新

    const val listcarcolor="usr/fleet/listcarcolor"//获取车辆颜色列表
}