#include <SoftwareSerial.h>
SoftwareSerial mySerial(6, 5); // RX, TX 配置 3、2 为软串口

void setup()
{
  Serial.begin(115200);
  Serial.println("ESP8266 WIFI Test");
  while (!Serial) {
    ;
  }
  Serial.println("hardware serial!");
  mySerial.begin(115200);
  mySerial.println("software seria");
}

String inputString="";

void loop()
{
      while(mySerial.available()){
           inputString=inputString+char(mySerial.read());
           delay(10);
      }
 
      if(inputString.length()>0){
          Serial.println(inputString);
          inputString="";
     }
     
  if (Serial.available()) {
    mySerial.write(Serial.read());
  }
}
