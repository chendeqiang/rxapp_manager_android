package com.it.rxapp_manager_android.modle;

import java.io.Serializable;
import java.util.List;


public class ListOrderEntity implements Serializable {

    /**
     * rspCode : 00
     * orders : [{"bookTime":"2017-09-14 10:08:18","carBrand":"舒适型","carLevel":0,"carNo":"苏E55882","carType":"2","ccustomPhone":"15950062135","cdays":"1","cendAddress":"昆山经济开发区","cpayState":"0","cpayprice":"13000.00","cstartAddress":"虹桥国际机场","driver":"擎天柱","orderId":"027282f200954e2db9","orderNo":"201709141008186143","orderStatus":"1","orderType":"0","orgId":"ffcf2166b02642e4bf","orgName":"汽车人","passengerName":"ceshi7","payType":"1","payTypeName":"支付宝","relationBillId":"ceshi22","startCity":"310100","tripNo":"无","useTime":"2017-09-22 10:05:00"},{"bookTime":"2017-09-14 10:06:37","carBrand":"舒适型","carLevel":0,"carNo":"苏E55882","carType":"2","ccustomPhone":"15950062135","cdays":"1","cendAddress":"徐州小商品市场","cpayState":"0","cpayprice":"3000.00","startLon":"121.33456568169129","startLat":"31.20016670869357","endLon":"121.50637475316677","endLat":"121.50637475316677","cremark":"123","cstartAddress":"南京禄口国际机场","driver":"擎天柱","orderId":"296e17b2298c45eeb3","orderNo":"201709141006373520","orderStatus":"1","orderType":"0","orgId":"ffcf2166b02642e4bf","orgName":"汽车人","passengerName":"ceshi5","payType":"1","payTypeName":"支付宝","relationBillId":"ceshi20","startCity":"320300","tripNo":"无","useTime":"2017-09-20 10:05:00"}]
     * rspDesc : 成功
     */

    public String rspCode;
    public String rspDesc;
    public List<OrdersBean> orders;

    @Override
    public String toString() {
        return "{" +
                "rspCode='" + rspCode + '\'' +
                ", rspDesc='" + rspDesc + '\'' +
                ", orders=" + orders +
                '}';
    }

    public static class OrdersBean implements Serializable {
        /**
         * bookTime : 2017-09-14 10:08:18
         * carTypeName : 舒适型
         * carLevel : 0
         * carNo : 苏E55882
         * carType : 2
         * ccustomPhone : 15950062135
         * cdays : 1
         * cendAddress : 昆山经济开发区
         * cpayState : 0
         * cpayprice : 13000.00
         * cstartAddress : 虹桥国际机场
         * driver : 擎天柱
         * orderId : 027282f200954e2db9
         * orderNo : 201709141008186143
         * orderStatus : 1
         * orderType : 0
         * orgId : ffcf2166b02642e4bf
         * orgName : 汽车人
         * passengerName : ceshi7
         * payType : 1
         * payTypeName : 支付宝
         * relationBillId : ceshi22
         * startCity : 310100
         * tripNo : 无
         * useTime : 2017-09-22 10:05:00
         * startLon : 121.33456568169129
         * startLat : 31.20016670869357
         * endLon : 121.50637475316677
         * endLat : 121.50637475316677
         * cremark : 123
         */

        public String bookTime;
        public String carTypeName;
        public int carLevel;
        public String carNo;
        public String carType;
        public String ccustomPhone;
        public String cdays;
        public String cendAddress;
        public String cpayState;
        public String cpayprice;
        public String cstartAddress;
        public String driver;
        public String driverId;
        public String orderId;
        public String orderNo;
        public String orderStatus;
        public String orderType;
        public String orgId;
        public String orgName;
        public String passengerName;
        public String payType;
        public String payTypeName;
        public String relationBillId;
        public String startCity;
        public String tripNo;
        public String useTime;
        public String startLon;
        public String startLat;
        public String endLon;
        public String endLat;
        public String cremark;
        public String payAmount;
        public String source;

        @Override
        public String toString() {
            return "{" +
                    "bookTime='" + bookTime + '\'' +
                    ", carTypeName='" + carTypeName + '\'' +
                    ", carLevel=" + carLevel +
                    ", carNo='" + carNo + '\'' +
                    ", carType='" + carType + '\'' +
                    ", ccustomPhone='" + ccustomPhone + '\'' +
                    ", cdays='" + cdays + '\'' +
                    ", cendAddress='" + cendAddress + '\'' +
                    ", cpayState='" + cpayState + '\'' +
                    ", cpayprice='" + cpayprice + '\'' +
                    ", cstartAddress='" + cstartAddress + '\'' +
                    ", driver='" + driver + '\'' +
                    ", driverId='" + driverId + '\'' +
                    ", orderId='" + orderId + '\'' +
                    ", orderNo='" + orderNo + '\'' +
                    ", orderStatus='" + orderStatus + '\'' +
                    ", orderType='" + orderType + '\'' +
                    ", orgId='" + orgId + '\'' +
                    ", orgName='" + orgName + '\'' +
                    ", passengerName='" + passengerName + '\'' +
                    ", payType='" + payType + '\'' +
                    ", payTypeName='" + payTypeName + '\'' +
                    ", relationBillId='" + relationBillId + '\'' +
                    ", startCity='" + startCity + '\'' +
                    ", tripNo='" + tripNo + '\'' +
                    ", useTime='" + useTime + '\'' +
                    ", startLon='" + startLon + '\'' +
                    ", startLat='" + startLat + '\'' +
                    ", endLon='" + endLon + '\'' +
                    ", endLat='" + endLat + '\'' +
                    ", cremark='" + cremark + '\'' +
                    ", payAmount='" + payAmount + '\'' +
                    ", source='" + source + '\'' +
                    '}';
        }
    }
}
