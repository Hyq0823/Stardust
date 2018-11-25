package com.constants;

import com.hbase.HbaseClient;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * HBase相关配置
 */
@Configuration
public class HBaseConfig {
//    @Value("${HBase.nodes:47.92.236.148:2181}")
   // @Value("${HBase.nodes:10.161.11.222:2181,10.161.11.223:2181,10.161.11.224:2181}")
    @Value("${HBase.nodes:127.0.0.1:2181}")
    private String nodes;

    @Value("${HBase.maxsize:10000}")
    private String maxsize;

    @Bean
    public HbaseClient getHbaseService(){
        org.apache.hadoop.conf.Configuration conf = HBaseConfiguration.create();
//        conf.set("hbase.zookeeper.quorum","127.0.0.1:2181" );
//        conf.set("zookeeper.znode.parent","/hbase-unsecure");

//        conf.set("hbase.zookeeper.quorum","10.161.11.222:2181,10.161.11.223:2181,10.161.11.224:2181" );
        conf.set("hbase.client.keyvalue.maxsize",maxsize);
        conf.set("hbase.zookeeper.quorum","10.161.11.241" );

        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("zookeeper.znode.parent", "/hbase");
        conf.set("hbase.client.start.log.errors.counter", "1");
        conf.set("hbase.client.retries.number", "1");
        conf.set("zookeeper.session.timeout", "15000");

        return new HbaseClient(conf);
    }
}