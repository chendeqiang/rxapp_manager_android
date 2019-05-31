package com.it.rxapp_manager_android.modle;

/**
 * Created by deqiangchen on 2018/10/30 10:44
 */

public class CreateValuationEntity {

    /**
     * rspCode : 00
     * rspDesc : 成功
     * priceRule : {"outstartminsprice":"0","fridayRate":"1","isPush":"0","superLongDistanceKmPrice":"0","incityCartype":"1","thursdayRate":"1","longDistanceKmPrice":"0","fleetId":"3970204a2eac40f6bf","endCity":"320500","outStartKmPrice":"23","publicholidaysRate":"9","orgId":"ffcf2166b02642e4bf","startCityName":"苏州市","startKm":"40","tuesdayRate":"1","carType":"b29964d6b1144b479c","authCityId":"101653","otherPrice":"0","lineType":"5","authCityName":"【胜意】出发城市[苏州市]目的地城市[苏州市]服务类型[接机/接站]车型[经济型]","wednesdayRate":"1","longDistanceKm":"0","sundayRate":"8","productType":"1","carTypeName":"经济型","startCity":"320500","startPrice":"180","orgName":"汽车人","mondayRate":"1","isInquire":"0","nightFee":"0","lineTypeName":"胜意","endCityName":"苏州市","superLongDistanceKm":"0","saturdayRate":"7","online":"1"}
     */

    public String rspCode;
    public String rspDesc;
    public PriceRuleBean priceRule;

    @Override
    public String toString() {
        return "{" +
                "rspCode='" + rspCode + '\'' +
                ", rspDesc='" + rspDesc + '\'' +
                ", priceRule=" + priceRule +
                '}';
    }

    public static class PriceRuleBean {
        /**
         * outstartminsprice : 0
         * fridayRate : 1
         * isPush : 0
         * superLongDistanceKmPrice : 0
         * incityCartype : 1
         * thursdayRate : 1
         * longDistanceKmPrice : 0
         * fleetId : 3970204a2eac40f6bf
         * endCity : 320500
         * outStartKmPrice : 23
         * publicholidaysRate : 9
         * orgId : ffcf2166b02642e4bf
         * startCityName : 苏州市
         * startKm : 40
         * tuesdayRate : 1
         * carType : b29964d6b1144b479c
         * authCityId : 101653
         * otherPrice : 0
         * lineType : 5
         * authCityName : 【胜意】出发城市[苏州市]目的地城市[苏州市]服务类型[接机/接站]车型[经济型]
         * wednesdayRate : 1
         * longDistanceKm : 0
         * sundayRate : 8
         * productType : 1
         * carTypeName : 经济型
         * startCity : 320500
         * startPrice : 180
         * orgName : 汽车人
         * mondayRate : 1
         * isInquire : 0
         * nightFee : 0
         * lineTypeName : 胜意
         * endCityName : 苏州市
         * superLongDistanceKm : 0
         * saturdayRate : 7
         * online : 1
         */

        public String outstartminsprice;
        public String fridayRate;
        public String isPush;
        public String superLongDistanceKmPrice;
        public String incityCartype;
        public String thursdayRate;
        public String longDistanceKmPrice;
        public String fleetId;
        public String endCity;
        public String outStartKmPrice;
        public String publicholidaysRate;
        public String orgId;
        public String startCityName;
        public String startKm;
        public String tuesdayRate;
        public String carType;
        public String authCityId;
        public String otherPrice;
        public String lineType;
        public String authCityName;
        public String wednesdayRate;
        public String longDistanceKm;
        public String sundayRate;
        public String productType;
        public String carTypeName;
        public String startCity;
        public String startPrice;
        public String orgName;
        public String mondayRate;
        public String isInquire;
        public String nightFee;
        public String lineTypeName;
        public String endCityName;
        public String superLongDistanceKm;
        public String saturdayRate;
        public String online;

        @Override
        public String toString() {
            return "{" +
                    "outstartminsprice='" + outstartminsprice + '\'' +
                    ", fridayRate='" + fridayRate + '\'' +
                    ", isPush='" + isPush + '\'' +
                    ", superLongDistanceKmPrice='" + superLongDistanceKmPrice + '\'' +
                    ", incityCartype='" + incityCartype + '\'' +
                    ", thursdayRate='" + thursdayRate + '\'' +
                    ", longDistanceKmPrice='" + longDistanceKmPrice + '\'' +
                    ", fleetId='" + fleetId + '\'' +
                    ", endCity='" + endCity + '\'' +
                    ", outStartKmPrice='" + outStartKmPrice + '\'' +
                    ", publicholidaysRate='" + publicholidaysRate + '\'' +
                    ", orgId='" + orgId + '\'' +
                    ", startCityName='" + startCityName + '\'' +
                    ", startKm='" + startKm + '\'' +
                    ", tuesdayRate='" + tuesdayRate + '\'' +
                    ", carType='" + carType + '\'' +
                    ", authCityId='" + authCityId + '\'' +
                    ", otherPrice='" + otherPrice + '\'' +
                    ", lineType='" + lineType + '\'' +
                    ", authCityName='" + authCityName + '\'' +
                    ", wednesdayRate='" + wednesdayRate + '\'' +
                    ", longDistanceKm='" + longDistanceKm + '\'' +
                    ", sundayRate='" + sundayRate + '\'' +
                    ", productType='" + productType + '\'' +
                    ", carTypeName='" + carTypeName + '\'' +
                    ", startCity='" + startCity + '\'' +
                    ", startPrice='" + startPrice + '\'' +
                    ", orgName='" + orgName + '\'' +
                    ", mondayRate='" + mondayRate + '\'' +
                    ", isInquire='" + isInquire + '\'' +
                    ", nightFee='" + nightFee + '\'' +
                    ", lineTypeName='" + lineTypeName + '\'' +
                    ", endCityName='" + endCityName + '\'' +
                    ", superLongDistanceKm='" + superLongDistanceKm + '\'' +
                    ", saturdayRate='" + saturdayRate + '\'' +
                    ", online='" + online + '\'' +
                    '}';
        }
    }
}
