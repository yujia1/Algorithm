package binarySearchLecture;

public class binarySearchTest {
    /**
     * 1
     * @param arr
     * @param target
     * @return
     */
    public static int binarySearch(int[] arr, int target){
        if (arr.length == 0 || arr == null) return -1;
        int left = 0;
        int right = arr.length - 1;
        while (left <= right){
            int mid = (left + right) / 2;
            if (arr[mid] == target){
                return mid;
            }else if (arr[mid] < target){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 2
     * @param arr
     * @param target
     * @return
     */
    public static int[] binarySearch2D(int[][] arr, int target){
        int[] res = {-1,-1};
        if (arr.length ==0 || arr == null) return res;
        int left = 0;
        int right = arr[0].length * arr.length - 1;
        while (left <= right){
            int mid = (left + right) / 2;
            int row = mid / arr[0].length;
            int col = mid % arr[0].length;
            if (arr[row][col] == target){
                res[0] = row;
                res[1] = col;
                return res;
            }else if(arr[row][col] < target){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return res;
    }

    /**
     * 3
     * @param arr
     * @param target
     * @return
     */
    public static int closest(int[] arr, int target){
        if (arr.length == 0 || arr == null) return -1;
        if (arr[0] > target) return 0;
        if (arr[arr.length-1] < target) return arr.length-1;
        int left = 0;
        int right = arr.length - 1;
        while (left < right - 1){
            int mid = (left + right) / 2;
            if (arr[mid] == target){
                return mid;
            }else if(arr[mid] < target){
                left = mid;
            }else {
                right = mid;
            }
        }

        if (Math.abs(arr[left] - target) <= Math.abs(arr[right] - target)){
            return left;
        }else{
            return right;
        }
    }

    /**
     * 4
     * @param arr
     * @param target
     * @return
     */
    public static int firstOccurrence(int[] arr, int target){
        if (arr == null || arr.length == 0) return -1;
        if (target > arr.length -1) return -1;
        int left = 0;
        int right = arr.length - 1;
        while (left < right - 1){
            int mid = (left + right) / 2;
            if (arr[mid] <= target){
                left = mid;
            }else {
                right = mid;
            }
        }
        if (arr[left] == target){
            return left;
        }
        if (arr[right] == target){
            return right;
        }
        return -1;
    }

    /**
     * 5
     * @param arr
     * @param target
     * @return
     */
    public static int lastOccurrence(int[] arr, int target){
        if(arr == null || arr.length == 0) return -1;
        if (target> arr[arr.length - 1]) return -1;
        int left = 0;
        int right = arr.length - 1;
        while (left < right - 1){
            int mid = (left + right) / 2;
            if (arr[mid] >= target){
                right = mid;
            }else {
                left = mid;
            }
        }
        if (arr[right] == target){
            return right;
        }
        if (arr[left] == target){
            return left;
        }
        return -1;
    }

    /**
     * 6
     * @param array
     * @param target
     * @param k
     * @return
     */
    public int[] kClosest(int[] array, int target, int k) {
        // Write your solution here
        int left = leftLargestSmaller(array, target);
        int right = left + 1;
        int[] res = new int[k];
        for(int i = 0; i < k; i++){
            if(right >= array.length || left >= 0 && target - array[left] <= array[right] - target){

                res[i] = array[left--];

            }else{
                res[i] = array[right++];
            }
        }
        return res;
    }

    private int leftLargestSmaller(int[] arr, int target){
        int left = 0;
        int right = arr.length - 1;
        while(left < right - 1){
            int mid = (left + right) / 2;
            if(arr[mid] <= target){
                left = mid;
            }else{
                right = mid;
            }
        }
        if(arr[left] <= target){
            return left;
        }
        if(arr[right] <= target){
            return right;
        }
        return -1;

    }

    /**
     * 7
     * @param array
     * @param target
     * @return
     */
    public static int smallestElementLargerThanTarget(int[] array, int target){
        if (array.length == 0 || array == null) return -1;
        int left = 0;
        int right = array.length -1;
        while (left < right - 1){
            int mid = (left + right) / 2;
            if (array[mid] <= target){
                left = mid + 1; // does it matter to mid + 1? is it associated with order of post-processing?
                /**
                 * if left = mid; the order of post-processing doesn't matter?
                 * if left = mid+ 1; the order of post-processing matter.
                 */
            }else {
                right = mid;
            }
        }
        // have to check left first, and then right.
        if (array[left] > target){
            return left;
        }
        if (array[right] > target){
            return right;
        }

        return -1;
    }

    /**
     * 8 不会
     * k-th smallest in two sorted array
     * @param args
     */
//    public static int kthSmallestInTwoSortArrays(int[] a, int aLeft, int[] b, int bLeft, int k){
//        // base case
//        if (aLeft >= a.length) {
//            return b[bLeft + k - 1];
//        }
//        if (bLeft >= b.length) {
//            return a[aLeft + k - 1];
//        }
//        if (k == 1) {
//            return Math.max(a[aLeft, b[bLeft]);
//        }
//        int amid = aLeft + k / 2 - 1;
//        int bmid = bLeft + k / 2 - 1;
//
//        int aval = amid >= a.length? Integer.MAX_VALUE : a[amid];
//        int bval = bmid >= b.length? Integer.MAX_VALUE : b[bmid];
//
//        if (aval <= bval) {
//            return kthSmallestInTwoSortArrays(a, amid + 1, b, bLeft, k - k/2);
//        } else {
//            return kthSmallestInTwoSortArrays(a, aLeft,b,bmid + 1, k- k /2);
//        }
//    }
    // 1,  / 3, / 5,  7
    // 2,  / 4, / 6,  9
    // k = 2
    //

    /**
     * 9
     * k-th closest element
     * @param args
     */

    /**
     * 10
     * binary search with unknown size, on laicode
     * @param args
     */

    public static void main(String[] args) {
        int[] arr = new int[]{1,4,8,10};
        int res = smallestElementLargerThanTarget(arr,6);
        int x = 5;
        int y = 6;
        System.out.println(x==y);
    }
}
