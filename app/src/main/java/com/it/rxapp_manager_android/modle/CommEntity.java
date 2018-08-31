package com.it.rxapp_manager_android.modle;

/**
 * Created by deqiangchen on 2018/8/29 13:31
 */

public class CommEntity {
    /**
     * rspCode : 00
     * rspDesc : 成功
     */

    public String rspCode;
    public String rspDesc;

    @Override
    public String toString() {
        return "{" +
                "rspCode='" + rspCode + '\'' +
                ", rspDesc='" + rspDesc + '\'' +
                '}';
    }
}
