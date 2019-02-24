package com.jmx;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class MyAgent {
    public static void main(String[] args) {
        //1：获取平台已经创建并初始化的MBeanServer,没有就通过MBeanServerFactory.createMBeanServer()创建
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        try {
            //2:每个MBean必须有一个object name,name遵照标准格式
            ObjectName name = new ObjectName("main.jmx:type=Paxi");
            Paxi paxi = new Paxi();
            //3:注册MBean
            mbs.registerMBean(paxi,name);
            System.out.println("wait for incoming request");
            Thread.sleep(Long.MAX_VALUE);
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (NotCompliantMBeanException e) {
            e.printStackTrace();
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
        } catch (MBeanRegistrationException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}