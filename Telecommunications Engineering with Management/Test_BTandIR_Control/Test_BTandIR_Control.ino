#include <SoftwareSerial.h>

SoftwareSerial BT(6, 5);


int R_Brightness;
int G_Brightness;
int B_Brightness;
int brightness;

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



//蓝牙输出亮度
void BTShowBrightness()
{
  BT.println(brightness);
}

void setup()
{
  //1602初始化
  Serial.begin(9600);
  Serial.println("BT is ready!");
  BT.begin(9600); //定义蓝牙接收频率
  BT.print("BT is ready");
}



void loop(){
  //蓝牙
  if (Serial.available())
  {
    Serial.println("a");
    char PCCmd = Serial.read();
    BT.print("PC command is ");
    BT.println(PCCmd);
  }

  if (BT.available())
  {
    String btCmd = BT.readString();
    Serial.print("BT command is ");
    Serial.println(btCmd);
    if(btCmd == "GreenLight_0"){
      analogWrite(11,0);
      G_Brightness = 0;
    }else if(btCmd == "GreenLight_1"){
      analogWrite(11,25);
      G_Brightness = 1;
    }else if(btCmd == "GreenLight_2"){
      analogWrite(11,50);
      G_Brightness = 2;
    }else if(btCmd == "GreenLight_3"){
      analogWrite(11,75);
      G_Brightness = 3;
    }else if(btCmd == "GreenLight_4"){
      analogWrite(11,100);
      G_Brightness = 4;
    }else if(btCmd == "GreenLight_5"){
      analogWrite(11,125);
      G_Brightness = 5;
    }else if(btCmd == "GreenLight_6"){
      analogWrite(11,150);
      G_Brightness = 6;
    }else if(btCmd == "GreenLight_7"){
      analogWrite(11,175);
      G_Brightness = 7;
    }else if(btCmd == "GreenLight_8"){
      analogWrite(11,200);
      G_Brightness = 8;
    }else if(btCmd == "GreenLight_9"){
      analogWrite(11,255);
      G_Brightness = 9;
    }else if(btCmd == "RedLight_0"){
      analogWrite(10,0);
      R_Brightness = 0;
    }else if(btCmd == "RedLight_1"){
      analogWrite(10,25);
      R_Brightness = 1;
    }else if(btCmd == "RedLight_2"){
      analogWrite(10,50);
      R_Brightness = 2;
    }else if(btCmd == "RedLight_3"){
      analogWrite(10,75);
      R_Brightness = 3;
    }else if(btCmd == "RedLight_4"){
      analogWrite(10,100);
      R_Brightness = 4;
    }else if(btCmd == "RedLight_5"){
      analogWrite(10,125);
      R_Brightness = 5;
    }else if(btCmd == "RedLight_6"){
      analogWrite(10,150);
      R_Brightness = 6;
    }else if(btCmd == "RedLight_7"){
      analogWrite(10,175);
      R_Brightness = 7;
    }else if(btCmd == "RedLight_8"){
      analogWrite(10,200);
      R_Brightness = 8;
    }else if(btCmd == "RedLight_9"){
      analogWrite(10,255);
      R_Brightness = 9;
    }else if(btCmd == "BlueLight_0"){
      analogWrite(9,0);
      B_Brightness = 0;
    }else if(btCmd == "BlueLight_1"){
      analogWrite(9,25);
      B_Brightness = 1;
    }else if(btCmd == "BlueLight_2"){
      analogWrite(9,50);
      B_Brightness = 2;
    }else if(btCmd == "BlueLight_3"){
      analogWrite(9,75);
      B_Brightness = 3;
    }else if(btCmd == "BlueLight_4"){
      analogWrite(9,100);
      B_Brightness = 4;
    }else if(btCmd == "BlueLight_5"){
      analogWrite(9,125);
      B_Brightness = 5;
    }else if(btCmd == "BlueLight_6"){
      analogWrite(9,150);
      B_Brightness = 6;
    }else if(btCmd == "BlueLight_7"){
      analogWrite(9,175);
      B_Brightness = 7;
    }else if(btCmd == "BlueLight_8"){
      analogWrite(9,200);
      B_Brightness = 8;
    }else if(btCmd == "BlueLight_9"){
      analogWrite(9,255);
      B_Brightness = 9;
    }else if(btCmd == "GetBrightness"){
      brightness = GetMax(R_Brightness,G_Brightness,B_Brightness);
      BTShowBrightness();
    }else if(btCmd == "AutoLightControl_On"){
      //AutoLight = true;
    }else if(btCmd == "AutoLightControl_Off"){
      //AutoLight = false;
    }else if(btCmd == "GetTemperature"){
      //BTShowTemperature();
    }else if(btCmd == "GetHumidity"){
      //BTShowHumidity();
    }else if(btCmd == "GetDistance"){
      //BTShowDistance();
    }else if(btCmd == "GetVolume"){
      //BTShowVolume();
    }else{
      Serial.println("Unknown BT Command");
    }
}
}
