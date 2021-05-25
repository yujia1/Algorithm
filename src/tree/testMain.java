package tree;
import src.binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class testMain {
    //TODO review all the methods in second way, record audio explanation
    /**
     * iterator to traverse a post-order tree
     * method 1 is to reverse order of pre-order list
     * methid 2 is directly traverse a post-order tree
     * @param root
     * @return
     */
    public static List<Integer> postOrderIterator(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.offerFirst(root);
        //record previous node
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peekFirst();
            if (prev == null || cur== prev.left || cur == prev.right) {
                if (cur.left != null) {
                    stack.offerFirst(cur.left);
                } else if (cur.right != null) {
                    stack.offerFirst(cur.right);
                } else {
                    stack.pollFirst();
                    list.add(cur.value);
                }
            } else if (prev == cur.right || prev == cur.left && cur.right == null) {
                stack.pollFirst();
                list.add(cur.value);
            } else {
                stack.offerFirst(cur.right);
            }
            prev = cur;
        }
        return list;
    }
    /**
     * iterator to traverse a in-order tree
     * @param root
     * @return
     */
    public static List<Integer> inOrderIterator(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                // offer all node from tree, in sequence all way to left
                stack.offerFirst(cur);
                cur = cur.left;
            } else {
                cur = stack.pollFirst(); // poll last left
                list.add(cur.value); // add left
                cur = cur.right; // go up parent node's right
            }
        }
        return list;
    }

    /**
     * iterator to traverse a pre-order a tree
     * @param root
     * @return
     */
    public static List<Integer> preOrderIterator(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.offerFirst(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pollFirst();
            if (cur.right != null) {
                stack.offerFirst(cur.right);
            }
            if (cur.left != null) {
                stack.offerFirst(cur.left);
            }
            list.add(cur.value);
        }
        return list;
    }

    /**
     * delete node in BST
     * @param root
     * @param target
     * @return
     */
    //TODO last step recursion to discuss
    public static TreeNode delete(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
        // find target node
        if (root.value > target) {
            root.left = delete(root.left, target);
        } else if (root.value < target) {
            root.right = delete(root.right, target);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            TreeNode minNode = findMin(root.right);
            root.value = minNode.value;
            root.right = delete(root.right, root.value);
        }
        return root;
    }
    private static TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * using recursion way to insert node
     * @param root
     * @param value
     * @return
     */
    public static TreeNode insertRecursion(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        if (root.value < value) {
            root.right = insertRecursion(root.right, value);
        } else if (root.value > value) {
            root.left = insertRecursion(root.left, value);
        }
        return root;
    }
    /**
     * insert node in tree, iterator way
     * @param root
     * @param value
     * @return
     */
    public static TreeNode insertIterator(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        TreeNode prev = null;
        TreeNode cur = root;
        TreeNode newNode = new TreeNode(value);
        while (cur != null) {
            prev = cur;
            if (cur.value > value) {
                cur = cur.left;
            } else if(cur.value < value) {
                cur = cur.right;
            } else {
                return root;
            }
        }
        if (prev.value > value) {
            prev.left = newNode;
        }else {
            prev.right = newNode;
        }
        return root;
    }
    /**
     * search node in tree
     * @param root
     * @param val
     * @return
     */
    public static TreeNode search(TreeNode root, int val) {
        if (root == null || root.value == val) return root;
        if (root.value < val) {
            return search(root.right, val);
        } else {
            return search(root.left, val);
        }
    }
    /**
     * get range of tree
     * @param root
     * @param min
     * @param max
     * @return
     */
    public static List<Integer> getRange(TreeNode root, int min, int max) {
        if (root == null) {
            return null;
        }
        List<Integer> list = new LinkedList<>();
        getRange(root, list, min, max);
        return list;
    }
    private static void getRange(TreeNode root, List<Integer> list, int min, int max) {
        if (root == null) {
            return;
        }
        if (root.value > min) {
            getRange(root.left, list, min, max);
        }
        if (root.value >= min && root.value <= max) {
            list.add(root.value);
        }
        if (root.value < max) {
            getRange(root.right, list, min, max);
        }
    }
    /**
     * check if the tree is a binary search tree
     * @param root
     * @return
     */
    public static boolean isBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        return isBST(root,min, max);
    }
    private static boolean isBST(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.value <= min ||  root.value >= max) {
            return false;
        }
        return isBST(root.left,min,root.value) && isBST(root.right, root.value, max);
    }
    /**
     * check if tree is tweaked identical
     * @param one
     * @param two
     * @return
     */
    public static boolean isTweakedIdentical(TreeNode one, TreeNode two) {
        // Write your solution here
        if(one == null && two == null) {
            return true;
        } else if(one == null || two == null) {
            return false;
        } else if(one.value != two.value) {
            return false;
        }
        return isTweakedIdentical(one.left, two.left) && isTweakedIdentical(one.right, two. right) ||
                isTweakedIdentical(one.left,two.right) && isTweakedIdentical(one.right, two.left);
    }
    /**
     * check if tree is symmetric
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
             return true;
        }
        return isSymmetric(root, root);
    }
    private static boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if(left == null || right == null) {
            return false;
        } else if (left.value != right.value) {
            return false;
        }
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }
    /**
     * using hight check if is balanced Tree
     * @param root
     * @return
     */
    public static boolean isBalance(TreeNode root) {
        if (root == null) {
            return true;
        }
        return getHeightCheckBalance(root) >= 0;
    }
    private static int getHeightCheckBalance(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftH = getHeightCheckBalance(root.left);
        int rightH = getHeightCheckBalance(root.right);
        if (leftH == -1 || rightH == -1) {
            return -1;
        }
        if (Math.abs(leftH -rightH) > 1) {
            return -1;
        }
        return Math.max(leftH, rightH) + 1;
    }

    public static int getHeightOfNode(TreeNode root, TreeNode key) {

            if(root == null) return -1;
            return 0; /// helper
    }

    /**
     * to get node level, from top to bottom
     * @param root
     * @param key
     * @param level
     * @return
     */
    private static int helper(TreeNode root, int key, int level) {
        if (root == null) return -1;
        if (root.value == key) return level;
        int leftL = helper(root.left, key, level +1);
        int rightL = helper(root.right, key, level+ 1);
        return leftL == -1 ? rightL : leftL;
    }
    /**
     * to get node level, from bottom to top
     * @param root
     * @param target
     * @param res
     * @return
     */

    private static int helper(TreeNode root, TreeNode target, int[] res) {
        if (root == null) return 0;
        int left = helper(root.left, target, res);
        int right = helper(root.right, target, res);
        if (root == target) {
            res[0] = Math.max(left, right) + 1;
        }
        return Math.max(left, right) + 1;
    }
    // 给一个range， 把所有范围内的node加起来
    public static int sumRange(TreeNode root, int left, int right) {
        int[] sum = new int[1];
        helper(root, left , right, sum);
        return sum[0];
    }
    private static void helper(TreeNode root, int low, int high, int[] sum) {
        // root == none
        if (root == null || root.left.value < low || root.right.value > high) {
            return;
        }
        // low == high
        if (low == high) {
            sum[0] = low;
        }
        // low < * < high
        if(root.value >= low && root.value <=high) {
            sum[0] += root.value;
        }
        helper(root.left, low, high, sum);
        helper(root.right, low, high, sum);
    }

    public static void main(String[] args) {
    }
}
