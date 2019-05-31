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

    fun listDriver(no: String, pageIndex: Int, pageCount: Int, driverName: String, mobile: String) {
        manger.listDriver(no, pageIndex, pageCount, driverName, mobile, object : Callback<ListDriverEntity> {
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

    fun listCar(orgId: String, pageIndex: Int, pageCount: Int, carNo: String, carBrand: String) {
        manger.listCar(orgId, pageIndex, pageCount, carNo, carBrand, object : Callback<ListCarEntity> {
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

    fun updateOrgInfo(orgId: String, fleetName: String, dispatchPattern: String, fleetMobile: String, fleetNo: String, orgcommissionFZ: String, orgcommissionSY: String, orgcommissionTC: String, orgcommissionXC: String, phone1: String, phone2: String, phone3: String) {
        manger.updateOrgInfo(orgId, fleetName, dispatchPattern, fleetMobile, fleetNo, orgcommissionFZ, orgcommissionSY, orgcommissionTC, orgcommissionXC, phone1, phone2, phone3, object : Callback<UpdateOrgInfoEntity> {
            override fun onResponse(call: Call<UpdateOrgInfoEntity>, response: Response<UpdateOrgInfoEntity>) {
                if (response.body() != null) {
                    LogUtils.d("updateOrgInfo", response.body().toString())
                    mBs.post(response.body())
                }
            }

            override fun onFailure(call: Call<UpdateOrgInfoEntity>, t: Throwable) {
                val data = UpdateOrgInfoEntity()
                data.rspCode = "1000"
                data.rspDesc = "网络连接失败"
                mBs.post(data)
                t.printStackTrace()
            }
        })
    }

    fun listRelation(orgId: String, pageIndex: Int, pageCount: Int, driverName: String,mobile: String) {
        manger.listRelation(orgId, pageIndex, pageCount, driverName,mobile, object : Callback<ListRelationEntity> {
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

    fun editCar(carID: String, carType: String) {
        manger.editCar(carID, carType, object : Callback<CommEntity> {
            override fun onResponse(call: Call<CommEntity>, response: Response<CommEntity>) {
                LogUtils.d("editCar", "" + response.body() + "")
                if (response.body() != null) {
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

    fun listOrder(orgId: String, orderStatus: String, orderType: String, pageIndex: Int, pageCount: Int, sortType: String) {
        manger.listOrder(orgId, orderStatus, orderType, pageIndex, pageCount, sortType,
                object : Callback<ListOrderEntity> {
                    override fun onResponse(call: Call<ListOrderEntity>, response: Response<ListOrderEntity>) {
                        LogUtils.d("listOrder", "" + response.body() + "")
                        if (response.body() != null) {
                            mBs.post(response.body())
                        }
                    }

                    override fun onFailure(call: Call<ListOrderEntity>, t: Throwable) {
                        val data = ListOrderEntity()
                        data.rspCode = "1000"
                        data.rspDesc = "网络连接失败"
                        mBs.post(data)
                        t.printStackTrace()
                    }
                })
    }

    fun pubOrder(orgId: String, orderNo: String, driverNo: String, price: String) {
        manger.pubOrder(orgId, orderNo, driverNo, price, object : Callback<CommEntity> {
            override fun onFailure(call: Call<CommEntity>, t: Throwable) {
                val data = CommEntity()
                data.rspCode = "1000"
                data.rspDesc = "网络连接失败"
                mBs.post(data)
                t.printStackTrace()
            }

            override fun onResponse(call: Call<CommEntity>, response: Response<CommEntity>) {
                LogUtils.d("pubOrder", "" + response.body() + "")
                if (response.body() != null) {
                    mBs.post(response.body())
                }
            }
        })
    }

    fun getOrderCar(orgId: String, orderNo: String, pageIndex: Int, pageCount: Int, mobile: String, driverName: String) {
        manger.getOrderCar(orgId, orderNo, pageIndex, pageCount, mobile, driverName, object : Callback<ListDriversEntity> {
            override fun onResponse(call: Call<ListDriversEntity>, response: Response<ListDriversEntity>) {
                if (response.body() != null) {
                    LogUtils.d("getOrderCar", response.body().toString())
                    mBs.post(response.body())
                }
            }

            override fun onFailure(call: Call<ListDriversEntity>, t: Throwable) {
                val data = ListCarEntity()
                data.rspCode = "1000"
                data.rspDesc = "网络连接失败"
                mBs.post(data)
                t.printStackTrace()
            }
        })
    }

    fun remarkPush(orderNo: String, driverNo: String, remark: String) {
        manger.remarkPush(orderNo, driverNo, remark, object : Callback<CommEntity> {
            override fun onFailure(call: Call<CommEntity>, t: Throwable) {
                val data = CommEntity()
                data.rspCode = "1000"
                data.rspDesc = "网络连接失败"
                mBs.post(data)
                t.printStackTrace()
            }

            override fun onResponse(call: Call<CommEntity>, response: Response<CommEntity>) {
                LogUtils.d("remarkPush", "" + response.body() + "")
                if (response.body() != null) {
                    mBs.post(response.body())
                }
            }
        })
    }

    fun returnToOrderPool(orgId: String, orderNo: String) {
        manger.returnToOrderPool(orgId, orderNo, object : Callback<CommEntity> {
            override fun onFailure(call: Call<CommEntity>, t: Throwable) {
                val data = CommEntity()
                data.rspCode = "1000"
                data.rspDesc = "网络连接失败"
                mBs.post(data)
                t.printStackTrace()
            }

            override fun onResponse(call: Call<CommEntity>, response: Response<CommEntity>) {
                LogUtils.d("returnToOrderPool", "" + response.body() + "")
                if (response.body() != null) {
                    mBs.post(response.body())
                }
            }
        })
    }

    fun checkVersion(key: String) {
        manger.checkVersion(key, object : Callback<CheckVersionEntity> {
            override fun onResponse(call: Call<CheckVersionEntity>, response: Response<CheckVersionEntity>) {
                LogUtils.d("getDataDict", "" + response.body() + "")
                if (response.body() != null) {
                    mBs.post(response.body())
                }
            }

            override fun onFailure(call: Call<CheckVersionEntity>, t: Throwable) {
                val data = CheckVersionEntity()
                data.rspCode = "1000"
                data.rspDesc = "网络连接失败"
                mBs.post(data)
                t.printStackTrace()
            }
        })
    }

    fun listPriceRule(orgId: String, pageIndex: Int, pageCount: Int) {
        manger.listPriceRule(orgId, pageIndex, pageCount, object : Callback<ListValuationsEntity> {
            override fun onResponse(call: Call<ListValuationsEntity>, response: Response<ListValuationsEntity>) {
                LogUtils.d("listPriceRule", "" + response.body() + "")
                if (response.body() != null) {
                    mBs.post(response.body())
                }
            }

            override fun onFailure(call: Call<ListValuationsEntity>, t: Throwable) {
                val data = ListValuationsEntity()
                data.rspCode = "1000"
                data.rspDesc = "网络连接失败"
                mBs.post(data)
                t.printStackTrace()
            }
        })
    }

    fun listBasicAuthCity(orgId: String, startCity: String, endCity: String, lineType: String, carType: String, pageIndex: Int, pageCount: Int) {
        manger.listBasicAuthCity(orgId, startCity, endCity, lineType, carType, pageIndex, pageCount, object : Callback<ListBasicAuthCityEntity> {
            override fun onFailure(call: Call<ListBasicAuthCityEntity>, t: Throwable) {
                val data = ListBasicAuthCityEntity()
                data.rspCode = "1000"
                data.rspDesc = "网络连接失败"
                mBs.post(data)
                t.printStackTrace()
            }

            override fun onResponse(call: Call<ListBasicAuthCityEntity>, response: Response<ListBasicAuthCityEntity>) {
                LogUtils.d("listBasicAuthCity", "" + response.body() + "")
                if (response.body() != null) {
                    mBs.post(response.body())
                }
            }
        })
    }

    fun addPriceRule(orgId: String, startPrice: String, startKm: String, orgName: String, outStartKmPrice: String, productType: String,
                     startCity: String, startCityName: String, endCity: String, endCityName: String, authCityId: String, authCityName: String,
                     incityCartype: String, carType: String, carTypeName: String, online: String, lineType: String, lineTypeName: String,
                     isPush: String, isInquire: String, longDistanceKm: String, longDistanceKmPrice: String, superLongDistanceKm: String,
                     superLongDistanceKmPrice: String, otherPrice: String, nightFee: String, nightBegin: String, nightEnd: String, mondayRate: String,
                     tuesdayRate: String, wednesdayRate: String, thursdayRate: String, fridayRate: String, saturdayRate: String, sundayRate: String,
                     publicholidaysRate: String) {
        manger.addPriceRule(orgId, startPrice, startKm, orgName, outStartKmPrice, productType, startCity, startCityName, endCity, endCityName, authCityId, authCityName, incityCartype, carType, carTypeName, online, lineType, lineTypeName, isPush, isInquire, longDistanceKm, longDistanceKmPrice, superLongDistanceKm, superLongDistanceKmPrice, otherPrice, nightFee, nightBegin, nightEnd, mondayRate, tuesdayRate, wednesdayRate, thursdayRate, fridayRate, saturdayRate, sundayRate, publicholidaysRate, object : Callback<CreateValuationEntity> {
            override fun onResponse(call: Call<CreateValuationEntity>, response: Response<CreateValuationEntity>) {
                LogUtils.d("addPriceRule", "" + response.body() + "")
                if (response.body() != null) {
                    mBs.post(response.body())
                }
            }

            override fun onFailure(call: Call<CreateValuationEntity>, t: Throwable) {
                val data = CreateValuationEntity()
                data.rspCode = "1000"
                data.rspDesc = "网络连接失败"
                mBs.post(data)
                t.printStackTrace()
            }
        })
    }

    fun updatePriceRule(orgId: String, productNo: String, startPrice: String, startKm: String, outStartKmPrice: String, longDistanceKm: String, longDistanceKmPrice: String, superLongDistanceKm: String,
                        superLongDistanceKmPrice: String, otherPrice: String, nightFee: String, nightBegin: String, nightEnd: String, mondayRate: String,
                        tuesdayRate: String, wednesdayRate: String, thursdayRate: String, fridayRate: String, saturdayRate: String, sundayRate: String,
                        publicholidaysRate: String) {
        manger.updatePriceRule(orgId, productNo, startPrice, startKm, outStartKmPrice, longDistanceKm, longDistanceKmPrice, superLongDistanceKm, superLongDistanceKmPrice, otherPrice, nightFee, nightBegin, nightEnd, mondayRate, tuesdayRate, wednesdayRate, thursdayRate, fridayRate, saturdayRate, sundayRate, publicholidaysRate, object : Callback<UpdateValuationEntity> {
            override fun onResponse(call: Call<UpdateValuationEntity>, response: Response<UpdateValuationEntity>) {
                LogUtils.d("updatePriceRule", "" + response.body() + "")
                if (response.body() != null) {
                    mBs.post(response.body())
                }
            }

            override fun onFailure(call: Call<UpdateValuationEntity>, t: Throwable) {
                val data = UpdateValuationEntity()
                data.rspCode = "1000"
                data.rspDesc = "网络连接失败"
                mBs.post(data)
                t.printStackTrace()
            }
        })
    }
}