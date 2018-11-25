package com.kafka.example1;

public class ConsumerMain {
 
     public static void main(String[] args) {
//         String brokerList = "10.161.24.231:9092,10.161.24.232:9092,10.161.24.233:9092";
         String brokerList = "10.161.24.231:9092,10.161.24.232:9092,10.161.24.233:9092";
         String groupId = "group2";
         String topic = "pinpoint-api";
         int consumerNum = 3;
 
         ConsumerGroup consumerGroup = new ConsumerGroup(consumerNum, groupId, topic, brokerList);
         consumerGroup.execute();
     }
 }