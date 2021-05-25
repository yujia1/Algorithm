package stackAndQueue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.*;
public class MinStack {
    private Deque<Integer> input;
    private Deque<Integer> minStack;
    public MinStack(Deque<Integer> input){
        this.input = input;
        this.minStack = new LinkedList<>();
    }
    // 同加同减
    //TODO review min using two stacks
    public Integer min() {
        if (input.isEmpty()) {
            return null;
        }
        int globalMin = Integer.MAX_VALUE;
        Deque<Integer> buff = new LinkedList<>();
        while (!input.isEmpty()) {
            int cur = input.poll();
            if (cur < globalMin) {
                globalMin = cur;
            }
            buff.offerFirst(cur);
            minStack.offerFirst(globalMin);
        }
        while (!buff.isEmpty()) {
            this.input.offerFirst(buff.pollFirst());
        }
        return minStack.peekFirst();
    }
    public Integer poll() {
        return input.pollFirst();
    }
    public Integer minLessSpace() {
        int globalMin = Integer.MAX_VALUE;
        Deque<int[]> pair = new LinkedList<>();
        int index = 1;
        while (!input.isEmpty()) {
            int cur = input.pollFirst();
            if (cur < globalMin) {
                globalMin = cur;
                int[] pairs = new int[]{globalMin, index};
                pair.offerFirst(pairs);
            }
            index++;
            // throw it to minStack;
            minStack.offerFirst(cur);
        }
        // throw it back to input
        while (!minStack.isEmpty()) {
            input.offerFirst(minStack.pollFirst());
        }
        //return to min number
        return pair.peekFirst()[0];
        /// 2       1       3   1    -1
        // <2,1>    <1,2>           <-1,5>
    }
}
