package com.it.rxapp_manager_android.module.base;

import com.it.rxapp_manager_android.module.act.LoginActivity;
import com.it.rxapp_manager_android.module.act.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by chendeqiang on 2018/7/31 13:46
 */
@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    void inject(LoginActivity activity);

    void inject(MainActivity activity);
}
