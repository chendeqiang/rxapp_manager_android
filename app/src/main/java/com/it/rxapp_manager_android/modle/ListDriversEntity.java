package com.it.rxapp_manager_android.modle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by deqiangchen on 2018/10/16 09:45
 */

public class ListDriversEntity implements Serializable {

    /**
     * rspCode : 00
     * drivers : [{"carBrand":"凯迪拉克XTS","carName":"豪华型","carNo":"苏E88888","cphone":"18721655801","driverName":"姜楠","no":"d116c44f2b144eb0b2"},{"carBrand":"奔驰GLK级(进口)","carName":"豪华型","carNo":"苏E66666","cphone":"18015436112","driverName":"陈德强","no":"a916194631aa425aa6"}]
     * rspDesc : 成功
     */

    public String rspCode;
    public String rspDesc;
    public List<DriversBean> drivers;

    @Override
    public String toString() {
        return "{" +
                "rspCode='" + rspCode + '\'' +
                ", rspDesc='" + rspDesc + '\'' +
                ", drivers=" + drivers +
                '}';
    }

    public static class DriversBean implements Serializable {
        /**
         * carBrand : 凯迪拉克XTS
         * carName : 豪华型
         * carNo : 苏E88888
         * cphone : 18721655801
         * driverName : 姜楠
         * no : d116c44f2b144eb0b2
         */

        public String carBrand;
        public String carName;
        public String carNo;
        public String cphone;
        public String driverName;
        public String no;

        @Override
        public String toString() {
            return "{" +
                    "carBrand='" + carBrand + '\'' +
                    ", carName='" + carName + '\'' +
                    ", carNo='" + carNo + '\'' +
                    ", cphone='" + cphone + '\'' +
                    ", driverName='" + driverName + '\'' +
                    ", no='" + no + '\'' +
                    '}';
        }
    }
}
