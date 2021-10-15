#include <TM1637.h>

#define CLK A2
#define DIO A3

int HOUR=9;
int MINUTE=34;
int SECOND=50;//Initial the time with 9h34m50s

TM1637 TM(CLK,DIO);//Initial TM1637

void setup()
{
  Serial.begin(9600);
  TM.init();
  TM.point(0);//Set center points dark
  TM.set(BRIGHT_TYPICAL);
 }

void loop()
{
  TM.display(0,HOUR/10);//First position
  TM.display(1,HOUR%10);//Second position
  TM.display(2,MINUTE/10);//Third position
  TM.display(3,MINUTE%10);//Fourth position

  delay(1000);//Delay in one second and 
  TM.point(1);//Set center points light

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
  else if(SECOND%2 == 0)
  {
    TM.point(0);//Set center points dark
    SECOND = SECOND+1;
  }
  else
  {
    SECOND = SECOND+1;
  }
}
