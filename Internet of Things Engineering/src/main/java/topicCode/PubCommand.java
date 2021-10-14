package topicCode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


public class PubCommand implements Runnable{
	public void run() {

//		String subTopic = "esp8266/data";
        String pubTopic = "61_LightControl";
        
        int qos = 1;
        String broker = "tcp://broker.emqx.io:1883";
        String clientId = "pub1111";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient client = new MqttClient(broker, clientId, persistence);

            // MQTT 连接选项
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName("pubxxxxxxxxxxxx");
            connOpts.setPassword("emqx_test_password".toCharArray());
            // 保留会话
            connOpts.setCleanSession(true);

            // 设置回调
            client.setCallback(new PushCallback());

            // 建立连接
            System.out.println("Connecting to broker6: " + broker);
            client.connect(connOpts);

            System.out.println("Connected6");


            // 消息发布所需参数
            
            boolean x=true;
            while(x) {
            	String content="";
        		try {
        			content = CommandContent.commandContent();
        			System.out.println("content:"+content);
        		} catch (ClassNotFoundException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		} catch (SQLException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}///要发送的指令
            	if(!content.equals("")) {
            		MqttMessage message = new MqttMessage(content.getBytes());
                    System.out.println(message);

                    message.setQos(qos);
                    
                	client.publish(pubTopic, message);
                	 System.out.println("pubc1");
            	}
            	try {
					Thread.sleep(500);//每隔0.5秒发送1次
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
            }
            
            System.out.println("Message published");

            client.disconnect();
            System.out.println("Disconnected");
            client.close();
            System.exit(0);
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
	}
}
