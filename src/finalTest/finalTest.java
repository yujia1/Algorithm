package src.finalTest;
import java.util.*;
public class finalTest {
    public static List<String> subset(String input){
        char[] set = input.toCharArray();
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        helper1(set, 0, sb, res);
        return res;
    }
    private static void helper1(char[] set, int index, StringBuilder sb, List<String> res) {
        if (index == set.length) {
            res.add(sb.toString());
            return;
        }
        sb.append(set[index]);
        helper1(set, index + 1, sb, res);
        if (index < set.length - 1) { // set.length -1 是给后面letter 留个位置
            sb.append("x");
            helper1(set, index + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.deleteCharAt(sb.length() - 1);
        // 吃吐平衡
    }

    public static void main(String[] args) {
        List<String> sol = subset("ABC");
        for (String a: sol) {
            System.out.println(a);
        }
    }
}
