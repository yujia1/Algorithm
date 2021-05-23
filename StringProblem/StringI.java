package StringProblem;
import java.util.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StringI {
    //TODO
    /**
     * right shift
     * "abc", 4 -> "cab"
     * ["abcdefg",39] => ["defgabc"]
     * @param input
     * @param n
     * @return
     */
    public static String rightShift(String input, int n) {
        // Write your solution here
        if(input.length()<= 1) {
            return input;
        }
        char[] arr = input.toCharArray(); // 7
        int shift = n % arr.length; // 4
        reverse(arr, arr.length - shift, arr.length - 1);// abc defg -> abc gfed
        reverse(arr, 0, arr.length - shift - 1); // abc gfed->cba gfed
        reverse(arr, 0, arr.length - 1);//defgabc
        return new String(arr);
    }
    /**
     * reverse a sentence
     * I love google -> google love i
     * @param input
     * @return
     */
    public static String reverseSentence(String input) {
        if(input == null || input.length() <= 1) return input;
        char[] arr = input.toCharArray();
        reverse(arr, 0, arr.length - 1);
        int start = 0;
        // i love google
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] != ' ' && (i == 0 || arr[i - 1] == ' ')) {
                start = i;
            }
            if(arr[i] != ' ' && (i == arr.length - 1 || arr[i + 1] == ' ')){
                reverse(arr, start, i);
            }
        }
        return new String(arr);
    }

    /**
     * replace words
     * input = "appledogapple", S = "apple", T = "cat", input becomes "catdogcat"
     * @param input
     * @param source
     * @param target
     * @return
     */
    public static String replace(String input, String source, String target) {
        // Write your solution here
        return null;
    }
    /**
     * reorder array
     * 123456-> 132436
     * @param array
     * @return
     */
    public static int[] reorder(int[] array) {
        if (array.length % 2 == 0) {
            shuffle(array, 0, array.length - 1);
        } else {
            shuffle(array, 0, array.length - 2);
        }
        return array;
    }
    public static void shuffle(int[] input, int left, int right) {
        int size = right - left + 1;
        if (size <= 2) {
            return;
        }
        int mid = left + size / 2;
        int leftMid = left + size / 4;
        int rightMid = left + size * 3 / 4;
        //ABCD1234
        reverse(input, leftMid, mid-1); // ABDC1234
        reverse(input, mid, rightMid - 1); // ABDC2134
        reverse(input, leftMid, rightMid -1); // AB12CD34

        shuffle(input, left, left + 2 * (leftMid - left) - 1);
        shuffle(input, left + 2 * (leftMid - left), right);
    }
    private static void reverse(int[] input, int i, int j) {
        while (i < j) {
            int temp = input[i];
            input[i] = input[j];
            input[j] = temp;
            i++;
            j--;
        }
    }
    private static void reverse(char[] input, int i, int j) {
        while (i < j) {
            char temp = input[i];
            input[i] = input[j];
            input[j] = temp;
            i++;
            j--;
        }
    }
    private static void rotate(char[] input, int i, int m, int j) {  // abcdef - > efabcd
        reverse(input,i,j);
        reverse(input,i, m-1);
        reverse(input, m, j);
    }




    public static void main(String[] args) {


    }
}


