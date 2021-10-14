void setup() {
  Serial.begin(115200);
  while (!Serial)
    {
        ;
    }
}

void loop() {
  // put your main code here, to run repeatedly:
  String str = "0;1;5;2;0.00;0;0;417";
  int last_index = str.indexOf(';');
  int index,disStartIndex,disEndIndex,volStartIndex;
  //Serial.println(last_index);
  index = str.indexOf(';',last_index + 1);
  int lightType = str.substring(6, 7).toInt();
  Serial.println(lightType);
  str.setCharAt(6,'5');
  lightType = str.substring(6, 7).toInt();
  Serial.println(lightType);
  //Serial.println(index);
  for(int i = 2; i <= 7; i++){
    index = str.indexOf(';',last_index + 1);
    last_index = index;
    Serial.println(index);
    if(i == 4){
      disStartIndex = index + 1;
    }
    if(i == 6){
      disEndIndex = index - 2;
    }
    if(i == 7){
      volStartIndex = index + 1;
    }
  }
  Serial.println(str);
  Serial.println(str.substring(disStartIndex,disEndIndex));
  Serial.println(str.substring(volStartIndex,str.length()));
  while(true);
}
