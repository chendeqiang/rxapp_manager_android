package com.it.rxapp_manager_android.modle;

/**
 * Created by deqiangchen on 2018/10/10 16:54
 */

public class UpdateOrgInfoEntity {

    /**
     * billNotification : {"orgId":"ffcf2166b02642e4bf","phone1":"15950062135","phone2":"15950062136","phone3":"15950062137"}
     * rspCode : 00
     * organization : {"dispatchPattern":"1","fleetMobile":"15950062135","fleetName":"小黄蜂","fleetNo":"浙E88888","orgId":"ffcf2166b02642e4bf","orgcommissionFZ":"10","orgcommissionSY":"11","orgcommissionTC":"12","orgcommissionXC":"13"}
     * rspDesc : 成功
     */

    public BillNotificationBean billNotification;
    public String rspCode;
    public OrganizationBean organization;
    public String rspDesc;

    @Override
    public String toString() {
        return "{" +
                "billNotification=" + billNotification +
                ", rspCode='" + rspCode + '\'' +
                ", organization=" + organization +
                ", rspDesc='" + rspDesc + '\'' +
                '}';
    }

    public static class BillNotificationBean {
        /**
         * orgId : ffcf2166b02642e4bf
         * phone1 : 15950062135
         * phone2 : 15950062136
         * phone3 : 15950062137
         */

        public String orgId;
        public String phone1;
        public String phone2;
        public String phone3;

        @Override
        public String toString() {
            return "{" +
                    "orgId='" + orgId + '\'' +
                    ", phone1='" + phone1 + '\'' +
                    ", phone2='" + phone2 + '\'' +
                    ", phone3='" + phone3 + '\'' +
                    '}';
        }
    }

    public static class OrganizationBean {
        /**
         * dispatchPattern : 1
         * fleetMobile : 15950062135
         * fleetName : 小黄蜂
         * fleetNo : 浙E88888
         * orgId : ffcf2166b02642e4bf
         * orgcommissionFZ : 10
         * orgcommissionSY : 11
         * orgcommissionTC : 12
         * orgcommissionXC : 13
         */

        public String dispatchPattern;
        public String fleetMobile;
        public String fleetName;
        public String fleetNo;
        public String orgId;
        public String orgcommissionFZ;
        public String orgcommissionSY;
        public String orgcommissionTC;
        public String orgcommissionXC;

        @Override
        public String toString() {
            return "{" +
                    "dispatchPattern='" + dispatchPattern + '\'' +
                    ", fleetMobile='" + fleetMobile + '\'' +
                    ", fleetName='" + fleetName + '\'' +
                    ", fleetNo='" + fleetNo + '\'' +
                    ", orgId='" + orgId + '\'' +
                    ", orgcommissionFZ='" + orgcommissionFZ + '\'' +
                    ", orgcommissionSY='" + orgcommissionSY + '\'' +
                    ", orgcommissionTC='" + orgcommissionTC + '\'' +
                    ", orgcommissionXC='" + orgcommissionXC + '\'' +
                    '}';
        }
    }
}
