package com.it.rxapp_manager_android.modle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by deqiangchen on 2018/9/30 14:49
 */

public class ListCarEntity implements Serializable {

    /**
     * cars : [{"carBrand":"雅阁","carID":"41147374b4a5415ca1","carLevel":2,"carLevelName":"舒适型","carNo":"苏E12345","orgId":"ffcf2166b02642e4bf"},{"carBrand":"奔驰GLC","carID":"b1be7b08318047aba7","carLevel":3,"carLevelName":"豪华型","carNo":"苏E15801","orgId":"ffcf2166b02642e4bf"},{"carBrand":"奥迪A6L","carID":"9ff4c95cf4de4fc691","carLevel":3,"carLevelName":"豪华型","carNo":"苏E11223","orgId":"ffcf2166b02642e4bf"},{"carBrand":"奥迪A6L","carID":"1e77c8fc736a44728d","carLevel":3,"carLevelName":"豪华型","carNo":"苏E55556","orgId":"ffcf2166b02642e4bf"},{"carBrand":"奥迪A6L","carID":"719295b64dac41fe81","carLevel":3,"carLevelName":"豪华型","carNo":"苏E55882","orgId":"ffcf2166b02642e4bf"},{"carBrand":"奔驰GLC","carID":"ddafdd60603248179b","carLevel":3,"carLevelName":"豪华型","carNo":"浙A10086","orgId":"ffcf2166b02642e4bf"},{"carBrand":"奥迪A6L","carID":"cd6fae78e9da4877a6","carLevel":3,"carLevelName":"豪华型","carNo":"苏E99887","orgId":"ffcf2166b02642e4bf"},{"carBrand":"奥迪A6L","carID":"6bfcae39e3834709b9","carLevel":3,"carLevelName":"豪华型","carNo":"苏E12369","orgId":"ffcf2166b02642e4bf"}]
     * rspCode : 00
     * rspDesc : 成功
     */

    public String rspCode;
    public String rspDesc;
    public List<CarsBean> cars;

    @Override
    public String toString() {
        return "{" +
                "rspCode='" + rspCode + '\'' +
                ", rspDesc='" + rspDesc + '\'' +
                ", cars=" + cars +
                '}';
    }

    public static class CarsBean implements Serializable {
        /**
         * carBrand : 雅阁
         * carID : 41147374b4a5415ca1
         * carLevel : 2
         * carLevelName : 舒适型
         * carNo : 苏E12345
         * orgId : ffcf2166b02642e4bf
         */

        public String carBrand;
        public String carID;
        public int carLevel;
        public String carLevelName;
        public String carNo;
        public String orgId;
        public String ccolor;

        @Override
        public String toString() {
            return "{" +
                    "carBrand='" + carBrand + '\'' +
                    ", carID='" + carID + '\'' +
                    ", carLevel=" + carLevel +
                    ", carLevelName='" + carLevelName + '\'' +
                    ", carNo='" + carNo + '\'' +
                    ", orgId='" + orgId + '\'' +
                    ", ccolor='" + ccolor + '\'' +
                    '}';
        }
    }
}
