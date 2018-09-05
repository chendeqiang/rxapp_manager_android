package com.it.rxapp_manager_android.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;

import java.io.File;

public class StartUtil {

    //判断是否安装目标应用
    public static boolean isInstallByread(String packageName) {
        try {
            return new File("/data/data/" + packageName).exists();
        } catch (Exception e) {
            return false;
        }

    }

    public static void startPhone(String mobile, Activity activity) {
        if (TextUtil.isEmpty(mobile)) {
            return;
        }
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, 1);
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobile));
            activity.startActivity(intent);
        }
    }
}
