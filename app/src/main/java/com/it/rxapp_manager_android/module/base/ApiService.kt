package com.it.rxapp_manager_android.module.base

import com.it.rxapp_manager_android.modle.*
import retrofit2.Call
import retrofit2.http.*
import java.util.*

/**
 * Created by deqiangchen on 2018/9/3 14:49
 */
interface ApiService {

    @FormUrlEncoded
    @POST(ApiConstants.login)
    fun login(@FieldMap map: TreeMap<String, Any>, @HeaderMap headers: Map<String, String>): Call<LoginEntity>

    @FormUrlEncoded
    @POST(ApiConstants.changepassword)
    fun changePassword(@FieldMap map: TreeMap<String, Any>, @HeaderMap headers: Map<String, String>): Call<CommEntity>

    @FormUrlEncoded
    @POST(ApiConstants.listdriver)
    fun listDriver(@FieldMap map: TreeMap<String, Any>, @HeaderMap headers: Map<String, String>): Call<ListDriverEntity>

    @FormUrlEncoded
    @POST(ApiConstants.adddriver)
    fun addDriver(@FieldMap map: TreeMap<String, Any>, @HeaderMap headers: Map<String, String>): Call<AddDriverEntity>

    @FormUrlEncoded
    @POST(ApiConstants.enabledriver)
    fun enableDriver(@FieldMap map: TreeMap<String, Any>, @HeaderMap headers: Map<String, String>): Call<CommEntity>

    @FormUrlEncoded
    @POST(ApiConstants.disabledriver)
    fun disableDriver(@FieldMap map: TreeMap<String, Any>, @HeaderMap headers: Map<String, String>): Call<CommEntity>

    @FormUrlEncoded
    @POST(ApiConstants.listcar)
    fun listCar(@FieldMap map: TreeMap<String, Any>, @HeaderMap headers: Map<String, String>): Call<ListCarEntity>

    @FormUrlEncoded
    @POST(ApiConstants.listorganizationinfo)
    fun listOrganizationInfo(@FieldMap map: TreeMap<String, Any>, @HeaderMap headers: Map<String, String>): Call<OrganizationInfoEntity>

    @FormUrlEncoded
    @POST(ApiConstants.updateorginfo)
    fun updateOrgInfo(@FieldMap map: TreeMap<String, Any>, @HeaderMap headers: Map<String, String>): Call<UpdateOrgInfoEntity>

    @FormUrlEncoded
    @POST(ApiConstants.listrelation)
    fun listRelation(@FieldMap map: TreeMap<String, Any>, @HeaderMap headers: Map<String, String>): Call<ListRelationEntity>

    @FormUrlEncoded
    @POST(ApiConstants.relation)
    fun relation(@FieldMap map: TreeMap<String, Any>, @HeaderMap headers: Map<String, String>): Call<CommEntity>

    @FormUrlEncoded
    @POST(ApiConstants.addcar)
    fun addCar(@FieldMap map: TreeMap<String, Any>, @HeaderMap headers: Map<String, String>): Call<AddCarEntity>

    @FormUrlEncoded
    @POST(ApiConstants.editcar)
    fun editCar(@FieldMap map: TreeMap<String, Any>, @HeaderMap headers: Map<String, String>): Call<CommEntity>

    @FormUrlEncoded
    @POST(ApiConstants.listorder)
    fun listOrder(@FieldMap map: TreeMap<String, Any>, @HeaderMap headers: Map<String, String>): Call<ListOrderEntity>

    @FormUrlEncoded
    @POST(ApiConstants.getordercar)
    fun getOrderCar(@FieldMap map: TreeMap<String, Any>, @HeaderMap headers: Map<String, String>): Call<ListDriversEntity>

    @FormUrlEncoded
    @POST(ApiConstants.puborder)
    fun pubOrder(@FieldMap map: TreeMap<String, Any>, @HeaderMap headers: Map<String, String>): Call<CommEntity>

    @FormUrlEncoded
    @POST(ApiConstants.remarkpush)
    fun remarkPush(@FieldMap map: TreeMap<String, Any>, @HeaderMap headers: Map<String, String>): Call<CommEntity>

    @FormUrlEncoded
    @POST(ApiConstants.returntoorderpool)
    fun returnToOrderPool(@FieldMap map: TreeMap<String, Any>, @HeaderMap headers: Map<String, String>): Call<CommEntity>

    @FormUrlEncoded
    @POST(ApiConstants.listpricerule)
    fun listPriceRule(@FieldMap map: TreeMap<String, Any>, @HeaderMap headers: Map<String, String>): Call<ListValuationsEntity>

    @FormUrlEncoded
    @POST(ApiConstants.listbasicauthcity)
    fun listBasicAuthCity(@FieldMap map: TreeMap<String, Any>, @HeaderMap headers: Map<String, String>): Call<ListBasicAuthCityEntity>


}