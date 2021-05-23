package StringProblem;
import java.util.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StringII {
    //TODO

    /**
     * find the common numbers between two sorted arrays a[M], b[N].
     * using two pointers, 谁小移动谁
     * @param a
     * @param b
     * @return
     */
    public static List<Integer> common(int[] a, int[] b) {
        // Write your solution here
        List<Integer> common = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < a.length && j < b.length) {
            if (a[i] == b[j]) {
                common.add(a[i]);
                i++;
                j++;
            } else if (a[i] > b[j]) {
                j++;
            } else {
                i++;
            }
        }
        return common;
    }
    /**
     * find top k frequent words
     * @param combo
     * @param k
     * @return
     */
    public static String[] topKFrequent(String[] combo, int k) {
        // Write your solution here
        if(combo.length ==0) return new String[0];
        HashMap<String,Integer> hashmap = new HashMap<>();

        for(String str: combo) {
            int count = hashmap.getOrDefault(str, 0);
            hashmap.put(str, count + 1);
        }
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(k,

                new Comparator<Map.Entry<String, Integer>>(){
                    @Override
                    public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                        return e1.getValue().compareTo(e2.getValue());
                    }
                });
        for(Map.Entry<String, Integer> entry: hashmap.entrySet()) {
            if(minHeap.size() < k) {
                minHeap.offer(entry);
            } else if(entry.getValue() > minHeap.peek().getValue()) {
                minHeap.poll();
                minHeap.offer(entry);
            }
        }
        String[] res = new String[minHeap.size()];
        for(int i = minHeap.size()-1; i >=0; i--) {
            res[i] = minHeap.poll().getKey();
        }
        return res;

    }

    /** Q2
     *  find common integers between two sorted array
     * @param a
     * @param b
     * @return
     */
    public static List<Integer> commonNumbersII(int[] a, int[] b) {
        List<Integer> common = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < a.length && j < b.length) {
            if (a[i] == b[j]) {
                common.add(a[i]);
                i++;
                j++;
            } else if (a[i] > b[j]) {
                j++;
            } else {
                i++;
            }
        }
        return common;
    }

    /**
     * de-duplicate adjacent repeated characters I
     * "aaaabbbbc" => "abc"
     * @param input
     * @return
     */
    public String deDup(String input) {
        // Write your solution here
        if(input == null) {
            return input;
        }
        char[] array = input.toCharArray();
        int slow = 0;
        // step 1: if fast is different, copy to slow
        for(int fast = 0; fast < array.length; fast++) {
            if(slow == 0 || array[fast] != array[slow -1]) {
                array[slow++] = array[fast];
            }
        }
        return new String(array, 0, slow);
    }

    /**
     * repeatedly de-duplicate
     * "abbbaaccz" => "z"
     * @param input
     * @return
     */
    public static String removeAdjacentRepeated(String input) {
        if (input == null) return null;
        Deque<Character> stack = new ArrayDeque<>();
        char[] arr = input.toCharArray();
        int i = 0;
        while (i < arr.length) {
            char curChar = arr[i];
            if (!stack.isEmpty() && stack.peekFirst() == curChar) {
                while (i < arr.length && arr[i] == curChar) {
                    i++;
                }
                stack.pollFirst();
            } else {
                stack.offerFirst(curChar);
                i++;
            }
        }
        int len = stack.size();
        for (int j = len - 1; j >= 0; j--) {
            arr[j] = stack.pollFirst();
        }
        return new String(arr, 0, len);

    }

    /**
     * find start index of a substring in a string
     * abc, b = > 1
     * @param large
     * @param small
     * @return
     */
    public static int strstr(String large, String small) {
        if(small.length() > large.length()) {
            return -1;
        }
        if(small.length() == 0) {
            return 0;
        }
        // ab , b
        for(int i = 0; i <= large.length() - small.length(); i++) {
            if(equals(large, i, small)) {
                return i;
            }
        }
        return -1;
    }
    private static boolean equals(String large, int i, String small) {
        for(int j = 0; j < small.length(); j++) {
            if(large.charAt(i + j) != small.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    /**
     * remove space
     * "I   love MTV  " -> "I love MTV"
     * @param input
     * @return
     */
    public static String removeSpaces(String input) {
        // Write your solution here
        if(input == null) {
            return input;
        }
        char[] array = input.toCharArray();
        int slow = 0;
        for(int fast = 0; fast < array.length; fast++) {
            //不能copy的情况有 fast == ‘ ’ 并且 condition (开头 或者 前面 '' )，跳过
            if(array[fast] == ' ' && ( fast == 0 || array[fast - 1] == ' ')) {
                continue;
            }
            array[slow++] = array[fast];
        }
        // post-processing
        if(slow > 0 && array[slow-1] ==' ') {
            return new String(array, 0, slow-1);
        }
        return new String(array, 0, slow);
    }
    /**
     * de-duplicate permutations
     * @param input
     * @return
     */
    public static List<String> permutations(String input) {
        if(input == null) {
            return new ArrayList<String>();
        }
        List<String> list = new ArrayList<>();
        char[] arr = input.toCharArray();
        helper(arr, 0, list);
        return list;
    }
    private static void helper(char[] arr, int index, List<String> list) {
        if(index == arr.length) {
            list.add(new String(arr, 0, index));
            return;
        }
        HashSet<Character> used = new HashSet<>();
        for(int i = index; i < arr.length; i++) {
            if(used.add(arr[i])) {
                swap(arr, i, index);
                helper(arr, index + 1, list);
                swap(arr, i, index);
            }
        }
    }
    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * shuffle
     * @param input
     * @param left
     * @param right
     */
    public static void shuffle(char[] input, int left, int right) {
        if (right - left == 1) {
            return;
        }
        int size = right - left + 1;
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
    //TODO encode
    public static String EncodingString(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        char[] array = input.toCharArray();
        return encode(array);
    }
    private static String encode(char[] input) {
        int slow = 0;
        int fast = 0;
        int newLength = 0;
        while (fast < input.length) {
            int begin = fast;
            while (fast < input.length && input[fast] == input[begin]) {
                fast++;
            }
            input[slow++] = input[begin];
            if (fast - begin == 1) {
                newLength +=2;
            } else {
                int len = copyDigits(input, slow, fast- begin);
                slow += len;
                newLength += len + 1;
            }
        }
        char[] res = new char[newLength];
        fast = slow - 1;
        slow = newLength -1;
        while (fast >= 0) {
            if (Character.isDigit(input[fast])) {
                while (fast >= 0 && Character.isDigit(input[fast])) {
                    res[slow--] = input[fast--];
                }
            } else {
                res[slow--] = '1';
            }
            res[slow--] = input[fast--];
        }
        return new String(res);
    }
    private static int copyDigits(char[] input, int index, int count) {
        int len = 0;
        for (int i = count; i >0 ; i /= 10) {
            index++;
            len++;
        }
        for (int i = count; i >0; i /=10) {
            int digit = i % 10;
            input[--index] = (char) ('0' + digit);
        }
        return len;
    }
    //TODO decode

    //TODO sliding window
    // Q4.1,

    /**Q4.1
     * longest substring that contains only unique char
     * @param input
     * @return
     */
    public static int longestSubstring(String input) {
        HashSet<Character> distinct = new HashSet();
        int slow = 0;
        int fast = 0;
        int longest = 0;
        while( fast < input.length()) {
            if (distinct.contains(input.charAt(fast))) {
                distinct.remove(input.charAt(slow++));
            } else {
                distinct.add(input.charAt(fast++));
                longest =Math.max(longest, fast - slow);
            }
        }
        return longest;
    }

    /** 4.2
     * find all anagrams of a substring s2 in a long string s1
     * @param s
     * @param l
     * @return
     */
    public static List<Integer> allAnagrams(String s, String l) {
        List<Integer> result = new ArrayList<>();
        if (l.length() == 0) return result;

        if (s.length() > l.length()) {
            return result;
        }
        Map<Character, Integer> map = countMap(s);
        int match = 0;
        for (int i = 0; i < l.length(); i++) {
            char temp = l.charAt(i);
            Integer count = map.get(temp);
            if (count != null) {
                map.put(temp, count -1);
                if (count == 1) {
                    match++;
                }
            }
            if (i >= s.length()) {
                temp = l.charAt(i - s.length());
                count = map.get(temp);
                if (count != null) {
                    map.put(temp, count + 1);
                    if (count == 0) {
                        match--;
                    }
                }
            }
            if (match == map.size()) {
                result.add(i-s.length() + 1);
            }
        }
        return result;
    }
    private static Map<Character, Integer> countMap(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch: s.toCharArray()) {
            Integer count = map.getOrDefault(ch, 0);
            map.put(ch, count + 1);
        }
        return map;
    }
    //TODO
    /** 4.3
     * 0111110111110111111011110 find longest substrig of including most k = 4;
     * @param input
     * @param k
     * @return
     */
    public static int findLongestSubArray(int[] input, int k) {
        int slow = 0;
        int fast = 0;
        int count = 0;
        int longest = 0;
        while (fast < input.length) {
            if (input[fast] == 1) {
                longest = Math.max(longest, ++fast - slow);
            } else if (count < k) {
                count++;
                longest = Math.max(longest, ++fast - slow);
            } else if (input[slow++] == 0) { //Question, why input[slow++]
                count--;
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        int[] arr = {1,1,0,0,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,0};
        System.out.println(findLongestSubArray(arr, 0));
    }
}

