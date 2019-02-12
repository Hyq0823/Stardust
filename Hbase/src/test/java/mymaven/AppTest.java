package mymaven;

import hbase.HbaseClient;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private HbaseClient hbaseClient;

    private Table baseTable;
    private Table orderTable;

    @Before
    public void before() throws Exception {
        org.apache.hadoop.conf.Configuration conf = HBaseConfiguration.create();
//        conf.set("hbase.zookeeper.quorum","127.0.0.1:2181" );
//        conf.set("zookeeper.znode.parent","/hbase-unsecure");
//        conf.set("hbase.zookeeper.quorum","10.161.11.222:2181,10.161.11.223:2181,10.161.11.224:2181" );
//        conf.set("hbase.client.keyvalue.maxsize",maxsize);

        conf.set("hbase.zookeeper.quorum","10.161.11.241" );
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("zookeeper.znode.parent", "/hbase");
        conf.set("hbase.client.start.log.errors.counter", "1");
        conf.set("hbase.client.retries.number", "1");
        conf.set("zookeeper.session.timeout", "15000");
        hbaseClient = new HbaseClient(conf);

        Connection connection = hbaseClient.getConnection();
        baseTable = connection.getTable(TableName.valueOf("test_tianyan"));
        orderTable = connection.getTable(TableName.valueOf("order_info_tbl"));
    }

    @Test
    public void firstStep() throws IOException {
        System.out.println(hbaseClient);
        Connection connection = hbaseClient.getConnection();
        Table table = connection.getTable(TableName.valueOf("test_tianyan"));
        System.out.println(table.getName());
        System.out.println(table.getTableDescriptor());
    }

    /**
     *  get.addFamily 和
     *  get.addColumn 同时指定则两个条件都会满足
     * @throws IOException
     */
    @Test
    public void get() throws IOException {
        String rowKey = "row1";
        String family = "main2";
        Get get = new Get(rowKey.getBytes());

        //指定列族
        //keyvalues={row1/main2:info2/1547728381239/Put/vlen=3/seqid=0}
        get.addFamily("main".getBytes());
        //指定具体列
        get.addColumn(family.getBytes(),"info2".getBytes());

        Result result = baseTable.get(get);
        System.out.println(result);

        //默认情况下返回所有信息
        //keyvalues={row2/main:id/1543813062349/Put/vlen=2/seqid=0, row2/main:name/1543813090552/Put/vlen=3/seqid=0}
//        keyvalues={row1/main:info/1547728357955/Put/vlen=3/seqid=0, row1/main2:info2/1547728381239/Put/vlen=3/seqid=0}

    }

    @Test
    public void scan() throws Exception {
        String family = "main";
        Scan scan = new Scan();
        scan.setRowPrefixFilter("1".getBytes()); //rowKey前缀

        scan.setCaching(10); //缓存
        scan.setBatch(2); //batch中一次性查询的条数
        ResultScanner tableScanner = orderTable.getScanner(scan);
        Result next = tableScanner.next();
        if(next == null){
            System.out.println("未查询到数据....");
            return;
        }
        String rowKey =new String(next.getRow());
        System.out.println("rowKey : " + rowKey);
        Cell[] cells = next.rawCells();
        for (Cell cell : cells){
            String qulifier = new String(CellUtil.cloneQualifier(cell));
            String value = new String(CellUtil.cloneValue(cell));
            System.out.println("列名： "+qulifier + "  列值：" + value);
        }
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
}
