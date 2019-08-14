package com.it.rxapp_manager_android.modle;

/**
 * Created by deqiangchen on 2018/10/10 11:02
 */

public class AddCarEntity {

    /**
     * rspCode : 00
     * rspDesc : 成功
     * car : {"carID":"12345","carNo":"苏E12345","carType":"abcsda","orgId":"ffcf2166b02642e4bf"}
     */

    public String rspCode;
    public String rspDesc;
    public CarBean car;

    @Override
    public String toString() {
        return "{" +
                "rspCode='" + rspCode + '\'' +
                ", rspDesc='" + rspDesc + '\'' +
                ", car=" + car +
                '}';
    }

    public static class CarBean {
        /**
         * carID : 12345
         * carNo : 苏E12345
         * carType : abcsda
         * orgId : ffcf2166b02642e4bf
         */

        public String carID;
        public String carNo;
        public String carType;
        public String orgId;
        public String ccolor;

        @Override
        public String toString() {
            return "{" +
                    "carID='" + carID + '\'' +
                    ", carNo='" + carNo + '\'' +
                    ", carType='" + carType + '\'' +
                    ", orgId='" + orgId + '\'' +
                    ", ccolor='" + ccolor + '\'' +
                    '}';
        }
    }
}
