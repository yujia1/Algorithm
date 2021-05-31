package Recursion.recursionII;

import binaryTree.TreeNode;

import java.util.*;
public class recursionIIMain {
    // 第一遍训练法
    // 第二遍laicode，语音
    // 第三遍下周默写
    /** Q1
     * spiral put matrix to array
     * @param matrix
     * @return
     */
    public static List<Integer> spiralI(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        recursiveTraverse(matrix, 0, matrix.length, list);
        return list;
    }
    private static void recursiveTraverse(int[][] matrix, int offset, int size, List<Integer> res) {
        if (size == 0) {
            return;
        }
        if (size == 1) {
            res.add(matrix[offset][offset]);
            return;
        }
        for (int i =0; i< size -1; i++) {
            res.add(matrix[offset][offset+i]); // 上
        }
        for (int i = 0; i < size -1; i++) {
            res.add(matrix[offset+i][offset + size - 1]); // 右
        }
        for (int i = size - 1; i >=1; i--) {
            res.add(matrix[offset+size-1][offset + i]);//下
        }
        for (int i = size -1; i>=1; i--) {
            res.add(matrix[offset+i][offset]); //左
        }
        recursiveTraverse(matrix,offset+1, size -2, res);
    }
    /**
     * advanced spiral
     * @param matrix
     * @return
     */
    public static List<Integer> spiralII(int[][] matrix) {
        // Write your solution here
        List<Integer> list = new ArrayList<>();
        int m = matrix.length;
        if (m == 0) {
            return list;
        }
        int n = matrix[0].length;
        int left = 0;
        int right = n - 1;
        int up = 0;
        int down = m -1;
        while (left < right && up < down) {
            for (int i = left; i <= right; i++) { //每排row 的全部元素
                list.add(matrix[up][i]);
            }
            for (int i = up + 1; i <= down -1; i++) {
                list.add(matrix[i][right]);
            }
            for (int i = right; i >= left; i--) {
                list.add(matrix[down][i]);
            }
            for (int i = down -1; i >= up+1; i--) {
                list.add(matrix[i][left]);
            }
            left++;
            right--;
            up++;
            down--;
        }
        // check left
        if (left > right || up > down) {
            return list;
        }
        // left out one column
        if (left == right) {
            for (int i = up; i <= down; i++) {
                list.add(matrix[i][left]);
            }
        } else {
            // one row left
            for (int i = left; i<= right; i++) {
                list.add(matrix[up][i]);
            }
        }
        return list;

    }


    /** Q2
     * N 皇后
     * time complexity -> O(n!)
     * @param n
     * @return
     */
    public static List<List<Integer>> nqueens(int n) {
        // Write your solution here
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        helper(n, cur, res);
        return res;
    }
    private static void helper(int n,List<Integer> cur, List<List<Integer>> res) {
        if (cur.size() == n) {
            res.add(new ArrayList<Integer>(cur));
            return;
        }
        for (int i = 0; i < n; i++) { // i represent column
            if (valid(cur, i)) {
                cur.add(i); // cur index represent its row, element represent its column
                helper(n,cur,res);
                cur.remove(cur.size() - 1);
            }
        }
    }
    private static boolean valid(List<Integer> cur, int column) {
        int row = cur.size();
        for (int i = 0; i < row; i++) {
            if (cur.get(i) == column || Math.abs(cur.get(i) - column) == row - i) { //同一column || 对角线
                return false;
            }
        }
        return true;
    }

    public static boolean match(String input, String pattern) {
        // Write your solution here
        return match(input, pattern, 0, 0);
    }
    /** Q3
     *
     * @param s
     * @param t
     * @param si
     * @param ti
     * @return
     */
    private static boolean match(String s, String t, int si, int ti) {
        if (si == s.length() && ti == t.length()) {
            return true;
        }
        if (si >= s.length() || ti >= t.length()) {
            return false;
        }
        //case 1 if the current char int t is not a digit
        if (t.charAt(ti) <'0' || t.charAt(ti)>'9') {
            if (s.charAt(si) == t.charAt(ti)) {
                return match(s, t, si+1, ti+1);
            }
            return false;
        }
        //case 2 if the current char in t is a digit
        int count = 0;
        while (ti < t.length() && t.charAt(ti) >='0' && t.charAt(ti) <='9') {
            count = count * 10 + (t.charAt(ti) - '0');
            ti++;
        }
        return match(s, t, si+count, ti);
    }

    public static void numNodesLeft(TreeNodeLeft root) {
        // Write your solution here
        numNodes(root);
    }
    /**Q4
     * find left number subtree
     * @param root
     * @return
     */
    private static int numNodes(TreeNodeLeft root) {
        if (root == null) {
            return 0;
        }
        int leftNum = numNodes(root.left);
        int rightNum = numNodes(root.right);
        root.numNodesLeft = leftNum; //往上挂
        return leftNum + rightNum + 1;
    }

    /**Q5
     * low common ancestor
     * @param root
     * @param a
     * @param b
     * @return
     */
    static TreeNode LCA(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null || root == a || root == b) {
            return root;
        }
        TreeNode left = LCA(root.left, a, b);
        TreeNode right = LCA(root.right, a, b);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }

    /**Q6
     * reverse tree upside down
     * @param root
     * @return
     */
    public static TreeNode reverse(TreeNode root) {
        // Write your solution here
        if(root == null || root.left == null) {
            return root;
        }
        TreeNode newHead = reverse(root.left);
        root.left.left = root;
        root.left.right = root.right;
        root.left = null;
        root.right = null;
        return newHead;
    }

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(12);
//
//        TreeNode l1Left = new TreeNode(10);
//        TreeNode l1Right = new TreeNode(16);
//
//        TreeNode l2Left = new TreeNode(5);
//        TreeNode l2Right = new TreeNode(8);
//
//        TreeNode l3Left = new TreeNode(2);
//        TreeNode l3Right = new TreeNode(7);
//
//        TreeNode l4Right = new TreeNode(9);
//        root.left = l1Left;
//        root.right = l1Right;
//        l1Left.left = l2Left;
//        l1Left.right = l2Right;
//        l2Left.left = l3Left;
//        l2Left.right = l3Right;
//        l3Right.right = l4Right;
//
//        TreeNode res = LCA(root, l2Left, l1Right);
//        System.out.println(res.value);
        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        System.out.println(a.toString());
    }


}
