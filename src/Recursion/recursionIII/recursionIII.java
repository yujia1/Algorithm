package Recursion.recursionIII;


import binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class recursionIII {
    /**
     * Q1.1
     * check if a tree is balance: left and right sub-tree have similar height. diff is 0 or 1
     * laioffer: https://app.laicode.io/app/problem/46
     * TC: O(n log n)
     * @param root
     * @return
     */
    public boolean isBalance(TreeNode root) {
        if (root == null) return true;
        int diff = getHeight(root.left) - getHeight(root.right);
        if (Math.abs(diff) > 1) {
            return false;
        }
        return isBalance(root.left) && isBalance(root.right);
    }
    private int getHeight(TreeNode root) {
        if (root == null) return 0;
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    /**
     * Q1.2
     * determine whether a binary tree is a balanced binary tree O(n) solution
     * @param root
     * way of thinking
     * 1. what do you expect from left child or right child?
     * >= 0 height; -1 child not balance
     * 2. what do you want to do in the current layer?
     * case 1: if lChild not balanced or rChild is not balance, then return -1;
     * case 2: both balanced, check height of left - height of right <=1
     * 3. what do you want to report to your parent
     * max(height(l), height(r)) + 1; or -1 if not balance
     */
    public boolean isBalance2(TreeNode root) {
        if (root == null) return true;
        return getHeight2(root) != -1;
    }
    private int getHeight2(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = getHeight2(root.left);
        int rightHeight = getHeight2(root.right);
        //current layer operation
        if (leftHeight == - 1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        //report to parent node
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Q1.3
     * Given a binary tree in which each node contains an integer number, find the maximum possible sum
     * from one leaf node to another leaf node
     * e.g     -15
     *       2      11
     *           6      14
     * maximum path: 6 + 11 + 14 = 31
     * laioffer: https://app.laicode.io/app/problem/138
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        int[] res = new int[]{Integer.MIN_VALUE};
        maxPathSumLeafToLeaf(root, res);
        return res[0];
    }
    private int maxPathSumLeafToLeaf(TreeNode root, int[] res) {
        if (root == null) return 0;
        int leftMax = maxPathSumLeafToLeaf(root.left, res);
        int rightMax = maxPathSumLeafToLeaf(root.right, res);

        int currSum = leftMax + rightMax + root.value;

        if (res[0] < currSum && (root.left != null && root.right != null)) {
            res[0] = currSum;
        }
        if (root.left == null) {
            return root.value + rightMax;
        } else if (root.right == null) {
            return root.value + leftMax;
        }
        return Math.max(leftMax, rightMax) + root.value;
    }

    /**
     * Q1.4
     * Given a binary tree in which each node contains an integer number, find the maximum possible sum
     * from any node to another  node
     * @param root
     * @return
     */
    public int maxPathSumNodeToNode(TreeNode root) {
        int[] res = new int[] {Integer.MIN_VALUE};
        helperMathPath(root, res);
        return res[0];
    }
    private int helperMathPath(TreeNode root, int[] res) {
        if (root == null) {
            return 0;
        }
        // expect for left and right
        int left = Math.max(helperMathPath(root.left, res), 0);
        int right = Math.max(helperMathPath(root.right, res), 0);
        // current layer operation to update max path sum
        res[0] = Math.max(root.value + left + right, res[0]);
        // report to parent node
        return root.value + Math.max(left, right);
    }

    /**
     * Q2.1 直上直下 path
     * find the maximum oath cost for all paths from leaf to root in a binary tree
     * @param root
     * @return
     */
    public int mathPathLeafToRoot(TreeNode root) {
        int[] globalMax = new int[]{Integer.MIN_VALUE};
        helperMathPathLeafToRoot(root, 0, globalMax);
        return globalMax[0];
    }
    private void helperMathPathLeafToRoot(TreeNode root, int prefixSum, int[] globalMax) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            globalMax[0] = Math.max(prefixSum + root.value, globalMax[0]);
            return;
        }
        helperMathPathLeafToRoot(root.left, prefixSum + root.value, globalMax);
        helperMathPathLeafToRoot(root.right, prefixSum + root.value, globalMax);
    }

    /**
     * Q2.2
     * laioffer: https://app.laicode.io/app/problem/141
     * Given a binary tree, determine if there exists a path from any node to any node, sum of the number on
     * path is equal to the given target number
     * @param root
     * @param target
     * @return
     */
    public boolean PathSumToTarget(TreeNode root, int target) {
        if (root == null) return false;
        //pass down the prefix of the path
        List<TreeNode> path = new ArrayList<>();
        return helperPathSumToTarget(root, path, target);
    }
    private boolean helperPathSumToTarget(TreeNode root, List<TreeNode> path, int sum) {
        path.add(root);
        //check if can find a subpath ended at root node,
        // the sum of the subpath is target
        int temp = 0;
        for (int i = path.size() - 1; i >= 0; i--) {
            temp += path.get(i).value;
            if (temp == sum) return true;
        }
        if (root.left != null && helperPathSumToTarget(root.left, path, sum)) {
            return true;
        }
        if (root.right != null && helperPathSumToTarget(root.right, path, sum)) {
            return true;
        }
        //don't forgot for the cleanup when return to the previous level
        path.remove(path.size() - 1);
        return false;
    }

    /**
     * Q2.3
     * laioffer:https://app.laicode.io/app/problem/140
     * Given a binary tree in which each node contains an integer number.
     * Find the maximum possible subpath sum(both the starting and ending node of the subpath should be on the same path
     * from root to one of the leaf nodes, and the subpath is allowed to contain only one node).
     * @param root
     * @return
     * O(n)
     */
    public int maxPathSumNodeToNodeFromRootTOLeaf(TreeNode root) {
        int[] max = new int[]{Integer.MIN_VALUE};
        helperMaxPathSumNodeToNode(root, max);
        return max[0];
    }
    private int helperMaxPathSumNodeToNode(TreeNode root, int[] max) {
        if (root == null) return 0;
        int left = helperMaxPathSumNodeToNode(root.left, max);
        int right = helperMaxPathSumNodeToNode(root.right, max);
        int sin = Math.max(Math.max(left, right), 0) + root.value;
        max[0] = Math.max(max[0], sin);
        return sin;
    }


    //TODO
    /**
     * Q3 Tree + Recursion: Tree serialization problems
     * Q3.2
     * leetcode: https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
     * @param root
     * @return
     */
    public TreeNode flatten(TreeNode root) {
       return null;
    }

    /**
     * Q4 Tree + Recursion: Tree de-serialization problems
     * @param inOrder
     * @param preOrder
     * laioffer:https://app.laicode.io/app/problem/213
     * @return
     */
    public TreeNode reconstructI(int[] inOrder, int[] preOrder) {
        // Write your solution here
        return null;
    }

    public TreeNode reconstructII(int[] post) {
        // Write your solution here
        return null;
    }
    public TreeNode reconstructIII(int[] inOrder, int[] levelOrder) {
        // Write your solution here
        return null;
    }

    public static void main(String[] args) {

    }
}
