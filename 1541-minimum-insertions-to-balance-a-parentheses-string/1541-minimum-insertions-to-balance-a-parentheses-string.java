class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int open = 0; // unmatched '('

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                open++;
            } else {
                // Found ')': check if the next char is also ')'
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++; // consume the pair "))"
                } else {
                    insertions++; // insert one ')' to complete the pair
                }
                if (open > 0) {
                    open--;
                } else {
                    insertions++; // insert a '(' to match this pair
                }
            }
        }

        // Each leftover '(' needs two ')'
        return insertions + open * 2;
    }
}