package com.it.rxapp_manager_android.module.base;

import com.it.rxapp_manager_android.module.act.BillActivity;
import com.it.rxapp_manager_android.module.act.CarsActivity;
import com.it.rxapp_manager_android.module.act.DriversActivity;
import com.it.rxapp_manager_android.module.act.LoginActivity;
import com.it.rxapp_manager_android.module.act.MainActivity;
import com.it.rxapp_manager_android.module.act.OrdersActivity;
import com.it.rxapp_manager_android.module.act.SearchRouteActivity;

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

    void inject(OrdersActivity activity);

    void inject(DriversActivity activity);

    void inject(CarsActivity activity);

    void inject(BillActivity activity);

    void inject(SearchRouteActivity activity);
}
