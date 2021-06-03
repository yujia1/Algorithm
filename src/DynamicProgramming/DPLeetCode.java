package DynamicProgramming;

import java.util.Arrays;
import java.util.List;

/**
 * https://dev.to/nikolaotasevic/dynamic-programming--7-steps-to-solve-any-dp-interview-problem-3870
 */
public class DPLeetCode {
    /**
     * Q1
     * climbStairs
     * https://leetcode.com/problems/climbing-stairs
     * @param n
     * @return
     * TC: O(n)
     * SC: O(n)
     */
    public int climbStairs(int n) {
        // in order to know how many distinct ways to climb 4-th stairs, we have know 3-th and 2-th
        // break down it f(n) = f(n-1) + f(n-2)
        // dp represents steps to climb current stairs index-th
        int[] dp = new int[n + 1];
        //base case
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
    /**
     * Q2
     * https://leetcode.com/problems/coin-change
     * coin change
     *  TC: O(n)
     *  SC: O(n * m) where m is length of coins
     */
    public int coinChange(int[] coins, int n) {
        //dp represents minimum amount coins to equals money index-th
        int[] dp = new int[n+1];
        Arrays.fill(dp, n+1);
        dp[0] = 0;
        for (int i = 1; i <=n; i++) { // i represents amount money
            for (int j = 0; j < coins.length; j++) { // j just iterator coins[]
                if (i - coins[j] >= 0) { // if current money is bigger or equals than current coins currency coins[j], we can use this currency
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1); // i - coins[j] means x-th index of dp + 1 can equals to current money i
                }
            }
        }
        return dp[n] > n ? -1 : dp[n]; // if dp[n] does not change, then there is no way to exchange coins to equals n. otherwise, return dp[n]
    }
    /**Q3
     * leetcode: https://leetcode.com/problems/longest-increasing-subsequence/
     * longest increasing subsequence
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        //[10,9,2,5,3,7,101,18]
        // 1, 1,1,2,2, 3,
        if (nums.length == 0) {
            return 0;
        }
        // dp represents longest increasing subsequence
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxLength = 1;
        for (int i = 1; i < nums.length; i++) {
            int maxVal = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    //[10,9,2,5,3,7,101,18]
                    // 1, 1,1,2,2, 3,
                    maxVal = Math.max(maxVal, dp[j]); // find current maxLength within ith e.g.: when traverse 101, we want 7 and corresponding 3 rather than 10 corresponding to 1
                }
            }
            dp[i] = maxVal + 1;
            maxLength = Math.max(dp[i], maxLength);
        }
        return maxLength;
    }
    /**
     * Q4
     * leetcode:https://leetcode.com/problems/word-break
     * word break
     * @param s
     * @param wordDict
     * @return
     * TC: O(n^2)
     * space= O(n) where n is length of input;)
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // empty
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= i; j++) {
                if(dp[j] && wordDict.contains(s.substring(j,i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
