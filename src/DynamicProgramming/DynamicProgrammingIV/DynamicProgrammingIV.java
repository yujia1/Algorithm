package DynamicProgramming.DynamicProgrammingIV;

import java.util.Arrays;
/**
 * laioffer
 * questions
 * https://app.laicode.io/app/training-plan/3
 * answers
 * https://docs.google.com/spreadsheets/d/1_o8HP7O6jwpftkkf4OAmZrh7FZ6k8blw8_JC4HmdZRw/edit?usp=sharing
 * */
public class DynamicProgrammingIV {
    /**
     * Q1.1
     * longest sub-array
     * e.g: {7,2,3,1,5,8,9,6} -> longest sub-array is {1,5,8,9} and output is length of sub-array 4
     * @param array
     * @return integer
     * @Algorithm time = O(n) space= O(n) where n is length of array;
     */

    public static int longestSubArray(int[] array) {
        // corner case
        if (array==null || array.length == 0) {
            return 0;
        }
        int[] M = new int[array.length];
        M[0] = 1;
        int max = 1;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[i-1]) {
                M[i] = M[i-1] + 1;
            } else {
                M[i] = 1;
            }
            max = Math.max(max, M[i]);
        }
        return max;
    }

    /**
     * Q1.2
     * longest sub-sequence
     * e.g: {7,2,3,1,5,8,9,6} -> longest sub-sequence is {2,3,5,8,9} and output is length of sub-array 5
     * @param nums
     * @return integer
     * @Algorithm
     */
    public static int longestSubSequence(int[] nums) {
        //{7,2,3,1,5,8,9,6}
        //{1,1,2,1,3,4,5,4}
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < dp.length; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxval = Math.max(maxval, dp[j]); // find  Math.max(dp[j])
                }
            }
            dp[i] = maxval + 1;
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }

    /**
     * Q1.3
     * count ascending subsequence(not necessarily the longest)
     * e.g: {1, 3, 2a, 2b}
     * count = 7 {1, 3, 2a, 2b, {1,3}, {1, 2a},{1, 2b}}
     * time complexity: O(nlogn)
     * follow up:
     * https://leetcode.com/problems/increasing-subsequences/
     * */
    public static int countAscendingSub(int[] array) {
        int[] dp = new int[array.length];
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[j] < array[i]) {
                    dp[i] +=  dp[j];
                }
            }
        }
        for (int i = 0; i < dp.length; i++) {
            res += dp[i];
        }
        return res;
    }

    /**
     * Q1.4
     * Given an array pf coordinates of 2D pts, how to find the largest subset of pts in which
     * any pair of pts can form a line with positive slope
     * step1: sort input pts according to their x-coordinates A[n]
     * step2: find the longest ascending sub-sequence in A[n] according to y-coordinates
     * similar with ascending subsequence
     * */

    /**
     * Q2.1
     * longest common substring/subsequence between two strings
     * solution consecutive letters
     * e.g: "student & sweden"
     * return length of "den"
     * time complexity: O(n*m)
     * Space complexity: O(m*n)
     * */
    public static int longestCommonSubString(String a, String b) {
        int res = 0;
        char[] arr1 = a.toCharArray();
        char[] arr2 = b.toCharArray();
        int[][] dp = new int[a.length()+1][b.length()+1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (arr1[i-1] == arr2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = 0;
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }

    /**
     * Q2.1
     * longest common substring/subsequence between two strings
     * solution no necessary consecutive letters
     * e.g: "student & sweden"
     * return length of "den"
     * time complexity: O(n*m)
     * Space complexity: O(m*n)
     * */
    public static int longestCommonSubsequence(String a, String b) {
        int res = 0;
        char[] arr1 = a.toCharArray();
        char[] arr2 = b.toCharArray();
        int[][] dp = new int[a.length()+1][b.length()+1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (arr1[i-1] == arr2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }

    /**
     * Q3 DP 中心开花
     * cutting wood
     * */

    /**
     * Q4 optimal game
     * You are given an array A of size N. The array contains integers and is of even length. The elements of the array represent N coin of values x1, x2, ... ,xn. The rule of game is:
     * Each of the players gets alternative turns.
     * In each turn, a player selects either the first or last coin from the row, removes it
     * permanently, and receives the value of the coin.
     * Both the player plays in an optimal way, i.e., both want to maximize total winning money.
     * dp[i][j] = f(i, j)= maximum value when ith to jth coins are remaining
     * e.g:
     * we can pick x_i: opponents x_i+1 corresponds to f(i+2, j) or x_j corresponds to f(i+1, j -1)
     * or pick x_j: opponents x_i corresponds to f(i+1, j -1) or x(j-1) corresponds f(i,j-2)
     * */

    public static int optimalGame(int[]array) {
        int[][] dp = new int[array.length][array.length];
        int res = 0;
        for (int i = 0; i < array.length; i++) {
            dp[i][i] = array[i]; // when only one number (index i) left, fill diagonal of [i][i]
            if ( i != array.length-1) {
                dp[i][i+1] = (array[i] > array[i+1]) ? array[i]: array[i+1]; //when there are two number to choose, fill the [i][i+1]
            }
        }
        for (int len = 2; len < array.length; len++) { // arr 的不同长度下的情况
            for (int i = 0, j = len; j < array.length; i++, j++) { // i 是从头开始pick，j是从尾部pick
                // we pick i and opponent may pick i+1, or j, then we pick min([i+2][j],dp[i+1][j-1]
                int a = array[i] + Math.min(dp[i+2][j], dp[i+1][j-1]);
                // we pick j and opponent may pick i or j -1, then we pick min([i+1][j-1], [i][j-2])
                int b = array[j] + Math.min(dp[i+1][j-1], dp[i][j-2]);
                dp[i][j] = Math.max(a,b);
            }
        }
        return dp[0][array.length-1];
    }
    public static void main(String[] args) {
        //test Q1.2
        int[] arr = new int[]{7,2,3,1,5,8,9,6};
        System.out.println("longestSubSequence: " +longestSubSequence(arr));
        //test Q1.3
        int[] arr2 = new int[]{7,4,6,8}; // 7, 4, 6, 46, 8,78,48,68,468,
        System.out.println("countAscendingSub: " + countAscendingSub(arr2));
        //test Q2.1
        System.out.println("longestCommonSubString: " + longestCommonSubString("student", "swden"));
        //test Q2.2
        System.out.println("longestCommonSubStringNoConsecutive: "+longestCommonSubsequence("student", "swdexxxxxxxxxxxxxn"));
        //test Q4
        int[] arr3 = new int[]{10,30,5,8};
        System.out.println("the maximum scores: " + optimalGame(arr3));
    }
}
