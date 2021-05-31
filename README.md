# Algorithm
# Graph search

## BFS

case 1. rotting orange 

![Screen Shot 2021-05-31 at 11.37.37 AM](/Users/hangxigudeaoqi/Library/Application Support/typora-user-images/Screen Shot 2021-05-31 at 11.37.37 AM.png)

```java
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
```

**Solution**:

1. We find initial state, in this problem it's grid element equals to 2. It could be mutiply points. 

2. We use Queue to store and track those point, and expand it to four directions. 
3. Given the condition, we add new expanded point to Queue for next round. 
4. Update the result we need, in this problem, we need update time. But we have to be careful the result is based on every round of loop of queue, not single queue state. It depends on problem by problem. 

## DFS

int[] nums = new int[] {1,2,3}

case 1: subset

```java
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
```

![Screen Shot 2021-05-31 at 11.33.53 AM](/Users/hangxigudeaoqi/Library/Application Support/typora-user-images/Screen Shot 2021-05-31 at 11.33.53 AM.png)

As pic above seen, for (int i = index; i <nums.length; i++) is actually gradually reduce branches, dfsSubset(nums, i + 1, curr, res); is keep tree move to next state. At each node, it been added to res list. 



case 2: permutations 

```java
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
```

Tree map look like 

​										![Screen Shot 2021-05-31 at 11.29.57 AM](/Users/hangxigudeaoqi/Desktop/Screen Shot 2021-05-31 at 11.29.57 AM.png)



for (int i = 0; i < nums.length; i++) dfsPermute(nums, res, cur);is used for have three options for every layer. 

其中if (!cur.contains(nums[i])) is used for cutting branches of the tree. 


# dynamic programming summary

`https://docs.google.com/document/d/1r9PiZRMGYPTLX10fczPX2I9aGEsTCMmgmLOuOsjsLaM/edit#``

```java
DP 从小问题来解决大问题，记录sub-solution
由 size(<n)的sub-solution(s) -> size(n)的solution(s)
由 base case 以及 induction rule 组成
```



## DP I - 1-D DP problems

longest increasing sub-array, longest increasesing sub-sequence

MaximumProductOfRope, cutDictionary, cutPalindrome(combined with 2d dp), jumpGame



## DPII - 2-D DP problems

EditDIstance: to return minimum number of actions to transform string word1 to string word2; 

MaximumOneSquare: the edge of the largest square of 1's;

Minimum Jump(optimal solution is not dp);



## DP III

#### problems 1's matrix

LongestSubarray with maximum length if arrray contiguous 1s; 

LongestCrossOne: associated with longestSubarray with 1's;

LargestSquare: associated with longestSubarray with 1's

#### Problems prefix sum

LargestSumSubarray: prefix sum;

LargestSumWithRange: largest prefix sum to get two indexes ;

LargestSubMatrixSum: associated with sum subarray;

LargestSubMatrixSumNoLargerThanK:  associated with sum subarray;



## DP IV

CountAscendingSubsequence;

Pairs with positive slope;

LongestCommonSubString(2D) with condition that letters are consecutive;

LongestCommonSubSequence(2D) with condition that letters are not necessarily consecutive;

OptimalGame(pick pizza): 填表

