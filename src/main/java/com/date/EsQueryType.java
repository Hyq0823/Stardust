package com.date;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/9/28 17:19.
 */
public enum EsQueryType {
    SEARCH("_search")
    ,COUNT("_count");

    private final String value;
    EsQueryType(String value) {
        this.value = value;
    }
    public String value(){
        return this.value;
    }
}
