class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, Math.abs(nums1[i] - nums2[i]));
        }

        // cnt[d] = how many positions have difference d
        long[] cnt = new long[max + 1];
        long total = 0;
        for (int i = 0; i < n; i++) {
            int d = Math.abs(nums1[i] - nums2[i]);
            cnt[d]++;
            total += d;
        }

        // Not enough moves to matter: all differences become 0
        if (total <= k) return 0;

        // Reduce the largest differences first
        for (int d = max; d > 0 && k > 0; d--) {
            if (cnt[d] == 0) continue;
            long move = Math.min(cnt[d], k);
            cnt[d] -= move;
            cnt[d - 1] += move;
            k -= move;
        }

        long result = 0;
        for (int d = 1; d <= max; d++) {
            result += cnt[d] * (long) d * d;
        }
        return result;
    }
}