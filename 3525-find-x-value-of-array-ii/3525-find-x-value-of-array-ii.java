 class Solution {
    int[] tree_prod;
    int[][] tree_cnt;
    int K;

    class Node {
        int prod;
        int[] cnt;
        Node() {
            cnt = new int[K];
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        this.K = k;
        tree_prod = new int[4 * n];
        tree_cnt = new int[4 * n][k];
        build(1, 0, n - 1, nums);

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            
            update(1, 0, n - 1, index, value);
            
    
            Node resNode = query(1, 0, n - 1, start, n - 1);
            result[i] = resNode.cnt[x];
        }
        
        return result;
    }

    private void build(int node, int l, int r, int[] nums) {
        if (l == r) {
            tree_prod[node] = nums[l] % K;
            tree_cnt[node][nums[l] % K] = 1;
            return;
        }
        int mid = l + (r - l) / 2;
        build(2 * node, l, mid, nums);
        build(2 * node + 1, mid + 1, r, nums);
        pushUp(node);
    }

    private void pushUp(int node) {
        int left = 2 * node;
        int right = 2 * node + 1;
        
       
        tree_prod[node] = (tree_prod[left] * tree_prod[right]) % K;
        
 
        for (int i = 0; i < K; i++) {
            tree_cnt[node][i] = tree_cnt[left][i];
        }
        
       
        for (int i = 0; i < K; i++) {
            if (tree_cnt[right][i] > 0) {
                int new_val = (tree_prod[left] * i) % K;
                tree_cnt[node][new_val] += tree_cnt[right][i];
            }
        }
    }

    private void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            tree_prod[node] = val % K;
            for (int i = 0; i < K; i++) {
                tree_cnt[node][i] = 0;
            }
            tree_cnt[node][val % K] = 1;
            return;
        }
        
        int mid = l + (r - l) / 2;
        if (idx <= mid) {
            update(2 * node, l, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, r, idx, val);
        }
        pushUp(node);
    }

    private Node query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            Node res = new Node();
            res.prod = tree_prod[node];
            for (int i = 0; i < K; i++) {
                res.cnt[i] = tree_cnt[node][i];
            }
            return res;
        }
        
        int mid = l + (r - l) / 2;
        if (qr <= mid) {
            return query(2 * node, l, mid, ql, qr);
        }
        if (ql > mid) {
            return query(2 * node + 1, mid + 1, r, ql, qr);
        }

        
        Node leftNode = query(2 * node, l, mid, ql, qr);
        Node rightNode = query(2 * node + 1, mid + 1, r, ql, qr);
        
        Node res = new Node();
        res.prod = (leftNode.prod * rightNode.prod) % K;
        for (int i = 0; i < K; i++) {
            res.cnt[i] = leftNode.cnt[i];
        }
        for (int i = 0; i < K; i++) {
            if (rightNode.cnt[i] > 0) {
                int new_val = (leftNode.prod * i) % K;
                res.cnt[new_val] += rightNode.cnt[i];
            }
        }
        return res;
    }
}