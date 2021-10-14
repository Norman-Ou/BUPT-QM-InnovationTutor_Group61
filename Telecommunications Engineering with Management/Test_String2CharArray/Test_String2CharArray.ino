#include <PubSubClient.h>
#include <ESP8266WiFi.h>

WiFiClient espClient;
PubSubClient client(espClient);

void setup()
{
  Serial.begin(9600);
}
 
 
void loop()
{
  unsigned long along = 1234213;
  String str1 = String(along);
  str1 += "mimi";
  char cArr[str1.length() + 1];
  char cArr2[str1.length() + 3];
  str1.toCharArray(cArr,str1.length() + 1);
  str1.toCharArray(cArr2,str1.length() + 3);
  Serial.println(str1);
  Serial.println(cArr);
  Serial.println(cArr2);
  Serial.println(sizeof(str1));
  Serial.println(sizeof(cArr));
  Serial.println(sizeof(cArr2));
  Serial.println(strlen(cArr));
  Serial.println(strlen(cArr2));
  client.publish("test",cArr);
  if(cArr[str1.length()] == '\0')
  {
    Serial.println("cArr1: has ending mark. ");
  }
  if(cArr2[str1.length()] == '\0')
  {
    Serial.println("cArr2: has ending mark. ");
  }
  while(1);
}
