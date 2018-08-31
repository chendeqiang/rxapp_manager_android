package com.it.rxapp_manager_android

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.it.rxapp_manager_android.utils.Constants
import com.it.rxapp_manager_android.utils.LogUtils

/**
 * Created by deqiangchen on 2018/8/1 15:45
 */
class StartPageActivity:BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d("classname", this::class.java.name)
        setContentView(R.layout.activity_start_page)
        addPermissions()
        MyApplication.bus.post(Any())
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        grantResults.forEach {
            if (it != -1) {
                addPermissions()
                return
            }
        }
        MainActivity.startMainActivity(this)
        finish()
    }

    fun addPermissions() {
        val list = arrayListOf(Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE)
        list.filter {
            ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
        }.map {
            list.remove(it)
        }

        if (list.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, list.toArray(Array<String>(list.size, { i -> i.toString() })), Constants.permissionMain)
        } else {
            if(MyApplication.isMainActivityLive){
                finish()
                return
            }
            Handler().postDelayed(Runnable {
                MainActivity.startMainActivity(this)
                finish()
            },5000)
        }
    }
}