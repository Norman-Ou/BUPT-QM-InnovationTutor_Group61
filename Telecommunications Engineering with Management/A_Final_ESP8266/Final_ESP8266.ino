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
const char *pubtopic = "esp8266/data";
const char *mqtt_username = "123";
const char *mqtt_password = "123";
const int mqtt_port = 1883;

WiFiClient espClient;
PubSubClient client(espClient);
SoftwareSerial ArduinoSerial(D6, D5); // RX, TX 配置 3、2 为软串口
decode_results results;
IRrecv irrecv(12);

//global variables
String recData = "";
String serData = "";
int R_Brightness = 0;
int G_Brightness = 0;
int B_Brightness = 0;
int lightType = 1; //1 -> R; 2 -> G; 0 -> B; 3 ->RGB
int warning = 0;//0->no warning; 1->warning; -1->shutdown warning system
int IR_brightness;
int hit;
int detect;
int volume;
double distance;

//IR light control
void IR_LightControl()
{
  lightType = lightType % 4; //1 -> R; 2 -> G; 0 -> B; 3 ->RGB
  int analogBrightness = map((IR_brightness % 10), 0, 9, 0, 255);
  Serial.print("Brightness: ");
  Serial.print(IR_brightness);
  Serial.print("; LightType: ");
  Serial.print(lightType);
  Serial.print("; AnalogBrightness: ");
  Serial.print(analogBrightness);

  switch (lightType)
  {
  case 0:
    Serial.println("; case 0");
    analogWrite(D3, analogBrightness); //blue
    digitalWrite(D1, LOW);
    digitalWrite(D2, LOW);
    break;
  case 1:
    Serial.println("; case 1");
    analogWrite(D1, analogBrightness); //red
    digitalWrite(D2, LOW);
    digitalWrite(D3, LOW);
    break;
  case 2:

    if (digitalRead(D2) == 1 || IR_brightness == 0)
    {
      digitalWrite(D2, LOW);
    }
    else
    {
      digitalWrite(D2, HIGH);
    }
    //analogWrite(3, analogBrightness);//green
    digitalWrite(D1, LOW);
    digitalWrite(D3, LOW);
    break;
  case 3:
    Serial.println("; case 3");
    if (IR_brightness != 9)
    {
      digitalWrite(D1, HIGH);
      digitalWrite(D2, HIGH);
      digitalWrite(D3, HIGH);
      IR_brightness = 9;
    }
    else
    {
      digitalWrite(D1, LOW);
      digitalWrite(D2, LOW);
      digitalWrite(D3, LOW);
      IR_brightness = 0;
    }
    break;
  default:
    Serial.println("LightOn ERROR");
    break;
  }
}

//return maximum value
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

//Sending temperature and humidity data to server
void UploadData(String information)
{
  char cArr[information.length() + 1];
  information.toCharArray(cArr, information.length() + 1);
  client.publish("61_Temperature_Humididy", cArr);
}

//Callback method for server receiving
void ServerReceive(char *topic, byte *payload, unsigned int length)
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
  WIFI_Control();
  serData = "";
}

//Control method, including light control and warning control
void WIFI_Control()
{
  if (serData == "1;0")
  {
    analogWrite(D1, 0);
    R_Brightness = 0;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "1;1")
  {
    analogWrite(D1, 25);
    R_Brightness = 1;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "1;2")
  {
    analogWrite(D1, 50);
    R_Brightness = 2;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "1;3")
  {
    analogWrite(D1, 75);
    R_Brightness = 3;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "1;4")
  {
    analogWrite(D1, 10);
    R_Brightness = 4;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "1;5")
  {
    analogWrite(D1, 125);
    R_Brightness = 5;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "1;6")
  {
    analogWrite(D1, 150);
    R_Brightness = 6;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "1;7")
  {
    analogWrite(D1, 175);
    R_Brightness = 7;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "1;8")
  {
    analogWrite(D1, 200);
    R_Brightness = 8;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "1;9")
  {
    analogWrite(D1, 255);
    R_Brightness = 9;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }

  else if (serData == "2;0")
  {
    analogWrite(2, 0);
    G_Brightness = 0;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "2;1")
  {
    analogWrite(D2, 25);
    G_Brightness = 1;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "2;2")
  {
    analogWrite(D2, 50);
    G_Brightness = 2;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "2;3")
  {
    analogWrite(D2, 75);
    G_Brightness = 3;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "2;4")
  {
    analogWrite(D2, 10);
    G_Brightness = 4;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "2;5")
  {
    analogWrite(D2, 125);
    G_Brightness = 5;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "2;6")
  {
    analogWrite(D2, 150);
    G_Brightness = 6;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "2;7")
  {
    analogWrite(D2, 175);
    G_Brightness = 7;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "2;8")
  {
    analogWrite(D2, 200);
    G_Brightness = 8;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "2;9")
  {
    analogWrite(D2, 255);
    G_Brightness = 9;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }

  else if (serData == "3;0")
  {
    analogWrite(D3, 0);
    B_Brightness = 0;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "3;1")
  {
    analogWrite(D3, 25);
    B_Brightness = 1;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "3;2")
  {
    analogWrite(D3, 50);
    B_Brightness = 2;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "3;3")
  {
    analogWrite(D3, 75);
    B_Brightness = 3;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "3;4")
  {
    analogWrite(D3, 10);
    B_Brightness = 4;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "3;5")
  {
    analogWrite(D3, 125);
    B_Brightness = 5;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "3;6")
  {
    analogWrite(D3, 150);
    B_Brightness = 6;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "3;7")
  {
    analogWrite(D3, 175);
    B_Brightness = 7;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "3;8")
  {
    analogWrite(D3, 200);
    B_Brightness = 8;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "3;9")
  {
    analogWrite(D3, 255);
    B_Brightness = 9;
    IR_brightness = GetMax(R_Brightness, G_Brightness, B_Brightness);
  }
  else if (serData == "shutdown")
  {
    warning = -1;
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

//Extract the message and judge whether it meets the alarm conditions, and then integrate and send
void ArduinoMesProcessing(String recData)
{
  Serial.print("Rereceiving Arduino Data:");
  Serial.println(recData);

  //Extracting information
  hit = recData.substring(0, 1).toInt();
  detect = recData.substring(2, 3).toInt();
  int last_index = recData.indexOf(';');
  int index, disStartIndex, disEndIndex, volStartIndex;
  for (int i = 2; i <= 7; i++)
  {
    index = recData.indexOf(';', last_index + 1);
    last_index = index;
    if (i == 4)
    {
      disStartIndex = index + 1;
    }
    if (i == 6)
    {
      disEndIndex = index - 2;
    }
    if (i == 7)
    {
      volStartIndex = index + 1;
    }
  }
  distance = recData.substring(disStartIndex, disEndIndex).toDouble();
  volume = recData.substring(volStartIndex, recData.length()).toInt();
  String getInfo = "hit: " + String(hit) + ";" +
                   "detect: " + String(detect) + ";" +
                   "lightType: " + String(lightType) + ";" +
                   "distance: " + String(distance) + ";" +
                   "volume: " + String(volume) + ";";
  Serial.println(getInfo);

//Warning System
  if (warning != -1)
  {
    if (hit == 1 || distance > 7 || detect == 1 || volume > 60)
    {
      digitalWrite(D4, HIGH);
      warning = 1;
    }else{
      digitalWrite(D4, LOW);
      warning = 0;
    }
  }
  else
  {
    digitalWrite(D4, LOW);
  }
  //remove hit and detct data
  removalStr = recData.substring(0, 4).toInt();
  recData.replace(removalStr,"");
  //Splicing alarm information
  recData = recData + ";" + String(warning);
  //replace lightType information
  recData.setCharAt(6, char(lightType));
  //Checking
  Serial.print("Normal Uploading Data:");
  Serial.println(recData);
  //Uploading data to server
  UploadData(recData);
  Serial.println("Upload Complete!");
  //reset String, which used to receive data from Arduino, for the next loop
  recData = "";
}

void setup()
{
  // Set software serial baud to 25200;
  Serial.begin(115200);
  //Waiting software serial ready
  while (!Serial)
  {
    ;
  }
  ArduinoSerial.begin(115200);

  // connecting to a WiFi network
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED)
  {
    Serial.println("Connecting to WiFi..");
    delay(100);
  }
  Serial.println("Connected to the WiFi network");
  //connecting to a mqtt broker
  client.setServer(mqtt_broker, mqtt_port);
  client.setCallback(ServerReceive);
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
  // publish and subscribe
  client.publish(pubtopic, "hello emqx");
  client.subscribe(subtopic);

  //Serial Model Setting
  pinMode(D4, OUTPUT); //Alarm
  digitalWrite(13, LOW);

  pinMode(D1, OUTPUT); //Red Light
  pinMode(D2, OUTPUT); //Green Light
  pinMode(D3, OUTPUT); //Blue Light

  //enable IR receiver
  irrecv.enableIRIn();
}

void loop()
{
  client.loop();

  /**
   * Receiving the information from Arduino AKA sensor borad
  */
  while (ArduinoSerial.available())
  {
    recData = recData + char(ArduinoSerial.read());
    delay(5);
  }
  if (recData.length() > 0) 
  {
    ArduinoMesProcessing(recData);
  }

  //Remoter
  if (irrecv.decode(&results))
  {
    switch (results.value)
    {
    case 16716015: //left
      Serial.print("Left;");
      if (lightType == 0)
      {
        lightType = 4;
      }
      lightType--;
      IR_LightControl();

      break;
    case 16734885: //right
      Serial.print("Right;");
      lightType++;
      IR_LightControl();
      break;
    case 16718055: //upper
      Serial.print("upper;");
      if (IR_brightness == 9)
      {
        break;
      }
      else
      {
        IR_brightness++;
        IR_LightControl();
        break;
      }
    case 16730805: //lower
      Serial.print("lower;");
      if (IR_brightness == 0)
      {
        break;
      }
      else
      {
        IR_brightness--;
        IR_LightControl();
        break;
      }
    default:
      Serial.println("Unkonw IRReciever");
      break;
    }
    irrecv.resume(); //waiting for next value
  }
}
