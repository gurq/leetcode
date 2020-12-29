package other;

import java.util.Arrays;
import java.util.Queue;

/**
 * 优先级队列
 * @author gurq
 * @date 2020/11/17 12:12 上午
 */
public class PriorityQueue {
    public static void main(String[] args) {
        int[] arr = new int[]{3,2,1,5,6,4,9,8,7};
        System.out.println(Arrays.toString(getList(arr,3)));
    }

    private static int[] getList(int[] arr, int n) {
        if (arr == null || arr.length < n) {
            return arr;
        }

        Queue<Integer> queue = new java.util.PriorityQueue<>((x, y) -> y - x);
        for (int i : arr) {
            queue.offer(i);
            if (queue.size() > n) {
                System.out.println(queue.poll());
            }
        }
        int[] result = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            result[i] = queue.poll();
        }
        return result;
    }
}
