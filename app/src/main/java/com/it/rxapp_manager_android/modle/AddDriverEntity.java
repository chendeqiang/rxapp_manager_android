package com.it.rxapp_manager_android.modle;

/**
 * Created by deqiangchen on 2018/9/30 09:59
 */

public class AddDriverEntity {

    /**
     * rspCode : 00
     * driver : {"password":"dd33e4e124e222ef61c4aff5b5f87d96","mobile":"13962163384","driverName":"测试12345","driverNo":"f9de2e465ca543c98e","driverType":1,"orgId":"ffcf2166b02642e4bf"}
     * rspDesc : 成功
     */

    public String rspCode;
    public DriverBean driver;
    public String rspDesc;

    @Override
    public String toString() {
        return "{" +
                "rspCode='" + rspCode + '\'' +
                ", driver=" + driver +
                ", rspDesc='" + rspDesc + '\'' +
                '}';
    }

    public static class DriverBean {
        /**
         * password : dd33e4e124e222ef61c4aff5b5f87d96
         * mobile : 13962163384
         * driverName : 测试12345
         * driverNo : f9de2e465ca543c98e
         * driverType : 1
         * orgId : ffcf2166b02642e4bf
         */

        public String password;
        public String mobile;
        public String driverName;
        public String driverNo;
        public int driverType;
        public String orgId;

        @Override
        public String toString() {
            return "{" +
                    "password='" + password + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", driverName='" + driverName + '\'' +
                    ", driverNo='" + driverNo + '\'' +
                    ", driverType=" + driverType +
                    ", orgId='" + orgId + '\'' +
                    '}';
        }
    }
}
