#include <SoftwareSerial.h>
#include <dht11.h>
#include <Wire.h>
#include <LiquidCrystal_I2C.h>
#include <IRremote.h>

SoftwareSerial mySerial(6, 5); // RX, TX 配置 6、5 为软串口
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
    lcd.setCursor(0, 0); //light
    lcd.print("Light:");
    lcd.print(brightness, 10);
    if (distance > 7)
    {
        lcd.print("  Door:O");
    }
    else
    {
        lcd.print("  Door:X");
    }
    lcd.setCursor(0, 1);
    lcd.print("H:");
    lcd.print((int)DHT11.humidity);
    lcd.print("%");
    lcd.print(" T:");
    lcd.print((int)DHT11.temperature);
    lcd.print("C");
}

void setup()
{
    //1602初始化
    Serial.begin(115200);
    lcd.init();
    lcd.backlight();

    while (!Serial)
    {
        ;
    }
    mySerial.begin(115200);

    //ULTRASONIC
    pinMode(8, OUTPUT); //ULTRASONIC_Trig
    pinMode(7, INPUT);  //ULTRASONIC_Echo

    //HIT
    pinMode(3, INPUT);

    //LIGHT SENSOR
    pinMode(A1, INPUT);

    //BODY SENSOR
    pinMode(4, INPUT);
}

void loop()
{
    delay(5000);

    int chk = DHT11.read(2); //读取DHT11

    EPBrightness = map(analogRead(A1), 0, 1023, 255, 0);
    brightness = map(EPBrightness, 0, 255, 0, 9);

    //超声波测距
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

    //人体传感
    if (digitalRead(4) == HIGH)
    {
        detect = 1;
    }
    else
    {
        detect = 0;
    }

    String information = "" +
                         String(hit) + ";" +
                         String(detect) + ";" +
                         String(brightness) + ";" +
                         "1" + ";" +
                         String(distance) + ";" +
                         String((int)DHT11.temperature) + ";" +
                         String((int)DHT11.humidity) + ";" +
                         String(volume);
    information = "0;1;5;9;10.00;0;0;41";

    Serial.print("Sending to Wifi Module: ");
    Serial.println(information);

    Serial.println("Sending...");
    mySerial.print(information);
    Serial.println("Sending Complete!");

    LCDShowInfo();
}
