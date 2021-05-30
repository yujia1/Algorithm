package DynamicProgramming.facebookInterview;

import java.util.HashMap;
import java.util.Map;

public class facebookInterview {
    public boolean checkSubarraySum(int[] nums, int k) {
        //if (k ==0) return false
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        //23,2,4,6,7
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i]; //23, 5+2, 5
            if (k != 0) sum = sum % k; // 23 % 6 = 5, 7 % 6 = 1, 5 % 6 = 5;
            if (map.containsKey(sum)) {
                if (i - map.get(sum) >= 2) return true;
            } else {
                map.put(sum, i); // <5,0>, <1,1>
            }
        }
        return false;
    }
    public static void main(String[] args) {
        facebookInterview test = new facebookInterview();
        int[] nums = new int[]{23,2,4,6,7};
        System.out.print(test.checkSubarraySum(nums, 6));
    }
}
