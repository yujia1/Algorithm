package stackAndQueue;
import src.LiskedList.ListNode;
public class QueueByLinkedList {
    private ListNode head;
    private ListNode tail;
    public QueueByLinkedList() {
        head = null;
        tail = null;
    }
    public void offer(int value) {
        //TODO 为什么是头进？再移动tail
        if (head == null) {
            head = new ListNode(value);
            tail = head;
        } else {
            tail.next = new ListNode(value);
            tail = tail.next;
        }
        //  null    1       2
        //          head
        //                  tail
    }
    public Integer poll() {
        if (head == null) {
            return null;
        }
        ListNode prev = head;
        head = head.next;
        //TODO left out one element
        if (head == null) { // left out one element, we have to move tail to null too
            tail = null;
        }
        prev.next = null;
        return prev.value;
    }
    public Integer peek() {
        if (head == null) return null;
        return head.value;
    }

    public static void main(String[] args) {
        QueueByLinkedList deque = new QueueByLinkedList();
        deque.offer(1);
        deque.offer(2);
        System.out.println(deque.peek());
        System.out.println(deque.poll());
        System.out.println(deque.peek());
    }

}
