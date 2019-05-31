package com.it.rxapp_manager_android.modle;

/**
 * Created by deqiangchen on 2018/10/31 16:18
 */

public class UpdateValuationEntity {

    /**
     * rspCode : 00
     * rspDesc : 成功
     * priceRule : {"startPrice":"200","nightEnd":"08:00","superLongDistanceKmPrice":"19","mondayRate":"2","thursdayRate":"5","longDistanceKmPrice":"18","fleetId":"38c6fc6813bf4d43b2","nightFee":"58","outStartKmPrice":"8","publicholidaysRate":"9","startKm":"20","tuesdayRate":"3","superLongDistanceKm":"38","fridayrate":"6","nightBegin":"24:00","otherPrice":"10","saturdayRate":"7","wednesdayRate":"4","longDistanceKm":"88","sundayRate":"8"}
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
         * startPrice : 200
         * nightEnd : 08:00
         * superLongDistanceKmPrice : 19
         * mondayRate : 2
         * thursdayRate : 5
         * longDistanceKmPrice : 18
         * fleetId : 38c6fc6813bf4d43b2
         * nightFee : 58
         * outStartKmPrice : 8
         * publicholidaysRate : 9
         * startKm : 20
         * tuesdayRate : 3
         * superLongDistanceKm : 38
         * fridayrate : 6
         * nightBegin : 24:00
         * otherPrice : 10
         * saturdayRate : 7
         * wednesdayRate : 4
         * longDistanceKm : 88
         * sundayRate : 8
         */

        public String startPrice;
        public String nightEnd;
        public String superLongDistanceKmPrice;
        public String mondayRate;
        public String thursdayRate;
        public String longDistanceKmPrice;
        public String fleetId;
        public String nightFee;
        public String outStartKmPrice;
        public String publicholidaysRate;
        public String startKm;
        public String tuesdayRate;
        public String superLongDistanceKm;
        public String fridayrate;
        public String nightBegin;
        public String otherPrice;
        public String saturdayRate;
        public String wednesdayRate;
        public String longDistanceKm;
        public String sundayRate;

        @Override
        public String toString() {
            return "{" +
                    "startPrice='" + startPrice + '\'' +
                    ", nightEnd='" + nightEnd + '\'' +
                    ", superLongDistanceKmPrice='" + superLongDistanceKmPrice + '\'' +
                    ", mondayRate='" + mondayRate + '\'' +
                    ", thursdayRate='" + thursdayRate + '\'' +
                    ", longDistanceKmPrice='" + longDistanceKmPrice + '\'' +
                    ", fleetId='" + fleetId + '\'' +
                    ", nightFee='" + nightFee + '\'' +
                    ", outStartKmPrice='" + outStartKmPrice + '\'' +
                    ", publicholidaysRate='" + publicholidaysRate + '\'' +
                    ", startKm='" + startKm + '\'' +
                    ", tuesdayRate='" + tuesdayRate + '\'' +
                    ", superLongDistanceKm='" + superLongDistanceKm + '\'' +
                    ", fridayrate='" + fridayrate + '\'' +
                    ", nightBegin='" + nightBegin + '\'' +
                    ", otherPrice='" + otherPrice + '\'' +
                    ", saturdayRate='" + saturdayRate + '\'' +
                    ", wednesdayRate='" + wednesdayRate + '\'' +
                    ", longDistanceKm='" + longDistanceKm + '\'' +
                    ", sundayRate='" + sundayRate + '\'' +
                    '}';
        }
    }
}
