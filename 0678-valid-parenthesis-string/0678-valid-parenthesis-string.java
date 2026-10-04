class Solution {
    public boolean checkValidString(String s) {
        int lo = 0, hi = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                lo++;
                hi++;
            } else if (c == ')') {
                lo--;
                hi--;
            } else { // '*'
                lo--;   // treat as ')'
                hi++;   // treat as '('
            }
            if (hi < 0) return false;  // too many ')' even in best case
            if (lo < 0) lo = 0;        // can't have negative open count
        }
        return lo == 0;
    }
}