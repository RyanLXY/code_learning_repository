import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * 给出一个长度为n的由正整数组成的序列，你需要从中删除一个整数
 * 然后对删除后的序列求其最长上升子串 得最长长度
 * 上升子串：a_(i+1) > a_i
 *
 * 输入第一行仅包含一个正整数n 表示给出的序列长度（1 <= n <= 100000）
 * 接下来一行有n个正整数 即这个序列 中间用空格隔空 （1 <= n——i <= 100000）
 * 输出仅包含一个正整数 即删除一个数字之后的最长上升子串长度
 */
public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> number = new ArrayList<>();

        while (scanner.hasNext()) {
            int length = scanner.nextInt();

            if (checkLength(length)){

                for(int i=0;i<length;i++){
                    int num = scanner.nextInt();
                    if (checkLength(num)){
                        number.add(num);
                    }
                    else {
                        break;
                    }
                }
            }
            else {
                break;
            }
            String line = scanner.nextLine();   //接收到回车结束
            if (line.equals("")){
                break;
            }
        }

        ArrayList<Integer> maxLength = new ArrayList<>();
        ArrayList<Integer> clone = new ArrayList<>();
        for (int i = 0; i < number.size(); i++) {

//            ArrayList num = number;
            clone.clear();
            for (int j = 0; j < number.size(); j++) {   //Array 数组不可简单使用等号 每个元素都需复制
                clone.add(number.get(j));
            }
            clone.remove(i);

            maxLength.add(lengthOfLongest(clone));
//            for (int k = 0; k < clone.size(); k++) {
//                System.out.println(clone.get(k));
//            }
        }
        Collections.sort(maxLength);
        Collections.reverse(maxLength);

//        for (int i = 0; i < maxLength.size() ; i++) {
//
//            System.out.println(maxLength.get(i));
//        }
        System.out.println(maxLength.get(0));
    }

    /**
     * 检查输入整数是否在范围内
     * @param t 需检查的整数
     * @return  是否超出范围
     */
    public static boolean checkLength(int t) {
        boolean inRange;
        if (t > 100000 || t < 1) {
            System.out.println("长度超出限制");
            inRange = false;
        }
        else {
            inRange = true;
        }
        return inRange;
    }

    /**
     * 找寻上升子串
     * @param nums 需寻找的数组
     * @return 当前数组的最长上升子串的长度
     */

    public static int lengthOfLongest(ArrayList<Integer> nums) {

        if (nums.size() == 0) {
            return 0;
        }
        int[] dp = new int[nums.size()];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < dp.length; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (nums.get(i) > nums.get(j)) {
                    maxval = Math.max(maxval, dp[j]);
                }
            }
            dp[i] = maxval + 1;
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }

}





