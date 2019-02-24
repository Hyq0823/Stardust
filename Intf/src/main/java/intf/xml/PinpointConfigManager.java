package intf.xml;


import com.sun.tools.internal.jxc.ConfigReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class PinpointConfigManager implements PinpointConfig {
    public  Long agentBatchNum;
    public  String agentId;
    public  String applicationName;
    private Properties pt;

    public PinpointConfigManager(Properties pt){
        if (pt == null) {
            throw new NullPointerException("properties must not be null");
        }
        this.pt = pt;
        readPropertyValues();
    }

    private void readPropertyValues() {
        this.agentBatchNum = readLong("agent.batch.num",6L);
        this.agentId = readStr("agent.agentId","");
        this.applicationName = readStr("agent.applicationName","");
    }

    public static PinpointConfig load(String file){
        Properties pt = read(file);
        return new PinpointConfigManager(pt);
    }

    @Override
    public Long getAgentBatchNum() {
        return agentBatchNum;
    }

    @Override
    public String getAgentId() {
        return agentId;
    }

    @Override
    public String getApplicationName() {
       return applicationName;
    }

    public String readStr(String key,String defaultValue){
        return pt.getProperty(key,defaultValue);
    }
    public Long readLong(String key,Long defaultVlaue){
        String value = pt.getProperty(key, defaultVlaue+"");
        try {
            return Long.parseLong(value);
        }catch (NumberFormatException e){
            return defaultVlaue;
        }
    }



    public static Properties read(String fileName){
        Objects.requireNonNull(fileName,"config file is null");
        try (InputStream in = PinpointConfigManager.class.getClassLoader().getResourceAsStream(fileName)) {
            Properties pt = new Properties();
            pt.load(in);
            return pt;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("未找到文件",e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("io异常",e);
        }
    }
}
