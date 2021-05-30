package src.BloombergInterview;

import src.binaryTree.TreeNode;

public class interview {
    /**
     * Q1
     * inorderSuccessor
     * Given the root of a binary search tree and a node p in it, return the in-order successor of that node in the BST.
     * If the given node has no in-order successor in the tree, return null.
     * The successor of a node p is the node with the smallest key greater than p.val.
     *          20
     *     9        25
     *  5     12
     *      11  14
     * p = 9, then Successor is 11
     * p = 14, then successor is 20
     * */
    static TreeNode successor = null;
    private static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        while (root != null) {
            // keep looping and cut the tree half
            if (p.value >= root.value) {
                root = root.right; // keep going to right if p.val >= root.val
            } else {
                successor = root; // only keep successor when go to check out left p.val < root.val
                root = root.left;
            }
        }
        return successor;
    }

    public static void main(String[] args) {

    }
}
