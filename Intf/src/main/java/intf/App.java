package intf;

import intf.xml.PinpointConfig;
import intf.xml.PinpointConfigManager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        PinpointConfig pinpointConfig = PinpointConfigManager.load("pinpoint.config");
        System.out.println(pinpointConfig.getAgentId());
        System.out.println(pinpointConfig.getAgentBatchNum());
        System.out.println(pinpointConfig.getApplicationName());

        System.out.println( "Hello World!" );
    }
}
