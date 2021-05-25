package src.DynamicProgramming.DynamicProgrammingI;

import java.util.Set;

/**
 * @Author yu
 * @URL https://docs.google.com/document/d/1TGHpRuM_VxqHyZ1EYCk4XuWb4IpzH9HRWVzDWa53KGE/edit#heading=h.e6ear8p60pio
 * DP 从小问题来解决大问题，记录sub-solution
 * 由 size(<n)的sub-solution(s) -> size(n)的solution(s)
 * 由 base case 以及 induction rule 组成
 * */

public class dynamicProgrammingI {

    /**
     * finbon
     * assumption, k <= 0 or k overflowed
     * @param k
     * @return
     */
    public static long finbN(int k) {
        if(k <= 0) {
            return 0;
        }
        long[] res = new long[k+1];
        res[0] = 0;
        res[1] = 1;
        for(int i = 2; i<= k; i++) {
            res[i] = res[i-1] + res[i-2];
        }
        return res[k];
    }
    /**
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
    //TODO
    /**
     * longest sub-sequence
     * e.g: {7,2,3,1,5,8,9,6} -> longest sub-sequence is {2,3,5,8,9} and output is length of sub-array 5
     * @param array
     * @return integer
     * @Algorithm
     */
    public static int longestSubSequence(int[] array) {
        int [] dp = new int[array.length];
        dp[0] = 1;
        int res = 0;
        for (int i = 1; i < array.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (array[j] < array[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * Given a rope with integer-length n, how to cut the rope into m integer-length
     *  in order to get maximal product
     * @param n
     * @return int
     * @Algorithm time = O(n^2) space= O(n) where n is length of array;
     */
    public static int maximalProductOfRope(int n) {
        int[] M = new int[n+1];
        M[0] = 0;
        M[1] = 0;
        for(int i = 2; i <= n; i++) {
            int curMax = 0;
            for(int j = 1; j < i; j++) {
                int product = Math.max(j, M[j]) * (i-j);
                curMax = Math.max(curMax, product);
            }
            M[i] = curMax;
        }
        return M[n];
    }

    /**
     * 切字典
     * @param input
     * @param dict
     * @return
     * @Algorithm time = O(n^2) space= O(n) where n is length of input;
     */
    public static boolean canBreak(String input, Set<String> dict) {
        boolean[] M = new boolean[input.length()+1];
        M[0] = true;
        for (int i = 1; i <= input.length();i ++) {// liner scan
            for (int j = 0; j < i; j++) { // j是切的位置
                // M[j] represents left side letters, input.substring(j,i) represents right side letters
                if (M[j] && dict.contains(input.substring(j,i))){ // look back
                    M[i] = true;
                    break; //????
                }
            }
        }
        return M[input.length()];
    }

    //TODO cut palindrome
    /**
     * given a string, find minimum cut palindrome
     * leetcode:https://leetcode.com/problems/palindrome-partitioning-ii/submissions/
     * */
    public static int minimumCutPalindrome(String word) {
        char[] c = word.toCharArray();
        int n = c.length;
        int[] dp = new int[n];
        boolean[][] isPalindrome = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = 0; j <= i; j++) {
                //if i == j, s.substring(j, i + 1) is palindrome
                // if (c[i] == c[j]) && (i == j+1) , s.subString(j, i+1) is Palindrome
                // if (c[i] == c[j]) && (s.subString(j+1, i) is Palindrome),
                // then s.subString(j, i+1) is Palindrome, dp[i] = dp[j-1]+1
                if (c[j] == c[i] && (j + 1 > i -1 || isPalindrome[j+1][i-1])) {
                    isPalindrome[j][i] = true;
                    min = j == 0 ? 0 : Math.min(dp[i], dp[j-1] + 1);
                }
            }
            dp[i] = min;
        }
        return dp[n-1];
    }



    /**
     * given an array, it starts to jump from beginning to end in terms of value of array
     * to see if it can make it.
     * @Algorithm time = O(n^2) space= O(n) where n is length of array;
     * */
    public static boolean jumpGameI(int[] arr) {
        boolean[] M = new boolean[arr.length];
        M[arr.length-1] = true;
        for (int i = arr.length-2; i >= 0; i--) { // liner scan every element in array
            M[i] = false;
            for (int j = 1 ; j <= arr[i] && j + i < arr.length; j++) { // jump at least one step to arr[i] step
                if (M[j + i]) { // j + i 是跳的步数加上current index 不能超过array
                    M[i] = true;
                    break;
                }
            }
        }
        return M[0];
    }



    public static void main(String[] args) {
        int[] arr = new int[]{3,2,3,4,5,8,9,1};
        System.out.println("The longest increasing sub-sequence is "+longestSubSequence(arr));
        String word = "ababbbabbababa";
        System.out.println("The minimum cut having palindrome is " + minimumCutPalindrome(word));
        int[] arr2 = new int[] {2,3,1,1,4};
        int[] arr3 = new int[] {3,2,1,0,4};
        System.out.println("The jump game can make: " + jumpGameI(arr3));
    }
}
