#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* reverseParentheses(char* s) {
    int n = strlen(s);
    char* stack = (char*)malloc((n + 1) * sizeof(char));
    int top = -1;

    for (int i = 0; i < n; i++) {
        if (s[i] == ')') {
            // Stack se characters ko pop karke temp buffer me rakhein
            char temp[n + 1];
            int temp_len = 0;

            while (top >= 0 && stack[top] != '(') {
                temp[temp_len++] = stack[top--];
            }

            // '(' ko remove karein
            if (top >= 0 && stack[top] == '(') {
                top--;
            }

            // Reverse kiye gaye characters ko wapas stack me push karein
            for (int j = 0; j < temp_len; j++) {
                stack[++top] = temp[j];
            }
        } else {
            stack[++top] = s[i];
        }
    }

    // Stack ke end me null terminator lagayein
    stack[++top] = '\0';
    return stack;
}