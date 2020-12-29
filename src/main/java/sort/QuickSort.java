package sort;

import java.util.Arrays;

/**
 * 快排
 * @author gurq
 * @date 2020/11/15 10:22 下午
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};
        quick(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void quick(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int left, int right) {
        int a = left;
        int b = right;
        if (a > b) {
            return;
        }
        int c = arr[a];

        int temp;
        while (a < b) {
            while (a < b && arr[b] >= c) {
                b--;
            }
            while (a < b && arr[a] <= c) {
                a++;
            }
            if (a < b) {
                temp = arr[a];
                arr[a] = arr[b];
                arr[b] = temp;
            }
        }

        arr[left] = arr[a];
        arr[a] = c;

        sort(arr, left, a - 1);
        sort(arr, a + 1, right);
    }
}
