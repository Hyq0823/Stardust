package com.hbase;

import com.constants.HbaseTable;
import com.google.protobuf.ServiceException;
import org.apache.hadoop.hbase.client.HTable;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/10/28 16:51.
 * 操作Hbase的api
 */
public interface HbaseApi {

    /**
     * 创建表
     * @param tableName 表名
     * @param columnFamily 列族
     * @return
     */
    boolean creatTable(String tableName, List<String> columnFamily) throws IOException, ServiceException;


    /**
     * 删除表
     * @param tableName 表名
     * @return
     */
    boolean deleteTable(String tableName) throws IOException, ServiceException;


    /**
     * 添加数据
     * @param tableName 表名
     * @param rowKey rowKey
     * @param familyName 列族
     * @param dataMap 数据集
     */
    void put(String tableName,String rowKey,String familyName,Map<String,Object> dataMap) throws IOException, ServiceException;


    /**
     * 添加数据
     * @param table 枚举表
     * @param rowKey rowKey
     * @param dataMap 数据map
     */
    void put(HbaseTable table,String rowKey,Map<String,Object> dataMap) throws IOException, ServiceException;



    public HTable buildHtable(String tableName);


    void eableTable(String tableName);



}
