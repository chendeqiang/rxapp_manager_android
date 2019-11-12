package com.it.rxapp_manager_android.modle;

import java.util.List;

/**
 * Created by deqiangchen on 2018/9/29 14:48
 */

public class ListDriverEntity {


    /**
     * rspCode : 00
     * drivers : [{"cloginid":"xiaowan","cname":"小万","cphone":"13083951559","cstate":1,"no":"3b7c0463c2d74ea2af","orgId":"ffcf2166b02642e4bf"},{"cloginid":"juwuba","cname":"巨无霸","cphone":"13962163381","cstate":1,"no":"e507e9b552cc45789e","orgId":"ffcf2166b02642e4bf"},{"cloginid":"chendeqiang","cname":"陈德强","cphone":"18662170766","cstate":1,"no":"47fc6f6b5bc84cd983","orgId":"ffcf2166b02642e4bf"},{"cloginid":"qingtianzhu","cname":"擎天柱","cphone":"18721655801","cstate":1,"no":"34dad4414cc646fba8","orgId":"ffcf2166b02642e4bf"},{"cloginid":"dahuangfeng2","cname":"大黄蜂","cphone":"13858021464","cstate":1,"no":"cac3ae843dfd4f4fa2","orgId":"ffcf2166b02642e4bf"},{"cloginid":"lilaoban","cname":"李老板","cphone":"18767126165","cstate":1,"no":"ea4ce28614cf4030b8","orgId":"ffcf2166b02642e4bf"},{"cloginid":"guolaomao","cname":"郭老猫","cphone":"13764747630","cstate":1,"no":"14b26e738e3c460791","orgId":"ffcf2166b02642e4bf"},{"cloginid":"weilaoban","cname":"saber","cphone":"18767126193","cstate":1,"no":"6279c7f4ad22412fb8","orgId":"ffcf2166b02642e4bf"},{"cloginid":"changchunsun2","cname":"常春笋","cphone":"15950062135","cstate":1,"no":"bf0372435c8448cc8e","orgId":"ffcf2166b02642e4bf"},{"cname":"测试12345","cphone":"13962163384","cstate":1,"no":"f9de2e465ca543c98e","orgId":"ffcf2166b02642e4bf"}]
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

    public static class DriversBean {
        /**
         * cloginid : xiaowan
         * cname : 小万
         * cphone : 13083951559
         * cstate : 1
         * no : 3b7c0463c2d74ea2af
         * orgId : ffcf2166b02642e4bf
         */

        public String cloginid;
        public String cname;
        public String cphone;
        public String cidentity;
        public int cstate;
        public String no;
        public String orgId;

        @Override
        public String toString() {
            return "{" +
                    "cloginid='" + cloginid + '\'' +
                    ", cname='" + cname + '\'' +
                    ", cphone='" + cphone + '\'' +
                    ", cidentity='" + cidentity + '\'' +
                    ", cstate=" + cstate +
                    ", no='" + no + '\'' +
                    ", orgId='" + orgId + '\'' +
                    '}';
        }
    }
}
