package targettooffer;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Demo05 {
    public static void main(String[] args) {

    }

    public String replaceSpace(String s) {
        /**
         *         执行用时：
         *         0 ms
         *         , 在所有 Java 提交中击败了
         *         100.00%
         *         的用户
         *         内存消耗：
         *         36.2 MB
         *         , 在所有 Java 提交中击败了
         *         81.26%
         *         的用户
         */
//        return s.replace(" ", "%20");

        /**
         * 执行用时：
         * 6 ms
         * , 在所有 Java 提交中击败了
         * 5.34%
         * 的用户
         * 内存消耗：
         * 36.7 MB
         * , 在所有 Java 提交中击败了
         * 30.44%
         * 的用户
         */
//        return Arrays.stream(s.split(""))
//                .map(i->" ".equals(i) ? "%20" : i)
//                .collect(Collectors.joining());

        /**
         * 执行用时：
         * 3 ms
         * , 在所有 Java 提交中击败了
         * 13.43%
         * 的用户
         * 内存消耗：
         * 36.7 MB
         * , 在所有 Java 提交中击败了
         * 25.06%
         * 的用户
         */
//        StringBuilder sb = new StringBuilder();
//        for (String item : s.split("")) {
//            if (" ".equals(item)) {
//                sb.append("%20");
//            }else {
//                sb.append(item);
//            }
//        }
//        return sb.toString();

        /**
         * 执行用时：
         * 3 ms
         * , 在所有 Java 提交中击败了
         * 13.43%
         * 的用户
         * 内存消耗：
         * 36.5 MB
         * , 在所有 Java 提交中击败了
         * 42.54%
         * 的用户
         */
        String[] arr = s.split("");
        for (int i = 0; i < arr.length; i++) {
            if (" ".equals(arr[i])) {
                arr[i] = "%20";
            }
        }
        return String.join("",arr);
    }
}
