#include <SoftwareSerial.h>
#include <ESP8266WiFi.h>
#include <PubSubClient.h>
#include <IRremote.h>
#include <Wire.h>

// WiFi
const char *ssid = "jyc";           // Enter your WiFi name
const char *password = "123456789"; // Enter WiFi password

// MQTT Broker
const char *mqtt_broker = "broker.emqx.io";
const char *subtopic = "61_LightControl";
const char *pubtopic = "61_Bus";
const char *mqtt_username = "123";
const char *mqtt_password = "123";
const int mqtt_port = 1883;

WiFiClient espClient;
PubSubClient client(espClient);
SoftwareSerial mySerial(D6, D5); // RX, TX 配置 3、2 为软串口
decode_results results;
IRrecv irrecv(12);

//global variables
String serData = "";
int brightness;
int R_Brightness;
int G_Brightness;
int B_Brightness;
int lightType = 4; //0 -> R; 1 -> G; 2 -> B; 3 ->RGB

void IR_LightControl()
{
  lightType = lightType % 4; //0 -> Blue; 1 -> Red; 2 -> Green; 3 ->RGB
  int analogBrightness = map((brightness % 10), 0, 9, 0, 255);
  Serial.print("Brightness: ");
  Serial.print(brightness);
  Serial.print("; LightType: ");
  Serial.print(lightType);
  Serial.print("; AnalogBrightness: ");
  Serial.print(analogBrightness);

  switch (lightType)
  {
  case 0:
    Serial.println("; case 0");
    analogWrite(9, analogBrightness); //blue
    digitalWrite(10, LOW);
    digitalWrite(11, LOW);
    break;
  case 1:
    Serial.println("; case 1");
    analogWrite(10, analogBrightness); //red
    digitalWrite(9, LOW);
    digitalWrite(11, LOW);
    break;
  case 2:

    if (digitalRead(11) == 1 || brightness == 0)
    {
      digitalWrite(11, LOW);
    }
    else
    {
      digitalWrite(11, HIGH);
    }
    //analogWrite(3, analogBrightness);//green
    digitalWrite(10, LOW);
    digitalWrite(9, LOW);
    break;
  case 3:
    Serial.println("; case 3");
    if (brightness != 9)
    {
      digitalWrite(9, HIGH);
      digitalWrite(10, HIGH);
      digitalWrite(11, HIGH);
      brightness = 9;
    }
    else
    {
      digitalWrite(9, LOW);
      digitalWrite(10, LOW);
      digitalWrite(11, LOW);
      brightness = 0;
    }
    break;
  default:
    Serial.println("LightOn ERROR");
    break;
  }
}

int GetMax(int a, int b, int c)
{
  if (a > b && a > c)
  {
    return a;
  }
  else if (b > a && b > c)
  {
    return b;
  }
  else if (c > a && c > b)
  {
    return c;
  }
}

void UploadDistance(String distance)
{
  char cArr[distance.length() + 1];
  distance.toCharArray(cArr, distance.length() + 1);
  client.publish("61_Distance", cArr);
}

void UploadTemperatureHumidity(String temp_hum)
{
  char cArr[temp_hum.length() + 1];
  temp_hum.toCharArray(cArr, temp_hum.length() + 1);
  client.publish("61_Temperature_Humididy", cArr);
}

void UploadBrightness(String brightness)
{
  char cArr[brightness.length() + 1];
  brightness.toCharArray(cArr, brightness.length() + 1);
  client.publish("61_Brightness", cArr);
}

void UploadVolume(String volume)
{
  char cArr[volume.length() + 1];
  volume.toCharArray(cArr, volume.length() + 1);
  client.publish("61_Volume", cArr);
}

void WIFIAlarm()
{
  String webWarning = "1";
  char cArr[webWarning.length() + 1];
  webWarning.toCharArray(cArr, webWarning.length() + 1);
  //client.publish("61_Alart", "true");
  client.publish("61_Alart", cArr);
  Serial.println("Web Alarting");
}

//蜂鸣器发出警报
void Alarm()
{
  for (int i = 0; i < 100; i++)
  {
    digitalWrite(D4, HIGH); //发声音
    delay(2);
    digitalWrite(D4, LOW); //不发声音
    delay(2);              //修改延时时间，改变发声频率
  }
}

void callback(char *topic, byte *payload, unsigned int length)
{
  Serial.print("Message arrived in topic: ");
  Serial.println(topic);
  Serial.print("Message:");
  for (int i = 0; i < length; i++)
  {
    serData = serData + (char)payload[i];
    delay(2);
  }
  Serial.println(serData);
  WIFI_LightControl(serData);
  serData = "";
}

void WIFI_LightControl(String serData)
{
  if (serData == "1;0")
  {
    analogWrite(D1, 0);
    R_Brightness = 0;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "1;1")
  {
    analogWrite(D1, 25);
    R_Brightness = 1;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "1;2")
  {
    analogWrite(D1, 50);
    R_Brightness = 2;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "1;3")
  {
    analogWrite(D1, 75);
    R_Brightness = 3;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "1;4")
  {
    analogWrite(D1, 10);
    R_Brightness = 4;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "1;5")
  {
    analogWrite(D1, 125);
    R_Brightness = 5;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "1;6")
  {
    analogWrite(D1, 150);
    R_Brightness = 6;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "1;7")
  {
    analogWrite(D1, 175);
    R_Brightness = 7;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "1;8")
  {
    analogWrite(D1, 200);
    R_Brightness = 8;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "1;9")
  {
    analogWrite(D1, 255);
    R_Brightness = 9;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }

  else if (serData == "2;0")
  {
    analogWrite(2, 0);
    G_Brightness = 0;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "2;1")
  {
    analogWrite(D2, 25);
    G_Brightness = 1;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "2;2")
  {
    analogWrite(D2, 50);
    G_Brightness = 2;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "2;3")
  {
    analogWrite(D2, 75);
    G_Brightness = 3;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "2;4")
  {
    analogWrite(D2, 10);
    G_Brightness = 4;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "2;5")
  {
    analogWrite(D2, 125);
    G_Brightness = 5;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "2;6")
  {
    analogWrite(D2, 150);
    G_Brightness = 6;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "2;7")
  {
    analogWrite(D2, 175);
    G_Brightness = 7;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "2;8")
  {
    analogWrite(D2, 200);
    G_Brightness = 8;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "2;9")
  {
    analogWrite(D2, 255);
    G_Brightness = 9;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }

  else if (serData == "3;0")
  {
    analogWrite(D3, 0);
    B_Brightness = 0;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "3;1")
  {
    analogWrite(D3, 25);
    B_Brightness = 1;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "3;2")
  {
    analogWrite(D3, 50);
    B_Brightness = 2;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "3;3")
  {
    analogWrite(D3, 75);
    B_Brightness = 3;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "3;4")
  {
    analogWrite(D3, 10);
    B_Brightness = 4;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "3;5")
  {
    analogWrite(D3, 125);
    B_Brightness = 5;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "3;6")
  {
    analogWrite(D3, 150);
    B_Brightness = 6;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "3;7")
  {
    analogWrite(D3, 175);
    B_Brightness = 7;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "3;8")
  {
    analogWrite(D3, 200);
    B_Brightness = 8;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "3;9")
  {
    analogWrite(D3, 255);
    B_Brightness = 9;
    brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "")
  {
    ;
  }
  else
  {
    Serial.println("Unknown WIFI Command");
  }
}

void setup()
{
  // Set software serial baud to 115200;
  Serial.begin(115200);
  //Waiting software serial ready
  while (!Serial)
  {
    ;
  }
  mySerial.begin(115200);

  // connecting to a WiFi network
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED)
  {
    Serial.println("Connecting to WiFi..");
    delay(10);
  }
  Serial.println("Connected to the WiFi network");
  //connecting to a mqtt broker
  client.setServer(mqtt_broker, mqtt_port);
  client.setCallback(callback);
  while (!client.connected())
  {
    String client_id = "esp8266-client-";
    client_id += String(WiFi.macAddress());
    Serial.printf("The client %s connects to the public mqtt broker\n", client_id.c_str());
    if (client.connect(client_id.c_str(), mqtt_username, mqtt_password))
    {
      Serial.println("Public emqx mqtt broker connected");
    }
    else
    {
      Serial.print("failed with state ");
      Serial.print(client.state());
      delay(2000);
    }

    
  }

  //蜂鸣器
  pinMode(D4, OUTPUT);
  digitalWrite(D4, LOW);

  pinMode(D1, OUTPUT); //LED蓝光输出
  pinMode(D2, OUTPUT); //LED红光输出
  pinMode(D3, OUTPUT); //LED绿光输出

  irrecv.enableIRIn(); //Enable IR Receiver

  // publish and subscribe
  client.publish(pubtopic, "61_Wifi_Readey!");
  client.subscribe(subtopic);

  
}

void loop()
{
  client.loop();

  String recData = "";

  //Soft serial communication
  while (mySerial.available())
  {
    recData = recData + char(mySerial.read());
    delay(50);
  }
  if (recData.length() > 0)
  {
    Serial.print("Receiving Server Data:");
    Serial.println(recData);
    int hit = recData.substring(0, 1).toInt();
    int detect = recData.substring(2, 3).toInt();
    brightness = recData.substring(10, 11).toInt();
    double distance = recData.substring(15, recData.length()).toDouble();
    int volume = recData.substring(12, 14).toInt();
    UploadBrightness(recData.substring(10, 11));
    UploadDistance(recData.substring(15, recData.length()));
    UploadVolume(recData.substring(12, 14));
    UploadTemperatureHumidity(recData.substring(4, 9));
    if (hit == 1 || distance > 7 || detect == 1 || volume > 60)
    {
      WIFIAlarm();
      Alarm();
    }
    Serial.println("Upload complete!");
    recData = "";
  }

  // //Remoter
  // if (irrecv.decode(&results))
  // {
  //   //Serial.println("\nDetective");
  //   switch (results.value)
  //   {
  //   case 16716015: //left
  //     Serial.print("Left;");
  //     if (lightType == 0)
  //     {
  //       lightType = 4;
  //     }
  //     lightType--;
  //     IR_LightControl();

  //     break;
  //   case 16734885: //right
  //     Serial.print("Right;");
  //     lightType++;
  //     IR_LightControl();
  //     break;
  //   case 16718055: //upper
  //     Serial.print("upper;");
  //     if (brightness == 9)
  //     {
  //       break;
  //     }
  //     else
  //     {
  //       brightness++;
  //       IR_LightControl();
  //       break;
  //     }
  //   case 16730805: //lower
  //     Serial.print("lower;");
  //     if (brightness == 0)
  //     {
  //       break;
  //     }
  //     else
  //     {
  //       brightness--;
  //       IR_LightControl();
  //       break;
  //     }
  //   default:
  //     Serial.println("Unkonw message from IRReciever");
  //     break;
  //   }
  //   irrecv.resume(); // 接收下一个值
  // }
}
