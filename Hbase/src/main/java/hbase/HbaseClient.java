package hbase;
import com.google.protobuf.ServiceException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/10/28 20:16.
 */
public class HbaseClient  {
    private Configuration conf;

    public Connection getConnection(){
        Connection connection = null;
        try {
            connection = ConnectionFactory.createConnection(conf);
        } catch (IOException e) {
            e.printStackTrace();
            if(connection != null){
                try {
                    connection.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            throw new RuntimeException("error get hbase connection");
        }
        return connection;
    }

    public void close(Connection connection){
        if(connection != null){
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void eableTable(String tableName){
        Connection connection = null;
        try {
            HBaseAdmin.checkHBaseAvailable(conf);
            connection = getConnection();
            Admin admin = connection.getAdmin();
            admin.enableTable(TableName.valueOf(tableName));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
    }

    public HbaseClient(Configuration conf) {
        this.conf = conf;
    }

    public void scan(String tableName){
        Connection connection = null;
        try {

            connection = getConnection();
            Scan scan = new Scan();
            Table table = connection.getTable(TableName.valueOf(tableName));
            ResultScanner scanner = table.getScanner(scan);
            for (Result result = scanner.next(); result != null; result = scanner.next()) {
                System.out.println("Found row : " + result);
                // keyvalues={1/main:iccId/1540806892366/Put/vlen=5/seqid=0, 1/main:provOrderId/1540806892366/Put/vlen=3/seqid=0, 1/main:simId/1540806892366/Put/vlen=3/seqid=0}
            }
            scanner.close();
        }catch (Exception e){
            e.printStackTrace();
            close(connection);
        }
    }

    /**
     * 获取表中某个row的result
     * @param tablename
     * @param rowkey
     * @return
     */
    public Result getResult(TableName tablename,String rowkey) {
        Result result = new Result();
        Table table = null;
        try {
            table = getConnection().getTable(tablename);
            Get get = new Get(Bytes.toBytes(rowkey));
            result = table.get(get);
        } catch (IOException e) {
           e.printStackTrace();
        }finally {
            if (table != null){
                try {
                    table.close();
                } catch (IOException e) {
                   e.printStackTrace();
                }
            }
        }
        return result;
    }


    public boolean creatTable(String tableName, List<String> columnFamilys)  {
        Connection connection = null;
        try {
            HBaseAdmin.checkHBaseAvailable(conf);
            connection = getConnection();
            Admin admin = connection.getAdmin();
            //FIXME  过时的api,在hbase2.X中会被移除 2018-10-28
            HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
            columnFamilys.forEach(columnFamily-> tableDescriptor.addFamily(new HColumnDescriptor(columnFamily)));
            admin.createTable(tableDescriptor);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close(connection);
        }
        return true;
    }

    public boolean deleteTable(String tableName)  {
        Connection connection = null;
        try {
            HBaseAdmin.checkHBaseAvailable(conf);
            connection = getConnection();
            Admin admin = connection.getAdmin();
            admin.disableTable(TableName.valueOf(tableName));
            admin.deleteTable(TableName.valueOf(tableName));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close(connection);
        }
    }

    public void put(String tableName, String rowKey, String familyName, Map<String, Object> dataMap) throws IOException, ServiceException {
        Connection connection = null;

        try {
            connection = getConnection();
            HBaseAdmin.checkHBaseAvailable(conf);
            Admin admin = connection.getAdmin();
            TableName tbObj = TableName.valueOf(tableName);
            if(!admin.tableExists(tbObj)){
                throw new RuntimeException("table not exists," + tableName);
            }
            HTable hTable = (HTable) connection.getTable(tbObj);
            Put put = new Put(Bytes.toBytes(rowKey));
            //列族，列名，列值
            dataMap.forEach((key,value)-> put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(key), Bytes.toBytes(value.toString())));
            hTable.put(put);
            hTable.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
    }


    public HTable buildHtable(String tableName) {
        try {
            Connection connection = getConnection();
            TableName tbObj = TableName.valueOf(tableName);
            HTable hTable = (HTable) connection.getTable(tbObj);
            return hTable;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("错误：can't not build htable..." + e.getMessage());
        }
    }
}
