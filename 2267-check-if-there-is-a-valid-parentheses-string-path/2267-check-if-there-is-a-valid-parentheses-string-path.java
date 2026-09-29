class Solution {
    private Boolean[][][] memo;

    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Total path length odd nahi ho sakti
        if ((m + n - 1) % 2 != 0) return false;
        // Start '(' aur End ')' hona chahiye
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') return false;

        // Memoization array size
        memo = new Boolean[m][n][m + n];
        return dfs(grid, 0, 0, 0, m, n);
    }

    private boolean dfs(char[][] grid, int r, int c, int open, int m, int n) {
        if (grid[r][c] == '(') open++;
        else open--;

        // Invalid path length or closing brackets exceed opening ones
        if (open < 0 || open > (m + n) / 2) return false;

        // Reached destination
        if (r == m - 1 && c == n - 1) {
            return open == 0;
        }

        if (memo[r][c][open] != null) {
            return memo[r][c][open];
        }

        boolean result = false;

        // Move Down
        if (r + 1 < m) {
            result = result || dfs(grid, r + 1, c, open, m, n);
        }

        // Move Right
        if (c + 1 < n) {
            result = result || dfs(grid, r, c + 1, open, m, n);
        }

        return memo[r][c][open] = result;
    }
}