package stackAndQueue;


import LiskedList.ListNode;

public class StackByLinkedList {
    private ListNode head;
    public Integer pop() {
        if (head == null) {
            return null;
        }
        ListNode temp = head;
        head = head.next;
        temp.next = null;
        return temp.value;
    }
    public Integer peek() {
        if (head == null) {
            return null;
        }
        return head.value;
    }
    public void push(int value) {
        ListNode newHead = new ListNode(value);
        newHead.next = head;
        head = newHead;
    }

    public static void main(String[] args) {
        StackByLinkedList deque = new StackByLinkedList();
        deque.push(1);
        deque.push(2);
        System.out.println(deque.peek());
        System.out.println(deque.pop());
        System.out.println(deque.peek());
    }

    // head
}
