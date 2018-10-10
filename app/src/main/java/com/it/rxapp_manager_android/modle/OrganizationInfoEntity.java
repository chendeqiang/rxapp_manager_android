package com.it.rxapp_manager_android.modle;

import java.util.List;

/**
 * Created by deqiangchen on 2018/9/30 16:58
 */

public class OrganizationInfoEntity {

    /**
     * rspCode : 00
     * organizations : [{"dispatchPattern":"2","fleetMobile":"15857121977","fleetName":"大黄蜂","fleetNo":"浙E99999","orgId":"ffcf2166b02642e4bf","orgName":"汽车人","orgcommissionFZ":"0","orgcommissionSY":"0","orgcommissionTC":"0","orgcommissionXC":"0","phone1":"18913196887","phone2":"","phone3":""}]
     * rspDesc : 成功
     */

    public String rspCode;
    public String rspDesc;
    public List<OrganizationsBean> organizations;

    @Override
    public String toString() {
        return "{" +
                "rspCode='" + rspCode + '\'' +
                ", rspDesc='" + rspDesc + '\'' +
                ", organizations=" + organizations +
                '}';
    }

    public static class OrganizationsBean {
        /**
         * dispatchPattern : 2
         * fleetMobile : 15857121977
         * fleetName : 大黄蜂
         * fleetNo : 浙E99999
         * orgId : ffcf2166b02642e4bf
         * orgName : 汽车人
         * orgcommissionFZ : 0
         * orgcommissionSY : 0
         * orgcommissionTC : 0
         * orgcommissionXC : 0
         * phone1 : 18913196887
         * phone2 :
         * phone3 :
         */

        public String dispatchPattern;
        public String fleetMobile;
        public String fleetName;
        public String fleetNo;
        public String orgId;
        public String orgName;
        public String orgcommissionFZ;
        public String orgcommissionSY;
        public String orgcommissionTC;
        public String orgcommissionXC;
        public String phone1;
        public String phone2;
        public String phone3;

        @Override
        public String toString() {
            return "{" +
                    "dispatchPattern='" + dispatchPattern + '\'' +
                    ", fleetMobile='" + fleetMobile + '\'' +
                    ", fleetName='" + fleetName + '\'' +
                    ", fleetNo='" + fleetNo + '\'' +
                    ", orgId='" + orgId + '\'' +
                    ", orgName='" + orgName + '\'' +
                    ", orgcommissionFZ='" + orgcommissionFZ + '\'' +
                    ", orgcommissionSY='" + orgcommissionSY + '\'' +
                    ", orgcommissionTC='" + orgcommissionTC + '\'' +
                    ", orgcommissionXC='" + orgcommissionXC + '\'' +
                    ", phone1='" + phone1 + '\'' +
                    ", phone2='" + phone2 + '\'' +
                    ", phone3='" + phone3 + '\'' +
                    '}';
        }
    }
}
