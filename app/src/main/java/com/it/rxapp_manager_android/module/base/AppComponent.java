package com.it.rxapp_manager_android.module.base;

import com.it.rxapp_manager_android.module.act.AddCarActivity;
import com.it.rxapp_manager_android.module.act.BillActivity;
import com.it.rxapp_manager_android.module.act.CarTypeActivity;
import com.it.rxapp_manager_android.module.act.CarsActivity;
import com.it.rxapp_manager_android.module.act.ChangePasswordActivity;
import com.it.rxapp_manager_android.module.act.CreateDriverActivity;
import com.it.rxapp_manager_android.module.act.DriversActivity;
import com.it.rxapp_manager_android.module.act.LoginActivity;
import com.it.rxapp_manager_android.module.act.MainActivity;
import com.it.rxapp_manager_android.module.act.OrderInfoActivity;
import com.it.rxapp_manager_android.module.act.OrdersActivity;
import com.it.rxapp_manager_android.module.act.RelationActivity;
import com.it.rxapp_manager_android.module.act.SearchRouteActivity;
import com.it.rxapp_manager_android.module.act.SettingActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by chendeqiang on 2018/7/31 13:46
 */
@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    void inject(LoginActivity activity);

    void inject(OrdersActivity activity);

    void inject(OrderInfoActivity activity);

    void inject(DriversActivity activity);

    void inject(CarsActivity activity);

    void inject(BillActivity activity);

    void inject(SearchRouteActivity activity);

    void inject(CreateDriverActivity activity);

    void inject(SettingActivity activity);

    void inject(RelationActivity activity);

    void inject(ChangePasswordActivity activity);

    void inject(AddCarActivity activity);

}
