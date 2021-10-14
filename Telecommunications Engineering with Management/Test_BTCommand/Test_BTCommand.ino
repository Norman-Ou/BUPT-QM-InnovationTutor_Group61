switch (btCmd)
    {
    case 'Q':
      analogWrite(9, 0);
      analogWrite(10,0);
      analogWrite(11,0);
      brightness = 0;
      break;
    case 'W':
      analogWrite(9, 28.3);
      analogWrite(10,0);
      analogWrite(11,0);
      brightness = 1;
      break;
    case 'E':
      analogWrite(9, 56.6);
      analogWrite(10,0);
      analogWrite(11,0);
      brightness = 2;
      break;
    case 'R':
      analogWrite(9, 84.9);
      analogWrite(10,0);
      analogWrite(11,0);
      brightness = 3;
      break;
    case 'T':
      analogWrite(9, 113.2);
      analogWrite(10,0);
      analogWrite(11,0);
      brightness = 4;
      break;
    case 'Y':
      analogWrite(9, 141.5);
      analogWrite(10,0);
      analogWrite(11,0);
      brightness = 5;
      break;
    case 'U':
      analogWrite(9, 169.8);
      analogWrite(10,0);
      analogWrite(11,0);
      brightness = 6;
      break;
    case 'I':
      analogWrite(9, 198.1);
      analogWrite(10,0);
      analogWrite(11,0);
      brightness = 7;
      break;
    case 'O':
      analogWrite(9, 226.4);
      analogWrite(10,0);
      analogWrite(11,0);
      brightness = 8;
      break;
    case 'P':
      analogWrite(9, 255);
      analogWrite(10,0);
      analogWrite(11,0);
      brightness = 9;
      break;
    
    case 'd':
      BTShowBrightness();
      break;
    case 't':
      BTShowTemperature();
      break;
    case 'h':
      BTShowHumidity();
      break;
    case 'D':
      BTShowDistance();
      break;
    case 'l':
      AutoLight = AutoEPBrightness();
      break;
    default:
      BT.println("Unknown Command for Bluetooth");
      break;
    }

    boolean AutoEPBrightness(){
  if(AutoLight){
    BT.println("AutoLight System Off");
    return false;
  }else{
    BT.println("AutoLight System On");
    return true;
  }
}