package binaryTree;

import com.sun.source.tree.Tree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TreeNode {

    public int value;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int value) {
        this.value = value;
    }

    public TreeNode(int value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public static List<Integer> preOrder(TreeNode root){

        List<Integer> list = new ArrayList<>();
        preOrder(root, list);
        return list;
    }
    private static void preOrder(TreeNode root, List list){
        if (root == null) {
            return;
        }
        list.add(root);
        preOrder(root.left, list);
        preOrder(root.right, list);
    }

    public static int getHeight(TreeNode root){
        if (root == null) {
            return 0;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        return Math.max(left, right) +1;
    }
    public static int countNode(TreeNode root) {
        if (root == null){
            return 0;
        }
        int left = countNode(root.left);
        int right = countNode(root.right);
        return left + right + 1;
    }
    public static boolean checkBalance(TreeNode root){
        // base case
        if (root == null) {
            return true;
        }

        boolean leftCheck = checkBalance(root.left);
        boolean rightCheck = checkBalance(root.right);
        if(!leftCheck || !rightCheck) {
            return false;
        }
        int leftH = getHeight(root.left);
        int rightH = getHeight(root.right);
        if(Math.abs(leftH - rightH) <= 1) {
            return true;
        }
        return false;
    }
    public static int mystery(TreeNode root) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                return 0;
            }
            int sum = mystery(root.left) + mystery(root.right);
            if (sum == 0) {
                TreeNode temp = root.left;
                root.left = root.right;
                root.right = temp;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(10);
        TreeNode n2 = new TreeNode(5);
        TreeNode n3 = new TreeNode(2);
        TreeNode n4 = new TreeNode(7);
        TreeNode n5 = new TreeNode(15);
        TreeNode n6 = new TreeNode(12);
        TreeNode n7 = new TreeNode(9);
        n1.left = n2;
        n1.right = n5;
        n2.left = n3;
        n2.right = n4;
        n5.left = n6;
        n6.left = n7;

        System.out.println(getHeight(n1));
        System.out.println(countNode(n1));
        System.out.println(checkBalance(n1));
        System.out.println(mystery(n1));

    }
}
