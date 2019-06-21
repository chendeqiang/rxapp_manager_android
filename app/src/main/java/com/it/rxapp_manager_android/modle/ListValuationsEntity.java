package com.it.rxapp_manager_android.modle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by deqiangchen on 2018/10/19 15:00
 */

public class ListValuationsEntity implements Serializable{

    /**
     * rspCode : 00
     * priceRules : [{"authCityId":"fde8d6c0bcf54a02aa","carType":"d44016b2d02d48fd9a","cartypename":"豪华型","endCity":"420100","endcityname":"武汉市","fleetId":"38c6fc6813bf4d43b2","fridayRate":"1.01","lineType":"1","linetypename":"携程","longDistanceKm":"20.00","longDistanceKmPrice":"8.00","mondayRate":"1.01","orgId":"ffcf2166b02642e4bf","orgName":"汽车人","otherPrice":"0.00","outStartKmPrice":"6.00","publicholidaysRate":"1.2","saturdayRate":"1.15","startCity":"420100","startKm":"10.00","startPrice":"120.00","startcityname":"武汉市","sundayRate":"1.15","superLongDistanceKm":"30.00","superLongDistanceKmPrice":"9.00","thursdayRate":"1.01","tuesdayRate":"1.01","wednesdayRate":"1.01"},{"authCityId":"fe76ee4d4f264c55b3","carType":"7823aa88349f4b8c85","cartypename":"10座小巴","endCity":"321100","endcityname":"镇江市","fleetId":"02a8cc609ffb4510bc","fridayRate":"1","lineType":"1","linetypename":"携程","longDistanceKm":"300.00","longDistanceKmPrice":"7.00","mondayRate":"1","orgId":"ffcf2166b02642e4bf","orgName":"汽车人","otherPrice":"0.00","outStartKmPrice":"6.00","publicholidaysRate":"1","saturdayRate":"1","startCity":"310100","startKm":"250.00","startPrice":"4300.00","startcityname":"上海市","sundayRate":"1","superLongDistanceKm":"0.00","superLongDistanceKmPrice":"0.00","thursdayRate":"1","tuesdayRate":"1","wednesdayRate":"1"}]
     * rspDesc : 成功
     */

    public String rspCode;
    public String rspDesc;
    public List<PriceRulesBean> priceRules;

    @Override
    public String toString() {
        return "{" +
                "rspCode='" + rspCode + '\'' +
                ", rspDesc='" + rspDesc + '\'' +
                ", priceRules=" + priceRules +
                '}';
    }

    public static class PriceRulesBean implements Serializable {
        /**
         * authCityId : fde8d6c0bcf54a02aa
         * carType : d44016b2d02d48fd9a
         * cartypename : 豪华型
         * endCity : 420100
         * endcityname : 武汉市
         * fleetId : 38c6fc6813bf4d43b2
         * fridayRate : 1.01
         * lineType : 1
         * linetypename : 携程
         * longDistanceKm : 20.00
         * longDistanceKmPrice : 8.00
         * mondayRate : 1.01
         * orgId : ffcf2166b02642e4bf
         * orgName : 汽车人
         * otherPrice : 0.00
         * outStartKmPrice : 6.00
         * publicholidaysRate : 1.2
         * saturdayRate : 1.15
         * startCity : 420100
         * startKm : 10.00
         * startPrice : 120.00
         * startcityname : 武汉市
         * sundayRate : 1.15
         * superLongDistanceKm : 30.00
         * superLongDistanceKmPrice : 9.00
         * thursdayRate : 1.01
         * tuesdayRate : 1.01
         * wednesdayRate : 1.01
         */

        public String authCityId;
        public String carType;
        public String cartypename;
        public String endCity;
        public String endcityname;
        public String fleetId;
        public String fridayRate;
        public String lineType;
        public String linetypename;
        public String longDistanceKm;
        public String longDistanceKmPrice;
        public String mondayRate;
        public String orgId;
        public String orgName;
        public String otherPrice;
        public String outStartKmPrice;
        public String publicholidaysRate;
        public String saturdayRate;
        public String startCity;
        public String startKm;
        public String startPrice;
        public String startcityname;
        public String sundayRate;
        public String superLongDistanceKm;
        public String superLongDistanceKmPrice;
        public String maxdistancekm ;
        public String maxdistancekmprice;
        public String thursdayRate;
        public String tuesdayRate;
        public String wednesdayRate;
        public String productType;
        public String productNo;
        public String nightBegin;
        public String nightEnd;
        public String nightFee;

        @Override
        public String toString() {
            return "{" +
                    "authCityId='" + authCityId + '\'' +
                    ", carType='" + carType + '\'' +
                    ", cartypename='" + cartypename + '\'' +
                    ", endCity='" + endCity + '\'' +
                    ", endcityname='" + endcityname + '\'' +
                    ", fleetId='" + fleetId + '\'' +
                    ", fridayRate='" + fridayRate + '\'' +
                    ", lineType='" + lineType + '\'' +
                    ", linetypename='" + linetypename + '\'' +
                    ", longDistanceKm='" + longDistanceKm + '\'' +
                    ", longDistanceKmPrice='" + longDistanceKmPrice + '\'' +
                    ", mondayRate='" + mondayRate + '\'' +
                    ", orgId='" + orgId + '\'' +
                    ", orgName='" + orgName + '\'' +
                    ", otherPrice='" + otherPrice + '\'' +
                    ", outStartKmPrice='" + outStartKmPrice + '\'' +
                    ", publicholidaysRate='" + publicholidaysRate + '\'' +
                    ", saturdayRate='" + saturdayRate + '\'' +
                    ", startCity='" + startCity + '\'' +
                    ", startKm='" + startKm + '\'' +
                    ", startPrice='" + startPrice + '\'' +
                    ", startcityname='" + startcityname + '\'' +
                    ", sundayRate='" + sundayRate + '\'' +
                    ", superLongDistanceKm='" + superLongDistanceKm + '\'' +
                    ", superLongDistanceKmPrice='" + superLongDistanceKmPrice + '\'' +
                    ", maxdistancekm='" + maxdistancekm + '\'' +
                    ", maxdistancekmprice='" + maxdistancekmprice + '\'' +
                    ", thursdayRate='" + thursdayRate + '\'' +
                    ", tuesdayRate='" + tuesdayRate + '\'' +
                    ", wednesdayRate='" + wednesdayRate + '\'' +
                    ", productType='" + productType + '\'' +
                    ", productNo='" + productNo + '\'' +
                    ", nightBegin='" + nightBegin + '\'' +
                    ", nightEnd='" + nightEnd + '\'' +
                    ", nightFee='" + nightFee + '\'' +
                    '}';
        }
    }
}
