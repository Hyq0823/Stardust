package com.json;

import com.alibaba.fastjson.JSONObject;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/12/10 9:42.
 */
public class PackageBo {
    /**
     * 源日志pattern
     */
   public static String LOG_PATTERN = "%d{YYYY-MM-dd HH:mm:ss:SSS} %property{app.name} [%thread] %-5level %logger{36}[%L] [TxId : %X{PtxId} , SpanId : %X{PspanId}]- %msg%n";

    /**
     * 解析pattern
     * {0,date,YYYY-MM-dd HH:mm:ss:SSS}
     */
   public static String PARSE_PATTERN = "{0,date,yyyy-MM-dd HH:mm:ss:SSS} {1} [{2}] {3}  {4}[{5}] [TxId : {6}, SpanId : {7}]- {8}";

    /**
     * 日志时间戳
     */
    private Long logTime;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 当前线程
     */
    private String currentThread;
    /**
     * 日志级别
     */
    private String logLevel;

    /**
     * 日志文件名
     */
    private String logFileName;

    /**
     * 行号
     */
    private String lineNo;
    private String txId;
    private String spanId;

    /**
     * json请求/响应 报文
     */
    private String source;

    //业务报文中抽离日志其它字段
    private String correlation;
    private JSONObject sourceAll;
    private String sourceAllStr;
    private String type;

    public static PackageBo fromLog(String log) throws ParseException {
        MessageFormat messageFormat = new MessageFormat(PARSE_PATTERN);
        Object[] objs = messageFormat.parse(log);
        Date date = (Date)objs[0];
        PackageBo packageBo = new PackageBo();
        packageBo.setLogTime(date.getTime());
        packageBo.setAppName((objs[1]+"").replaceAll(" ",""));
        packageBo.setCurrentThread((objs[2]+"").replaceAll(" ",""));
        packageBo.setLogLevel((objs[3]+"").replaceAll(" ",""));
        packageBo.setLogFileName((objs[4]+"").replaceAll(" ",""));
        packageBo.setLineNo(objs[5]+"".replaceAll(" ",""));
        packageBo.setTxId((objs[6]+"").replaceAll(" ",""));
        packageBo.setSpanId((objs[7]+"").replaceAll(" ",""));

        String sourceJson = (objs[8] + "").replaceAll(" ", "");
        packageBo.setSource(sourceJson);

        JSONObject sourceJsonObj = JSONObject.parseObject(sourceJson);
        packageBo.setCorrelation(sourceJsonObj.getString("correlation"));
        packageBo.setType(sourceJsonObj.getString("type"));

        //加入日志中其它字段
        packageBo = buildSourceAll(sourceJsonObj,packageBo);

        return packageBo;
    }

    private static PackageBo buildSourceAll(JSONObject sourceJson, PackageBo packageBo) {
        JSONObject all = new JSONObject();
        all.putAll(sourceJson);

        all.put("logTime",packageBo.getLogTime());
        all.put("appName",packageBo.getAppName());
        all.put("currentThread",packageBo.getCurrentThread());
        all.put("logLevel",packageBo.getLogLevel());
        all.put("logFileName",packageBo.getLogFileName());
        all.put("lineNo",packageBo.getLineNo());
        all.put("txId",packageBo.getTxId());
        all.put("spanId",packageBo.getSpanId());
        packageBo.setSourceAll(all);

        packageBo.setSourceAllStr(all.toString());
        return packageBo;
    }


    public String getSourceAllStr() {
        return sourceAllStr;
    }

    public void setSourceAllStr(String sourceAllStr) {
        this.sourceAllStr = sourceAllStr;
    }

    public JSONObject getSourceAll() {
        return sourceAll;
    }
    public void setSourceAll(JSONObject sourceAll) {
        this.sourceAll = sourceAll;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCorrelation() {
        return correlation;
    }

    public void setCorrelation(String correlation) {
        this.correlation = correlation;
    }

    public String getLogFileName() {
        return logFileName;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }


    public Long getLogTime() {
        return logTime;
    }

    public void setLogTime(Long logTime) {
        this.logTime = logTime;
    }

    public String getCurrentThread() {
        return currentThread;
    }

    public void setCurrentThread(String currentThread) {
        this.currentThread = currentThread;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
