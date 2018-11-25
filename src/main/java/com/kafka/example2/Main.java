package com.kafka.example2;

public class Main {
 
     public static void main(String[] args) {
         String brokerList = "10.161.24.231:9092,10.161.24.232:9092,10.161.24.233:9092";
         String groupId = "group2";
         String topic = "req.log.converted";
         int workerNum = 3;
 
         ConsumerHandler consumers = new ConsumerHandler(brokerList, groupId, topic);
         consumers.execute(workerNum);
         try {
             Thread.sleep(1000000);
         } catch (InterruptedException ignored) {}
         consumers.shutdown();
     }
 }