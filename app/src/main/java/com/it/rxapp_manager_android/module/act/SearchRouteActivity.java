package com.it.rxapp_manager_android.module.act;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;
import com.it.rxapp_manager_android.R;
import com.it.rxapp_manager_android.modle.OrderEntity;
import com.it.rxapp_manager_android.modle.OrderInfoEntity;
import com.it.rxapp_manager_android.module.base.ComponentHolder;
import com.it.rxapp_manager_android.module.base.MyPresenter;
import com.it.rxapp_manager_android.utils.Constants;
import com.it.rxapp_manager_android.widget.MyProgress;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by deqiangchen on 2018/9/10 17:15
 */

public class SearchRouteActivity extends BaseActivity {

    @Inject
    MyPresenter presenter;
    @BindView(R.id.tv_toolbar_title)
    TextView mTvToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.mapView_search)
    MapView mMapViewSearch;
    private MyProgress progress;
    private RoutePlanSearchUtil searchUtil;
    private String orderNo;
    private OrderEntity order;


    public static void startSearchRouteActivity(Context context, String orderNo) {
        context.startActivity(new Intent(context, SearchRouteActivity.class)
                .putExtra(Constants.ORDER_NO, orderNo));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //百度地图
        SDKInitializer.initialize(this);
        SDKInitializer.setCoordType(CoordType.GCJ02);
        setContentView(R.layout.activity_search_route);
        ButterKnife.bind(this);
        ComponentHolder.getAppComponent().inject(this);

        presenter.register(this);
        progress = new MyProgress(this);
        orderNo = getIntent().getStringExtra(Constants.ORDER_NO);
        setToolbar(mToolbar);
        mTvToolbarTitle.setText("路线详情");
        progress.show();
//        presenter.qryOrder(orderNo);
    }

    @Subscribe
    public void loadData(Object object) {
        if (object.getClass() == OrderInfoEntity.class) {
            route((OrderInfoEntity) object);
            progress.dismiss();
        }
    }

    private void route(OrderInfoEntity object) {
        if (object.rspCode.equals("00")) {
            order = object.order;
            routePlan(order.startLat, order.startLon, order.endLat, order.endLon);
        }
    }


    //路径规划
    private void routePlan(double startLat, double startLon, double endLat, double endLon) {
        searchUtil = new RoutePlanSearchUtil(mMapViewSearch.getMap(), this);
        searchUtil.drivingPlan(startLat, startLon, endLat, endLon);
        //endLat=30.303667, endLon=120.225824,startLat=30.248061, startLon=120.451534
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapViewSearch.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapViewSearch.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapViewSearch.onDestroy();
        presenter.unregister(this);
    }
}
