package com.kafka;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaTools {

    /**
     * 私有静态方法，创建Kafka生产者
     *
     * @return KafkaProducer
     * @author IG
     * @Date 2017年4月14日 上午10:32:32
     * @version 1.0.0
     */
    private static KafkaProducer<String, String> createProducer() {
        Properties properties = new Properties();
//        properties.put("bootstrap.servers", "huakServer1:9092,huakServer2:9092");// 声明kafka
//        properties.put("bootstrap.servers", "192.168.1.107:9092");
//        properties.put("bootstrap.servers", "iZ8vb9asavs10215gequ1lZ:9092");
        properties.put("bootstrap.servers", "10.161.11.222:6667,10.161.11.223:6667,10.161.11.224:6667");

//        properties.put("bootstrap.servers", "10.161.24.231:9092,10.161.24.232:9092,10.161.24.233:9092");



//        properties.put("zookeeper.connect", "192.168.1.107:9181");
//        properties.put("metadata.broker.list", "192.168.1.107:9092");

        // properties.put("value.serializer",
        // "org.apache.kafka.common.serialization.ByteArraySerializer");
        // properties.put("key.serializer",
        // "org.apache.kafka.common.serialization.ByteArraySerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return new KafkaProducer<String, String>((properties));
    }

    /**
     * 传入kafka约定的topicName,json格式字符串，发送给kafka集群
     *
     * @param topicName
     * @param jsonMessage
     * @author IG
     * @Date 2017年4月14日 下午1:29:09
     * @version 1.0.0
     */
    public static void sendMessage(String topicName, String jsonMessage) {
        KafkaProducer<String, String> producer = createProducer();
        producer.send(new ProducerRecord<String, String>(topicName, jsonMessage));
        producer.close();
    }

    /**
     * 传入kafka约定的topicName,json格式字符串数组，发送给kafka集群<br>
     * 用于批量发送消息，性能较高。
     *
     * @param topicName
     * @param jsonMessages
     * @throws InterruptedException
     * @author IG
     * @Date 2017年4月14日 下午2:00:12
     * @version 1.0.0
     */
    public static void sendMessage(String topicName, String... jsonMessages) throws InterruptedException {
        KafkaProducer<String, String> producer = createProducer();
        for (String jsonMessage : jsonMessages) {
            producer.send(new ProducerRecord<String, String>(topicName, jsonMessage));
        }
        producer.close();
    }


    public static void sendMessageByParation(String topicName, String partition, String... jsonMessages) throws InterruptedException {
        KafkaProducer<String, String> producer = createProducer();
        for (String jsonMessage : jsonMessages) {
            producer.send(new ProducerRecord<String, String>(topicName,partition ,jsonMessage));
        }
        producer.close();
    }

    /**
     *
     * 传入kafka约定的topicName,Map集合，内部转为json发送给kafka集群 <br>
     * 用于批量发送消息，性能较高。
     *
     * @author IG
     * @Date 2017年4月14日 下午2:01:18
     * @version 1.0.0
     * @param topicName
     * @param mapMessageToJSONForArray
    //     */
// public static void sendMessage(String topicName, List<Map<Object, Object>> mapMessageToJSONForArray) {
//        KafkaProducer<String, String> producer = createProducer();
// for (Map<Object, Object> mapMessageToJSON : mapMessageToJSONForArray) {
//            String array = JSONObject.fromObject(mapMessageToJSON).toString();
//            producer.send(new ProducerRecord<String, String>(topicName, array));
//        }
//        producer.close();
//    }

    /**
     * 传入kafka约定的topicName,Map，内部转为json发送给kafka集群
     *
     * @author IG
     * @Date 2017年4月14日 下午1:30:10
     * @version 1.0.0
     */
// public static void sendMessage(String topicName, Map<Object, Object> mapMessageToJSON) {
//        KafkaProducer<String, String> producer = createProducer();
//        String array = JSONObject.fromObject(mapMessageToJSON).toString();
//        producer.send(new ProducerRecord<String, String>(topicName, array));
//        producer.close();
//    }
    public static void main(String[] args) throws InterruptedException {
        String[] s = new String[]{"{\"userName\":\"赵四31\",\"pwd\":\"lisi\",\"age\":13}",
                "{\"userName\":\"赵四41\",\"pwd\":\"lisi\",\"age\":14}",
                "{\"userName\":\"赵四51\",\"pwd\":\"lisi\",\"age\":15}"};

//        for (String a : s) {
//            System.out.println(a);
//            Thread.sleep(3000);
//            KafkaTools.sendMessage(topicName, jsonMessages);
//        }
//        KafkaTools.sendMessage("logstest", s);

        for(int i =0;i<10;i++){
            JSONObject json = new JSONObject();
                json.put("key1",i);
                json.put("value1","value"+ i);
                String s1 = json.toString();
//                KafkaTools.sendMessage("dyl_tf_b_trade_item",s1);
                KafkaTools.sendMessage("pinpoint-api",s1);
        }

    }

}