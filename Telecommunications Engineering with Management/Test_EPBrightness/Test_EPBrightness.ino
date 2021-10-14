void setup() {
  Serial.begin(9600);
  //光传感器
  pinMode(A2, INPUT);

}

void loop() {
  //根据环境亮度调节灯亮度
  int EPBrightness = analogRead(2); //传感器接到模拟口2
  Serial.println(map(analogRead(A2), 0, 1023, 255, 0));


}
