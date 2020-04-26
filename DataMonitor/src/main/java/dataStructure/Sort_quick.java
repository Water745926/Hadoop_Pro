package dataStructure;

import java.util.Arrays;

/**
 * @description: 快速排序
 * @author: lijiayu
 * @create: 2020-04-26 09:15
 */
public class Sort_quick {
    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        //定义变量保存基准数下标、指针i和j
        int base = arr[left];
        int i = left;
        int j = right;

        // 开始排序：
        while (i != j) {
            //找到小的停止
            while (arr[j] >= base && i < j) {
                j--;
            }
            //找到大的停止
            while (arr[i] <= base && i < j) {
                i++;
            }
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        //相遇
        arr[left] = arr[i];
        arr[i] = base;
        //再次交换子区间
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }
}