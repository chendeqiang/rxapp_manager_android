package com.it.rxapp_manager_android.modle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by deqiangchen on 2018/10/22 11:00
 */

public class ListBasicAuthCityEntity implements Serializable {

    /**
     * rspCode : 00
     * authCitys : [{"authCityId":"101653","cartype":"b29964d6b1144b479c","cartypename":"经济型","endCity":"320500","endcityname":"苏州市","isInquire":"0","isPush":"0","isonline":"1","linetype":"5","linetypename":"胜意","orgId":"ffcf2166b02642e4bf","orgName":"汽车人","org_incity_cartype":"1","startCity":"320500","startcityname":"苏州市"},{"authCityId":"103228","cartype":"b29964d6b1144b479c","cartypename":"经济型","endCity":"320500","endcityname":"苏州市","isInquire":"0","isPush":"0","isonline":"1","linetype":"5","linetypename":"胜意","orgId":"ffcf2166b02642e4bf","orgName":"汽车人","org_incity_cartype":"1","startCity":"320500","startcityname":"苏州市"}]
     * rspDesc : 成功
     */

    public String rspCode;
    public String rspDesc;
    public List<AuthCitysBean> authCitys;

    @Override
    public String toString() {
        return "{" +
                "rspCode='" + rspCode + '\'' +
                ", rspDesc='" + rspDesc + '\'' +
                ", authCitys=" + authCitys +
                '}';
    }

    public static class AuthCitysBean implements Serializable{
        /**
         * authCityId : 101653
         * cartype : b29964d6b1144b479c
         * cartypename : 经济型
         * endCity : 320500
         * endcityname : 苏州市
         * isInquire : 0
         * isPush : 0
         * isonline : 1
         * linetype : 5
         * linetypename : 胜意
         * orgId : ffcf2166b02642e4bf
         * orgName : 汽车人
         * org_incity_cartype : 1
         * startCity : 320500
         * startcityname : 苏州市
         */

        public String authCityId;
        public String cartype;
        public String cartypename;
        public String endCity;
        public String endcityname;
        public String isInquire;
        public String isPush;
        public String isonline;
        public String linetype;
        public String linetypename;
        public String orgId;
        public String orgName;
        public String org_incity_cartype;
        public String startCity;
        public String startcityname;
        public String productType;

        @Override
        public String toString() {
            return "{" +
                    "authCityId='" + authCityId + '\'' +
                    ", cartype='" + cartype + '\'' +
                    ", cartypename='" + cartypename + '\'' +
                    ", endCity='" + endCity + '\'' +
                    ", endcityname='" + endcityname + '\'' +
                    ", isInquire='" + isInquire + '\'' +
                    ", isPush='" + isPush + '\'' +
                    ", isonline='" + isonline + '\'' +
                    ", linetype='" + linetype + '\'' +
                    ", linetypename='" + linetypename + '\'' +
                    ", orgId='" + orgId + '\'' +
                    ", orgName='" + orgName + '\'' +
                    ", org_incity_cartype='" + org_incity_cartype + '\'' +
                    ", startCity='" + startCity + '\'' +
                    ", startcityname='" + startcityname + '\'' +
                    ", productType='" + productType + '\'' +
                    '}';
        }
    }
}
