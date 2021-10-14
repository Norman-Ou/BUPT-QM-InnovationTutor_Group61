int temp,data;
void setup()
{
Serial.begin(115200);
}
void loop()
{
temp = analogRead(A0);
//temp = (long)100*temp/1024;
Serial.print("db:");
Serial.println(temp);
delay(1000);
}
