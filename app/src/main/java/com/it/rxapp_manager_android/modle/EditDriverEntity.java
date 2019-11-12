package com.it.rxapp_manager_android.modle;

public class EditDriverEntity {

    /**
     * rspCode : 00
     * driverIdentity : {"cuuid":"77a553bc301d48deb3","driverName":"李亚","cidentity":"340621198807223651"}
     * rspDesc : 成功
     */

    public String rspCode;
    public DriverIdentityBean driverIdentity;
    public String rspDesc;

    @Override
    public String toString() {
        return "{" +
                "rspCode='" + rspCode + '\'' +
                ", driverIdentity=" + driverIdentity +
                ", rspDesc='" + rspDesc + '\'' +
                '}';
    }

    public static class DriverIdentityBean {
        /**
         * cuuid : 77a553bc301d48deb3
         * driverName : 李亚
         * cidentity : 340621198807223651
         */

        public String cuuid;
        public String driverName;
        public String cidentity;

        @Override
        public String toString() {
            return "{" +
                    "cuuid='" + cuuid + '\'' +
                    ", driverName='" + driverName + '\'' +
                    ", cidentity='" + cidentity + '\'' +
                    '}';
        }
    }
}
