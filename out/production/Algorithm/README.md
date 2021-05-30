# Algorithm
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

