package com.it.rxapp_manager_android;

import android.app.Application;

import com.it.rxapp_manager_android.module.AppComponent;
import com.it.rxapp_manager_android.module.AppModule;
import com.it.rxapp_manager_android.module.ComponentHolder;
import com.it.rxapp_manager_android.module.DaggerAppComponent;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by chendeqiang on 2018/7/31 13:15
 */

public class MyApplication extends Application {
    public static Application application;
    public static Bus bus;
    public static String currActivity = "";
    public static boolean isMainActivityLive = false;

    @Override
    public void onCreate() {
        super.onCreate();
        AppComponent appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        ComponentHolder.setAppComponent(appComponent);
        application = this;
        bus = new Bus();
        bus.register(this);
    }

    @Subscribe
    public void startApp(Object o){
        //umeng
        MobclickAgent.setDebugMode(false);
        MobclickAgent.setCatchUncaughtExceptions(true);
        MobclickAgent.enableEncrypt(true);//6.0.0版本及以后
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);

        bus.unregister(this);
    }
}