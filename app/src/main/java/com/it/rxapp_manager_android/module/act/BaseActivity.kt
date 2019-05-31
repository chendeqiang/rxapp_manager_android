package com.it.rxapp_manager_android.module.act

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.WindowManager
import com.it.rxapp_manager_android.MyApplication
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.module.base.HttpUtil
import com.umeng.analytics.MobclickAgent

/**
 * Created by deqiangchen on 2018/8/1 15:37
 */
@Suppress("DEPRECATED_IDENTITY_EQUALS")
open class BaseActivity : AppCompatActivity() {

    fun setToolbar(toolbar: Toolbar) {
        toolbar.title = ""
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_whilte_arrow)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId === android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        HttpUtil.checkNetwork(this)
        if (this::class.java.equals(StartPageActivity::class.java)) {
            MobclickAgent.onPageStart(this.packageName)
        }
        MobclickAgent.onResume(this)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        MyApplication.currActivity = this::class.java.name
    }

    override fun onPause() {
        super.onPause()
        if (this::class.java.equals(StartPageActivity::class.java)) {
            MobclickAgent.onPageEnd(this.packageName)
        }
        MobclickAgent.onPause(this)
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this::class.java.name.equals(MainActivity::class.java.name) && MyApplication.currActivity.equals(MainActivity::class.java.name)) {
            MyApplication.currActivity = ""
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
    }

    //重写字体缩放比例
//    app 在 Android 7.0 上登录的时候， Dialog 不显示了，但是半透明背景显示
//    经过搜索和对比，发现出现该问题是因为重写了 getResources() 方法造成的 。
//    重写该方法是为了 app 的字体不会随着手机字体的改变而变化，造成布局混乱，设置字体用到变量 fontScale ，其他的参数不用。
    override fun getResources(): Resources {
        var res = super.getResources()
        var config = res.configuration
        config.fontScale = 1F
        res.updateConfiguration(config, res.getDisplayMetrics())
        return res
    }
}


