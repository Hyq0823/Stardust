package com.json;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/11/1 13:14.
 */
public class CastJSon {
    public static void main(String[] args) {
//        String json = "{\"@version\":\"1\",\"host\":\"10.161.66.60\",\"@timestamp\":\"2018-10-31T13:46:05.636Z\",\"message\":\"{\\\"response\\\":{\\\"timestamp\\\":1540993565.637,\\\"size_bytes\\\":\\\"156\\\",\\\"response_time\\\":0.488,\\\"headers\\\":{\\\"x-application-context\\\":\\\"2i2c_newusertrades:prod:8080\\\",\\\"content-type\\\":\\\"application\\\\/json;charset=UTF-8\\\",\\\"date\\\":\\\"Wed, 31 Oct 2018 13:46:05 GMT\\\",\\\"via\\\":\\\"kong\\\\/0.11.2\\\",\\\"connection\\\":\\\"close\\\",\\\"x-kong-proxy-latency\\\":\\\"0\\\",\\\"x-kong-upstream-latency\\\":\\\"488\\\",\\\"content-length\\\":\\\"156\\\"},\\\"body\\\":\\\"{\\\\\\\"bssOrderId\\\\\\\":\\\\\\\"8518103195468716\\\\\\\",\\\\\\\"msg\\\\\\\":\\\\\\\"成功\\\\\\\",\\\\\\\"totalFee\\\\\\\":\\\\\\\"0\\\\\\\",\\\\\\\"txid\\\\\\\":\\\\\\\"fdc1af2305e2^1537453612754^4691582\\\\\\\",\\\\\\\"provOrderId\\\\\\\":\\\\\\\"8518103195468716\\\\\\\",\\\\\\\"status\\\\\\\":\\\\\\\"0000\\\\\\\"}\\\",\\\"status\\\":200},\\\"request\\\":{\\\"timestamp\\\":1540993565.149,\\\"form\\\":{},\\\"url\\\":\\\"http:\\\\/\\\\/marathon-lb-external.marathon.mesos:8000\\\\/2i2c\\\\/newusertrades\\\\/\\\",\\\"headers\\\":{\\\"host\\\":\\\"marathon-lb-external.marathon.mesos\\\",\\\"content-type\\\":\\\"application\\\\/json; charset=UTF-8\\\",\\\"accept-encoding\\\":\\\"\\\",\\\"user-agent\\\":\\\"okhttp\\\\/3.9.0\\\",\\\"accept\\\":\\\"application\\\\/json\\\",\\\"x-forwarded-for\\\":\\\"10.1.0.100\\\",\\\"x-forwarded-port\\\":\\\"80\\\",\\\"content-length\\\":\\\"1307\\\"},\\\"body\\\":\\\"{\\\\\\\"appkey\\\\\\\":\\\\\\\"mall.sub\\\\\\\",\\\\\\\"apptx\\\\\\\":\\\\\\\"181031214605213420\\\\\\\",\\\\\\\"bizkey\\\\\\\":\\\\\\\"TS-4G-004\\\\\\\",\\\\\\\"method\\\\\\\":\\\\\\\"ecaop.trades.sell.mob.newu.open.app\\\\\\\",\\\\\\\"msg\\\\\\\":{\\\\\\\"acctInfo\\\\\\\":[{\\\\\\\"accountPayType\\\\\\\":\\\\\\\"10\\\\\\\",\\\\\\\"createOrExtendsAcct\\\\\\\":\\\\\\\"0\\\\\\\"}],\\\\\\\"channelId\\\\\\\":\\\\\\\"85b1jxw\\\\\\\",\\\\\\\"channelType\\\\\\\":\\\\\\\"1030100\\\\\\\",\\\\\\\"chkBlcTag\\\\\\\":\\\\\\\"1\\\\\\\",\\\\\\\"city\\\\\\\":\\\\\\\"850\\\\\\\",\\\\\\\"customerInfo\\\\\\\":[{\\\\\\\"authTag\\\\\\\":\\\\\\\"1\\\\\\\",\\\\\\\"custType\\\\\\\":\\\\\\\"0\\\\\\\",\\\\\\\"newCustomerInfo\\\\\\\":[{\\\\\\\"certAdress\\\\\\\":\\\\\\\"贵州省贵阳市南明区花溪大道北殄17附1\\\\\",\\\\\\\"certExpireDate\\\\\\\":\\\\\\\"20500101\\\\\\\",\\\\\\\"certNum\\\\\\\":\\\\\\\"320481199001052239\\\\\\\",\\\\\\\"certType\\\\\\\":\\\\\\\"02\\\\\\\",\\\\\\\"contactAddress\\\\\\\":\\\\\\\"贵州省贵阳市南明区花溪大道北殄17附1\\\\\",\\\\\\\"contactPerson\\\\\\\":\\\\\\\"郑晔\\\\\\\",\\\\\\\"contactPhone\\\\\\\":\\\\\\\"13678085331\\\\\\\",\\\\\\\"custType\\\\\\\":\\\\\\\"01\\\\\\\",\\\\\\\"customerName\\\\\\\":\\\\\\\"郑晔\\\\\\\"}],\\\\\\\"realNameType\\\\\\\":\\\\\\\"0\\\\\\\"}],\\\\\\\"delayDealTag\\\\\\\":\\\\\\\"0\\\\\\\",\\\\\\\"delayTag\\\\\\\":\\\\\\\"0\\\\\\\",\\\\\\\"district\\\\\\\":\\\\\\\"00000000\\\\\\\",\\\\\\\"eModeCode\\\\\\\":\\\\\\\"Q\\\\\\\",\\\\\\\"numId\\\\\\\":[{\\\\\\\"proKey\\\\\\\":\\\\\\\"999998098582969\\\\\\\",\\\\\\\"serialNumber\\\\\\\":\\\\\\\"13017455197\\\\\\\"}],\\\\\\\"opeSysType\\\\\\\":\\\\\\\"2\\\\\\\",\\\\\\\"operatorId\\\\\\\":\\\\\\\"AGYDZSW1\\\\\\\",\\\\\\\"ordersId\\\\\\\":\\\\\\\"211897202539679744\\\\\\\",\\\\\\\"para\\\\\\\":[{\\\\\\\"paraId\\\\\\\":\\\\\\\"SPEED\\\\\\\",\\\\\\\"paraValue\\\\\\\":\\\\\\\"300\\\\\\\"}],\\\\\\\"province\\\\\\\":\\\\\\\"85\\\\\\\",\\\\\\\"recomDepartId\\\\\\\":\\\\\\\"85b2zi4\\\\\\\",\\\\\\\"recomPersonId\\\\\\\":\\\\\\\"8508770374\\\\\\\",\\\\\\\"recomPersonName\\\\\\\":\\\\\\\"申晓萍\\\\\\\",\\\\\\\"userInfo\\\\\\\":[{\\\\\\\"bipType\\\\\\\":\\\\\\\"1\\\\\\\",\\\\\\\"checkType\\\\\\\":\\\\\\\"03\\\\\\\",\\\\\\\"firstMonBillMode\\\\\\\":\\\\\\\"02\\\\\\\",\\\\\\\"groupFlag\\\\\\\":\\\\\\\"0\\\\\\\",\\\\\\\"is3G\\\\\\\":\\\\\\\"2\\\\\\\",\\\\\\\"packageTag\\\\\\\":\\\\\\\"0\\\\\\\",\\\\\\\"payInfo\\\\\\\":{\\\\\\\"payType\\\\\\\":\\\\\\\"10\\\\\\\"},\\\\\\\"product\\\\\\\":[{\\\\\\\"productId\\\\\\\":\\\\\\\"90350506\\\\\\\",\\\\\\\"productMode\\\\\\\":\\\\\\\"1\\\\\\\"}],\\\\\\\"serType\\\\\\\":\\\\\\\"1\\\\\\\",\\\\\\\"userType\\\\\\\":\\\\\\\"1\\\\\\\"}]},\\\\\\\"timestamp\\\\\\\":\\\\\\\"2018-10-31 21:46:05\\\\\\\"}\\\",\\\"method\\\":\\\"POST\\\"}}\"}";
//        json = StringEscapeUtils.unescapeJava(json);
//        JSONObject jsonObject = JSONObject.parseObject(json);
//        System.out.println(jsonObject);
//        System.out.println(jsonObject);

        String testSplit = "e665634e965e3e258ea0ed8705cb60a8@";
        String[] split = testSplit.split("@");
        System.out.println(split);
        System.out.println(split);

//        Map<String, String> dataLegend = getDataLegend("4G");
        Map<String, String> dataLegend = getDataLegend("1G");
        System.out.println(dataLegend);
    }


    public static Map<String,String> getDataLegend(String sceneCode) {
        Map legend = new LinkedHashMap();
        if("4G".equals(sceneCode)){
            legend.put("oneS","1M");
            legend.put("threeS","3M");
            legend.put("fiveS","5M");
        }else{
            legend.put("oneS","1S");
            legend.put("threeS","3S");
            legend.put("fiveS","5S");
        }
        legend.put("fail","异常量");
        legend.put("timeout","超时量");
        return legend;
    }
}
