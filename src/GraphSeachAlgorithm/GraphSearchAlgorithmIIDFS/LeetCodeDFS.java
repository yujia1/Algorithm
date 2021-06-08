package GraphSeachAlgorithm.GraphSearchAlgorithmIIDFS;

import java.util.*;

public class LeetCodeDFS {
    /**
     * Q1.1 subset problem:https://leetcode.com/problems/subsets
     * without dups
     * @param nums
     * @return
     * TC：O(2^N * N)
     * SC: O(N)
     */
    public List<List<Integer>> subsets(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        List<Integer> curr = new ArrayList<>();
        dfsSubset(nums, 0, curr, res);
        return res;
    }
    private void dfsSubset(int[] nums, int index, List<Integer> curr, List<List<Integer>> res) {
        if (index > nums.length) {
            return;
        }
        res.add(new ArrayList<>(curr));
        for (int i = index; i <nums.length; i++) {
            curr.add(nums[i]);
            dfsSubset(nums, i + 1, curr, res); // i+1 is key, to narrow down next element of nums[i]
            curr.remove(curr.size() - 1);
        }
    }
    /**
     * Q1.2 subset problem: https://leetcode.com/problems/subsets-ii/
     * with dups
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        List<Integer> cur = new ArrayList<>();
        dfsSubsetsWithDup(nums, res, cur, 0);
        return res;
    }
    private void dfsSubsetsWithDup(int[] nums, List<List<Integer>> res, List<Integer> cur, int index) {
        if (index > nums.length) {
            return;
        }
        res.add(new ArrayList<>(cur));
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i-1]) continue; // i must be bigger than current index, 这样就在同一个loop里的下一个数字
            cur.add(nums[i]);
            dfsSubsetsWithDup(nums, res, cur, i + 1);
            cur.remove(cur.size() - 1);
        }
    }

    /**
     * Q2.1: find all possible permutaions, given an array nums that are no duplicate elements
     * https://leetcode.com/problems/permutations
     * @param nums
     * @return
     * TC:(N! *N)
     * SC: O(N)
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        dfsPermute(nums, res, new ArrayList<>());
        return res;
    }
    private void dfsPermute(int[] nums, List<List<Integer>> res, List<Integer> cur) {
        if (cur.size() == nums.length) {
            //System.out.println("return last level");
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //System.out.println("current index: " + i);
            if (!cur.contains(nums[i])) { //这个条件其实是砍掉了树枝
                //System.out.println("add value: " + nums[i]);
                cur.add(nums[i]);
                dfsPermute(nums, res, cur);
                //System.out.println("remove value " + cur.get(cur.size() -1));
                cur.remove(cur.size() - 1);
            }
        }
    }
    /**
     * Q2.2:find all possible permutaions, given an array nums that are with duplicate elements
     * https://leetcode.com/problems/permutations-ii/
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        boolean[] m = new boolean[nums.length];
        dfsPermuteUnique(nums, res, new ArrayList<>(), m);
        return res;
    }
    public void dfsPermuteUnique(int[] nums, List<List<Integer>> res, List<Integer> cur, boolean[] m) {
        if (cur.size() == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (m[i] || i > 0 && nums[i] == nums[i-1] && !m[i-1]) continue;
            m[i] = true;
            cur.add(nums[i]);
            dfsPermuteUnique(nums, res, cur, m);
            m[i] = false;
            cur.remove(cur.size() - 1);
        }
    }
    //TODO
    // using swap way to do it
    //https://leetcode.com/problems/permutations-ii/discuss/932886/java-0-ms-%3A-100-faster-%3A-using-swap


    /**
     * Q3 letter combinations
     * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
     * @param digits
     * @return
     */
    final String[] mapping = new String[]{"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        char[] cur = new char[digits.length()];
        dfsLetterComb(digits,0,cur, res);
        return res;
    }
    private void dfsLetterComb(String digits, int index, char[] cur, List<String> res) {
        if (index == digits.length()) {
            res.add(new String(cur));
            return;
        }
        String combo = mapping[digits.charAt(index) - '0'];
        for (int i = 0; i < combo.length(); i++) {
            cur[index] = combo.charAt(i);
            dfsLetterComb(digits, index+1, cur, res);
        }
    }

    /**
     * Q4
     * leetcode: https://leetcode.com/problems/combination-sum/
     *Time Complexity: O(N^(T/M + 1))
     * Let N be the number of candidates, T be the target value, and M be the minimal value among the candidates.
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(candidates == null || candidates.length ==0) return res;
        List<Integer> cur = new ArrayList<>();
        helper(candidates, 0, target, cur, res);
        return res;
    }
    private void helper(int[] arr, int index, int target, List<Integer> cur, List<List<Integer>> res) {
        if(target == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for(int i = index; i < arr.length; i++) { // move to next one i = index
            if(arr[i] > target) continue;
            cur.add(arr[i]);
            helper(arr, i, target - arr[i], cur, res); // move to next one index = i; if index = i+1, 则不包括重复的自己
            cur.remove(cur.size() - 1);
        }
    }

    /**
     * Q5
     * leetcode:https://leetcode.com/problems/number-of-islands
     * using dfs
     * TC: O(N*M) where n is length of row and m is length of column
     * SC: worst case O(N*M) gird filled with island dfs have to go by M* N deep
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int row = grid.length;
        int col = grid[0].length;
        int numIslands = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    DFSNumIslands(grid, i, j);
                    numIslands++;
                }
            }
        }
        return numIslands;
    }
    private void DFSNumIslands(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '0';
        DFSNumIslands(grid, i+1, j);
        DFSNumIslands(grid, i-1, j);
        DFSNumIslands(grid, i, j+1);
        DFSNumIslands(grid, i, j-1);
    }

    /**
     * Q6 numEnclaves
     * leetcode:https://leetcode.com/problems/number-of-enclaves
     * using dfs
     * TC: O(N*M) where n is length of row and m is length of column
     * SC: worst case O(N*M) gird filled with island dfs have to go by M* N deep
     * @param grid
     * @return
     */
    public int numEnclaves(int[][] grid) {
        int step = 0;
        int n = grid.length;
        int m = grid[0].length;
        for(int i = 0; i < n; i++) {
            for(int j =0; j < m; j++) {
                if((i==0 || j==0 || i==grid.length-1 || j== grid[0].length-1) && grid[i][j]==1) {
                    dfs(grid, i, j); // 挨着edge的都变成0
                }
            }
        }
        for(int i = 0; i < n; i++) {
            for(int j =0; j < m; j++) {
                if(grid[i][j] == 1) {
                    step++;
                }
            }
        }
        //然后找剩下的1

        return step;
    }
    private void dfs(int[][] grid, int i, int j) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length  || grid[i][j] != 1) {
            return;
        }
        grid[i][j] = 0;
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }

    /**
     * Q7 surroundedRegions
     * leetcode: https://leetcode.com/problems/surrounded-regions/
     * TC: O(N*M) where n is length of row and m is length of column
     * SC: worst case O(N*M) gird filled with island dfs have to go by M* N deep
     * @param board
     */
    public void surroundedRegions(char[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length, n = board[0].length;
        //convert all O at edges to A a
        for (int i = 0; i < n; i++) {
            if (board[0][i] == 'O') dfs(board, 0, i);
        }
        for (int i = 0; i < n; i++) {
            if (board[m - 1][i] == 'O') dfs(board, m - 1, i);
        }
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') dfs(board, i, 0);
        }
        for (int i = 0; i < m; i++) {
            if (board[i][n - 1] == 'O') dfs(board, i, n - 1);
        }
        //convert akk edges A to O, and other transform to X
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'A') board[i][j] = 'O';
                else board[i][j] = 'X';
            }
        }
    }
    public void dfs(char[][] board, int i, int j) {
        int m = board.length, n = board[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'O') return;
        board[i][j] = 'A';
        dfs(board, i + 1, j);
        dfs(board, i - 1, j);
        dfs(board, i, j + 1);
        dfs(board, i, j - 1);
    }

    /**
     * Q8.1 word search
     * leetcode:https://leetcode.com/problems/word-search
     * TC: O(N * M * 4^l) where n is length of row and m is length of column, 4 is four directions, L is length of words
     * SC: worst case O(N*M) gird filled with island dfs have to go by M* N deep
     * @param word
     */
    public boolean wordSearch(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || word == null) return false;
        boolean[][] used = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (word.charAt(0) == board[i][j] && dfs(board, word, 0, i, j, used)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean dfs(char[][] board, String word, int index, int i, int j, boolean[][] used) {
        if (index == word.length()) {
            return true;
        }
        if ( i < 0 || j < 0 || i >= board.length || j >= board[0].length || used[i][j] || word.charAt(index) != board[i][j]) {
            return false;
        }
        used[i][j] = true;
        if (dfs(board, word, index + 1, i + 1, j, used) || dfs(board, word, index + 1, i - 1, j, used)||
            dfs(board, word, index + 1, i, j + 1, used) || dfs(board, word, index + 1, i, j - 1, used)) {
            return true;
        }
        used[i][j] = false;
        return false;
    }

    /**
     * Q8.2 following up word search
     * leetcode:https://leetcode.com/problems/word-search-ii
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        int count = 0;
        for(String word : words){
            for(int i = 0; i<board.length; i++){
                for(int j = 0; j<board[i].length; j++){
                    if(board[i][j]==word.charAt(0)){
                        if(dfs(board,word,i,j,count))
                            if(!result.contains(word))
                                result.add(word);
                    }
                }
            }
        }
        return result;

    }
    public boolean dfs(char[][]board,String word, int i, int j, int count){
        if(count==word.length()){
            return true;
        }
        if(i<0||i>=board.length||j<0||j>=board[i].length||board[i][j]=='.'||board[i][j]!=word.charAt(count)){
            return false;
        }
        char temp = board[i][j];
        board[i][j] = '.';//表示访问过
        boolean resp = dfs(board,word,i+1,j,count+1)||
                dfs(board,word,i-1,j,count+1)||
                dfs(board,word,i,j+1,count+1)||
                dfs(board,word,i,j-1,count+1);
        board[i][j]=temp;
        return resp;
    }
    /**
     * Q9
     * leetcode:https://leetcode.com/problems/smallest-rectangle-enclosing-black-pixels
     * smallest rectangle enclosing black pixels
     * @param image, x, y
     */
    private int top, bottom, left, right;
    public int minArea(char[][] image, int x, int y) {
        if (image.length == 0 || image[0].length == 0) return 0;
        top = bottom = x;
        left = right = y;
        dfsMinArea(image, x, y);
        return (right - left) * (bottom - top);
    }
    private void dfsMinArea(char[][] image, int x, int y) {
        if (x < 0 || y < 0 || x >= image.length || y > image[0].length || image[x][y] == '0') {
            return;
        }
        image[x][y] = 0;
        top = Math.min(top, x);
        bottom = Math.max(bottom, x + 1);
        left = Math.min(left, y);
        right = Math.max(right, y + 1);
        dfsMinArea(image, x + 1, y);
        dfsMinArea(image, x - 1, y);
        dfsMinArea(image, x, y + 1);
        dfsMinArea(image, x, y - 1);
    }

    /**
     * Q10
     * daddy of DFS
     * N-queens
     * leetcode: https://leetcode.com/problems/n-queens/
     * @param n
     */
    public List<List<String>> NQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        List<List<String>> res = new ArrayList<>();
        dfsNQueens(board, 0, res);
        return res;
    }
    private void dfsNQueens(char[][] board, int colIndex, List<List<String>> res) {
        if (colIndex == board.length) {
            res.add(construct(board));
            return;
        }
        for (int i = 0; i < board.length; i++) {
            if (validate(board, i, colIndex)) {
                board[i][colIndex] = 'Q';
                dfsNQueens(board, colIndex + 1, res);
                board[i][colIndex] = '.';
            }
        }
    }
    private boolean validate(char[][] board, int x, int y) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < y; j++) {
                if (board[i][j] == 'Q' && (x - i == y - j || x + y == i + j || x == i)) { //俩个斜对角 以及同一row
                    return false;
                }
            }
        }
        return true;
    }
    private List<String> construct(char[][] board) {
        List<String> res = new LinkedList<String>();
        for(int i = 0; i < board.length; i++) {
            String s = new String(board[i]);
            res.add(s);
        }
        return res;
    }
    // 第二类graph search  dfs

    /**
     * Q11
     * Number of Connected Components in an Undirected Graph
     * leetcode:https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
     * @param n
     * @param edges
     * @return
     */
    public int countComponents(int n, int[][] edges) {
        if (n <= 1)
            return n;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }
        //populate pairs to map and construct undirected graph
        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        Set<Integer> visited = new HashSet<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (visited.add(i)) { // if never visited, we count one graph
                dfsVisit(i, map, visited); // start with node i-th and dfs to its adj where stores at map.get(i)
                count++;
            }
        }
        return count;
    }
    private void dfsVisit(int i, Map<Integer, List<Integer>> map, Set<Integer> visited) {
        for (int j : map.get(i)) {
            if (visited.add(j))
                dfsVisit(j, map, visited);
        }
    }
    /**
     * another two sum unique pairs
     */
    public int uniquePairs(int[] nums, int target){
        Set<Integer> set = new HashSet<Integer>();
        Set<Integer> seen = new HashSet<Integer>();
        int count = 0;
        for(int num : nums){
            if(set.contains(target-num) && !seen.contains(num)){
                count++;
                seen.add(target-num);
                seen.add(num);
            }
            else if(!set.contains(num)){
                set.add(num);
            }

        }

        return count;
    }
    /**
     * Q12
     * course schedule
     * @param numCourses
     * @param prerequisites
     * @return
     * Time Complexity: {O}(|E| + |V| ^ 2) where ∣E∣ is the number of dependencies, ∣V∣ is the number of courses
     * Space Complexity: {O}(|E| + |V|)O(∣E∣+∣V∣)
     */
    //TODO
    // try to use bipart bfs to do courses
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //corner case
        if (prerequisites == null || numCourses == 0 || prerequisites.length == 0) return true;
        //set up adj list
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            map.put(i, new ArrayList<Integer>());
        }
        for (int[] req: prerequisites) {
            map.get(req[1]).add(req[0]); // directed graph, so only one-way
        }
        //create a visited array where 0-unvisited, -1 = visiting, 1=visited
        int[] visited = new int[numCourses]; // visited original =0, and we trynna to split people into 1 and -1 two group
        for (int i = 0; i< numCourses; i++) {
            if (!dfsCourse(map, visited, i)){
                return false;
            }
        }
        return true;
    }
    private boolean dfsCourse(HashMap<Integer, ArrayList<Integer>> map, int[] visited, int i) {
        // 0 is never visited, 1 is current visited , -1 visiting
        if (visited[i] == -1) return false; // its current dfs cycle
        if (visited[i] == 1) return true; // separate dfs visited
        visited[i] = -1;
        if (map.containsKey(i)) {
            for (int j : map.get(i)) {
                if (!dfsCourse(map, visited, j)) {
                    return false;
                }
            }
        }
        visited[i] = 1; // set up separate visited
        return true;
    }

    /**
     * Q13
     * bipartition problems to spilt dislike people into two group
     * @param n
     * @param dislikes
     * @return
     */
    public boolean possibleBipartition(int n, int[][] dislikes) {
        //corn case
        if(n == 0 || dislikes == null ||
                dislikes.length == 0 || dislikes[0].length == 0) return true;
        //contruct undirected graph
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            map.put(i, new ArrayList<Integer>());
        }
        // populate dislikes into graph
        for(int[] dis: dislikes) {
            map.get(dis[0] - 1).add(dis[1] -1); // watch out the bipartion is start with 1 to n, which affect following dfs
            map.get(dis[1] - 1).add(dis[0] -1); // undirected graph
        }
        //visited
        int[] visited = new int[n];
        //graph traverse
        for(int i = 0; i < n; i++) {
            if(visited[i] == 0 && !dfsBipart(map, visited, i, 1)) {
                return false;
            }
        }
        return true;
    }
    private boolean dfsBipart(HashMap<Integer, List<Integer>> map, int[] visited, int person, int group) {
        visited[person] = group;
        for(int nei : map.get(person)) {
            if(visited[nei] == group) { // nei cannot be same group with current person
                return false; // two dislike people on same group -> person and nei
            }
            if(visited[nei] == 0 && !dfsBipart(map, visited, nei, -group)) { //-group pass to next person
                return false;
            }
        }
        return true;
    }

    /**
     * find minimum height of trees
     * think it as a tissue, find the spot where the distance to edges are farthest
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) return Collections.singletonList(0);
        //set up the adjacency list
        List<Set<Integer>> graph  = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new HashSet<>());
        }
        // construct graph node
        for (int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        //keep track of all leaves( nodes on the edge of the graph with only one nei)
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (graph.get(i).size() == 1) {
                leaves.add(i);
            }
        }
        //keep removing leaves until there's at most 2 nodes left on the graph
        while ( n > 2) {
            // update the total number of nodes in the graph, after we remove the leaves
            n -= leaves.size();
            // temporary array to hold the new leaf
            List<Integer> newLeaves = new ArrayList<>();
            //remove each of current leaves
            for (int i : leaves) {
                //get this leaf's one and only nei
                int j = graph.get(i).iterator().next();
                // go to this leaf's nei and remove this leaf from this list
                graph.get(j).remove(i);
                // if that nei only has one nei left, it's a leaf now
                if (graph.get(j).size() == 1) {
                    newLeaves.add(j);
                }
            }
            leaves = newLeaves;
        }
        return leaves;
    }

    public static void main(String[] args) {
        LeetCodeDFS test = new LeetCodeDFS();
        int[] nums = new int[] {1, 2, 3};
        //Q1.1 test or Q1.2
//        List<List<Integer>> res1 = test.subsetsWithDup(nums);
//        for (List<Integer> list: res1) {
//            for (int num: list) {
//                System.out.print( num + " ");
//            }
//            System.out.println();
//        }
        //Q2.1 test
//        List<List<Integer>> res2 = test.permute(nums);
//        for (List<Integer> list: res2) {
//            for (int num: list) {
//                System.out.print( num + " ");
//            }
//            System.out.println();
//        }

   }
}
