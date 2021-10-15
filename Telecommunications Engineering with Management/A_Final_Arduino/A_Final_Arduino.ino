#include <SoftwareSerial.h>
#include <dht11.h>
#include <Wire.h>
#include <LiquidCrystal_I2C.h>
#include <IRremote.h>

//Defining Hardware which needed import library
SoftwareSerial mySerial(6, 5);//RX and TX serial ports 6 and 5 are configured as Softserial ports
LiquidCrystal_I2C lcd(0x27, 16, 2);
dht11 DHT11;

int brightness = 0;
double distance = 0;
int volume = 0;
int EPBrightness;

//internal-used variables
int webWarning = 0;
int hit = 0;
int detect = 0;

//LCD Screen display information
void LCDShowInfo()
{
    lcd.setCursor(0, 0); 
    if (hit == 1)
    {
        lcd.print("Window:O");
    }
    else
    {
        lcd.print("Window:X");
    }
    if (distance < 8.8)
    {
        lcd.print(" Door:C");
    }
    else
    {
        lcd.print(" Door:O");
    }
    lcd.setCursor(0, 1);
    lcd.print("B:");
    lcd.print(brightness);
    lcd.print(" H:");
    lcd.print((int)DHT11.humidity);
    lcd.print("%");
    lcd.print(" T:");
    lcd.print((int)DHT11.temperature);
    lcd.print("C");
}

void setup()
{
    //Arduino initialization
    Serial.begin(115200);
    //LCD initialization
    lcd.init();
    lcd.backlight();
    //Waiting Arduino ready
    while (!Serial)
    {
        ;
    }
    //Connectiong ESP8266 serial initialization
    mySerial.begin(115200);

    //ULTRASONIC
    pinMode(8, OUTPUT); //ULTRASONIC_Trig
    pinMode(7, INPUT);  //ULTRASONIC_Echo

    //HIT
    pinMode(3, INPUT);

    //LIGHT SENSOR
    pinMode(A1, INPUT);

    //PIR SENSOR
    pinMode(4, INPUT);
}

void loop()
{
    //Sensor information is read every five seconds
    delay(5000);
    
    //Reading DHT11 information
    int chk = DHT11.read(2); 
    
    //Ambient brightness measurement
    EPBrightness = map(analogRead(A1), 200, 1023, 255, 0);
    brightness = map(EPBrightness, 0, 255, 9, 0);

    //Ultrasonic ranging
    digitalWrite(8, LOW);
    delayMicroseconds(2);
    digitalWrite(8, HIGH);
    delayMicroseconds(10);
    digitalWrite(8, LOW);
    distance = pulseIn(7, HIGH) / 58.0;

    //Reading sound transducer
    volume = analogRead(A0);

    //Reading collision sensor
    if (digitalRead(3) == LOW)
    {
        hit = 1;
    }
    else
    {
        hit = 0;
    }

    //PIR Sensor
    if (digitalRead(4) == HIGH)
    {
        detect = 1;
    }
    else
    {
        detect = 0;
    }

    //Assemble sensor information
    String information = "" +
                         String(hit) + ";" +
                         String(detect) + ";" +
                         String(brightness) + ";" +
                         "1" + ";" +
                         String(distance) + ";" +
                         String((int)DHT11.temperature) + ";" +
                         String((int)DHT11.humidity) + ";" +
                         String(volume);
                         
    //Checking assembled information
    Serial.print("Sending to Wifi Module: ");
    Serial.println(information);
    
    //Sending information to ESP8266
    Serial.println("Sending...");
    mySerial.print(information);
    Serial.println("Sending Complete!");
    
    //LCD screen display the sensor information
    LCDShowInfo();
}
