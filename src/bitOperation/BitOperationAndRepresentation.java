package bitOperation;

public class BitOperationAndRepresentation {
    //第一遍 intelj
    //第二遍laicode
    //第三遍 summary
     /**
     * find power of two
     * 4 = 0000 0000 0000 0000 0000 0000 0000 0100
     * @param number
     * @return
     */
    public static boolean isPowerOfTwoI(int number) {
        if (number <= 0) {
            return false;
        }
        while ((number & 1) == 0) {
            number >>>= 1;
        }
        return number == 1;
    }
    /**
     * a = a ^ b will make all diff bit on a return to 1, then count how many 1 on a
     * @param a
     * @param b
     * @return
     */
    public static int diffBits(int a , int b) {
        a ^= b;
        int count = 0;
        while (a != 0) {
            count += a&1;
            a =  a >> 1;
        }
        return count;
    }

    /**
     * we are using ASCII encoding and the number of valid letters is 256
     * encoded from 0 - 255
     * @param word
     * @return
     */
    public static boolean allUnique(String word) {
        // each int value can represent 32 bit, so we need 8 ints to represent 256 bits
        int[] vec = new int[8];
        char[] array = word.toCharArray();
        for (char c: array) {
            // for a value c in the range of 0-255
            //it is actually mapped to the int valye at c/ 32 as index
            // and the c % 32'th bit of the int value
            if ((vec[c / 32] >>> (c%32) & 1) != 0) {
                // 先定位到 c/32index 然后位移右 c%32 bit 位， & 1, 如果！= 0,
                //说明c%32'th 有 1
                return false;
            }
            // 0 代表没有该letter, 1代表有letter
            vec[c / 32] = vec[c / 32] | 1<< (c %32) ;

        }
        return true;
    }
    //TODO
    public static long reverseI(long num) {
        for (int offset = 0; offset < 16; ++offset) {
            long right = (num >> offset) & 1L;
            long left = (num >>(31 - offset)) & 1L;
            if (left != right) {
                num ^= (1L << offset);
                num ^= (1L<<(31 - offset));
            }
        }
        return num;
    }
//    public static long reverseII(long num) {
//        num = ((num & 0xFFFF0000) >>> 16) |(num & 0x0000FFFF) <<16);
//        num = ((num & 0xFF00FF00) >>> 8) |(num & 0x00FF00FF) <<8);
//        num = ((num & 0xF0F0F0F0) >>> 4) |(num & 0x0F0F0F0F) <<4);
//        num = ((num & 0xCCCCCCCC) >>> 2) |(num & 0x33333333) <<2);
//        num = ((num & 0xAAAAAAAA) >>> 1) |(num & 0x55555555) <<1);
//        return num;
//    }
    public static String hexRepresentation(int number) {
        String prefix = "0x";
        //handle the special case of 0 first
        if (number ==0) {
            return prefix + "0";
        }
        //using stringBuilder so append operation is more efficient
        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            int rem = number % 16;
            if (rem < 10) {
                sb.append((char) ('0' + rem));
            } else {
                sb.append((char) (rem - 10 + 'A'));
            }
            number >>>= 4;
        }
        // reverse it at last so in all complexity is O(N)
        return prefix + sb.reverse().toString();

    }
    public static void main(String[] args) {
        String a = "abcda";
        System.out.println(allUnique(a));
    }
}
