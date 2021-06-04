package tree;

import binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class TreesLeetCode {
    /**
     * Q1
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
     * case1:
     *      10
     *  9       20
     *        15    7
     * at node 20, max = left 15 + right 7 + root.val 20 = 42, but we also consider return to upper layer node 10
     *  therefore, we only return one of path from 15+20 or 7+20, apparently, we return 15+20 where math.max(15, 7) as return to parent node
     * case 2
     *      10
     *  9       -20
     *        15    7
     * at node -20, whatever path we choose between 15-20 or 7-20, the path is less 0, therefore, upper layer 10 definitely dont want it
     * therefore, whatever left child or right child, node 10 either want positive number or 0. in other word, left/right = Math.max(0, recursion(root.left));
     */

    public int maxPathSumNodeToNode(TreeNode root) {
        int[] max = new int[1];
        max[0] = Integer.MIN_VALUE;

        nodeToNodeSearch(root, max);
        return max[0];
    }
    private int nodeToNodeSearch(TreeNode root, int[] max) {
        if (root == null) return 0;
        int left = Math.max(0, nodeToNodeSearch(root.left, max));
        int right = Math.max(0, nodeToNodeSearch(root.right, max));
        max[0] = Math.max(left + right + root.value, max[0]);
        return Math.max(left, right) + root.value;
    }

    /**
     * leaf to leaf max path
     * @param root
     * @return
     */
    public int maxPathSumLeafToLeaf(TreeNode root) {
        int[] res = new int[1];
        leafToLeafSearch(root, res);
        return res[0];
    }
    private int leafToLeafSearch(TreeNode root, int[] res) {
        if (root == null) return 0;
        int left = leafToLeafSearch(root.left, res);
        int right = leafToLeafSearch(root.right, res);
        int curMax = left + right + root.value;
        if (root.left != null && root.right !=null && res[0] < curMax) {
            res[0] = curMax;
        }
        if (root.left == null) {
            return root.value + right; // 缺少一个叶子， 必须返回另外一半
        } else if(root.right == null) {
            return root.value + left;
        }
        return Math.max(left, right) + root.value;
    }

    /**
     * max sum path from root to leaf
     * @param root
     * @return
     */
    public int maxPathSumRootToLeaf(TreeNode root) {
        int[] res = new int[1];
        maxPathSumRootToLeafSearch(root, 0, res);
        return res[0];
    }
    private void maxPathSumRootToLeafSearch(TreeNode root, int prefix, int[] res) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            res[0] = Math.max(res[0], prefix);
            return;
        }
        maxPathSumRootToLeafSearch(root.left, prefix + root.value, res);
        maxPathSumRootToLeafSearch(root.right, prefix + root.value, res);

    }

    /**
     * traverse tree by level
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        traverse(root, res, 0);
        return res;
    }
    private void traverse(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null) return;
        if (level == res.size()) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.value);
        traverse(root.left, res, level + 1);
        traverse(root.right, res, level + 1);
    }
    //find largest value in each tree row
    //binary tree zigzag level order traversal
    /**
     * populating next right to pointer in each node of tree
     * leetcode: https://leetcode.com/problems/populating-next-right-pointers-in-each-node
     */

    /**
     * construct tree in term of preOrder and inOrder
     * leetCode: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/submissions/
     */

}
