package LiskedList;

public class main {
    public static void print(ListNode head){
        ListNode cur = head;
        while (cur != null){
            System.out.print(cur.value + "  ");
            cur = cur.next;
        }
    }
    /**
     * @input: ListNode
     * @output: integer length of ListNode
     */
    public static int count(ListNode head) {
        // corner case
        if(head == null) {
            return -1;
        }
        int n = 0;
        ListNode curr = head;

        while(curr != null) {
            n++;
            curr = curr.next;
        }
        return n;
    }
    /**
     * @input parameters: ListNode, target
    @ @output: ListNode
     */
    public static ListNode find(ListNode head, int k) {
        //corner case
        if(head == null || k < 0) {
            return null;
        }
        ListNode cur = head;
        while(cur != null && k > 0){
            cur = cur.next;
            k--;

        }
        return cur;

    }

    // basic question
    /** 1
     * reverse ListNode
     * @param head
     */
    public static ListNode reverse(ListNode head) {
        // corner case
        if (head == null) {
            return head;
        }
        ListNode prev = null, cur = head, next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
    /** 2
     * recursion to reverse ListNode
     * @param head
     */
    public static ListNode reverseReursion(ListNode head) {
        // corner case
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newListNode = reverseReursion(head.next);
        head.next.next = head;
        head.next = null;
        return newListNode;
        // 1    ->2     ->3     -> 4 ->      null
        //                       newListnode = n4( bottom level)
        //               head                    // 4->3 -> null
        //      head
    }
    //TODO complete this
    /** 3 recursion reversely swap two node
     * recursion skip two step
     */
    public static ListNode swapLinkList(ListNode head){
        if (head == null || head.next ==null) {
            return head;
        }
        ListNode subHead= swapLinkList(head.next.next);
        head.next.next = head;
        ListNode newHead = head.next;
        head.next = subHead;
        return newHead;
        //      1    2   3   4   5
        //                        5(subhead) return to head = 3
        //               4    3    newHead = 4 -> 3 -> null
        //      2    1              head = 1; newHead = 2-> 1- > subHead, which is equal to newHead因为上一轮的subhead = 返回值newHead;
    }
    //TODO complete this
    //  Question is method does not work.
    /**
     * using recursion way to reverse every k node
     * reverse k node
     */
    public static ListNode reverseByKNode(ListNode head, int k) {
      //    1   2   3   4   5   null    k = 3
        //
        //  3   2   1   4   5
        //
        // step1: count first k node
        // step2: recursion- reverse(node k + 1)
        // step3: reverse th first k node
        // step4: concatenate the tail with the subhead
        int count = 0;
        ListNode cur = head;
        while (count < k -1 && cur!= null) {
            cur = cur.next;
            count++;
        }
        if (cur == null) return head;
        ListNode subHead = reverseByKNode(cur.next, k); // step 2
        cur.next = null;
        ListNode newHead = reverse(head);
        head.next = subHead;
        return newHead;
    }
    // 举一反二
    /** 1 find mid note of ListNode
     * @input: ListNode
     * @output: middle node of ListNode
     */
    public static ListNode findMiddle(ListNode head) {
        // corner case
        if(head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next !=null) { // odd, even
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    /** 2 check cycle
     * check cycle
     * @param head
     */
    public static boolean cycle(ListNode head) {
        // sanity check
        if(head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
            if(slow == fast) {
                return true;
            }
            fast = fast.next;
        }
        return false;
    }
    /** 3 insert node
     * input parameters: ListNode head and integer target
     * output: updated ListNode
     */
    public static ListNode InsertNode(ListNode head, int target) {
        ListNode newNode = new ListNode(target);
        // corner case
        // first situation target is less than head.value
        if(head == null || target <= head.value) {
            newNode.next = head;
            return newNode;
        }
        // second situation head < target
        ListNode cur = head;
        while(cur.next != null && cur.next.value < target) {
            cur = cur.next;
        }
        newNode.next = cur.next;
        cur.next = newNode;
        return head;
    }
    /** 4 merge two sorted ListNodes
     * input parameters: two sorted ListNodes
     * output: one sorted ListNode
     */
    public static ListNode mergerTwoSortListNode(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while(head1 != null && head2 != null) {
            if(head1.value <= head2.value) {
                curr.next = head1;
                head1 = head1.next;
            } else {
                curr.next = head2;
                head2 = head2.next;
            }
            curr = curr.next;
        }
        // link the remaining possible nodes
        if( head1 != null ) {
            curr.next = head1;
        } else {
            curr.next = head2;
        }

        return dummy.next;
    }
    //TODO complete this
    // Question to be asked is about sequence of merge and post-processing
    // Check and compare to standard answer
    public static ListNode headToTail(ListNode head) {
        // sanity check
        if (head == null || head.next == null) {
            return head;
        }
        // find middle node and split into halves
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode post = slow.next;
        slow.next = null;
        ListNode prev = head;
        // reverse postNode
        ListNode postReverse = reverse(post);
        // merge into
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (postReverse != null) {
            cur.next = prev;
            cur = cur.next;
            prev = prev.next;
            cur.next = postReverse;
            cur = cur.next;
            postReverse = postReverse.next;
        }
        // post-processing aftermath
        if (prev != null) {
            cur.next = prev;
        }
        return dummy.next;
    }
    /** 6 partition ListNode
     * partition listNode
     * @param head, target
     *
     */
    public static ListNode partition(ListNode head, int target) {
        ListNode small = new ListNode(0);
        ListNode large = new ListNode(-1);
        ListNode smallCur = small;
        ListNode largeCur = large;
        while(head != null) {
            if(head.value < target) {
                smallCur.next = head;
                smallCur = smallCur.next;
            } else {
                largeCur.next = head;
                largeCur = largeCur.next;
            }
            head = head.next;
        }
        smallCur.next = large.next;
        largeCur.next = null; // 断开原来的LinkNode
        return small.next;
    }
    // 举一反三
    //TODO complete this
    // Question to be asked is about how to split listNode
    /** 1 merge sort a random Linked List
     * merge sort a random listNode
     * @param head
     */
    public static ListNode mergeSortLinkedList(ListNode head) {
        // base case
        if (head == null || head.next == null) {
            return head;
        }
        // split
        ListNode middle = findMiddle(head);
        ListNode middleNext = middle.next;
        middle.next = null;
        // sort each half
        ListNode left = mergeSortLinkedList(head);
        ListNode right = mergeSortLinkedList(middleNext);
        // merge
        return merge(left, right);
    }
    private static ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(-1);
        ListNode res = dummy;
        while (left != null && right != null) {
            if (left.value < right.value) {
                res.next = left;
                left = left.next;
            } else {
                res.next = right;
                right = right.next;
            }
            res = res.next;
        }
        if (left != null) {
            res.next = left;
        }
        if (right != null) {
            res.next = right;
        }
        return dummy.next;
    }
    //TODO complete this
    // Question to be asked is what is best algorithm
    // check and compare to standard answer, and other students
    /** 2
     * sum up twp listNodes
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode sumTwoListNode(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode dummyHead = new ListNode(0);
        ListNode p =l1;
        ListNode q = l2;
        ListNode curr = dummyHead;
        while(p != null || q != null){
            int x = (p != null) ? p.value : 0;
            int y = (q != null) ? q.value : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if(p != null) p = p.next;
            if(q != null) q = q.next;
        }
        // post-processing, in the end
        if(carry > 0){
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
        // (2-> 4-> 3) + (5->6->4)
        // 7-> 0 -> 8
    }
    // TODO complete this
    /** 3
     * check if is palindrome
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode middle = findMiddle(head);
        ListNode right = reverse(middle.next);
        while (right != null) {
            if (head.value != right.value) {
                return false;
            }
            head = head.next;
            right = right.next;
        }
        return true;
        // 1    2   3   2   1
        // find middle node and split into halves

        // compare two halves
    }
    /** 4
     * remove ListNode
     * @param head, val
     */
    public static ListNode remove(ListNode head, int val) {
        // corner case
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = head;
        while (cur != null){
            if (cur.value == val) {
                prev.next = cur.next;
            } else {
                prev = prev.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    // 午间课堂
    public static ListNode reverseByK(ListNode head, int k) {
        ListNode cur = head;
        int count = 0;
        while (count < k -1 && cur !=null) {
            cur = cur.next;
            count++;
        }
        if (count < k -1) return head;
        ListNode subHead = reverseByKNode(cur.next, k);
        cur.next = null;
        ListNode newHead = reverse(head);
        head.next = subHead;
        return newHead;
    }

    public static void main(String[] args) {
//        ListNode l1 = new ListNode(1);
//        ListNode l2 = new ListNode(2);
//        ListNode l3 = new ListNode(3);
//        l1.next = l2;
//        l2.next = l3;
//
//        System.out.println("Length of ListNode " + count(l1));
//
//        System.out.println("K-element is "+find(l1,1).value);
//
//        ListNode l4 = new ListNode(6);
//        ListNode l5 = new ListNode(7);
//        l3.next = l4;
//        l4.next = l5;
//        System.out.println("The middle node of ListNode'value is "+findMiddle(l1).value);
//        System.out.print("The insert new target into ListNode aftermath is ");
//        print( InsertNode(l1,9));
//        System.out.println();
//        // two sorted listNode l1 and l6, in order to merge two Listnodes
//        ListNode l6 = new ListNode(5);
//        ListNode l7 = new ListNode(8);
//        ListNode l8 = new ListNode(10);
//        l6.next = l7;
//        l7.next = l8;
//        System.out.print("The new ListNode after sorted two sorted ListNodes aftermath is ");
//        print(mergerSort(l1,l6));
//        System.out.println();
//        // queue FIFO
//        Queue<Integer> queue = new LinkedList<>();
//        queue.offer(1);
//        queue.offer(2);
//        queue.offer(3);
//        System.out.println("The elements in queue are "+queue);
//        System.out.println("The head of queue is " + queue.peek());
//        System.out.println("After we poll head "+  queue.poll() + " residual elements are: "+ queue + ", the size of queue is " + queue.size() + ", Is queue empty " + queue.isEmpty());
//        // stack LILO
//        Deque<Integer> stack = new LinkedList<>();
//        stack.push(9);
//        stack.push(8);
//        stack.push(7);
//        System.out.println("The elements in stack are " + stack);
//        System.out.println("The head of stack is " + stack.peek());
//        System.out.println("After stack pop "+ stack.pop() + ", the elements remaining stack are " + stack +", the size of stack is " + stack.size() + ", is the stack empty " + stack.isEmpty());

//    ListNode n1 = new ListNode(1);
//    ListNode n2 = new ListNode(3);
////    ListNode n3 = new ListNode(5);
////    ListNode n4 = new ListNode(7);
//    n1.next = n2;
//    n2.next = n3;
//    n3.next = n4;
//    print(n1);
//    System.out.println("after");
//    print(reverseReursion(n1));
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;

        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(4);
        l3.next = l4;
        l4.next = l5;
 //       l5.next = l6;

        print(l1);
        System.out.println();
        ListNode res = reverseByK(l1, 2);
        print(res);

    }
}
