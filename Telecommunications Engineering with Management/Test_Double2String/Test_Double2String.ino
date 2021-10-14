#include<iostream>
#include<string>
using namespace std;
#include <ESP8266WiFi.h>
#include <PubSubClient.h>

void setup() {
  float  f=3.1415;
  char   c[1024];
  dtostrf(f,1,2,c);
  Serial.println(c);
  //定义一个字符串
  char s[]="///sensor1///ly///sensor1///PM2.5///100.70///";
  //进行字符串拼接操作
  strcat(s,c);
  Serial.println(s);
}

void loop() {
  // put your main code here, to run repeatedly:
}
