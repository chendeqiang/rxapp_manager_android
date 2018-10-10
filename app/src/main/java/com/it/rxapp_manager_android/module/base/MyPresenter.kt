package com.it.rxapp_manager_android.module.base

import com.it.rxapp_manager_android.modle.*
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

    fun login(loginName: String, loginPassword: String, devType: Int, devToken: String, devInfo: String) {
        if (TextUtil.isEmpty(loginName)) {
            val data = LoginEntity()
            data.rspCode = "1000"
            data.rspDesc = "账号不能为空"
            mBs.post(data)
        }

        if (TextUtil.isEmpty(loginPassword)) {
            val data = LoginEntity()
            data.rspCode = "1000"
            data.rspDesc = "密码不能为空"
            mBs.post(data)
        }

        manger.login(loginName, loginPassword, devType, devToken, devInfo, object : Callback<LoginEntity> {
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

    fun changePassword(loginName: String, loginPassword: String, newpassword: String, devType: Int, devToken: String, devInfo: String) {
        if (TextUtil.isEmpty(loginName)) {
            val data = CommEntity()
            data.rspCode = "102"
            data.rspDesc = "账号不能为空"
            mBs.post(data)
        }

        if (TextUtil.isEmpty(loginPassword)) {
            val data = CommEntity()
            data.rspCode = "102"
            data.rspDesc = "密码不能为空"
            mBs.post(data)
        }
        if (TextUtil.isEmpty(newpassword)) {
            val data = CommEntity()
            data.rspCode = "102"
            data.rspDesc = "密码不能为空"
            mBs.post(data)
        }
        manger.changePassword(loginName, loginPassword, newpassword, devType, devToken, devInfo, object : Callback<CommEntity> {
            override fun onFailure(call: Call<CommEntity>, t: Throwable) {
                val data = CommEntity()
                data.rspCode = "1000"
                data.rspDesc = "网络连接失败"
                mBs.post(data)
                t.printStackTrace()
            }

            override fun onResponse(call: Call<CommEntity>, response: Response<CommEntity>) {
                if (response.body() != null) {
                    LogUtils.d("changePassword", response.body().toString())
                    mBs.post(response.body())
                }
            }
        })
    }

    fun listDriver(no: String, pageIndex: Int, pageCount: Int) {
        manger.listDriver(no, pageIndex, pageCount, object : Callback<ListDriverEntity> {
            override fun onResponse(call: Call<ListDriverEntity>, response: Response<ListDriverEntity>) {
                if (response.body() != null) {
                    LogUtils.d("listDriver", response.body().toString())
                    mBs.post(response.body())
                }
            }

            override fun onFailure(call: Call<ListDriverEntity>, t: Throwable) {
                val data = ListDriverEntity()
                data.rspCode = "1000"
                data.rspDesc = "网络连接失败"
                mBs.post(data)
                t.printStackTrace()
            }
        })
    }

    fun addDriver(orgId: String, driverName: String, mobile: String) {
        if (TextUtil.isEmpty(orgId)) {
            mBs.post("工号不能为空,请重新登录")
            return
        }
        manger.addDriver(orgId, driverName, mobile, object : Callback<AddDriverEntity> {
            override fun onResponse(call: Call<AddDriverEntity>, response: Response<AddDriverEntity>) {
                if (response.body() != null) {
                    LogUtils.d("addDriver", response.body().toString())
                    mBs.post(response.body())
                }
            }

            override fun onFailure(call: Call<AddDriverEntity>, t: Throwable) {
                val data = AddDriverEntity()
                data.rspCode = "1000"
                data.rspDesc = "网络连接失败"
                mBs.post(data)
                t.printStackTrace()
            }
        })
    }

    fun enableDriver(driverNo: String) {
        manger.enableDriver(driverNo, object : Callback<CommEntity> {
            override fun onResponse(call: Call<CommEntity>, response: Response<CommEntity>) {
                if (response.body() != null) {
                    LogUtils.d("enableDriver", response.body().toString())
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

    fun disableDriver(driverNo: String) {
        manger.disableDriver(driverNo, object : Callback<CommEntity> {
            override fun onResponse(call: Call<CommEntity>, response: Response<CommEntity>) {
                if (response.body() != null) {
                    LogUtils.d("disableDriver", response.body().toString())
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

    fun listCar(orgId: String, pageIndex: Int, pageCount: Int) {
        manger.listCar(orgId, pageIndex, pageCount, object : Callback<ListCarEntity> {
            override fun onResponse(call: Call<ListCarEntity>, response: Response<ListCarEntity>) {
                if (response.body() != null) {
                    LogUtils.d("listCar", response.body().toString())
                    mBs.post(response.body())
                }
            }

            override fun onFailure(call: Call<ListCarEntity>, t: Throwable) {
                val data = ListCarEntity()
                data.rspCode = "1000"
                data.rspDesc = "网络连接失败"
                mBs.post(data)
                t.printStackTrace()
            }
        })
    }

    fun listOrganizationInfo(orgId: String) {
        manger.listOrganizationInfo(orgId, object : Callback<OrganizationInfoEntity> {
            override fun onResponse(call: Call<OrganizationInfoEntity>, response: Response<OrganizationInfoEntity>) {
                if (response.body() != null) {
                    LogUtils.d("listOrganizationInfo", response.body().toString())
                    mBs.post(response.body())
                }
            }

            override fun onFailure(call: Call<OrganizationInfoEntity>, t: Throwable) {
                val data = ListCarEntity()
                data.rspCode = "1000"
                data.rspDesc = "网络连接失败"
                mBs.post(data)
                t.printStackTrace()
            }
        })
    }

    fun listRelation(orgId: String, pageIndex: Int, pageCount: Int) {
        manger.listRelation(orgId, pageIndex, pageCount, object : Callback<ListRelationEntity> {
            override fun onFailure(call: Call<ListRelationEntity>, t: Throwable) {
                val data = ListRelationEntity()
                data.rspCode = "4"
                data.rspDesc = "服务异常"
                mBs.post(data)
                t.printStackTrace()
            }

            override fun onResponse(call: Call<ListRelationEntity>, response: Response<ListRelationEntity>) {
                if (response.body() != null) {
                    LogUtils.d("listRelation", response.body().toString())
                    mBs.post(response.body())
                }
            }
        })
    }

    fun relation(orgId: String, carId: String, driver: String) {
        manger.relation(orgId, carId, driver, object : Callback<CommEntity> {
            override fun onFailure(call: Call<CommEntity>, t: Throwable) {
                val data = CommEntity()
                data.rspCode = "4"
                data.rspDesc = "服务异常"
                mBs.post(data)
                t.printStackTrace()
            }

            override fun onResponse(call: Call<CommEntity>, response: Response<CommEntity>) {
                if (response.body() != null) {
                    LogUtils.d("relation", response.body().toString())
                    mBs.post(response.body())
                }
            }
        })
    }

    fun addCar(orgId: String, carType: String, carNo: String) {

        if (TextUtil.isEmpty(carNo)) {
            val data = AddCarEntity()
            data.rspCode = "1000"
            data.rspDesc = "车牌号不能为空"
            mBs.post(data)
        }
        if (TextUtil.isEmpty(carType)) {
            val data = AddCarEntity()
            data.rspCode = "1000"
            data.rspDesc = "车型不能为空"
            mBs.post(data)
        }

        manger.addCar(orgId, carType, carNo, object : Callback<AddCarEntity> {
            override fun onResponse(call: Call<AddCarEntity>, response: Response<AddCarEntity>) {
                if (response.body() != null) {
                    LogUtils.d("addCar", response.body().toString())
                    mBs.post(response.body())
                }
            }

            override fun onFailure(call: Call<AddCarEntity>, t: Throwable) {
                val data = AddCarEntity()
                data.rspCode = "4"
                data.rspDesc = "服务异常"
                mBs.post(data)
                t.printStackTrace()
            }

        })
    }

//    fun listOrder(driverNo: String, flowStatus: Int, orderType: Int, pageIndex: Int, pageCount: Int) {
//        manger.listOrder(driverNo, flowStatus, orderType, pageIndex, pageCount,
//                object : Callback<ListOrderEntity> {
//                    override fun onResponse(call: Call<ListOrderEntity>, response: Response<ListOrderEntity>) {
//                        LogUtils.d("listOrder", "" + response.body() + "")
//                        if (response.body() != null) {
//                            mBs.post(response.body())
//                        }
//                    }
//
//                    override fun onFailure(call: Call<ListOrderEntity>, t: Throwable) {
//                        val data = ListOrderEntity()
//                        data.rspCode = "1000"
//                        data.rspDesc = "网络连接失败"
//                        mBs.post(data)
//                        t.printStackTrace()
//                    }
//                })
//    }

//    fun qryOrder(orderNo: String) {
//        manger.qryOrder(orderNo, object : Callback<OrderInfoEntity> {
//            override fun onResponse(call: Call<OrderInfoEntity>, response: Response<OrderInfoEntity>) {
//                LogUtils.d("OrderInfo", "" + response.body() + "")
//                if (response.body() != null) {
//                    mBs.post(response.body())
//                }
//            }
//
//            override fun onFailure(call: Call<OrderInfoEntity>, t: Throwable) {
//                val data = OrderInfoEntity()
//                data.rspCode = "1000"
//                data.rspDesc = "网络连接失败"
//                mBs.post(data)
//                t.printStackTrace()
//            }
//        })
//    }
}