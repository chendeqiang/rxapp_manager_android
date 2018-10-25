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
import com.it.rxapp_manager_android.modle.ListOrderEntity;
import com.it.rxapp_manager_android.module.base.ComponentHolder;
import com.it.rxapp_manager_android.utils.Constants;
import com.it.rxapp_manager_android.widget.ShowToast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by deqiangchen on 2018/9/10 17:15
 */

public class SearchRouteActivity extends BaseActivity {

    @BindView(R.id.tv_toolbar_title)
    TextView mTvToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.mapView_search)
    MapView mMapViewSearch;

    private RoutePlanSearchUtil searchUtil;
    private ListOrderEntity.OrdersBean data;

    public static void startSearchRouteActivity(Context context, ListOrderEntity.OrdersBean data) {
        context.startActivity(new Intent(context, SearchRouteActivity.class)
                .putExtra(Constants.ORDER_INFO, data));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //百度地图
        SDKInitializer.initialize(this);
        SDKInitializer.setCoordType(CoordType.BD09LL);
        setContentView(R.layout.activity_search_route);
        ButterKnife.bind(this);

        data = (ListOrderEntity.OrdersBean) getIntent().getSerializableExtra(Constants.ORDER_INFO);
        setToolbar(mToolbar);
        mTvToolbarTitle.setText("路线详情");
        initView(data);
    }

    private void initView(ListOrderEntity.OrdersBean data) {
        if (data.startLat != null && data.startLon != null && data.endLat != null && data.endLon != null) {
            routePlan(Double.parseDouble(data.startLat), Double.parseDouble(data.startLon), Double.parseDouble(data.endLat), Double.parseDouble(data.endLon));
        } else {
            ShowToast.showBottom(this, "获取路线数据失败");
        }
    }


    //路径规划
    private void routePlan(double startLat, double startLon, double endLat, double endLon) {
        searchUtil = new RoutePlanSearchUtil(mMapViewSearch.getMap(), this);
        searchUtil.drivingPlan(startLat, startLon, endLat, endLon);
//        searchUtil.drivingPlan(30.248061, 120.451534, 30.303667, 120.225824);
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
    }
}
