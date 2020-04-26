package dataStructure;

import java.util.Arrays;

public class Sort_merge {
    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            System.out.println("---");
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid, temp);   //左边递归拆解
            mergeSort(arr, mid + 1, right, temp);//右边递归拆解
            merge(arr, left, mid, right, temp);    //合并
        }
    }

    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        System.out.println("===");
        int i = left;// 左边索引
        int j = mid + 1;// 右边索引
        int t = 0;// 临时数组索引
        // 左右比较，小的放进去
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {// 左边小，取左边元素填充到temp数组
                temp[t] = arr[i];
                i++;
                t++;
            } else {
                temp[t] = arr[j];
                j++;
                t++;
            }
        }
        // 左右比较以后，剩余的放进去
        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }
        // 讲temp数组拷贝到arr中：
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            tempLeft++;
            t++;
        }
    }
}