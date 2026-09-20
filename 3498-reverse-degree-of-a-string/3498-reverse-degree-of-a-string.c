#include <string.h>
int reverseDegree(char* s) {
    int ans = 0;
    int len = strlen(s);

    for (int i = 0; i < len; i++) {
        // 'a' -> 26, 'z' -> 1
        int char_pos = 26 - (s[i] - 'a');

        // 1-based position ke liye (i + 1) multiply karenge
        ans += char_pos * (i + 1);
    }
    return ans;
    
    
}