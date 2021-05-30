package GraphSeachAlgorithm.GraphSearchAlgorithmBFS;

import src.binaryTree.TreeNode;
import java.util.*;

/**
 * breadth-first- search BFS1 -> apply to same level node interaction
 * Best-first-search BFS2-> apply to problem to find short way and relationship between different level node
 */
public class
BFS {
    //TODO this is heap and graph

    // use queue to poll every and

    /** 1
     * // breadth-first search (BFS-1)
     * print out node by level
     * @param root
     */
    public static void printNodeByLevel(TreeNode root){
        return;
    }
    /**
     * given an unsorted array, and return smallest k element array
     * @param array
     * @param k
     * @return
     */
    public static int[] kthSmallestInUnsortedArray(int[] array, int k) {
        if (array.length == 0 || k == 0) {
            return new int[0];
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue(k, new Comparator<Integer>(){
            public int compare(Integer a, Integer b) {
                if (a == b) {
                    return 0;
                }
                return a > b ? -1: 1;
            }
        });
        // go through array element to maxHeap and pick elements
        for (int i = 0; i < array.length; i++) {
            if (i < k) {
                maxHeap.offer(array[i]);
            }else if (array[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(array[i]);
            }
        }
        int[] res = new int[k];
        for (int i = k-1; i >= 0; i--) {
            res[i] = maxHeap.poll();
        }
        return res;
    }
    //TODO review it
    /** 2
     * if it is completed binary search tree
     * @param root
     * @return
     */
    public static boolean isCompletedBST(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean flag = false;
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            // if it has null, then set up flag true,
            if (cur.left == null) {
                flag = true;
            } else if (flag) { // after flag, it does not allow node aftermath, 下一层的不允许有node
                return false;
            } else {
                q.offer(cur.left);
            }

            if (cur.right == null) {
                flag = true;
            } else if (flag) {
                return false;
            } else {
                q.offer(cur.right);
            }
        }
        return true;
    }


    /** 3
     * bipartite
     * @param graph
     * @return
     */
    public static boolean isBipartite(List<GraphNode> graph) {
        //use 0 and 1 to denote two different groups
        // the map maintains for each node which group it belong
        HashMap<GraphNode, Integer> visited = new HashMap<GraphNode, Integer>();
        // the graph can be represented by a list of nodes( if it is not guaranteed tl
        // to be connected.) we have to do bfs from each of the node
        for (GraphNode node: graph) {
            if (!BFS1(node, visited)) {
                return false;
            }
        }
        return true;
    }
    // high level idea
    // step1: set up initial color
    // step2: set up traverse neighbor and assign color
    //  2.1 if neighbor does not exist , add into, and assign neighbor color
    //  2.2 if neighbor already existed, check if it maintain same group color; if not, false;
    private static boolean BFS1(GraphNode node, HashMap<GraphNode, Integer> visited) {
        // if this node has been traversed, no need to do bfs
        if (visited.containsKey(node)) {
            return true;
        }
        Queue<GraphNode> queue = new LinkedList<>();
        queue.offer(node);
        visited.put(node, 0);
        // start breadth-first-search from the node, and assign them group
        while (!queue.isEmpty()) {
            GraphNode cur = queue.poll();
            int curGroup = visited.get(cur);
            int neiGroup = curGroup == 0 ? 1: 0;
            for (GraphNode nei: cur.neighbors) {
                if (!visited.containsKey(nei)) {
                    visited.put(nei, neiGroup);
                    queue.offer(nei);
                } else if (visited.get(nei) != neiGroup) {
                    return false;
                }
            }
        }
        return true;
    }

    //TODO find kth smallest in sorted Matrix, using my own way
    /**4
     * best-first - search
     * 1    2   3   5   6
     * 2    3   4   6   8
     * 3    4   5   7   9
     * 4    5   6   8   10
     * 5    6   9   10  11
     * find kth smallest element in it
     * @param matrix
     * @param k
     * @return
     */
    public static int kthSmallestInSortedMatrix(int[][] matrix, int k) {
        if (matrix.length == 0 || k == 0) {
            return -1;
        }

        PriorityQueue<Cell> minHeap = new PriorityQueue<>(k, new Comparator<Cell>(){
            public int compare(Cell c1, Cell c2) {
                if (c1.value == c2.value) {
                    return 0;
                }
                return c1.value < c2.value ? -1: 1;
            }
        });
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        minHeap.offer(new Cell(0,0,matrix[0][0]));
        visited[0][0] = true;

        for (int i =0; i < k-1; i++){ //到正好k-th 个数字停下来
            Cell cur = minHeap.poll();
            if (cur.row + 1 < rows && !visited[cur.row + 1][cur.col]) { // expand to row
                minHeap.offer(new Cell(cur.row+1, cur.col, matrix[cur.row+1][cur.col]));
                visited[cur.row+1][cur.col] = true;
            }
            if (cur.col + 1 < cols && !visited[cur.row][cur.col+1]) { // expand to col
                minHeap.offer(new Cell(cur.row, cur.col + 1, matrix[cur.row][cur.col+1]));
                visited[cur.row][cur.col+1] = true;
            }
        }
        return minHeap.peek().value;
    }



    /**5
     * breadth-first-search
     * merge two binary tree to one
     * @param n1
     * @param n2
     * @return
     */
    public TreeNode merge(TreeNode n1, TreeNode n2) {
        if (n1== null && n2 == null) {
            return null;
        }
        if (n1 == null) {
            return n2;
        }
        if (n2 == null) {
            return n1;
        }

        TreeNode n3 = new TreeNode(n1.value + n2.value);
        n3.left = merge(n1.left, n2.left);
        n3.right = merge(n1.right, n2.right);
        return n3;
    }

    //TODO create an array that transforms to  tree(hard)
    //TODO create a tree that transforms to array(easy)
    public static int findKSmallest(int[] array1, int[] array2, int k) {
        if (k > array1.length * array2.length) {
            return -1;
        }
        if (array1 == null && array2 == null) return -1;
        if (array1 == null || array2 == null) return -1;
        PriorityQueue<Integer> maxHeap = new PriorityQueue(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1.equals(o2)) {
                    return 0;
                }
                return o1 > o2 ? -1 : 1;
            }
        });
        //step 1 , sum up all a1 and a2 element

        int[] res = new int[array1.length * array2.length];
        int n = 0 ;
        for (int i = 0; i < array1.length; i ++) {
            for (int j = 0; j < array2.length; j++) {
                res[n] = array1[i] + array2[j];
                n++;
            }
        }
        // step 2, pick up k smallest element
        for (int m = 0; m < res.length; m++) {
            if (m < k ) {
                maxHeap.offer(res[m]);
            } else if (res[m] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(res[m]);
            }
        }
        return maxHeap.peek();
    }


    //TODO print out right side 1,
    public static List<Integer> printOut(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        int[] globalLevel = new int[1];
        helper(root, globalLevel, 0, res);
        return res;
    }
    public static void helper(TreeNode root, int[] globalLevel, int curLevel, List<Integer> res) {
        if (root == null) return;
        if (globalLevel[0] < curLevel) {
            globalLevel[0] = curLevel;
            res.add(root.value);
        }
        helper(root.right, globalLevel, curLevel + 1, res);
        helper(root.left, globalLevel, curLevel + 1, res);
    }

    //TODO reverse a binary tree upside down
    public static TreeNode reverseTree(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        TreeNode newRoot = reverseTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return newRoot;
    }
    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }
        return fib(n-1) + fib(n  - 2);
    }
    public static void main(String[] args) {
//        int[] array1  = {1,3,5};
//        int[] array2 = {4,8};
//        int a = findKSmallest(array1, array2, 5);
//        System.out.println(a);
//        PriorityQueue<Integer> q = new PriorityQueue<>(11);
        System.out.println(fib(2));
    }

}
