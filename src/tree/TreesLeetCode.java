package tree;

import binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class TreesLeetCode {
    /**
     * path sum
     */
    public List<List<Integer>> pathSumRootToLeave(TreeNode tree, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        pathSumRootToLeaveHelper(tree, sum, cur, res);
        return res;
    }
    private void pathSumRootToLeaveHelper(TreeNode root, int sum, List<Integer> cur, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        cur.add(root.value);
        if (root.left == null && root.right == null && root.value == sum) {
            res.add(new ArrayList<>(cur));
        }
        pathSumRootToLeaveHelper(root.left, sum - root.value, cur, res);
        pathSumRootToLeaveHelper(root.right, sum - root.value, cur, res);
        cur.remove(cur.size() - 1);
    }
    /**
     * Q2 sum root to leafs number
     * https://leetcode.com/problems/sum-root-to-leaf-numbers
     * @param root
     * @return
     * TC: O(N)
     * SC: O(H) where h is height of root
     */
    int rootToLeaf = 0;
    public int sumNumbers(TreeNode root) {
        preOrder(root, 0);
        return rootToLeaf;
    }
    private void preOrder(TreeNode root, int currNumber) {
        if(root != null) {
            currNumber = currNumber * 10 + root.value;

            if(root.left == null && root.right == null) {
                rootToLeaf += currNumber;
            }
            preOrder(root.left, currNumber);
            preOrder(root.right, currNumber);
        }
    }
    // most frequent subtree sum


    /** Q3
     * binary tree longest consecutive sequence
     * leetcode: https://leetcode.com/problems/binary-tree-longest-consecutive-sequence
      * @param root
     * @return
     */
    int res = 0;
    public int longestConsecutive(TreeNode root) {
        if(root == null) {
            return 0;
        }
        helper(root);
        return res;
    }
    private int helper(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int left = helper(root.left);
        int right = helper(root.right);
        int max = 1;
        if(root.left == null || root.left.value == root.value + 1) {
            max = Math.max(max, left + 1);
        }
        if(root.right== null || root.right.value == root.value + 1) {
            max = Math.max(max, right + 1);
        }
        res = Math.max(res, max);
        return max;
    }
    /**
     * binary tree maximum path sum
     */
    //
}
