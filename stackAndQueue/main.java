package stackAndQueue;

import java.util.*;

public class main {
    /**
     * a
     * 将stack1 所有元素move to stack2，元素在stack2 顺序reverse，time compleixty 0（ n + n）
     * 将stack1 全部元素move to stack2，所有or部分元素move back stack1，则原来stack1元素顺序不变
     * amortized 的时间复杂度往往O（1）
     */
    //TODO: complete sort stacks Q1 and Q2
    // question: time complexity
    /**1
     * using three stacks to sort stack1
     * @param s1
     * @param res
     * @param buffer
     * @return
     */
    static Deque<Integer> sortStackWithThreeStacks(Deque<Integer> s1,Deque<Integer> res,Deque<Integer> buffer){
            while (!s1.isEmpty()) {
                int curMin = Integer.MAX_VALUE;
                // step 1: compare each element of s1 with global min, and store element in buffer,
                while (!s1.isEmpty()) {
                    int cur = s1.pollFirst();
                    if (cur < curMin) {
                        curMin = cur;
                    }
                    buffer.offerFirst(cur);
                }
                // step 2: put min to res, and put all element bigger than res.peek() back to s1, then reset global min,
                while (!buffer.isEmpty()) {
                    int temp = buffer.pollFirst();
                    if (temp == curMin) {
                        res.offerFirst(temp);
                    } else {
                        s1.offerFirst(temp);
                    }
                }
            }
            // step3: give back all elements in res to s1
            while (!res.isEmpty()) {
                s1.offerFirst(res.pollFirst());
            }
        //  s1   2  5   3   4   2
        // res   1  1                         | min = 1
        // buff
        return s1;
    }
    /** 2
     * using two stacks to sort stacks in ascending order
     * @param s1
     * @return
     */
    static void sortStackWithTwoStacks(Deque<Integer> s1,Deque<Integer> s2 ) {

        while (!s1.isEmpty()) {
            int curMin = Integer.MAX_VALUE;
            int count = 0; // check out how many repeated num;
            // step 1: move all s1 elements to s2, and record repeated numbers, and min
            while (!s1.isEmpty()) {
                int cur = s1.pollFirst();
                if (cur < curMin) {
                    curMin = cur;
                    count = 1;
                } else if (cur == curMin) {
                    count++;
                }
                s2.offerFirst(cur);
            }
            // step 2: move all elements s2 back to s1 except elements less than curMin
            while (!s2.isEmpty() && s2.peekFirst() >= curMin) { //之所以要等于是要所有的比这一轮的curmin删掉，因为不知道curmin在上s2位置
                int temp = s2.pollFirst();
                if (temp != curMin) {
                    s1.offerFirst(temp);
                }
            }
            // step3: put curMin and repeated number back to s2, which is ascending order from bottom to top
            while (count-- >0) {
                s2.offerFirst(curMin);
            }
        }
        // give s2 elements all back to s1, which lead to descending order from bottom to top
        while (!s2.isEmpty()) {
            s1.offerFirst(s2.pollFirst());
        }
        //  s1  2   1
        //  s2  -3  -3  -1  2   -1                               | min = -1, count= 2
    }
    /**
     * 3
     * using two stacks to sort stacks in descending order
     * @param s1
     * @param s2
     */
    static void sortStackMethod2(Deque<Integer> s1, Deque<Integer> s2) {
        int prevMax = Integer.MAX_VALUE;
        while (s1.peekFirst() < prevMax) {
            int curMax = Integer.MIN_VALUE;
            int count = 0;
            // step1: find max value in s1, and count repeated nums, store all elements to s2
            while (!s1.isEmpty() && s1.peekFirst() < prevMax) {
                int cur = s1.pollFirst();
                if (cur > curMax) {
                    curMax = cur;
                    count = 1;
                } else if (cur == curMax) {
                    count++;
                }
                s2.offerFirst(cur);
            }
            // store all max elements back to s1
            while (count-- >0) {
                s1.offerFirst(curMax);
            }
            // throw all element from s2 to s1, except elements equal max
            while (!s2.isEmpty()) {
                int temp = s2.pollFirst();
                if (temp != curMax) {
                    s1.offerFirst(temp);
                }
            }
            // up date prevMax
            prevMax = curMax;
        }
    }

    /**4
     *  merge sort stack using three stacks
     * @param s1
     */
    //TODO review this and pass test manually
    // do not understand
    static void mergeSortStackByThree(LinkedList<Integer> s1) {
        LinkedList<Integer> s2 = new LinkedList<>();
        LinkedList<Integer> s3 = new LinkedList<>();
        sort(s1, s2, s3, s1.size());
    }
    private static void sort(LinkedList<Integer> s1, LinkedList<Integer> s2, LinkedList<Integer>s3, int size) {
        if (size <= 1) {
                return;
        }
        int mid1 = size/2;
        int mid2 = size - size / 2;
        for (int i =0; i < mid1 ; i++) {
            s2.offerFirst(s1.pollFirst());
        }
        sort(s2, s3, s1, mid1);
        sort(s1,s3, s2, mid2);
        int i =0;
        int j =0;
        while ( i < mid1 && j < mid2) {
            if (s2.peekFirst() < s1.peekFirst()) {
                s3.offerFirst(s2.pollFirst());
                i++;
            } else {
                s3.offerFirst(s1.pollFirst());
                j++;
            }
        }
        while (i < mid1) {
            s3.offerFirst(s2.pollFirst());
            i++;
        }
        while (j < mid2) {
            s3.offerFirst(s1.pollFirst());
            j++;
        }
        for (int index = 0; index < size; index++) {
            s1.offerFirst(s3.pollFirst());
        }
    }

    public static void main(String[] args) {
        Deque<Integer> s1 = new LinkedList<>();
        Deque<Integer> s2 = new LinkedList<>();
        Deque<Integer> s3 = new LinkedList<>();
        s1.offerFirst(2);
        s1.offerFirst(1);
        s1.offerFirst(3);
//        s1.offerFirst(-1);
//        s1.offerFirst(-3);
//        s1.offerFirst(2);
//        s1.offerFirst(-1);
        System.out.println(s1.peekLast());
 //       sortStackWithTwoStacks(s1,s2);
 //       System.out.println(Arrays.toString(s1.toArray()));
 //       s1 = sortStackWithThreeStacks(s1,s2,s3);
//        System.out.println(Arrays.toString(s1.toArray()));
//        MinStack m = new MinStack(s1);
//        System.out.println(m.minLessSpace());
//        m.poll();
//        m.poll();
//        m.poll();
//        System.out.println(m.minLessSpace());
//        System.out.println(s1.pollLast());
//        Queue<Integer> q = new ArrayDeque<>();
    }
}
