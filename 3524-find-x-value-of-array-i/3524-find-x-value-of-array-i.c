/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
long long* resultArray(int* nums, int numsSize, int k, int* returnSize) {
    long long* result = (long long*)calloc(k, sizeof(long long));
    *returnSize = k;
    
    // DP array to store frequencies of remainders
    long long dp[k];
    for (int i = 0; i < k; i++) {
        dp[i] = 0;
    }
    
    for (int i = 0; i < numsSize; i++) {
        long long next_dp[k];
        for (int j = 0; j < k; j++) {
            next_dp[j] = 0;
        }
        
        int val = nums[i] % k;
        next_dp[val]++;
        
        for (int r = 0; r < k; r++) {
            if (dp[r] > 0) {
                int new_r = (int)(((long long)r * val) % k);
                next_dp[new_r] += dp[r];
            }
        }
        
        for (int r = 0; r < k; r++) {
            dp[r] = next_dp[r];
            result[r] += dp[r];
        }
    }
    
    return result;
}