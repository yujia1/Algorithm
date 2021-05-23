package stackAndQueue;

public class StackByArray<E> {

    private int head;
    private E[] array;
    private int capacity;
    public StackByArray(final int capacity){
        this.head = -1;
        this.capacity = capacity;
        this.array = (E[]) new Object[capacity];
    }
    public boolean push(E obj) {
        if (head == array.length - 1) {
            return false;
        }
        array[++head] = obj;
        return true;
    }
    public E pop() {
        return head == -1 ? null : array[head--];
    }
    public E peek() {
        return head == -1 ? null : array[head];
    }


    public static void main(String[] args) {
        StackByArray<Integer> deque = new StackByArray<>(5);
        deque.push(1);
        deque.push(2);
        deque.push(3);
        deque.push(4);
        System.out.println(deque.pop());
    }
}
