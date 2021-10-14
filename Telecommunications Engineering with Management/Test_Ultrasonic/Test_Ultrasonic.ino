//程序效果：打开串口监视器可以观察到输出的距离值为当前超声波距前方障碍物的实际距离。
const int TrigPin = 2;
const int EchoPin = 3;
float distance;
void setup()
{
Serial.begin(9600);
pinMode(TrigPin, OUTPUT);
pinMode(EchoPin, INPUT);
pinMode(13,OUTPUT);
Serial.println("Sensor Value:");
}
void loop()
{
/*发一个10μs的高脉冲去触发TrigPin*/
digitalWrite(TrigPin, LOW);
digitalWrite(13,HIGH);
delayMicroseconds(2);
digitalWrite(TrigPin, HIGH);
delayMicroseconds(10);
digitalWrite(TrigPin, LOW);
distance = pulseIn(EchoPin, HIGH) / 58.0; 
/*算成厘米，声音的速度340m/s或29us/cm，脉冲传播出来再折返回来，所以所求对象的距离行程的一般/29/2=/58 */
if(distance <= 10.0){
  digitalWrite(13,HIGH);//set the LED on
  delay(500);//wait for a second
  digitalWrite(13,LOW);//set the LED off
  delay(500);//wait for a second
}  
Serial.print("Distance=");
Serial.print(distance);
Serial.println("cm");
delay(200);
}
