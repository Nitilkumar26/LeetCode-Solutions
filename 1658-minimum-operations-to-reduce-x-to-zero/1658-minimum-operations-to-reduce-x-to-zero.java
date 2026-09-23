class Solution {
    public int minOperations(int[] nums, int x) {
        int total = 0;

        // Array का total sum
        for (int num : nums) {
            total += num;
        }

        int target = total - x;

        // अगर target negative है, तो answer possible नहीं
        if (target < 0) {
            return -1;
        }

        int left = 0;
        int sum = 0;
        int maxLength = -1;

        // Longest subarray जिसका sum = target
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (sum > target && left <= right) {
                sum -= nums[left];
                left++;
            }

            if (sum == target) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }

        // जितने elements बाहर से हटेंगे = operations
        if (maxLength == -1) {
            return -1;
        }

        return nums.length - maxLength;
    }
}