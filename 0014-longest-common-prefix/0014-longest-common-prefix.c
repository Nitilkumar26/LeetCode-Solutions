#include <stdlib.h>
#include <string.h>

char* longestCommonPrefix(char** strs, int strsSize) {
    if (strsSize == 0) {
        char* empty = malloc(1);
        empty[0] = '\0';
        return empty;
    }

    int len = strlen(strs[0]);

    for (int i = 1; i < strsSize; i++) {
        int j = 0;
        while (j < len && strs[i][j] != '\0' && strs[i][j] == strs[0][j]) {
            j++;
        }
        len = j;  // prefix ko chhota karo
    }

    char* result = malloc(len + 1);
    strncpy(result, strs[0], len);
    result[len] = '\0';
    return result;
}