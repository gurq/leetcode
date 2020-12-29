package sort;

import java.util.Arrays;

/**
 * 归并排序
 * @author gurq
 * @date 2020/11/15 11:46 下午
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int[] temp = new int[arr.length];
        sort(arr, temp, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int[] temp, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        sort(arr, temp, left, mid);
        sort(arr, temp, mid + 1, right);
        merge(arr, temp, left, mid, right);
    }

    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        int a = left;
        int b = mid + 1;
        int c = 0;

        while (a <= mid && b <= right) {
            if (arr[a] <= arr[b]) {
                temp[c++] = arr[a++];
            } else {
                temp[c++] = arr[b++];
            }
        }

        while (a <= mid) {
            temp[c++] = arr[a++];
        }

        while (b <= right) {
            temp[c++] = arr[b++];
        }

        c = 0;
        while (left <= right) {
            arr[left++] = temp[c++];
        }
    }
}
