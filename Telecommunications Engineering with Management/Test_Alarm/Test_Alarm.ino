#include <SoftwareSerial.h>
#include <ESP8266WiFi.h>

//蜂鸣器发出警报并将webWarning设为true
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

void setup() {
  // Set software serial baud to 115200;
  Serial.begin(115200);

  pinMode(D4, OUTPUT);
  digitalWrite(13, LOW);
}

boolean warning = false;

void loop() {
  if(warning == true){
    digitalWrite(D4,HIGH);
  }else{
    digitalWrite(D4,LOW);
  }

  if(Serial.available()){
    String input = String(char(Serial.read()));
    if(input == "1" ){
      warning = true;
    }else if(input == "2"){
      warning = false;
    }
  }
  
}
