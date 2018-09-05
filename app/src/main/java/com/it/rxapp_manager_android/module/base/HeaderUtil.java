package com.it.rxapp_manager_android.module.base;

import com.it.rxapp_manager_android.module.base.data.UserInfoPreferences;
import com.it.rxapp_manager_android.utils.VersionInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by deqiangchen on 2018/9/3 14:57
 */

public class HeaderUtil {
    public static Map<String,String> getHeaders(Map<String,Object> map){
        Map<String, String> headers = new HashMap<>();
        headers.put(ApiConstants.token, UserInfoPreferences.getInstance().getToken());
        headers.put(ApiConstants.version, VersionInfo.getVersionName());
        headers.put(ApiConstants.sign, SignUtil.getSign(map,VersionInfo.getVersionName()));
        return headers;
    }
}
