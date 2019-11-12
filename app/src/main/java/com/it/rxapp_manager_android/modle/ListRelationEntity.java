package com.it.rxapp_manager_android.modle;

import java.util.List;

/**
 * Created by deqiangchen on 2018/10/8 09:45
 */

public class ListRelationEntity {

    /**
     * rspCode : 00
     * relations : [{"carID":"719295b64dac41fe81","carname":"豪华型","cname":"常春笋","cphone":"15950062135","cplatenumber":"苏E55882","cstate":"1","driver":"bf0372435c8448cc8e","model_name":"奥迪A6L","orgCarId":"dcce865b04904129aa","orgId":"ffcf2166b02642e4bf","orgName":"汽车人","relationid":"bf0372435c8448cc88"},{"cname":"Wtge773","cphone":"17636326367","cstate":"1","driver":"260eb5875b1e4ede8f","orgId":"ffcf2166b02642e4bf","orgName":"汽车人","relationid":"ee71e335089b422796"},{"cname":"郭靖","cphone":"18662170755","cstate":"0","driver":"6d49b3f75a564fb6be","orgId":"ffcf2166b02642e4bf","orgName":"汽车人","relationid":"1396f5c0208a4f7791"},{"cname":"Dhghd","cphone":"178217673263","cstate":"1","driver":"8441912f7c404040af","orgId":"ffcf2166b02642e4bf","orgName":"汽车人","relationid":"985eeda761a2404f95"},{"cname":"Djhjewj","cphone":"1267363256e","cstate":"1","driver":"9d5997ed08ad4d3f9b","orgId":"ffcf2166b02642e4bf","orgName":"汽车人","relationid":"eea1f0c757a9404aa3"},{"cname":"Df3232","cphone":"1322443434","cstate":"1","driver":"d1af6398fccc49a589","orgId":"ffcf2166b02642e4bf","orgName":"汽车人","relationid":"5322da0d00e644d4a1"},{"cname":"测试12345","cphone":"13962163384","cstate":"1","driver":"f9de2e465ca543c98e","orgId":"ffcf2166b02642e4bf","orgName":"汽车人","relationid":"37fe9d975f0e4abaaa"}]
     * rspDesc : 成功
     */

    public String rspCode;
    public String rspDesc;
    public List<RelationsBean> relations;

    @Override
    public String toString() {
        return "{" +
                "rspCode='" + rspCode + '\'' +
                ", rspDesc='" + rspDesc + '\'' +
                ", relations=" + relations +
                '}';
    }

    public static class RelationsBean {
        /**
         * carID : 719295b64dac41fe81
         * carname : 豪华型
         * cname : 常春笋
         * cphone : 15950062135
         * cplatenumber : 苏E55882
         * cstate : 1
         * driver : bf0372435c8448cc8e
         * model_name : 奥迪A6L
         * orgCarId : dcce865b04904129aa
         * orgId : ffcf2166b02642e4bf
         * orgName : 汽车人
         * relationid : bf0372435c8448cc88
         */

        public String carID;
        public String carname;
        public String cname;
        public String cphone;
        public String cplatenumber;
        public String cstate;
        public String driver;
        public String model_name;
        public String orgCarId;
        public String orgId;
        public String orgName;
        public String cidentity;
        public String relationid;

        @Override
        public String toString() {
            return "{" +
                    "carID='" + carID + '\'' +
                    ", carname='" + carname + '\'' +
                    ", cname='" + cname + '\'' +
                    ", cphone='" + cphone + '\'' +
                    ", cplatenumber='" + cplatenumber + '\'' +
                    ", cstate='" + cstate + '\'' +
                    ", driver='" + driver + '\'' +
                    ", model_name='" + model_name + '\'' +
                    ", orgCarId='" + orgCarId + '\'' +
                    ", orgId='" + orgId + '\'' +
                    ", orgName='" + orgName + '\'' +
                    ", cidentity='" + cidentity + '\'' +
                    ", relationid='" + relationid + '\'' +
                    '}';
        }
    }
}
