package com.hbase;

import com.google.protobuf.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试Hbase SQL
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TestHbaseSql {

    @Autowired
    private HbaseClient hbaseService;

    /**
     * 测试删除、创建表
     */
    @Test
    public void testCreateTable() throws IOException, ServiceException {

        String tableName = "test_tianyan";
        String zk = "localhost:2181";
//        hbaseService.creatTable("test_tianyan", Arrays.asList("main"));
        Map data = new HashMap<>();
        data.put("provOrderId","123");
        data.put("simId","555");
        data.put("iccId","ggfdg");


//        hbaseService.scan(tableName);

//        HbaseUtils hbaseUtils = new HbaseUtils(zk);
//        Connection connection = hbaseUtils.getConnection();
//        Result result = hbaseUtils.getResult(TableName.valueOf(tableName), "1");
//        System.out.println(result);



        //删除表
        hbaseService.deleteTable(tableName);

//        //创建表
        hbaseService.creatTable(tableName, Arrays.asList("main"));


        hbaseService.put(tableName,"1","main",data);

        hbaseService.scan(tableName);

//        Result result = hbaseService.getResult(TableName.valueOf(tableName), "1");
//        System.out.println(result);


//        //hbaseService.createTableBySplitKeys("test_base", Arrays.asList("f","back"),hbaseService.getSplitKeys(null));
//
//        //插入三条数据
//        hbaseService.putData("test_tianyang","66804_000001","main",new String[]{"project_id","varName","coefs","pvalues","tvalues","create_time"},new String[]{"40866","mob_3","0.9416","0.0000","12.2293","null"});
//        hbaseService.putData("test_tianyang","66804_000002","main",new String[]{"project_id","varName","coefs","pvalues","tvalues","create_time"},new String[]{"40866","idno_prov","0.9317","0.0000","9.8679","null"});
//        hbaseService.putData("test_tianyang","66804_000003","main",new String[]{"project_id","varName","coefs","pvalues","tvalues","create_time"},new String[]{"40866","education","0.8984","0.0000","25.5649","null"});
//
//        //查询数据
//        //1. 根据rowKey查询
//        Map<String,String> result1 = hbaseService.getRowData("test_base","66804_000001");
//        System.out.println("+++++++++++根据rowKey查询+++++++++++");
//        result1.forEach((k,value) -> {
//            System.out.println(k + "---" + value);
//        });
//        System.out.println();
//
//        //精确查询某个单元格的数据
//        String str1 = hbaseService.getColumnValue("test_base","66804_000002","main","varName");
//        System.out.println("+++++++++++精确查询某个单元格的数据+++++++++++");
//        System.out.println(str1);
//        System.out.println();
//
//        //2. 遍历查询
//        Map<String,Map<String,String>> result2 = hbaseService.getResultScanner("test_base");
//        System.out.println("+++++++++++遍历查询+++++++++++");
//        result2.forEach((k,value) -> {
//            System.out.println(k + "---" + value);
//        });
    }

}