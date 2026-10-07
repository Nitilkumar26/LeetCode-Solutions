import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int remL = 0, remR = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') remL++;
            else if (c == ')') {
                if (remL > 0) remL--;
                else remR++;
            }
        }
        Set<String> res = new HashSet<>();
        dfs(s, 0, 0, 0, remL, remR, new StringBuilder(), res);
        return new ArrayList<>(res);
    }

    private void dfs(String s, int i, int open, int close, int remL, int remR,
                     StringBuilder sb, Set<String> res) {
        if (i == s.length()) {
            if (remL == 0 && remR == 0) res.add(sb.toString());
            return;
        }
        char c = s.charAt(i);
        int len = sb.length();

        // Remove this parenthesis
        if (c == '(' && remL > 0) dfs(s, i + 1, open, close, remL - 1, remR, sb, res);
        if (c == ')' && remR > 0) dfs(s, i + 1, open, close, remL, remR - 1, sb, res);

        // Keep it
        sb.append(c);
        if (c != '(' && c != ')') dfs(s, i + 1, open, close, remL, remR, sb, res);
        else if (c == '(') dfs(s, i + 1, open + 1, close, remL, remR, sb, res);
        else if (open > close) dfs(s, i + 1, open, close + 1, remL, remR, sb, res);
        sb.setLength(len);
    }
}