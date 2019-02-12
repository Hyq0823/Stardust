package com.date;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/9/28 17:19.
 */
public enum EsIndex {
    SCENE_CONTROL("index_scene_control")
    ,SCENE_TACHE_FINISH("index_scene_tache_finish");

    private final String value;
    EsIndex(String value) {
        this.value = value;
    }
    public String value(){
        return this.value;
    }
}
