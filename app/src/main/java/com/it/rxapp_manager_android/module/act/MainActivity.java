package com.it.rxapp_manager_android.module.act;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.it.rxapp_manager_android.MyApplication;
import com.it.rxapp_manager_android.R;
import com.it.rxapp_manager_android.dialog.MessageDialog;
import com.it.rxapp_manager_android.modle.LogoutEntity;
import com.it.rxapp_manager_android.modle.UserinfoEntity;
import com.it.rxapp_manager_android.module.base.ComponentHolder;
import com.it.rxapp_manager_android.module.base.MyPresenter;
import com.it.rxapp_manager_android.module.base.data.UserInfoPreferences;
import com.it.rxapp_manager_android.utils.StartUtil;
import com.it.rxapp_manager_android.widget.MyProgress;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Inject
    MyPresenter presenter;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rl_notice)
    RelativeLayout rlNotice;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private Button btnExit;
    private TextView tvName;
    private TextView tvMobile;
    private String userNo;
    private MyProgress progress;

    public static void startMainActivity(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        progress = new MyProgress(this);
        userNo = UserInfoPreferences.getInstance().getDriverNo();
        initView();
        ComponentHolder.getAppComponent().inject(this);
        presenter.register(this);

        MyApplication.isMainActivityLive = true;
    }

    private void initView() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        tvToolbarTitle.setText(R.string.app_name);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        toolbar.setNavigationIcon(R.drawable.ic_header);
        rlNotice.setOnClickListener(this);

        View view = navView.getHeaderView(0);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvMobile = (TextView) view.findViewById(R.id.tv_mobile);
        btnExit = view.findViewById(R.id.btn_exit);
        btnExit.setOnClickListener(this);
        view.findViewById(R.id.ll_drivers).setOnClickListener(this);
        view.findViewById(R.id.ll_orders).setOnClickListener(this);
        view.findViewById(R.id.ll_car).setOnClickListener(this);
        view.findViewById(R.id.ll_valuation).setOnClickListener(this);
        view.findViewById(R.id.ll_count).setOnClickListener(this);
        view.findViewById(R.id.ll_rule).setOnClickListener(this);
        view.findViewById(R.id.ll_setting).setOnClickListener(this);
        view.findViewById(R.id.ll_custom_service).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_drivers://司机管理
                break;
            case R.id.ll_orders://订单管理
                break;
            case R.id.ll_car://车辆管理
                break;
            case R.id.ll_valuation://计价管理
                break;
            case R.id.ll_count://统计管理
                break;
            case R.id.ll_rule://服务规范
                WebViewActivity.startWebViewActivity(this, "服务规范", "http://www.mxingo.com/mxnet/app/serviceSpec.html");
                break;
            case R.id.ll_setting://用户设置
                break;
            case R.id.ll_custom_service://联系客服
                callMobile("4008878810");
                break;
            case R.id.btn_exit:
                progress.show();
                presenter.logout(userNo);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    private void callMobile(final String mobile) {
        final MessageDialog dialog = new MessageDialog(this);
        dialog.setMessageText("" + mobile);
        dialog.setOkText("呼叫");
        dialog.setOnCancelClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setOnOkClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                StartUtil.startPhone(mobile, MainActivity.this);
            }
        });
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.unregister(this);
        }
        MyApplication.isMainActivityLive = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getInfo(userNo);
    }

    @Subscribe
    public void loadData(Object o) {
        if (o.getClass() == LogoutEntity.class) {
            UserInfoPreferences.getInstance().clear();
            LoginActivity.startLoginActivity(this);
            finish();
        } else if (o.getClass() == UserinfoEntity.class) {
            userInfo(o);
            progress.dismiss();
        }
    }

    private void userInfo(Object o) {
        UserinfoEntity data = (UserinfoEntity) o;
        if (data.rspCode.equals("00")) {
            tvName.setText(data.driver.cname);
            tvMobile.setText(data.driver.cphone);
        }
    }
}
