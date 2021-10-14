package topicCode;


import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class PushCallback implements MqttCallback {
	String totalData;
    public void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连
        System.out.println("连接断开，可以做重连");
        System.out.println(cause);
        System.out.println("111111111111111111111111111111111");
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // subscribe后得到的消息会执行到这里面
    	totalData=new String(message.getPayload());
        System.out.println("接收消息主题:" + topic);
        System.out.println("接收消息Qos:" + message.getQos());
        System.out.println("接收消息内容:" + totalData);
        totalData=new String(message.getPayload());
        System.out.println(totalData);
        String[] splitStr = totalData.split(";");
//        if(topic.equals("61_Brightness")) {
//        	DataStorage1.dataStorage(splitStr,1);}
//        }else if(topic.equals("61_Distance")) {
//        	DataStorage1.dataStorage(splitStr,2);
       /* }else */if(topic.equals("61_Temperature_Humididy")&&splitStr.length==7) {
        	DataStorage1.dataStorage(splitStr,3);}
//        }else if(topic.equals("61_Volume")) {
//        	DataStorage1.dataStorage(splitStr,4);
//        }else if(topic.equals("61_Alart")) {
//        	DataStorage1.dataStorage(splitStr,5);
//        }
        
     
    }

    public void deliveryComplete(IMqttDeliveryToken token) {
//        System.out.println("deliveryComplete---------" + token.isComplete());
//        System.out.println(token);
//        System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwww");
    }
}