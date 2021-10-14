#include <SoftwareSerial.h>
SoftwareSerial mySerial(6, 5); // RX, TX 配置 3、2 为软串口

void setup()
{
  Serial.begin(115200);
  while (!Serial)
  {
    ;
  }
  mySerial.begin(115200);
}

String inputString = "";
String information = "61GD_28.04;97;26;9;22;1";

void loop()
{
  
  while (mySerial.available())
  {
    Serial.println("Forming");
    inputString = inputString + char(mySerial.read());
    delay(10);
  }

  if (inputString.length() > 0)
  {
    Serial.println(inputString);
    inputString = "";
  }

  if (Serial.available())
  {
    if(information.substring(0,5) == "61GD_"){
      Serial.println("True");
    }
    String distance = information.substring(5,10);
    String humidity = information.substring(11,13);
    String temperature = information.substring(14,16);
    String brightness = information.substring(17,18);
    String volume = information.substring(19,21);
    String webWarning = information.substring(22,23);
    Serial.println(distance.toDouble());
    Serial.println(humidity.toInt());
    Serial.println(temperature.toInt());
    Serial.println(brightness.toInt());
    Serial.println(volume.toInt());
    Serial.println(webWarning.toInt());
    mySerial.print(information);
    while(Serial.available()){
      Serial.read();
    }
  }
}
