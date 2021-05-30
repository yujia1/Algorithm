package src.DynamicProgramming.DynamicProgrammingII;

public class dynamicsProgrammingIIMain {
    //TODO
    // 第一遍C
    // 第二遍C
    // 第三遍


    /**
     * Given two strings chatacters, determine the minimum numbers of Replace, Delete, and Insert operations needed to transform one to another
     * @param word1
     * @param word2
     * @return integer
     * method 1 recursion: time complexity O(3^(m+n)) where m and n are lengths of words, respectively.
     * method 2 2D DP: time complexity O( m * n) and space complexity O(m * n)
     */
    public static int editDistanceRecursion(String word1, String word2) {
        if (word1.isEmpty()) return word2.length();
        if (word2.isEmpty()) return word1.length();
        if (word1.charAt(0) == word2.charAt(0)) {
            int nothing = editDistanceRecursion(word1.substring(1), word2.substring(1));
            return nothing;
        }
        int replace = 1 + editDistanceRecursion(word1.substring(1), word2.substring(1));
        int delete = 1 + editDistanceRecursion(word1.substring(1), word2);
        int insert = 1 + editDistanceRecursion(word1, word2.substring(1));
        return Math.min(Math.min(replace, delete), insert);
    }
    public static int editDistance(String word1, String word2) {
        int row = word1.length() + 1;
        int col = word2.length() + 1;
        // distance[i][j] represents the minimum number of actions to transform first i letters of word1 to the first j letters of word2
        int[][] distance = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0) {
                    distance[i][j] = j;
                } else if (j == 0) {
                    distance[i][j] = i;
                } else if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    distance[i][j] = distance[i-1][j-1];
                } else {
                    distance[i][j] = Math.min(distance[i-1][j] + 1, distance[i][j-1]+1); // delete & insert
                    distance[i][j] = Math.min(distance[i-1][j-1]+1, distance[i][j]); // replace
                }
            }
        }
        return distance[row-1][col-1];
    }
    /**
     *  the edge length of the largest square of 1's in a given binary matrix.
     *  0 0 0 0 0
     *  1 1 1 1 1
     *  1 1 1 1 1
     *  1 1 1 1 0
     *  1 1 1 0 0
     * @param matrix
     * @return integer
     * time complexity = O (n^2) space complexity O(n^2)
     */
    public static int maximumSquare(int[][] matrix) {
        int N = matrix.length;
        if (N == 0) {
            return 0;
        }
        int result = 0;
        // largest[i][j] represents the max size of square with (i,j) as the bottom right corner
        int[][] largest = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0 || j == 0) {
                    largest[i][j] = matrix[i][j] == 1 ? 1 : 0;
                } else if (matrix[i][j] == 1) {
                    largest[i][j] = Math.min(largest[i][j-1] + 1, largest[i-1][j] + 1);
                    largest[i][j] = Math.min(largest[i][j], largest[i-1][j-1] + 1 );
                }
                result = Math.max(result,largest[i][j]);
            }
        }
        return result;
    }

    /**
     * largest sum subArray
     * time complexity O（n）
     * space complexity O（n）
     * @param array
     * @return
     */
    public static int LargestSumSubArray(int[] array) {
        int n = array.length;
        int[] M = new int[n];
        M[0] = array[0];
        int globalMax = array[0];
        for (int i = 1; i < n; i++) {
            M[i] = Math.max(M[i-1]+ array[i], array[i]);// 防止前面一个是负数
            globalMax = Math.max(globalMax, M[i]);
        }
        return globalMax;
        // 0    1   2   3   4   5   6   i
        // 1    2   4   -1  -2  10  -1  arr
        // 0    3   7   6   4   14  13
    }

    /**
     *  https://leetcode.com/problems/jump-game-ii/
     * */
    public static int minJump(int[] nums) {
        int jumps = 0, curEnd = 0, curFarthest = 0;
        for(int i = 0; i < nums.length -1; i++) {
            curFarthest = Math.max(curFarthest, i + nums[i]);
            if(i == curEnd) {
                jumps++;
                curEnd = curFarthest;
            }
        }
        return jumps;
    }

    public static void main(String[] args) {
        // Q1 test
        String word1 = "asdf";
        String word2 = "sghj";
        System.out.println("Minimum operation is " + editDistanceRecursion(word1, word2));
        int[] arr2 = new int[] {2,3,1,4};
        System.out.println("Minimum step is " + minJump(arr2));
    }
}
