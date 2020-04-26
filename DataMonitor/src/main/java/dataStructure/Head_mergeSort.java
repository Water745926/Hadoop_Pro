package dataStructure;

import java.util.Arrays;

/**
 * @description: 手写归并排序
 * @author: lijiayu
 * @create: 2020-04-26 14:29
 */
public class Head_mergeSort {
    public static void main(String[] args) {
        int[] arr={2,5,3,6,4,9,78,5,6,12};
        mergeSort(arr,0,arr.length-1,new int[arr.length]);
        System.out.println(Arrays.toString(arr));
    }

    // 归并排序
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            // 左边
            int mid = left + (right - left) / 2;
            mergeSort(arr,left,mid,temp);
            // 右边
            mergeSort(arr,mid+1,right,temp);
            // 合并
            merge(arr,left,mid,right,temp);
        }
    }
    // 遍历兵比较大小、合并左右两边的数据
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        //左右指针
        int i = left;
        int j = mid + 1;
        int t = 0;
        //一次比较左右大小，直到一方元素比较完毕,
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];i++;t++;
            } else {
                temp[t] = arr[j];j++;t++;
            }
        }
        // 剩余元素拷贝
        while (i <= mid) {
            temp[t] = arr[i];i++;t++;
        }
        while (j <= right) {
            temp[t] = arr[j];j++;t++;
        }
        // 有序元素替换到原数组
        t = 0;
        while (left <= right) {
            arr[left] = temp[t];left++;t++;
        }
    }
}
