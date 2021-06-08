package src.sortAlgorithm;

import LiskedList.ListNode;

import java.util.Arrays;
import java.util.Random;



public class sortAlgorithm {
    public static Random random = new Random();
    //TODO review this
    // question is about complexity
    /** 4
     * quick select
     * find the smallest k integer in an unsorted array with size n
     * TC avg:O (n), worse O(n^2); SC: O(1);
     */
    public static void quickSelect(int[] input, int k, int left, int right) {
        // base case
        if (input == null || k == 0) {
            return;
        }
        int pivot = left + random.nextInt(right - left + 1);
        swap(input, pivot, right);
        int L = left;
        int R = right - 1;
        while (L <= R) {
            if (input[L] < input[right]) {
                L++;
            } else {
                swap(input, L, R);
                R--;
            }
        }
        swap(input, L, right);

        if ((L - left + 1) == k) {
            return;
        } else if (L - left + 1 < k) {
            k = k - (L-left + 1);
            left = L + 1;
            quickSelect(input,k, left, right);
        } else {
            right = L - 1;
            quickSelect(input, k, left, right);
        }
    }
    /** 3
     * given an array, to turn out to be PNPNPNPNNNN or PNPNPNPNPNPPPPP
      */
    public static void zebra(int[] array) {
        int i = 0, j = array.length - 1;
        while (i <= j) {
            if (array[i] >0) {
                i++;
            } else {
                swap(array, i, j);
                j--;
            }
        }
        i = 1;
        j = array.length -1;
        while (array[i] > 0) {
            if (array[i] >0) {
                swap(array, i, j);
                i =+2;
                j--;
            } else {
                i =+2;
            }
        }
    }
    //TODO review this
    // question is about complexity
    // it does not understand
    /**2
     * n ge color to rainbow
     */
    public static int[] nthRainbow(int[] array, int k) {
        if (array == null || array.length == 0){
            return array;
        }
        int[] pointers = new int[k+1];
        pointers[k] = array.length - 1;
        while(pointers[k-1] <= pointers[k]){
            int cur = array[pointers[k-1]];
            if(cur < k-1){
                for(int i = k-1; i > cur; i--){
                    swap(array,pointers[i]++,pointers[i-1]);
                }
                pointers[cur]++;
            } else if (cur == k-1){
                pointers[k-1]++;
            } else if (cur == k){
                swap(array,pointers[k-1],pointers[k]--);
            }
        }
        return array;

    }
    //TODO review this
    /** 1
     * four color to rainbow
     * @param array
     * @return
     */
    public static void rainbowMultiplySort(int[] array) {
        int i = 0, j = 0, k = 0, t = array.length - 1;
        while (k <= t) {
            if (array[k] == 1) { // [0, i)
                swap(array, k, j);
                swap(array,j, i);
                i++;
                j++;
                k++;
            } else if (array[k] == 2) { //[i, j)
                swap(array, k, j);
                j++;
                k++;
            } else if (array[k] == 3) { //[j, k)
                k++;
            } else {
                swap(array, k, t);
                t--;
            }
        }
        // AaaaaaxxxaBbbbxxxbbccccxxxxxddddd
        //  i        j       k       t
    }
    /**
     * to sort and merge two sorted ListNode
     * @param one
     * @param two
     * @return
     */
    public static ListNode merge(ListNode one, ListNode two) {
        ListNode cur = new ListNode(-1);
        ListNode head = cur;
        while (one != null && two != null) {
            if (one.value < two.value) {
                head.value = one.value;
                one = one.next;
            } else {
                head.value = two.value;
                two = two.next;
            }
            head = head.next;
        }
        while (one != null) {
            head.value = one.value;
            one = one.next;
            head = head.next;
        }
        while (two != null) {
            head.value = two.value;
            two = two.next;
            head = head.next;
        }
        return cur;
        //      1    3   5       2   4   6
        //      one
        //                      two
        // cur
    }
    /**
     * Rainbow sort
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public static void rainbowSort(int[] array) {
        // sanity check
        if (array == null || array.length <= 1) {
            return;
        }
        int i = 0;
        int j = 0;
        int k = array.length-1;
        while (j <= k) { // our search area is [j,k]
            if (array[j] == 1) {
                //case 1
                swap(array, i, j); // [0, i) put all elements = 1 on left side of i
                j++;
                i++;
            } else if(array[j] == 2) {
                //case 2
                j++; // [i,j),  put all elements = 2 on left side of j
            } else {
                // case 3
                swap(array, j, k);
                k--; // [j,k), put all elements =3  on left side of k
            }
        }
        // [ 2, 3, 1, 2, 1, 3]
        //   i
        //          j
        //                  k
        //[0,i) > 1
        //[i,j) > 2
        //[j,k] > to be sorted
        //(k, n-1) > 3

    }
    /**
     * QuickSort
     * time complexity: average O(nlogn) worse case O(n^2)
     * space complexity: average O(logn) worse case O(n)
     */
    public static void QuickSort(int[] array) {
        // sanity check
        if (array == null || array.length <=1) {
            return;
        }
        QuickSort(array, 0, array.length - 1);
    }
    private static void QuickSort(int[] array, int left, int right) {
        // base case
        if (left >= right) {
            return;
        }
        // pivot index // random.nextInt[0,num)
        int pivotIndex = left + sortAlgorithm.random.nextInt(right-left+1);
        // swap
        swap(array, pivotIndex, right);

        //partition
        int i = left;
        int j = right -1 ;
        while (i <= j) {
            if (array[i] < array[right]) {
                i++;
            } else {
                swap(array,i, j);
                j--;
            }
        }
        // i > j;
        // swap back pivot place
        // which make all value before i is less than value of pivot 
        swap(array, right, i);
        // recursion
        QuickSort(array, left, i -1);
        QuickSort(array, i+ 1, right);
    }
    /**
     * selection sort
     * time complexity: worst case:O(n^2), space complexity: O(1)
     * @param arr
     * @output sorted array
     */
    public static void selectionSort(int[] arr) {
        // corner case
        if(arr == null || arr.length <=1 ) {
            return ;
        }
        for (int i = 0; i < arr.length -1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]){
                    minIndex = j;
                }
            }
            swap(arr,minIndex,i);
        }
    }
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    /**
     * merge sort
     * time complexity: split: O(n);  merge O(n) * O(log n) = O(n log n)
     * space complexity: stack(log n), heap( n): max O(n), but we assumed that GC happens on time
     * @param arr
     */
    public static int[] mergeSort(int[] arr) {
        // corner case
        if (arr == null || arr.length <= 1) {
            return arr;
        }
        return mergeSort(arr, 0, arr.length -1);
    }
    private static int[] mergeSort(int[] arr, int left, int right) {
        // base case
        if (left == right) {
            return new int[]{arr[right]};
        }
        //sub-problem and solution
        int mid = left + (right -left) /2;
        int[] leftResult = mergeSort(arr,left, mid);
        // System.out.println(Arrays.toString(leftResult));
        int[] rightResult = mergeSort(arr, mid+1, right);
        // System.out.println(Arrays.toString(rightResult));

        return merge(leftResult, rightResult);
    }
    private static int[] merge(int[] leftResult, int[] rightResult) {
        int i =0, j = 0, k = 0;
        int n = leftResult.length + rightResult.length;
        int[] result = new int[n];
        while (i < leftResult.length && j < rightResult.length) {
            if (leftResult[i] < rightResult[j]) {
                result[k] = leftResult[i];
                i++;
            } else {
                result[k] = rightResult[j];
                j++;
            }
            k++;
        }
        // dealing with remaining elements in left or right
        while (i < leftResult.length) {
            result[k] = leftResult[i];
            i++;
            k++;
        }
        while ( j < rightResult.length) {
            result[k] = rightResult[j];
            j++;
            k++;
        }
        // System.out.println(Arrays.toString(leftResult));
        // System.out.println(Arrays.toString(rightResult));
        return result;
    }

    /**
     * merge sort better method
     * optimization 严老师公用一个new array
     * @param array
     * TC: O(nLogn)
     * SC: O(n)
     */
    public static int[] mergeSortBetter(int[] array) {
        // check null first
        if (array == null) {
            return array;
        }
        // allocate helper array to help merge step
        // so that we guarantee no more than O(n) space is used
        // the space complexity is O(n) in this case
        int[] helper = new int[array.length];
        mergeSortBetter(array, helper, 0, array.length);
        return array;
    }
    private static void mergeSortBetter(int[] array, int[] helper, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) /2;
        mergeSortBetter(array, helper, left, mid);
        mergeSortBetter(array, helper, mid + 1, right);
        mergeBetter(array, helper, left, mid ,right);
    }
    private static void mergeBetter(int[] array, int[] helper, int left, int mid, int right) {
        // cope the content to helper array and we will merge from the helper array
        for (int i = left; i <= right ;i++) {
            helper[i] = array[i];
        }
        int leftIndex =left;
        int rightIndex = mid + 1;
        while (leftIndex <= mid && rightIndex <= right) {
            if (helper[leftIndex] <= helper[rightIndex]) {
                array[left++] = helper[leftIndex];
            } else {
                array[left++] = helper[rightIndex];
            }
        }
        // if we still have some element at left side
        while (leftIndex <= mid) {
            array[left++] = helper[leftIndex++];
        }
        // we do not need to care about right side, cuz they are at their position
    }

    /**
     * partition different elements
     * @param array
     * @param pivotIndex
     */
    public static void partition(int[] array, int pivotIndex) {
        int left = 0;
        int right = array.length - 1;
        int i = left;
        int j = right - 1;
        swap(array,pivotIndex,right);
        while (i<=j) {
            if (array[i] < array[right]) {
                i++;
            } else {
                swap(array, i , j);
                j--;
            }
        }
        swap(array, i, right);
    }

    public static void main(String[] args) {
        int[] arr1 = {-1,1,1,1,-1,-1,1,-1};
        zebra(arr1);
        System.out.println(Arrays.toString(arr1));
    }

}
