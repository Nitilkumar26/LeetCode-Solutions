class Solution {
    public int maxSum(List<Integer> nums, int k) {
        final int MOD = 1_000_000_007;
        int[] cnt = new int[31];

        for (int x : nums) {
            for (int b = 0; b < 31; b++) {
                if (((x >> b) & 1) == 1) cnt[b]++;
            }
        }

        long ans = 0;
        for (int i = 0; i < k; i++) {
            long cur = 0;
            for (int b = 0; b < 31; b++) {
                if (cnt[b] > 0) {
                    cur |= (1L << b);
                    cnt[b]--;
                }
            }
            if (cur == 0) break;
            ans = (ans + (cur % MOD) * (cur % MOD)) % MOD;
        }
        return (int) ans;
    }
}