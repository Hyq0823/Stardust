package com.hbase;

import com.google.protobuf.ServiceException;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.*;

/**
 * 测试Hbase SQL
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TestHbaseSql {

    @Autowired
    private HbaseClient hbaseService;

    @Test
    public void scanTest() throws Exception {
        String tableName = "order_info_tbl";
        Connection connection = hbaseService.getConnection();
        Scan scan = new Scan();


        Filter filter = new SingleColumnValueFilter(Bytes
                .toBytes("main"), Bytes.toBytes("iccId"),
                 CompareFilter.CompareOp.EQUAL, Bytes
                .toBytes("8986011880212939852"));
        scan.setFilter(filter);
        Table table = connection.getTable(TableName.valueOf(tableName));
        ResultScanner scanner = table.getScanner(scan);

        for (Result result = scanner.next(); result != null; result = scanner.next()) {
            System.out.println("获得到rowkey:" + new String(result.getRow()));
//          System.out.println("column family: "+new String(keyValue.getFamily()));
            for (KeyValue keyValue : result.raw()) {

                System.out.println("列：" + new String(keyValue.getQualifier())
                        + "====值:" + new String(keyValue.getValue()));
            }
        }

    }


    /**
     * 单值查询
     */
    @Test
    public void testGetSingleResult(){
        String tableName = "order_info_tbl";
        Result result = hbaseService.getResult(TableName.valueOf(tableName), "1018111859949971");

//        KeyValue[] raw = result.raw();
        Cell[] cells = result.rawCells();

        for(Cell cell : cells){
            System.out.print(new String(cell.getRow()) + " " );
            System.out.print(new String(cell.getFamily()) +" ");
            System.out.print(cell.getTimestamp() + " " );

            //这两个值有用 key value
            System.out.print(new String(cell.getQualifier()) + " " );
            System.out.println(new String(cell.getValue()));

        }

    }


    /**
     * 让表有效
     */
    @Test
    public void enableTable() throws IOException, ServiceException {
        String tableName = "sence_2i2c";
//        String tableName = "process_2i2c_finish";
        hbaseService.eableTable(tableName);

        //org.apache.hadoop.hbase.TableNotEnabledException: process_2i2c_finish
    }


    /**
     * 删除表
     */
    @Test
    public void deleteTable() throws IOException, ServiceException {
//        String tableName = "sence_2i2c";
        String tableName = "process_2i2c_finish";
        hbaseService.deleteTable(tableName);



        //org.apache.hadoop.hbase.TableNotEnabledException: process_2i2c_finish
    }


    /**
     * 创建表
     */
    @Test
    public void createTable() throws IOException, ServiceException {
        String tableName = "sence_2i2c";
        hbaseService.creatTable(tableName, Arrays.asList("main"));
    }


    /**
     * 删除，并且重新创建表
     */
    @Test
    public void deleteAndRebuildTable(){
        List<String> tables = new ArrayList<>();
        tables.add("order_info_tbl");

        tables.add("sence_2i2c");
        tables.add("process_2i2c_openuser");
        tables.add("process_2i2c_active");
        tables.add("process_2i2c_iom");
        tables.add("process_2i2c_back");
        tables.add("process_2i2c_finish");
        tables.add("process_2i2c_syncacctuser");

        tables.add("tache_2i2c_newusertrades");
        tables.add("tache_2i2c_newusers");
        tables.add("tache_2i2c_carddatas");
        tables.add("tache_2i2c_remotecardstates");
        tables.add("tache_2i2c_carddatasync");

        tables.add("tache_2i2c_newactivateshops");

        tables.forEach(tableName->{
            hbaseService.deleteTable(tableName);
            hbaseService.creatTable(tableName,Arrays.asList("main"));
        });

    }




    /**
     * 根据RowKey前缀清除表数据
     * @throws IOException
     */
    @Test
    public void testDeleteByRowKeyPrefix() throws IOException {
        String rowKeyPrefix = "1";
        HTable hTable = hbaseService.buildHtable("sence_2i2c");
        Scan scan = new Scan();
//        scan.setFilter(new PrefixFilter(rowKeyPrefix.getBytes()));
        scan.setRowPrefixFilter(rowKeyPrefix.getBytes());
        ResultScanner scanner = hTable.getScanner(scan);
        for(Result r : scanner) {
//            System.out.println("获得到rowkey:" + new String(r.getRow()));
            KeyValue[] kv = r.raw();
            for (int i = 0; i < kv.length; i++) {
                KeyValue keyValue = kv[i];
//                System.out.println("列：" + new String(keyValue.getFamily())
//                        + "====值:" + new String(keyValue.getValue()));
                Delete delete = new Delete(keyValue.getRow());
                hTable.delete(delete);
            }
        }
        hTable.close();
    }

    /**
     * 测试删除、创建表
     */
    @Test
    public void testCreateTable() throws IOException, ServiceException {

        String tableName = "tache_2i2c_newusers";
        String zk = "10.161.11.241:2181";
//        hbaseService.creatTable("test_tianyan", Arrays.asList("main"));
        Map data = new HashMap<>();
//        data.put("provOrderId","123");
//        data.put("simId","555");
//        data.put("iccId","ggfdg");

        data.put("testsetestset","fsdfsdfsdf");

        hbaseService.put(tableName,"1","main",data);


//        hbaseService.scan(tableName);

//        HbaseUtils hbaseUtils = new HbaseUtils(zk);
//        Connection connection = hbaseUtils.getConnection();
//        Result result = hbaseUtils.getResult(TableName.valueOf(tableName), "1");
//        System.out.println(result);



        //删除表
//        hbaseService.deleteTable(tableName);

//        //创建表
//        hbaseService.creatTable(tableName, Arrays.asList("main"));




//        hbaseService.scan(tableName);

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