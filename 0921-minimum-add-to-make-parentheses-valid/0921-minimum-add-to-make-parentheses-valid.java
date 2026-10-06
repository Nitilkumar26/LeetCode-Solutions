class Solution {
    public int minAddToMakeValid(String s) {
        int open = 0;   // unmatched '('
        int add = 0;    // unmatched ')' that need a '(' added

        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++;
            } else if (open > 0) {
                open--;      // ')' matches an earlier '('
            } else {
                add++;       // ')' with nothing to match
            }
        }
        return add + open;
    }
}