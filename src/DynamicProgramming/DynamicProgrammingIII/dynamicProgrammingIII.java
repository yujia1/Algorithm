package DynamicProgramming.DynamicProgrammingIII;
import java.util.*;
public class dynamicProgrammingIII {
    //TODO
    // 第一遍 C
    // 第二遍
    /**
     * Q0
     * @param array
     * @return
     */
    // //prefix sum
    static int largestSumSubarray(int[] array) {
        int[] M = new int[array.length];
        // M represents maximum
        M[0] = array[0];
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            M[i] = Math.max(M[i-1] + array[i], array[i]);
            max = Math.max(M[i], max);
        }
        return max;
    }
    //TODO
    // prefix sum
    public static int[] largestSumWithRange(int[] array) {
        int[] M = new int[array.length];
        M[0] = array[0];
        int max = array[0];
        int currentLeft = 0;
        int globalLeft = 0;
        int globalRight = 0;

        for (int i = 1; i < array.length; i++) {
            if (M[i-1] < 0) {
                currentLeft = i ;
                M[i] = array[i];
            } else {
                M[i] = M[i-1] + array[i];
            }
            if (M[i] > max) {
                globalLeft = currentLeft;
                globalRight = i;
                max = Math.max(max, M[i]);
            }
        }
        return new int[] {globalLeft, globalRight};
    }

    /** Q1
     * find maximum length of array contiguous 1s
     * @param array
     * @return
     */
    public static int longestSubArray(int[] array) {
        if (array == null || array.length < 1) {
            return 0;
        }
        int[] M = new int[array.length];
        M[0] = array[0];
        int max = array[0];
        for (int i =1; i < array.length; i++) {
            if (array[i]==1) {
                M[i] = M[i-1] + 1;
            } else {
                M[i] = 0;
            }
            max = Math.max(max, M[i]);
        }
        return max;
    }

    /**
     * Q2
     * @param matrix
     * @return
     * time complexity: O(n^2)
     */
    public static int longestCrossOne(int[][] matrix) {
        int N = matrix.length;
        if (N == 0) {
            return 0;
        }
        int M = matrix[0].length;
        if (M==0) {
            return 0;
        }
        int[][] leftToRight = leftRight(matrix, N, M);
        int[][] rightToLeft = rightLeft(matrix, N, M);
        int[][] upToDown = upDown(matrix, N, M);
        int[][] downToUp = downUp(matrix, N, M);
        return mergeMatrix(leftToRight, rightToLeft, upToDown, downToUp, N, M);
    }
    private static int[][] leftRight(int[][] matrix, int N, int M) {
        int[][] M1 = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (j == 0) {
                    M1[i][j] = matrix[i][0];
                } else if(matrix[i][j] == 1) {
                    M1[i][j] = M1[i][j-1] + matrix[i][j];
                } else {
                    M1[i][j] = matrix[i][j];
                }
            }
        }
        return M1;
    }
    private static int[][] rightLeft(int[][] matrix, int N, int M) {
        int[][] M2 = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = N-1; j >=0; j--) {
                if (j == N-1) {
                    M2[i][j] = matrix[i][j];
                }else if(matrix[i][j] == 1) {
                    M2[i][j] = M2[i][j+1] + matrix[i][j];
                } else {
                    M2[i][j] = matrix[i][j];
                }
            }
        }
        return M2;
    }
    private static int[][] upDown(int[][] matrix, int N, int M) {
        int[][] M3 = new int[N][M];

        for (int j = 0; j < M; j++) {
            for (int i = 0; i < N; i++) {
                if (i == 0) {
                    M3[i][j] = matrix[i][j];
                } else if (matrix[i][j] == 1) {
                    M3[i][j] = M3[i-1][j] + matrix[i][j];
                } else {
                    M3[i][j] = matrix[i][j];
                }
            }
        }
        return M3;
    }
    private static int[][] downUp(int[][] matrix, int N, int M) {
        int[][] M3 = new int[N][M];

        for (int j = 0; j < M; j++) {
            for (int i = N-1; i >= 0; i--) {
                if (i == N-1) {
                    M3[i][j] = matrix[i][j];
                } else if (matrix[i][j] == 1) {
                    M3[i][j] = M3[i+1][j] + matrix[i][j];
                } else {
                    M3[i][j] = matrix[i][j];
                }
            }
        }
        return M3;
    }
    private static int mergeMatrix(int[][] left, int[][] right, int[][] up, int[][] down, int N, int M) {
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int leftUp = Math.min(left[i][j], up[i][j]);
                int rightDown = Math.min(right[i][j], down[i][j]);
                int minMatrix = Math.min(leftUp, rightDown);
                res = Math.max(res, minMatrix);
            }
        }
        return res;
    }

    /**
     * Q3
     * @param matrix
     * @return
     * time complexity： O( n^3)
     */
    public static int largestSquare(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int res = 0;
        int M = matrix.length;
        int N = matrix[0].length;
        int[][] left = new int[M+1][N+1];
        int[][] up = new int[M+1][N+1];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == 1) {
                    //同时更新俩个M
                    left[i+1][j+1] = left[i+1][j] + 1; // left to right
                    up[i+1][j+1] = up[i][j+1] + 1; // up to down
                }
                //TODO ?????
                // min 实际上锁定的是往左和往上的最长距离
                for (int maxLength = Math.min(left[i+1][j+1], up[i+1][j+1]); maxLength >=1; maxLength--) {
                    // left >= max 是比较往右能不能匹配 上面条件锁定的往左和往右
                    // left[i+1- maxLength + 1][j+1] 是标示相同col不同row是否能满足 maxLength，same to up[i+1][j+1- maxLength + 1]
                    if (left[i+1- maxLength + 1][j+1] >= maxLength && up[i+1][j+1- maxLength + 1] >=maxLength) {
                        res = Math.max(res, maxLength);
                        break;
                    }
                }
            }
        }
        return res;
    }

    /** Q4
     * match maxium 火柴
     * @param matrix
     * @return
     */
    //TODO ?????
    public static int largestSquareMatch(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int res = 0;
        int M = matrix.length;
        int N = matrix[0].length;
        int[][] right = new int[M+1][N+1];
        int[][] down = new int[M+1][N+1];
        for (int i = M-1; i >=0; i--) {
            for (int j = N-1; j>=0; j--) {
                if (hasRight(matrix[i][j])) {
                    right[i][j] = right[i][j+1] + 1;
                }
                if (hasDown(matrix[i][j])) {
                    down[i][j] = right[i+1][j] + 1;
                }
                if (hasBoth(matrix[i][j])) {
                    //TODO ?????
                    for (int maxLength = Math.min(right[i][j], down[i][j]); maxLength >=1; maxLength--) {
                        if (right[i+maxLength][j] >= maxLength && down[i][j+maxLength] >= maxLength) {
                            res = Math.max(res, maxLength);
                            break;
                        }
                    }
                }
            }
        }
        return res;
    }
    private static boolean hasRight(int val) {

        return (val & 0b1) != 0;
    }
    private static boolean hasDown(int val) {
        return (val & 0b10) !=0;
    }
    private static boolean hasBoth(int val) {
        return val == 0b11;
    }
    //TODO? 多少个正方形： 三种切法 
    //prefix sum
    /**
     * find largest sum of submatrix
     * time complexity: O(n^3)
     * */

    public static int largestSubMatrixSum(int[][] matrix)  {
        int N = matrix.length;
        int M = matrix[0].length;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            int[] cur = new int[M];
            for (int j = i; j < N; j++) {
                add(cur, matrix[j]);

                res = Math.max(res, max(cur));
            }
        }
        return res;
    }
    private static void add(int[] cur, int[] add) {
        for (int i= 0; i < cur.length; i++) {
            cur[i] += add[i]; // col prefix-sum
        }
    }
    private static int max(int[] cur) {
        int res = cur[0];
        int temp = cur[0];
        for (int i = 1; i < cur.length; i++) {
            temp = Math.max(temp + cur[i], cur[i]);//row prefix-sum，但是这时候的cur[i]是被压扁以后的
            res = Math.max(res, temp);
        }
        return res;
    }

    /**
     * find largest sum of submatrix, not larger than k
     * */

    public static int maxSumSubmatrixNoLargerThanK(int[][] matrix, int k) {
        int n = matrix.length;
        int m = matrix[0].length;
        int res = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            int[] cur = new int[m];
            for(int j = i; j < n; j++) {
                // prefix-sum columns
                addColumns(cur, matrix[j]);
                // compare to k, and update res
                res = Math.max(res, maxSubarray(cur, k));
                if(res == k) {
                    return res;
                }
            }
        }
        return res;
    }

    private static void addColumns(int[] cur, int[] add) {
        for(int i = 0; i < cur.length; i++) {
            cur[i] += add[i];
        }
    }

    private static int maxSubarray(int[] a, int k) {
        int max = Integer.MIN_VALUE;
        int sumj = 0;
        TreeSet<Integer> ts = new TreeSet();
        ts.add(0);

        for(int i=0;i<a.length;i++){
            sumj += a[i];
            Integer gap = ts.ceiling(sumj - k);
            if(gap != null) max = Math.max(max, sumj - gap);
            ts.add(sumj);
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{5,-4,-3, 4}, {-3,-4,4,5}, {5,1,5,-4}};
        int res = maxSumSubmatrixNoLargerThanK(matrix, 10);
        System.out.println(res);

    }
}
