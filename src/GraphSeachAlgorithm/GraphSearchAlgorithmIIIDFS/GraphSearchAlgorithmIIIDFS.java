package src.GraphSeachAlgorithm.GraphSearchAlgorithmIIIDFS;

import java.util.*;
//https://docs.google.com/document/d/1r9PiZRMGYPTLX10fczPX2I9aGEsTCMmgmLOuOsjsLaM/edit#

public class GraphSearchAlgorithmIIIDFS {
    /**
     * Q1 BFS-1 on implicit graphs
     * Q1.1 Seven puzzle
     * laioffer:
     * https://app.laicode.io/app/problem/681
     * leetcode:
     * https://leetcode.com/problems/sliding-puzzle/
     * time complexity: O( (m*n)^3; where 3 is supposed to be 4 directions, but the one direction is always out of bound.
     * */
    private static final int ROWS = 2;
    private static final int COLS = 4;
    private static final int[][] MOVES = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};//down, top, right, left four irections
    private static final String TARGET = "01234567";
    private static boolean inBound(int r, int c) {
        if (r < 0 || c < 0 || r >= ROWS || c >= COLS) {
            return false;
        }
        return true;
    }
    // convert it to string to compare
    private static String boardString(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }
    // swap 0 to adjacent values
    private static String moveZero(String currBoard, int currZero, int nextZero) {
        char[] chars = currBoard.toCharArray();
        char tmp = chars[nextZero];
        chars[nextZero] = '0';
        chars[currZero] = tmp;
        return new String(chars);
    }
    //conditional input is an array, instead of matrix
    private static int[][] convert(int[] values) {
        int[][] board = new int[ROWS][COLS];
        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLS; j++) {
                if(i == 0) {
                    board[i][j] = values[j];
                } else {
                    board[i][j] = values[i * j + 4];
                }
            }
        }
        return board;
    }
    public static int slidingPuzzle(int[][] board) {
        // pre: convert matrix to string, using it store and compare
        String state = boardString(board);
        int count = -1;
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(state);
        visited.add(state);
        // BFS
        while (!queue.isEmpty()) {
            Queue<String> nextQueue = new LinkedList<>();
            count++;
            for (String curr: queue) {
                // if current state is target, then return
                if (curr.equals(TARGET)) {
                    return count;
                }
                // find index of 0, convert it to coordinate(r,c)
                int currZero = curr.indexOf("0");
                int r = currZero / COLS;
                int c = currZero % COLS;
                for (int[] move: MOVES) { // expand curr state to four different directions
                    int nr = r + move[0];
                    int nc = c + move[1];
                    int nextZero = nr * COLS + nc; // convert next zero index according to (r,c)
                    // check out if out of bound
                    if (inBound(nr, nc)) {
                        //swap 0 with adjacent numbers
                        String nextBoard = moveZero(curr, currZero, nextZero);
                        if (!visited.contains(nextBoard)) {
                            visited.add(nextBoard); // add visited
                            nextQueue.add(nextBoard); // record new state board to new queue
                        }
                    }
                }
            }
            queue = nextQueue; // replace current queue
        }
        return -1;
    }

    /**
     * Q1.2Word Ladder
     * laioffer: https://app.laicode.io/app/problem/661
     * leetcode: https://leetcode.com/problems/word-ladder/
     * find minimum step to transform begin word to end word within wordList
     * Time complexity: O(n * m) where n is length of beginword, and m is size of list
     * Space Complexity: O(m)
     * */
    public static class Pair<K, V> {
        K key;
        V value;

        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public V getValue() {
            return value;
        }

        public K getKey() {
            return key;
        }
    }
    public static int wordsLadderI(String beginWord, String endWord, List<String> wordList) {
       // all words are of same length
        int L = beginWord.length();
        //dictionary to hold combination of words that can be formed, from any given word. by changing one letter at a time
        Map<String, List<String>> allComboDict = new HashMap<>();
        wordList.forEach(
                word -> {
                    for (int i = 0; i < L; i++) {
                        //key is then generic word
                        // value is a list of words
                        String newWord = word.substring(0,i) + '*' + word.substring(i+1, L);
                        List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
                        transformations.add(word);
                        allComboDict.put(newWord, transformations);
                    }
                }
        );
        //Queue for BFS
        Queue<Pair<String, Integer>> Q = new LinkedList<>();
        Q.add(new Pair<String, Integer>(beginWord,1));
        //visited to make sure we don't repeat processing same word
        Map<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);

        while (!Q.isEmpty()) {
            Pair<String, Integer> node = Q.remove();
            String word = node.getKey();
            int level = node.getValue();
            for (int i = 0; i < L; i++) {
                String newWord = word.substring(0,i) + '*' + word.substring(i+1, L);

                for (String adjacentWord: allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                    if (adjacentWord.equals(endWord)) {
                        return level + 1;
                    }
                    if (!visited.containsKey(adjacentWord)) {
                        visited.put(adjacentWord, true);
                        Q.add(new Pair<String, Integer>(adjacentWord, level+1));
                    }
                }
            }
        }
        return 0;
    }

    //TODO
    //go through
    /**
     * Q1.3 word Ladder II
     * laioffer:https://app.laicode.io/app/problem/662
     * leetcode:https://leetcode.com/problems/word-ladder-ii/
     * find shortest path to transform begin word to end word within wordList
     * solution:https://leetcode.com/problems/word-ladder-ii/discuss/1211402/Java-Clean-BFS-%2B-DFS-Solution-(98-Two-Way-BFS)-oror-with-comments
     * time complexity:
     * space complexity:
     * */
    private Set<String> dict;
    private String beginWord;
    private String endWord;
    private Map<String, List<String>> parentsMap = new HashMap<>();
    private List<List<String>> paths = new ArrayList<>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList){
        this.dict = new HashSet<>(wordList);
        dict.add(beginWord);
        if (!dict.contains(endWord)) return paths;
        this.beginWord = beginWord;
        this.endWord = endWord;

        if (!buildMap()) return paths;

        List<String> curr = new ArrayList<>();
        curr.add(beginWord);
        collectAllPaths(beginWord, curr); // all paths
        return paths;
    }
    //DFS to get all paths from begin word to end word
    private void collectAllPaths(String s, List<String> curr) {
        if (s.equals(endWord)) {
            paths.add(new ArrayList<>(curr));
            return;
        }
        for (String next: parentsMap.get(s)) {
            curr.add(next);
            collectAllPaths(next, curr);
            curr.remove(curr.size() - 1);
        }
    }

    private boolean buildMap() {
        Set<String> backward = new HashSet<>();
        backward.add(endWord);
        Set<String> visited = new HashSet<>();
        boolean found = false;

        while (!backward.isEmpty() && !found) {
            Set<String> temp = new HashSet<>();
            for (String s: backward) {
                visited.add(s);
                for (String nb: getNext(s)) {
                    if (backward.contains(nb) || visited.contains(nb)) continue;
                    if (beginWord.equals(nb)) found = true;

                    temp.add(nb);
                    parentsMap.putIfAbsent(nb, new ArrayList<>());
                    parentsMap.get(nb).add(s);
                }
            }
            backward = temp;
        }
        return found;
    }
    private List<String> getNext(String s) {
        char[] arr = s.toCharArray();
        List<String> nbs = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            char ch = arr[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == ch) continue;
                arr[i] = c;
                String nb = new String(arr);
                if (dict.contains(nb)) nbs.add(nb);
            }
            arr[i] = ch;
        }

        return nbs;
    }

    /**
     * Q2.2
     * how to find the k-th smallest number in the f(x,y,z) = 3^x + 5^y + 7^z(int x,y,z>0)
     * */
    public static Long kth(int k) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();
        pq.offer(3L * 5 * 7);
        set.add(3L * 5 * 7);
        for (int i = 1; i < k; i++) {
            long val = pq.poll();
            long a = val * 3;
            long b = val * 5;
            long c = val * 7;
            if (!set.contains(a)) {
                pq.offer(a);
                set.add(a);
            }
            if (!set.contains(b)) {
                pq.offer(b);
                set.add(b);
            }
            if (!set.contains(c)) {
                pq.offer(c);
                set.add(c);
            }
        }
        return pq.peek();
    }

    /**
     * Q2.3
     * laioffer:https://app.laicode.io/app/problem/194
     * given three arrays with positive numbers in ascending order. pull one number from each array
     * to form a coordinate <x,y,z> in a 3D space, how to find the coordinate of the points
     * that is k-th closest to <0,0,0></0,0,0>
     * */
    public static List<Integer> closest(int[] a, int[] b, int[] c, int k) {
        PriorityQueue<List<Integer>> minHeap = new PriorityQueue<>(2 * k, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                long d1 = distance(o1, a, b, c);
                long d2 = distance(o2, a, b, c);
                if (d1 == d2) {
                    return 0;
                }
                return d1 < d2? -1: 1;
            }
        });
        Set<List<Integer>> visited = new HashSet<>();
        List<Integer> cur = Arrays.asList(0,0,0);
        visited.add(cur);
        minHeap.offer(cur);
        while (k > 0) {
            cur = minHeap.poll();
            List<Integer> n = Arrays.asList(cur.get(0)+1, cur.get(1), cur.get(2));
            if (n.get(0) < a.length && visited.add(n)) minHeap.offer(n);

            n = Arrays.asList(cur.get(0), cur.get(1) + 1, cur.get(2));
            if (n.get(1) < b.length && visited.add(n)) minHeap.offer(n);

            n = Arrays.asList(cur.get(0), cur.get(1), cur.get(2) + 1);
            if (n.get(2) < c.length && visited.add(n)) minHeap.offer(n);
            k--;
        }
        cur.set(0, a[cur.get(0)]);
        cur.set(1, b[cur.get(1)]);
        cur.set(2, c[cur.get(2)]);
        return cur;
    }
    private static long distance(List<Integer> point, int[] a, int[] b, int[] c) {
        long dis = 0;
        dis += a[point.get(0)] * a[point.get(0)];
        dis += b[point.get(1)] * b[point.get(1)];
        dis += c[point.get(2)] * c[point.get(2)];
        return dis;
    }

    /**
     * Q2.4
     * laioffer:https://app.laicode.io/app/problem/195
     * given a gym with k equipments and some obstacles. we bought a chair and want to put this chair into the gym
     * such that the sum of the shortest path cost from the chair to the k equipment is minimal
     * */
    public static List<Integer> putChair(char[][] gym) {
        // Write your solution here
        return null;
    }

    /**
     * Q3 Topological sort
     * Q3.1 Course schedule
     * laioffer:https://app.laicode.io/app/problem/430
     * leetcode:https://leetcode.com/problems/course-schedule/submissions/
     * given n courses and the prerequisites of each course, find a valid order to take all the courses
     * */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // the adjacency list representation of prerequisites
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] dependency: prerequisites) {
            int x = dependency[0];
            int y = dependency[1];
            graph.get(y).add(x);
        }
        return topologicalSort(graph);
    }
    private int[] topologicalSort(List<List<Integer>> graph) {
        int numCourses = graph.size();
        int[] topologicalOrder = new int[numCourses];
        int[] incomingEdges = new int[numCourses];
        for (int x = 0; x < numCourses; x++) {
            for (int y : graph.get(x)) {
                incomingEdges[y]++;
            }
        }
        //nodes with no incoming edges
        Queue<Integer> queue = new ArrayDeque<>();
        for (int x = 0; x < numCourses; x++) {
            if (incomingEdges[x] == 0) { // initial state with no dependency like 0
                queue.offer(x);
            }
        }
        int numExpanded = 0;
        while (!queue.isEmpty()) {
            int x = queue.poll();
            topologicalOrder[numExpanded] = x; //a++ or a-- is postfix operation. e.g:a = 4; b = a++; // first b will be 4, and after this a will be 5
            numExpanded++;// move to the next dependency course
            for (int y : graph.get(x)) {
                incomingEdges[y]--; // link y with other dependency with current course x, and reduce y edges
                if (incomingEdges[y] == 0) { //++a or --a is prefix operation. e.g:c = ++a; // first a will be 6, then 6 will be assigned to c
                    queue.offer(y); // once the course y's edges is zero, y can be next node in graph to link
                }
            }
        }
        return numExpanded == numCourses ? topologicalOrder: new int[] {};
    }
    //TODO
    /**
     * Q3.2
     * laioffer:https://app.laicode.io/app/problem/501
     * leetcode:https://leetcode.com/problems/alien-dictionary/submissions/
     * There is a new alien language which uses the latin alphabet.
     * However, the order among letters are unknown to you.
     * You receive a list of non-empty words from the dictionary,
     * where words are sorted lexicographically by the rules of this new language.
     * Derive the order of letters in this language.
     * */
    public String alienOrder(String[] words) {
        // Write your solution here
        if (words.length == 1) return words[0];
        HashMap<Character, HashSet<Character>> grid = new HashMap<>();
        //build the graph
        for (int i = 1; i < words.length; i++) {
            String w1 = words[i-1];
            String w2 = words[i];
            boolean diffFound = false;
            //build the graph with dependencies
            //read all characters for processing
            //if the characters are not already in the graph, add them
            //for the characters of the two consecutive string that do not match add a dependency in the graph
            for (int j = 0; j < Math.min(w1.length(), w2.length()); j++) {
                if (!diffFound && w1.charAt(j) != w2.charAt(j)) { // w1(j) != w2(j)
                    HashSet<Character> child = grid.getOrDefault(w1.charAt(j), new HashSet<>());
                    if (child == null) child = new HashSet<>();
                    child.add(w2.charAt(j));
                    grid.put(w1.charAt(j), child);
                    diffFound = true;
                }
                if (!grid.containsKey(w1.charAt(j))) { // w1(j) == w2(j)
                    grid.put(w1.charAt(j), null);
                }
                if (!grid.containsKey(w2.charAt(j))) { // w1(j) == w2(j)
                    grid.put(w2.charAt(j), null);
                }
            }
            // edge case - if all the chars are same and the first one is bigger,
            // then that is an exceptional condition.
            if (!diffFound && w1.length() > w2.length()) return "";
            String w = w1.length() > w2.length() ? w1 : w2;
            for (int j = Math.min(w1.length(), w2.length()); j < Math.max(w1.length(), w2.length()); j++) {
                if (!grid.containsKey(w.charAt(j))) {
                    grid.put(w.charAt(j), null);
                }
            }
        }
        //topological search
        LinkedList<Character> stack = new LinkedList<>();
        HashSet<Character> visited = new HashSet<>();
        for (char c: grid.keySet()) {
            if (visited.contains(c)) continue;
            HashSet<Character> currentPath = new HashSet<>();
            //if any time a cycle detected, an invalid scenario was found
            if (!dfs(stack, c, currentPath, visited, grid)) return "";
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());

        }
        return sb.toString();
    }
    // return false - if cycle found
    // populate stack with nodes processed
    private boolean dfs(LinkedList<Character> stack, Character current, HashSet<Character> currentPath,
                        HashSet<Character> visited, HashMap<Character,HashSet<Character>> grid )
    {
        if (currentPath.contains(current)) return false;
        if (visited.contains(current)) return true;

        if (grid.get(current) == null) {
            stack.push(current);
            visited.add(current);
            return true;
        }
        currentPath.add(current);
        for (char c: grid.get(current)) {
            if (!dfs(stack, c, currentPath, visited, grid)) return false;
        }
        stack.push(current);
        visited.add(current);
        currentPath.remove(current);
        return true;
    }
    public static void main(String[] args) {
        //Test Q1.1
        int[][] board = new int[][] {{5,4,1,0}, {2,6,3,7}};
        System.out.println("Minimum steps to slid puzzle is: " + slidingPuzzle(board));
        //Test Q1.2

    }
}
