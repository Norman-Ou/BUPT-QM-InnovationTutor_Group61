# BUPT-QM-InnovationTutor_Group61

Project: Smart home model

**Scenario**

- Your company is requested to design and build a prototype
  of a smart home system that showcases
  - electronic installation (sensors, display, etc.)
  - a smart home web application for monitoring temperature and
    humidity, and control for security and comfort purposes.
- As extra functions, the system may check if the doors and
  windows are open or closed and detects movements for
  burglary intrusion and sounds an alarm if the sensor
  detects a movement.

## Requirement

![image-20211014190748409](https://typorastroage.oss-cn-beijing.aliyuncs.com/img/image-20211014190748409.png)

![image-20211014190808771](https://typorastroage.oss-cn-beijing.aliyuncs.com/img/image-20211014190808771.png)

![image-20211014190820383](https://typorastroage.oss-cn-beijing.aliyuncs.com/img/image-20211014190820383.png)

# Telecommunications Engineering with Management

## Circuit board naming

Arduino：**Arduino Board/Sensor Board**

ESP8266：**Wifi Board/Central Board**

## Circuit board installation of the original and the role of the circuit board

### Arduino

All sensors are mounted on the circuit board

- Sound transducer

- Ranging instrument (ultrasonic sensor)
- Luminance sensor
- Collision sensor
- Infrared sensor (body sensor)
- Temperature and humidity sensor

**Function: All sensor information is transmitted to the ESP8266 circuit board ==every 5 seconds==**

### ESP8266

Installation original:

- buzzer
- RGB Light
- Infrared receiver (Remoter)
- Bluetooth receiver

**Function:** 

① The messages transmitted by Arduino are disassembled, processed, spliced, and then sent to the server.

- disassembled
  - Separate collision, infrared (human), distance, and volume from the integrated information transmitted
- processed
  - Judge according to certain conditions (` hit == 1 || distance > 7 || detect == 1 || volume > 60`). If the condition is met, the alarm is reported
- spliced
  - Remove collision and infrared (body) information
  - Spell the alarm information close to the original (Information transmitted by Arduino) information
  - Replace the color of the lamp with the default bit of the color information in the original (Arduino information) information
- Send to the server
  - Sends the spliced general *information* to the server
  - Topic: `61_Temperature_Humididy`

② Install controllable components for control

- RGB Light
  - Realize through WIFI interconnection, webpage to light RGB three colors, nine colors of independent adjustment
  - Realize remote control to RGB light three colors, 9 colors of independent adjustment
  - Achieve bluetooth light RGB three colors, 9 colors of independent adjustment
- Alarm system
  - Realize the switch control of the alarm system through WIFI interconnection and web page

### Alarm system

Realize the house to warn the intruder, to remind the owner. The alarm system can be remotely controlled on and off.

**The alarm system is turned on by default at startup**

**After receiving the remote shutdown command, the alarm system will not give feedback to any information that can trigger the warning**

The alarm system restarts automatically at regular intervals (currently set to one minute). Make sure the house is in a high state of security.

## Explanation of the problem that the server contents cannot be updated in time when the controllable components are controlled by multiple control devices

*Arduino sends a message to ESP8266 every 5 seconds*

8266 does the operations mentioned above on the information after receiving it, and sends the message to the server. **That is to say, 8266 will only send data to the server every 5 seconds**

- Explanation of how to update status to server if other controllable variables (color and brightness of lamp) are changed by other control devices (Bluetooth or remote control) within 5 seconds:
  - The brightness of the lamp is not uploaded to the cloud, *The brightness information in the cloud is the ambient brightness*
  - The color of the lamp is updated by overwriting the reserved bits in the message sent by the Arduino before each message is sent to the server.
  - *In other words, if any other device changes the message within 5 seconds, the server will not receive the update immediately, but will wait for the next time to receive it*

## Format description of messages sent to ESP8266 by Arduino

```c
String information = "" +
                     String(hit) + ";" +
                     String(detect) + ";" +
                     String(brightness) + ";" +
                     "1" + ";" +
                     String(distance) + ";" +
                     String((int)DHT11.temperature) + ";" 
                     String((int)DHT11.humidity) + ";" +
                     String(volume);
```

## The FORMAT of the message that the ESP8266 board sends to the server

```c
String information = "" +
                     String(brightness) + ";" +
                     lightType + ";" +
                     String(distance) + ";" +
                     String((int)DHT11.temperature) + ";" 
                     String((int)DHT11.humidity) + ";" +
                     String(volume) + ";" +
    				 warning;
```

