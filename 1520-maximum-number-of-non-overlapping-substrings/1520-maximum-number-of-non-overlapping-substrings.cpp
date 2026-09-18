class Solution {
public:
    vector<string> maxNumOfSubstrings(string s) {
        int n = s.length();
        vector<int> l(26, INT_MAX), r(26, -1);
        
        // Step 1: Har character ka pehla aur aakhri index
        for (int i = 0; i < n; i++) {
            l[s[i] - 'a'] = min(l[s[i] - 'a'], i);
            r[s[i] - 'a'] = max(r[s[i] - 'a'], i);
        }
        
        vector<string> res;
        int last = -1;
        
        // Step 2 & 3: Valid intervals dhoondho aur res me daalo
        for (int i = 0; i < n; i++) {
            if (i == l[s[i] - 'a']) {
                int new_r = checkSubstr(s, i, l, r);
                if (new_r != -1) {
                    if (i > last) {
                        res.push_back("");
                    }
                    last = new_r;
                    res.back() = s.substr(i, last - i + 1);
                }
            }
        }
        return res;
    }

private:
    int checkSubstr(string &s, int i, vector<int> &l, vector<int> &r) {
        int right = r[s[i] - 'a'];
        for (int j = i; j <= right; j++) {
            if (l[s[j] - 'a'] < i) return -1;
            right = max(right, r[s[j] - 'a']);
        }
        return right;
    }
};