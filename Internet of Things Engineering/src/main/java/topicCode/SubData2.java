package topicCode;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class SubData2 implements Runnable{
	public void run() {
		String subTopic = "61_Temperature_Humididy";     /*esp8266/data*/
        String pubTopic = "esp8266/command";
//        String content = "1111111111";///要发送的指令
        int qos = 2;//这个目前不太清楚还需要再研究
        String broker = "tcp://broker.emqx.io:1883";
        String clientId = "sub1434928384";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient client = new MqttClient(broker, clientId, persistence);

            // MQTT 连接选项
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName("sub12387793223");
            connOpts.setPassword("emqx_test_password".toCharArray());
            // 保留会话
            connOpts.setCleanSession(true);

            // 设置回调
            client.setCallback(new PushCallback());

            // 建立连接
            System.out.println("Connecting to broker2: " + broker);
            client.connect(connOpts);

            System.out.println("Connected2");
//            System.out.println("Publishing message: " + content);

            // 订阅
            boolean x=true;
            while(x) {
                client.subscribe(subTopic);
                System.out.println("subc2");
                try {
					Thread.sleep(2000);//每隔1秒接收1次
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }


 
            client.disconnect();
            System.out.println("Disconnected");
            client.close();
//            System.exit(0);
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
