package com.it.rxapp_manager_android.modle;

import java.io.Serializable;
import java.util.List;

public class ListColorEntity implements Serializable {

    /**
     * rspCode : 00
     * carcolors : [{"id":"1","name":"黑色"},{"id":"2","name":"银色"},{"id":"3","name":"黄色"},{"id":"4","name":"红色"},{"id":"5","name":"蓝色"},{"id":"6","name":"绿色"},{"id":"7","name":"白色"}]
     * rspDesc : 成功
     */

    public String rspCode;
    public String rspDesc;
    public List<CarcolorsBean> carcolors;

    @Override
    public String toString() {
        return "{" +
                "rspCode='" + rspCode + '\'' +
                ", rspDesc='" + rspDesc + '\'' +
                ", carcolors=" + carcolors +
                '}';
    }

    public static class CarcolorsBean implements Serializable{
        /**
         * id : 1
         * name : 黑色
         */

        public String id;
        public String name;

        @Override
        public String toString() {
            return "{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
