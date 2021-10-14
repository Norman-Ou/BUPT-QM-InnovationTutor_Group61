package topicCode;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
/**
 * 接收和发送单开一个线程√
 * 数据库加一个指令的table,数据库data里面再加一条灯的颜色√
 * table开一个线程用while循环检测并发送√
 * 接收要写到数据库里面
 * equipment 的 图片!!!!!!!!!!!!!!!!!自增（跟当前）!!!
 * 管理员界面要大调整，id不能
 * command删除√
 * 报警信息要多开一个线程
 */

public class test implements Runnable{
	public void run() {
		String subTopic = "esp8266/data";
        String pubTopic = "esp8266/command";
        String content = "1111111111";///要发送的指令
        int qos = 2;
        String broker = "tcp://broker.emqx.io:1883";
        String clientId = "emqx_test31";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient client = new MqttClient(broker, clientId, persistence);

            // MQTT 连接选项
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName("emqx_test31");
            connOpts.setPassword("emqx_test_password".toCharArray());
            // 保留会话
            connOpts.setCleanSession(true);

            // 设置回调
            client.setCallback(new PushCallback());

            // 建立连接
            System.out.println("Connecting to broker: " + broker);
            client.connect(connOpts);

            System.out.println("Connected");
            System.out.println("Publishing message: " + content);

            // 订阅
//            boolean x=true;
//            while(x) {
//                client.subscribe(subTopic);
//                try {
//					Thread.sleep(2000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//            }


            // 消息发布所需参数
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
//            boolean x=true;
//            while(x) {
            	client.publish(pubTopic, message);
//            }
//            
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
