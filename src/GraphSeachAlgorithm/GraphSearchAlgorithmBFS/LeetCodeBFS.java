package GraphSeachAlgorithm.GraphSearchAlgorithmBFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * BFS 是一层一层的traverse，DFS是一步步深入到edges，然后返回上一层继续深入
 */
public class LeetCodeBFS {
    /**
     * Q1
     * https://leetcode.com/problems/number-of-islands/
     * @param grid
     * @return
     */
    public final int[][] dirs = new int[][]{{-1,0}, {1,0}, {0,-1}, {0, 1}};
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int row = grid.length;
        int col = grid[0].length;
        int numIslands = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    BFSNumIslands(grid, i, j);
                    numIslands++;
                }
            }
        }
        return numIslands;
    }
    private void BFSNumIslands(char[][] grid, int row, int col) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{row, col});
        grid[row][col] = '2';//mark as visited;
        while (!q.isEmpty()) {
            int[] state = q.poll();
            int currR = state[0];
            int currC = state[1];
            for (int[] dir: dirs) {
                int nextRow = currR + dir[0];
                int nextCol = currC + dir[1];
                if (nextRow >=0 && nextRow < grid.length && nextCol >=0 && nextCol < grid[0].length && grid[nextRow][nextCol] == '1') {
                    q.offer(new int[] {nextRow, nextCol});
                    grid[nextRow][nextCol] = '2';
                }
            }
        }
    }

    /**
     * Q@
     * @param grid
     * @return
     */
    public int orangesRotting(int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 2) {
                    q.offer(new int[]{r,c}); // store all initially starting to rotten point
                }
            }
        }
        //rotting started
        int minutes = -1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int s =0; s < size; s++) {
                int[] state = q.poll();
                int r0 = state[0];
                int c0 = state[1];

                for (int[] dir: dirs) {
                    int r = r0 + dir[0];
                    int c = c0 + dir[1];
                    // check if it can move to neighbors
                    if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] == 1) {
                        q.offer(new int[]{r, c});
                        grid[r][c] = 2; //rotten
                    }
                }
            }
            minutes++; // 在run size of q的loop才能保证所有的rotten point 同时 expand 周围， 然后才能加时间
        }
        //check if all fresh orange rotten
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                   return -1;
                }
            }
        }
        if (minutes == -1 ) return 0;
        return minutes;
    }



    public static void main(String[] args) {

    }
}
