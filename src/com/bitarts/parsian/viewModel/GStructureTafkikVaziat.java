package com.bitarts.parsian.viewModel;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 7/8/12
 * Time: 9:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class GStructureTafkikVaziat {
    private String key;
    private Map<String, Long> data;

    public GStructureTafkikVaziat(String key, Map<String, Long> data) {
        this.key = key;
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map<String, Long> getData() {
        return data;
    }

    public void setData(Map<String, Long> data) {
        this.data = data;
    }
}
