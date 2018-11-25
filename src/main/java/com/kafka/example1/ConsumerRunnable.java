package com.kafka.example1;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;
 
 public class ConsumerRunnable implements Runnable {
 
     // 每个线程维护私有的KafkaConsumer实例
     private final KafkaConsumer<String, String> consumer;
 
     public ConsumerRunnable(String brokerList, String groupId, String topic) {
         Properties props = new Properties();
         // org.apache.kafka.common.config.ConfigException:  "bootstrap.servers" which has no default value.
         props.put("bootstrap.servers", brokerList);
//         props.put("zookeeper.connect","192.168.1.107:9181");
         props.put("group.id", groupId);
         props.put("enable.auto.commit", "true");        //本例使用自动提交位移
         props.put("auto.commit.interval.ms", "1000");
         props.put("session.timeout.ms", "30000");
         props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
         props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

         //auto.offset.reset
         //smallest, 将offset设为当前所能用的最小的offset。 注意不一定是0。
         //largest, 将offset设为当前可用的最大的offset。也就是consumer将只处理最新写入的消息。 默认值。

         this.consumer = new KafkaConsumer<>(props);
//         consumer.subscribe(Arrays.asList(topic));   // 本例使用分区副本自动分配策略
     }
 
     @Override
     public void run() {
         while (true) {
//             ConsumerRecords<String, String> records = consumer.poll(200);   // 使用200ms作为获取超时时间
//             for (ConsumerRecord<String, String> record : records) {
//                 // 这里面写处理消息的逻辑
//                 System.out.println(Thread.currentThread().getName() + " consumed " + record.partition() +
//                         "th message with offset: " + record.offset());
//             }
         }
     }
 }