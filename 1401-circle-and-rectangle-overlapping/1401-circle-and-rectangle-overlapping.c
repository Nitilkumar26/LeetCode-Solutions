
 #include <stdbool.h>

// Helper function jo value ko bound karta hai
int clamp(int val, int min, int max) {
    if (val < min) return min;
    if (val > max) return max;
    return val;
}

bool checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
    // 1. Rectangle par sabse paas ka X aur Y coordinate
    int nearestX = clamp(xCenter, x1, x2);
    int nearestY = clamp(yCenter, y1, y2);

    // 2. Center se nearest point ka distance
    int distX = xCenter - nearestX;
    int distY = yCenter - nearestY;

    // 3. Compare distance squared with radius squared
    return (distX * distX + distY * distY) <= (radius * radius);
}   
