#include <string.h>
int getValue(char c) {
    if (c == 'I')
        return 1;
  else if (c == 'V')
        return 5;
   else if (c == 'X')
         return 10;
    else if (c == 'L')
          return 50;
    else if (c == 'C')
        return 100;
    else if (c == 'D')
        return 500;
    else if (c == 'M')
        return 1000;
        return 0;
}
int romanToInt(char* s) {
    int total = 0;
    int len = strlen(s);

    for (int i = 0; i < len; i++) {
        // Current character ki value
        int current = getValue(s[i]);

        // Agle character ki value (agar aage letter ho)
        int next = (i + 1 < len) ? getValue(s[i + 1]) : 0;

        //Agar chhota symbol pehle aaye toh subtract karo
        if (current < next) {
            total = total - current;
        } else {
            total = total + current;
        }
    }
    
    return total; 
    }

    
    
