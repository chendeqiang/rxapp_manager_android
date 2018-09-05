package com.it.rxapp_manager_android.module.base

import com.it.rxapp_manager_android.modle.CommEntity
import com.it.rxapp_manager_android.modle.LoginEntity
import com.it.rxapp_manager_android.modle.LogoutEntity
import com.it.rxapp_manager_android.modle.UserinfoEntity
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import java.util.*

/**
 * Created by deqiangchen on 2018/9/3 14:49
 */
interface ApiService {
    @FormUrlEncoded
    @POST(ApiConstants.getVcode)
    fun getVcode(@FieldMap map: TreeMap<String, Any>, @HeaderMap headers: Map<String, String>): Call<CommEntity>

    @FormUrlEncoded
    @POST(ApiConstants.login)
    fun login(@FieldMap map: TreeMap<String, Any>, @HeaderMap headers: Map<String, String>): Call<LoginEntity>

    @FormUrlEncoded
    @POST(ApiConstants.logout)
    fun logout(@FieldMap map: TreeMap<String, Any>, @HeaderMap headers: Map<String, String>): Call<LogoutEntity>

    @FormUrlEncoded
    @POST(ApiConstants.getInfo)
    fun getInfo(@FieldMap map: TreeMap<String, Any>, @HeaderMap headers: Map<String, String>): Call<UserinfoEntity>
}