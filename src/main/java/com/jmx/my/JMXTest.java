package com.jmx.my;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 * @Author: huangyunquan
 * @Description:Created on 2019/2/20 18:31.
 */
public class JMXTest {
    public static void main(String[] args) {
//        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
//        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
//        long used = heapMemoryUsage.getUsed();
//        System.out.println(used);
        getTest();
    }

    public static void getTest(){

        //1：获取平台已经创建并初始化的MBeanServer,没有就通过MBeanServerFactory.createMBeanServer()创建
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        try {
            //2:每个MBean必须有一个object name,name遵照标准格式
            ObjectName name = new ObjectName("main.jmx:type=hiMBean");
            HiMBean hiMBean = new Hi();
            //3:注册MBean
            mbs.registerMBean(hiMBean,name);
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
