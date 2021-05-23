package SnowFlakeInteview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class onlineTest {
    /**
     * give an array and int k, then find all unique pairs in arrays to meet requirement int[] {a, b}, a + k = b or b + k = a
     * */
    private static HashMap<Integer, Integer> uniquePairsToEqualsToK(int[] array, int k) {
        Arrays.sort(array);
        HashMap<Integer, Integer> map = new HashMap<>();
        // find all pairs
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] + k == array[j] || array[j] + k == array[i]) {
                    map.put(array[i], array[j]); // filters dup pairs
                }
            }
        }
        return map;
    }
    /**
     *  1.
     * give N jobs where every job is represented by start time, finish time, and profit
     * find the maximum profit subset of jobs that not two jobs in the subset overlap
     * */
    static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int[][] jobs = new int[startTime.length][3];
        for (int i = 0; i < startTime.length; i++) {


            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a,b) -> a[1] - b[1]);
        int n =startTime.length;
        int[] dp = new int[n];
        dp[0] = jobs[0][2];
        for (int i = 1; i < n; i++) { // 类似于array里找最大的和
            int start = jobs[i][0]; //当前工作开始时间
            int compatible = -1;
            for(int j = i-1; j >=0; j--) {
                if (jobs[j][1] <= start) { // job[j][1] 前份工作结束时间 <= 当前工作开始时间比较, 不影响当前工作
                    compatible = dp[j]; //loop可以把之前所以工作都检查一遍
                    break;
                }
            }
            dp[i] += jobs[i][2] + ((compatible==-1) ? 0 : compatible); // 如果前后工作不冲突，在相加当前工作和前一份工作的最大利润
            dp[i] = Math.max(dp[i], dp[i-1]); // 比较当前利润和之前利润，保持dp[i]的最大利润
        }
        return dp[n-1];
    }

    /**
     * 2 和 job schedule 问题相关的 job sequencing problems
     * https://www.geeksforgeeks.org/job-sequencing-problem/
     * Given an array of jobs where every job has a deadline and associated profit if the job is finished before the deadline.
     * It is also given that every job takes a single unit of time, so the minimum possible deadline for any job is 1.
     * How to maximize total profit if only one job can be scheduled at a time.
     * */

    public static void main(String[] args) {
        // Q1 test
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] arr = new int[]{1,2,1,2};
        map = uniquePairsToEqualsToK(arr, 1);
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
        }
        //Q2
        int[] start = new int[] {1,2,3,3};
        int[] end = new int[] {3,4,5,6};
        int[] profit = new int[] {50,10,40,70};
        int maximumProfit = jobScheduling(start, end, profit);
        System.out.println("Answer is 120: " + maximumProfit);
    }
}
