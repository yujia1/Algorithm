package stackAndQueue;

public class QueueByArray<E> {
    private int head;
    private int tail;
    private E[] array;
    private int size;
    private int capacity;
    public QueueByArray(final int capacity) {
        this.capacity = capacity;
        array =  (E[]) new Object [capacity];
        head = 0;
        tail = capacity -1;
        size = 0;
    }
    public boolean offer(final E obj) {
        if (size < capacity) {
            size ++;
            tail = (tail + 1) % capacity;
            array[tail] = obj;
            return true;
        }
        return false;
    }
    public E poll() {
        if (size == 0) {
            return null;
        }
        E temp = array[head];
        array[head] = null;
        head = (head + 1) % capacity;
        size--;
        return temp;
    }
    public E peek() {
        if (size == 0) {
            return null;
        }
        return array[head];
    }
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
    public Integer size() {
        return size;
    }

    public static void main(String[] args) {
        QueueByArray<Integer> queue = new QueueByArray<>(5);
        System.out.println(queue.isEmpty());
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        System.out.println(queue.offer(6));
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.peek());
    }
}
