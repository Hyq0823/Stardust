package com.hl.hlf;


import java.util.List;
import java.util.Map;

public interface GoodsMapper {
    public List<Map> selectAllGoods() throws Exception;
    public Long testCount(String id);

}