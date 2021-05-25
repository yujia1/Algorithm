package DynamicProgramming.googleInterview;

import java.util.Arrays;

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

    /**
     * Q3
     * Given an integer array nums, find a contiguous non-empty subarray within the array
     * that has the largest product, and return the product.
     * @param nums
     * @return
     */
    public int maxProductSubArray(int[] nums) {
        if (nums.length == 0) return 0;
        int currMax = nums[0];
        int currMin = nums[0];
        int res = currMax;
        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];
            //tempMax is used for store temp max and calculation of currMin use previous currMax, then update currMax,
            int tempMax = Math.max(curr, Math.max(curr * currMax, curr* currMin));
            currMin = Math.min(curr, Math.min(curr * currMax, curr * currMin));
            currMax = tempMax;
            res = Math.max(res, currMax);
        }
        return res;
    }

    /**
     * Q4
     * You are given an integer array coins representing coins of different denominations
     * and an integer amount representing a total amount of money.
     * e.g:
     * Input: coins = [1,2,5], amount = 11
     * 11 / 5 = 2, if % return, or remind / 2..
     * Output: 3
     * Explanation: 11 = 5 + 5 + 1
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];// current min number of coins, given amount i-th index
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {// i represents reminder
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount? - 1 : dp[amount];
    }

    /**
     * Given an array nums which consists of non-negative integers and an integer m,
     * you can split the array into m non-empty continuous subarrays.
     * @param nums
     * @param m
     * @return
     */
    public int splitArray(int[] nums, int m) {
        return 0;
    }

    public static void main(String[] args) {
        googleInterview test = new googleInterview();
        int[] coins = new int[] {1,2,5};
        System.out.print(test.coinChange(coins, 11));
    }
}
