package GraphSeachAlgorithm.GraphSearchAlgorithmIIDFS;

import java.util.*;

public class DFSIIMain {
    //TODO
    // 第一遍
    // Q1 subset
    // Q1.3 suubset only contain k element
    public static List<String> subsetKth(String set ,int k) {
        List<String> result = new ArrayList<>();
        if (set == null) {
            return result;
        }
        char[] arraySet = set.toCharArray();
        // record the current subset
        StringBuilder sb = new StringBuilder();
        helper3(arraySet, sb,0,result, k);
        return result;
    }
    private static void helper3(char[] set, StringBuilder sb, int index, List<String> result, int k) {
        if (sb.length() == k) {
            result.add(sb.toString());
            return;//要return 回上一个breaking point
        }
        if (index == set.length) {
            return;
        }
        // add set[index] to StirngBuilder
        sb.append(set[index]); //直上直下吃进去
        helper3(set, sb, index + 1, result, k);
        sb.deleteCharAt(sb.length() - 1); // 删掉一个尾巴，直下直上吐出来
        // do not add set[index] to StringBuilder
        helper3(set,sb,index + 1, result, k);
    }
    //TODO
    // sub set- problem with duplicate
    // Q1.4 all subset
    public static List<String> subsetDup(String set) {
        List<String> list = new ArrayList<>();
        char[] input = set.toCharArray();
        Arrays.sort(input);
        StringBuilder sb = new StringBuilder();
        helper4(input,0,sb,list);
        return list;
    }
    //method 1
//    private static void helper4(char[] set, int index, StringBuilder sb, List<String> list) {
//        if (index == set.length) {
//            list.add(sb.toString());
//            return;
//        }
//        sb.append(set[index]);
//        helper4(set, index + 1, sb, list);
//        sb.deleteCharAt(sb.length() - 1);
//        while (index < set.length-1 && set[index] == set[index+1]) {
//            index++;
//        }
//        helper4(set, index + 1, sb, list);
//    }
    //method 2
    private static void helper4(char[] set, int index, StringBuilder sb, List<String> list) {
        if (index > set.length) {
            return;
        }
        list.add(sb.toString());
        for (int i = index; i < set.length; i++) {
            if (i > index && set[i] == set[i -1]) {
                continue;
            }
            sb.append(set[i]);
            helper4(set, i + 1, sb, list);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    //TODO
    // Q1.5 given an array of size n with duplicate, print all possible combination of k elements
    public static List<String> subsetDupKth(String set, int k) {
        List<String> res = new ArrayList<>();
        char[] arraySet = set.toCharArray();
        StringBuilder sb = new StringBuilder();
        helper5(arraySet, sb, 0, res, k);
        return res;
    }
    private static void helper5(char[] set, StringBuilder sb, int index, List<String> res, int k) {
        if (sb.length() == k) {
            res.add(sb.toString());
            return;
        }
        if (index == set.length) {
            return;
        }
        sb.append(set[index]);
        helper5(set, sb, index + 1, res, k);
        sb.deleteCharAt(sb.length() - 1);
        while (index < set.length -1 && set[index] == set[index+1]) {
            index++;
        }
        helper5(set, sb, index + 1, res, k);
    }



    //TODO
    // Q2 parenthesis problem
    // Q2.1 {}{} {}
    public static List<String> validParenthesis(int k) {

        List<String> result = new ArrayList<>();
        StringBuilder solutionPrefix = new StringBuilder();
        helper6(0, 0, k, solutionPrefix,result);
        return result;
    }
    private static void helper6(int left, int right, int k, StringBuilder solution,List<String> result) {
        if (left == k && right == k) {
            result.add(solution.toString());
            return;
        }
        if (left < k) {
            solution.append('(');
            helper6(left + 1, right, k, solution, result);
            solution.deleteCharAt(solution.length() - 1);
        }
        if (right < left) {
            solution.append(')');
            helper6(left, right + 1, k, solution, result);
            solution.deleteCharAt(solution.length()-1);
        }
    }

    /**
     * Q2.2 n pairs of {}, k pairs of [], t pairs of () can be valid permutation
     * @param n
     * @param k
     * @param t
     * @return
     */
    public static List<String> permutationParenthesis(int n, int k, int t) {
        int[] remain = new int[] {n, n, k, k, t, t};
        int targetLen = 2 * (n + k + t);
        StringBuilder cur = new StringBuilder();
        Deque<Character> stack = new LinkedList<>();
        List<String> res = new ArrayList<>();
        helper7(cur, stack, remain, targetLen , res);
        return res;
    }
    private static final char[] PS = new char[] {'{', '}', '[', ']', '(', ')'};
    private static void helper7(StringBuilder cur, Deque<Character> stack, int[] remain, int targetLen, List<String> res) {
        if (cur.length() == targetLen) {
            res.add(cur.toString());
            return;
        }
        for (int i = 0; i < remain.length; i++) {
            if (i % 2 == 0) { // 左边 { [ (
                if (remain[i] > 0) {
                    cur.append(PS[i]);
                    stack.offerFirst(PS[i]); // stack 持续添加左边括号
                    remain[i]--;
                    helper7(cur, stack, remain, targetLen, res);
                    cur.deleteCharAt(cur.length() - 1);
                    stack.pollFirst(); // stack poll out 最边上的
                    remain[i]++;
                }
            } else { // 右边 } ] )
                if (!stack.isEmpty() && stack.peekFirst() == PS[i-1]) { //当stack
                    cur.append(PS[i]);
                    stack.pollFirst(); // 匹配了一个就扔出去一个
                    remain[i]--;
                    helper7(cur, stack, remain, targetLen, res);
                    cur.deleteCharAt(cur.length() - 1);
                    stack.offerFirst(PS[i-1]); // stack当前尾巴上的是最新的left } ] )
                    remain[i]++;
                }
            }
        }
    }
    //TODO
    // Q2.3 {} > [] > ()
    // solution:
    // add left: whenever add left parenthesis, check priority of current parenthesis against the stack.peek
    // add right: same in helper7

    // Q3.1 print out 99 cent
    /**
     * Q3.2 print out all valid combination of factors that form an integer
     * method 1
     * @param n
     * @return
     */
    public static List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        getFactorsHelper(n,cur, res);
        return res;
    }
    private static void getFactorsHelper(int n, List<Integer> cur, List<List<Integer>> res) {
        if(n == 1 && cur.size() > 1) {
            res.add(new ArrayList<>(cur));
            return;
        }
        int minFactor = cur.size() == 0 ? 2 : cur.get(cur.size() - 1);
        for(int i = minFactor; i <=n; i++) {
            if(n % i == 0) {
                cur.add(i);
                getFactorsHelper(n / i, cur, res);
                cur.remove(cur.size() -1);
            }
        }
    }


    // method2
    public static List<List<Integer>> factorCombination(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n < 1) {
            return res;
        }
        List<Integer> cur = new ArrayList<>();
        List<Integer> factors = factorFind(n);
        helper8(n, factors, 0, cur, res);
        return res;
    }

    private static void helper8(int target,List<Integer> factors, int index, List<Integer> cur, List<List<Integer>> res) {
        if (index == factors.size()) {
            if (target == 1) {
                res.add(new ArrayList<>(cur));
            }
            return;
        }
        //TODO ? why
        helper8(target, factors, index + 1, cur, res);
        int factor = factors.get(index);
        int size = cur.size();
        while (target % factor == 0) {
            cur.add(factor);
            target = target / factor;
            helper8(target, factors, index + 1, cur, res);
        }
        cur.subList(size, cur.size()).clear();
    }

    private static List<Integer> factorFind(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                res.add(i);
            }
        }
        return res;
    }

    //method3
    private static void helper8Same(List<List<Integer>> combs, List<Integer> comb, int factor, int target) {
        while (factor * factor <= target) {
            if (target % factor == 0) {
                comb.add(factor);
                comb.add(target / factor);
                combs.add(new ArrayList<>(comb));
                comb.remove(comb.size() -1);
                helper8Same(combs, comb, factor, target / factor);
                comb.remove(comb.size() - 1);
            }
            factor +=1;
        }
    }

    //TODO Q4.1 print out all permutation of a string without duplication
    // Q4.2 print out all permutation of a string with duplication
    public static List<String> permutationsWithDup(String input) {
        // Write your solution here
        if(input == null) {
            return new ArrayList<String>();
        }
        List<String> list = new ArrayList<>();
        char[] arr = input.toCharArray();
        helper9(arr, 0, list);
        return list;
    }
    private static void helper9(char[] arr, int index, List<String> list) {
        if(index == arr.length) {
            list.add(new String(arr));
            return;
        }
        HashSet<Character> used = new HashSet<>();
        for(int i = index; i < arr.length; i++) {
            if(used.add(arr[i])) {
                swap(arr, i, index);
                helper9(arr, index + 1, list);
                swap(arr, i, index);
            }
        }
    }
    private static void swap(char[] set, int i ,int j) {
        char temp = set[i];
        set[i] = set[j];
        set[j] = temp;
    }
    //TODO Q4.3 print out all permutation of a string and its subset without duplication
    public static List<String> subsetPermutation(String input) {
        char[] set = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        List<String> res = new ArrayList<>();
       // helper10(set, 0, sb, res);
        return res;
    }
//    private static void helper10(char[] set, int index, StringBuilder sb, List<String> res) {
//        if (index == set.length) {
//            res.add(sb.toString());
//            return;
//        }
//
//    }
    //TODO Q4.4 possible shuffle {3,1,2,1,3,2} when n = 3; 数字间隔
    // 需要summary 这些DFS的题目

    public static void main(String[] args) {
        //TODO Q1.3 test
//       String str = "abcd";
//       List<String> res = subsetKth(str, 2);
//       for (String st: res) {
//           System.out.println(st);
//       }
        //TODO Q1.4 test

        String str = "abb";
        List<String> res = subsetDup(str);
        for (String st: res) {
            System.out.println(st);
        }
        //TODO Q1.5 test
//        String str = "12223";
//        List<String> res = subsetDupKth(str,3);
//        for (String st:res) {
//            System.out.println(st);
//        }
        //TODO Q
//        List<String> res = permutationParenthesis(3, 2, 1);
//        for (String st: res) {
//            System.out.println(st);
//        }
        //TODO Q3.2 test
//        List<List<Integer>> res = factorCombination(12);
//        System.out.println(res.toString());
//        int[] arr = new int[]{1,1,2,2};
//        List<List<Integer>> res1 = allPairs(arr);
//        for (List<Integer> lis: res1) {
//            System.out.println(lis);
//        }

    }
}
