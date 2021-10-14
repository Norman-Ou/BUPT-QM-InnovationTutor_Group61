void setup() {
  // put your setup code here, to run once:

}

void IR_LightControl()
{
  lightType = lightType % 4; //1 -> R; 2 -> G; 0 -> B; 3 ->RGB
  int analogBrightness = map((IR_brightness % 10), 0, 9, 0, 255);
  Serial.print("Brightness: ");
  Serial.print(IR_brightness);
  Serial.print("; LightType: ");
  Serial.print(lightType);
  Serial.print("; AnalogBrightness: ");
  Serial.print(analogBrightness);

  switch (lightType)
  {
  case 0:
    Serial.println("; case 0");
    analogWrite(D3, analogBrightness); //blue
    digitalWrite(D1, LOW);
    digitalWrite(D2, LOW);
    break;
  case 1:
    Serial.println("; case 1");
    analogWrite(D1, analogBrightness); //red
    digitalWrite(D2, LOW);
    digitalWrite(D3, LOW);
    break;
  case 2:

    if (digitalRead(D2) == 1 || IR_brightness == 0)
    {
      digitalWrite(D2, LOW);
    }
    else
    {
      digitalWrite(D2, HIGH);
    }
    //analogWrite(3, analogBrightness);//green
    digitalWrite(D1, LOW);
    digitalWrite(D3, LOW);
    break;
  case 3:
    Serial.println("; case 3");
    if (IR_brightness != 9)
    {
      digitalWrite(D1, HIGH);
      digitalWrite(D2, HIGH);
      digitalWrite(D3, HIGH);
      IR_brightness = 9;
    }
    else
    {
      digitalWrite(D1, LOW);
      digitalWrite(D2, LOW);
      digitalWrite(D3, LOW);
      IR_brightness = 0;
    }
    break;
  default:
    Serial.println("LightOn ERROR");
    break;
  }
}

void loop() {
  if (irrecv.decode(&results))
  {
    switch (results.value)
    {
    case 16716015: //left
      Serial.print("Left;");
      if (lightType == 0)
      {
        lightType = 4;
      }
      lightType--;
      IR_LightControl();

      break;
    case 16734885: //right
      Serial.print("Right;");
      lightType++;
      IR_LightControl();
      break;
    case 16718055: //upper
      Serial.print("upper;");
      if (IR_brightness == 9)
      {
        break;
      }
      else
      {
        IR_brightness++;
        IR_LightControl();
        break;
      }
    case 16730805: //lower
      Serial.print("lower;");
      if (IR_brightness == 0)
      {
        break;
      }
      else
      {
        IR_brightness--;
        IR_LightControl();
        break;
      }
    default:
      Serial.println("Unkonw message from IRReciever");
      break;
    }

}
