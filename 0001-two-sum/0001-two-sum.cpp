#include <unordered_map>
class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
                unordered_map<int, int> seen;
        for (int i = 0; i < nums.size(); i++) {
            int diff = target - nums[i];
            if (seen.find(diff) != seen.end()) {
                return {seen[diff], i};
            }
            seen[nums[i]] = i;
        }
        return {};

        
    }
};