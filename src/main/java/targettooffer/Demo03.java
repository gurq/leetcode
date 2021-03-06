package targettooffer;

import java.util.HashSet;
import java.util.Set;

/**
 * 找出数组中重复的数字。
 *
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 *
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *
 *
 * 限制：
 *
 * 2 <= n <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gurq
 * @date 2020/11/29 1:40 下午
 */
public class Demo03 {
    public static void main(String[] args) {
        // 思路：用哈希表做，遍历的时候先判断存不存在，不存在即为所求值，set基本只算一次hash，很快
        int[] arr = {2, 3, 1, 0, 2, 5, 3};
        System.out.println(getSingle(arr));
    }

    private static int getSingle(int[] arr){
        if (arr == null || arr.length < 1) {
            return -1;
        }
        Set<Integer> set = new HashSet<>(arr.length);
        for (int temp : arr) {
            if (set.contains(temp)) {
                return temp;
            }
            set.add(temp);
        }
        return -1;
    }


}
