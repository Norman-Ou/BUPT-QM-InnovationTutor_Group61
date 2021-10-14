#include <SoftwareSerial.h>
#include <dht11.h>
#include <Wire.h>
#include <LiquidCrystal_I2C.h>
#include <IRremote.h>

//#define DTH11DTH 2
//#define IMPACT 3
//#define IRdetector 4
// #define BULETOOTH_TX 5
// #define BULETOOTH_RX 6
// #define ULTRASONIC_Echo 7
// #define ULTRASONIC_Trig 8
// #define LEDLIGHT_B 9
// #define LEDLIGHT_R 10
// #define LEDLIGHT_G 11
//#define IRreceiver 12
//#define Alart 13
//#define CLK A2
//#define DIO A3

SoftwareSerial BT(6, 5);
decode_results results;
IRrecv irrecv(12);
//设置LCD1602设备地址，我这里是PCF8572AT,地址是0x3F，一般是0x20，或者0x27具体看模块手册
LiquidCrystal_I2C lcd(0x27, 16, 2);
dht11 DHT11;

int brightness = 0;
double distance = 0;
boolean webWarning = false;
boolean AutoLight = false;
int volume = 0;
int R_Brightness = 0;
int G_Brightness = 0;
int B_Brightness = 0;
int lightType = 4; //0 -> R; 1 -> G; 2 -> B; 3 ->RGB

void lightOn()
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
        analogWrite(9, analogBrightness);//blue
        digitalWrite(10,LOW);
        digitalWrite(11,LOW);
        break;
    case 1:
    Serial.println("; case 1");
        analogWrite(10, analogBrightness);//red
        digitalWrite(9,LOW);
        digitalWrite(11,LOW);
        break;
    case 2:
        
         if(digitalRead(11)==1 || brightness == 0){
           
           digitalWrite(11,LOW);
           
         }else{
           
           digitalWrite(11,HIGH);
         }
        //analogWrite(3, analogBrightness);//green
        digitalWrite(10,LOW);
        digitalWrite(9,LOW);
        break;
    case 3:
      Serial.println("; case 3");
      if(brightness != 9){
        digitalWrite(9,HIGH);
        digitalWrite(10,HIGH);
        digitalWrite(11,HIGH);
        brightness = 9;
      }else{
        digitalWrite(9,LOW);
        digitalWrite(10,LOW);
        digitalWrite(11,LOW);
        brightness = 0;
      }
      break;
    default:
        Serial.println("LightOn ERROR");
        break;
    }
}

//返回最大值
int GetMax(int a, int b, int c){
  if(a > b && a > c){
    return a;
  }else if(b > a && b > c){
    return b;
  }else if(c > a && c > b){
    return c;
  }
}

//蓝牙输出距离
void BTShowDistance()
{
  BT.print(distance);
  BT.println("cm");
}

//蓝牙输出温度
void BTShowTemperature()
{
  BT.print((int)DHT11.temperature);
  BT.print("C");
  BT.println();
}

//蓝牙输出湿度
void BTShowHumidity()
{
  BT.print((int)DHT11.humidity);
  BT.print("%");
  BT.println();
}

//蓝牙输出亮度
void BTShowBrightness()
{
  BT.println(brightness);
}

//蓝牙输出音量
void BTShowVolume(){
  BT.print(volume);
  BT.print('d');
  BT.print('B');
  BT.println();
}

//LCD屏幕显示信息
void LCDShowInfo()
{
  lcd.setCursor(0, 0); //light
  lcd.print("Light:");
  lcd.print(brightness, 10);
  if(distance > 7){
    lcd.print("  Door:O");
  }else{
    lcd.print("  Door:X");
  }
  lcd.setCursor(0, 1);
  lcd.print("H:");
  lcd.print((int)DHT11.humidity);
  lcd.print("%");
  lcd.print(" T:");
  lcd.print((int)DHT11.temperature);
  lcd.print("C");
}

//蜂鸣器发出警报并将webWarning设为true
void Alarm()
{
  webWarning = true;
  for (int i = 0; i < 100; i++)
  {
    digitalWrite(13, HIGH); //发声音
    delay(2);
    digitalWrite(13, LOW); //不发声音
    delay(2);                 //修改延时时间，改变发声频率
  }
}

void setup()
{
  //1602初始化
  Serial.begin(9600);
  Serial.println("BT is ready!");
  BT.begin(9600); //定义蓝牙接收频率
  BT.print("BT is ready");
  lcd.init();
  lcd.backlight();
  irrecv.enableIRIn(); // 启动接收器

  pinMode(9, OUTPUT);  //LED蓝光输出
  pinMode(10, OUTPUT); //LED红光输出
  pinMode(11, OUTPUT); //LED绿光输出

  //超声波
  pinMode(8, OUTPUT); //ULTRASONIC_Trig
  pinMode(7, INPUT);  //ULTRASONIC_Echo

  //碰撞按钮
  pinMode(3, INPUT);

  //光传感器
  pinMode(A2, INPUT);

  //人体红外
  pinMode(4, INPUT);

  //蜂鸣器
  pinMode(13, OUTPUT);
  digitalWrite(13, LOW);
}

void loop()
{ 
  digitalWrite(13, LOW);
  delay(1000);
  
  int chk = DHT11.read(2); //读取DHT11

  if (AutoLight)
  {
    //根据环境亮度调节灯亮度
    Serial.print("analogRead(A2) is: ");
    Serial.println(analogRead(A2));
    int EPBrightness = map(analogRead(A2), 0, 1023, 255, 0);
    Serial.print("EP is: ");
    Serial.println(EPBrightness);
    analogWrite(9, EPBrightness);
    brightness = map(EPBrightness, 0, 255, 0, 9);
  }

  //超声波测距
  digitalWrite(8, LOW);
  delayMicroseconds(2);
  digitalWrite(8, HIGH);
  delayMicroseconds(10);
  digitalWrite(8, LOW);
  distance = pulseIn(7, HIGH) / 58.0;

  //分贝测量
  volume = analogRead(A0);

  //报警系统 【   碰撞           距离>7代表门开    检测是否有人，有人为HIGH       分贝大于60则通过】
  if (digitalRead(3) == LOW || distance > 7 || digitalRead(4) == HIGH || analogRead(A0) > 60)
  {
    Alarm();
    webWarning = true;
  }else{
    webWarning = false;
  }

  //蓝牙
  //PC -> 手机
  if (Serial.available())
  {
    String PCCmd = Serial.readString();
    BT.print("PC command is ");
    BT.println(PCCmd);
  }

  //手机 -> PC
  if (BT.available())
  {
    String btCmd = BT.readString();
    Serial.print("BT command is ");
    Serial.println(btCmd);
    if(btCmd == "GreenLight_0"){
      analogWrite(11,0);
      G_Brightness = 0;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "GreenLight_1"){
      analogWrite(11,25);
      G_Brightness = 1;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "GreenLight_2"){
      analogWrite(11,50);
      G_Brightness = 2;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "GreenLight_3"){
      analogWrite(11,75);
      G_Brightness = 3;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "GreenLight_4"){
      analogWrite(11,100);
      G_Brightness = 4;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "GreenLight_5"){
      analogWrite(11,125);
      G_Brightness = 5;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "GreenLight_6"){
      analogWrite(11,150);
      G_Brightness = 6;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "GreenLight_7"){
      analogWrite(11,175);
      G_Brightness = 7;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "GreenLight_8"){
      analogWrite(11,200);
      G_Brightness = 8;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "GreenLight_9"){
      analogWrite(11,255);
      G_Brightness = 9;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "RedLight_0"){
      analogWrite(10,0);
      R_Brightness = 0;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "RedLight_1"){
      analogWrite(10,25);
      R_Brightness = 1;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "RedLight_2"){
      analogWrite(10,50);
      R_Brightness = 2;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "RedLight_3"){
      analogWrite(10,75);
      R_Brightness = 3;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "RedLight_4"){
      analogWrite(10,100);
      R_Brightness = 4;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "RedLight_5"){
      analogWrite(10,125);
      R_Brightness = 5;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "RedLight_6"){
      analogWrite(10,150);
      R_Brightness = 6;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "RedLight_7"){
      analogWrite(10,175);
      R_Brightness = 7;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "RedLight_8"){
      analogWrite(10,200);
      R_Brightness = 8;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "RedLight_9"){
      analogWrite(10,255);
      R_Brightness = 9;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "BlueLight_0"){
      analogWrite(9,0);
      B_Brightness = 0;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "BlueLight_1"){
      analogWrite(9,25);
      B_Brightness = 1;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "BlueLight_2"){
      analogWrite(9,50);
      B_Brightness = 2;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "BlueLight_3"){
      analogWrite(9,75);
      B_Brightness = 3;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "BlueLight_4"){
      analogWrite(9,100);
      B_Brightness = 4;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "BlueLight_5"){
      analogWrite(9,125);
      B_Brightness = 5;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "BlueLight_6"){
      analogWrite(9,150);
      B_Brightness = 6;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "BlueLight_7"){
      analogWrite(9,175);
      B_Brightness = 7;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "BlueLight_8"){
      analogWrite(9,200);
      B_Brightness = 8;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "BlueLight_9"){
      analogWrite(9,255);
      B_Brightness = 9;
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
    }else if(btCmd == "GetBrightness"){
      BTShowBrightness();
    }else if(btCmd == "AutoLightControl_On"){
      AutoLight = true;
    }else if(btCmd == "AutoLightControl_Off"){
      AutoLight = false;
    }else if(btCmd == "GetTemperature"){
      BTShowTemperature();
    }else if(btCmd == "GetHumidity"){
      BTShowHumidity();
    }else if(btCmd == "GetDistance"){
      BTShowDistance();
    }else if(btCmd == "GetVolume"){
      BTShowVolume();
    }else{
      Serial.println("Unknown BT Command");
    }

    
  }

  //遥控器
  if (irrecv.decode(&results))
    {
        //Serial.println("\nDetective");
        switch (results.value)
        {
        case 16716015: //left
        Serial.print("Left;");
        if(lightType == 0){
          lightType = 4;
        }
            lightType--;
            lightOn();
        
            break;
        case 16734885: //right
        Serial.print("Right;");
            lightType++;
            lightOn();
            break;
        case 16718055: //upper
        Serial.print("upper;");
            if (brightness == 9)
            {
                break;
            }
            else
            {
                brightness++;
                lightOn();
                break;
            }
        case 16730805: //lower
        Serial.print("lower;");
            if (brightness == 0)
            {
                break;
            }
            else
            {
                brightness--;
                lightOn();
                break;
            }

        case 16726215: //中间按钮
            if (AutoLight)
            {
                AutoLight = false;
                Serial.println("AutoLightOff");
                break;
            }
            else
            {
                AutoLight = true;
                Serial.println("AutoLightOn");
                break;
            }
        default:
            Serial.println("Unkonw message from IRReciever");
            break;
        }
        irrecv.resume(); // 接收下一个值
    }
  LCDShowInfo();
}
