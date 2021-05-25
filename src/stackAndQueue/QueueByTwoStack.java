package stackAndQueue;

import java.net.Inet4Address;
import java.util.LinkedList;

public class QueueByTwoStack {
    private LinkedList<Integer> in;
    private LinkedList<Integer> out;
    public QueueByTwoStack(){
        in = new LinkedList<>();
        out = new LinkedList<>();
    }
    public void offer(int value) { // O(1)

        in.offerFirst(value);
    }

    public Integer poll() { // O(n), amortized time O(1)
        move();
        return out.isEmpty()? null : out.pollFirst();
    }
    private void move(){
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.offerFirst(in.pollFirst());
            }
        }
    }
    public Integer peek() {
        move();
        return out.isEmpty() ? null : out.peekFirst();
    }
    // 为什么返回值为int时候不能return null；？
    public Integer size() {
        return in.size() + out.size();
    }
    public boolean isEmpty() {
        return in.size() == 0 && out.size() ==0;
    }
}
