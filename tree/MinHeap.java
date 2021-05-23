package tree;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class MinHeap {
    private int[] array;
    private int size;
    public MinHeap(int[] array) {
        if (array == null || array.length ==0) {
            throw new IllegalArgumentException("capacity cannot be null or empty!");
        }
        this.array = array;
        this.size = array.length;
        heapify();
    }
    public MinHeap(int cap) {
        if (cap <= 0) {
            throw new IllegalArgumentException("capacity can not be null or empty");
        }
        array = new int[cap];
        size = 0;
    }
    //TODO 为什么可以heapify？
    // for each node that has at least one child, we perform percolate down
    // in the order of from the node on the deepest level to root
    private void heapify(){
        for (int i = size / 2 - 1; i >=0; i--) {
            percolateDown(i);
        }
    }
    public void offer(int ele) {
        array[size] = ele;
        percolateUp(size);
        size++;
        // resize
        if (size == array.length) {
            int[] newArray = new int[(int) (array.length * 1.5)];
            newArray = Arrays.copyOf(array, array.length);
        }
    }
    private void percolateUp(int index) {
        while (index > 0 && array[index] < array[index / 2 -1]) {
            swap(array,index,index / 2 - 1);
            index = index / 2 -1;
        }
    }
    public int poll() {
        if (size == 0) {
            throw new NoSuchElementException("heap is empty");
        }
        int result = array[0];
        array[0] = array[size - 1];
        size--;
        percolateDown(0);
        return result;
    }
    private void percolateDown(int index) {
       while (index < size / 2 - 1 && array[index] > array[index * 2 + 1]) {
           if (array[index * 2+ 1] < array[index * 2 + 2]) {
               swap(array, index, index * 2 + 1);
               index = index * 2 + 1;
           } else {
               swap(array, index, index * 2 + 2);
               index = index * 2 + 2;
           }
       }
    }
    public int update(int index, int ele) {
        if (index < 0 || index > size -1) {
            throw new ArrayIndexOutOfBoundsException("invalid index range");
        }
        int result = array[index];
        array[index] = ele;
        if (result > ele) {
            percolateUp(index);
        } else {
            percolateDown(index);
        }
        return result;
    }
    public int peek() {
        if (size == 0) {
            throw new NoSuchElementException("heap is empty");
        }
        return array[0];
    }
    public int size() {
        return size;
    }
    public boolean isEmpty(){
        return size ==0;
    }
    public boolean isFull() {
        return size == array.length;
    }

    private void swap(int[] array, int i, int j) {
        int temp = this.array[i];
        this.array[i] = this.array[j];
        this.array[j] = temp;
    }

    public static void main(String[] args) {
        //TODO why heapify the random array into new array, but it does not convert to solid a logic tree
        int[] array = {1,3,10,5,4,13,19,11,8};
        MinHeap heap = new MinHeap(array);
        System.out.println(Arrays.toString(heap.array));
        heap.poll();
        System.out.println(Arrays.toString(heap.array));
        heap.offer(2);
        System.out.println(Arrays.toString(heap.array));
        heap.poll();
        heap.poll();
        heap.offer(1);
        System.out.println(Arrays.toString(heap.array));
    }
}
