#include <TM1637.h>

#define CLK A2
#define DIO A3
#define TEST_DELAY   2000//延时时间
int HOUR=23;
int MINUTE=59;
int SECOND=50;

TM1637 TM(A2,A3);

void setup()
{
  Serial.begin(9600);
  TM.init();
  TM.point(1);
  TM.set(BRIGHT_TYPICAL);

  Serial.print("adjusted done.............");
 }

void loop()
{

  TM.display(0,HOUR/10);
  TM.display(1,HOUR%10);
  TM.display(2,MINUTE/10);
  TM.display(3,MINUTE%10);

  delay(1000);
  if(SECOND==59)
  {
    SECOND = 0;
    if(MINUTE==59)
    {
      MINUTE=0;
      if(HOUR==23)
      {
        HOUR = 0;
      }
      else
      {
        HOUR = HOUR+1;
      }
    }
    else
    {
      MINUTE=MINUTE+1;
    }
  }
  else
  {
    SECOND = SECOND+1;
  }
  

}
