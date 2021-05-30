package GraphSeachAlgorithm.GraphSearchAlgorithmIDFS;

import java.util.*;
/**
 * Depth-first-search(DFS)
 * 1. how many levels in the recursion tree? what does it store on each level?
 * 2. how many different states should we try to put on each level?
 */
public class DFS {
    /**
     * q1
     * find all subset of a string
     *  time complexity: O(2^n) where n is string length
     *  space complexity: O(n) StringBuilder is 粉色路径
     * @param set
     * @return
     * Time: O (2^n *n), Space: O(n)
     */
    public static List<String> subset(String set) {
        List<String> result = new ArrayList<>();
        if (set == null) {
            return result;
        }
        char[] arraySet = set.toCharArray();
        // record the current subset
        StringBuilder sb = new StringBuilder();
        subsetHelper(arraySet, sb,0,result);
        return result;
    }
    private static void subsetHelper(char[] set, StringBuilder sb, int index, List<String> result) {
        if (index == set.length) {//要return 回上一个breaking point
            result.add(sb.toString());
            return;
        }
        sb.append(set[index]); // add set[index] to StringBuilder, 吃进去
        subsetHelper(set, sb, index+1, result); //不断recursively call
        sb.deleteCharAt(sb.length()-1); // 吐出来最后一层的东西
        subsetHelper(set, sb, index+1, result); // 保证当前index能满足return的条件。
    }
    /**
     * q2
     * ()()() find all valid permutation using parenthesis provided, given k pair parenthesis
     * time complexity O (2 ^ (2k) * k)
     *  space complexity O(k)
     * @param k
     * @return
     */
    public static List<String> validParenthesis(int k) {
        List<String> result = new ArrayList<>();
        StringBuilder solutionPrefix = new StringBuilder();
        validParenthesisHelper(0, 0, k, solutionPrefix,result);
        return result;
    }

    private static void validParenthesisHelper(int left, int right, int k, StringBuilder solution,List<String> result) {
        if (left == k && right == k) {
            result.add(solution.toString());
            return;
        }
        if (left < k) {
            solution.append('(');
            validParenthesisHelper(left + 1, right, k, solution, result);
            solution.deleteCharAt(solution.length() - 1);
        }
        if (right < left ) {
            solution.append(')');
            validParenthesisHelper(left, right+1, k, solution, result);
            solution.deleteCharAt(solution.length() - 1);
        }
    }

    //TODO review, write it own, 数学计算多少种情况
    /**
     * given a target value, and compute combination of different face value
     * TC: O(99 ^ 4) = target / min(coins) ^ coins.length
     * SC: O(4)
     * @param target
     * @param coins
     * @return
     */
    public static List<List<Integer>> coinsCombination(int target, int[] coins) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        coinsHelper(target, coins, 0 , cur, result);
        return result;
    }
    private static void coinsHelper(int target, int[] coins, int index, List<Integer> cur, List<List<Integer>> result) {
        if (index == coins.length - 1) {
            if (target % coins[index] == 0) { //只决定最后一个coins[index] 能否被剩余的target整除， 因为在for loop里其他面值的已经被加到cur里面了
                cur.add(target / coins[index]);
                result.add(new ArrayList<>(cur));
                cur.remove(cur.size()-1); //最后删除最后一个可能性，返回到上一层loop里上一个面值的另外一个情况
                return;
            }

        }
        //实际上length of coins绝对了 levels
        int max = target / coins[index]; //当前面值有多少种可能性
        for (int i = 0; i <= max; i++) { //实际上for loop是考虑当前level的 max中状态
            cur.add(i); //加入其中一种可能性， 实际上保存了当前level里面值数量
            coinsHelper(target - coins[index] * i, coins, index + 1, cur, result);
            cur.remove(cur.size()-1);
        }
    }
    //TODO swap 本质是什么
    /**
     * Given a string, find all its permutation
     * TC: O(n! * n)
     * SP: O(n)
     * @param set
     * @return
     */
    public static List<String> stringPermutation(String set) {
        List<String> result = new ArrayList<>();
        if (set == null) {
            return result;
        }
        char[] array = set.toCharArray();
        stringPermutationHelper(array, 0, result);
        return result;
    }

    private static void stringPermutationHelper(char[] array, int index, List<String> result) {
        if (index == array.length) {
            result.add(new String(array));
            return;
        }
        for (int i = index; i < array.length; i++) {
            swap(array, index, i);
            stringPermutationHelper(array, index + 1, result);
            swap(array, index, i);
        }
    }
    private static void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void hanoli(final String src, final String temp,final String dest,final int n){
        if (n ==1){

            System.out.println("move from "+ src + " to " + dest);
        }else{

            hanoli(src,dest,temp, n-1);
            System.out.println("move from " + src + " to " + dest);
            hanoli(temp,src,dest, n-1);
        }
    }

    public static void main(String[] args) {
        // subset
        List<String> res1 = subset("ABC");
        for (String str: res1) {
            System.out.println(str);
        }
        // valid parenthesis ()()
                List<String> list = validParenthesis(3);
        System.out.println(list.toString());
        // combinations of face value
        System.out.println(Arrays.deepToString(coinsCombination(5, new int[]{4,3,2,1}).toArray()));
        // string permutation
        List<String> res2 = stringPermutation("abc");
        for (String str: res2) {
            System.out.println(str);
        }

    }
}
