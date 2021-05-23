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

        String[] dp = new String[n];
        char[] chars = s.toCharArray();
        String res = s.substring(0,1);
        for (int i = 0; i < n; i++) {
            dp[i] = s.substring(i,i+1);
            int maxLen = 1;
            for (int j = 0; j <= i; j++) {
                if (chars[i] == chars[j] && (j + 1 > i -1 || isPalindrome[j+1][i-1])) {
                    isPalindrome[j][i] = true;
                    if (i - j >= maxLen) {
                        maxLen = i - j;
                        dp[i] = s.substring(j,i+1);
                    }
                }
            }
        }
        for(String str: dp) {
            if(str.length() > res.length()) {
                res = str;
            }
        }
        return res;
    }

    /**
     * Q2 maximum profit to sell stocks
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        //Input: prices = [7,1,5,3,6,4]
        //                [        0,]
        // Output: 5, buy at 1, sell at 6
        //dp represents current maximum profit
        int size = prices.length;
        if(size < 2)
            return 0;
        int[] dp = new int[size];
        int max = prices[size-1];
        for(int i = size-2; i >= 0 ; i--){
            max= Math.max(prices[i], max);//(6,4)=>6
            dp[i] = Math.max(max-prices[i], dp[i+1]);//
        }
        return dp[0];
    }

    public static void main(String[] args) {

    }
}
