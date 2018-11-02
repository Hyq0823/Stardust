package com.json;

import com.alibaba.fastjson.JSONPath;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/10/25 19:14.
 */
public class ReadPathDemo {
    public static void main(String[] args) {
        String json = "{\"endpoint\":{\"serial\":\"IBM06C949T\",\"host_name\":\"gsdb_2\",\"network\":[{\"ip\":\"3.3.3.22\",\"name\":\"en10\",\"mac\":\"34:40:B5:A9:24:FA\"},{\"ip\":\"5.5.5.22|135.129.24.22|135.129.24.53\",\"name\":\"en6\",\"mac\":\"34:40:B5:A9:52:BA\"}]},\"level\":\"3\",\"name\":\"alarm\",\"message\":\"计费域_计费账务系统(135.129.24.53)产生主机CPU平均使用率超阀值告警:性能值为75.10%,阀值设置为大于等于70.00%,一般告警\",\"timestamp\":\"20181019231903\",\"tags\":[{\"idc\":\"gansu-idc\"},{\"group\":[\"1005\"]}]}";
        String path = "endpoint.network.ip[1]";
        Object read = JSONPath.read(json, path);
        System.out.println(read);
    }
}
