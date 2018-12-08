package com.json;

import java.text.MessageFormat;
import java.text.ParseException;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/12/6 14:16.
 */
public class JsonConverted {
    public static void main(String[] args) throws ParseException {
        String pattern = "%d{YYYY-MM-dd HH:mm:ss:SSS} %property{app.name} [%thread] %-5level %logger{36}[%L] [TxId : %X{PtxId} , SpanId : %X{PspanId}]- %msg%n";
        String testPattern = "{0,date,YYYY-MM-dd HH:mm:ss:SSS} {1} [{2}] {3}  {4} [TxId : {5}, SpanId : {6}]- {7}";

        String str11 = "2018-12-04 13:37:05:945 2i2c_newusertrades [http-nio-8080-exec-4] INFO  http.wire-log[79] [TxId :  , SpanId : ]- {\"origin\":\"remote\",\"type\":\"request\",\"correlation\":\"f3847411ac6d278d\",\"protocol\":\"HTTP/1.1\",\"remote\":\"0:0:0:0:0:0:0:1\",\"method\":\"POST\",\"uri\":\"http://localhost:8080/2i2c/newusertrades/\",\"headers\":{\"accept\":[\"*/*\"],\"accept-encoding\":[\"gzip, deflate\"],\"cache-control\":[\"no-cache\"],\"connection\":[\"keep-alive\"],\"content-length\":[\"1623\"],\"content-type\":[\"application/json\"],\"cookie\":[\"JSESSIONID=B8CAF03CF7A696D7699DB374278FF674\"],\"host\":[\"localhost:8080\"],\"jwt\":[\"${ACESS_TOKEN}\"],\"postman-token\":[\"2f880280-fae5-46e1-9dd4-4410da4c5a4d\"],\"user-agent\":[\"PostmanRuntime/6.1.6\"]},\"body\":{\"msg\":{\"userInfo\":[{\"product\":[{\"productId\":\"90438554\",\"productMode\":\"1\"}]}],\"recomPersonName\":\"www.10010.com\",\"city\":\"110\",\"numId\":[{\"proKey\":\"999993861779815\",\"serialNumber\":\"18516919501\"}],\"useCustInfo\":[{\"useCustPsptAddress\":\"北京市海淀区软件园二号路尚东数字谷B区2号楼\",\"useCustPsptType\":\"02\",\"useCustPsptCode\":\"513122198805100010\",\"itmPrdRespobsible\":\"1\",\"useCustName\":\"杨剑超\"}],\"simCardNo\":[{\"materialCode\":\"8601500009\",\"imsi\":\"460016911505428\",\"ki\":\"MIIBmQYJKoZIhvcNAQcDoIIBijCCAYYCAQAxggESMIIBDgIBADB3MHAxCzAJBgNV\\r\\nBAYTAkNOMQ4wDAYDVQQIDAVqaWxpbjESMBAGA1UEBwwJY2hhbmdjaHVuMQwwCgYD\\r\\nVQQKDANDTkMxDjAMBgNVBAsMBUNOQ0NBMR8wHQYDVQQDDBZDSElOQSBORVRDT00g\\r\\nQ0xBU1MzIENBAgM03a4wDQYJKoZIhvcNAQEBBQAEgYCYu9lDOKXN5y3RT7dFcwyB\\r\\nJCzrEU7Ks0QW0AdU8bZZdKVtZvzQkNMli3I0HbR+xrziFiTsJL4GAwLm3jsMAwAY\\r\\nEELLE7BsAIzTysR1eOZMO2XIBrQnSgGUPGlnaKbqpGHUTehPVEGgAoV5l87BlL8U\\r\\n5IGPw0Kmv1jg9G5+5kPaCzBrBgkqhkiG9w0BBwEwFAYIKoZIhvcNAwcECCTCxi4T\\r\\nBc3WgEheGSYjIKBmOZzv/e9iddTO3XDqSqqlixKN3M9DVI78tm31up2nadx5zdDj\\r\\niwNb8qnWnfljo5Ir4JAlRZ5OAEsW7Tyyp9D4a30=\",\"simId\":\"8986011881100000806\"}],\"customerInfo\":[{\"newCustomerInfo\":[{\"custType\":\"02\",\"certType\":\"07\",\"certAdress\":\"北京时东城区建国门内大街18号办一1106单元\",\"certNum\":\"9930152471\",\"groupId\":\"1111000000000059378\"}]}],\"channelType\":\"1030100\",\"chkBlcTag\":\"1\",\"recomDepartId\":\"11a0399\",\"isSecret\":\"1\",\"province\":\"11\",\"district\":\"11a01s\",\"operatorId\":\"chixq3\",\"channelId\":\"11a0399\",\"isEsim\":\"1\",\"recomPersonId\":\"11012111012111751175\"},\"method\":\"newusertrades\",\"appkey\":\"mall.sub\",\"apptx\":\"181126000001001589\",\"timestamp\":\"2018-11-26 10:11:55\"}}";
        String str22 = "2018-12-04 13:37:09:698 2i2c_newusertrades [http-nio-8080-exec-4] INFO  http.wire-log[84] [TxId :  , SpanId : ]- {\"origin\":\"local\",\"type\":\"response\",\"correlation\":\"f3847411ac6d278d\",\"duration\":3919,\"protocol\":\"HTTP/1.1\",\"status\":560,\"headers\":{\"Content-Type\":[\"application/json;charset=UTF-8\"],\"Date\":[\"Tue, 04 Dec 2018 05:37:09 GMT\"],\"Transfer-Encoding\":[\"chunked\"],\"X-Application-Context\":[\"2i2c_newusertrades:local\"]},\"body\":{\"msg\":\"调用规则校验微服务失败：对不起，此号码已正常在用，不允许开户！\",\"code\":\"3000\",\"txid\":\"TxidError0000!\",\"detail\":\"调用规则校验微服务失败：对不起，此号码已正常在用，不允许开户！\",\"status\":\"1000\"}}";
        String str3 = "2018-12-05 15:10:31:840 2i2c_newusertrades [http-nio-8080-exec-3] INFO  http.wire-log[79] [TxId : ebe030c5ff7a^1543993452084^83 , SpanId : -6204141714460300866]- {\"origin\":\"remote\",\"type\":\"request\",\"correlation\":\"f4fa576d6a70cb46\",\"protocol\":\"HTTP/1.1\",\"remote\":\"10.251.12.248\",\"method\":\"GET\",\"uri\":\"http://10.124.143.37:29874/\",\"headers\":{\"accept\":[\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\"],\"accept-encoding\":[\"gzip, deflate\"],\"accept-language\":[\"zh-CN,zh;q=0.8\"],\"connection\":[\"keep-alive\"],\"host\":[\"10.124.143.37:29874\"],\"referer\":[\"http://10.124.142.85/marathon/ui/\"],\"upgrade-insecure-requests\":[\"1\"],\"user-agent\":[\"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 UBrowser/6.2.4094.1 Safari/537.36\"]}}";

        MessageFormat messageFormat = new MessageFormat(testPattern);
        Object[] objs = messageFormat.parse(str3);
        for(int index = 0;index < objs.length;index++){
            System.out.println(index + "  " + objs[index]);
        }

    }
}
