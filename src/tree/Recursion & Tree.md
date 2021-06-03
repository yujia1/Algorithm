# Recursion & Tree

Three steps of recursion of tree

1. what do you expect from left child or rught child?
2. what do you want to do in the current layer?
3. what do you want to report to your parent?



Two cases to explain:

Case 1: 

```java
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
```

The code 

```java
    currNumber = currNumber * 10 + root.value;
    if(root.left == null && root.right == null) {
        rootToLeaf += currNumber;
    }
```

 executed before call recursively function, it means we operate current layer or root when tree root from top to down. Meanwhile, cuz the functuon return void, we dont have to return anything.

Case 2:

```java
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
```

In the section, the code executed after recursion call:

```java
int max = 1;
if(root.left == null || root.left.value == root.value + 1) {
    max = Math.max(max, left + 1);
}
if(root.right== null || root.right.value == root.value + 1) {
    max = Math.max(max, right + 1);
}
res = Math.max(res, max);
return max;
```

We actually operate current layer or root when tree root reach bottom and return to previous layer or root.  

For this problem(binary-tree-longest-consecutive-sequence), e.g.

​							![Screen Shot 2021-06-03 at 4.10.50 PM](/Users/hangxigudeaoqi/IdeaProjects/Algorithm/src/tree/treeimage.png)



Let's say we want to know node 3's longest consecutive sequence, we have to know maximum longest consecutive seuqence from both left and right child of node 3 and add 1. Matematically, F(3) = Math.max( F(3.left) + F(3.right)) + 1, and break it down to node 2 (left child of node 3) and node 4(right child of node 4). Meanwhile, we also consider the condition that the node is consecutive. Thus, node 2 will not be considered. 