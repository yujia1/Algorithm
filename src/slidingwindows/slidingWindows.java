package slidingwindows;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class slidingWindows {
    public int MiniSizeSubSum(int[] nums, int target){
        if (nums == null || nums.length == 0) return 0;
        int left = 0, right = 0, sum = 0, ans = Integer.MAX_VALUE;
        for (right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target) {
                ans = Math.min(ans, right + 1 - left);
                sum -= nums[left++];
            }
        }
        return ans != Integer.MAX_VALUE ? ans : 0;
    }
    //max consecutive one
    public int longestSubNoDup(String s) {
         if ( s == null || s.length() == 0) return 0;
         Set<Character> set = new HashSet<>();
         int left = 0, right = 0, ans = 1;
         for (right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            while (set.contains(c)) {
                set.remove(s.charAt(left++));
            }
            set.add(c);
             ans = Math.max(ans, right -left + 1);
         }
        return ans;
    }
//    public int longestSubMostTwoDistinctCharacters(){}
//    public int longestRepeatingCharacterReplacement(){}

    public List<Integer> findAllAnagramsInString(String s, String p){
        List<Integer> soln = new ArrayList<>();
        if (s.length() == 0 || p.length() == 0 || s.length() < p.length()) {
            return soln;
        }
        //set up character hash (counts of each char in the anagram
        int[] chars = new int[26];
        for (Character c: p.toCharArray()) {
            chars[c-'a']++;
        }
        int start = 0, end = 0, len = p.length();
        int diff = len;
        char temp;
        for (end = 0; end < s.length(); end++) {
            if (end - start >= len) {
                temp = s.charAt(start);
                chars[temp - 'a']++;
                if (chars[temp -'a'] > 0) {
                    diff++;
                }
                start++;
            }
            temp = s.charAt(end);
            chars[temp - 'a']--;

            if (chars[temp - 'a'] >= 0) {
                diff--;
            }
            if (diff == 0) {
                soln.add(start);
            }
        }
        return soln;
    }

    //permutation in a string
    //minimum window substring

}
