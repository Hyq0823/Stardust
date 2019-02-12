package com.json;

import com.alibaba.fastjson.JSONObject;

import java.text.ParseException;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/12/6 14:16.
 */
public class JsonConverted {
    public static void main(String[] args) throws ParseException {
//        String target = "ebe030c5ff7a^1543993452084^83 ";
//        System.out.println(target.replaceAll(" ",""));

        String pattern = "%d{YYYY-MM-dd HH:mm:ss:SSS} %property{app.name} [%thread] %-5level %logger{36}[%L] [TxId : %X{PtxId} , SpanId : %X{PspanId}]- %msg%n";
        String testPattern = "{0,date,YYYY-MM-dd HH:mm:ss:SSS} {1} [{2}] {3}  {4}[{5}] [TxId : {6}, SpanId : {7}]- {8}";

        String str22 = "2018-12-04 13:37:09:698 2i2c_newusertrades [http-nio-8080-exec-4] INFO  http.wire-log[84] [TxId :  , SpanId : ]- {\"origin\":\"local\",\"type\":\"response\",\"correlation\":\"f4fa576d6a70cb46\",\"duration\":3919,\"protocol\":\"HTTP/1.1\",\"status\":560,\"headers\":{\"Content-Type\":[\"application/json;charset=UTF-8\"],\"Date\":[\"Tue, 04 Dec 2018 05:37:09 GMT\"],\"Transfer-Encoding\":[\"chunked\"],\"X-Application-Context\":[\"2i2c_newusertrades:local\"]},\"body\":{\"msg\":\"调用规则校验微服务失败：对不起，此号码已正常在用，不允许开户！\",\"code\":\"3000\",\"txid\":\"TxidError0000!\",\"detail\":\"调用规则校验微服务失败：对不起，此号码已正常在用，不允许开户！\",\"status\":\"1000\"}}";
        String str3 = "2018-12-05 15:10:31:840 2i2c_newusertrades [http-nio-8080-exec-3] INFO  http.wire-log[79] [TxId : ebe030c5ff7a^1543993452084^83 , SpanId : -6204141714460300866]- {\"origin\":\"remote\",\"type\":\"request\",\"correlation\":\"f4fa576d6a70cb46\",\"protocol\":\"HTTP/1.1\",\"remote\":\"10.251.12.248\",\"method\":\"GET\",\"uri\":\"http://10.124.143.37:29874/\",\"headers\":{\"accept\":[\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\"],\"accept-encoding\":[\"gzip, deflate\"],\"accept-language\":[\"zh-CN,zh;q=0.8\"],\"connection\":[\"keep-alive\"],\"host\":[\"10.124.143.37:29874\"],\"referer\":[\"http://10.124.142.85/marathon/ui/\"],\"upgrade-insecure-requests\":[\"1\"],\"user-agent\":[\"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 UBrowser/6.2.4094.1 Safari/537.36\"]}}";
        PackageBo packageBo = PackageBo.fromLog(str22);


        JSONObject source = packageBo.getSourceAll();
        System.out.println(source);
        System.out.println(source);

//        MessageFormat messageFormat = new MessageFormat(testPattern);
//        Object[] objs = messageFormat.parse(str3);
//        for(int index = 0;index < objs.length;index++){
//            String obj = objs[index]+"";
//            String trim = obj.trim();
//            System.out.println(index + "  " + trim);
//        }
    }
}
