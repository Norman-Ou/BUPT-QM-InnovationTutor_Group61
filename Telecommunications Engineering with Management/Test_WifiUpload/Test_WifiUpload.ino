#include <ESP8266WiFi.h>
#include <PubSubClient.h>

// WiFi
const char *ssid = "jyc"; // Enter your WiFi name
const char *password = "123456789";  // Enter WiFi password

// MQTT Broker
const char *mqtt_broker = "broker.emqx.io";
const char *subtopic = "esp8266/command";
const char *pubtopic = "esp8266/data";
const char *mqtt_username = "123";
const char *mqtt_password = "123";
const int mqtt_port = 1883;

WiFiClient espClient;
PubSubClient client(espClient);

double distance = 28.04;
int humidity = 97;
int temperature = 26;
int brightness = 9;
int volume = 22;
int webWarning = 1;

//WIFI输出距离
void WIFIShowDistance(){
  char   sum[10];
  dtostrf(distance,1,2,sum);
  client.publish("61_Distance", sum);
}

void WIFIShowTemperatureHumidity(){
  char   sum[10];
  dtostrf(humidity,1,0,sum);
  char   c_temperature[10];
  dtostrf(temperature,1,0,c_temperature);
  //定义一个字符串
  char s[]=";";
  //进行字符串拼接操作
  strcat(sum,s);
  strcat(sum,c_temperature);
  
  client.publish("61_Temperature_Humididy", sum);
}

//WIFI输出亮度
void WIFIShowBrightness()
{
  char   sum[10];
  dtostrf(brightness,1,0,sum);
  //client.publish("61_Brightness", "6");
  client.publish("61_Brightness", sum);
}

//WIFI输出音量
void WIFIShowVolume()
{
  char   sum[10];
  dtostrf(volume,1,0,sum);
  //client.publish("61_Volume", "30");
  client.publish("61_Volume", sum);
}

void WIFIAlarm(){
  char   sum[10];
  dtostrf(webWarning,1,0,sum);
  //client.publish("61_Alart", "true");
  client.publish("61_Alart", sum);
}

void setup() {
    // Set software serial baud to 9600;
    Serial.begin(9600);
    // connecting to a WiFi network
    WiFi.begin(ssid, password);
    while (WiFi.status() != WL_CONNECTED) {
        delay(500);
        Serial.println("Connecting to WiFi..");
    }
    Serial.println("Connected to the WiFi network");
    //connecting to a mqtt broker
    client.setServer(mqtt_broker, mqtt_port);
    client.setCallback(callback);
    while (!client.connected()) {
        String client_id = "esp8266-client-";
        client_id += String(WiFi.macAddress());
        Serial.printf("The client %s connects to the public mqtt broker\n", client_id.c_str());
        if (client.connect(client_id.c_str(), mqtt_username, mqtt_password)) {
            Serial.println("Public emqx mqtt broker connected");
        } else {
            Serial.print("failed with state ");
            Serial.print(client.state());
            delay(2000);
        }
    }
    // publish and subscribe
    client.publish(pubtopic, "hello emqx");
    client.subscribe(subtopic);
}

void callback(char *topic, byte *payload, unsigned int length) {
    Serial.print("Message arrived in topic: ");
    Serial.println(topic);
    Serial.print("Message:");
    for (int i = 0; i < length; i++) {
        Serial.print((char) payload[i]);
    }
    Serial.println();
    Serial.println("-----------------------");
}

void loop() {
    client.loop();
    //WIFI
    //Send
    WIFIShowBrightness();
    WIFIShowDistance();
    WIFIShowVolume();
    WIFIShowTemperatureHumidity();
    WIFIAlarm();
    

    //receive
    // WIFI_Cmd = BT.readString();
    // Serial.print("WIFI command is ");
    // Serial.println(WIFI_Cmd);

    delay(2000);
}
