package GraphSeachAlgorithm.GraphSearchAlgorithmIIDFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LeetCodeDFS {
    /**
     * Q1.1 subset problem:https://leetcode.com/problems/subsets
     * without dups
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        List<Integer> curr = new ArrayList<>();
        dfsSubset(nums, 0, curr, res);
        return res;
    }
    private void dfsSubset(int[] nums, int index, List<Integer> curr, List<List<Integer>> res) {
        if (index > nums.length) {
            return;
        }
        res.add(new ArrayList<>(curr));
        for (int i = index; i <nums.length; i++) {
            curr.add(nums[i]);
            dfsSubset(nums, i + 1, curr, res); // i+1 is key, to narrow down next element of nums[i]
            curr.remove(curr.size() - 1);
        }
    }
    /**
     * Q1.2 subset problem: https://leetcode.com/problems/subsets-ii/
     * with dups
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        List<Integer> cur = new ArrayList<>();
        dfsSubsetsWithDup(nums, res, cur, 0);
        return res;
    }
    private void dfsSubsetsWithDup(int[] nums, List<List<Integer>> res, List<Integer> cur, int index) {
        if (index > nums.length) {
            return;
        }
        res.add(new ArrayList<>(cur));
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i-1]) continue;
            cur.add(nums[i]);
            dfsSubsetsWithDup(nums, res, cur, i + 1);
            cur.remove(cur.size() - 1);
        }
    }

    /**
     * Q2.1: find all possible permutaions, given an array nums that are no duplicate elements
     * https://leetcode.com/problems/permutations
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        dfsPermute(nums, res, new ArrayList<>());
        return res;
    }
    private void dfsPermute(int[] nums, List<List<Integer>> res, List<Integer> cur) {
        if (cur.size() == nums.length) {
            //System.out.println("return last level");
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //System.out.println("current index: " + i);
            if (!cur.contains(nums[i])) { //这个条件其实是砍掉了树枝
                //System.out.println("add value: " + nums[i]);
                cur.add(nums[i]);
                dfsPermute(nums, res, cur);
                //System.out.println("remove value " + cur.get(cur.size() -1));
                cur.remove(cur.size() - 1);
            }
        }
    }
    /**
     * Q2.2:find all possible permutaions, given an array nums that are with duplicate elements
     * https://leetcode.com/problems/permutations-ii/
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        boolean[] m = new boolean[nums.length];
        dfsPermuteUnique(nums, res, new ArrayList<>(), m);
        return res;
    }
    public void dfsPermuteUnique(int[] nums, List<List<Integer>> res, List<Integer> cur, boolean[] m) {
        if (cur.size() == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (m[i] || i > 0 && nums[i] == nums[i-1] && !m[i-1]) continue;
            m[i] = true;
            cur.add(nums[i]);
            dfsPermuteUnique(nums, res, cur, m);
            m[i] = false;
            cur.remove(cur.size() - 1);
        }
    }
    //TODO
    // using swap way to do it
    //https://leetcode.com/problems/permutations-ii/discuss/932886/java-0-ms-%3A-100-faster-%3A-using-swap


    /**
     * Q3 letter combinations
     * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
     * @param digits
     * @return
     */
    final String[] mapping = new String[]{"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        char[] cur = new char[digits.length()];
        dfsLetterComb(digits,0,cur, res);
        return res;
    }
    private void dfsLetterComb(String digits, int index, char[] cur, List<String> res) {
        if (index == digits.length()) {
            res.add(new String(cur));
            return;
        }
        String combo = mapping[digits.charAt(index) - '0'];
        for (int i = 0; i < combo.length(); i++) {
            cur[index] = combo.charAt(i);
            dfsLetterComb(digits, index+1, cur, res);
        }
    }

    public static void main(String[] args) {
        LeetCodeDFS test = new LeetCodeDFS();
        int[] nums = new int[] {1, 2, 3};
        //Q1.1 test or Q1.2
//        List<List<Integer>> res1 = test.subsetsWithDup(nums);
//        for (List<Integer> list: res1) {
//            for (int num: list) {
//                System.out.print( num + " ");
//            }
//            System.out.println();
//        }
        //Q2.1 test
        List<List<Integer>> res2 = test.permute(nums);
        for (List<Integer> list: res2) {
            for (int num: list) {
                System.out.print( num + " ");
            }
            System.out.println();
        }
    }
}
