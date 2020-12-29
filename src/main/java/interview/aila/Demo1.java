package interview.aila;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author gurq
 * @date 2020/12/3 7:03 下午
 */
public class Demo1 {
    public static void main(String[] args) {
        List<Integer> list = Stream.of(2, 5, 3, 9, 7).collect(Collectors.toList());
        System.out.println(f(list ,12));
        System.out.println(f(list ,8));
        System.out.println(f(list ,13));
    }

    public static boolean f(List<Integer> list, int sum) {
        if (list == null || list.size() < 2) {
            return false;
        }
        if (list.get(0) > sum || list.get(list.size() - 1) < sum / 2) {
            return false;
        }
        Collections.sort(list);
        int leftStart = 0;
        int rightEnd = list.size();
        for (int index = list.size() - 1; index >= 0; index--) {
            if (list.get(index) < sum) {
                rightEnd = index;
                break;
            }
        }
        int index = leftStart + 1;
        int left;
        int right;
        while (leftStart < rightEnd) {
            left = list.get(leftStart);
            right = list.get(index);

            if (index >= rightEnd) {
                leftStart++;
                index = leftStart+1;
                continue;
            }

            if (left + right == sum) {
                return true;
            }else if (left + right > sum) {
                leftStart++;
                index = leftStart+1;
            }else {
                index++;
            }
        }
        return false;
    }

}
