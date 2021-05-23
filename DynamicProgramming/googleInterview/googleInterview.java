package DynamicProgramming.googleInterview;

public class googleInterview {
    /**
     * Q1
     * Longest Palindromic substring
     * Given a string s, return the longest palindromic substring s
     * e.g: s = "cbbd" => "bb"
     * */
    //TODO
    //https://leetcode.com/problems/longest-palindromic-substring/discuss/425016/Java-DP
    //not finished
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        // go through string i
            // look back j
                // if char[j]== char[i], j == 0 is palindrome => res = 1;
                // if char[j]== char[i], j >= i + 1, is palindrome => res = Math.max(res, i - j + 1);
        int[] dp = new int[n];
        char[] chars = s.toCharArray();
        String res = null;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (chars[i] == chars[j] && (j >= i + 1 || isPalindrome[j+1][i-1])) {
                    isPalindrome[j][i] = true;
                    res = res.length() < (i-j) ? s.substring(i,j) : res;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
