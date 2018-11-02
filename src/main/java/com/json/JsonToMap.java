package com.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/10/16 17:24.
 */
public class JsonToMap {
    public static void main(String[] args) throws Exception {
        //String json = "{\"appkey\":\"mall.sub\",\"apptx\":\"181015083259152841\",\"bizkey\":\"TS-4G-004\",\"method\":\"ecaop.trades.sell.mob.newu.opencarddate.syn\",\"msg\":{\"channelId\":\"10a2180\",\"channelType\":\"1030100\",\"city\":\"107\",\"district\":\"10a1gi\",\"numId\":\"16647631378\",\"opeSysType\":\"2\",\"operatorId\":\"DWSSC001\",\"ordersId\":\"7818093038780889\",\"provOrderId\":\"1018101586993860\",\"province\":\"10\",\"simCardNo\":[{\"capacityTypeCode\":\"1000101\",\"cardData\":\"MIIBmQYJKoZIhvcNAQcDoIIBijCCAYYCAQAxggESMIIBDgIBADB3MHAxCzAJBgNV\\r\\nBAYTAkNOMQ4wDAYDVQQIDAVqaWxpbjESMBAGA1UEBwwJY2hhbmdjaHVuMQwwCgYD\\r\\nVQQKDANDTkMxDjAMBgNVBAsMBUNOQ0NBMR8wHQYDVQQDDBZDSElOQSBORVRDT00g\\r\\nQ0xBU1MzIENBAgM2w0owDQYJKoZIhvcNAQEBBQAEgYA5I5TChFpfhwB6DjuGWzvx\\r\\n6bERseznB99EP0mtMSfRUsDf5rjhzfvC4Ztfpxn0NjLE76awVFmwDFF4qDZS/N5u\\r\\naPX/nDlj8nZ8H0E2lR2WLd79Y4OpUwX9R8SplbMcLm3RMQK6nhdNGmKlm80ciUSf\\r\\nnSqhNIwduNh/Bapai++MdDBrBgkqhkiG9w0BBwEwFAYIKoZIhvcNAwcECDWIzML6\\r\\n6TabgEi7fkssvgpK2YhLeYFTvrHgwzC0QYN82ipBYt/+cuMfdpwOocnFoKle051E\\r\\n4ogbGTBn7qcmJ9gjbya7yRXDWOan7sV5IWGCRX0=\",\"cardDataProcId\":\"2018101508325684692547\",\"cardType\":\"04\",\"imsi\":\"460097634106389\",\"resKindCode\":\"59\",\"simId\":\"8986011880204882684\"}],\"taxType\":\"3\"},\"timestamp\":\"2018-10-15 08:32:59\"}";

        //案例1 , 只平铺msg
        //样例1
        String example1 = "{\"appkey\":\"mall.sub\",\"apptx\":\"181013001288659039\",\"bizkey\":\"TS-4G-004\",\"method\":\"ecaop.trades.sell.mob.newu.open.app\",\"msg\":{\"acctInfo\":[{\"accountPayType\":\"10\",\"createOrExtendsAcct\":\"0\"}],\"channelId\":\"51b13xh\",\"channelType\":\"1030100\",\"chkBlcTag\":\"1\",\"city\":\"565\",\"customerInfo\":[{\"authTag\":\"1\",\"custType\":\"0\",\"newCustomerInfo\":[{\"certAdress\":\"中华人民共和国联通\",\"certExpireDate\":\"20500101\",\"certNum\":\"441781200207294110\",\"certType\":\"02\",\"contactAddress\":\"中华人民共和国联通\",\"contactPerson\":\"麦富淇\",\"contactPhone\":\"13620479087\",\"custType\":\"01\",\"customerName\":\"麦富淇\"}],\"realNameType\":\"0\"}],\"delayDealTag\":\"0\",\"delayTag\":\"1\",\"district\":\"512097\",\"eModeCode\":\"Q\",\"numId\":[{\"proKey\":\"999991404620287\",\"serialNumber\":\"13226390397\"}],\"opeSysType\":\"2\",\"operatorId\":\"DZYJJ008\",\"ordersId\":\"3018101368435452\",\"para\":[{\"paraId\":\"SPEED\",\"paraValue\":\"300\"}],\"province\":\"51\",\"recomDepartId\":\"51b0kz4\",\"recomPersonId\":\"5102239253\",\"recomPersonName\":\"王迪\",\"userInfo\":[{\"bipType\":\"1\",\"checkType\":\"03\",\"groupFlag\":\"0\",\"is3G\":\"2\",\"packageTag\":\"0\",\"payInfo\":{\"payType\":\"10\"},\"product\":[{\"productId\":\"90063345\",\"productMode\":\"1\"}],\"serType\":\"1\",\"userType\":\"1\"}]},\"timestamp\":\"2018-10-13 09:45:21\"}";

        //样例2
        String example2 = "{\"appkey\":\"mall.sub\",\"apptx\":\"181013093746330577\",\"bizkey\":\"TS-4G-004\",\"method\":\"ecaop.trades.sell.mob.newu.open.sub\",\"msg\":{\"acceptanceReqTag\":\"1\",\"channelId\":\"38b0fhf\",\"channelType\":\"1030100\",\"city\":\"390\",\"district\":\"0000000000\",\"opeSysType\":\"2\",\"operatorId\":\"5920455602\",\"ordersId\":\"3618101164029285\",\"origTotalFee\":\"0\",\"payInfo\":{\"payType\":\"10\"},\"provOrderId\":\"3818101354070446\",\"province\":\"38\"},\"timestamp\":\"2018-10-13 09:37:46\"}";

        //样例3
        String example3 = "{\"appkey\":\"mall.sub\",\"apptx\":\"181013001288658913\",\"bizkey\":\"TS-4G-004\",\"method\":\"ecaop.trades.sell.mob.comm.carddate.autoqry\",\"msg\":{\"allocationFlag\":\"0\",\"busiType\":\"32\",\"cardType\":\"04\",\"cardUseType\":\"0\",\"channelId\":\"38b0akm\",\"channelType\":\"1030100\",\"city\":\"480\",\"district\":\"000000\",\"iccid\":\"89860118801120251633\",\"numId\":\"18559053968\",\"opeSysType\":\"2\",\"operatorId\":\"5950773201\",\"province\":\"38\",\"reDoTag\":\"0\",\"userType\":\"1\"},\"timestamp\":\"2018-10-13 09:38:26\"}";

        //样例4
        String example4 = "{\"appkey\":\"mall.sub\",\"apptx\":\"181013094302744862\",\"bizkey\":\"TS-4G-004\",\"method\":\"ecaop.trades.sell.mob.comm.cardres.notify\",\"msg\":{\"activeId\":\"2018101309430040555150\",\"capacityTypeCode\":\"1000101\",\"cardUseType\":\"0\",\"channelId\":\"59b2gh9\",\"channelType\":\"1030100\",\"city\":\"598\",\"district\":\"59a0yi\",\"iccid\":\"8986011880154783917\",\"imsi\":\"460016807327027\",\"opeSysType\":\"2\",\"operatorId\":\"244998\",\"ordersId\":\"205725121552756736\",\"procId\":\"2018101309430040555150\",\"province\":\"59\",\"reasonId\":\"0\",\"resKindCode\":\"59\"},\"timestamp\":\"2018-10-13 09:43:02\"}";

        //样例5
        String example5 = "{\"appkey\":\"mall.sub\",\"apptx\":\"181013500062076262\",\"bizkey\":\"TS-4G-004\",\"method\":\"ecaop.trades.sell.mob.newu.opencarddate.syn\",\"msg\":{\"channelId\":\"51b2aub\",\"channelType\":\"1030100\",\"city\":\"510\",\"district\":\"51a0np\",\"feeInfo\":[{\"feeCategory\":\"4\",\"feeDes\":\"多缴预存\",\"feeId\":\"99\",\"origFee\":\"0\",\"realFee\":\"0\",\"reliefFee\":\"0\",\"reliefResult\":\"无减免原因\"}],\"numId\":\"13026890294\",\"opeSysType\":\"2\",\"operatorId\":\"TJGZJ025\",\"ordersId\":\"5218101267422046\",\"provOrderId\":\"5118101369110218\",\"province\":\"51\",\"simCardNo\":[{\"capacityTypeCode\":\"1000101\",\"cardData\":\"MIIBmQYJKoZIhvcNAQcDoIIBijCCAYYCAQAxggESMIIBDgIBADB3MHAxCzAJBgNV\\r\\nBAYTAkNOMQ4wDAYDVQQIDAVqaWxpbjESMBAGA1UEBwwJY2hhbmdjaHVuMQwwCgYD\\r\\nVQQKDANDTkMxDjAMBgNVBAsMBUNOQ0NBMR8wHQYDVQQDDBZDSElOQSBORVRDT00g\\r\\nQ0xBU1MzIENBAgM2w0owDQYJKoZIhvcNAQEBBQAEgYBewsJnx+GHgCwXeN0j6JSI\\r\\nweXzGAfD7ByXhF36LyivTW7aiMBmfl3Sitj+gYcBUKyvKtoQ2AygeZoiCHZ+xJUz\\r\\nbVovCPkvWjWfeg+J/mIYe7qm+I4vJ0eM1CRBCqhEhuBXNDKVwM/NoMzO2QHY+hNf\\r\\n1yvCEFgXKbx6xEdtZNOjMTBrBgkqhkiG9w0BBwEwFAYIKoZIhvcNAwcECM4KWQ1z\\r\\nT/HtgEiM9XtGwfIaksTq9X6qFLw+U91GNNjDSUKMgyhwkROzA0Zp/rYZKZRQQVvF\\r\\nf9ZCJZU+EoOaKqslMml3kKakxMJ8jTbkC6UUWHU=\",\"cardDataProcId\":\"2018101309373813027356\",\"cardFee\":\"\",\"cardType\":\"04\",\"imsi\":\"460016892069154\",\"resKindCode\":\"59\",\"simId\":\"8986011880124451213\"}],\"taxBatchNo\":\"\",\"taxNo\":\"\",\"taxType\":\"3\"},\"timestamp\":\"2018-10-13 09:37:40\"}";


        String complete1 = "{\"response\":{\"timestamp\":1539395122.399,\"size_bytes\":\"156\",\"response_time\":0.534,\"headers\":{\"x-application-context\":\"2i2c_newusertrades:prod:8080\",\"content-type\":\"application\\/json;charset=UTF-8\",\"date\":\"Sat, 13 Oct 2018 01:45:22 GMT\",\"via\":\"kong\\/0.11.2\",\"connection\":\"close\",\"x-kong-proxy-latency\":\"0\",\"x-kong-upstream-latency\":\"533\",\"content-length\":\"156\"},\"body\":\"{\\\"bssOrderId\\\":\\\"5118101369144243\\\",\\\"msg\\\":\\\"成功\\\",\\\"totalFee\\\":\\\"0\\\",\\\"txid\\\":\\\"a72482c6bc97^1537453613026^2531296\\\",\\\"provOrderId\\\":\\\"5118101369144243\\\",\\\"status\\\":\\\"0000\\\"}\",\"status\":200},\"request\":{\"timestamp\":1539395121.865,\"form\":{},\"url\":\"http:\\/\\/marathon-lb-external.marathon.mesos:8000\\/2i2c\\/newusertrades\\/\",\"headers\":{\"host\":\"marathon-lb-external.marathon.mesos\",\"content-length\":\"1206\",\"x-forwarded-for\":[\"10.20.25.101\",\"10.1.0.100\"],\"user-agent\":\"Apache-HttpClient\\/4.2.3 (java 1.5)\",\"x-forwarded-port\":\"80\",\"content-encoding\":\"UTF-8\",\"content-type\":\"application\\/json\"},\"body\":\"{\\\"appkey\\\":\\\"mall.sub\\\",\\\"apptx\\\":\\\"181013001288659039\\\",\\\"bizkey\\\":\\\"TS-4G-004\\\",\\\"method\\\":\\\"ecaop.trades.sell.mob.newu.open.app\\\",\\\"msg\\\":{\\\"acctInfo\\\":[{\\\"accountPayType\\\":\\\"10\\\",\\\"createOrExtendsAcct\\\":\\\"0\\\"}],\\\"channelId\\\":\\\"51b13xh\\\",\\\"channelType\\\":\\\"1030100\\\",\\\"chkBlcTag\\\":\\\"1\\\",\\\"city\\\":\\\"565\\\",\\\"customerInfo\\\":[{\\\"authTag\\\":\\\"1\\\",\\\"custType\\\":\\\"0\\\",\\\"newCustomerInfo\\\":[{\\\"certAdress\\\":\\\"中华人民共和国联通\\\",\\\"certExpireDate\\\":\\\"20500101\\\",\\\"certNum\\\":\\\"441781200207294110\\\",\\\"certType\\\":\\\"02\\\",\\\"contactAddress\\\":\\\"中华人民共和国联通\\\",\\\"contactPerson\\\":\\\"麦富淇\\\",\\\"contactPhone\\\":\\\"13620479087\\\",\\\"custType\\\":\\\"01\\\",\\\"customerName\\\":\\\"麦富淇\\\"}],\\\"realNameType\\\":\\\"0\\\"}],\\\"delayDealTag\\\":\\\"0\\\",\\\"delayTag\\\":\\\"1\\\",\\\"district\\\":\\\"512097\\\",\\\"eModeCode\\\":\\\"Q\\\",\\\"numId\\\":[{\\\"proKey\\\":\\\"999991404620287\\\",\\\"serialNumber\\\":\\\"13226390397\\\"}],\\\"opeSysType\\\":\\\"2\\\",\\\"operatorId\\\":\\\"DZYJJ008\\\",\\\"ordersId\\\":\\\"3018101368435452\\\",\\\"para\\\":[{\\\"paraId\\\":\\\"SPEED\\\",\\\"paraValue\\\":\\\"300\\\"}],\\\"province\\\":\\\"51\\\",\\\"recomDepartId\\\":\\\"51b0kz4\\\",\\\"recomPersonId\\\":\\\"5102239253\\\",\\\"recomPersonName\\\":\\\"王迪\\\",\\\"userInfo\\\":[{\\\"bipType\\\":\\\"1\\\",\\\"checkType\\\":\\\"03\\\",\\\"groupFlag\\\":\\\"0\\\",\\\"is3G\\\":\\\"2\\\",\\\"packageTag\\\":\\\"0\\\",\\\"payInfo\\\":{\\\"payType\\\":\\\"10\\\"},\\\"product\\\":[{\\\"productId\\\":\\\"90063345\\\",\\\"productMode\\\":\\\"1\\\"}],\\\"serType\\\":\\\"1\\\",\\\"userType\\\":\\\"1\\\"}]},\\\"timestamp\\\":\\\"2018-10-13 09:45:21\\\"}\",\"method\":\"POST\"}}";

        JsonToMap obj = new JsonToMap();
//        Map<String,Object> result1 = obj.process(example1);
//        Map<String,Object> result2 = obj.process(example2);
//        Map<String,Object> result3 = obj.process(example3);
//        Map<String,Object> result4 = obj.process(example4);
//        Map<String,Object> result5 = obj.process(example5);

        long start = System.currentTimeMillis();
        for(int i=0;i<10000;i++){
            long innerStart = System.currentTimeMillis();
            Map<String,Object> results = obj.process(complete1);
        }
        System.out.println("耗时： " + (System.currentTimeMillis() - start) + "  ms");


//        Map<String,Object> total = new HashMap<>();
//        total.putAll(result1);
////        total.putAll(result2);
////        total.putAll(result3);
////        total.putAll(result4);
////        total.putAll(result5);

//            total.putAll(results);
//        for(Map.Entry<String,Object> me : results.entrySet()){
//            System.out.println(me.getKey() + " = " + me.getValue());
//
//        }
//
//
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode jsonNode = (JsonNode) mapper.readTree(json);
//        obj.processObjectNode(jsonNode);
    }


    public  void parse(Map<String,Object> result,Object json,List<String> names){
        if(json instanceof JSONObject){
            JSONObject jsonObj = (JSONObject) json;
            for(Map.Entry<String,Object> me : jsonObj.entrySet()){
                String currentKey = me.getKey();
                Object value = me.getValue();

                names.add(currentKey);
                String key = String.join(".", names);
                result.put(key,value);
            }
        }
        if(json instanceof JSONArray){
            JSONArray array = (JSONArray) json;
            if(!array.isEmpty()){
                Object o = array.get(0);
               parse(result,o,names);
            }
        }

    }

  public Map<String,Object> process(String jsonStr){
      JSONObject jsonObj = JSON.parseObject(jsonStr);
      Map<String,Object> result = new HashMap<>();
      processJsonObj(result,jsonObj,new ArrayList<>());
      return result;
  }

  public void processJsonObj(Map<String,Object> result,JSONObject obj,List<String> names){
      Set<Map.Entry<String, Object>> node = obj.entrySet();
      Iterator<Map.Entry<String,Object>> iterator = node.iterator();
      iterator.forEachRemaining(item -> itemAppender(result,item,names));
  }

    private void itemAppender(Map<String, Object> result, Map.Entry<String, Object> item, List<String> names) {
        List<String> nameCopy = new ArrayList<>();
        nameCopy.addAll(names);

        nameCopy.add(item.getKey());
        Object value = item.getValue();
        if(value instanceof String && value.toString().startsWith("{")){
            value = JSONObject.parseObject(value.toString());
        }

        if(value instanceof JSONObject){
            JSONObject obj = (JSONObject) value;
            Set<Map.Entry<String, Object>> node = obj.entrySet();
            Iterator<Map.Entry<String,Object>> iterator = node.iterator();
            iterator.forEachRemaining(itemChild -> itemAppender(result,itemChild,nameCopy));
            return;
        }
        if(value instanceof JSONArray){
            JSONArray array = (JSONArray) value;
            if(array.isEmpty()){
                return;
            }
            Object o = array.get(0);
            if(o instanceof JSONObject){
                JSONObject obj = (JSONObject) o;
                processJsonObj(result,obj,nameCopy);
            }
            return;
        }
        String name = nameCopy.stream().collect(Collectors.joining("."));
        result.put(name, value);
    }


    public void processJsonString(String jsonString) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = (ArrayNode) mapper.readTree(jsonString);
        processArrayNode(arrayNode);
    }

    private void processObjectNode(JsonNode jsonNode) {
        Map<String, String> result = new HashMap<>();
        Iterator<Map.Entry<String, JsonNode>> iterator = jsonNode.fields();
        iterator.forEachRemaining(node -> mapAppender(result, node, new ArrayList<String>()));
        System.out.println(result);
    }

    private void processArrayNode(ArrayNode arrayNode) {
        for (JsonNode jsonNode : arrayNode) {
            processObjectNode(jsonNode);
        }
    }

    private void mapAppender(Map<String, String> result, Map.Entry<String, JsonNode> node, List<String> names) {
        names.add(node.getKey());
        if (node.getValue().isTextual()) {
            String name = names.stream().collect(Collectors.joining("."));
            result.put(name, node.getValue().asText());
        } else if (node.getValue().isArray()) {
            processArrayNode((ArrayNode) node.getValue());
        } else if (node.getValue().isNull()) {
//            String name = names.stream().collect(Collectors.joining("."));
//            result.put(name, null);
        } else {
            node.getValue().fields()
                    .forEachRemaining(nested -> mapAppender(result, nested, new ArrayList<>(names)));
        }
    }
}
