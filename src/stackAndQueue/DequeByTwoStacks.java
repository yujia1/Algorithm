package stackAndQueue;

import java.util.Deque;
import java.util.LinkedList;

public class DequeByTwoStacks <E> {
    private Deque<E> leftStack;
    private Deque<E> rightStack;
    public DequeByTwoStacks() {
        this.leftStack = new LinkedList<>();
        this.leftStack = new LinkedList<>();
    }
    public Integer size() {
        return leftStack.size() + rightStack.size();
    }
    public void leftAdd(E input) {
        leftStack.offerFirst(input);
    }
    public void rightAdd(E input) {
        rightStack.offerFirst(input);
    }

    /** in order to left move, condition: left empty
     * the steps:
     *  1. move 1/2 element from right stack to buffer ( reverse order)
     *  2. move 1/2 left out element from right stack to left stack ( reverse order, but for left stack is in sequence to linked with stack2
     *  3. move all elements from buffer to right stacks
     *  4. pop out left stack elements;
     *   amortize TC: O(1) because expectation is first step is O(3n + 1), other steps are O(1);
     * @return
     */
    public E leftRemove() { // TC: O(n)
        if (!leftStack.isEmpty()) {
            return leftStack.pollFirst();
        } else {
            Deque<E> buffer = new LinkedList<>();
            int n = rightStack.size();
            int index = 1;
            // 把1/2上尾巴的rightstack 给buffer
            while (index <= n/2) {
                buffer.offerFirst(rightStack.pollFirst());
                index++;
            }
            // 把剩余1/2 给 leftstack
            while (!rightStack.isEmpty()) {
                leftStack.offerFirst(rightStack.pollFirst());
            }
            // throw elements from buffer to rightstack
            while (!buffer.isEmpty()) {
                rightStack.offerFirst(buffer.pollFirst());
            }
        }
        return leftStack.pollFirst();
    }
    public E rightRemove() {
        if (!rightStack.isEmpty()) {
            return rightStack.pollFirst();
        } else {
            Deque<E> buffer = new LinkedList<>();
            int n = leftStack.size();
            int index = 1;
            // move 1/2 尾巴上的 leftStack to buffer
            while (index <= n/2) {
                buffer.offerFirst(leftStack.pollFirst());
                index++;
            }
            // move left out elements from leftStack to rightStack
            while (!leftStack.isEmpty()) {
                rightStack.offerFirst(leftStack.pollFirst());
            }
            // throw back all elements from buffer to leftStack
            while (!buffer.isEmpty()) {
                leftStack.offerFirst(buffer.pollFirst());
            }
        }
        return rightStack.pollFirst();
    }

}
