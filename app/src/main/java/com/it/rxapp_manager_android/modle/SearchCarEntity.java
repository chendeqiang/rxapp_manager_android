package com.it.rxapp_manager_android.modle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by deqiangchen on 2018/10/8 21:51
 */

public class SearchCarEntity implements Serializable {

    /**
     * value : c88023a470984b479a
     * label : 一汽-大众-大众凌度
     */

    public String value;
    public String label;

    @Override
    public String toString() {
        return "{" +
                "value='" + value + '\'' +
                ", label='" + label + '\'' +
                '}';
    }


}
