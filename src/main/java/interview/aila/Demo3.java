package interview.aila;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author gurq
 * @date 2020/12/3 7:47 下午
 */
public class Demo3 {
    public static void main(String[] args) {
        int[] arr = {3, 3, 9, 4, 9, 2, 2, 8, 9, 9, 8, 9};
        Map<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < arr.length; i++) {
            int count = treeMap.getOrDefault(arr[i], 0);
            treeMap.put(arr[i], count + 1);
        }
        System.out.println(treeMap.keySet());
    }

}
