package src.LiskedList;

public class ListNode {
    public int value;
    public ListNode next;
    public ListNode(int value){
        this.value = value;
    }
    public static void removeLast(ListNode head) {
        ListNode p = head;
        ListNode q = head.next;
        while (q.next != null) {
            p = q;
            q = q.next;
        }
        p.next = null;
    }

}
