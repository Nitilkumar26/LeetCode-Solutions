 #include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define TABLE_SIZE 100003 // Large prime size for hash table

typedef struct Node {
    char* key;
    char* val;
    struct Node* next;
} Node;

// Hash function for strings (djb2)
unsigned long hash(const char* str) {
    unsigned long hash = 5381;
    int c;
    while ((c = *str++)) {
        hash = ((hash << 5) + hash) + c; // hash * 33 + c
    }
    return hash % TABLE_SIZE;
}

char* evaluate(char* s, char*** knowledge, int knowledgeSize, int* knowledgeColSize) {
    // Step 1: Create Hash Table
    Node** hashTable = (Node**)calloc(TABLE_SIZE, sizeof(Node*));
    
    for (int i = 0; i < knowledgeSize; i++) {
        char* k = knowledge[i][0];
        char* v = knowledge[i][1];
        unsigned long idx = hash(k);
        
        Node* newNode = (Node*)malloc(sizeof(Node));
        newNode->key = k;
        newNode->val = v;
        newNode->next = hashTable[idx];
        hashTable[idx] = newNode;
    }

    // Step 2: Parse and build output
    int sLen = strlen(s);
    char* result = (char*)malloc(sizeof(char) * 200005);
    int resIdx = 0;

    int i = 0;
    while (i < sLen) {
        if (s[i] == '(') {
            i++;
            int start = i;
            while (s[i] != ')') {
                i++;
            }
            int keyLen = i - start;

            char key[keyLen + 1];
            strncpy(key, s + start, keyLen);
            key[keyLen] = '\0';

            // Fast hash lookup O(1)
            unsigned long idx = hash(key);
            Node* curr = hashTable[idx];
            char* val = NULL;

            while (curr != NULL) {
                if (strcmp(curr->key, key) == 0) {
                    val = curr->val;
                    break;
                }
                curr = curr->next;
            }

            if (val != NULL) {
                int valLen = strlen(val);
                strcpy(result + resIdx, val);
                resIdx += valLen;
            } else {
                result[resIdx++] = '?';
            }
            i++; // skip ')'
        } else {
            result[resIdx++] = s[i++];
        }
    }

    result[resIdx] = '\0';

    // Step 3: Free memory
    for (int i = 0; i < TABLE_SIZE; i++) {
        Node* curr = hashTable[i];
        while (curr != NULL) {
            Node* temp = curr;
            curr = curr->next;
            free(temp);
        }
    }
    free(hashTable);

    return result;
}