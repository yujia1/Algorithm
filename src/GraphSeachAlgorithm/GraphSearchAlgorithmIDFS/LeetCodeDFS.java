package GraphSeachAlgorithm.GraphSearchAlgorithmIDFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class LeetCodeDFS {
    /**
     * Q1: find all possible permutaions, given an array nums that are no duplicate elements
     * https://leetcode.com/problems/permutations
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        helper(nums, 0, res);
        return res;
    }
    private void helper(int[] nums, int index, List<List<Integer>> res) {
        if (index == nums.length) {
            List<Integer> curr = helper(nums);
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            swap(nums, i, index);
            helper(nums, index+1, res);
            swap(nums, i, index);
        }
    }
    private List<Integer> helper(int[] nums) {
       List<Integer> curr = new ArrayList<>();
        for (int num : nums) {
            curr.add(num);
        }
        return curr;
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * Q2:find all possible permutaions, given an array nums that are with duplicate elements
     * https://leetcode.com/problems/permutations-ii/
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        boolean[] m = new boolean[nums.length];
        dfs(nums, res, new ArrayList<>(), m);
        return res;
    }
    public void dfs(int[] nums, List<List<Integer>> res, List<Integer> cur, boolean[] m) {
        if (cur.size() == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (m[i] || i > 0 && nums[i] == nums[i-1] && !m[i-1]) continue;
            m[i] = true;
            cur.add(nums[i]);
            dfs(nums, res, cur, m);
            m[i] = false;
            cur.remove(cur.size() - 1);
        }
    }
    //TODO
    // using swap way to do it
    //https://leetcode.com/problems/permutations-ii/discuss/932886/java-0-ms-%3A-100-faster-%3A-using-swap

    public static void main(String[] args) {
        LeetCodeDFS test = new LeetCodeDFS();
        //Q1 test
        int[] nums = new int[] {1, 2, 3};
        List<List<Integer>> res = test.permute(nums);
        for (List<Integer> list: res) {
            for (int num: list) {
                System.out.print( num + " ");
            }
            System.out.println();
        }
    }
}
