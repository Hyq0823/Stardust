package com.kafka;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class KafkaConsumer {
    
    public static void main(String[] args) {
        Properties prop = new Properties();

        // prop.setProperty("zookeeper.connect", "10.161.24.231:2181,10.161.24.232:2181,10.161.24.233:2181");
//        prop.setProperty("zookeeper.connect", "10.161.11.222:2181,10.161.11.223:2181,10.161.11.224:2181");
        prop.setProperty("zookeeper.connect", "10.161.11.222:2181,10.161.11.223:2181,10.161.11.224:2181");

//        prop.put("zk.connectiontimeout.ms", "1000000");
        prop.put("zk.connectiontimeout.ms", "1000");
        prop.setProperty("group.id", "group122");
        
        ConsumerConnector consumer = Consumer.createJavaConsumerConnector(new ConsumerConfig(prop));
        Map<String, Integer> map = new HashMap<String, Integer>();
//        map.put("req.log.converted", 1);
//        map.put("dyl_tf_b_trade_item", 1);
        map.put("pinpoint-api", 1);

        Map<String, List<KafkaStream<byte[], byte[]>>> msgStreams = consumer.createMessageStreams(map);
        KafkaStream<byte[], byte[]> kafkaStreams = msgStreams.get("pinpoint-api").get(0);
        ConsumerIterator<byte[], byte[]> iterator = kafkaStreams.iterator();
        while(iterator.hasNext()) {
            String msg = new String(iterator.next().message());
            System.out.println(msg);
        }
    }
    
}